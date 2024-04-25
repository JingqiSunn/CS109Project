package GameVisual;

import java.awt.*;

public class Block {

    public int kind;
    public Font wFont;
    public Color wColor;
    public Color bColor;
    public String s;

    public Block (int kind){
        this.kind = kind;
    }

    private final Font font1 = new Font("宋体", Font.BOLD, 46);
    private final Font font2 = new Font("宋体", Font.BOLD, 40);
    private final Font font3 = new Font("宋体", Font.BOLD, 34);
    private final Font font4 = new Font("宋体", Font.BOLD, 28);

    private final Color color1 = new Color(119,108,99);
    private final Color color2 = new Color(254,254,254);

    public void setAllFont() {
        switch (kind) {
            case 0 -> bColor = new Color(206, 194, 180);
            case 1 -> {
                wFont = font1;          //文字字体
                wColor = color1;        //文字颜色
                bColor = new Color(237, 229, 218);     //背景颜色
                s = 2 + "";
            }
            case 2 -> {
                wFont = font1;
                wColor = color1;
                bColor = new Color(237, 220, 190);
                s = 4 + "";
            }
            case 3 -> {
                wFont = font1;
                wColor = color1;
                bColor = new Color(242, 177, 123);
                s = 8 + "";
            }
            case 4 -> {
                wFont = font2;
                wColor = color2;
                bColor = new Color(246, 147, 92);
                s = 16 + "";
            }
            case 5 -> {
                wFont = font2;
                wColor = color2;
                bColor = new Color(245, 118, 86);
                s = 32 + "";
            }
            case 6 -> {
                wFont = font2;
                wColor = color2;
                bColor = new Color(245, 83, 45);
                s = 64 + "";
            }
            case 7 -> {
                wFont = font3;
                wColor = color2;
                bColor = new Color(237, 206, 115);
                s = 128 + "";
            }
            case 8 -> {
                wFont = font3;
                wColor = color2;
                bColor = new Color(230,209,81);
                s = 256 + "";
            }
            case 9 -> {
                wFont = font3;
                wColor = color2;
                bColor = new Color(207,163,12);
                s = 512 + "";
            }
            case 10 -> {
                wFont = font4;
                wColor = color2;
                bColor = new Color(229,180,6);
                s = 1024 + "";
            }
            case 11 -> {
                wFont = font4;
                wColor = color2;
                bColor = new Color(161,131,115);
                s = 2048 + "";
            }
            default -> {
            }
        }
    }

}
