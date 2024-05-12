package GameVisual.Panels;

import javax.swing.*;
import java.awt.*;

public class TimeLimitChoosingPage extends JPanel {

    Dimension totalSize;

    int totalWidth;
    int totalHeight;

    int heightOfComponent;
    int widthOfComponent;

    int xOfCenterOfOneMinuteOption;
    int xOfCenterOfDIYOption;
    int xOfCenterOfTwoMinutesOption;
    int yOfCenterOfOneMinuteOption;
    int yOfCenterOfDIYOption;
    int yOfCenterOfTwoMinutesOption;
    public JPanel OneMinuteOption;
    public JPanel TwoMinutesOption;
    public JPanel DIYOption;
    JLabel OneMinuteLabel;
    JLabel TwoMinutesLabel;
    JLabel DIYLabel;
    GridBagConstraints locationOfLabelInOption;


    public TimeLimitChoosingPage(Dimension screenSize) {
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setUpTheLocationOfLabelInOptions();
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInTheModeChoosingPage();
    }

    public JPanel getOneMinuteOption() {
        return OneMinuteOption;
    }

    public JPanel getTwoMinutesOption() {
        return TwoMinutesOption;
    }

    public JPanel getDIYOption() {
        return DIYOption;
    }

    void SetUpOptionsInTheModeChoosingPage() {
        OneMinuteOption = new JPanel(new GridBagLayout());
        TwoMinutesOption = new JPanel(new GridBagLayout());
        DIYOption = new JPanel(new GridBagLayout());
        OneMinuteOption.setBounds(xOfCenterOfOneMinuteOption - widthOfComponent / 2, yOfCenterOfOneMinuteOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        TwoMinutesOption.setBounds(xOfCenterOfTwoMinutesOption - widthOfComponent / 2, yOfCenterOfTwoMinutesOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        DIYOption.setBounds(xOfCenterOfDIYOption - widthOfComponent / 2, yOfCenterOfDIYOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        this.SetInsideComponentOfOptions();
        OneMinuteOption.setVisible(true);
        TwoMinutesOption.setVisible(true);
        DIYOption.setVisible(true);
        this.add(OneMinuteOption);
        this.add(TwoMinutesOption);
        this.add(DIYOption);
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        heightOfComponent = totalHeight / 7;
        widthOfComponent = totalWidth * 3 / 7;
        xOfCenterOfOneMinuteOption = totalWidth / 2;
        xOfCenterOfDIYOption = totalWidth / 2;
        xOfCenterOfTwoMinutesOption = totalWidth / 2;
        yOfCenterOfOneMinuteOption = totalHeight * 4 / 14;
        yOfCenterOfTwoMinutesOption = totalHeight * 7 / 14;
        yOfCenterOfDIYOption = totalHeight * 10 / 14;
    }

    void SetInsideComponentOfOptions() {
        OneMinuteOption.setBackground(Color.LIGHT_GRAY);
        TwoMinutesOption.setBackground(Color.LIGHT_GRAY);
        DIYOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        OneMinuteLabel = new JLabel("One Minute");
        TwoMinutesLabel = new JLabel("Two Minutes");
        DIYLabel = new JLabel("Create Your Own");
        OneMinuteLabel.setFont(font);
        TwoMinutesLabel.setFont(font);
        DIYLabel.setFont(font);
        OneMinuteLabel.setForeground(Color.WHITE);
        TwoMinutesLabel.setForeground(Color.WHITE);
        DIYLabel.setForeground(Color.WHITE);
        OneMinuteLabel.setVisible(true);
        TwoMinutesLabel.setVisible(true);
        DIYLabel.setVisible(true);
        OneMinuteOption.add(OneMinuteLabel, locationOfLabelInOption);
        TwoMinutesOption.add(TwoMinutesLabel, locationOfLabelInOption);
        DIYOption.add(DIYLabel, locationOfLabelInOption);
    }

    void setUpTheLocationOfLabelInOptions() {
        locationOfLabelInOption = new GridBagConstraints();
        locationOfLabelInOption.gridx = 0;
        locationOfLabelInOption.gridy = 0;
        locationOfLabelInOption.anchor = GridBagConstraints.CENTER;
    }
}