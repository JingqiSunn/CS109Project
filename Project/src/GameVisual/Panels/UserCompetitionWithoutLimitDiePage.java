package GameVisual.Panels;

import GameElement.ControllingCenter;
import MultiUserSupply.User;

import javax.swing.*;
import java.awt.*;

public class UserCompetitionWithoutLimitDiePage extends DiePage{
    JPanel userPanel;
    JLabel userLabel;
    public UserCompetitionWithoutLimitDiePage(Dimension screenSize, ControllingCenter controllingCenter, User user){
        super(screenSize,controllingCenter,user.getBestScoreInCompetitionWithoutTimeLimit(),user);
        this.SetUpUserPanel();
    }
    void SetUpWordSpaceOne(){
        if (controllingCenter.getCurrentGameScore()>=user.getBestScoreInCompetitionWithoutTimeLimit()) {
            contentInWordSpaceOne = String.format("Congratulation!");
        } else {
            contentInWordSpaceOne = String.format("Game ended, You got %d", controllingCenter.getCurrentGameScore());
        }
        wordPlaceOneLabel = new JLabel(contentInWordSpaceOne);
    }
    void SetUpWordSpaceTwo(){
        if (controllingCenter.getCurrentGameScore()>=user.getBestScoreInCompetitionWithoutTimeLimit()) {
            contentInWordSpaceTwo = String.format("%d is your best score!",controllingCenter.getCurrentGameScore());
        } else {
            contentInWordSpaceTwo = String.format("Your best score is %d, keep Fighting!",user.getBestScoreInCompetitionWithoutTimeLimit());
        }
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
