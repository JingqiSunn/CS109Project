package GameVisual.Panels;

import javax.swing.*;
import java.awt.*;

public class UserLoginPage extends JPanel {
    Dimension totalSize;

    int totalWidth;
    int totalHeight;
    int startXOfLoginFunctionPanel;
    int startYOfLoginFunctionPanel;
    int widthOfLoginFunctionPanel;
    int heightYOfLoginFunctionPanel;
    Dimension sizeOfLoginFunctionPanel;
    LoginFunctionPanel loginFunctionPanel;
    JPanel upperPart;
    JPanel lowerPart;


    public UserLoginPage(Dimension screenSize){
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.SetUpThePanel();
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize) {
        totalSize = screenSize;
        totalHeight = totalSize.height;
        totalWidth = totalSize.width;
        startXOfLoginFunctionPanel = totalWidth/3;
        widthOfLoginFunctionPanel = totalWidth/3;
        startYOfLoginFunctionPanel = totalHeight/7;
        heightYOfLoginFunctionPanel = totalHeight*5/7;
        sizeOfLoginFunctionPanel = new Dimension(widthOfLoginFunctionPanel,heightYOfLoginFunctionPanel);
    }
    void SetUpThePanel() {
        this.setBounds(0,0,totalWidth,totalHeight);
        upperPart = new JPanel();
        upperPart.setBounds(0,0,totalWidth,totalHeight*2/3);
        upperPart.setBackground(new Color(0x7469B6));
        lowerPart = new JPanel();
        lowerPart.setBounds(0,totalHeight*2/3,totalWidth,totalHeight/3);
        lowerPart.setBackground(new Color(0xAD88C6));
        loginFunctionPanel = new LoginFunctionPanel(sizeOfLoginFunctionPanel, startXOfLoginFunctionPanel, startYOfLoginFunctionPanel);
        this.add(loginFunctionPanel);
        this.add(upperPart);
        this.add(lowerPart);
        this.setVisible(true);
    }

}
