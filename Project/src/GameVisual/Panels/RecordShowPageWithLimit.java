package GameVisual.Panels;

import MultiUserSupply.User;

import javax.swing.*;
import java.awt.*;

public class RecordShowPageWithLimit extends JPanel {

    Dimension totalSize;
    User user;

    int totalWidth;
    int totalHeight;

    int heightOfComponent;
    int widthOfComponent;

    int startX;
    public JPanel totalGameNumberOption;
    public JPanel bestOneOption;
    public JPanel bestTwoOption;
    public JPanel bestThreeOption;
    public JPanel bestFourOption;
    public JPanel bestFiveOption;
    public JPanel averageOption;
    JLabel totalGameNumberLabel;
    JLabel bestOneLabel;
    JLabel bestTwoLabel;
    JLabel bestThreeLabel;
    JLabel bestFourLabel;
    JLabel bestFiveLabel;
    JLabel averageLabel;
    GridBagConstraints locationOfLabelInOption;
    JPanel userPanel;
    JLabel userLabel;


    public RecordShowPageWithLimit(Dimension screenSize, User user) {
        this.user = user;
        user.UpdateUserInformationForRecord();
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInUserCompetitionChoosingPage();
    }

    public JPanel getBestOneOption() {
        return bestOneOption;
    }

    public JPanel getBestTwoOption() {
        return bestTwoOption;
    }

    void SetUpOptionsInUserCompetitionChoosingPage() {
        this.SetInsideComponentOfOptions();
        totalGameNumberOption.setVisible(true);
        bestOneOption.setVisible(true);
        bestTwoOption.setVisible(true);
        bestThreeOption.setVisible(true);
        bestFourOption.setVisible(true);
        bestFiveOption.setVisible(true);
        averageOption.setVisible(true);
        this.add(totalGameNumberOption);
        this.add(bestOneOption);
        this.add(bestTwoOption);
        this.add(bestThreeOption);
        this.add(bestFourOption);
        this.add(bestFiveOption);
        this.add(averageOption);
        this.SetUpUserPanel();
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        heightOfComponent = (int)((double)totalHeight / (double) 11);
        widthOfComponent = totalWidth * 5 / 7;
        startX = totalWidth / 7;
    }

    void SetInsideComponentOfOptions() {
        totalGameNumberOption = new JPanel();
        totalGameNumberOption.setLayout(new BorderLayout());
        totalGameNumberOption.setBounds(startX,heightOfComponent*2,widthOfComponent,heightOfComponent);
        totalGameNumberLabel = new JLabel(String.format("Total Game Number:  %d",user.getTotalGameNumberWith()));
        totalGameNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        totalGameNumberLabel.setForeground(Color.BLACK);
        totalGameNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        totalGameNumberLabel.setVerticalAlignment(JLabel.CENTER);
        totalGameNumberOption.add(totalGameNumberLabel,BorderLayout.CENTER);
        bestOneOption = new JPanel();
        bestOneOption.setLayout(new BorderLayout());
        bestOneOption.setBounds(startX,heightOfComponent*3,widthOfComponent,heightOfComponent);
        bestOneLabel = new JLabel(String.format("Best Score 1:  %d",user.getBestOneWith()));
        bestOneLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        bestOneLabel.setForeground(Color.BLACK);
        bestOneLabel.setHorizontalAlignment(JLabel.CENTER);
        bestOneLabel.setVerticalAlignment(JLabel.CENTER);
        bestOneOption.add(bestOneLabel,BorderLayout.CENTER);
        bestTwoOption = new JPanel();
        bestTwoOption.setLayout(new BorderLayout());
        bestTwoOption.setBounds(startX,heightOfComponent*4,widthOfComponent,heightOfComponent);
        bestTwoLabel = new JLabel(String.format("Best Score 2:  %d",user.getBestTwoWith()));
        bestTwoLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        bestTwoLabel.setForeground(Color.BLACK);
        bestTwoLabel.setHorizontalAlignment(JLabel.CENTER);
        bestTwoLabel.setVerticalAlignment(JLabel.CENTER);
        bestTwoOption.add(bestTwoLabel,BorderLayout.CENTER);
        bestThreeOption = new JPanel();
        bestThreeOption.setLayout(new BorderLayout());
        bestThreeOption.setBounds(startX,heightOfComponent*5,widthOfComponent,heightOfComponent);
        bestThreeLabel = new JLabel(String.format("Best Score 3:  %d",user.getBestThreeWith()));
        bestThreeLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        bestThreeLabel.setForeground(Color.BLACK);
        bestThreeLabel.setHorizontalAlignment(JLabel.CENTER);
        bestThreeLabel.setVerticalAlignment(JLabel.CENTER);
        bestThreeOption.add(bestThreeLabel,BorderLayout.CENTER);
        bestFourOption = new JPanel();
        bestFourOption.setLayout(new BorderLayout());
        bestFourOption.setBounds(startX,heightOfComponent*6,widthOfComponent,heightOfComponent);
        bestFourLabel = new JLabel(String.format("Best Score 4:  %d",user.getBestFourWith()));
        bestFourLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        bestFourLabel.setForeground(Color.BLACK);
        bestFourLabel.setHorizontalAlignment(JLabel.CENTER);
        bestFourLabel.setVerticalAlignment(JLabel.CENTER);
        bestFourOption.add(bestFourLabel,BorderLayout.CENTER);
        bestFiveOption = new JPanel();
        bestFiveOption.setLayout(new BorderLayout());
        bestFiveOption.setBounds(startX,heightOfComponent*7,widthOfComponent,heightOfComponent);
        bestFiveLabel = new JLabel(String.format("Best Score 5:  %d",user.getBestFiveWith()));
        bestFiveLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        bestFiveLabel.setForeground(Color.BLACK);
        bestFiveLabel.setHorizontalAlignment(JLabel.CENTER);
        bestFiveLabel.setVerticalAlignment(JLabel.CENTER);
        bestFiveOption.add(bestFiveLabel,BorderLayout.CENTER);
        averageOption= new JPanel();
        averageOption.setLayout(new BorderLayout());
        averageOption.setBounds(startX,heightOfComponent*8,widthOfComponent,heightOfComponent);
        averageLabel = new JLabel(String.format("Average Score:  %d",user.getAverageWith()));
        averageLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        averageLabel.setForeground(Color.BLACK);
        averageLabel.setHorizontalAlignment(JLabel.CENTER);
        averageLabel.setVerticalAlignment(JLabel.CENTER);
        averageOption.add(averageLabel,BorderLayout.CENTER);
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