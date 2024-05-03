package GameVisual.Panels;

import javax.swing.*;

public class UnitBlock extends JPanel {
    boolean whetherChoosing = false;
    int xCoordinate;
    int yCoordinate;
    UnitBlock(){
        super();
        whetherChoosing = false;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public boolean getWhetherChoosing() {
        return whetherChoosing;
    }

    public void setWhetherChoosing(boolean whetherChoosing) {
        this.whetherChoosing = whetherChoosing;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
