import GameElement.ControllingCenter;
import GameVisual.TotalGameFrame;
import GameVisual.myBoard;
import Music.BackGroundMusic;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        TotalGameFrame totalGameFrame = new TotalGameFrame();

        /*JFrame jf = new JFrame("Game_2048");
        myBoard currentBoard = new myBoard();
        jf.add(currentBoard);
        ControllingCenter currentControllingCenter = currentBoard.getControllingCenter();

        jf.setSize(401,552);
        jf.setLocationRelativeTo(null);     //居中
        jf.setResizable(false);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);*/
<<<<<<< HEAD

=======
        BackGroundMusic audioPlayWave = new BackGroundMusic("src/Music/music.wav");
        audioPlayWave.start();
>>>>>>> 030a927ca493fb9f8933fd13c67c48668289c6ae
    }
}
