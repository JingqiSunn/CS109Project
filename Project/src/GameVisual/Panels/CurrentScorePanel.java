package GameVisual.Panels;

import GameElement.ControllingCenter;

import javax.swing.*;
import java.awt.*;

public class CurrentScorePanel extends JPanel {
    JLabel currentScore;
    ControllingCenter controllingCenter;
    public CurrentScorePanel(ControllingCenter controllingCenter){
        super();
        this.controllingCenter=controllingCenter;
        this.setLayout(new BorderLayout());

    }
    public void CreateTheCurrentLabelJLabel(){
        currentScore = new JLabel("0");
        currentScore.setHorizontalAlignment(JLabel.CENTER);
        currentScore.setVerticalAlignment(JLabel.CENTER);
        currentScore.setForeground(null);
    }
}
