package org.verteramo;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

import es.ubu.lsi.common.ChatMessage;
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
				//thread.start();
			}

			shutdown();
		} catch (IOException e) {
			show("ServerSocket: %s", e.getMessage());
		} catch (ClassNotFoundException e) {
			show("ClassNotFoundException: %s", e.getMessage());
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
}
