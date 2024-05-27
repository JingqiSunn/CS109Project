package GameVisual.Panels;

import GameElement.ControllingCenter;
import GameVisual.TotalGameFrame;
import MultiUserSupply.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InGamePageWithTimeLimitForMultiUser extends JPanel {
    TotalGameFrame totalGameFrame;
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
    JPanel timePanel;
    JLabel timeLabel;
    int originalTimeLimit;
    boolean whetherOutOfTime;
    int controllingSize;
    int currentTime;
    ThreadForTimer threadForTimer;
    JPanel buttonControllerSwitch;
    boolean whetherButtonControllerOut;
    ButtonController buttonController;
    int sizeOfButtonController;
    int repeatTimeForButtonControllerToComeOut;
    int movingSpeedForButtonController;
    User user;
    JPanel userPanel;
    JLabel userLabel;
    boolean whetherCompetition;
    boolean whetherServer;
    public InGamePageWithTimeLimitForMultiUser(Dimension screenSize, ControllingCenter controllingCenter, boolean whetherTourist, int timeLimit, TotalGameFrame totalGameFrame, boolean whetherServer) {
        this.whetherServer = whetherServer;
        this.setLayout(null);
        this.whetherOutOfTime = false;
        this.totalGameFrame = totalGameFrame;
        this.whetherTourist = whetherTourist;
        this.originalTimeLimit = timeLimit;
        currentTime = originalTimeLimit;
        this.controllingCenter = controllingCenter;
        this.totalSize = screenSize;
        this.UpdateSizeAndLocationForOptions(totalSize, controllingCenter);
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpBlockUnitsInGame();
        this.setVisible(true);
        threadForTimer = new ThreadForTimer();
        threadForTimer.start();
    }

    public boolean isWhetherServer() {
        return whetherServer;
    }

    public void setWhetherButtonControllerOut(boolean whetherButtonControllerOut) {
        this.whetherButtonControllerOut = whetherButtonControllerOut;
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

    public JPanel getButtonControllerSwitch() {
        return buttonControllerSwitch;
    }

    public boolean GetWhetherDirectionButtonOut() {
        return whetherButtonControllerOut;
    }

    public JPanel GetUpButton() {
        return buttonController.up;
    }

    public JPanel GetDownButton() {
        return buttonController.down;
    }

    public JPanel GetLeftButton() {
        return buttonController.left;
    }

    public JPanel GetRightButton() {
        return buttonController.right;
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize, ControllingCenter controllingCenter) {
        totalSize = screenSize;
        totalHeight = (int) screenSize.getHeight();
        totalWidth = (int) screenSize.getWidth();
        sizeOfTheBlock = Math.min((totalHeight / 14) / (controllingCenter.FindTheMaxYCoordinate() + 1) * 10, (totalWidth / 14) / (controllingCenter.FindTheMaxXCoordinate() + 1) * 10);
        sizeOfTheBlockUnit = (int) (((double) sizeOfTheBlock / (double) 100) * 92);
        widthOfTheBlockSet = sizeOfTheBlock * (controllingCenter.FindTheMaxXCoordinate() + 1);
        heightOfTheBlockSet = sizeOfTheBlock * (controllingCenter.FindTheMaxYCoordinate() + 1);
        startXOfBlockSet = (totalWidth - widthOfTheBlockSet) / 2;
        startYOfBlockSet = (totalHeight - heightOfTheBlockSet) * 3 / 4;
        blockUnits = new ArrayList<>();
        for (int sequenceInPoints = 0; sequenceInPoints < controllingCenter.getInformationOfAllTheCoordinateOfTheBoardUnit().size() / 2; sequenceInPoints++) {
            DrawnBlockUnit newBlockUnit = new DrawnBlockUnit(controllingCenter.getInformationOfAllTheCoordinateOfTheBoardUnit().get(sequenceInPoints * 2), controllingCenter.getInformationOfAllTheCoordinateOfTheBoardUnit().get(sequenceInPoints * 2 + 1), sizeOfTheBlockUnit, controllingCenter);
            blockUnits.add(newBlockUnit);
        }
        controllingSize = Math.min(sizeOfTheBlock / 3, startXOfBlockSet - startYOfBlockSet / 5);
        sizeOfButtonController = (totalWidth / 24) * 3;
        movingSpeedForButtonController = (totalWidth - (int) (((double) totalWidth * 45) / (double) 48) + sizeOfButtonController) / 10;
    }

    public void SetUpBlockUnitsInGame() {
        totalBoard = new JPanel();
        totalBoard.setLayout(null);
        totalBoard.setBounds(startXOfBlockSet, startYOfBlockSet, widthOfTheBlockSet, heightOfTheBlockSet);
        totalBoard.setBackground(Color.LIGHT_GRAY);
        for (int indexInBlocks = 0; indexInBlocks < blockUnits.size(); indexInBlocks++) {
            blockUnits.get(indexInBlocks).UpdateTheOutputShow();
            totalBoard.add(blockUnits.get(indexInBlocks));
        }
        totalBoard.setVisible(true);
        this.add(totalBoard);
        this.LoadTheScorePanel();
        this.LoadButtonControllerSwitch();
        this.LoadTimer();
    }

    public void UpdateBlockUnitsInGame() {
        for (DrawnBlockUnit blockUnit : blockUnits) {
            blockUnit.UpdateTheOutputShowInGame();
        }
        scorePanel.UpdateTheMonitorPanel();
        this.UpDateTheTimerPanel();
        repaint();
    }

    public void RestartBlockUnitsInGame() {
        for (DrawnBlockUnit blockUnit : blockUnits) {
            blockUnit.UpdateTheOutputShowInGame();
        }
        scorePanel.UpdateTheMonitorPanel();
        this.RestartTheTimerPanel();
        repaint();
    }

    void LoadTheScorePanel() {
        scorePanel = new ScorePanel(startXOfBlockSet, startYOfBlockSet, widthOfTheBlockSet, controllingCenter, whetherTourist,user,true,whetherCompetition);
        scorePanel.setVisible(true);
        this.add(scorePanel);
    }

    public void RestartTheGame() {
        controllingCenter.CleanThePlayingBoardForRestart();
        controllingCenter.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
        controllingCenter.UpdateGameValidity();
        this.RestartBlockUnitsInGame();
    }

    private void LoadTimer() {
        timePanel = new JPanel();
        timePanel.setLayout(new BorderLayout());
        timePanel.setBounds(startXOfBlockSet + widthOfTheBlockSet / 3, startYOfBlockSet / 3, widthOfTheBlockSet / 3, startYOfBlockSet / 3);
        timePanel.setBackground(new Color(0x7ABA78));
        timeLabel = new JLabel(String.valueOf(currentTime));
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setFont(new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.4)));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setVerticalAlignment(JLabel.CENTER);
        timePanel.add(timeLabel, BorderLayout.CENTER);
        this.add(timePanel);
    }

    public void UpDateTheTimerPanel() {
        if (whetherTourist) {
            this.remove(timePanel);
            timeLabel.setText(String.valueOf(currentTime));
            timeLabel.setFont(new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.4)));
            timeLabel.setHorizontalAlignment(JLabel.CENTER);
            timeLabel.setVerticalAlignment(JLabel.CENTER);
            timeLabel.setVisible(true);
            timePanel.add(timeLabel, BorderLayout.CENTER);
            timePanel.setVisible(true);
            this.add(timePanel);
        }
    }

    public void RestartTheTimerPanel() {
        currentTime = originalTimeLimit;
        if (whetherTourist) {
            this.remove(timePanel);
            timePanel.setBackground(new Color(0x7ABA78));
            timeLabel.setText(String.valueOf(originalTimeLimit));
            timeLabel.setFont(new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.4)));
            timeLabel.setHorizontalAlignment(JLabel.CENTER);
            timeLabel.setVerticalAlignment(JLabel.CENTER);
            timeLabel.setVisible(true);
            timePanel.add(timeLabel, BorderLayout.CENTER);
            timePanel.setVisible(true);
            this.add(timePanel);
        }
    }

    public void UpdateTheTimerPanel() {
        this.remove(timePanel);
        timeLabel.setText(String.valueOf(currentTime));
        timeLabel.setFont(new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.4)));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setVerticalAlignment(JLabel.CENTER);
        timeLabel.setVisible(true);
        timePanel.add(timeLabel, BorderLayout.CENTER);
        timePanel.setVisible(true);
        this.add(timePanel);
        this.repaint();
    }

    void LoadButtonControllerSwitch() {
        buttonControllerSwitch = new JPanel();
        buttonControllerSwitch.setBounds((int) (((double) totalWidth * 47) / (double) 48), 4 * totalHeight / 5, (int) (((double) totalWidth) / (double) 48), 5 * totalHeight / 5);
        this.add(buttonControllerSwitch);
    }

    public void LoadButtonController() {
        buttonController = new ButtonController(sizeOfButtonController, sizeOfButtonController);
        buttonController.setBounds(totalWidth, totalHeight - sizeOfButtonController, sizeOfButtonController, sizeOfButtonController);
        this.add(buttonController);
        ActionOfMovingButtonControllerIn actionOfMovingButtonControllerIn = new ActionOfMovingButtonControllerIn();
        actionOfMovingButtonControllerIn.start();
        whetherButtonControllerOut = true;
    }

    public void CleanButtonController() {
        InGamePageWithTimeLimitForMultiUser.ActionOfMovingButtonControllerOut actionOfMovingButtonControllerOut = new ActionOfMovingButtonControllerOut();
        actionOfMovingButtonControllerOut.start();
    }

    class ThreadForTimer extends Thread {
        @Override
        public void run() {
            super.run();
            Timer timer = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (controllingCenter.getGameValidity()) {
                        currentTime -= 1;
                        if (currentTime <= 5) {
                            timePanel.setBackground(Color.RED);
                        }
                        UpdateTheTimerPanel();
                        if (currentTime <= 0) {
                            ((Timer) e.getSource()).stop();
                            controllingCenter.setGameValidity(false);
                            totalGameFrame.JudgeWhetherEndOfGameWithTimeLimit();
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

    class ActionOfMovingButtonControllerIn extends Thread {
        public void run() {
            super.run();
            repeatTimeForButtonControllerToComeOut = 10;
            Timer timer = new Timer(20, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (controllingCenter.getGameValidity()) {
                        repeatTimeForButtonControllerToComeOut -= 1;
                        buttonController.setBounds((int) (((double) totalWidth * 45) / (double) 48) - sizeOfButtonController + repeatTimeForButtonControllerToComeOut * movingSpeedForButtonController, totalHeight - sizeOfButtonController, sizeOfButtonController, sizeOfButtonController);
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

    class ActionOfMovingButtonControllerOut extends Thread {
        public void run() {
            super.run();
            repeatTimeForButtonControllerToComeOut = 10;
            Timer timer = new Timer(20, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (controllingCenter.getGameValidity()) {
                        repeatTimeForButtonControllerToComeOut -= 1;
                        buttonController.setBounds(totalWidth - repeatTimeForButtonControllerToComeOut * movingSpeedForButtonController, totalHeight - sizeOfButtonController, sizeOfButtonController, sizeOfButtonController);
                        repaint();
                        if (repeatTimeForButtonControllerToComeOut < 0) {
                            ((Timer) e.getSource()).stop();
                            remove(buttonController);
                            buttonController = null;
                            whetherButtonControllerOut = false;
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
    void SetUpUserPanel(){
        userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());
        userPanel.setBounds(0,0,totalWidth/4,totalHeight/24);
        userLabel = new JLabel(" "+user.getUserName());
        userLabel.setFont(new Font("Bradley Hand",Font.BOLD, 30));
        userLabel.setForeground(Color.BLACK);
        userLabel.setHorizontalAlignment(JLabel.LEFT);
        userLabel.setVerticalAlignment(JLabel.CENTER);
        userPanel.add(userLabel,BorderLayout.WEST);
        this.add(userPanel);
    }

}
