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
    JPanel timePanel;
    JLabel timeLabel;
    int originalTimeLimit;
    boolean whetherOutOfTime;
    int controllingSize;
    public InGamePageWithTimeLimit(Dimension screenSize, ControllingCenter controllingCenter, boolean whetherTourist,int timeLimit){
        this.setLayout(null);
        this.whetherOutOfTime=false;
        this.whetherTourist = whetherTourist;
        this.originalTimeLimit = timeLimit;
        this.controllingCenter = controllingCenter;
        this.totalSize = screenSize;
        this.UpdateSizeAndLocationForOptions(totalSize,controllingCenter);
        this.setBounds(0,0,totalWidth,totalHeight);
        this.SetUpBlockUnitsInGame();
        this.setVisible(true);
    }

    public int getOriginalTimeLimit() {
        return originalTimeLimit;
    }

    public boolean GetWhetherTourist() {
        return whetherTourist;
    }

    public boolean GetWhetherOutOfTime() {
        return whetherOutOfTime;
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
        controllingSize = Math.min(sizeOfTheBlock/3,startXOfBlockSet-startYOfBlockSet/5);
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
        this.LoadTimer();
    }
    public void UpdateBlockUnitsInGame(){
        for (DrawnBlockUnit blockUnit : blockUnits) {
            blockUnit.UpdateTheOutputShowInGame();
        }
        scorePanel.UpdateTheScorePanel();
        this.UpdateTheTimerPanel();
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
    private void LoadTimer(){
        timePanel = new JPanel();
        timePanel.setLayout(new BorderLayout());
        timePanel.setBounds(startXOfBlockSet+widthOfTheBlockSet/3,startYOfBlockSet/3,widthOfTheBlockSet/3,startYOfBlockSet/3);
        timePanel.setBackground(new Color(0x7ABA78));
        timeLabel = new JLabel(String.valueOf(originalTimeLimit));
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setFont(new Font("Times New Roman", Font.BOLD, (int)((double)controllingSize*0.4)));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setVerticalAlignment(JLabel.CENTER);
        timePanel.add(timeLabel,BorderLayout.CENTER);
        this.add(timePanel);
    }
    public void UpdateTheTimerPanel(){
        if(whetherTourist){
            this.remove(timePanel);
            timePanel.setBackground(new Color(0x7ABA78));
            timeLabel.setText(String.valueOf(originalTimeLimit));
            timeLabel.setFont(new Font("Times New Roman", Font.BOLD, (int)((double)controllingSize*0.4)));
            timeLabel.setHorizontalAlignment(JLabel.CENTER);
            timeLabel.setVerticalAlignment(JLabel.CENTER);
            timeLabel.setVisible(true);
            timePanel.add(timeLabel,BorderLayout.CENTER);
            timePanel.setVisible(true);
            this.add(timePanel);
        }
    }
}
