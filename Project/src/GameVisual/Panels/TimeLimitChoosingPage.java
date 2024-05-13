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
    public JPanel threeMinutesOption;
    public JPanel sixMinutesOption;
    public JPanel DIYOption;
    JLabel threeMinutesLabel;
    JLabel sixMinutesLabel;
    JLabel DIYLabel;
    GridBagConstraints locationOfLabelInOption;


    public TimeLimitChoosingPage(Dimension screenSize) {
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setUpTheLocationOfLabelInOptions();
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInTheModeChoosingPage();
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

    void SetUpOptionsInTheModeChoosingPage() {
        threeMinutesOption = new JPanel(new GridBagLayout());
        sixMinutesOption = new JPanel(new GridBagLayout());
        DIYOption = new JPanel(new GridBagLayout());
        threeMinutesOption.setBounds(xOfCenterOfOneMinuteOption - widthOfComponent / 2, yOfCenterOfOneMinuteOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        sixMinutesOption.setBounds(xOfCenterOfTwoMinutesOption - widthOfComponent / 2, yOfCenterOfTwoMinutesOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
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
        xOfCenterOfOneMinuteOption = totalWidth / 2;
        xOfCenterOfDIYOption = totalWidth / 2;
        xOfCenterOfTwoMinutesOption = totalWidth / 2;
        yOfCenterOfOneMinuteOption = totalHeight * 4 / 14;
        yOfCenterOfTwoMinutesOption = totalHeight * 7 / 14;
        yOfCenterOfDIYOption = totalHeight * 10 / 14;
    }

    void SetInsideComponentOfOptions() {
        threeMinutesOption.setBackground(Color.LIGHT_GRAY);
        sixMinutesOption.setBackground(Color.LIGHT_GRAY);
        DIYOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        threeMinutesLabel = new JLabel("Three Minutes");
        sixMinutesLabel = new JLabel("Six Minutes");
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
}