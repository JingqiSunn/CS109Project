package GameElement;

import java.util.ArrayList;
import java.util.Collections;

class BoardUnit {
    private int xCoordinate;
    private int yCoordinate;
    private Cell cell;

    private Board currentPlayingBoard;

    BoardUnit(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    int getxCoordinate() {
        return xCoordinate;
    }

    int getyCoordinate() {
        return yCoordinate;
    }

    Cell getCell() {
        return cell;
    }

    Board getCurrentPlayingBoard() {
        return currentPlayingBoard;
    }

    void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }


    void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    void setCell(Cell cell) {
        this.cell = cell;
    }

    void setCurrentPlayingBoard(Board currentPlayingBoard) {
        this.currentPlayingBoard = currentPlayingBoard;
    }

    static Boolean whetherTwoBoardUnitsTheSameWhenSetUp(BoardUnit boardUnit1, BoardUnit boardUnit2){
        if(boardUnit1.getxCoordinate()==boardUnit2.getxCoordinate()&&  boardUnit1.getyCoordinate()==boardUnit2.getyCoordinate()){
            return true;
        } else {
            return false;
        }
    }

    static Boolean whetherTwoBoardUnitsAreNeighborInRow(BoardUnit boardUnit1, BoardUnit boardUnit2){
        int xCoordinateOfBoardUnit1 = boardUnit1.getxCoordinate();
        int yCoordinateOfBoardUnit1 = boardUnit1.getyCoordinate();
        int xCoordinateOfBoardUnit2 = boardUnit2.getxCoordinate();
        int yCoordinateOfBoardUnit2 = boardUnit2.getyCoordinate();
        if (yCoordinateOfBoardUnit1==yCoordinateOfBoardUnit2&&((xCoordinateOfBoardUnit1-xCoordinateOfBoardUnit2)==1||(xCoordinateOfBoardUnit1-xCoordinateOfBoardUnit2)==-1)){
            return true;
        } else {
            return false;
        }
    }

    static Boolean whetherTwoBoardUnitsAreNeighborInColumn(BoardUnit boardUnit1, BoardUnit boardUnit2){
        int xCoordinateOfBoardUnit1 = boardUnit1.getxCoordinate();
        int yCoordinateOfBoardUnit1 = boardUnit1.getyCoordinate();
        int xCoordinateOfBoardUnit2 = boardUnit2.getxCoordinate();
        int yCoordinateOfBoardUnit2 = boardUnit2.getyCoordinate();
        if (xCoordinateOfBoardUnit1==xCoordinateOfBoardUnit2&&((yCoordinateOfBoardUnit1-yCoordinateOfBoardUnit2)==1||(yCoordinateOfBoardUnit1-yCoordinateOfBoardUnit2)==-1)){
            return true;
        } else {
            return false;
        }
    }
    static ArrayList<BoardUnit> SortTheBoardUnitSetInTheSameColumnByRow(ArrayList<BoardUnit> originalBoardUnitSet){
        ArrayList<Integer> recordOfYCoordinate = new ArrayList<>();
        ArrayList<BoardUnit> BoardUnitSetAfterSortingByY=new ArrayList<>();
        for (int indexInTheOriginalBoardUnitSet = 0; indexInTheOriginalBoardUnitSet < originalBoardUnitSet.size(); indexInTheOriginalBoardUnitSet++) {
            recordOfYCoordinate.add(originalBoardUnitSet.get(indexInTheOriginalBoardUnitSet).getyCoordinate());
        }
        Collections.sort(recordOfYCoordinate);
        for (int indexInTheRecordOfYCoordinate = 0; indexInTheRecordOfYCoordinate < recordOfYCoordinate.size(); indexInTheRecordOfYCoordinate++) {
            for (int indexInTheOriginalBoardUnit = 0; indexInTheOriginalBoardUnit < originalBoardUnitSet.size(); indexInTheOriginalBoardUnit++) {
                if(recordOfYCoordinate.get(indexInTheRecordOfYCoordinate)==originalBoardUnitSet.get(indexInTheOriginalBoardUnit).getyCoordinate()){
                    BoardUnitSetAfterSortingByY.add(originalBoardUnitSet.get(indexInTheOriginalBoardUnit));
                    break;
                }
            }
        }
        return BoardUnitSetAfterSortingByY;
    }
    static ArrayList<BoardUnit> SortTheBoardUnitSetInTheSameRowByColumn(ArrayList<BoardUnit> originalBoardUnitSet){
        ArrayList<Integer> recordOfXCoordinate = new ArrayList<>();
        ArrayList<BoardUnit> BoardUnitSetAfterSortingByX=new ArrayList<>();
        for (int indexInTheOriginalBoardUnitSet = 0; indexInTheOriginalBoardUnitSet < originalBoardUnitSet.size(); indexInTheOriginalBoardUnitSet++) {
            recordOfXCoordinate.add(originalBoardUnitSet.get(indexInTheOriginalBoardUnitSet).getxCoordinate());
        }
        Collections.sort(recordOfXCoordinate);
        for (int indexInTheRecordOfXCoordinate = 0; indexInTheRecordOfXCoordinate < recordOfXCoordinate.size(); indexInTheRecordOfXCoordinate++) {
            for (int indexInTheOriginalBoardUnit = 0; indexInTheOriginalBoardUnit < originalBoardUnitSet.size(); indexInTheOriginalBoardUnit++) {
                if(recordOfXCoordinate.get(indexInTheRecordOfXCoordinate)==originalBoardUnitSet.get(indexInTheOriginalBoardUnit).getxCoordinate()){
                    BoardUnitSetAfterSortingByX.add(originalBoardUnitSet.get(indexInTheOriginalBoardUnit));
                    break;
                }
            }
        }
        return BoardUnitSetAfterSortingByX;
    }

    static Boolean WhetherTheContentInTheTwoMeaningfulBoardUnitsCanBeEliminated(BoardUnit boardUnit1,BoardUnit boardUnit2){
        if(Cell.WhetherHaveTheSameValue(boardUnit1.getCell(),boardUnit2.getCell())){
            return true;
        } else {
            return false;
        }
    }


}
