package GameVisual.Panels;

import MultiUserSupply.User;

import javax.swing.*;
import java.awt.*;

public class UserGameTypeChoosingPage extends JPanel {

    Dimension totalSize;

    int totalWidth;
    int totalHeight;

    int heightOfComponent;
    int widthOfComponent;

    int xOfCenterOfSinglePlayerOption;
    int xOfCenterOfRecordOption;
    int xOfCenterOfMultiPlayerOption;
    int yOfCenterOfSinglePlayerOption;
    int yOfCenterOfRecordOption;
    int yOfCenterOfMultiPlayerOption;
    public JPanel singlePlayerOption;
    public JPanel multiPlayerOption;
    public JPanel recordOption;
    JLabel singlePlayerLabel;
    JLabel multiPlayerLabel;
    JLabel recordLabel;
    GridBagConstraints locationOfLabelInOption;
    JPanel userPanel;
    JLabel userLabel;
    User user;
    public UserGameTypeChoosingPage(Dimension screenSize, User user) {
        this.user = user;
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setUpTheLocationOfLabelInOptions();
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInTheUserGameChoosingPage();
    }

    public JPanel getSinglePlayerOption() {
        return singlePlayerOption;
    }

    public JPanel getMultiPlayerOption() {
        return multiPlayerOption;
    }

    public JPanel getRecordOption() {
        return recordOption;
    }

    void SetUpOptionsInTheUserGameChoosingPage() {
        singlePlayerOption = new JPanel(new GridBagLayout());
        multiPlayerOption = new JPanel(new GridBagLayout());
        recordOption = new JPanel(new GridBagLayout());
        singlePlayerOption.setBounds(xOfCenterOfSinglePlayerOption - widthOfComponent / 2, yOfCenterOfSinglePlayerOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        multiPlayerOption.setBounds(xOfCenterOfMultiPlayerOption - widthOfComponent / 2, yOfCenterOfMultiPlayerOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        recordOption.setBounds(xOfCenterOfRecordOption - widthOfComponent / 2, yOfCenterOfRecordOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        this.SetInsideComponentOfOptions();
        this.SetUpUserPanel();
        singlePlayerOption.setVisible(true);
        multiPlayerOption.setVisible(true);
        recordOption.setVisible(true);
        this.add(singlePlayerOption);
        this.add(multiPlayerOption);
        this.add(recordOption);
        this.add(userPanel);
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        heightOfComponent = totalHeight / 7;
        widthOfComponent = totalWidth * 3 / 7;
        xOfCenterOfSinglePlayerOption = totalWidth / 2;
        xOfCenterOfRecordOption = totalWidth / 2;
        xOfCenterOfMultiPlayerOption = totalWidth / 2;
        yOfCenterOfSinglePlayerOption = totalHeight * 4 / 14;
        yOfCenterOfMultiPlayerOption = totalHeight * 7 / 14;
        yOfCenterOfRecordOption = totalHeight * 10 / 14;
    }

    void SetInsideComponentOfOptions() {
        singlePlayerOption.setBackground(Color.LIGHT_GRAY);
        multiPlayerOption.setBackground(Color.LIGHT_GRAY);
        recordOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        singlePlayerLabel = new JLabel("Single Player");
        multiPlayerLabel = new JLabel("MultiPlayer");
        recordLabel = new JLabel("Record");
        singlePlayerLabel.setFont(font);
        multiPlayerLabel.setFont(font);
        recordLabel.setFont(font);
        singlePlayerLabel.setForeground(Color.WHITE);
        multiPlayerLabel.setForeground(Color.WHITE);
        recordLabel.setForeground(Color.WHITE);
        singlePlayerLabel.setVisible(true);
        multiPlayerLabel.setVisible(true);
        recordLabel.setVisible(true);
        singlePlayerOption.add(singlePlayerLabel, locationOfLabelInOption);
        multiPlayerOption.add(multiPlayerLabel, locationOfLabelInOption);
        recordOption.add(recordLabel, locationOfLabelInOption);
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