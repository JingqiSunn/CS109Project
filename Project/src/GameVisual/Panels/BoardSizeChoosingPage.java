package GameVisual.Panels;

import javax.swing.*;
import java.awt.*;

public class BoardSizeChoosingPage extends JPanel {

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
    public JPanel ThreeOption;
    public JPanel FourOption;
    public JPanel DIYOption;
    JLabel ThreeLabel;
    JLabel FourLabel;
    JLabel DIYLabel;
    GridBagConstraints locationOfLabelInOption;


    public BoardSizeChoosingPage(Dimension screenSize) {
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setUpTheLocationOfLabelInOptions();
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInTheLoginPage();
    }

    public JPanel getThreeOption() {
        return ThreeOption;
    }

    public JPanel getFourOption() {
        return FourOption;
    }

    public JPanel getDIYOption() {
        return DIYOption;
    }

    void SetUpOptionsInTheLoginPage() {
        ThreeOption = new JPanel(new GridBagLayout());
        FourOption = new JPanel(new GridBagLayout());
        DIYOption = new JPanel(new GridBagLayout());
        ThreeOption.setBounds(xOfCenterOfThreeOption - widthOfComponent / 2, yOfCenterOfThreeOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        FourOption.setBounds(xOfCenterOfFourOption - widthOfComponent / 2, yOfCenterOfFourOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        DIYOption.setBounds(xOfCenterOfDIYOption - widthOfComponent / 2, yOfCenterOfDIYOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        this.SetInsideComponentOfOptions();
        ThreeOption.setVisible(true);
        FourOption.setVisible(true);
        DIYOption.setVisible(true);
        this.add(ThreeOption);
        this.add(FourOption);
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
        ThreeOption.setBackground(Color.LIGHT_GRAY);
        FourOption.setBackground(Color.LIGHT_GRAY);
        DIYOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        ThreeLabel = new JLabel("Three By Three");
        FourLabel = new JLabel("Four By Four");
        DIYLabel = new JLabel("Create Your Own");
        ThreeLabel.setFont(font);
        FourLabel.setFont(font);
        DIYLabel.setFont(font);
        ThreeLabel.setForeground(Color.WHITE);
        FourLabel.setForeground(Color.WHITE);
        DIYLabel.setForeground(Color.WHITE);
        ThreeLabel.setVisible(true);
        FourLabel.setVisible(true);
        DIYLabel.setVisible(true);
        ThreeOption.add(ThreeLabel, locationOfLabelInOption);
        FourOption.add(FourLabel, locationOfLabelInOption);
        DIYOption.add(DIYLabel, locationOfLabelInOption);
    }

    void setUpTheLocationOfLabelInOptions() {
        locationOfLabelInOption = new GridBagConstraints();
        locationOfLabelInOption.gridx = 0;
        locationOfLabelInOption.gridy = 0;
        locationOfLabelInOption.anchor = GridBagConstraints.CENTER;
    }
}