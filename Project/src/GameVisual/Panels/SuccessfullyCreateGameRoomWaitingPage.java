package GameVisual.Panels;

import MultiUserSupply.User;

import javax.swing.*;
import java.awt.*;

public class SuccessfullyCreateGameRoomWaitingPage extends JPanel{
    Dimension screenSize;
    int totalWides;
    int totalHeight;
    int unitHeight;
    int unitWidth;
    int roomNumber;
    boolean whetherServer;
    String serverUser;
    String clientUser;
    JPanel serverPanel;
    JLabel serverLabel;
    JPanel clientPanel;
    JLabel clientLabel;
    JPanel continuePanel;
    JLabel continueLabel;
    JPanel roomNumberPanel;
    JLabel roomNumberLabel;
    public SuccessfullyCreateGameRoomWaitingPage(int roomNumber, Dimension screenSize, String serverUser){
        this.setBounds(0,0, screenSize.width,screenSize.height);
        this.setLayout(null);
        this.roomNumber = roomNumber;
        this.whetherServer = true;
        this.serverUser = serverUser;
        this.UpdateSizeInformation(screenSize);
        this.EstablishTheInsidePanels();
        this.UpdatePanelForServer();
        this.setVisible(true);
    }
    public SuccessfullyCreateGameRoomWaitingPage(int roomNumber, Dimension screenSize, String serverUser, String clientUser){
        this.setBounds(0,0, screenSize.width,screenSize.height);
        this.setLayout(null);
        this.roomNumber = roomNumber;
        this.whetherServer = true;
        this.serverUser = serverUser;
        this.UpdateSizeInformation(screenSize);
        this.EstablishTheInsidePanels();
        this.UpdatePanelForServer();
        this.UpdatePanelForClient();
        this.setVisible(true);
    }

    public JPanel getContinuePanel() {
        return continuePanel;
    }

    private void UpdateSizeInformation(Dimension screenSize){
        this.screenSize = screenSize;
        this.totalHeight = (int) screenSize.getHeight();
        this.totalWides = (int) screenSize.getWidth();
        this.unitHeight = (int)((double)totalHeight/(double) 12);
        this.unitWidth = (int)((double)totalWides/(double) 11);
    }
    void EstablishTheInsidePanels(){
        serverPanel = new JPanel();
        serverPanel.setBounds(unitWidth,3*unitHeight,unitWidth*4,unitHeight*6);
        serverPanel.setLayout(new BorderLayout());
        serverPanel.setBackground(new Color(0xE2DFD0));
        serverLabel = new JLabel();
        serverLabel.setForeground(Color.BLACK);
        serverLabel.setFont(new Font("Times New Roman", Font.BOLD, 60));
        serverLabel.setHorizontalAlignment(JLabel.CENTER);
        serverLabel.setVerticalAlignment(JLabel.CENTER);
        serverPanel.add(serverLabel,BorderLayout.CENTER);
        clientPanel = new JPanel();
        clientPanel.setBounds(6*unitWidth,3*unitHeight,unitWidth*4,unitHeight*6);
        clientPanel.setLayout(new BorderLayout());
        clientPanel.setBackground(new Color(0xE2DFD0));
        clientLabel = new JLabel();
        clientLabel.setForeground(Color.BLACK);
        clientLabel.setFont(new Font("Times New Roman", Font.BOLD, 60));
        clientLabel.setHorizontalAlignment(JLabel.CENTER);
        clientLabel.setVerticalAlignment(JLabel.CENTER);
        clientPanel.add(clientLabel,BorderLayout.CENTER);
        continuePanel = new JPanel();
        continuePanel.setBounds(5*unitWidth,10*unitHeight,unitWidth,unitHeight);
        continuePanel.setLayout(new BorderLayout());
        continuePanel.setBackground(Color.LIGHT_GRAY);
        continueLabel = new JLabel("Continue");
        continueLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        continueLabel.setForeground(Color.WHITE);
        continueLabel.setHorizontalAlignment(JLabel.CENTER);
        continueLabel.setVerticalAlignment(JLabel.CENTER);
        continuePanel.add(continueLabel,BorderLayout.CENTER);
        roomNumberPanel = new JPanel();
        roomNumberPanel.setBounds(4*unitWidth,unitHeight,3*unitWidth,unitHeight);
        roomNumberPanel.setLayout(new BorderLayout());
        roomNumberLabel = new JLabel(String.valueOf(roomNumber));
        roomNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        roomNumberLabel.setForeground(Color.BLACK);
        roomNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        roomNumberLabel.setVerticalAlignment(JLabel.CENTER);
        roomNumberPanel.add(roomNumberLabel,BorderLayout.CENTER);
        this.add(serverPanel);
        this.add(clientPanel);
        this.add(continuePanel);
        this.add(roomNumberPanel);
    }
    void UpdatePanelForServer(){
        this.setVisible(false);
        serverLabel.setText(serverUser);
        serverPanel.setBackground(new Color(0xB0EBB4));
        this.repaint();
        this.setVisible(true);
    }
    void UpdatePanelForClient(){
        this.setVisible(false);
        clientLabel.setText(serverUser);
        clientPanel.setBackground(new Color(0xB0EBB4));
        this.repaint();
        this.setVisible(true);
    }
}
