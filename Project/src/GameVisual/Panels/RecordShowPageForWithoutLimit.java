package GameVisual.Panels;

import MultiUserSupply.User;

import javax.swing.*;
import java.awt.*;

public class RecordShowPageForWithoutLimit extends JPanel {

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
    public JPanel rateOverSevenThousandOption;
    public JPanel rateOverFourteenThousandOption;
    public JPanel rateWinningOption;
    JLabel totalGameNumberLabel;
    JLabel bestOneLabel;
    JLabel bestTwoLabel;
    JLabel bestThreeLabel;
    JLabel bestFourLabel;
    JLabel bestFiveLabel;
    JLabel averageLabel;
    JLabel rateOverSevenThousandLabel;
    JLabel rateOverFourteenThousandLabel;
    JLabel rateWinningLabel;
    GridBagConstraints locationOfLabelInOption;
    JPanel userPanel;
    JLabel userLabel;


    public RecordShowPageForWithoutLimit(Dimension screenSize, User user) {
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
        rateWinningOption.setVisible(true);
        rateOverSevenThousandOption.setVisible(true);
        rateOverFourteenThousandOption.setVisible(true);
        this.add(totalGameNumberOption);
        this.add(bestOneOption);
        this.add(bestTwoOption);
        this.add(bestThreeOption);
        this.add(bestFourOption);
        this.add(bestFiveOption);
        this.add(averageOption);
        this.add(rateWinningOption);
        this.add(rateOverSevenThousandOption);
        this.add(rateOverFourteenThousandOption);
        this.SetUpUserPanel();
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        heightOfComponent = (int)((double)totalHeight / (double) 14);
        widthOfComponent = totalWidth * 5 / 7;
        startX = totalWidth / 7;
    }

