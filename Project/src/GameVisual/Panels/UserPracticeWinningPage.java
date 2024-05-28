package GameVisual.Panels;

import GameElement.ControllingCenter;
import MultiUserSupply.User;

import javax.swing.*;
import java.awt.*;

public class UserPracticeWinningPage extends WinningPage{
    JPanel userPanel;
    JLabel userLabel;
    public UserPracticeWinningPage(Dimension screenSize, ControllingCenter controllingCenter, User user){
        super(screenSize,controllingCenter,user);
        this.SetUpUserPanel();
    }
    void SetUpWordSpaceOne(){
        contentInWordSpaceOne = String.format("You reached %d successfully!",controllingCenter.getTargetWinningScore());
        wordPlaceOneLabel = new JLabel(contentInWordSpaceOne);
    }
    void SetUpWordSpaceTwo(){
        contentInWordSpaceTwo = String.format("Congratulations");
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
