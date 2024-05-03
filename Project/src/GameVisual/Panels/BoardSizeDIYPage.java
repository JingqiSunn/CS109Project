package GameVisual.Panels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BoardSizeDIYPage extends JPanel {

    Dimension totalSize;
    JPanel blockSets;

    UnitBlock[][] blockSet;
    int [][] startXOfBlocks;
    int [][] startYOfBlocks;
    int totalWidth;
    int totalHeight;
    int controllingSide;
    int startXOfBlockSet;
    int startYOfBlockSet;

    int sizeOfTheBlock;
    int sizeOfTheBlockSet;

    public BoardSizeDIYPage(Dimension screenSize){
        this.setLayout(null);
        this.UpdateSizeAndLocationForOptions(screenSize);
        this.setBounds(0, 0, totalWidth, totalHeight);
        this.SetUpOptionsInTheLoginPage();
    }

    public UnitBlock[][] getBlockSet() {
        return blockSet;
    }

    void UpdateSizeAndLocationForOptions(Dimension screenSize){
        totalSize = screenSize;
        totalHeight = (int) screenSize.getHeight();
        totalWidth = (int) screenSize.getWidth();
        controllingSide = Math.min(totalHeight, totalWidth);
        sizeOfTheBlockSet = controllingSide/14*10;
        sizeOfTheBlock = sizeOfTheBlockSet/10;
        startXOfBlockSet = (totalWidth-sizeOfTheBlockSet)/2;
        startYOfBlockSet = (totalHeight-sizeOfTheBlockSet)/2;
        startXOfBlocks = new int[10][10];
        startYOfBlocks = new int[10][10];
        for (int layerInTheBlockSizeInRow = 0; layerInTheBlockSizeInRow < 10; layerInTheBlockSizeInRow++) {
            for (int layerInTheBlockSizeInColumn = 0; layerInTheBlockSizeInColumn < 10; layerInTheBlockSizeInColumn++) {
                startXOfBlocks[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn]= layerInTheBlockSizeInColumn*sizeOfTheBlock;
                startYOfBlocks[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn]= layerInTheBlockSizeInRow*sizeOfTheBlock;
            }
        }
    }
    void SetUpOptionsInTheLoginPage(){
        Border borderOfTheBlock = BorderFactory.createLineBorder(Color.BLACK,6,false);
        blockSets = new JPanel();
        blockSets.setBounds(startXOfBlockSet,startYOfBlockSet,sizeOfTheBlockSet,sizeOfTheBlockSet);
        blockSets.setLayout(null);
        blockSets.setBackground(Color.LIGHT_GRAY);
        blockSet = new UnitBlock[10][10];
        for (int layerInTheBlockSizeInRow = 0; layerInTheBlockSizeInRow < 10; layerInTheBlockSizeInRow++) {
            for (int layerInTheBlockSizeInColumn = 0; layerInTheBlockSizeInColumn < 10; layerInTheBlockSizeInColumn++) {
                blockSet [layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn] = new UnitBlock();
                blockSet [layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn].setxCoordinate(layerInTheBlockSizeInColumn);
                blockSet [layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn].setyCoordinate(9-layerInTheBlockSizeInRow);
                int startX = startXOfBlocks[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn];
                int startY = startYOfBlocks[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn];
                blockSet[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn].setBounds(startX,startY,sizeOfTheBlock,sizeOfTheBlock);
                blockSet[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn].setBorder(borderOfTheBlock);
                blockSet[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn].setBackground(Color.white);
                blockSet[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn].setVisible(true);
                blockSets.add(blockSet[layerInTheBlockSizeInRow][layerInTheBlockSizeInColumn]);
            }
        }
        blockSets.setVisible(true);
        this.add(blockSets);
    }
}