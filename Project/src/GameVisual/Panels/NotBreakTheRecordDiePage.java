package GameVisual.Panels;

import GameElement.ControllingCenter;

import javax.swing.*;
import java.awt.*;

public class NotBreakTheRecordDiePage extends DiePage{
    NotBreakTheRecordDiePage(Dimension screenSize, ControllingCenter controllingCenter, int ownBestScore){
        super(screenSize,controllingCenter,ownBestScore);
    }
    void SetUpWordSpaceOne(){
        contentInWordSpaceOne = String.format("Game ended, You got %d",controllingCenter.getCurrentGameScore());
        wordPlaceOneLabel = new JLabel(contentInWordSpaceOne);
    }
    void SetUpWordSpaceTwo(){
        contentInWordSpaceTwo = String.format("You record is %d, Keep fighting!",ownBestScore);
        wordPlaceTwoLabel = new JLabel(contentInWordSpaceTwo);
    }
}
