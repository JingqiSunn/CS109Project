package GameVisual.Panels;

import GameElement.ControllingCenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InGamePageWithoutTimeLimit extends JPanel {
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
    JPanel buttonControllerSwitch;
    boolean whetherButtonControllerOut;
    ButtonController buttonController;
    int sizeOfButtonController;
    int repeatTimeForButtonControllerToComeOut;
    int movingSpeedForButtonController;

    public InGamePageWithoutTimeLimit(Dimension screenSize, ControllingCenter controllingCenter, boolean whetherTourist){
        whetherButtonControllerOut = false;
        this.setLayout(null);
        this.whetherTourist = whetherTourist;
        this.controllingCenter = controllingCenter;
        this.totalSize = screenSize;
        this.UpdateSizeAndLocationForOptions(totalSize,controllingCenter);
        this.setBounds(0,0,totalWidth,totalHeight);
        this.SetUpBlockUnitsInGame();
        this.setVisible(true);
    }

    public JPanel getButtonControllerSwitch() {
        return buttonControllerSwitch;
    }

    public void setWhetherButtonControllerOut(boolean whetherButtonControllerOut) {
        this.whetherButtonControllerOut = whetherButtonControllerOut;
    }

    public boolean GetWhetherDirectionButtonOut() {
        return whetherButtonControllerOut;
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
        sizeOfButtonController = (totalWidth/24)*3;
        movingSpeedForButtonController = (totalWidth- (int)(((double)totalWidth*45)/(double)48)+sizeOfButtonController)/10;
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
        this.LoadButtonControllerSwitch();
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
    void LoadButtonControllerSwitch(){
        buttonControllerSwitch = new JPanel();
        buttonControllerSwitch.setBounds((int)(((double)totalWidth*47)/(double)48),4*totalHeight/5,(int)(((double)totalWidth)/(double)48),5*totalHeight/5);
        this.add(buttonControllerSwitch);
    }
    public void RestartTheGame(){
        controllingCenter.CleanThePlayingBoardForRestart();
        controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
        controllingCenter.UpdateGameValidity();
        this.UpdateBlockUnitsInGame();
    }
    public void LoadButtonController(){
        buttonController = new ButtonController(sizeOfButtonController,sizeOfButtonController);
        buttonController.setBounds(totalWidth,totalHeight-sizeOfButtonController,sizeOfButtonController,sizeOfButtonController);
        this.add(buttonController);
        InGamePageWithoutTimeLimit.ActionOfMovingButtonControllerIn actionOfMovingButtonControllerIn = new InGamePageWithoutTimeLimit.ActionOfMovingButtonControllerIn();
        actionOfMovingButtonControllerIn.start();
        whetherButtonControllerOut = true;
    }
    public void CleanButtonController(){
        this.remove(buttonController);
        buttonController = null;
        whetherButtonControllerOut = false;
    }
    class ActionOfMovingButtonControllerIn extends Thread{
        public void run() {
            super.run();
            repeatTimeForButtonControllerToComeOut = 10;
            Timer timer = new Timer(20, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (controllingCenter.getGameValidity()) {
                        repeatTimeForButtonControllerToComeOut -=1;
                        buttonController.setBounds((int)(((double)totalWidth*45)/(double)48)-sizeOfButtonController+repeatTimeForButtonControllerToComeOut*movingSpeedForButtonController,totalHeight-sizeOfButtonController,sizeOfButtonController,sizeOfButtonController);
                        repaint();
                        if (repeatTimeForButtonControllerToComeOut < 0) {
                            ((Timer) e.getSource()).stop();
                        }
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            timer.setRepeats(true);
            timer.start();
        }
    }
}
