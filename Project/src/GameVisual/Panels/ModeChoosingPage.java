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
    public JPanel singlePlayerOption;
    public JPanel multiPlayersOption;
    public JPanel otherModesOption;
    JLabel singlePlayerLabel;
    JLabel multiPlayersLabel;
    JLabel otherModesLabel;
    GridBagConstraints locationOfLabelInOption;


    public ModeChoosingPage(Dimension screenSize) {
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setUpTheLocationOfLabelInOptions();
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInTheModeChoosingPage();
    }

    public JPanel getSinglePlayerOption() {
        return singlePlayerOption;
    }

    public JPanel getMultiPlayersOption() {
        return multiPlayersOption;
    }

    public JPanel getOtherModesOption() {
        return otherModesOption;
    }

    void SetUpOptionsInTheModeChoosingPage() {
        singlePlayerOption = new JPanel(new GridBagLayout());
        multiPlayersOption = new JPanel(new GridBagLayout());
        otherModesOption = new JPanel(new GridBagLayout());
        singlePlayerOption.setBounds(xOfCenterOfSinglePlayerOption - widthOfComponent / 2, yOfCenterOfSinglePlayerOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        multiPlayersOption.setBounds(xOfCenterOfMultiplePlayerOption - widthOfComponent / 2, yOfCenterOfMultiplePlayerOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        otherModesOption.setBounds(xOfCenterOfOtherModesOption - widthOfComponent / 2, yOfCenterOfOtherModesOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        this.SetInsideComponentOfOptions();
        singlePlayerOption.setVisible(true);
        multiPlayersOption.setVisible(true);
        otherModesOption.setVisible(true);
        this.add(singlePlayerOption);
        this.add(multiPlayersOption);
        this.add(otherModesOption);
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
        singlePlayerOption.setBackground(Color.LIGHT_GRAY);
        multiPlayersOption.setBackground(Color.LIGHT_GRAY);
        otherModesOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        singlePlayerLabel = new JLabel("Single Player");
        multiPlayersLabel = new JLabel("Multiple Players");
        otherModesLabel = new JLabel("Other Modes");
        singlePlayerLabel.setFont(font);
        multiPlayersLabel.setFont(font);
        otherModesLabel.setFont(font);
        singlePlayerLabel.setForeground(Color.WHITE);
        multiPlayersLabel.setForeground(Color.WHITE);
        otherModesLabel.setForeground(Color.WHITE);
        singlePlayerLabel.setVisible(true);
        multiPlayersLabel.setVisible(true);
        otherModesLabel.setVisible(true);
        singlePlayerOption.add(singlePlayerLabel, locationOfLabelInOption);
        multiPlayersOption.add(multiPlayersLabel, locationOfLabelInOption);
        otherModesOption.add(otherModesLabel, locationOfLabelInOption);
    }

    void setUpTheLocationOfLabelInOptions() {
        locationOfLabelInOption = new GridBagConstraints();
        locationOfLabelInOption.gridx = 0;
        locationOfLabelInOption.gridy = 0;
        locationOfLabelInOption.anchor = GridBagConstraints.CENTER;
    }
}