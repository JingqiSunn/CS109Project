package GameVisual.Panels;

import MultiUserSupply.User;

import javax.swing.*;
import java.awt.*;

public class DiePageForMultiUser extends JPanel {

    Dimension totalSize;
    User user;

    int totalWidth;
    int totalHeight;

    int heightOfComponent;
    int widthOfComponent;

    int xOfCenterOfWithoutTimeLimitationOption;
    int xOfCenterOfWithTimeLimitationOption;
    int yOfCenterOfWithoutTimeLimitationOption;
    int yOfCenterOfWithTimeLimitationOption;
    public JPanel withoutTimeLimitationOption;
    public JPanel withTimeLimitationOption;
    JLabel withoutTimeLimitationLabel;
    JLabel withTimeLimitationLabel;
    GridBagConstraints locationOfLabelInOption;
    String content;


    public DiePageForMultiUser(Dimension screenSize,String content) {
        this.content = content;
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setUpTheLocationOfLabelInOptions();
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInUserCompetitionChoosingPage();
    }


    public JPanel getWithTimeLimitationOption() {
        return withTimeLimitationOption;
    }

    void SetUpOptionsInUserCompetitionChoosingPage() {
        withoutTimeLimitationOption = new JPanel(new GridBagLayout());
        withTimeLimitationOption = new JPanel(new GridBagLayout());
        withoutTimeLimitationOption.setBounds(xOfCenterOfWithoutTimeLimitationOption - widthOfComponent / 2, yOfCenterOfWithoutTimeLimitationOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        withTimeLimitationOption.setBounds(xOfCenterOfWithTimeLimitationOption - widthOfComponent / 2, yOfCenterOfWithTimeLimitationOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        this.SetInsideComponentOfOptions();
        withoutTimeLimitationOption.setVisible(true);
        withTimeLimitationOption.setVisible(true);
        this.add(withoutTimeLimitationOption);
        this.add(withTimeLimitationOption);
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        heightOfComponent = totalHeight / 7;
        widthOfComponent = totalWidth * 3 / 7;
        xOfCenterOfWithoutTimeLimitationOption = totalWidth / 2;
        xOfCenterOfWithTimeLimitationOption = totalWidth / 2;
        yOfCenterOfWithoutTimeLimitationOption = totalHeight * 5 / 14;
        yOfCenterOfWithTimeLimitationOption = totalHeight * 9 / 14;
    }

    void SetInsideComponentOfOptions() {
        withTimeLimitationOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        withoutTimeLimitationLabel = new JLabel(content);
        withTimeLimitationLabel = new JLabel("With Time Limitation");
        withoutTimeLimitationLabel.setFont(font);
        withTimeLimitationLabel.setFont(font);
        withoutTimeLimitationLabel.setForeground(Color.BLACK);
        withTimeLimitationLabel.setForeground(Color.WHITE);
        withoutTimeLimitationLabel.setVisible(true);
        withTimeLimitationLabel.setVisible(true);
        withoutTimeLimitationOption.add(withoutTimeLimitationLabel, locationOfLabelInOption);
        withTimeLimitationOption.add(withTimeLimitationLabel, locationOfLabelInOption);
    }

    void setUpTheLocationOfLabelInOptions() {
        locationOfLabelInOption = new GridBagConstraints();
        locationOfLabelInOption.gridx = 0;
        locationOfLabelInOption.gridy = 0;
        locationOfLabelInOption.anchor = GridBagConstraints.CENTER;
    }
}