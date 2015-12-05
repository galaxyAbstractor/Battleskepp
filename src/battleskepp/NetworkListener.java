package battleskepp;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * This is the network listener that listens for network happenings, lika a connecting client or packets
 * @author galaxyAbstractor
 */
public class NetworkListener extends Listener {
    private Game game = Game.getInstance();

    /**
     * Called when an object is received from a sender.
     * @param connection The connection from where we got the object
     * @param object The object sent
     */
    @Override
    public void received (Connection connection, Object object) {
        // Make sure the object is of type Packet
        if(object instanceof Packet){
            // We know it's a packet so we have to typecast it to use it
            Packet packet = (Packet) object;
            
            // Get the messageid
            int messageid = packet.messageid;
            
            /*
             * Check what we should do
             * 
             * 1: Ready identifier
             * 2: Start game identifier
             * 3: Shot identifier
             * 4: Hit identifier
             * 5: Miss identifier
             * 6: Game over identifier
             * 7: Sunken ship identifier
             * 
             */
            switch(messageid){
                case 1:
                    game.opponentReady();
                    break;
                case 2:
                    game.startGame();
                    break;
                case 3:
                    game.checkHit(packet.x, packet.y);
                    break;
                case 4:
                case 5:
                    game.didHit(messageid, packet.x, packet.y);
                    break;
                case 6:
                    game.setOver(true);
                    game.endgame(true);
                    break;
                case 7:
                    game.setSunkText(packet.message);
                    break;
                    
            }
            
        }
    }
    
    /**
     * Someone connected to the server and we need to start the game
     * @param connection the connected person
     */
    @Override
    public void connected (Connection connection) {
        System.out.println(connection.getRemoteAddressTCP() +" connected");
        game.connected();
    }
}
