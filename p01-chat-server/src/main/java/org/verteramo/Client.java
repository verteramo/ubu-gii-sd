package org.verteramo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Runnable {
    
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private int id;
    private String username;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void write(String message) throws IOException {
        outputStream.writeObject(message);
    }

    public Message read() throws IOException, ClassNotFoundException {
        return (Message) inputStream.readObject();
    }

    public Client(Socket socket, int id) throws IOException, ClassNotFoundException {
        this.id = id;
        
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());

        this.username = (String) inputStream.readObject();
    }

    public void shutdown() throws IOException {
        inputStream.close();
        outputStream.close();
    }

    public boolean isAlive() {
        return true;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}
