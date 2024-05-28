import GameElement.ControllingCenter;
import GameVisual.TotalGameFrame;
import GameVisual.myBoard;
import Music.BackGroundMusic;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        TotalGameFrame totalGameFrame = new TotalGameFrame();
        BackGroundMusic audioPlay = new BackGroundMusic("src/Music/background.wav");
        audioPlay.start();

        /*JFrame jf = new JFrame("Game_2048");
        myBoard currentBoard = new myBoard();
        jf.add(currentBoard);
        ControllingCenter currentControllingCenter = currentBoard.getControllingCenter();

        jf.setSize(401,552);
        jf.setLocationRelativeTo(null);     //居中
        jf.setResizable(false);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);*/
    }
}
