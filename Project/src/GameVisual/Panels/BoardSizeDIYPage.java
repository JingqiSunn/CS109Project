package GameVisual.Panels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BoardSizeDIYPage extends JPanel {

    Dimension totalSize;
    JPanel blockSets;

    UnitBlockInDIY[][] blockSet;
    int [][] startXOfBlocks;
    int [][] startYOfBlocks;
    int totalWidth;
    int totalHeight;
    int controllingSide;
    int startXOfBlockSet;
    int startYOfBlockSet;

    int sizeOfTheBlock;
    int sizeOfTheBlockSet;
    boolean whetherTimeLimited;
    JPanel askForLimit;
    JLabel askForLimitLabel;
    JTextField askForLimitField;
    JPanel warnPanel;
    JLabel warnLabel;
    JPanel continueToPlay;
    JLabel continueToPlayLabel;
    JPanel skinSwitcher;

    public BoardSizeDIYPage(Dimension screenSize, boolean whetherTimeLimited){
        this.setLayout(null);
        this.whetherTimeLimited = whetherTimeLimited;
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInTheLoginPage();
        if (whetherTimeLimited){
            this.SetUpForAskForTimeLimit();
        }
    }

    public UnitBlockInDIY[][] getBlockSet() {
        return blockSet;
    }
    public JPanel GetContinueButton(){
        return continueToPlay;
    }
    public int GetTimeLimit(){
        if (whetherTimeLimited){
            return Integer.parseInt(askForLimitField.getText());
        } else {
            return 0;
        }
    }
    public boolean getWhetherTimeLimited() {
        return whetherTimeLimited;
    }
    public String GetTimeLimitation(){
        if (whetherTimeLimited){
        return askForLimitField.getText();} else {
            return null;
        }
    }
    public JLabel GetContinueLabel(){
        return continueToPlayLabel;
    }

    public JPanel getSkinSwitcher() {
        return skinSwitcher;
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize){
        totalSize = screenSize;
        totalHeight = (int) screenSize.getHeight();
        totalWidth = (int) screenSize.getWidth();
        controllingSide = Math.min(totalHeight, totalWidth);
        sizeOfTheBlockSet = controllingSide/14*10;
        sizeOfTheBlock = sizeOfTheBlockSet/10;
        startXOfBlockSet = (totalWidth-sizeOfTheBlockSet)/2;
        startYOfBlockSet = (totalHeight-sizeOfTheBlockSet)/2;
        startXOfBlocks = new int[10][10];
        startYOfBlocks = new int[10][10];
        for (int layerInTheBlockSizeInRow = 0; layerInTheBlockSizeInRow < 10; layerInTheBlockSizeInRow++) {
            for (int layerInTheBlockSizeInColumn = 0; layerInTheBlockSizeInColumn < 10; layerInTheBlockSizeInColumn++) {
                startXOfBlocks[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn]= layerInTheBlockSizeInColumn*sizeOfTheBlock;
                startYOfBlocks[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn]= layerInTheBlockSizeInRow*sizeOfTheBlock;
            }
        }
    }
    void SetUpOptionsInTheLoginPage(){
        Border borderOfTheBlock = BorderFactory.createLineBorder(Color.BLACK,6,false);
        blockSets = new JPanel();
        blockSets.setBounds(startXOfBlockSet,startYOfBlockSet,sizeOfTheBlockSet,sizeOfTheBlockSet);
        blockSets.setLayout(null);
        blockSets.setBackground(Color.LIGHT_GRAY);
        blockSet = new UnitBlockInDIY[10][10];
        for (int layerInTheBlockSizeInRow = 0; layerInTheBlockSizeInRow < 10; layerInTheBlockSizeInRow++) {
            for (int layerInTheBlockSizeInColumn = 0; layerInTheBlockSizeInColumn < 10; layerInTheBlockSizeInColumn++) {
                blockSet [layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn] = new UnitBlockInDIY();
                blockSet [layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn].setxCoordinate(layerInTheBlockSizeInColumn);
                blockSet [layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn].setyCoordinate(9-layerInTheBlockSizeInRow);
                int startX = startXOfBlocks[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn];
                int startY = startYOfBlocks[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn];
                blockSet[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn].setBounds(startX,startY,sizeOfTheBlock,sizeOfTheBlock);
                blockSet[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn].setBorder(borderOfTheBlock);
                blockSet[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn].setBackground(Color.white);
                blockSet[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn].setVisible(true);
                blockSets.add(blockSet[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn]);
            }
        }
        blockSets.setVisible(true);
        this.add(blockSets);
        continueToPlay = new JPanel();
        continueToPlay.setBounds(startXOfBlockSet+sizeOfTheBlock*3,startYOfBlockSet+sizeOfTheBlockSet+sizeOfTheBlock/4,sizeOfTheBlock*4,sizeOfTheBlock);
        continueToPlay.setBackground(Color.LIGHT_GRAY);
        continueToPlay.setLayout(new BorderLayout());
        continueToPlayLabel = new JLabel("Continue");
        continueToPlayLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        continueToPlayLabel.setForeground(Color.BLACK);
        continueToPlayLabel.setHorizontalAlignment(JLabel.CENTER);
        continueToPlayLabel.setVerticalAlignment(JLabel.CENTER);
        continueToPlay.add(continueToPlayLabel);
        this.add(continueToPlay);
        this.LoadSkinSwitcher();
    }
    public void SetUpForAskForTimeLimit() {
        askForLimit = new JPanel();
        askForLimit.setLayout(new BorderLayout());
        askForLimit.setBackground(Color.LIGHT_GRAY);
        askForLimit.setBounds(startXOfBlockSet, startYOfBlockSet / 2, sizeOfTheBlockSet / 2, startYOfBlockSet / 2);
        askForLimitLabel = new JLabel("Time Limit: ");
        askForLimitLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        askForLimitLabel.setForeground(Color.BLACK);
        askForLimitLabel.setHorizontalAlignment(JLabel.CENTER);
        askForLimitLabel.setVerticalAlignment(JLabel.CENTER);
        askForLimit.add(askForLimitLabel);
        askForLimitField = new JTextField();
        askForLimitField.setFont(new Font("Times New Roman", Font.BOLD, 40));
        askForLimitField.setBounds(startXOfBlockSet + sizeOfTheBlockSet / 2, startYOfBlockSet / 2, sizeOfTheBlockSet / 2, startYOfBlockSet / 2);
        this.add(askForLimit);
        this.add(askForLimitField);
    }
        public void EstablishWarn(String wordsOutput) {
            if (warnPanel != null) {
                this.remove(warnPanel);
                warnPanel = null;
                warnLabel = null;
            }
            warnPanel = new JPanel();
            warnPanel.setBounds(startXOfBlockSet,startYOfBlockSet+sizeOfTheBlockSet,sizeOfTheBlockSet,sizeOfTheBlock/4);
            warnPanel.setBackground(new Color(0xE3E1D9));
            warnPanel.setLayout(new BorderLayout());
            warnLabel = new JLabel(wordsOutput);
            warnLabel.setForeground(Color.RED);
            warnLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
            warnLabel.setHorizontalAlignment(JLabel.CENTER);
            warnLabel.setVerticalAlignment(JLabel.CENTER);
            warnLabel.setVisible(true);
            warnPanel.add(warnLabel, BorderLayout.CENTER);
            warnPanel.setVisible(true);
            this.add(warnPanel);
            this.repaint();
            this.setVisible(true);
        }
    public void CleanExistingWarning() {
        this.setVisible(false);
        if (warnPanel != null) {
            this.remove(warnPanel);
            warnPanel = null;
            warnLabel = null;
        }
        this.repaint();
        this.setVisible(true);
    }
    void LoadSkinSwitcher(){
        skinSwitcher = new JPanel();
        skinSwitcher.setBounds((int)(((double)totalWidth*47)/(double)48),4*totalHeight/5,(int)(((double)totalWidth)/(double)48),5*totalHeight/5);
        this.add(skinSwitcher);
    }
}