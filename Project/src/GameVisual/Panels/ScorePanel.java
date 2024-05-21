package GameVisual.Panels;

import GameElement.ControllingCenter;
import MultiUserSupply.User;

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
    JPanel currentStepNumberPanel;
    JLabel currentStepNumberLabel;
    JPanel personalBestPanel;
    JLabel personalBestLabel;
    User user;
    JPanel historicalBestPanel;
    JLabel historicalBestLabel;
    boolean whetherTimeLimited;
    boolean whetherCompetition;

    public ScorePanel(int startX, int startY, int totalWidth, ControllingCenter controllingCenter, boolean whetherTourist, User user, boolean whetherTimeLimited, boolean whetherCompetition) {
        super();
        this.whetherCompetition = whetherCompetition;
        this.whetherTimeLimited = whetherTimeLimited;
        this.user = user;
        this.startXForBlockSet = startX;
        this.startYForBlockSet = startY;
        this.totalWidth = totalWidth;
        this.controllingCenter = controllingCenter;
        this.whetherTourist = whetherTourist;
        this.setBounds(startXForBlockSet, 2 * startYForBlockSet / 3, totalWidth, startYForBlockSet - 2 * startYForBlockSet / 3);
        this.setLayout(null);
        this.SetUpDataAndPanel();
    }

    public ScorePanel(int startX, int startY, int totalWidth, ControllingCenter controllingCenter, boolean whetherTourist) {
        super();
        this.startXForBlockSet = startX;
        this.startYForBlockSet = startY;
        this.totalWidth = totalWidth;
        this.controllingCenter = controllingCenter;
        this.whetherTourist = whetherTourist;
        this.setBounds(startXForBlockSet, 2 * startYForBlockSet / 3, totalWidth, startYForBlockSet - 2 * startYForBlockSet / 3);
        this.setLayout(null);
        this.SetUpDataAndPanel();
    }

    public void SetUpDataAndPanel() {
        if (whetherTourist) {
            this.UpdateTheCurrentFontForCurrentScore();
            currentScorePanel = new JPanel(new BorderLayout());
            currentScorePanel.setBounds(totalWidth / 3, 0, totalWidth / 3, startYForBlockSet - 2 * startYForBlockSet / 3);
            currentScoreLabel = new JLabel(String.valueOf(controllingCenter.getCurrentGameScore()));
            currentScoreLabel.setFont(currentFont);
            currentScoreLabel.setHorizontalAlignment(JLabel.CENTER);
            currentScoreLabel.setVerticalAlignment(JLabel.CENTER);
            currentScoreLabel.setVisible(true);
            currentScorePanel.add(currentScoreLabel, BorderLayout.CENTER);
            currentScorePanel.setBackground(Color.LIGHT_GRAY);
            currentScorePanel.setVisible(true);
            this.add(currentScorePanel);
        } else if (whetherCompetition) {
            if (!whetherTimeLimited) {
                int controllingSize = Math.min(totalWidth / 3, startYForBlockSet - startYForBlockSet / 5);
                this.UpdateTheCurrentFontForCurrentScore();
                currentScorePanel = new JPanel(new BorderLayout());
                currentScorePanel.setBounds(totalWidth / 3, 0, totalWidth / 3, startYForBlockSet - 2 * startYForBlockSet / 3);
                currentScoreLabel = new JLabel(String.valueOf(controllingCenter.getCurrentGameScore()));
                currentScoreLabel.setFont(currentFont);
                currentScoreLabel.setHorizontalAlignment(JLabel.CENTER);
                currentScoreLabel.setVerticalAlignment(JLabel.CENTER);
                currentScoreLabel.setVisible(true);
                currentScorePanel.add(currentScoreLabel, BorderLayout.CENTER);
                currentScorePanel.setBackground(Color.LIGHT_GRAY);
                currentScorePanel.setVisible(true);
                currentStepNumberPanel = new JPanel(new BorderLayout());
                currentStepNumberPanel.setBounds(2 * totalWidth / 3, 0, totalWidth / 3, startYForBlockSet - 2 * startYForBlockSet / 3);
                currentStepNumberLabel = new JLabel(String.valueOf(controllingCenter.getNumberOfStep()));
                currentStepNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.18)));
                currentStepNumberLabel.setHorizontalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVerticalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVisible(true);
                currentStepNumberPanel.add(currentScoreLabel, BorderLayout.CENTER);
                currentStepNumberPanel.setBackground(Color.LIGHT_GRAY);
                currentStepNumberPanel.setVisible(true);
                historicalBestPanel = new JPanel(new BorderLayout());
                historicalBestPanel.setBounds(0, 0, totalWidth / 3, startYForBlockSet - 2 * startYForBlockSet / 3);
                historicalBestLabel = new JLabel(String.valueOf(user.getBestScoreInCompetitionWithoutTimeLimit()));
                historicalBestLabel.setFont(new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.18)));
                historicalBestLabel.setHorizontalAlignment(JLabel.CENTER);
                historicalBestLabel.setVerticalAlignment(JLabel.CENTER);
                historicalBestLabel.setVisible(true);
                historicalBestPanel.add(currentScoreLabel, BorderLayout.CENTER);
                historicalBestPanel.setBackground(Color.LIGHT_GRAY);
                historicalBestPanel.setVisible(true);
                this.add(historicalBestPanel);
                this.add(currentScorePanel);
                this.add(currentStepNumberPanel);
            } else if (whetherTimeLimited) {
                int controllingSize = Math.min(totalWidth / 3, startYForBlockSet - startYForBlockSet / 5);
                this.UpdateTheCurrentFontForCurrentScore();
                currentScorePanel = new JPanel(new BorderLayout());
                currentScorePanel.setBounds(totalWidth / 3, 0, totalWidth / 3, startYForBlockSet - 2 * startYForBlockSet / 3);
                currentScoreLabel = new JLabel(String.valueOf(controllingCenter.getCurrentGameScore()));
                currentScoreLabel.setFont(currentFont);
                currentScoreLabel.setHorizontalAlignment(JLabel.CENTER);
                currentScoreLabel.setVerticalAlignment(JLabel.CENTER);
                currentScoreLabel.setVisible(true);
                currentScorePanel.add(currentScoreLabel, BorderLayout.CENTER);
                currentScorePanel.setBackground(Color.LIGHT_GRAY);
                currentScorePanel.setVisible(true);
                currentStepNumberPanel = new JPanel(new BorderLayout());
                currentStepNumberPanel.setBounds(2 * totalWidth / 3, 0, totalWidth / 3, startYForBlockSet - 2 * startYForBlockSet / 3);
                currentStepNumberLabel = new JLabel(String.valueOf(controllingCenter.getNumberOfStep()));
                currentStepNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.18)));
                currentStepNumberLabel.setHorizontalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVerticalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVisible(true);
                currentStepNumberPanel.add(currentScoreLabel, BorderLayout.CENTER);
                currentStepNumberPanel.setBackground(Color.LIGHT_GRAY);
                currentStepNumberPanel.setVisible(true);
                historicalBestPanel = new JPanel(new BorderLayout());
                historicalBestPanel.setBounds(0, 0, totalWidth / 3, startYForBlockSet - 2 * startYForBlockSet / 3);
                historicalBestLabel = new JLabel(String.valueOf(user.getBestScoreInCompetitionWithTimeLimit()));
                historicalBestLabel.setFont(new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.18)));
                historicalBestLabel.setHorizontalAlignment(JLabel.CENTER);
                historicalBestLabel.setVerticalAlignment(JLabel.CENTER);
                historicalBestLabel.setVisible(true);
                historicalBestPanel.add(currentScoreLabel, BorderLayout.CENTER);
                historicalBestPanel.setBackground(Color.LIGHT_GRAY);
                historicalBestPanel.setVisible(true);
                this.add(currentStepNumberPanel);
                this.add(currentScorePanel);
                this.add(historicalBestPanel);
            }
        } else if (!whetherCompetition) {
            if (!whetherTimeLimited) {
                int controllingSize = Math.min(totalWidth / 3, startYForBlockSet - startYForBlockSet / 5);
                this.UpdateTheCurrentFontForCurrentScore();
                currentScorePanel = new JPanel(new BorderLayout());
                currentScorePanel.setBounds(totalWidth / 3, 0, totalWidth / 3, startYForBlockSet - 2 * startYForBlockSet / 3);
                currentScoreLabel = new JLabel(String.valueOf(controllingCenter.getCurrentGameScore()));
                currentScoreLabel.setFont(currentFont);
                currentScoreLabel.setHorizontalAlignment(JLabel.CENTER);
                currentScoreLabel.setVerticalAlignment(JLabel.CENTER);
                currentScoreLabel.setVisible(true);
                currentScorePanel.add(currentScoreLabel, BorderLayout.CENTER);
                currentScorePanel.setBackground(Color.LIGHT_GRAY);
                currentScorePanel.setVisible(true);
                currentStepNumberPanel = new JPanel(new BorderLayout());
                currentStepNumberPanel.setBounds(2 * totalWidth / 3, 0, totalWidth / 3, startYForBlockSet - 2 * startYForBlockSet / 3);
                currentStepNumberLabel = new JLabel(String.valueOf(controllingCenter.getNumberOfStep()));
                currentStepNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.18)));
                currentStepNumberLabel.setHorizontalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVerticalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVisible(true);
                currentStepNumberPanel.add(currentScoreLabel, BorderLayout.CENTER);
                currentStepNumberPanel.setBackground(Color.LIGHT_GRAY);
                currentStepNumberPanel.setVisible(true);
                this.add(currentScorePanel);
                this.add(currentStepNumberPanel);
            } else if (whetherTimeLimited) {
                int controllingSize = Math.min(totalWidth / 3, startYForBlockSet - startYForBlockSet / 5);
                this.UpdateTheCurrentFontForCurrentScore();
                currentScorePanel = new JPanel(new BorderLayout());
                currentScorePanel.setBounds(totalWidth / 3, 0, totalWidth / 3, startYForBlockSet - 2 * startYForBlockSet / 3);
                currentScoreLabel = new JLabel(String.valueOf(controllingCenter.getCurrentGameScore()));
                currentScoreLabel.setFont(currentFont);
                currentScoreLabel.setHorizontalAlignment(JLabel.CENTER);
                currentScoreLabel.setVerticalAlignment(JLabel.CENTER);
                currentScoreLabel.setVisible(true);
                currentScorePanel.add(currentScoreLabel, BorderLayout.CENTER);
                currentScorePanel.setBackground(Color.LIGHT_GRAY);
                currentScorePanel.setVisible(true);
                currentStepNumberPanel = new JPanel(new BorderLayout());
                currentStepNumberPanel.setBounds(2 * totalWidth / 3, 0, totalWidth / 3, startYForBlockSet - 2 * startYForBlockSet / 3);
                currentStepNumberLabel = new JLabel(String.valueOf(controllingCenter.getNumberOfStep()));
                currentStepNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.18)));
                currentStepNumberLabel.setHorizontalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVerticalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVisible(true);
                currentStepNumberPanel.add(currentScoreLabel, BorderLayout.CENTER);
                currentStepNumberPanel.setBackground(Color.LIGHT_GRAY);
                currentStepNumberPanel.setVisible(true);
                this.add(currentScorePanel);
                this.add(currentStepNumberPanel);
            }
        }
    }

    void UpdateTheCurrentFontForCurrentScore() {
        int currentScore = controllingCenter.getCurrentGameScore();
        int controllingSize = Math.min(totalWidth / 3, startYForBlockSet - startYForBlockSet / 5);
        if (currentScore < 10) {
            currentFont = new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.3));
        } else if (currentScore < 100) {
            currentFont = new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.25));
        } else if (currentScore < 1000) {
            currentFont = new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.20));
        } else if (currentScore < 10000) {
            currentFont = new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.18));
        } else if (currentScore < 100000) {
            currentFont = new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.13));
        } else {
            currentFont = new Font("Times New Roman", Font.BOLD, (int) ((double) controllingSize * 0.1));
        }
    }

    public void UpdateTheMonitorPanel() {
        if (whetherTourist) {
            this.UpdateTheCurrentFontForCurrentScore();
            this.remove(currentScorePanel);
            currentScoreLabel.setText(String.valueOf(controllingCenter.getCurrentGameScore()));
            currentScoreLabel.setFont(currentFont);
            currentScoreLabel.setHorizontalAlignment(JLabel.CENTER);
            currentScoreLabel.setVerticalAlignment(JLabel.CENTER);
            currentScoreLabel.setVisible(true);
            currentScorePanel.add(currentScoreLabel, BorderLayout.CENTER);
            currentScorePanel.setVisible(true);
            this.add(currentScorePanel);
        } else if (whetherCompetition) {
            if (!whetherTimeLimited) {
                this.UpdateTheCurrentFontForCurrentScore();
                this.remove(currentScorePanel);
                this.remove(currentStepNumberPanel);
                this.remove(historicalBestPanel);
                currentScoreLabel.setText(String.valueOf(controllingCenter.getCurrentGameScore()));
                currentScoreLabel.setFont(currentFont);
                currentScoreLabel.setHorizontalAlignment(JLabel.CENTER);
                currentScoreLabel.setVerticalAlignment(JLabel.CENTER);
                currentScoreLabel.setVisible(true);
                currentScorePanel.add(currentScoreLabel, BorderLayout.CENTER);
                currentScorePanel.setVisible(true);
                currentStepNumberLabel.setText(String.valueOf(controllingCenter.getNumberOfStep()));
                currentStepNumberLabel.setHorizontalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVerticalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVisible(true);
                currentStepNumberPanel.add(currentStepNumberLabel, BorderLayout.CENTER);
                currentScorePanel.setVisible(true);
                historicalBestLabel.setText(String.valueOf(user.getBestScoreInCompetitionWithoutTimeLimit()));
                historicalBestLabel.setHorizontalAlignment(JLabel.CENTER);
                historicalBestLabel.setVerticalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVisible(true);
                historicalBestPanel.add(historicalBestLabel, BorderLayout.CENTER);
                historicalBestPanel.setVisible(true);
                this.add(historicalBestPanel);
                this.add(currentScorePanel);
                this.add(currentStepNumberPanel);
            } else if (whetherTimeLimited) {
                this.UpdateTheCurrentFontForCurrentScore();
                this.remove(currentScorePanel);
                this.remove(currentStepNumberPanel);
                currentScoreLabel.setText(String.valueOf(controllingCenter.getCurrentGameScore()));
                currentScoreLabel.setFont(currentFont);
                currentScoreLabel.setHorizontalAlignment(JLabel.CENTER);
                currentScoreLabel.setVerticalAlignment(JLabel.CENTER);
                currentScoreLabel.setVisible(true);
                currentScorePanel.add(currentScoreLabel, BorderLayout.CENTER);
                currentScorePanel.setVisible(true);
                currentStepNumberLabel.setText(String.valueOf(controllingCenter.getNumberOfStep()));
                currentStepNumberLabel.setHorizontalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVerticalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVisible(true);
                currentStepNumberPanel.add(currentStepNumberLabel, BorderLayout.CENTER);
                currentScorePanel.setVisible(true);
                historicalBestLabel.setText(String.valueOf(user.getBestScoreInCompetitionWithTimeLimit()));
                historicalBestLabel.setHorizontalAlignment(JLabel.CENTER);
                historicalBestLabel.setVerticalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVisible(true);
                historicalBestPanel.add(historicalBestLabel, BorderLayout.CENTER);
                historicalBestPanel.setVisible(true);
                this.add(historicalBestPanel);
                this.add(currentScorePanel);
                this.add(currentStepNumberPanel);
            }
        }else if (!whetherCompetition){
            if (!whetherTimeLimited) {
                this.UpdateTheCurrentFontForCurrentScore();
                this.remove(currentScorePanel);
                this.remove(currentStepNumberPanel);
                currentScoreLabel.setText(String.valueOf(controllingCenter.getCurrentGameScore()));
                currentScoreLabel.setFont(currentFont);
                currentScoreLabel.setHorizontalAlignment(JLabel.CENTER);
                currentScoreLabel.setVerticalAlignment(JLabel.CENTER);
                currentScoreLabel.setVisible(true);
                currentScorePanel.add(currentScoreLabel, BorderLayout.CENTER);
                currentScorePanel.setVisible(true);
                currentStepNumberLabel.setText(String.valueOf(controllingCenter.getNumberOfStep()));
                currentStepNumberLabel.setHorizontalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVerticalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVisible(true);
                currentStepNumberPanel.add(currentStepNumberLabel, BorderLayout.CENTER);
                currentScorePanel.setVisible(true);
                this.add(currentScorePanel);
                this.add(currentStepNumberPanel);
            } else if (whetherTimeLimited){
                this.UpdateTheCurrentFontForCurrentScore();
                this.remove(currentScorePanel);
                this.remove(currentStepNumberPanel);
                currentScoreLabel.setText(String.valueOf(controllingCenter.getCurrentGameScore()));
                currentScoreLabel.setFont(currentFont);
                currentScoreLabel.setHorizontalAlignment(JLabel.CENTER);
                currentScoreLabel.setVerticalAlignment(JLabel.CENTER);
                currentScoreLabel.setVisible(true);
                currentScorePanel.add(currentScoreLabel, BorderLayout.CENTER);
                currentScorePanel.setVisible(true);
                currentStepNumberLabel.setText(String.valueOf(controllingCenter.getNumberOfStep()));
                currentStepNumberLabel.setHorizontalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVerticalAlignment(JLabel.CENTER);
                currentStepNumberLabel.setVisible(true);
                currentStepNumberPanel.add(currentStepNumberLabel, BorderLayout.CENTER);
                currentScorePanel.setVisible(true);
                this.add(currentScorePanel);
                this.add(currentStepNumberPanel);
            }
        }
    }
}
