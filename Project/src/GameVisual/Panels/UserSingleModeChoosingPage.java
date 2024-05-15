package GameVisual.Panels;

import MultiUserSupply.User;

import javax.swing.*;
import java.awt.*;

public class UserSingleModeChoosingPage extends JPanel {

    Dimension totalSize;
    User user;

    int totalWidth;
    int totalHeight;

    int heightOfComponent;
    int widthOfComponent;

    int xOfCenterOfCompetitionOption;
    int xOfCenterOfPracticeOption;
    int yOfCenterOfCompetitionOption;
    int yOfCenterOfPracticeOption;
    public JPanel competitionOption;
    public JPanel practiceOption;
    JLabel competitionLabel;
    JLabel practiceLabel;
    GridBagConstraints locationOfLabelInOption;
    JPanel userPanel;
    JLabel userLabel;


    public UserSingleModeChoosingPage(Dimension screenSize, User user) {
        this.user = user;
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setUpTheLocationOfLabelInOptions();
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInUserSingleModeChoosingPage();
    }

    public JPanel getCompetitionOption() {
        return competitionOption;
    }

    public JPanel getPracticeOption() {
        return practiceOption;
    }

    void SetUpOptionsInUserSingleModeChoosingPage() {
        competitionOption = new JPanel(new GridBagLayout());
        practiceOption = new JPanel(new GridBagLayout());
        competitionOption.setBounds(xOfCenterOfCompetitionOption - widthOfComponent / 2, yOfCenterOfCompetitionOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        practiceOption.setBounds(xOfCenterOfPracticeOption - widthOfComponent / 2, yOfCenterOfPracticeOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        this.SetInsideComponentOfOptions();
        competitionOption.setVisible(true);
        practiceOption.setVisible(true);
        this.add(competitionOption);
        this.add(practiceOption);
        this.SetUpUserPanel();
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        heightOfComponent = totalHeight / 7;
        widthOfComponent = totalWidth * 3 / 7;
        xOfCenterOfCompetitionOption = totalWidth / 2;
        xOfCenterOfPracticeOption = totalWidth / 2;
        yOfCenterOfCompetitionOption = totalHeight * 5 / 14;
        yOfCenterOfPracticeOption = totalHeight * 9 / 14;
    }

    void SetInsideComponentOfOptions() {
        competitionOption.setBackground(Color.LIGHT_GRAY);
        practiceOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        competitionLabel = new JLabel("Competition");
        practiceLabel = new JLabel("Practice");
        competitionLabel.setFont(font);
        practiceLabel.setFont(font);
        competitionLabel.setForeground(Color.WHITE);
        practiceLabel.setForeground(Color.WHITE);
        competitionLabel.setVisible(true);
        practiceLabel.setVisible(true);
        competitionOption.add(competitionLabel, locationOfLabelInOption);
        practiceOption.add(practiceLabel, locationOfLabelInOption);
    }

    void setUpTheLocationOfLabelInOptions() {
        locationOfLabelInOption = new GridBagConstraints();
        locationOfLabelInOption.gridx = 0;
        locationOfLabelInOption.gridy = 0;
        locationOfLabelInOption.anchor = GridBagConstraints.CENTER;
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