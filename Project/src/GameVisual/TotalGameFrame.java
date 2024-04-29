package GameVisual;

import javax.swing.*;
import java.awt.*;

public class TotalGameFrame extends JFrame {

    int totalWides;
    int totalHeight;

    Dimension screenSize;
    LoginPage loginPage;

    public TotalGameFrame(){
        this.setLayout(null);
        this.UpdateTheSizeOfTheScreen();
        this.SetFullScreen();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.LoadLoginPage();
    }

    //OK
    void SetFullScreen(){
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if(graphicsDevice.isFullScreenSupported()){
            this.setUndecorated(true);
            graphicsDevice.setFullScreenWindow(this);
        } else {
            System.out.println("Full screen is not supported in your device!");
        }
    }

    //OK
    void UpdateTheSizeOfTheScreen(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        totalWides = (int) screenSize.getWidth();
        totalHeight = (int) screenSize.getHeight();
    }

    void LoadLoginPage(){
        loginPage = new LoginPage(screenSize);
        loginPage.setVisible(true);
        this.add(loginPage);
        loginPage.requestFocus();
    }
}
