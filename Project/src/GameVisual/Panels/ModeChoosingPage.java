package GameVisual.Panels;

import javax.swing.*;
import java.awt.*;

public class ModeChoosingPage extends JPanel {

    Dimension totalSize;

    int totalWidth;
    int totalHeight;

    int heightOfComponent;
    int widthOfComponent;

    int xOfCenterOfSinglePlayerOption;
    int xOfCenterOfOtherModesOption;
    int xOfCenterOfMultiplePlayerOption;
    int yOfCenterOfSinglePlayerOption;
    int yOfCenterOfOtherModesOption;
    int yOfCenterOfMultiplePlayerOption;
    public JPanel noTimeLimitationOption;
    public JPanel limitedTimeOption;
    public JPanel RegisterOption;
    JLabel noTimeLimitationLabel;
    JLabel limitedTimeLabel;
    JLabel RegisterLabel;
    GridBagConstraints locationOfLabelInOption;


    public ModeChoosingPage(Dimension screenSize) {
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setUpTheLocationOfLabelInOptions();
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInTheModeChoosingPage();
    }

    public JPanel getNoTimeLimitationOption() {
        return noTimeLimitationOption;
    }

    public JPanel getLimitedTimeOption() {
        return limitedTimeOption;
    }

    public JPanel getRegisterOption() {
        return RegisterOption;
    }

    void SetUpOptionsInTheModeChoosingPage() {
        noTimeLimitationOption = new JPanel(new GridBagLayout());
        limitedTimeOption = new JPanel(new GridBagLayout());
        RegisterOption = new JPanel(new GridBagLayout());
        noTimeLimitationOption.setBounds(xOfCenterOfSinglePlayerOption - widthOfComponent / 2, yOfCenterOfSinglePlayerOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        limitedTimeOption.setBounds(xOfCenterOfMultiplePlayerOption - widthOfComponent / 2, yOfCenterOfMultiplePlayerOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        RegisterOption.setBounds(xOfCenterOfOtherModesOption - widthOfComponent / 2, yOfCenterOfOtherModesOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        this.SetInsideComponentOfOptions();
        noTimeLimitationOption.setVisible(true);
        limitedTimeOption.setVisible(true);
        RegisterOption.setVisible(true);
        this.add(noTimeLimitationOption);
        this.add(limitedTimeOption);
        this.add(RegisterOption);
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        heightOfComponent = totalHeight / 7;
        widthOfComponent = totalWidth * 3 / 7;
        xOfCenterOfSinglePlayerOption = totalWidth / 2;
        xOfCenterOfOtherModesOption = totalWidth / 2;
        xOfCenterOfMultiplePlayerOption = totalWidth / 2;
        yOfCenterOfSinglePlayerOption = totalHeight * 4 / 14;
        yOfCenterOfMultiplePlayerOption = totalHeight * 7 / 14;
        yOfCenterOfOtherModesOption = totalHeight * 10 / 14;
    }

    void SetInsideComponentOfOptions() {
        noTimeLimitationOption.setBackground(Color.LIGHT_GRAY);
        limitedTimeOption.setBackground(Color.LIGHT_GRAY);
        RegisterOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        noTimeLimitationLabel = new JLabel("Unlimited Time Mode");
        limitedTimeLabel = new JLabel("CountDown Mode");
        RegisterLabel = new JLabel("Register");
        noTimeLimitationLabel.setFont(font);
        limitedTimeLabel.setFont(font);
        RegisterLabel.setFont(font);
        noTimeLimitationLabel.setForeground(Color.WHITE);
        limitedTimeLabel.setForeground(Color.WHITE);
        RegisterLabel.setForeground(Color.WHITE);
        noTimeLimitationLabel.setVisible(true);
        limitedTimeLabel.setVisible(true);
        RegisterLabel.setVisible(true);
        noTimeLimitationOption.add(noTimeLimitationLabel, locationOfLabelInOption);
        limitedTimeOption.add(limitedTimeLabel, locationOfLabelInOption);
        RegisterOption.add(RegisterLabel, locationOfLabelInOption);
    }

    void setUpTheLocationOfLabelInOptions() {
        locationOfLabelInOption = new GridBagConstraints();
        locationOfLabelInOption.gridx = 0;
        locationOfLabelInOption.gridy = 0;
        locationOfLabelInOption.anchor = GridBagConstraints.CENTER;
    }
}