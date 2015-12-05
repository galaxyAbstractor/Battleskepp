package battleskepp;

import javax.swing.JCheckBox;

public class ShipCheckBox extends JCheckBox {
    
    private int x;
    private int y;

    /**
     * @return the x
     */
    public int getXCoord() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setXCoord(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getYCoord() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setYCoord(int y) {
        this.y = y;
    }
    
}
