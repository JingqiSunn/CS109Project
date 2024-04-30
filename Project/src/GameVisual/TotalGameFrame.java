package GameVisual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TotalGameFrame extends JFrame implements KeyListener{

    int totalWides;
    int totalHeight;

    Dimension screenSize;
    LoginPage loginPage;
    Boolean whetherFullScreenNow;
    UpBoundWhenNotFullScreen upBoundWhenNotFullScreen;

    public TotalGameFrame(){
        this.setLayout(null);
        this.UpdateTheSizeOfTheScreen();
        this.SetFullScreen();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.LoadLoginPage();
        this.setFocusable(true);
        this.setVisible(true);
    }
    void SetFullScreen(){
        GraphicsDevice currentDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if(currentDevice.isFullScreenSupported()){
            this.setUndecorated(true);
            currentDevice.setFullScreenWindow(this);
            whetherFullScreenNow = true;
        } else {
            System.out.println("Full screen is not supported in your device!");
        }
    }

    void outOfFullScreen(){
        GraphicsDevice currentDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (whetherFullScreenNow && currentDevice.isFullScreenSupported()){
            currentDevice.setFullScreenWindow(null);
            whetherFullScreenNow = false;
            totalWides = 1100;
            totalHeight = 800;
            screenSize.setSize(totalWides,totalHeight);
            int xIndex = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getWidth()-totalWides)/2);
            int yIndex = (int) ((Toolkit.getDefaultToolkit().getScreenSize().getHeight()-totalHeight)/2);
            this.setBounds(xIndex,yIndex,totalWides,totalHeight);
            upBoundWhenNotFullScreen = new UpBoundWhenNotFullScreen(xIndex,yIndex);
            this.add(upBoundWhenNotFullScreen);
            setFocusable(true);
        }
    }

    void UpdateTheSizeOfTheScreen(){
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        totalWides = (int) screenSize.getWidth();
        totalHeight = (int) screenSize.getHeight();
    }

    void LoadLoginPage(){
        loginPage = new LoginPage(screenSize);
        loginPage.setVisible(true);
        this.add(loginPage);
        loginPage.setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyBeingActivated = e.getKeyCode();
        if(keyBeingActivated == KeyEvent.VK_ESCAPE){
            this.outOfFullScreen();
            setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
