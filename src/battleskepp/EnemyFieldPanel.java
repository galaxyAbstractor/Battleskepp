package battleskepp;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class EnemyFieldPanel extends FieldPanel {
    private int left = 18;
    private JLabel shipsToHitInfo;
    private JLabel shipSunk;
    private Game game = Game.getInstance();
    
    /**
     * Defines and creates the specifics of the enemyfield (the one you shoot on)
     */
    public EnemyFieldPanel() {
        // Call the superclass to create the initial stuff, like the field
        super();
        
        // Create some status labels
        shipsToHitInfo = new JLabel("Ships to hit: 18");
        shipsToHitInfo.setBounds(200, 350, 120, 30);
        
        shipSunk = new JLabel("");
        shipSunk.setBounds(200, 370, 300, 30);
        
        this.add(shipSunk); 
        this.add(shipsToHitInfo);
        
        // For each checkbox we need to add an listener to listen for when the user shoots. When the user does, we tell the game logic what coordinates
        // were shot, and it sends it to the opponent for verification
        for(JCheckBox[] x : checkboxes){
            for(JCheckBox y : x){
                y.addItemListener(new ItemListener() {
                    
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if(e.getStateChange() == 1 && !game.isOver()){
                            game.shoot(((ShipCheckBox)e.getSource()).getXCoord(), ((ShipCheckBox)e.getSource()).getYCoord());
                        }
                    }
                });
            }
        }
    }
    
    /**
     * 
     * @return the amount of ships left to hit 
     */
    public int getLeftCount(){
        return left;
    }
    
    /**
     * Sets the text of the label that indicates if a ship was sunk
     * @param str the string to set
     */
    public void setSunkText(String str){
        shipSunk.setText(str);
    }
    
    /**
     * This is called when receiving the notification from the opponent after a shot. It's for notifying us if we hit or not.
     * @param didhit If we hit
     * @param x the x-coordinate of the shot
     * @param y the y-coordinate of the shot
     */
    public void didHit(int didhit, int x, int y){
        JCheckBox cb = checkboxes[x][y];
        // 4 is a hit, 5 is a miss (5 is already tested for in the network listener)
        if(didhit == 4){
            // Set a hit icon for the checkbox, then disable it
            cb.setDisabledSelectedIcon(new ImageIcon(getClass().getResource("/resources/hit.png")));
            left--;
            shipsToHitInfo.setText("Ships to hit: "+ left);
            cb.setEnabled(false);
        } else {
            // Set a miss icon for the checkbox then disable it
            cb.setDisabledSelectedIcon(new ImageIcon(getClass().getResource("/resources/miss.png")));
            cb.setEnabled(false);
        }
    }
    
    /**
     * Reset the board after a game
     */
    @Override
    public void reset(){
        super.reset();
        left = 18;
        shipsToHitInfo.setText("Ships to hit: "+ left);
    }
}
