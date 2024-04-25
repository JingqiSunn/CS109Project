import GameVisual.myBoard;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame jf = new JFrame("Game_2048");

        jf.add(new myBoard());

        jf.setSize(401,552);
        jf.setLocationRelativeTo(null);     //居中
        jf.setResizable(false);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    }
