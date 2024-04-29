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



public class myBoard extends JPanel implements KeyListener,Runnable {
    boolean isRunning = false;
    boolean isStart;

    int score = 0;
    int bestScore;

    int fx; //描述上下左右
    InitBoard currentBoard;
    ArrayList<BoardUnit> currentBoardInformation;
    ControllingCenter controllingCenter;

    public myBoard() {
        currentBoard = new InitBoard();
        currentBoardInformation = currentBoard.getBoardInformation();
        controllingCenter = currentBoard.getControllingCenter();
        setFocusable(true);
        setBackground(new Color(241, 228, 219));
        this.addKeyListener(this);
        init();
    }

    public ControllingCenter getControllingCenter() {
        return controllingCenter;
    }

    private void init() {
        isStart = controllingCenter.getGameValidity();

        score = 0;

        try {
            bestScore = getS();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //理应初始值就是0
        controllingCenter.RandomlyGenerateCellInEmptyBoardUnits();//随机选取位置填入2或4（我觉得可能得把2或4改成case1或2----不确定）
    }


    private void Reinit() {
        ArrayList<Integer> formerBoardLocationSet = new ArrayList<>();
        formerBoardLocationSet = controllingCenter.getInformationOfAllTheCoordinateOfTheBoardUnit();
        ControllingCenter controllingCenter = new ControllingCenter();
        controllingCenter.setInformationOfAllTheCoordinateOfTheBoardUnit(formerBoardLocationSet);
        controllingCenter.SetThePlayingBoard();
        controllingCenter.ReIdentifyEmptyBoardUnitSet();
        isStart = controllingCenter.getGameValidity();

        score = 0;

        try {
            bestScore = getS();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //理应初始值就是0
        controllingCenter.RandomlyGenerateCellInEmptyBoardUnits();//随机选取位置填入2或4（我觉得可能得把2或4改成case1或2----不确定）
    }


    public int getS() throws IOException {
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
        String value2 = bestScore + "";
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
            int valueOfTheBlock = 0;
            if (currentBoardInformation.get(i).getCell() != null) {
                valueOfTheBlock = currentBoardInformation.get(i).getCell().getValue();
            }
            paintBlock(g, new Block(valueOfTheBlock), currentBoardInformation.get(i).getxCoordinate(), 3 - currentBoardInformation.get(i).getyCoordinate());
        }

    }

    private void paintBlock(Graphics g, Block block, int x, int y) {
        block.setAllFont();
        g.setColor(block.colorOfTheBlock);
        g.fillRect(10 + x * 90 + 7, 120 + y * 90 + 7, 83, 83);
        if (block.value > 0) {
            g.setColor(block.colorOfTheNumberInTheBlock);
            g.setFont(block.fontOfTheNumberInTheBlock);
            FontMetrics fm = getFontMetrics(block.fontOfTheNumberInTheBlock);
            String value = block.valueOfTheBlockInString;
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
        int keyCode = e.getKeyCode();
        if (isStart && !isRunning) {
            switch (keyCode) {
                case KeyEvent.VK_DOWN:
                    fx = 1;
                    break;
                case KeyEvent.VK_UP:
                    fx = 0;
                    break;
                case KeyEvent.VK_RIGHT:
                    fx = 3;
                    break;
                case KeyEvent.VK_LEFT:
                    fx = 2;
                    break;
            }
            new Thread(this).start();
            isRunning = true;

        }
        if (keyCode == KeyEvent.VK_SPACE) {
            Reinit();

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
                case 3 -> Right();
                case 1 -> Down();
                case 2 -> Left();
                default -> {
                }
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (bestScore < score) {
                bestScore = score;
                try {
                    setS(score);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
            controllingCenter.UpdateGameValidity();
        }
        isRunning = false;
        repaint();
    }


    private void setS(int y) throws IOException {
        String filePath = "src\\GameVisual\\bestScore.txt";
        FileWriter fileWriter = new FileWriter(filePath);
        String yy = y + "";
        fileWriter.write(yy);
        fileWriter.close();
    }

    private void Left() {
        controllingCenter.UpdateTheAvailableDirectionSet();
        controllingCenter.LeftAction();
        score = controllingCenter.getCurrentGameScore();
        controllingCenter.UpdateGameValidity();
    }

    private void Down() {
        controllingCenter.UpdateTheAvailableDirectionSet();
        controllingCenter.DownAction();
        score = controllingCenter.getCurrentGameScore();
        controllingCenter.UpdateGameValidity();
    }

    private void Right() {
        controllingCenter.UpdateTheAvailableDirectionSet();
        controllingCenter.RightAction();
        score = controllingCenter.getCurrentGameScore();
        controllingCenter.UpdateGameValidity();
    }

    private void Up() {
        controllingCenter.UpdateTheAvailableDirectionSet();
        controllingCenter.UpAction();
        score = controllingCenter.getCurrentGameScore();
        controllingCenter.UpdateGameValidity();
    }
}

