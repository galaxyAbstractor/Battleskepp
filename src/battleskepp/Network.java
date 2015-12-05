package battleskepp;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Server;
import java.io.IOException;

/**
 *
 * @author galaxyAbstractor
 */
public class Network {
    private static Server server;
    private static Client client;
    
    /**
     * Connects to the IP given. If connection didn't work out an IOException is thrown so an error can be shown somewhere in line
     * @param ip The IP to connect to
     * @throws IOException 
     */
    public void connect(String ip) throws IOException{
        // First, stop and reset eventual existing server/client from a previous game, then create a new Client
        stop();
        if(client == null) client = new Client();
        
        // Get the data serializer (Kryo) and register our Packet class
        Kryo kryo = client.getKryo();    
        kryo.register(Packet.class);
        
        // Start the client and connect to the given IP on TCP port 1337
        client.start();
        client.connect(30000, ip, 1337);
        client.setKeepAliveTCP(5000);
        
        // Add our NetworkListener
        client.addListener(new NetworkListener());
    }
    
    /**
     * Creates a server a client can connect to. Throws an IOException if something went wrong.
     * @throws IOException 
     */
    public void create() throws IOException {
        // First, stop and reset eventual existing server/client from a previous game, then create a new Server
        stop();
        if(server == null) server = new Server();
        
        // bind the server to TCP port 1337
        server.bind(1337);
        
        // Get the serializer and register our packet class
        Kryo kryo = server.getKryo();    
        kryo.register(Packet.class);
        
        // Start the server and add our listener
        server.start();
        server.addListener(new NetworkListener());
    }
    
    /**
     * This sends the packet to the receiver.
     * @param packet The packet to send
     */
    public void send(Packet packet){
        // Check if we are the client or the server. If we are the client, send the packet to the server, otherwise send it to the first connected client
        if(client != null) {
            client.sendTCP(packet);
        } else {
            server.sendToTCP(server.getConnections()[0].getID(), packet);
        }
    }

    /**
     * Stop and resets the server/client back to their initial values.
     */
    public void stop() {
        if(server != null) server.stop();
        if(client != null) client.close();
        
        server = null;
        client = null;
    }
    
    /**
     * Return if we are the server or not
     * @return true if we are the server, otherwise false
     */
    public boolean isServer(){
        return (server != null);
    }
    
}