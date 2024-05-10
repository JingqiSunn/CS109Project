package GameVisual.Panels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class RegistrationFunctionPanel extends JPanel {
    Dimension totalSize;
    int totalWidth;
    int totalHeight;
    int startX;
    int startY;
    Border border;
    JTextField userNameTextField;
    JPanel userNamePanel;
    JLabel userNameLabel;
    JPasswordField passWordField;
    JPanel passWordPanel;
    JLabel passWordLabel;
    JPasswordField againPassWordField;
    JPanel againPassWordPanel;
    JLabel againPassWordLabel;
    JPanel registrationPanel;
    JLabel registrationLabel;
    int thicknessOfField;
    int widthOfField;
    int startXOfField;
    int widthOfLabel;
    Font bigFont;
    Font smallFont;
    Font warningFont;
    JPanel registrationConfirmButton;
    JLabel registrationConfirmingLabel;
    JPanel alreadyHaveAccount;
    JLabel alreadyHaveAccountLabel;
    JPanel clickHereToLogin;
    JLabel clickHereToLoginLabel;
    JPanel warnForUserName;
    JPanel warnForPassWord;
    JPanel warnForAgainPassWord;
    JLabel warnForUserNameLabel;
    JLabel warnForPassWordLabel;
    JLabel warnForAgainPassWordLabel;

    RegistrationFunctionPanel(Dimension totalSize, int startX, int startY){
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(totalSize,startX,startY);
        this.SetUpThePanel();
    }

    public JPanel getClickHereToLogin() {
        return clickHereToLogin;
    }

    public JPanel getRegistrationConfirmButton() {
        return registrationConfirmButton;
    }

    void UpdateSizeAndLocationForOptions(Dimension totalSize, int startX, int startY){
        this.totalSize = totalSize;
        this.startX = startX;
        this.startY = startY;
        totalWidth = totalSize.width;
        totalHeight = totalSize.height;
        border = BorderFactory.createLineBorder(Color.BLACK, 0, true);
        thicknessOfField = totalHeight/14;
        widthOfField = totalWidth*4/7;
        widthOfLabel = totalWidth*2/7;
        startXOfField = widthOfLabel;
        bigFont = new Font("Lucida Calligraphy", Font.BOLD, 100);
        smallFont = new Font("Times New Roman", Font.BOLD, 30);
        warningFont = new Font("Times New Roman", Font.BOLD, 15);
    }
    void SetUpThePanel(){
        this.setBorder(border);
        this.setBounds(startX,startY,totalWidth,totalHeight);
        userNameTextField = new JTextField();
        passWordField = new JPasswordField();
        this.SetUpTheRegistrationTitle();
        this.SetUpUserNameTextField();
        this.SetUpPasswordField();
        this.SetUpAgainPasswordField();
        this.setBackground(new Color(0xE3E1D9));
        this.SetUpConfirmingAndSwitch();
        this.SetUpLoginRecommendation();
        this.add(registrationPanel);
        this.add(userNamePanel);
        this.add(passWordPanel);
        this.add(againPassWordPanel);
        this.add(userNameTextField);
        this.add(passWordField);
        this.add(againPassWordField);
        this.add(registrationConfirmButton);
        this.add(alreadyHaveAccount);
        this.add(clickHereToLogin);
        this.setVisible(true);
    }
    void SetUpTheRegistrationTitle(){
        registrationPanel = new JPanel();
        registrationPanel.setBounds(0,thicknessOfField,totalWidth,3*thicknessOfField);
        registrationPanel.setBackground(new Color(0xE3E1D9));
        registrationPanel.setLayout(new BorderLayout());
        registrationLabel = new JLabel("Register");
        registrationLabel.setForeground(Color.BLACK);
        registrationLabel.setFont(bigFont);
        registrationLabel.setHorizontalAlignment(JLabel.CENTER);
        registrationLabel.setVerticalAlignment(JLabel.CENTER);
        registrationPanel.add(registrationLabel);
    }
    void SetUpUserNameTextField(){
        userNamePanel = new JPanel();
        userNamePanel.setBounds(0,5*thicknessOfField,totalWidth/3,thicknessOfField);
        userNamePanel.setBackground(new Color(0xE3E1D9));
        userNamePanel.setLayout(new BorderLayout());
        userNameLabel = new JLabel("User Name:");
        userNameLabel.setForeground(Color.BLACK);
        userNameLabel.setFont(smallFont);
        userNameLabel.setHorizontalAlignment(JLabel.CENTER);
        userNameLabel.setVerticalAlignment(JLabel.CENTER);
        userNamePanel.add(userNameLabel);
        userNameTextField = new JTextField();
        userNameTextField.setFont(smallFont);
        userNameTextField.setBounds(totalWidth/3,5*thicknessOfField,(int)((double)totalWidth*(double)9/(double)15 ),thicknessOfField);
    }
    void SetUpPasswordField(){
        passWordPanel = new JPanel();
        passWordPanel.setBounds(0,7*thicknessOfField,totalWidth/3,thicknessOfField);
        passWordPanel.setBackground(new Color(0xE3E1D9));
        passWordPanel.setLayout(new BorderLayout());
        passWordLabel = new JLabel("Password:");
        passWordLabel.setForeground(Color.BLACK);
        passWordLabel.setFont(smallFont);
        passWordLabel.setHorizontalAlignment(JLabel.CENTER);
        passWordLabel.setVerticalAlignment(JLabel.CENTER);
        passWordPanel.add(passWordLabel);
        passWordField = new JPasswordField();
        passWordField.setFont(smallFont);
        passWordField.setBounds(totalWidth/3,7*thicknessOfField,(int)((double)totalWidth*(double)9/(double)15 ),thicknessOfField);
    }
    void SetUpAgainPasswordField(){
        againPassWordPanel = new JPanel();
        againPassWordPanel.setBounds(0,9*thicknessOfField,totalWidth/3,thicknessOfField);
        againPassWordPanel.setBackground(new Color(0xE3E1D9));
        againPassWordPanel.setLayout(new BorderLayout());
        againPassWordLabel = new JLabel("Confirm:");
        againPassWordLabel.setForeground(Color.BLACK);
        againPassWordLabel.setFont(smallFont);
        againPassWordLabel.setHorizontalAlignment(JLabel.CENTER);
        againPassWordLabel.setVerticalAlignment(JLabel.CENTER);
        againPassWordPanel.add(againPassWordLabel);
        againPassWordField = new JPasswordField();
        againPassWordField.setFont(smallFont);
        againPassWordField.setBounds(totalWidth/3,9*thicknessOfField,(int)((double)totalWidth*(double)9/(double)15 ),thicknessOfField);
    }
    void SetUpConfirmingAndSwitch(){
        registrationConfirmButton = new JPanel();
        registrationConfirmButton.setBounds(totalWidth/4,11*thicknessOfField,totalWidth/2,3*thicknessOfField/2);
        registrationConfirmButton.setBackground(new Color(0xF2EFE5));
        registrationConfirmButton.setLayout(new BorderLayout());
        registrationConfirmingLabel = new JLabel("Create");
        registrationConfirmingLabel.setForeground(Color.BLACK);
        registrationConfirmingLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
        registrationConfirmingLabel.setHorizontalAlignment(JLabel.CENTER);
        registrationConfirmingLabel.setVerticalAlignment(JLabel.CENTER);
        registrationConfirmButton.add(registrationConfirmingLabel);
    }
    void SetUpLoginRecommendation(){
        alreadyHaveAccount = new JPanel();
        alreadyHaveAccount.setBounds(totalWidth/5,25*thicknessOfField/2,totalWidth/3,thicknessOfField);
        alreadyHaveAccount.setBackground(new Color(0xE3E1D9));
        alreadyHaveAccount.setLayout(new BorderLayout());
        alreadyHaveAccountLabel = new JLabel("Already have an account? ");
        alreadyHaveAccountLabel.setForeground(Color.BLACK);
        alreadyHaveAccountLabel.setFont(warningFont);
        alreadyHaveAccountLabel.setHorizontalAlignment(JLabel.CENTER);
        alreadyHaveAccountLabel.setVerticalAlignment(JLabel.CENTER);
        alreadyHaveAccount.add(alreadyHaveAccountLabel);
        clickHereToLogin = new JPanel();
        clickHereToLogin.setBounds(totalWidth/2,25*thicknessOfField/2,totalWidth/3,thicknessOfField);
        clickHereToLogin.setBackground(new Color(0xE3E1D9));
        clickHereToLogin.setLayout(new BorderLayout());
        clickHereToLoginLabel = new JLabel("<html><u>Click here login!</u><html>");
        clickHereToLoginLabel.setForeground(Color.BLACK);
        clickHereToLoginLabel.setFont(warningFont);
        clickHereToLoginLabel.setHorizontalAlignment(JLabel.CENTER);
        clickHereToLoginLabel.setVerticalAlignment(JLabel.CENTER);
        clickHereToLogin.add(clickHereToLoginLabel);
    }
    public void EstablishWarnForUserName(String wordsOutput){
        warnForUserName = new JPanel();
        warnForUserName.setBounds(totalWidth/4,11*thicknessOfField,totalWidth/2,3*thicknessOfField/2);
        registrationConfirmButton.setBackground(new Color(0xF2EFE5));
        registrationConfirmButton.setLayout(new BorderLayout());
        registrationConfirmingLabel = new JLabel("Create");
        registrationConfirmingLabel.setForeground(Color.BLACK);
        registrationConfirmingLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
        registrationConfirmingLabel.setHorizontalAlignment(JLabel.CENTER);
        registrationConfirmingLabel.setVerticalAlignment(JLabel.CENTER);
        registrationConfirmButton.add(registrationConfirmingLabel);
    }
}
