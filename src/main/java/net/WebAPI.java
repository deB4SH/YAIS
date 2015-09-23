package net;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class WebAPI extends WebSocketServer{

    private int port;
    public WebAPI(int port) throws UnknownHostException{
        super( new InetSocketAddress(port) );
        this.port = port;
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        System.out.println("NEW CONNECTION");
        System.out.println(clientHandshake.getResourceDescriptor());
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println("client left the room");
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        System.out.println(message);
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }
}
