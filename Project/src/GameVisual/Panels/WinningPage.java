package GameVisual.Panels;

import GameElement.ControllingCenter;
import MultiUserSupply.User;

import javax.swing.*;
import java.awt.*;

public abstract class WinningPage extends JPanel {

    Dimension totalSize;
    ControllingCenter controllingCenter;
    int ownLeastStep;
    int totalWidth;
    int totalHeight;
    int globalLeastStep;
    int heightOfBigComponent;
    int widthOfBigComponent;
    int heightOfSmallComponent;
    int widthOfSmallComponent;

    int xOfCenterOfWordSpaceOne;
    int xOfCenterOfWordSpaceTwo;
    int xOfCenterOfRestartAndBackToMenu;
    int yOfCenterOfWordSpaceOne;
    int yOfCenterOfWordSpaceTwo;
    int yOfCenterOfRestartAndBackToMenu;
    JPanel wordSpaceOne;
    JPanel wordSpaceTwo;
    JPanel continueOption;
    JPanel backToMenuOption;
    JLabel wordPlaceOneLabel;
    JLabel wordPlaceTwoLabel;
    JLabel continueLabel;
    JLabel backToMenuLabel;
    String contentInWordSpaceOne;
    String contentInWordSpaceTwo;
    User user;

    public WinningPage(Dimension screenSize, ControllingCenter controllingCenter, int ownLeastStep) {
        this.controllingCenter = controllingCenter;
        this.ownLeastStep = ownLeastStep;
        this.globalLeastStep = 0;
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInTheDiePage();
    }
    public WinningPage(Dimension screenSize, ControllingCenter controllingCenter, int ownLeastStep,User user) {
        this.user = user;
        this.controllingCenter = controllingCenter;
        this.ownLeastStep = ownLeastStep;
        this.globalLeastStep = 0;
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInTheDiePage();
    }

    public JPanel getWordSpaceOne() {
        return wordSpaceOne;
    }

    public JPanel getWordSpaceTwo() {
        return wordSpaceTwo;
    }

    public JPanel getContinueOption() {
        return continueOption;
    }

    public JPanel getBackToMenuOption() {
        return backToMenuOption;
    }

    void SetUpOptionsInTheDiePage() {
        wordSpaceOne = new JPanel(new BorderLayout());
        wordSpaceTwo = new JPanel(new BorderLayout());
        continueOption = new JPanel(new BorderLayout());
        backToMenuOption = new JPanel(new BorderLayout());
        wordSpaceOne.setBounds(xOfCenterOfWordSpaceOne - widthOfBigComponent / 2, yOfCenterOfWordSpaceOne - heightOfBigComponent / 2, widthOfBigComponent, heightOfBigComponent);
        wordSpaceTwo.setBounds(xOfCenterOfWordSpaceTwo - widthOfBigComponent / 2, yOfCenterOfWordSpaceTwo - heightOfBigComponent / 2, widthOfBigComponent, heightOfBigComponent);
        continueOption.setBounds(xOfCenterOfRestartAndBackToMenu- widthOfBigComponent / 2, yOfCenterOfRestartAndBackToMenu - heightOfSmallComponent / 2, widthOfSmallComponent, heightOfSmallComponent);
        backToMenuOption.setBounds(xOfCenterOfRestartAndBackToMenu- widthOfBigComponent / 2 + widthOfBigComponent -widthOfSmallComponent, yOfCenterOfRestartAndBackToMenu - heightOfSmallComponent / 2, widthOfSmallComponent, heightOfSmallComponent);
        this.SetInsideComponentOfOptions();
        wordSpaceOne.setVisible(true);
        wordSpaceTwo.setVisible(true);
        continueOption.setVisible(true);
        backToMenuOption.setVisible(true);
        this.add(wordSpaceOne);
        this.add(wordSpaceTwo);
        this.add(continueOption);
        this.add(backToMenuOption);
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        heightOfBigComponent = totalHeight / 7;
        widthOfBigComponent = totalWidth * 3 / 7;
        heightOfSmallComponent = heightOfBigComponent;
        widthOfSmallComponent = widthOfBigComponent/2;
        xOfCenterOfWordSpaceOne = totalWidth / 2;
        xOfCenterOfRestartAndBackToMenu = totalWidth / 2;
        xOfCenterOfWordSpaceTwo = totalWidth / 2;
        yOfCenterOfWordSpaceOne = totalHeight * 4 / 14;
        yOfCenterOfWordSpaceTwo = totalHeight * 7 / 14;
        yOfCenterOfRestartAndBackToMenu = totalHeight * 10 / 14;
    }

    void SetInsideComponentOfOptions() {
        continueOption.setBackground(Color.LIGHT_GRAY);
        backToMenuOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        this.SetUpWordSpaceOne();
        this.SetUpWordSpaceTwo();
        continueLabel = new JLabel("Continue");
        backToMenuLabel = new JLabel("Back to Menu");
        wordPlaceOneLabel.setFont(font);
        wordPlaceTwoLabel.setFont(font);
        continueLabel.setFont(font);
        backToMenuLabel.setFont(font);
        wordPlaceOneLabel.setForeground(Color.BLACK);
        wordPlaceTwoLabel.setForeground(Color.BLACK);
        continueLabel.setForeground(Color.WHITE);
        backToMenuLabel.setForeground(Color.WHITE);
        wordPlaceOneLabel.setHorizontalAlignment(JLabel.CENTER);
        wordPlaceTwoLabel.setHorizontalAlignment(JLabel.CENTER);
        continueLabel.setHorizontalAlignment(JLabel.CENTER);
        backToMenuLabel.setHorizontalAlignment(JLabel.CENTER);
        wordPlaceOneLabel.setVerticalAlignment(JLabel.CENTER);
        wordPlaceTwoLabel.setVerticalAlignment(JLabel.CENTER);
        continueLabel.setVerticalAlignment(JLabel.CENTER);
        backToMenuLabel.setVerticalAlignment(JLabel.CENTER);
        wordPlaceOneLabel.setVisible(true);
        wordPlaceTwoLabel.setVisible(true);
        backToMenuLabel.setVisible(true);
        continueLabel.setVisible(true);
        wordSpaceOne.add(wordPlaceOneLabel,BorderLayout.CENTER);
        wordSpaceTwo.add(wordPlaceTwoLabel,BorderLayout.CENTER);
        continueOption.add(continueLabel,BorderLayout.CENTER);
        backToMenuOption.add(backToMenuLabel,BorderLayout.CENTER);
        wordSpaceOne.setVisible(true);
        wordSpaceTwo.setVisible(true);
        continueOption.setVisible(true);
        backToMenuOption.setVisible(true);
        this.setVisible(true);
    }
    abstract void SetUpWordSpaceOne();
    abstract void SetUpWordSpaceTwo();
}