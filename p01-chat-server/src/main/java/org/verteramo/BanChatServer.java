package org.verteramo;

import java.util.Iterator;

import es.ubu.lsi.common.ChatMessage;

public class BanChatServer extends AbstractChatServer {

    protected BanChatServer(int port) {
        super(port);
    }

    @Override
    public synchronized void broadcast(ChatMessage message) {
        Iterator<Client> clients = getClients();

        while (clients.hasNext()) {
            Client client = clients.next();

            if (client.isAlive()) {
                handleMessage(message, client.getId());
            } else {
                clients.remove();
                show("Disconnected Client %s removed from list.", client.getUsername());
            }
        }
    }

    @Override
    protected boolean handleMessage(ChatMessage chatMessage, int clientId) {
        throw new UnsupportedOperationException("Unimplemented method 'handleMessage'");
    }

}
