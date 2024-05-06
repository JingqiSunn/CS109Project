package GameVisual.Panels;

import GameElement.ControllingCenter;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    int startXForBlockSet;
    int startYForBlockSet;
    int totalWidth;
    JPanel currentScorePanel;
    JPanel recordWithAIPanel;
    JPanel recordWithoutAIPanel;
    JLabel currentScoreLabel;
    JLabel recordWithAILabel;
    JLabel recordWithoutAILabel;
    ControllingCenter controllingCenter;
    Font currentFont;
    boolean whetherTourist;
    int recordWithAI;
    int recordWithoutAI;
    boolean whetherNewRecordWithAI;
    boolean whetherNewRecordWithoutAI;
    public ScorePanel(int startX,int startY, int totalWidth ,ControllingCenter controllingCenter, boolean whetherTourist){
        super();
        this.startXForBlockSet = startX;
        this.startYForBlockSet = startY;
        this.totalWidth = totalWidth;
        this.controllingCenter=controllingCenter;
        this.whetherTourist = whetherTourist;
        this.setBounds(startXForBlockSet,startYForBlockSet/5,totalWidth,startYForBlockSet-startYForBlockSet/5);
        this.setLayout(null);
        this.SetUpDataAndPanel();
    }
    public void SetUpDataAndPanel(){

        if(whetherTourist){
            this.UpdateTheCurrentFontForCurrentScore();
            currentScorePanel = new JPanel(new BorderLayout());
            currentScorePanel.setBounds(totalWidth/3,0,totalWidth/3,startYForBlockSet-startYForBlockSet/5);
            currentScoreLabel = new JLabel(String.valueOf(controllingCenter.getCurrentGameScore()));
            currentScoreLabel.setFont(currentFont);
            currentScoreLabel.setHorizontalAlignment(JLabel.CENTER);
            currentScoreLabel.setVerticalAlignment(JLabel.CENTER);
            currentScoreLabel.setVisible(true);
            currentScorePanel.add(currentScoreLabel,BorderLayout.CENTER);
            currentScorePanel.setBackground(Color.LIGHT_GRAY);
            currentScorePanel.setVisible(true);
            this.add(currentScorePanel);
        }
    }
    void UpdateTheCurrentFontForCurrentScore(){
        int currentScore = controllingCenter.getCurrentGameScore();
        int controllingSize = Math.min(totalWidth/3,startYForBlockSet-startYForBlockSet/5);
        if(currentScore<10){
            currentFont = new Font("Times New Roman", Font.BOLD, (int)((double)controllingSize*0.3));
        } else if(currentScore<100){
            currentFont = new Font("Times New Roman", Font.BOLD, (int)((double)controllingSize*0.25) );
        } else if(currentScore<1000){
            currentFont = new Font("Times New Roman", Font.BOLD, (int)((double)controllingSize*0.20) );
        } else if(currentScore<10000){
            currentFont = new Font("Times New Roman", Font.BOLD, (int)((double)controllingSize*0.18) );
        } else if(currentScore<100000){
            currentFont = new Font("Times New Roman", Font.BOLD, (int)((double)controllingSize*0.13));
        } else {
            currentFont = new Font("Times New Roman", Font.BOLD, (int)((double)controllingSize*0.1));
        }
    }
    public void UpdateTheScorePanel(){
        if(whetherTourist){
            this.UpdateTheCurrentFontForCurrentScore();
            this.remove(currentScorePanel);
            currentScoreLabel.setText(String.valueOf(controllingCenter.getCurrentGameScore()));
            currentScoreLabel.setFont(currentFont);
            currentScoreLabel.setHorizontalAlignment(JLabel.CENTER);
            currentScoreLabel.setVerticalAlignment(JLabel.CENTER);
            currentScoreLabel.setVisible(true);
            currentScorePanel.add(currentScoreLabel,BorderLayout.CENTER);
            currentScorePanel.setVisible(true);
            this.add(currentScorePanel);
        }
    }
}
