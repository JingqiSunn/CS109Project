package GameVisual.Panels;

import GameElement.ControllingCenter;
import MultiUserSupply.User;

import javax.swing.*;
import java.awt.*;

public class UserPracticeWithoutLimitDiePage extends DiePage{
    JPanel userPanel;
    JLabel userLabel;
    public UserPracticeWithoutLimitDiePage(Dimension screenSize, ControllingCenter controllingCenter, int ownBestScore, User user){
        super(screenSize,controllingCenter,ownBestScore,user);
        this.SetUpUserPanel();
    }
    void SetUpWordSpaceOne(){
        contentInWordSpaceOne = String.format("Game ended, You got %d",controllingCenter.getCurrentGameScore());
        wordPlaceOneLabel = new JLabel(contentInWordSpaceOne);
    }
    void SetUpWordSpaceTwo(){
        contentInWordSpaceTwo = String.format("Nice Try!",ownBestScore);
        wordPlaceTwoLabel = new JLabel(contentInWordSpaceTwo);
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