    void SetInsideComponentOfOptions() {
        totalGameNumberOption = new JPanel();
        totalGameNumberOption.setLayout(new BorderLayout());
        totalGameNumberOption.setBounds(startX,heightOfComponent*2,widthOfComponent,heightOfComponent);
        totalGameNumberLabel = new JLabel(String.format("Total Game Number:  %d",user.getTotalGameNumberWithout()));
        totalGameNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        totalGameNumberLabel.setForeground(Color.BLACK);
        totalGameNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        totalGameNumberLabel.setVerticalAlignment(JLabel.CENTER);
        totalGameNumberOption.add(totalGameNumberLabel,BorderLayout.CENTER);
        bestOneOption = new JPanel();
        bestOneOption.setLayout(new BorderLayout());
        bestOneOption.setBounds(startX,heightOfComponent*3,widthOfComponent,heightOfComponent);
        bestOneLabel = new JLabel(String.format("Best Score 1:  %d",user.getBestOneWithout()));
        bestOneLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        bestOneLabel.setForeground(Color.BLACK);
        bestOneLabel.setHorizontalAlignment(JLabel.CENTER);
        bestOneLabel.setVerticalAlignment(JLabel.CENTER);
        bestOneOption.add(bestOneLabel,BorderLayout.CENTER);
        bestTwoOption = new JPanel();
        bestTwoOption.setLayout(new BorderLayout());
        bestTwoOption.setBounds(startX,heightOfComponent*4,widthOfComponent,heightOfComponent);
        bestTwoLabel = new JLabel(String.format("Best Score 2:  %d",user.getBestTwoWithout()));
        bestTwoLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        bestTwoLabel.setForeground(Color.BLACK);
        bestTwoLabel.setHorizontalAlignment(JLabel.CENTER);
        bestTwoLabel.setVerticalAlignment(JLabel.CENTER);
        bestTwoOption.add(bestTwoLabel,BorderLayout.CENTER);
        bestThreeOption = new JPanel();
        bestThreeOption.setLayout(new BorderLayout());
        bestThreeOption.setBounds(startX,heightOfComponent*5,widthOfComponent,heightOfComponent);
        bestThreeLabel = new JLabel(String.format("Best Score 3:  %d",user.getBestThreeWithout()));
        bestThreeLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        bestThreeLabel.setForeground(Color.BLACK);
        bestThreeLabel.setHorizontalAlignment(JLabel.CENTER);
        bestThreeLabel.setVerticalAlignment(JLabel.CENTER);
        bestThreeOption.add(bestThreeLabel,BorderLayout.CENTER);
        bestFourOption = new JPanel();
        bestFourOption.setLayout(new BorderLayout());
        bestFourOption.setBounds(startX,heightOfComponent*6,widthOfComponent,heightOfComponent);
        bestFourLabel = new JLabel(String.format("Best Score 4:  %d",user.getBestFourWithout()));
        bestFourLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        bestFourLabel.setForeground(Color.BLACK);
        bestFourLabel.setHorizontalAlignment(JLabel.CENTER);
        bestFourLabel.setVerticalAlignment(JLabel.CENTER);
        bestFourOption.add(bestFourLabel,BorderLayout.CENTER);
        bestFiveOption = new JPanel();
        bestFiveOption.setLayout(new BorderLayout());
        bestFiveOption.setBounds(startX,heightOfComponent*7,widthOfComponent,heightOfComponent);
        bestFiveLabel = new JLabel(String.format("Best Score 5:  %d",user.getBestFiveWithout()));
        bestFiveLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        bestFiveLabel.setForeground(Color.BLACK);
        bestFiveLabel.setHorizontalAlignment(JLabel.CENTER);
        bestFiveLabel.setVerticalAlignment(JLabel.CENTER);
        bestFiveOption.add(bestFiveLabel,BorderLayout.CENTER);
        averageOption= new JPanel();
        averageOption.setLayout(new BorderLayout());
        averageOption.setBounds(startX,heightOfComponent*8,widthOfComponent,heightOfComponent);
        averageLabel = new JLabel(String.format("Average Score:  %d",user.getAverageWithout()));
        averageLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        averageLabel.setForeground(Color.BLACK);
        averageLabel.setHorizontalAlignment(JLabel.CENTER);
        averageLabel.setVerticalAlignment(JLabel.CENTER);
        averageOption.add(averageLabel,BorderLayout.CENTER);
        rateWinningOption= new JPanel();
        rateWinningOption.setLayout(new BorderLayout());
        rateWinningOption.setBounds(startX,heightOfComponent*9,widthOfComponent,heightOfComponent);
        rateWinningLabel = new JLabel(String.format("Winning Rate:  %d %%",user.getTotalWinRateWithout()));
        rateWinningLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        rateWinningLabel.setForeground(Color.BLACK);
        rateWinningLabel.setHorizontalAlignment(JLabel.CENTER);
        rateWinningLabel.setVerticalAlignment(JLabel.CENTER);
        rateWinningOption.add(rateWinningLabel,BorderLayout.CENTER);
        rateOverSevenThousandOption= new JPanel();
        rateOverSevenThousandOption.setLayout(new BorderLayout());
        rateOverSevenThousandOption.setBounds(startX,heightOfComponent*10,widthOfComponent,heightOfComponent);
        rateOverSevenThousandLabel = new JLabel(String.format("Rate Over 7000:  %d %%",user.getRateOverSevenThousand()));
        rateOverSevenThousandLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        rateOverSevenThousandLabel.setForeground(Color.BLACK);
        rateOverSevenThousandLabel.setHorizontalAlignment(JLabel.CENTER);
        rateOverSevenThousandLabel.setVerticalAlignment(JLabel.CENTER);
        rateOverSevenThousandOption.add(rateOverSevenThousandLabel,BorderLayout.CENTER);
        rateOverFourteenThousandOption= new JPanel();
        rateOverFourteenThousandOption.setLayout(new BorderLayout());
        rateOverFourteenThousandOption.setBounds(startX,heightOfComponent*11,widthOfComponent,heightOfComponent);
        rateOverFourteenThousandLabel = new JLabel(String.format("Rate Over 14000:  %d %%",user.getRateOverFourteenThousand()));
        rateOverFourteenThousandLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        rateOverFourteenThousandLabel.setForeground(Color.BLACK);
        rateOverFourteenThousandLabel.setHorizontalAlignment(JLabel.CENTER);
        rateOverFourteenThousandLabel.setVerticalAlignment(JLabel.CENTER);
        rateOverFourteenThousandOption.add(rateOverFourteenThousandLabel,BorderLayout.CENTER);
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