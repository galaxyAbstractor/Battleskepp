package battleskepp;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class PlayerFieldPanel extends FieldPanel {
    private int selected = 0;
    private JLabel shipsToPlaceInfo;
    private Game game = Game.getInstance();
    private JLabel shipSunk;
    
    /**
     * Creates things specific to the players field, like how many ships left and reporting hits/misses
     */
    public PlayerFieldPanel() {
        super();
        
        // Notify how many ships (or, rather, modules) are placed
        shipsToPlaceInfo = new JLabel("Ships placed: 0 / 18");
        shipsToPlaceInfo.setBounds(200, 350, 120, 30);
        
        // To notify if the opponent sunk a ship of ours
        shipSunk = new JLabel("");
        shipSunk.setBounds(200, 370, 300, 30);
        
        this.add(shipSunk);
        this.add(shipsToPlaceInfo);

        // For each checkbox, add an event listener that checks for when checkboxes are selected/unselected
        for(JCheckBox[] x : checkboxes){
            for(JCheckBox y : x){
                y.addItemListener(new ItemListener() {
                    
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        // Check what the event was. 1 is selected, 2 is unselected
                        if(e.getStateChange() == 1){
                            // If we are below the amount of ships that can be selected, proceed
                            // Otherwise, don't allow it
                            if(selected < 18){
                                selected++;
                            } else {
                                JCheckBox src =(JCheckBox) e.getSource();
                                selected++; // It will be decreased with as many as it increased with when the unselected event is called.
                                src.setSelected(false);
                            }
                            shipsToPlaceInfo.setText("Ships placed: "+selected+" / 18");
                        }   else if(e.getStateChange() == 2) {
                            selected--;
                            shipsToPlaceInfo.setText("Ships placed: "+selected+" / 18");
                        }
                    }
                });
            }
        }      
        
    }
    
    /**
     * Get the amount selected
     * @return 
     */
    public int getSelectedCount(){
        return selected;
    }
    
    /**
     * Set the status of sunken ships
     * @param str the status to be set
     */
    public void setSunkText(String str){
        shipSunk.setText(str);
    }
    
    /**
     * Check if it was a hit or not. If it's a hit, a hit icon is set, checkbox is disabled. A miss leads to a missed icon
     * 4 is sent to the opponent if it's a hit, 5 if it's a miss
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return 4 if a hit, 5 if miss
     */
    public int checkHit(int x, int y){
        if(checkboxes[x][y].isSelected()){
            checkboxes[x][y].setDisabledSelectedIcon(new ImageIcon(getClass().getResource("/resources/hit.png")));
            selected--;
            shipsToPlaceInfo.setText("Ships placed: "+selected+" / 18");
            
            // If there is no more checkboxes, the game is over and we have lost
            if(selected == 0){
                game.over();
            }
            return 4;
        } else {
            checkboxes[x][y].setDisabledIcon(new ImageIcon(getClass().getResource("/resources/miss.png")));
            return 5;
        }
    }
    
    /**
     * Selects a single checkbox, or deselects if reverse is true
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @param reverse If it should be reversed or not
     */
    public void select(int x, int y, boolean reverse){
        
        if(!reverse){
            checkboxes[x][y].setSelected(true);
        } else {
            checkboxes[x][y].setSelected(false);
        }
            
    }
    
    /**
     * Check if the ship to be placed is colliding with another ship.
     * The rules are that a ship cannot be placed on top of each other and not at the direct neighbor of another ship.
     * @param fromX the x-coordinate the ship starts on
     * @param toX the x-coordinate the ship ends on
     * @param fromY the y-coordinate the ship starts on
     * @param toY the y-coordinate the ship ends on
     * @return true if collifing, false if not
     */
    public boolean isColliding(int fromX, int toX, int fromY, int toY) {
       
        // Expand the area of the ship to check all checkboxes next to it
        fromX--;
        toX++;
        
        fromY--;
        toY++;
        
        // We don't wanna check outside of the field (and the array) so make sure we don't
        if(fromX < 0) fromX = 0;
        if(toX > 9) toX = 9;
        
        if(fromY < 0) fromY = 0;
        if(toY > 9) toY = 9;
        
        // Loop through all coordinates around and inside the ship, and if a checkbox already is selected, it's a collision
        for(int y = fromY; y <= toY; y++){
            for(int x = fromX; x <= toX; x++){
                if(checkboxes[x][y].isSelected()){
                    return true;
                }
            }
        }
        
        // If we reach here no collision was made
        return false;
    }
    
    /**
     * Resets the panel
     */
    @Override
    public void reset(){
        super.reset();
        selected = 0;
    }
}
