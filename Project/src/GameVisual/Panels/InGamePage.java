package GameVisual.Panels;

import GameElement.ControllingCenter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InGamePage extends JPanel {
    ControllingCenter controllingCenter;
    Dimension totalSize;
    ArrayList<DrawnBlockUnit> blockUnits;
    JPanel totalBoard;
    int totalWidth;
    int totalHeight;
    int startXOfBlockSet;
    int startYOfBlockSet;

    int sizeOfTheBlock;
    int sizeOfTheBlockUnit;
    int widthOfTheBlockSet;
    int heightOfTheBlockSet;
    public InGamePage(Dimension screenSize, ControllingCenter controllingCenter){
        this.setLayout(null);
        this.controllingCenter = controllingCenter;
        this.totalSize = screenSize;
        this.UpdateSizeAndLocationForOptions(totalSize,controllingCenter);
        this.setBounds(0,0,totalWidth,totalHeight);
        this.SetUpBlockUnitsInGame();
        this.setVisible(true);
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize,ControllingCenter controllingCenter){
        totalSize = screenSize;
        totalHeight = (int) screenSize.getHeight();
        totalWidth = (int) screenSize.getWidth();
        sizeOfTheBlock = Math.min((totalHeight/13)/(controllingCenter.FindTheMaxYCoordinate()+1)*10,(totalWidth/13)/(controllingCenter.FindTheMaxXCoordinate()+1)*10);
        sizeOfTheBlockUnit = (sizeOfTheBlock/10)*8;
        widthOfTheBlockSet = sizeOfTheBlock*(controllingCenter.FindTheMaxXCoordinate()+1);
        heightOfTheBlockSet=sizeOfTheBlock*(controllingCenter.FindTheMaxYCoordinate()+1);
        startXOfBlockSet = (totalWidth-widthOfTheBlockSet)/2;
        startYOfBlockSet = (totalHeight-heightOfTheBlockSet)/2;
        blockUnits = new ArrayList<>();
        for (int sequenceInPoints = 0; sequenceInPoints < controllingCenter.getInformationOfAllTheCoordinateOfTheBoardUnit().size()/2; sequenceInPoints++) {
            DrawnBlockUnit newBlockUnit = new DrawnBlockUnit(controllingCenter.getInformationOfAllTheCoordinateOfTheBoardUnit().get(sequenceInPoints*2),controllingCenter.getInformationOfAllTheCoordinateOfTheBoardUnit().get(sequenceInPoints*2+1),sizeOfTheBlockUnit,controllingCenter);
            blockUnits.add(newBlockUnit);
        }
    }
    public void SetUpBlockUnitsInGame(){
        totalBoard = new JPanel();
        totalBoard.setLayout(null);
        totalBoard.setBounds(startXOfBlockSet,startYOfBlockSet,widthOfTheBlockSet,heightOfTheBlockSet);
        totalBoard.setBackground(Color.LIGHT_GRAY);
        for (int indexInBlocks = 0; indexInBlocks < blockUnits.size(); indexInBlocks++) {
            blockUnits.get(indexInBlocks).setVisible(true);
            totalBoard.add(blockUnits.get(indexInBlocks));
        }
        totalBoard.setVisible(true);
        this.add(totalBoard);
    }
}
