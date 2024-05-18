package GameVisual.Panels;

import MultiUserSupply.User;

import javax.swing.*;
        import java.awt.*;

public class UserPracticeWithoutTimeLimitModeWhetherNewPage extends JPanel {

    Dimension totalSize;
    User user;

    int totalWidth;
    int totalHeight;

    int heightOfComponent;
    int widthOfComponent;

    int xOfCenterOfNewGameOption;
    int xOfCenterOfExistingArchiveOption;
    int yOfCenterOfNewGameLimitationOption;
    int yOfCenterOfExistingArchiveOption;
    public JPanel newGameLimitationOption;
    public JPanel existingArchiveOption;
    JLabel newGameLabel;
    JLabel existingArchiveLabel;
    GridBagConstraints locationOfLabelInOption;
    JPanel userPanel;
    JLabel userLabel;


    public UserPracticeWithoutTimeLimitModeWhetherNewPage(Dimension screenSize, User user) {
        this.user = user;
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setUpTheLocationOfLabelInOptions();
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInUserPracticeWithoutTimeLimitModeWhetherNewPage();
    }

    public JPanel getNewGameOption() {
        return newGameLimitationOption;
    }

    public JPanel getExistingArchiveOption() {
        return existingArchiveOption;
    }

    void SetUpOptionsInUserPracticeWithoutTimeLimitModeWhetherNewPage() {
        newGameLimitationOption = new JPanel(new GridBagLayout());
        existingArchiveOption = new JPanel(new GridBagLayout());
        newGameLimitationOption.setBounds(xOfCenterOfNewGameOption - widthOfComponent / 2, yOfCenterOfNewGameLimitationOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        existingArchiveOption.setBounds(xOfCenterOfExistingArchiveOption - widthOfComponent / 2, yOfCenterOfExistingArchiveOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        this.SetInsideComponentOfOptions();
        newGameLimitationOption.setVisible(true);
        existingArchiveOption.setVisible(true);
        this.add(newGameLimitationOption);
        this.add(existingArchiveOption);
        this.SetUpUserPanel();
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        heightOfComponent = totalHeight / 7;
        widthOfComponent = totalWidth * 3 / 7;
        xOfCenterOfNewGameOption = totalWidth / 2;
        xOfCenterOfExistingArchiveOption = totalWidth / 2;
        yOfCenterOfNewGameLimitationOption = totalHeight * 5 / 14;
        yOfCenterOfExistingArchiveOption = totalHeight * 9 / 14;
    }

    void SetInsideComponentOfOptions() {
        newGameLimitationOption.setBackground(Color.LIGHT_GRAY);
        existingArchiveOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        newGameLabel = new JLabel("New Game");
        existingArchiveLabel = new JLabel("Existing Archive");
        newGameLabel.setFont(font);
        existingArchiveLabel.setFont(font);
        newGameLabel.setForeground(Color.WHITE);
        existingArchiveLabel.setForeground(Color.WHITE);
        newGameLabel.setVisible(true);
        existingArchiveLabel.setVisible(true);
        newGameLimitationOption.add(newGameLabel, locationOfLabelInOption);
        existingArchiveOption.add(existingArchiveLabel, locationOfLabelInOption);
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