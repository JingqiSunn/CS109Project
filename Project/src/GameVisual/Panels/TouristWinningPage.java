package GameVisual.Panels;

import GameElement.ControllingCenter;

import javax.swing.*;
import java.awt.*;

public class TouristWinningPage extends WinningPage{
    public TouristWinningPage(Dimension screenSize, ControllingCenter controllingCenter){
        super(screenSize,controllingCenter);
    }
    void SetUpWordSpaceOne(){
        contentInWordSpaceOne = String.format("You reached %d successfully!",controllingCenter.getTargetWinningScore());
        wordPlaceOneLabel = new JLabel(contentInWordSpaceOne);
    }
    void SetUpWordSpaceTwo(){
        contentInWordSpaceTwo = String.format("Congratulations");
        wordPlaceTwoLabel = new JLabel(contentInWordSpaceTwo);
    }
}
