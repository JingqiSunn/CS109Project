package GameVisual.Panels;

import GameElement.BoardUnit;
import GameElement.ControllingCenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawnBlockUnit extends JPanel {
    BoardUnit correspondingBoardUnit;
    int currentValue;
    JLabel currentValueToShow;
    public Font fontOfTheNumberInTheBlock;
    public Color colorOfTheNumberInTheBlock;
    public Color colorOfTheBlock;
    public String valueOfTheBlockInString;

    public int sizeOfTheBlockUnit;
    public int sizeOfTheBlock;

    public int startX;
    public int startY;
    public int maxX;
    public int maxY;
    public ControllingCenter controllingCenter;
    public int xCoordinate;
    public int yCoordinate;

    DrawnBlockUnit(int xCoordinate, int yCoordinate, int sizeOfTheBlockUnit, ControllingCenter controllingCenter) {
        currentValue = 0;
        this.setLayout(new BorderLayout());
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.sizeOfTheBlockUnit = sizeOfTheBlockUnit;
        this.sizeOfTheBlock = (int) (((double) sizeOfTheBlockUnit / (double) 92) * 100);
        this.controllingCenter = controllingCenter;
        this.ConnectWithCorrespondingBoardUnit();
        maxX = controllingCenter.FindTheMaxXCoordinate();
        maxY = controllingCenter.FindTheMaxYCoordinate();
        startX = sizeOfTheBlock * xCoordinate + (sizeOfTheBlock - sizeOfTheBlockUnit) / 2;
        startY = sizeOfTheBlock * (maxY - yCoordinate) + (sizeOfTheBlock - sizeOfTheBlockUnit) / 2;
        this.setBounds(startX, startY, sizeOfTheBlockUnit, sizeOfTheBlockUnit);


        this.setAllFont();
    }

    public Color getColorOfTheBlock() {
        return colorOfTheBlock;
    }

    private final Font font1 = new Font("Times New Roman", Font.BOLD, 46);
    private final Font font2 = new Font("Times New Roman", Font.BOLD, 40);
    private final Font font3 = new Font("Times New Roman", Font.BOLD, 34);
    private final Font font4 = new Font("Times New Roman", Font.BOLD, 28);

    private final Color color1 = new Color(119, 108, 99);
    private final Color color2 = new Color(254, 254, 254);

    public void setAllFont() {
        switch (currentValue) {
            case 0 -> colorOfTheBlock = new Color(206, 194, 180);
            case 2 -> {
                fontOfTheNumberInTheBlock = font1;
                colorOfTheNumberInTheBlock = color1;
                colorOfTheBlock = new Color(237, 229, 218);
                valueOfTheBlockInString = 2 + "";
                this.AnimateEffect();
            }
            case 4 -> {
                fontOfTheNumberInTheBlock = font1;
                colorOfTheNumberInTheBlock = color1;
                colorOfTheBlock = new Color(237, 220, 190);
                valueOfTheBlockInString = 4 + "";
                this.AnimateEffect();
            }
            case 8 -> {
                fontOfTheNumberInTheBlock = font1;
                colorOfTheNumberInTheBlock = color1;
                colorOfTheBlock = new Color(242, 177, 123);
                valueOfTheBlockInString = 8 + "";
                this.AnimateEffect();
            }
            case 16 -> {
                fontOfTheNumberInTheBlock = font2;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(246, 147, 92);
                valueOfTheBlockInString = 16 + "";
                this.AnimateEffect();
            }
            case 32 -> {
                fontOfTheNumberInTheBlock = font2;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(245, 118, 86);
                valueOfTheBlockInString = 32 + "";
                this.AnimateEffect();
            }
            case 64 -> {
                fontOfTheNumberInTheBlock = font2;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(245, 83, 45);
                valueOfTheBlockInString = 64 + "";
                this.AnimateEffect();
            }
            case 128 -> {
                fontOfTheNumberInTheBlock = font3;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(237, 206, 115);
                valueOfTheBlockInString = 128 + "";
                this.AnimateEffect();
            }
            case 256 -> {
                fontOfTheNumberInTheBlock = font3;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(230, 209, 81);
                valueOfTheBlockInString = 256 + "";
                this.AnimateEffect();
            }
            case 512 -> {
                fontOfTheNumberInTheBlock = font3;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(207, 163, 12);
                valueOfTheBlockInString = 512 + "";
                this.AnimateEffect();
            }
            case 1024 -> {
                fontOfTheNumberInTheBlock = font4;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(229, 180, 6);
                valueOfTheBlockInString = 1024 + "";
                this.AnimateEffect();
            }
            case 2048 -> {
                fontOfTheNumberInTheBlock = font4;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(161, 131, 115);
                valueOfTheBlockInString = 2048 + "";
                this.AnimateEffect();
            }
            default -> {
            }
        }
    }

    public void UpdateTheOutputShow() {
        this.UpdateTheData();
        currentValueToShow = new JLabel(String.valueOf(currentValue));
        currentValueToShow.setHorizontalAlignment(JLabel.CENTER);
        currentValueToShow.setVerticalAlignment(JLabel.CENTER);
        currentValueToShow.setForeground(colorOfTheNumberInTheBlock);
        currentValueToShow.setFont(fontOfTheNumberInTheBlock);
        if (currentValue ==0){
            currentValueToShow.setVisible(false);
        } else {
            currentValueToShow.setVisible(true);
        }
        this.add(currentValueToShow,BorderLayout.CENTER);
        this.setBackground(colorOfTheBlock);
    }

    void UpdateTheOutputShowInGame(){
        currentValueToShow.setVisible(true);
        this.remove(currentValueToShow);
        this.UpdateTheData();
        currentValueToShow.setText(String.valueOf(currentValue));
        currentValueToShow.setForeground(colorOfTheNumberInTheBlock);
        currentValueToShow.setFont(fontOfTheNumberInTheBlock);
        if (currentValue ==0){
            currentValueToShow.setVisible(false);
        } else {
            currentValueToShow.setVisible(true);
        }
        this.add(currentValueToShow,BorderLayout.CENTER);
        this.setBackground(colorOfTheBlock);
    }

    void UpdateTheData() {
        if (correspondingBoardUnit.getCell() == null) {
            currentValue = 0;
        } else {
            currentValue = correspondingBoardUnit.getCell().getValue();
        }
        this.setAllFont();
    }

    void ConnectWithCorrespondingBoardUnit() {
        for (int indexInAllTheBoardUnit = 0; indexInAllTheBoardUnit < controllingCenter.GetTheBoardUnitSet().size(); indexInAllTheBoardUnit++) {
            if (this.xCoordinate == controllingCenter.GetTheBoardUnitSet().get(indexInAllTheBoardUnit).getxCoordinate() && this.yCoordinate == controllingCenter.GetTheBoardUnitSet().get(indexInAllTheBoardUnit).getyCoordinate()) {
                this.correspondingBoardUnit = controllingCenter.GetTheBoardUnitSet().get(indexInAllTheBoardUnit);
            }
        }
    }
    private void AnimateEffect(){
        if (controllingCenter.whetherAnimated){
        final int steps = 15;
        final int[] currentStep = {0};
        Timer timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStep[0] >= steps) {
                    ((Timer) e.getSource()).stop();
                    return;
                }
                int red =(colorOfTheBlock.getRed()-255)/steps*currentStep[0]+255;
                int green = (colorOfTheBlock.getGreen()-255)/steps * currentStep[0]+255;
                int blue = (colorOfTheBlock.getBlue()-255)/steps * currentStep[0]+255;
                setBackground(new Color(red,green,blue));
                currentStep[0]++;
            }
        });
        timer.start();} else {
            setBackground(new Color(colorOfTheBlock.getRed(),colorOfTheBlock.getGreen(),colorOfTheBlock.getBlue()));
        }
    }
}
