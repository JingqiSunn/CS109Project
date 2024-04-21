package GameElement;
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

}
