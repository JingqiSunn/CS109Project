package GameVisual.Panels;

import MultiUserSupply.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AskingForArchivePanel extends JPanel {

    Dimension totalSize;
    User user;
    int totalWidth;
    int totalHeight;
    JPanel warnPanel;
    JLabel warnLabel;
    JPanel continueToPlay;
    JLabel continueToPlayLabel;
    JPanel userPanel;
    JLabel userLabel;
    JPanel askForArchiveName;
    JLabel askForArchiveNameLabel;
    JTextField askForArchiveNameField;

    public AskingForArchivePanel(Dimension screenSize, User user){
        this.user = user;
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInTheLoginPage();
        this.SetUpUserPanel();
    }
    public String GetArchiveName(){
            return askForArchiveNameField.getText();
    }

    public JPanel getContinueToPlay() {
        return continueToPlay;
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize){
        totalSize = screenSize;
        totalHeight = (int) screenSize.getHeight();
        totalWidth = (int) screenSize.getWidth();
    }
    void SetUpOptionsInTheLoginPage(){
        continueToPlay = new JPanel();
        continueToPlay.setBounds((int) (((double)totalWidth*(double)7 )/(double) 17),(int) (((double)totalHeight*(double)8 )/(double) 12),(int) (((double)totalWidth*(double)3 )/(double) 17),(int) (((double)totalHeight )/(double) 12));
        continueToPlay.setBackground(Color.LIGHT_GRAY);
        continueToPlay.setLayout(new BorderLayout());
        continueToPlayLabel = new JLabel("Continue");
        continueToPlayLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        continueToPlayLabel.setForeground(Color.WHITE);
        continueToPlayLabel.setHorizontalAlignment(JLabel.CENTER);
        continueToPlayLabel.setVerticalAlignment(JLabel.CENTER);
        continueToPlay.add(continueToPlayLabel);
        this.add(continueToPlay);
        this.SetUpForAskForArchiveName();
    }
    public void SetUpForAskForArchiveName() {
        askForArchiveName = new JPanel();
        askForArchiveName.setLayout(new BorderLayout());
        askForArchiveName.setBackground(Color.LIGHT_GRAY);
        askForArchiveName.setBounds((int) (((double)totalWidth*(double)2 )/(double) 9),(int) (((double)totalHeight*(double)5 )/(double) 12),(int) (((double)totalWidth*(double)2 )/(double) 9),(int) (((double)totalHeight)/(double) 12));
        askForArchiveNameLabel = new JLabel("Archive Name: ");
        askForArchiveNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        askForArchiveNameLabel.setForeground(Color.BLACK);
        askForArchiveNameLabel.setHorizontalAlignment(JLabel.CENTER);
        askForArchiveNameLabel.setVerticalAlignment(JLabel.CENTER);
        askForArchiveName.add(askForArchiveNameLabel);
        askForArchiveNameField = new JTextField();
        askForArchiveNameField.setFont(new Font("Times New Roman", Font.BOLD, 40));
        askForArchiveNameField.setBounds((int) (((double)totalWidth*(double)4 )/(double) 9),(int) (((double)totalHeight*(double)5 )/(double) 12),(int) (((double)totalWidth*(double)3 )/(double) 9),(int) (((double)totalHeight)/(double) 12));
        this.add(askForArchiveName);
        this.add(askForArchiveNameField);
    }

    public void EstablishWarn(String wordsOutput) {
        this.setVisible(false);
        if (warnPanel != null) {
            this.remove(warnPanel);
            warnPanel = null;
            warnLabel = null;
        }
        warnPanel = new JPanel();
        warnPanel.setBounds((int) (((double)totalWidth*(double)2 )/(double) 9),(int) (((double)totalHeight*(double)6 )/(double) 12),(int) (((double)totalWidth*(double)5 )/(double) 9),(int) (((double)totalHeight )/(double) 48));
        warnPanel.setBackground(new Color(0xE3E1D9));
        warnPanel.setLayout(new BorderLayout());
        warnLabel = new JLabel(wordsOutput);
        warnLabel.setForeground(Color.RED);
        warnLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
        warnLabel.setHorizontalAlignment(JLabel.CENTER);
        warnLabel.setVerticalAlignment(JLabel.CENTER);
        warnLabel.setVisible(true);
        warnPanel.add(warnLabel, BorderLayout.CENTER);
        warnPanel.setVisible(true);
        this.add(warnPanel);
        this.repaint();
        this.setVisible(true);
    }
    public void CleanExistingWarning() {
        this.setVisible(false);
        if (warnPanel != null) {
            this.remove(warnPanel);
            warnPanel = null;
            warnLabel = null;
        }
        this.repaint();
        this.setVisible(true);
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