package GameVisual;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UpBoundWhenNotFullScreen extends JPanel implements MouseListener {
    int xIndex;
    int yIndex;
    int width;
    int height;
    JPanel closeButton;
    UpBoundWhenNotFullScreen(int xIndex,int yIndex){
        System.out.println("Yes");
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.width = 1100;
        this.height = 15;
        setBounds(xIndex,yIndex,width,height);
        this.DesignedTheCloseButton();
        closeButton.setBackground(Color.BLACK);
        this.add(closeButton);
        closeButton.addMouseListener(this);
        setVisible(true);
    }

    void DesignedTheCloseButton(){
        closeButton = new JPanel();
        closeButton.setBounds(xIndex+width-height,0,height,height);
        closeButton.setVisible(true);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(xIndex+height*3/8,height*3/8,xIndex+height*5/8,height*5/8);
        g2d.drawLine(xIndex+height*3/8,height*5/8,xIndex+height*5/8,height*3/8);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Component activatedComponent = e.getComponent();
        if(activatedComponent.equals(this)){
            setBackground(Color.decode("#FFDDDD"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Component activatedComponent = e.getComponent();
        if(activatedComponent.equals(this)){
            setBackground(Color.decode("#F5F5F5"));
        }
    }
}
