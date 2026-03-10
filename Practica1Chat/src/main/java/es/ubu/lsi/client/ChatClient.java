package es.ubu.lsi.client;

import es.ubu.lsi.common.ChatMessage;

/**
 * Chat client.
 * 
 * @author ...
 *
 */
public interface ChatClient {

	/**
	 * Starts chat.
	 * 
	 * @return true if everything goes right, false in other case
	 */
	public abstract boolean start();

	/**
	 * Sends a message to the server.
	 * 
	 * @param msg message
	 */
	public abstract void sendMessage(ChatMessage msg);

	/**
	 * Disconnect client closing resources.
	 */
	public abstract void disconnect();

}