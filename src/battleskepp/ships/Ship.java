package battleskepp.ships;

import battleskepp.Game;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.border.LineBorder;

public abstract class Ship extends JComponent {

    private int screenX = 0;
    private int screenY = 0;
    private int myX = 0;
    private int myY = 0;
    private int previousX = 0;
    private int previousY = 0;
    private int shipSizeX = 0;
    private int shipSizeY = 0;
    
    private int modules;
    
    protected Game game = Game.getInstance();

    /**
     * This creates the graphical representation of the ship and makes it moveable during the ship placement phase
     * @param x The x-coordinate (not pixels, the coordinate on the field) the ship should be placed on
     * @param y The y-coordinate (not pixels, the coordinate on the field) the ship should be placed on
     * @param length the length of the ship
     * @param width the width of the ship
     * @param rotation the rotation of the ship
     * @param color the color of the ship
     */
    public Ship(int x, int y, int length, int width, boolean rotation, Color color) {
        
        // Check what rotation the ship should be placed on. Either shipSizeX becomes the length or the width, same with shipSizeY
        if(rotation){
            shipSizeX = length;
            shipSizeY = width;
        } else {
            shipSizeX = width;
            shipSizeY = length;
        }
        
        // Set the number of modules a ship has. The ships are supposed to be rectangular so we can just multiply the length and width.
        modules = width*length;
        
        // Set a border for it so it can be distinguished
        setBorder(new LineBorder(color, 3));
        
        // Place it on the board. The math is based on the x and y coordinates, the ship sizes and the amount of pixels between the checkboxes.
        setBounds(101 + (31 * (x)), 50 + (31 * (y)), 22 + (31 * (shipSizeX - 1)), 22 + (31 * (shipSizeY - 1)));

        /**
         * Add a mouse listener to the ship
         */
        addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            /**
             * Handles what should hapen when a ship is pressed. It unchecks the boxes, and sets some variables
             */
            @Override
            public void mousePressed(MouseEvent e) {
                // Check if we are in ready mode, we cannot let the player move the ships if we aren't
                if(game.isReadyMode()){
                    // Set the position of the mouse on the screen
                    screenX = e.getXOnScreen();
                    screenY = e.getYOnScreen();

                    // Get the ships position on the screen
                    myX = getX();
                    myY = getY();

                    // Save the previous location so we can return the ship at an illegal move
                    previousX = getX();
                    previousY = getY();

                    // Uncheck the checkboxes (true for reverse)
                    check(true);
                }
            }

            /**
             * When the mouse is released we should try to check the boxes
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                // Check if we are in ready mode, we cannot let the player move the ships if we aren't
                if(game.isReadyMode()){
                    // check the boxes and see if it was valid. If it wasn't, move back to the previous location
                    boolean success = check();
                    if (!success) {
                        setLocation(previousX, previousY);
                        check();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        /**
         * Add our MouseMotion listener to listen for mouse motion
         */
        addMouseMotionListener(new MouseMotionListener() {

            /**
             * This is called while the player is dragging this component
             */
            @Override
            public void mouseDragged(MouseEvent e) {
                // Check if we are in ready mode, we cannot let the player move the ships if we aren't
                if(game.isReadyMode()){
                    // Calculate the delta of how much we have moved since last time
                    int deltaX = e.getXOnScreen() - screenX;
                    int deltaY = e.getYOnScreen() - screenY;

                    // If we moved more than 31px (the size of a checkbox), we should move.
                    // First we check if the user moved diagonally
                    if ((deltaX % 31 == 0) && (deltaY % 31 == 0)) {
                        // Move to the new position
                        setLocation(myX + deltaX, myY + deltaY);
                        // Update the ships position to the new one
                        myX = getX();
                        myY = getY();
                        // Update the mouse last position on the screen
                        screenX = e.getXOnScreen();
                        screenY = e.getYOnScreen();

                    } else if (deltaX % 31 == 0) {
                        // Player is only moving sideways, set new position and new variables
                        setLocation(myX + deltaX, myY);
                        myX = getX();
                        screenX = e.getXOnScreen();

                    } else if (deltaY % 31 == 0) {
                        // Player is only moving up/down, set new position and new variables
                        setLocation(myX, myY + deltaY);
                        myY = getY();
                        screenY = e.getYOnScreen();

                    }
                }

            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }
    
    /**
     * Check the boxes
     * @param reverse if it should work in reverse, unchecking the boxes
     * @return if it was successfull
     */
    public boolean check(boolean reverse){
        return game.checkBoxes(getX(), shipSizeX, getY(), shipSizeY, reverse);
    }
    
    /**
     * Check above, shortcut with reverse set to false
     * @return 
     */
    public boolean check(){
        return check(false);
    }
    
    /**
     * Checks if the coordinate is contained within this ship
     * @param x the x-coordinate to check
     * @param y the y-coordinate to check
     * @return true if contained, false if not
     */
    public boolean checkCoordinate(int x, int y){
        // Translate the pixels to real starting coordinates on the field (like x: 5, y:2)
        // There's 31px between every checkbox
        // and the field is positioned 101px in x-axis on the panel, and 50px in y-axis
        int realX = (getX()-101)/31;
        int realY = (getY()-50)/31;

        // Ships are only supposed to be 1 checkbox in x-axis or y-axis, so we check it's rotation
        if(shipSizeX == 1){
            // Check if the x-coordinate we are trying to check is the same as the ships. If not, the coordinate is not contained
            if(x == realX){
                // Check the ships Y-coordinates to check if the coordinate matches
                for(int i = realY; i <= realY+shipSizeY; i++){
                    if(y == i) return true;
                }
                return false;
            } else {
                return false;
            }
        } else {
            // Check if the y-coordinate we are trying to check is the same as the ships. If not, the coordinate is not contained
            if(y == realY){
                // Check the ships X-coordinates to check if the coordinate matches
                for(int i = realX; i <= realX+shipSizeX; i++){
                    if(x == i) return true;
                }
                return false;
            } else {
                return false;
            }
        }
    }
    
    /**
     * Called when ship is sunk, implemented in subclasses
     */
    public void sink(){
        modules = shipSizeX*shipSizeY; // Reset modules after this, so the ships can be reused in a new game
    };
    
    /**
     * Called when a hit is made, and checks if the ship is sunk
     */
    public void hit(){
        modules--;
        if(modules == 0){
            sink();
        }
    }
    
}