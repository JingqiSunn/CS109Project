package GameVisual.Panels;

import javax.swing.*;
import java.awt.*;

public class ModeChoosingPage extends JPanel {

    Dimension totalSize;

    int totalWidth;
    int totalHeight;

    int heightOfComponent;
    int widthOfComponent;

    int xOfCenterOfNoTimeLimitationOption;
    int xOfCenterOfRegistrationOption;
    int xOfCenterOfLimitedTimeOption;
    int yOfCenterOfNoTimeLimitationOption;
    int yOfCenterOfRegistrationOption;
    int yOfCenterOfLimitedTimeOption;
    public JPanel noTimeLimitationOption;
    public JPanel limitedTimeOption;
    public JPanel registerOption;
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
        return registerOption;
    }

    void SetUpOptionsInTheModeChoosingPage() {
        noTimeLimitationOption = new JPanel(new GridBagLayout());
        limitedTimeOption = new JPanel(new GridBagLayout());
        registerOption = new JPanel(new GridBagLayout());
        noTimeLimitationOption.setBounds(xOfCenterOfNoTimeLimitationOption - widthOfComponent / 2, yOfCenterOfNoTimeLimitationOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        limitedTimeOption.setBounds(xOfCenterOfLimitedTimeOption - widthOfComponent / 2, yOfCenterOfLimitedTimeOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        registerOption.setBounds(xOfCenterOfRegistrationOption - widthOfComponent / 2, yOfCenterOfRegistrationOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        this.SetInsideComponentOfOptions();
        noTimeLimitationOption.setVisible(true);
        limitedTimeOption.setVisible(true);
        registerOption.setVisible(true);
        this.add(noTimeLimitationOption);
        this.add(limitedTimeOption);
        this.add(registerOption);
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        heightOfComponent = totalHeight / 7;
        widthOfComponent = totalWidth * 3 / 7;
        xOfCenterOfNoTimeLimitationOption = totalWidth / 2;
        xOfCenterOfRegistrationOption = totalWidth / 2;
        xOfCenterOfLimitedTimeOption = totalWidth / 2;
        yOfCenterOfNoTimeLimitationOption = totalHeight * 4 / 14;
        yOfCenterOfLimitedTimeOption = totalHeight * 7 / 14;
        yOfCenterOfRegistrationOption = totalHeight * 10 / 14;
    }

    void SetInsideComponentOfOptions() {
        noTimeLimitationOption.setBackground(Color.LIGHT_GRAY);
        limitedTimeOption.setBackground(Color.LIGHT_GRAY);
        registerOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        noTimeLimitationLabel = new JLabel("Unlimited Time Mode");
        limitedTimeLabel = new JLabel("Count Down Mode");
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
        registerOption.add(RegisterLabel, locationOfLabelInOption);
    }

    void setUpTheLocationOfLabelInOptions() {
        locationOfLabelInOption = new GridBagConstraints();
        locationOfLabelInOption.gridx = 0;
        locationOfLabelInOption.gridy = 0;
        locationOfLabelInOption.anchor = GridBagConstraints.CENTER;
    }
}