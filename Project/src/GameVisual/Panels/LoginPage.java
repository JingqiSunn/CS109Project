package GameVisual.Panels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginPage extends JPanel {

    Dimension totalSize;

    int totalWidth;
    int totalHeight;

    int heightOfComponent;
    int widthOfComponent;

    int xOfCenterOfLoginOption;
    int xOfCenterOfRegistrationOption;
    int xOfCenterOfTouristOption;
    int yOfCenterOfLoginOption;
    int yOfCenterOfRegistrationOption;
    int yOfCenterOfTouristOption;
    public JPanel loginOption;
    public JPanel touristOption;
    public JPanel registrationOption;
    JLabel loginLabel;
    JLabel touristLabel;
    JLabel registrationLabel;
    GridBagConstraints locationOfLabelInOption;


    public LoginPage(Dimension screenSize) {
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setUpTheLocationOfLabelInOptions();
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInTheLoginPage();
    }

    public JPanel getLoginOption() {
        return loginOption;
    }

    public JPanel getTouristOption() {
        return touristOption;
    }

    public JPanel getRegistrationOption() {
        return registrationOption;
    }

    void SetUpOptionsInTheLoginPage() {
        loginOption = new JPanel(new GridBagLayout());
        touristOption = new JPanel(new GridBagLayout());
        registrationOption = new JPanel(new GridBagLayout());
        loginOption.setBounds(xOfCenterOfLoginOption - widthOfComponent / 2, yOfCenterOfLoginOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        touristOption.setBounds(xOfCenterOfTouristOption - widthOfComponent / 2, yOfCenterOfTouristOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        registrationOption.setBounds(xOfCenterOfRegistrationOption - widthOfComponent / 2, yOfCenterOfRegistrationOption - heightOfComponent / 2, widthOfComponent, heightOfComponent);
        this.SetInsideComponentOfOptions();
        loginOption.setVisible(true);
        touristOption.setVisible(true);
        registrationOption.setVisible(true);
        this.add(loginOption);
        this.add(touristOption);
        this.add(registrationOption);
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        heightOfComponent = totalHeight / 7;
        widthOfComponent = totalWidth * 3 / 7;
        xOfCenterOfLoginOption = totalWidth / 2;
        xOfCenterOfTouristOption = totalWidth / 2;
        xOfCenterOfRegistrationOption = totalWidth / 2;
        yOfCenterOfLoginOption = totalHeight * 4 / 14;
        yOfCenterOfTouristOption = totalHeight * 7 / 14;
        yOfCenterOfRegistrationOption = totalHeight * 10 / 14;
    }

    void SetInsideComponentOfOptions() {
        loginOption.setBackground(Color.LIGHT_GRAY);
        touristOption.setBackground(Color.LIGHT_GRAY);
        registrationOption.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Times New Roman", Font.BOLD, 40);
        loginLabel = new JLabel("Login");
        touristLabel = new JLabel("Tourist");
        registrationLabel = new JLabel("Registration");
        loginLabel.setFont(font);
        touristLabel.setFont(font);
        registrationLabel.setFont(font);
        loginLabel.setForeground(Color.WHITE);
        touristLabel.setForeground(Color.WHITE);
        registrationLabel.setForeground(Color.WHITE);
        loginLabel.setVisible(true);
        touristLabel.setVisible(true);
        registrationLabel.setVisible(true);
        loginOption.add(loginLabel, locationOfLabelInOption);
        touristOption.add(touristLabel, locationOfLabelInOption);
        registrationOption.add(registrationLabel, locationOfLabelInOption);
    }

    void setUpTheLocationOfLabelInOptions() {
        locationOfLabelInOption = new GridBagConstraints();
        locationOfLabelInOption.gridx = 0;
        locationOfLabelInOption.gridy = 0;
        locationOfLabelInOption.anchor = GridBagConstraints.CENTER;
    }
}