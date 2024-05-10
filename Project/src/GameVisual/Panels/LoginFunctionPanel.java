package GameVisual.Panels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LoginFunctionPanel extends JPanel {
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
    JPanel loginPanel;
    JLabel loginLabel;
    int thicknessOfField;
    int widthOfField;
    int startXOfField;
    int widthOfLabel;
    Font bigFont;
    Font smallFont;
    Font warningFont;
    JPanel loginConfirmButton;
    JLabel loginConfirmingLabel;
    JPanel noAccount;
    JLabel noAccountLabel;
    JPanel clickHereToGetOne;
    JLabel clickHereToGetOneLabel;
    LoginFunctionPanel(Dimension totalSize, int startX, int startY){
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(totalSize,startX,startY);
        this.SetUpThePanel();
    }

    public JPanel getClickHereToGetOne() {
        return clickHereToGetOne;
    }

    public JPanel getLoginConfirmButton() {
        return loginConfirmButton;
    }

    void UpdateSizeAndLocationForOptions(Dimension totalSize, int startX, int startY){
        this.totalSize = totalSize;
        this.startX = startX;
        this.startY = startY;
        totalWidth = totalSize.width;
        totalHeight = totalSize.height;
        border = BorderFactory.createLineBorder(Color.BLACK, 0, true);
        thicknessOfField = totalHeight/12;
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
        this.SetUpTheLoginTitle();
        this.SetUpUserNameTextField();
        this.SetUpPasswordField();
        this.setBackground(new Color(0xE1AFD1));
        this.SetUpConfirmingAndSwitch();
        this.SetUpRegistrationRecommendation();
        this.add(loginPanel);
        this.add(userNamePanel);
        this.add(passWordPanel);
        this.add(userNameTextField);
        this.add(passWordField);
        this.add(loginConfirmButton);
        this.add(noAccount);
        this.add(clickHereToGetOne);
        this.setVisible(true);
    }
    void SetUpTheLoginTitle(){
        loginPanel = new JPanel();
        loginPanel.setBounds(0,thicknessOfField,totalWidth,3*thicknessOfField);
        loginPanel.setBackground(new Color(0xE1AFD1));
        loginPanel.setLayout(new BorderLayout());
        loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.BLACK);
        loginLabel.setFont(bigFont);
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        loginLabel.setVerticalAlignment(JLabel.CENTER);
        loginPanel.add(loginLabel);
    }
    void SetUpUserNameTextField(){
        userNamePanel = new JPanel();
        userNamePanel.setBounds(0,5*thicknessOfField,totalWidth/3,thicknessOfField);
        userNamePanel.setBackground(new Color(0xE1AFD1));
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
        passWordPanel.setBackground(new Color(0xE1AFD1));
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
    void SetUpConfirmingAndSwitch(){
        loginConfirmButton = new JPanel();
        loginConfirmButton.setBounds(totalWidth/4,9*thicknessOfField,totalWidth/2,3*thicknessOfField/2);
        loginConfirmButton.setBackground(new Color(0xFFE6E6));
        loginConfirmButton.setLayout(new BorderLayout());
        loginConfirmingLabel = new JLabel("Login");
        loginConfirmingLabel.setForeground(Color.BLACK);
        loginConfirmingLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
        loginConfirmingLabel.setHorizontalAlignment(JLabel.CENTER);
        loginConfirmingLabel.setVerticalAlignment(JLabel.CENTER);
        loginConfirmButton.add(loginConfirmingLabel);
    }
    void SetUpRegistrationRecommendation(){
        noAccount = new JPanel();
        noAccount.setBounds(totalWidth/5,21*thicknessOfField/2,totalWidth/3,thicknessOfField);
        noAccount.setBackground(new Color(0xE1AFD1));
        noAccount.setLayout(new BorderLayout());
        noAccountLabel = new JLabel("Don't have an account? ");
        noAccountLabel.setForeground(Color.BLACK);
        noAccountLabel.setFont(warningFont);
        noAccountLabel.setHorizontalAlignment(JLabel.CENTER);
        noAccountLabel.setVerticalAlignment(JLabel.CENTER);
        noAccount.add(noAccountLabel);
        clickHereToGetOne = new JPanel();
        clickHereToGetOne.setBounds(totalWidth/2,21*thicknessOfField/2,totalWidth/3,thicknessOfField);
        clickHereToGetOne.setBackground(new Color(0xE1AFD1));
        clickHereToGetOne.setLayout(new BorderLayout());
        clickHereToGetOneLabel = new JLabel("<html><u>Click here to get one!</u><html>");
        clickHereToGetOneLabel.setForeground(Color.BLACK);
        clickHereToGetOneLabel.setFont(warningFont);
        clickHereToGetOneLabel.setHorizontalAlignment(JLabel.CENTER);
        clickHereToGetOneLabel.setVerticalAlignment(JLabel.CENTER);
        clickHereToGetOne.add(clickHereToGetOneLabel);
    }
}
