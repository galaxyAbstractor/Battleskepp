package battleskepp;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class FieldPanel extends JPanel {

    // A multi-dimensional array to hold the checkboxes
    protected ShipCheckBox[][] checkboxes = new ShipCheckBox[10][10];
    
    /**
     * Creates the field of checkboxes on each panel
     */
    public FieldPanel(){
        
        // Loop trough the entire matrix, adding new checkboxes and adding data to them.
        // ShipCheckBox is a regular JCheckBox with x and y coordinates added to them.
        for(int y = 0; y < 10; y++){
            for(int x = 0; x < 10; x++){
                checkboxes[x][y] = new ShipCheckBox();
                // Set the boxes 31px apart, 100px from the left and 50px from the top
                checkboxes[x][y].setBounds(x*31+100, y*31+50,21,21);
                checkboxes[x][y].setEnabled(false);
                checkboxes[x][y].setXCoord(x);
                checkboxes[x][y].setYCoord(y);
                checkboxes[x][y].setFocusable(false);
                checkboxes[x][y].setOpaque(false);
                
                this.add(checkboxes[x][y]);
            }
            
            // Add label on y-axis
            JLabel lblY = new JLabel(y+1+"");
            lblY.setBounds(80, y*31+45, 30, 30);
            this.add(lblY);
            
            // Add character labels on the x-axis. Increment the characters
            String value = "A";
            int charValue = value.charAt(0);
            String next = String.valueOf( (char) (charValue + y));
            
            JLabel lblX = new JLabel(next);
            lblX.setBounds(y*31+110, 25, 30, 30);
            this.add(lblX);
            
        }
        
    }
    
    /**
     * Enables the fields checkboxes.
     * @param readyMode If in readymode, all checkboxes should be enabled, otherwise only the unselected checkboxes should be enabled.
     */
    public void enableField(boolean readyMode){
        for(ShipCheckBox[] x : checkboxes){
            for(ShipCheckBox y : x){
               if(readyMode || !y.isSelected()) y.setEnabled(true);
            }
        }   
    }
    
    /**
     * See above. Shortcut for enableField with readyMode set to false.
     */
    public void enableField(){
        enableField(false); 
    }
    
    /**
     * Disables the fields checkboxes.
     */
    public void disableField(){
        for(ShipCheckBox[] x : checkboxes){
            for(ShipCheckBox y : x){
                y.setEnabled(false);
            }
        }
    }
    
    /**
     * Resets the field. Sets all checkboxes back to their initial state, unselected and no icons.
     */
    public void reset(){
        for(ShipCheckBox[] x : checkboxes){
            for(ShipCheckBox y : x){
                y.setSelected(false);
                y.setEnabled(false);
                y.setDisabledIcon(null);
                y.setDisabledSelectedIcon(null);
            }
        }
    }
    
}