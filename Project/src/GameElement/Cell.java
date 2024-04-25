package GameElement;


import java.awt.font.TextHitInfo;

public class Cell {
    private int value;
    private BoardUnit boardUnit;
    private Boolean whetherNeedToDisappear;

    private Board currentPlayingBoard;
    public Cell(int value, BoardUnit boardUnit) {
        this.value = value;
        this.boardUnit = boardUnit;
        this.whetherNeedToDisappear=false;
    }

    public int getValue() {
        return value;
    }

    public BoardUnit getBoardUnit() {
        return boardUnit;
    }

    public Boolean getWhetherNeedToDisappear() {
        return whetherNeedToDisappear;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setBoardUnit(BoardUnit boardUnit) {
        this.boardUnit = boardUnit;
    }

    public void setWhetherNeedToDisappear(Boolean whetherNeedToDisappear) {
        this.whetherNeedToDisappear = whetherNeedToDisappear;
    }

    static Boolean WhetherHaveTheSameValue(Cell cell1, Cell cell2){
        if(cell1.getValue()==cell2.getValue()){
            return true;
        } else{
            return false;
        }
    }
}
