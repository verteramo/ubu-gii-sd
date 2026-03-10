package org.verteramo;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

import es.ubu.lsi.common.ChatMessage;
import es.ubu.lsi.common.ChatMessage.MessageType;
import es.ubu.lsi.server.ChatServer;

public abstract class AbstractChatServer implements ChatServer {

	private static final int DEFAULT_PORT = 1500;

	private static int clientId;

	private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	private boolean alive;

	private int port;

	private List<Client> clients = new ArrayList<Client>();

	private ServerSocket serverSocket;

	protected abstract boolean handleMessage(ChatMessage chatMessage, int clientId);

	protected AbstractChatServer(int port) {
		this.port = port;
	}

	protected void show(String format, Object... args) {
		System.out.println(String.format("[%s] %s", sdf.format(new Date()), String.format(format, args)));
	}

	protected Iterator<Client> getClients() {
		return clients.iterator();
	}

	@Override
	public void startup() {
		alive = true;

		try {
			serverSocket = new ServerSocket(port);

			while (alive) {
				show("Listening on port %d.", port);
				Socket socket = serverSocket.accept();
				Client thread = new Client(socket, clientId++);
				clients.add(thread);
				thread.start();
			}

			shutdown();
		} catch (IOException e) {
			show("ServerSocket: %s", e.getMessage());
		}
	}

	@Override
	public synchronized void shutdown() {
		// Se cierra el socket del servidor para que deje de aceptar clientes
		try {
			serverSocket.close();
		} catch (Exception e) {
			show("Exception closing the server: %s", e.getMessage());
		}

		// Se cierra la conexión con cada cliente
		for (Client client : clients) {
			try {
				// Cada cliente sabe cómo cerrar su conexión
				client.shutdown();
			} catch (IOException e) {
				show("Error closing client connection: %s", e.getMessage());
			}
		}
	}

	@Override
	public synchronized void remove(int id) {
		clients.remove(id);
	}

	private final static String USAGE = "Usage is: > java Server [portNumber]";

	protected static int getPort(String[] args) {
		if (args.length == 0) {
			return DEFAULT_PORT;
		} else if (args.length == 1) {
			return Integer.parseInt(args[0]);
		} else {
			throw new IllegalArgumentException(USAGE);
		}
	}

	class Client extends Thread {

		private Socket socket;
		private ObjectInputStream sInput;
		private ObjectOutputStream sOutput;

		private int id;
		private String username;

		public int getClientId() {
			return id;
		}

		public String getClientUsername() {
			return username;
		}

		public void shutdown() throws IOException {
			socket.close();
			sInput.close();
			sOutput.close();
		}

		private void init() throws IOException, ClassNotFoundException {
			sOutput = new ObjectOutputStream(socket.getOutputStream());
			sInput = new ObjectInputStream(socket.getInputStream());

			username = (String) sInput.readObject();
			sOutput.writeInt(id);
			sOutput.flush();

			show("User %s (id: %d) just connected.", username, id);
			ChatMessage chatMessage = new ChatMessage(id, MessageType.MESSAGE, username + " just connected.");
			broadcast(chatMessage);
		}

		private Client(Socket socket, int id) {
			this.id = id;
			this.socket = socket;

			show("Server thread trying to create I/O streams for client");

			try {
				init();
			} catch (IOException e) {
				show("Exception creating new I/O Streams: %s", e.getMessage());
				return;
			} catch (ClassNotFoundException e) {
				close(); // organized panic case...
				throw new RuntimeException(String.format("Wrong message type: %s", e.getMessage()));
			}
		}

		public void run() {
			// to loop until LOGOUT
			boolean runningThread = true;
			ChatMessage chatMessage = null;
			while (runningThread) {
				// read a String (which is an object)
				try {
					chatMessage = (ChatMessage) sInput.readObject();
				} catch (IOException e) {
					show(username + " Exception reading Streams: " + e);
					break;
				} catch (ClassNotFoundException e2) {
					close(); // organized panic case...
					throw new RuntimeException("Wrong message type", e2);
				}

				// Switch on the type of message received
				switch (chatMessage.getType()) {
					case SHUTDOWN:
						show(username + " shutdown chat system.");
						runningThread = false;
						alive = false;
						break;
					case MESSAGE:
						chatMessage.setMessage(username + ": " + chatMessage.getMessage());
						broadcast(chatMessage);
						break;
					case LOGOUT:
						show(username + " disconnected with a LOGOUT message.");
						chatMessage.setMessage(username + " leaving chat room!");
						broadcast(chatMessage);
						runningThread = false;
						break;

				} // switch
			}
			// remove myself from the arrayList containing the list of the
			// connected Clients
			remove(id);
			show("Removing " + username + " with id: " + id);
			close();

			if (!alive) { // if was a shutdown close server
				shutdown();
			}
		}

		private void sendMessage(ChatMessage msg) throws IOException {
			sOutput.writeObject(msg);
		}

		/**
		 * Close streams and socket for client.
		 */
		private void close() {
			// try to close the connection
			try {
				if (sOutput != null)
					sOutput.close();
				if (sInput != null)
					sInput.close();
				if (socket != null && socket.isConnected() && !socket.isClosed())
					socket.close();

			} catch (Exception e) {
				show("Closed streams and socket");
			}
		}

	}
}
