package battleskepp;

import battleskepp.ships.Boat;
import battleskepp.ships.Ferry;
import battleskepp.ships.Submarine;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
    
    private static Game game = null;
    private Network network = new Network();
    
    private PlayerFieldPanel playerField;
    private EnemyFieldPanel enemyField;
    private Main main;
    
    private boolean over = false;
    
    private boolean readyMode = false;
    private boolean opponentReady = false;
    
    private ArrayList<Ship> ships = new ArrayList<Ship>();
    
    /**
     * Returns the game object
     * @return the game object
     */
    public static Game getInstance(){
        // if game is not yet created, create it, then return it
        if(game == null) game = new Game();
        return game;
    }
    
    /**
     * Sets the fields for the players
     * @param player the players field
     * @param enemy the enemys field
     */
    public void createFields(PlayerFieldPanel player, EnemyFieldPanel enemy, Main main){
        playerField = player;
        enemyField = enemy;
        this.main = main;
        placeShips();
    }
    /**
     * Create the different ships and place them on the board, and adjust their ZOrder to be placed ontop
     */
    public void placeShips(){
        Submarine ship1 = new Submarine(0, 0, true);
        playerField.add(ship1);
        playerField.setComponentZOrder(ship1, 0);
        
        Submarine ship2 = new Submarine(6, 4, false);
        playerField.add(ship2);
        playerField.setComponentZOrder(ship2, 0);
        
        Ferry ship3 = new Ferry(4, 7, true);
        playerField.add(ship3);
        playerField.setComponentZOrder(ship3, 0);
        
        Ferry ship4 = new Ferry(6, 0, false);
        playerField.add(ship4);
        playerField.setComponentZOrder(ship4, 0);
        
        Boat ship5 = new Boat(4, 2, true);
        playerField.add(ship5);
        playerField.setComponentZOrder(ship5, 0);
        
        Boat ship6 = new Boat(3, 5, false);
        playerField.add(ship6);
        playerField.setComponentZOrder(ship6, 0);
        
        playerField.validate();
        playerField.repaint();
        
        ships.add(ship1);
        ships.add(ship2);
        ships.add(ship3);
        ships.add(ship4);
        ships.add(ship5);
        ships.add(ship6);
    }
    
    /**
     * Shoot one of the opponents coordinates
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public void shoot(int x, int y){
        // Disable the enemyfield and inform whose turn it is
        enemyField.disableField();
        main.setStatus("Opponents turn");
        
        // We create a network package and assign it's data to it. Then send it over the network
        Packet packet = new Packet();
        packet.x = x;
        packet.y = y;
        packet.messageid = 3; // 3 is the messageid for shooting
        network.send(packet);
        
    }
    
    /**
     * Checks if the enemy (received shot) shot hit a ship or not
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public void checkHit(int x, int y){
        // Create a new packet, check if the shot hit and notify the enemy, then enable the field if the game isn't over
        Packet packet = new Packet();
        packet.messageid = playerField.checkHit(x, y); // 4 is hit, 5 is miss
        packet.x = x;
        packet.y = y;
        
        if(packet.messageid == 4){
            for(int i = 0; i < ships.size(); i++){
                if(ships.get(i).checkCoordinate(x, y)){
                    ships.get(i).hit();
                }
            }
        }
        
        network.send(packet);
        if(!over) {
            enemyField.enableField();
            main.setStatus("Your turn");
        }
    }
    
    /**
     * Catch the enemy reporting back if our shot hit their ship or not
     * @param didHit If we hit, 4 is a hit, 5 is a miss
     * @param x the x-coordinate of the hit
     * @param y the y-coordinate of the hit
     */
    public void didHit(int didHit, int x, int y){
        enemyField.didHit(didHit, x, y);
        
        enemyField.disableField();
    }
    
    /**
     * Notify that we are ready by sending a packet to the opponent
     */
    public void notifyReady(){
        Packet packet = new Packet();
        packet.messageid = 1; // 1 is the id for ready
        network.send(packet);
    }
    
    /**
     * The opponent has notified he has pressed the ready-toggle and we should indicate (and keep track of) it
     */
    public void opponentReady(){
        // invert the readiness for each time it's called
        opponentReady = !opponentReady;
        
        // Update the status
        if(opponentReady){
            main.setStatus("Opponent is ready"); 
        } else {
            main.setStatus("Opponent is not ready");
        }
        
        // If both we and the opponent is ready, let's start the game
        if(main.weReady() && opponentReady){
            Packet packet = new Packet();
            packet.messageid = 2; // 2 indicates the id of a start game
            network.send(packet);
            startGame();
        }
    }
    
    /**
     * Stop the networking
     */
    public void stop() {
        network.stop();
    }
    
    /**
     * Starts the game, when both players has placed their ships and pressed ready
     */
    public void startGame(){
        // turn of readymode, so you cannot move ships anymore
        readyMode = false;
        
        // The server should always start with the first move
        if(network.isServer()){
            playerField.disableField();
            enemyField.enableField();
        } else {
            playerField.disableField();
        }
        main.enableReadyToggle(false);
    }
    
    /**
     * Checks all the checkboxes in the pixelarea the ships enclose. It returns true
     * on success and false if it wasn't succeeding, like if it was an illegal move.
     * It also works in reverse, in other words, unchecks the boxes, should the 
     * player decide to move the ship
     * @param x the x-coordinate the ship starts on (pixels)
     * @param width the width (x-size) of the ship
     * @param y the y-coordinate the ship starts on (pixels)
     * @param height the height (y-size) of the ship
     * @param reverse if it should work in reverse or not
     * @return if it succeeded or not
     */
    public boolean checkBoxes(int x, int width, int y, int height, boolean reverse){
        // Translate the pixels to real starting coordinates on the field (like x: 5, y:2)
        // There's 31px between every checkbox
        // and the field is positioned 101px in x-axis on the panel, and 50px in y-axis
        int countX = (x-101)/31;
        int countY = (y-50)/31;
        
        // Check that the ship is inside the field
        if(countX+width <= 10 && countX >= 0 && countY+height <= 10 && countY >= 0) {
            
            // Check if the ship is colliding (not being placed on or around another ship) and return false if it does
            // Only do this if we are trying to place a ship, it has no use when moving a ship
            if(!reverse){
                if(playerField.isColliding(countX, (countX+width)-1, countY, (countY+height)-1)) return false;
            }
            
            // If we reached here, it means it's a valid move. Start by selecting checkboxes in the x-axis
            for(int i = countX; i < countX+width;i++){
                playerField.select(i, countY, reverse);

            }
            
            // Then in the y-axis. This is because the ships has no real rotation attribute, and is just different sizes
            for(int i = countY; i < countY+height;i++){
                playerField.select(countX, i, reverse);
            }
            
            // Return true as we succeeded, there was no collision
            return true;
    
        }
        
        // The ship is trying to be placed outside the field
        return false;
            
    }
    
    /**
     * The game has ended, we call this method. It notifies win and resets everything to their initial values
     * @param won true if we won, false if not
     */
    public void endgame(boolean won){

        if(won){
            main.setStatus("You won!");
        } else {
            main.setStatus("You lost!");
        }
        
        // Reset the fields
        playerField.reset();
        enemyField.reset();
        
        // Disable them
        playerField.disableField();
        enemyField.disableField();
        
        // Reset the rest
        playerField.setSunkText("");
        enemyField.setSunkText("");
        
        opponentReady = false;
        
        main.enableConnectCreateBtn(true);
        
    }

    /**
     * Creates a game (and the server). The port used is 1337, as defined in the network class.
     * If a server cannot be created/started, we pass on an IOException to the GUI to show an error.
     * @throws IOException 
     */
    public void createGame() throws IOException {
        // Reset game over
        over = false;
        
        // Create the server, disable the create/connect buttons, put us in ready-mode
        network.create();
        main.enableConnectCreateBtn(false);
        readyMode = true;
    }

    /**
     * Connects to a game as a client. If unable to connect, an IOException is passed on to the GUI so an error can be shown
     * @param ip the IP to connect to
     * @throws IOException 
     */
    public void connect(String ip) throws IOException {
        // Reset game over, then connect
        over = false;
        network.connect(ip);
    }

    /**
     * Return if we are in ready-mode
     * @return 
     */
    public boolean isReadyMode() {
        return readyMode;
    }
    
    /**
     * This checks the checkboxes of each ship when called. Used when starting a new game.
     */
    public void checkShips(){
        for(int i = 0; i < ships.size(); i++){
            ships.get(i).check();
        }
    }
    
    /**
     * When a client has connected to the server this method is called. It sets some initial
     * values like turning on ready-mode (the "place your ships" phase), checks ships and enables/disables buttons.
     */
    public void connected(){
        // Hide the "waiting for connection" dialog
        main.hideWaitingForPlayer();
        
        readyMode = true;
        
        // Check the ships, see above
        checkShips();
        
        main.setStatus("Opponent is not ready");
        main.enableReadyToggle(true);
        main.enableConnectCreateBtn(false);
    }
    
    /**
     * The game is over (and we lost, this is called when we detect all our ships being sunk). Set the over variable to true to indicate gameover, 
     * create a new packet and send it to the opponent to notify him that he won. Then end the game, losing.
     */
    public void over() {
        over = true;
        Packet packet = new Packet();
        packet.messageid = 6; // 6 is the id of game over
        network.send(packet);
        endgame(false);
    }
    
    /**
     * This is called when all the modules (checkboxes) of a ship has been sunk, sinking the whole ship
     * It notifies the player that his ship x is dead and notifies the opponent that he sank the ship
     * @param ship a message from the ship about which ship was sunk.
     */
    public void sinkedShip(String ship){
        playerField.setSunkText("Your " + ship);
        Packet packet = new Packet();
        packet.messageid = 7; // 7 is the id of ship sunk notifications
        packet.message = "Opponents " + ship;
        network.send(packet);
    }
    
    /**
     * Sets the sinked ship notificator label of the enemy field
     * @param msg the message
     */
    public void setSunkText(String msg){
        enemyField.setSunkText(msg);
    }

    /**
     * Return if the game is over or not
     * @return the over
     */
    public boolean isOver() {
        return over;
    }
    
    /**
     * Set the game over
     * @param over 
     */
    public void setOver(boolean over) {
        this.over = over;
    }
    
}
