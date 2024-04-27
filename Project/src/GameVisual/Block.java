package GameVisual;

import java.awt.*;

public class Block {

    public int value;
    public Font fontOfTheNumberInTheBlock;
    public Color colorOfTheNumberInTheBlock;
    public Color colorOfTheBlock;
    public String valueOfTheBlockInString;

    public Block (int value){
        this.value = value;
    }

    private final Font font1 = new Font("宋体", Font.BOLD, 46);
    private final Font font2 = new Font("宋体", Font.BOLD, 40);
    private final Font font3 = new Font("宋体", Font.BOLD, 34);
    private final Font font4 = new Font("宋体", Font.BOLD, 28);

    private final Color color1 = new Color(119,108,99);
    private final Color color2 = new Color(254,254,254);

    public void setAllFont() {
        switch (value) {
            case 0 -> colorOfTheBlock = new Color(206, 194, 180);
            case 2 -> {
                fontOfTheNumberInTheBlock = font1;          //文字字体
                colorOfTheNumberInTheBlock = color1;        //文字颜色
                colorOfTheBlock = new Color(237, 229, 218);     //背景颜色
                valueOfTheBlockInString = 2 + "";
            }
            case 4 -> {
                fontOfTheNumberInTheBlock = font1;
                colorOfTheNumberInTheBlock = color1;
                colorOfTheBlock = new Color(237, 220, 190);
                valueOfTheBlockInString = 4 + "";
            }
            case 8 -> {
                fontOfTheNumberInTheBlock = font1;
                colorOfTheNumberInTheBlock = color1;
                colorOfTheBlock = new Color(242, 177, 123);
                valueOfTheBlockInString = 8 + "";
            }
            case 16 -> {
                fontOfTheNumberInTheBlock = font2;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(246, 147, 92);
                valueOfTheBlockInString = 16 + "";
            }
            case 32 -> {
                fontOfTheNumberInTheBlock = font2;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(245, 118, 86);
                valueOfTheBlockInString = 32 + "";
            }
            case 64 -> {
                fontOfTheNumberInTheBlock = font2;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(245, 83, 45);
                valueOfTheBlockInString = 64 + "";
            }
            case 128 -> {
                fontOfTheNumberInTheBlock = font3;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(237, 206, 115);
                valueOfTheBlockInString = 128 + "";
            }
            case 256 -> {
                fontOfTheNumberInTheBlock = font3;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(230,209,81);
                valueOfTheBlockInString = 256 + "";
            }
            case 512 -> {
                fontOfTheNumberInTheBlock = font3;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(207,163,12);
                valueOfTheBlockInString = 512 + "";
            }
            case 1024 -> {
                fontOfTheNumberInTheBlock = font4;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(229,180,6);
                valueOfTheBlockInString = 1024 + "";
            }
            case 2048 -> {
                fontOfTheNumberInTheBlock = font4;
                colorOfTheNumberInTheBlock = color2;
                colorOfTheBlock = new Color(161,131,115);
                valueOfTheBlockInString = 2048 + "";
            }
            default -> {
            }
        }
    }

}
