package GameElement;


import java.awt.font.TextHitInfo;

class Cell {
    private int value;
    private BoardUnit boardUnit;

    private Board currentPlayingBoard;
    public Cell(int value, BoardUnit boardUnit) {
        this.value = value;
        this.boardUnit = boardUnit;
    }

    public int getValue() {
        return value;
    }

    public BoardUnit getBoardUnit() {
        return boardUnit;
    }



    public void setValue(int value) {
        this.value = value;
    }

    public void setBoardUnit(BoardUnit boardUnit) {
        this.boardUnit = boardUnit;
    }
}
