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

    int xOfCenterOfContentOption;
    int xOfCenterOfBackOption;
    int yOfCenterOfContentOption;
    int yOfCenterOfBackOption;
    public JPanel contentOption;
    public JPanel backOption;
    JLabel contentLabel;
    JLabel backLabel;
    String content;


    public DiePageForMultiUser(Dimension screenSize,String content) {
        this.content = content;
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInUserCompetitionChoosingPage();
    }


    public JPanel getBackOption() {
        return backOption;
    }

    void SetUpOptionsInUserCompetitionChoosingPage() {
        contentOption = new JPanel(new BorderLayout());
        backOption = new JPanel(new BorderLayout());
        contentOption.setBounds(xOfCenterOfContentOption - widthOfComponent / 2, yOfCenterOfContentOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        backOption.setBounds(xOfCenterOfBackOption - widthOfComponent / 2, yOfCenterOfBackOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        this.SetInsideComponentOfOptions();
        contentOption.setVisible(true);
        backOption.setVisible(true);
        this.add(contentOption);
        this.add(backOption);
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        heightOfComponent = totalHeight / 7;
        widthOfComponent = totalWidth * 3 / 7;
        xOfCenterOfContentOption = totalWidth / 2;
        xOfCenterOfBackOption = totalWidth / 2;
        yOfCenterOfContentOption = totalHeight * 5 / 14;
        yOfCenterOfBackOption = totalHeight * 9 / 14;
    }

    void SetInsideComponentOfOptions() {
        backOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        contentLabel = new JLabel(content);
        contentLabel.setHorizontalAlignment(JLabel.CENTER);
        contentLabel.setVerticalAlignment(JLabel.CENTER);
        backLabel = new JLabel("Back to Menu");
        backLabel.setHorizontalAlignment(JLabel.CENTER);
        backLabel.setVerticalAlignment(JLabel.CENTER);
        contentLabel.setFont(font);
        backLabel.setFont(font);
        contentLabel.setForeground(Color.BLACK);
        backLabel.setForeground(Color.WHITE);
        contentLabel.setVisible(true);
        backLabel.setVisible(true);
        contentOption.add(contentLabel, BorderLayout.CENTER);
        backOption.add(backLabel, BorderLayout.CENTER);
    }
}