package GameVisual.Panels;

import GameElement.ControllingCenter;

import javax.swing.*;
import java.awt.*;

public class BreakTheRecordDiePage extends DiePage{
    BreakTheRecordDiePage(Dimension screenSize, ControllingCenter controllingCenter, int ownBestScore){
        super(screenSize,controllingCenter,ownBestScore);
    }
    void SetUpWordSpaceOne(){
        contentInWordSpaceOne = String.format("You Broke Your record by %d, congratulation!",ownBestScore);
        wordPlaceOneLabel = new JLabel(contentInWordSpaceOne);
    }
    void SetUpWordSpaceTwo(){
        contentInWordSpaceTwo = String.format("The global best score is %d, keep fighting!");
        wordPlaceTwoLabel = new JLabel(contentInWordSpaceTwo);
    }
}
