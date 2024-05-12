package GameVisual.Panels;

import javax.swing.*;
import java.awt.*;

public class SuccessfullyRegisteredPage extends JPanel {
    Dimension totalSize;

    int totalWidth;
    int totalHeight;
    int startXOfRegistrationSuccessfulFunctionPanel;
    int startYOfRegistrationSuccessfulFunctionPanel;
    int widthOfRegistrationSuccessfulFunctionPanel;
    int heightYOfRegistrationSuccessfulFunctionPanel;
    Dimension sizeOfRegistrationSuccessfulFunctionPanel;
    SuccessfullyRegisteredFunctionPanel registrationSuccessfulFunctionPanel;
    JPanel upperPart;
    JPanel lowerPart;


    public SuccessfullyRegisteredPage(Dimension screenSize){
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.SetUpThePanel();
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        startXOfRegistrationSuccessfulFunctionPanel = totalWidth/3;
        widthOfRegistrationSuccessfulFunctionPanel = totalWidth/3;
        startYOfRegistrationSuccessfulFunctionPanel = totalHeight/7;
        heightYOfRegistrationSuccessfulFunctionPanel = totalHeight*5/7;
        sizeOfRegistrationSuccessfulFunctionPanel = new Dimension(widthOfRegistrationSuccessfulFunctionPanel, heightYOfRegistrationSuccessfulFunctionPanel);
    }
    void SetUpThePanel() {
        this.setBounds(0,0,totalWidth,totalHeight);
        upperPart = new JPanel();
        upperPart.setBounds(0,0,totalWidth,totalHeight*2/3);
        upperPart.setBackground(new Color(0xB4B4B8));
        lowerPart = new JPanel();
        lowerPart.setBounds(0,totalHeight*2/3,totalWidth,totalHeight/3);
        lowerPart.setBackground(new Color(0xC7C8CC));
        registrationSuccessfulFunctionPanel = new SuccessfullyRegisteredFunctionPanel(sizeOfRegistrationSuccessfulFunctionPanel, startXOfRegistrationSuccessfulFunctionPanel, startYOfRegistrationSuccessfulFunctionPanel);
        this.add(registrationSuccessfulFunctionPanel);
        this.add(upperPart);
        this.add(lowerPart);
        this.setVisible(true);
    }
    public JPanel GetBackToMenu(){
        return registrationSuccessfulFunctionPanel.backToMainMenu;
    }
    public JPanel GetToLogin(){
        return registrationSuccessfulFunctionPanel.comeToLogin;
    }
}
