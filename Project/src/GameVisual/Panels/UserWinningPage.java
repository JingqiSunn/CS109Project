package GameVisual.Panels;

import GameElement.ControllingCenter;
import MultiUserSupply.User;

import javax.swing.*;
import java.awt.*;

public class UserWinningPage extends WinningPage{
    JPanel userPanel;
    JLabel userLabel;
    public UserWinningPage(Dimension screenSize, ControllingCenter controllingCenter, User user){
        super(screenSize,controllingCenter,user.getOwnLeastStepToWinInCompetition(),user);
        this.SetUpUserPanel();
    }
    void SetUpWordSpaceOne(){
        contentInWordSpaceOne = String.format("You used %d steps to reach %d!",controllingCenter.getNumberOfStep(),2048);
        System.out.println(controllingCenter.getNumberOfStep());
        wordPlaceOneLabel = new JLabel(contentInWordSpaceOne);
    }
    void SetUpWordSpaceTwo(){
        contentInWordSpaceTwo = String.format("It is your %dth time to achieve that!",user.getHistoricalWinningTimeInCompetition()+1);
        wordPlaceTwoLabel = new JLabel(contentInWordSpaceTwo);
        System.out.println(user.getHistoricalWinningTimeInCompetition());
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
