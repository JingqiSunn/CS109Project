package GameVisual.Panels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SuccessfullyRegisteredFunctionPanel extends JPanel {
    Dimension totalSize;
    int unitHeight;
    int totalWidth;
    int totalHeight;
    int startX;
    int startY;
    Border border;
    Font bigFont;
    Font smallFont;
    JPanel wordSpaceOne;
    JLabel wordSpaceOneLabel;
    JPanel wordSpaceTwo;
    JLabel wordSpaceTwoLabel;
    JPanel comeToLogin;
    JLabel comeToLoginLabel;
    JPanel backToMainMenu;
    JLabel backToMainMenuLabel;
    SuccessfullyRegisteredFunctionPanel(Dimension totalSize, int startX, int startY) {
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(totalSize, startX, startY);
        this.SetUpThePanel();
    }

    void UpdateSizeAndLocationForOptions(Dimension totalSize, int startX, int startY) {
        this.totalSize = totalSize;
        this.startX = startX;
        this.startY = startY;
        totalWidth = totalSize.width;
        totalHeight = totalSize.height;
        unitHeight = totalHeight/12;
        border = BorderFactory.createLineBorder(Color.BLACK, 0, true);
        bigFont = new Font("Lucida Calligraphy", Font.BOLD, 55);
        smallFont = new Font("Times New Roman", Font.BOLD, 25);
    }

    void SetUpThePanel() {
        this.setBorder(border);
        this.setBounds(startX, startY, totalWidth, totalHeight);
        this.setBackground(new Color(0xB8B0B0));
        this.SetUpWordSpaceOne();
        this.SetUpWordSpaceTwo();
        this.SetUpComeToLoginButton();
        this.SetUpComeToBackToMainMenuButton();
        this.add(wordSpaceOne);
        this.add(wordSpaceTwo);
        this.add(comeToLogin);
        this.add(backToMainMenu);
        this.setVisible(true);
    }
    void SetUpWordSpaceOne(){
        wordSpaceOne = new JPanel();
        wordSpaceOne.setLayout(new BorderLayout());
        wordSpaceOne.setBounds(0,unitHeight,totalWidth,unitHeight*3);
        wordSpaceOne.setBackground(new Color(0xB8B0B0));
        wordSpaceOneLabel = new JLabel("Congratulations");
        wordSpaceOneLabel.setFont(bigFont);
        wordSpaceOneLabel.setHorizontalAlignment(JLabel.CENTER);
        wordSpaceOneLabel.setVerticalAlignment(JLabel.CENTER);
        wordSpaceOneLabel.setForeground(Color.BLACK);
        wordSpaceOne.add(wordSpaceOneLabel);
    }
    void SetUpWordSpaceTwo(){
        wordSpaceTwo = new JPanel();
        wordSpaceTwo.setLayout(new BorderLayout());
        wordSpaceTwo.setBounds(0,unitHeight*5,totalWidth,unitHeight*3);
        wordSpaceTwo.setBackground(new Color(0xB8B0B0));
        wordSpaceTwoLabel = new JLabel("You have successfully created your account!");
        wordSpaceTwoLabel.setFont(smallFont);
        wordSpaceTwoLabel.setHorizontalAlignment(JLabel.CENTER);
        wordSpaceTwoLabel.setVerticalAlignment(JLabel.CENTER);
        wordSpaceTwoLabel.setForeground(Color.BLACK);
        wordSpaceTwo.add(wordSpaceTwoLabel);
    }
    void SetUpComeToLoginButton(){
        comeToLogin = new JPanel();
        comeToLogin.setLayout(new BorderLayout());
        comeToLogin.setBounds(totalWidth/8,unitHeight*9,totalWidth/4,unitHeight*2);
        comeToLogin.setBackground(new Color(0x7C7575));
        comeToLoginLabel = new JLabel("Try Login");
        comeToLoginLabel.setFont(smallFont);
        comeToLoginLabel.setHorizontalAlignment(JLabel.CENTER);
        comeToLoginLabel.setVerticalAlignment(JLabel.CENTER);
        comeToLoginLabel.setForeground(Color.BLACK);
        comeToLogin.add(comeToLoginLabel);
    }
    void SetUpComeToBackToMainMenuButton(){
        backToMainMenu = new JPanel();
        backToMainMenu.setLayout(new BorderLayout());
        backToMainMenu.setBounds(5*totalWidth/8,unitHeight*9,totalWidth/4,unitHeight*2);
        backToMainMenu.setBackground(new Color(0x7C7575));
        backToMainMenuLabel = new JLabel("Main Menu");
        backToMainMenuLabel.setFont(smallFont);
        backToMainMenuLabel.setHorizontalAlignment(JLabel.CENTER);
        backToMainMenuLabel.setVerticalAlignment(JLabel.CENTER);
        backToMainMenuLabel.setForeground(Color.BLACK);
        backToMainMenu.add(backToMainMenuLabel);
    }
}
