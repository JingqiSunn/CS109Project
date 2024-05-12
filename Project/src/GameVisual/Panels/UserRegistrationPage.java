package GameVisual.Panels;

import javax.swing.*;
import java.awt.*;

public class UserRegistrationPage extends JPanel {
    Dimension totalSize;

    int totalWidth;
    int totalHeight;
    int startXOfRegistrationFunctionPanel;
    int startYOfRegistrationFunctionPanel;
    int widthOfRegistrationFunctionPanel;
    int heightYOfRegistrationFunctionPanel;
    Dimension sizeOfRegistrationFunctionPanel;
    RegistrationFunctionPanel registrationFunctionPanel;
    SuccessfullyRegisteredFunctionPanel successfullyRegisteredFunctionPanel;
    JPanel upperPart;
    JPanel lowerPart;


    public UserRegistrationPage(Dimension screenSize){
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.SetUpThePanel();
    }
    public JPanel GetClickHereToLoginPanel(){
        return registrationFunctionPanel.getClickHereToLogin();
    }
    public JPanel GetRegistrationConfirmPanel(){
        if (registrationFunctionPanel==null){
            return null;
        } else{ return registrationFunctionPanel.getRegistrationConfirmButton();}
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        startXOfRegistrationFunctionPanel = totalWidth/3;
        widthOfRegistrationFunctionPanel = totalWidth/3;
        startYOfRegistrationFunctionPanel = totalHeight/7;
        heightYOfRegistrationFunctionPanel = totalHeight*5/7;
        sizeOfRegistrationFunctionPanel = new Dimension(widthOfRegistrationFunctionPanel, heightYOfRegistrationFunctionPanel);
    }
    void SetUpThePanel() {
        this.setBounds(0,0,totalWidth,totalHeight);
        upperPart = new JPanel();
        upperPart.setBounds(0,0,totalWidth,totalHeight*2/3);
        upperPart.setBackground(new Color(0xB4B4B8));
        lowerPart = new JPanel();
        lowerPart.setBounds(0,totalHeight*2/3,totalWidth,totalHeight/3);
        lowerPart.setBackground(new Color(0xC7C8CC));
        registrationFunctionPanel = new RegistrationFunctionPanel(sizeOfRegistrationFunctionPanel, startXOfRegistrationFunctionPanel, startYOfRegistrationFunctionPanel);
        this.add(registrationFunctionPanel);
        this.add(upperPart);
        this.add(lowerPart);
        this.setVisible(true);
    }
    public String GetUserName(){
        return registrationFunctionPanel.userNameTextField.getText();
    }
    public String GetUserPassWord(){
        char[] passWordsInChars= registrationFunctionPanel.passWordField.getPassword();
        return new String(passWordsInChars);
    }
    public String GetUserAgainPassWord(){
        char[] againPassWordsInChars = registrationFunctionPanel.againPassWordField.getPassword();
        return new String(againPassWordsInChars);
    }
    public RegistrationFunctionPanel GetRegistrationFunctionPanel(){
        return registrationFunctionPanel;
    }
}
