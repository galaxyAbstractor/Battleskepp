package battleskepp;

/**
 * The required methods a ship needs
 * @author Victor Nagy <galaxyAbstractor@gmail.com>
 */
public interface Ship {
    /**
     * Called when all modules of the ship is hit and the ship is sunk
     */
    public void sink();
    
    /**
     * Checks the checkboxes inside the area of the ship
     * @param reverse If it should work in reverse, i.e uncheck the boxes
     * @return if it was successful
     */
    public boolean check(boolean reverse);
    
    /**
     * See above, same thing just shortcut, reverse is false
     * @return 
     */
    public boolean check();
    
    /**
     * When a ship is hit, this method is called. It decreases the amount of modules left and calls sink() if there is none left
     */
    public void hit();
    
    /**
     * Check if the given coordinate is contained within the ship
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return if it is contained or not
     */
    public boolean checkCoordinate(int x, int y);
}
