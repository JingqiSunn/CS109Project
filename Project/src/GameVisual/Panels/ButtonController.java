package GameVisual.Panels;

import javax.swing.*;
import java.awt.*;

public class ButtonController extends JPanel {
    int totalWidth;
    int totalHeight;
    JPanel up;
    int size;
    JPanel down;
    JPanel left;
    JPanel right;
    JLabel upLabel;
    JLabel downLabel;
    JLabel leftLabel;
    JLabel rightLabel;
    public ButtonController(int totalWidth, int totalHeight){
        this.totalWidth = totalWidth;
        this.totalHeight = totalHeight;
        size = totalWidth/3;
        this.setLayout(null);
        this.SetUpTheInnerPanels();
    }
    private void SetUpUpButton(){
        up = new JPanel();
        up.setLayout(new BorderLayout());
        up.setBounds(size,0,size,size);
        up.setBackground(new Color(0xFEFEA4));
        upLabel = new JLabel("UP");
        upLabel.setForeground(new Color(0x5C5757));
        upLabel.setHorizontalAlignment(JLabel.CENTER);
        upLabel.setVerticalAlignment(JLabel.CENTER);
        up.add(upLabel,BorderLayout.CENTER);
        this.add(up);
    }
    private void SetUpDownButton(){
        down = new JPanel();
        down.setLayout(new BorderLayout());
        down.setBounds(size,2*size,size,size);
        down.setBackground(new Color(0xFEFEA4));
        downLabel = new JLabel("DOWN");
        downLabel.setForeground(new Color(0x5C5757));
        downLabel.setHorizontalAlignment(JLabel.CENTER);
        downLabel.setVerticalAlignment(JLabel.CENTER);
        down.add(downLabel,BorderLayout.CENTER);
        this.add(down);
    }
    private void SetUpLeftButton(){
        left = new JPanel();
        left.setLayout(new BorderLayout());
        left.setBounds(0,size,size,size);
        left.setBackground(new Color(0xFEFEA4));
        leftLabel = new JLabel("LEFT");
        leftLabel.setForeground(new Color(0x5C5757));
        leftLabel.setHorizontalAlignment(JLabel.CENTER);
        leftLabel.setVerticalAlignment(JLabel.CENTER);
        left.add(leftLabel,BorderLayout.CENTER);
        this.add(left);
    }
    private void SetUpRightButton(){
        right = new JPanel();
        right.setLayout(new BorderLayout());
        right.setBounds(2*size,size,size,size);
        right.setBackground(new Color(0xFEFEA4));
        rightLabel = new JLabel("RIGHT");
        rightLabel.setForeground(new Color(0x5C5757));
        rightLabel.setHorizontalAlignment(JLabel.CENTER);
        rightLabel.setVerticalAlignment(JLabel.CENTER);
        right.add(rightLabel,BorderLayout.CENTER);
        this.add(right);
    }
    private void SetUpTheInnerPanels(){
        this.SetUpUpButton();
        this.SetUpDownButton();
        this.SetUpLeftButton();
        this.SetUpRightButton();
    }
}
