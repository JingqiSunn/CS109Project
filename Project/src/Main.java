import GameElement.ControllingCenter;
import GameVisual.TotalGameFrame;
import GameVisual.myBoard;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        /*TotalGameFrame totalGameFrame = new TotalGameFrame();*/
        TotalGameFrame frame = new TotalGameFrame();

        // 在调用 pack() 前打印组件的大小
        System.out.println("Before pack():");
        printComponentSizes(frame);

        // 调用 pack()
        frame.pack();

        // 在调用 pack() 后打印组件的大小
        System.out.println("\nAfter pack():");
        printComponentSizes(frame);

        // 设置框架可见
        frame.setVisible(true);
    }

    // 打印组件的大小
    private static void printComponentSizes(Container container) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            System.out.println(component.getClass().getSimpleName() + ": " + component.getSize());
        }
    }
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

