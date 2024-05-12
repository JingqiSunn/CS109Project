package GameVisual.Panels;

import GameElement.ControllingCenter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InGamePageWithTimeLimit extends JPanel {
    boolean whetherTourist;
    ControllingCenter controllingCenter;
    Dimension totalSize;
    ArrayList<DrawnBlockUnit> blockUnits;
    JPanel totalBoard;
    ScorePanel scorePanel;
    int totalWidth;
    int totalHeight;
    int startXOfBlockSet;
    int startYOfBlockSet;

    int sizeOfTheBlock;
    int sizeOfTheBlockUnit;
    int widthOfTheBlockSet;
    int heightOfTheBlockSet;
    Timer timer;
    public InGamePageWithTimeLimit(Dimension screenSize, ControllingCenter controllingCenter, boolean whetherTourist){
        this.setLayout(null);
        this.whetherTourist = whetherTourist;
        this.controllingCenter = controllingCenter;
        this.totalSize = screenSize;
        this.UpdateSizeAndLocationForOptions(totalSize,controllingCenter);
        this.setBounds(0,0,totalWidth,totalHeight);
        this.SetUpBlockUnitsInGame();
        this.setVisible(true);
    }

    public boolean GetWhetherTourist() {
        return whetherTourist;
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize, ControllingCenter controllingCenter){
        totalSize = screenSize;
        totalHeight = (int) screenSize.getHeight();
        totalWidth = (int) screenSize.getWidth();
        sizeOfTheBlock = Math.min((totalHeight/14)/(controllingCenter.FindTheMaxYCoordinate()+1)*10,(totalWidth/14)/(controllingCenter.FindTheMaxXCoordinate()+1)*10);
        sizeOfTheBlockUnit = (int)(((double)sizeOfTheBlock/(double) 100)*92);
        widthOfTheBlockSet = sizeOfTheBlock*(controllingCenter.FindTheMaxXCoordinate()+1);
        heightOfTheBlockSet=sizeOfTheBlock*(controllingCenter.FindTheMaxYCoordinate()+1);
        startXOfBlockSet = (totalWidth-widthOfTheBlockSet)/2;
        startYOfBlockSet = (totalHeight-heightOfTheBlockSet)*3/4;
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
            blockUnits.get(indexInBlocks).UpdateTheOutputShow();
            totalBoard.add(blockUnits.get(indexInBlocks));
        }
        totalBoard.setVisible(true);
        this.add(totalBoard);
        this.LoadTheScorePanel();
    }
    public void UpdateBlockUnitsInGame(){
        for (DrawnBlockUnit blockUnit : blockUnits) {
            blockUnit.UpdateTheOutputShowInGame();
        }
        scorePanel.UpdateTheScorePanel();
        repaint();
    }
    void LoadTheScorePanel(){
        scorePanel = new ScorePanel(startXOfBlockSet,startYOfBlockSet,widthOfTheBlockSet,controllingCenter,whetherTourist);
        scorePanel.setVisible(true);
        this.add(scorePanel);
    }
    public void RestartTheGame(){
        controllingCenter.CleanThePlayingBoardForRestart();
        controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
        controllingCenter.UpdateGameValidity();
        this.UpdateBlockUnitsInGame();
    }
}
