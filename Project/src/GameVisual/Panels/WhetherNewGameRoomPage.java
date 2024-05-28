package GameVisual.Panels;

import MultiUserSupply.User;

import javax.swing.*;
import java.awt.*;

public class WhetherNewGameRoomPage extends JPanel {

    Dimension totalSize;
    User user;

    int totalWidth;
    int totalHeight;

    int heightOfComponent;
    int widthOfComponent;

    int xOfCenterOfCreateNewGameRoomOption;
    int xOfCenterOfEnterExistingGameRoomOption;
    int yOfCenterOfCreateNewGameRoomOption;
    int yOfCenterOfEnterExistingGameRoomOption;
    public JPanel createNewGameRoomOption;
    public JPanel enterExistingGameRoomOption;
    JLabel createNewGameRoomLabel;
    JLabel enterExistingGameRoomLabel;
    GridBagConstraints locationOfLabelInOption;
    JPanel userPanel;
    JLabel userLabel;


    public WhetherNewGameRoomPage(Dimension screenSize, User user) {
        this.user = user;
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInWhetherNewGameRoomPage();
        this.setVisible(true);
    }

    public JPanel getCreateNewGameRoomOption() {
        return createNewGameRoomOption;
    }

    public JPanel getEnterExistingGameRoomOption() {
        return enterExistingGameRoomOption;
    }

    void SetUpOptionsInWhetherNewGameRoomPage() {
        createNewGameRoomOption = new JPanel(new GridBagLayout());
        enterExistingGameRoomOption = new JPanel(new GridBagLayout());
        createNewGameRoomOption.setBounds(xOfCenterOfCreateNewGameRoomOption - widthOfComponent / 2, yOfCenterOfCreateNewGameRoomOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        enterExistingGameRoomOption.setBounds(xOfCenterOfEnterExistingGameRoomOption - widthOfComponent / 2, yOfCenterOfEnterExistingGameRoomOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        this.SetInsideComponentOfOptions();
        createNewGameRoomOption.setVisible(true);
        enterExistingGameRoomOption.setVisible(true);
        this.add(createNewGameRoomOption);
        this.add(enterExistingGameRoomOption);
        this.SetUpUserPanel();
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        heightOfComponent = totalHeight / 7;
        widthOfComponent = totalWidth * 3 / 7;
        xOfCenterOfCreateNewGameRoomOption = totalWidth / 2;
        xOfCenterOfEnterExistingGameRoomOption = totalWidth / 2;
        yOfCenterOfCreateNewGameRoomOption = totalHeight * 5 / 14;
        yOfCenterOfEnterExistingGameRoomOption = totalHeight * 9 / 14;
    }

    void SetInsideComponentOfOptions() {
        createNewGameRoomOption.setBackground(Color.LIGHT_GRAY);
        enterExistingGameRoomOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        createNewGameRoomLabel = new JLabel("Create New Game Room");
        enterExistingGameRoomLabel = new JLabel("Enter Existing Game Room");
        createNewGameRoomLabel.setFont(font);
        enterExistingGameRoomLabel.setFont(font);
        createNewGameRoomLabel.setForeground(Color.WHITE);
        enterExistingGameRoomLabel.setForeground(Color.WHITE);
        createNewGameRoomLabel.setVisible(true);
        enterExistingGameRoomLabel.setVisible(true);
        createNewGameRoomOption.add(createNewGameRoomLabel, locationOfLabelInOption);
        enterExistingGameRoomOption.add(enterExistingGameRoomLabel, locationOfLabelInOption);
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