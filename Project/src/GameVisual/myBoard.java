package GameVisual;

import GameElement.BoardUnit;
import GameElement.ControllingCenter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import java.awt.event.*;
import java.util.Random;
import java.awt.Font;


public class MyBoard extends JPanel implements KeyListener, Runnable {
    boolean isStart;

    int source=0;
    int best;
    Random rand1 = new Random();
    int rand_location;

    int fx; //描述上下左右
    InitBoard boardInformation1 = new InitBoard();
    ArrayList<BoardUnit> boardInformation = boardInformation1.getBoardInformation();
    ControllingCenter controllingCenter=boardInformation1.getControllingCenter();
    public MyBoard(){
        setFocusable(true);
        setBackground(new Color(241, 228, 219));
        this.addKeyListener(this);
        init();
    }
    private void init(){
        isStart= controllingCenter.getGameValidity();

        source = 0;
        //try {
        //    best = getS();        //读取文档内容（还没写）
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}
        //理应初始值就是0
        controllingCenter.RandomlyGenerateCellInEmptyBoardUnits();//随机选取位置填入2或4（我觉得可能得把2或4改成case1或2----不确定）
    }

    public void paint(Graphics g) {
        super.paint(g);
        initPaint(g);
        g.setColor(Color.white);
        g.setFont(new Font("宋体", Font.BOLD, 25));
        FontMetrics fm = getFontMetrics(new Font("宋体", Font.BOLD, 20));

        String value1 = controllingCenter.getCurrentGameScore() + "";
        g.drawString(value1,
                195 + (80 - fm.stringWidth(value1)) / 2,
                30 + (50 - fm.getAscent() - fm.getDescent()) / 2 + fm.getAscent());
        String value2 = best + "";
        g.drawString(value2,
                285 + (80 - fm.stringWidth(value2)) / 2,
                30 + (50 - fm.getAscent() - fm.getDescent()) / 2 + fm.getAscent());

        if (!isStart) {
            g.setColor(new Color(0, 0, 0, 0.5f));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setFont(new Font("宋体", Font.BOLD, 60));
            fm = getFontMetrics(new Font("宋体", Font.BOLD, 60));
            String value = "游戏结束";
            g.drawString(value,
                    (getWidth() - fm.stringWidth(value)) / 2,
                    (getHeight() - fm.getAscent() - fm.getDescent()) / 2 + fm.getAscent());
        }

    }

    //初始化画板
    private void initPaint(Graphics g) {
        g.setColor(new Color(192, 175, 159));
        g.fillRect(10, 120, 360 + 8, 360 + 8);
        //画得分区域和说明区域
        g.setColor(new Color(209, 132, 102));
        g.fillRect(200, 15, 80, 50);
        g.fillRect(290, 15, 80, 50);

        g.setColor(Color.black);
        g.setFont(new Font("宋体", Font.BOLD, 20));
        g.drawString("得分", 220, 35);
        g.drawString("最高得分", 300, 35);
        g.setFont(new Font("宋体", Font.BOLD, 35));
        g.drawString("游戏说明", 15, 55);
        g.setFont(new Font("宋体", Font.BOLD, 20));
        g.drawString("按上下左右键控制，按空格重新开始游戏", 15, 100);

        for (int i = 0; i < boardInformation.size(); i++) {
            int cell_value = 0;
            if(boardInformation.get(i).getCell()!=null){
                cell_value = boardInformation.get(i).getCell().getValue();
            }
            paintBlock(g,new Block(cell_value),boardInformation.get(i).getxCoordinate(),boardInformation.get(i).getyCoordinate());
        }

    }

    private void paintBlock(Graphics g, Block block, int x, int y) {
        block.setAllFont();
        g.setColor(block.bColor);
        g.fillRect(10+x*90+7,120+y*90+7,83,83);
        if (block.kind > 0) {
            g.setColor(block.wColor);
            g.setFont(block.wFont);
            FontMetrics fm = getFontMetrics(block.wFont);
            String value = block.s;
            g.drawString(value,
                    10 + x * 90 + 7 + (83 - fm.stringWidth(value)) / 2,
                    120 + y * 90 + 7 + (83 - fm.getAscent() - fm.getDescent()) / 2 + fm.getAscent());
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode =e.getKeyCode();
        if (isStart){
            Thread thread = new Thread(this);
            if (keyCode == KeyEvent.VK_DOWN){
                fx = 2;
                thread.start();
            }else if (keyCode == KeyEvent.VK_UP) {
                fx = 0;
                thread.start();
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                fx = 1;
                thread.start();
            } else if (keyCode == KeyEvent.VK_LEFT) {
                fx = 3;
                thread.start();
            }
            if (keyCode==KeyEvent.VK_SPACE){
                init();                                   //空格重启
            }
            repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {    //尚未完成

    }

    private void Down(){
        controllingCenter.DownAction();
        controllingCenter.UpdateGameValidity();
    }
    private void Up(){
        controllingCenter.UpAction();
        controllingCenter.UpdateGameValidity();
    }
    private void Left(){
        controllingCenter.LeftAction();
        controllingCenter.UpdateGameValidity();
    }
    private void Right(){
        controllingCenter.RightAction();
        controllingCenter.UpdateGameValidity();
    }
}