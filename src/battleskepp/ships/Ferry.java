package battleskepp.ships;

import battleskepp.Ship;
import java.awt.Color;

public class Ferry extends battleskepp.ships.Ship implements Ship {

    private Ferry(int x, int y, int length, int width, boolean rotation, Color color) {
        super(x,y,length,width,rotation, color);
    }
    
    /**
     * This defines the constructor that is meant to be called. Size of the ship and color is static for all ships of same type 
     * so those doesn't need to be redefined at each creation.
     * @param x the x-coordinate the ship should be placed on
     * @param y the y-coordinate the ship should be placed on
     * @param rotation the rotation of the ship
     */
    public Ferry(int x, int y, boolean rotation){
        this(x, y, 1, 3, rotation, Color.blue);
    }

    /**
     * This is called when a ship is sunk
     */
    @Override
    public void sink() {
        super.sink();
        game.sinkedShip("Ferry was sunk");
    }

}
