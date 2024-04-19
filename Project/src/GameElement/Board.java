package GameElement;

import java.util.ArrayList;

class Board {
    private ArrayList<BoardUnit> boardLocationSet;
    private ControllingCenter controllingCenter;

    Board() {
        this.boardLocationSet = new ArrayList<>();
        controllingCenter = new ControllingCenter();
    }

    ArrayList<BoardUnit> getBoardLocationSet() {
        return boardLocationSet;
    }

    ControllingCenter getControllingCenter() {
        return controllingCenter;
    }

    void setBoardLocationSet(ArrayList<BoardUnit> boardLocationSet) {
        this.boardLocationSet = boardLocationSet;
    }

    void setControllingCenter(ControllingCenter controllingCenter) {
        this.controllingCenter = controllingCenter;
    }
    void addBoardUnitToCurrentBoard(BoardUnit additionBoardUnit){
        boardLocationSet.add(additionBoardUnit);
    }

    Board SetThePlayingBoard(ArrayList<Integer> theSetOfLocationOfBoardUnit){
        boolean whetherValidInput =true;
        for (int locationInTheSet = 0; locationInTheSet < theSetOfLocationOfBoardUnit.size(); locationInTheSet++) {
            if(theSetOfLocationOfBoardUnit.get(locationInTheSet)<0){
                whetherValidInput = false;
                System.out.println("The Coordinate you offered is invalid, there can't be negative number.");
                return this;
            }
        }
        if(theSetOfLocationOfBoardUnit.size()%2!=0){
            whetherValidInput = false;
            System.out.println("The Coordinate you offered is invalid, the total number should be even");
            return this;
        } else {
            for (int locationInTheInputPair = 0; locationInTheInputPair < theSetOfLocationOfBoardUnit.size()/2; locationInTheInputPair++) {
                boardLocationSet.add(new BoardUnit(theSetOfLocationOfBoardUnit.get(2*locationInTheInputPair),theSetOfLocationOfBoardUnit.get(2*locationInTheInputPair+1)));
            }
        }
        return  this;
    }

    static Board RemoveTheSameBoardUnit(Board originalBoard){
        Board theCopyOfTheOriginalBoard = originalBoard;
        for (int indexInTheTotalUnit = 0; indexInTheTotalUnit < originalBoard.getBoardLocationSet().size(); indexInTheTotalUnit++) {
            for (int indexInRestTotalUnit = indexInTheTotalUnit+1; indexInRestTotalUnit < originalBoard.getBoardLocationSet().size(); indexInRestTotalUnit++)
                if (BoardUnit.whetherTwoBoardUnitsTheSameWhenSetUp(theCopyOfTheOriginalBoard.getBoardLocationSet().get(indexInTheTotalUnit),theCopyOfTheOriginalBoard.getBoardLocationSet().get(indexInRestTotalUnit))==true) {
                    theCopyOfTheOriginalBoard.getBoardLocationSet().remove(indexInRestTotalUnit);
                    indexInRestTotalUnit--;
                }
        }
        return theCopyOfTheOriginalBoard;
    }
}
