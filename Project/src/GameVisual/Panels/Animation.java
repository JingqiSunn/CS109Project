package GameVisual.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animation {
    public void animation(int xFormer, int yFormer, int xNow, int yNow,JLabel currentValueToShow,String currentValue,Color color,Font font){
        final Point start = new Point(xFormer, yFormer);
        final Point end = new Point(xNow, yNow);
        final int duration = 2000; // 动画持续时间，单位毫秒
        final int steps = 100; // 动画步数
        final int delay = duration / steps; // 每步的延迟时间

        // 定时器用于动画
        Timer timer = new Timer(delay, new ActionListener() {
            private int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= steps) {
                    ((Timer) e.getSource()).stop();
                    currentValueToShow.setLocation(end); // 确保最后到达准确位置
                    return;
                }

                int dx = (end.x - start.x) / steps;
                int dy = (end.y - start.y) / steps;
                currentValueToShow.setLocation(currentValueToShow.getX() + dx, currentValueToShow.getY() + dy);
                currentValueToShow.setText(currentValue);
                currentValueToShow.setForeground(color);
                currentValueToShow.setFont(font);
                currentValueToShow.setVisible(true);
                count++;
            }
        });
        timer.start();
    }
}
