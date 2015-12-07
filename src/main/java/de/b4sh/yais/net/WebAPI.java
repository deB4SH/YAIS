package de.b4sh.yais.net;

import com.mongodb.util.JSON;
import de.b4sh.yais.YAIS;
import de.b4sh.yais.misc.LogType;
import de.b4sh.yais.misc.LogWriter;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class WebAPI extends WebSocketServer{

    private int port;
    private MessageInterpreter mI;
    public WebAPI(int port, MessageInterpreter mI) throws UnknownHostException{
        super( new InetSocketAddress(port) );
        this.port = port;
        this.mI = mI;
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        if(YAIS.DEBUG){
            LogWriter.logToConsole(LogType.debug, "WS: new connection | descriptor: " + clientHandshake.getResourceDescriptor());
        }
    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        System.out.println("client left the room");
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {

        Object jsonFile = JSON.parse(message);

        int b = 0;
        LogWriter.logToConsole(LogType.debug,"test");
        mI.renderIncommingMessage(jsonFile);

        if(YAIS.DEBUG){
            LogWriter.logToConsole(LogType.debug, message);
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {

    }
}
