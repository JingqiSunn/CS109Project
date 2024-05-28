package GameVisual.Panels;

import MultiUserSupply.User;

import javax.swing.*;
import java.awt.*;

public class UserPracticeWithLimitationModeChoosingPage extends JPanel {

    Dimension totalSize;

    int totalWidth;
    int totalHeight;

    int heightOfComponent;
    int widthOfComponent;

    int xOfCenterOfThreeOption;
    int xOfCenterOfDIYOption;
    int xOfCenterOfFourOption;
    int yOfCenterOfThreeOption;
    int yOfCenterOfDIYOption;
    int yOfCenterOfFourOption;
    public JPanel threeMinutesOption;
    public JPanel sixMinutesOption;
    public JPanel DIYOption;
    JLabel threeMinutesLabel;
    JLabel sixMinutesLabel;
    JLabel DIYLabel;
    GridBagConstraints locationOfLabelInOption;
    JPanel userPanel;
    JLabel userLabel;
    User user;


    public UserPracticeWithLimitationModeChoosingPage(Dimension screenSize, User user) {
        this.user = user;
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setUpTheLocationOfLabelInOptions();
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInTheLoginPage();
        this.SetUpUserPanel();
    }

    public JPanel getThreeMinutesOption() {
        return threeMinutesOption;
    }

    public JPanel getSixMinutesOption() {
        return sixMinutesOption;
    }

    public JPanel getDIYOption() {
        return DIYOption;
    }

    void SetUpOptionsInTheLoginPage() {
        threeMinutesOption = new JPanel(new GridBagLayout());
        sixMinutesOption = new JPanel(new GridBagLayout());
        DIYOption = new JPanel(new GridBagLayout());
        threeMinutesOption.setBounds(xOfCenterOfThreeOption - widthOfComponent / 2, yOfCenterOfThreeOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        sixMinutesOption.setBounds(xOfCenterOfFourOption - widthOfComponent / 2, yOfCenterOfFourOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        DIYOption.setBounds(xOfCenterOfDIYOption - widthOfComponent / 2, yOfCenterOfDIYOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        this.SetInsideComponentOfOptions();
        threeMinutesOption.setVisible(true);
        sixMinutesOption.setVisible(true);
        DIYOption.setVisible(true);
        this.add(threeMinutesOption);
        this.add(sixMinutesOption);
        this.add(DIYOption);
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        heightOfComponent = totalHeight / 7;
        widthOfComponent = totalWidth * 3 / 7;
        xOfCenterOfThreeOption = totalWidth / 2;
        xOfCenterOfFourOption = totalWidth / 2;
        xOfCenterOfDIYOption = totalWidth / 2;
        yOfCenterOfThreeOption = totalHeight * 4 / 14;
        yOfCenterOfFourOption = totalHeight * 7 / 14;
        yOfCenterOfDIYOption = totalHeight * 10 / 14;
    }

    void SetInsideComponentOfOptions() {
        threeMinutesOption.setBackground(Color.LIGHT_GRAY);
        sixMinutesOption.setBackground(Color.LIGHT_GRAY);
        DIYOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        threeMinutesLabel = new JLabel("Three Minutes");
        sixMinutesLabel = new JLabel("Four Minutes");
        DIYLabel = new JLabel("Create Your Own");
        threeMinutesLabel.setFont(font);
        sixMinutesLabel.setFont(font);
        DIYLabel.setFont(font);
        threeMinutesLabel.setForeground(Color.WHITE);
        sixMinutesLabel.setForeground(Color.WHITE);
        DIYLabel.setForeground(Color.WHITE);
        threeMinutesLabel.setVisible(true);
        sixMinutesLabel.setVisible(true);
        DIYLabel.setVisible(true);
        threeMinutesOption.add(threeMinutesLabel, locationOfLabelInOption);
        sixMinutesOption.add(sixMinutesLabel, locationOfLabelInOption);
        DIYOption.add(DIYLabel, locationOfLabelInOption);
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