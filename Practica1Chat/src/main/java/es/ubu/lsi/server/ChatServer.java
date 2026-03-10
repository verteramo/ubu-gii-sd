package es.ubu.lsi.server;

import java.net.ServerSocket;

import es.ubu.lsi.common.ChatMessage;

/**
 * Chat server.
 * 
 * @author ...
 *
 */
public interface ChatServer {

	/**
	 * Starts server.
	 */
	public abstract void startup();

	/**
	 * Shutdowns server.
	 */
	public abstract void shutdown();

	/**
	 * Broadcasts a message to all clients.
	 * 
	 * @param message message
	 */
	public abstract void broadcast(ChatMessage message);

	/**
	 * Removes a logout client.
	 * 
	 * @param id client id
	 */
	public abstract void remove(int id);

}