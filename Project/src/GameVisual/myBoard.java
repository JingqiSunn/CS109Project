package GameVisual;

import GameElement.BoardUnit;
import GameElement.ControllingCenter;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.event.*;
import java.awt.Font;


public class myBoard extends JPanel implements KeyListener, Runnable {
    boolean isStart;

    int source=0;
    int best;

    int fx; //描述上下左右
    InitBoard currentBoard;
    ArrayList<BoardUnit> currentBoardInformation = currentBoard.getBoardInformation();
    ControllingCenter controllingCenter= currentBoard.getControllingCenter();
    public myBoard(){
        currentBoard = new InitBoard();
        setFocusable(true);
        setBackground(new Color(241, 228, 219));
        this.addKeyListener(this);
        init();
    }

    public ControllingCenter getControllingCenter() {
        return controllingCenter;
    }

    private void init(){
        isStart= controllingCenter.getGameValidity();

        source = 0;

        try {
            best = getS();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //理应初始值就是0
        controllingCenter.RandomlyGenerateCellInEmptyBoardUnits();//随机选取位置填入2或4（我觉得可能得把2或4改成case1或2----不确定）
    }


    private void Reinit(){
        ArrayList<Integer> formerBoardLocationSet = new ArrayList<>();
        formerBoardLocationSet = controllingCenter.getInformationOfAllTheCoordinateOfTheBoardUnit();
        ControllingCenter controllingCenter = new ControllingCenter();
        controllingCenter.setInformationOfAllTheCoordinateOfTheBoardUnit(formerBoardLocationSet);
        controllingCenter.SetThePlayingBoard();
        controllingCenter.ReIdentifyEmptyBoardUnitSet();
        isStart= controllingCenter.getGameValidity();

        source = 0;

        try {
            best = getS();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //理应初始值就是0
        controllingCenter.RandomlyGenerateCellInEmptyBoardUnits();//随机选取位置填入2或4（我觉得可能得把2或4改成case1或2----不确定）
    }







    public int getS() throws IOException{
        String filePath = "src\\GameVisual\\bestScore.txt";
        FileReader fileReader = new FileReader(filePath);

        int data = 0;
        String s = "";
        while ((data = fileReader.read()) != -1) {
            s += (char) data;
        }
        int y = Integer.parseInt(s);
        fileReader.close();
        return y;
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
        g.drawString("最高分", 300, 35);
        g.setFont(new Font("宋体", Font.BOLD, 35));
        g.drawString("游戏说明", 15, 55);
        g.setFont(new Font("宋体", Font.BOLD, 20));
        g.drawString("按上下左右键控制，按空格重新开始游戏", 15, 100);

        for (int i = 0; i < currentBoardInformation.size(); i++) {
            int cell_value = 0;
            if(currentBoardInformation.get(i).getCell()!=null){
                cell_value = currentBoardInformation.get(i).getCell().getValue();
            }
            paintBlock(g,new Block(cell_value), currentBoardInformation.get(i).getxCoordinate(),3- currentBoardInformation.get(i).getyCoordinate());
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
        if (isStart) {
            Thread thread = new Thread(this);
            if (keyCode == KeyEvent.VK_DOWN) {
                fx = 2;
                thread.start();
            } else if (keyCode == KeyEvent.VK_UP) {
                fx = 0;
                thread.start();
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                fx = 1;
                thread.start();
            } else if (keyCode == KeyEvent.VK_LEFT) {
                fx = 3;
                thread.start();
            }
        }
        if (keyCode==KeyEvent.VK_SPACE){
            Reinit();                                   //空格重启
        }
            repaint();
        }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {

        for (int k = 0; k < 3; k++) {
            switch (fx) {
                case 0 -> Up();
                case 1 -> Right();
                case 2 -> Down();
                case 3 -> Left();
                default -> {
                }
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (best < source) {
                best = source;
                try {
                    setS(source);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
            repaint();
            controllingCenter.RandomlyGenerateCellInEmptyBoardUnits();
            controllingCenter.UpdateGameValidity();
    }
    }


    private void setS(int y) throws IOException {
        String filePath = "src\\GameVisual\\bestScore.txt";
        FileWriter fileWriter = new FileWriter(filePath);
        String yy = y + "";
        fileWriter.write(yy);
        fileWriter.close();
    }

    private void Left() {
        controllingCenter.LeftAction();
        source=controllingCenter.getCurrentGameScore();
        controllingCenter.UpdateGameValidity();
    }

    private void Down() {
        controllingCenter.DownAction();
        source=controllingCenter.getCurrentGameScore();
        controllingCenter.UpdateGameValidity();
    }

    private void Right() {
        controllingCenter.RightAction();
        source=controllingCenter.getCurrentGameScore();
        controllingCenter.UpdateGameValidity();
    }

    private void Up() {
        controllingCenter.UpAction();
        source=controllingCenter.getCurrentGameScore();
        controllingCenter.UpdateGameValidity();
    }

    }

