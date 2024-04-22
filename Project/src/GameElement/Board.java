package GameElement;

import java.util.ArrayList;
import java.util.Collections;

class Board {
    private ArrayList<ArrayList<BoardUnit>> neighborBoardUnitsInRow;
    private ArrayList<ArrayList<BoardUnit>> neighborBoardUnitsInColumn;

    private ArrayList<ArrayList<BoardUnit>> boardUnitsInTheSameRow;
    private ArrayList<ArrayList<BoardUnit>> boardUnitsIntheSameColumn;
    private ArrayList<BoardUnit> boardLocationSet;
    private ControllingCenter controllingCenter;
    private ArrayList<Integer> existingXCoordinate;
    private ArrayList<Integer> existingYCoordinate;

    Board() {
        existingXCoordinate = new ArrayList<>();
        existingYCoordinate = new ArrayList<>();
        boardUnitsInTheSameRow = new ArrayList<>();
        boardUnitsIntheSameColumn = new ArrayList<>();
        this.boardLocationSet = new ArrayList<>();
        controllingCenter = null;
        neighborBoardUnitsInColumn = new ArrayList<>();
        neighborBoardUnitsInRow = new ArrayList<>();
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
        Boolean whetherAlreadyExistX=false;
        Boolean whetherAlreadyExistY=false;
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
                boardLocationSet.get(locationInTheInputPair).setCurrentPlayingBoard(this);
                if (locationInTheInputPair==0){
                    existingXCoordinate.add(theSetOfLocationOfBoardUnit.get(0));
                    existingYCoordinate.add(theSetOfLocationOfBoardUnit.get(1));
                    continue;
                }

                for (int indexInTheExistingSet = 0; indexInTheExistingSet < existingXCoordinate.size(); indexInTheExistingSet++) {
                    if(existingXCoordinate.get(indexInTheExistingSet).equals(theSetOfLocationOfBoardUnit.get(2*locationInTheInputPair))){
                        whetherAlreadyExistX = true;
                        break;
                    }
                }
                for (int indexInTheExistingSet = 0; indexInTheExistingSet < existingYCoordinate.size(); indexInTheExistingSet++) {
                    if(existingYCoordinate.get(indexInTheExistingSet).equals(theSetOfLocationOfBoardUnit.get(2*locationInTheInputPair+1))){
                        whetherAlreadyExistY = true;
                        break;
                    }
                }
                if (!whetherAlreadyExistX){
                    existingXCoordinate.add(theSetOfLocationOfBoardUnit.get(2*locationInTheInputPair));
                }
                if(!whetherAlreadyExistY){
                    existingYCoordinate.add(theSetOfLocationOfBoardUnit.get(2*locationInTheInputPair+1));
                }
                whetherAlreadyExistX = false;
                whetherAlreadyExistY = false;
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

    static BoardUnit GetTheLeftBoundOfTheNeighborBoardUnitSet(ArrayList<BoardUnit> targetBoardUnitList){
        if(targetBoardUnitList.isEmpty()){
            return null;
        }
        BoardUnit currentLeftBound = targetBoardUnitList.get(0);
        for (int indexInTheSet = 0; indexInTheSet < targetBoardUnitList.size(); indexInTheSet++) {
            if(targetBoardUnitList.get(indexInTheSet).getxCoordinate()<currentLeftBound.getxCoordinate()){
                currentLeftBound = targetBoardUnitList.get(indexInTheSet);
            }
        }
        return currentLeftBound;
    }

    static BoardUnit GetTheRightBoundOfTheNeighborBoardUnitSet(ArrayList<BoardUnit> targetBoardUnitList){
        if(targetBoardUnitList.isEmpty()){
            return null;
        }
        BoardUnit currentLeftBound = targetBoardUnitList.get(0);
        for (int indexInTheSet = 0; indexInTheSet < targetBoardUnitList.size(); indexInTheSet++) {
            if(targetBoardUnitList.get(indexInTheSet).getxCoordinate()>currentLeftBound.getxCoordinate()){
                currentLeftBound = targetBoardUnitList.get(indexInTheSet);
            }
        }
        return currentLeftBound;
    }

    static BoardUnit GetTheUpBoundOfTheNeighborBoardUnitSet(ArrayList<BoardUnit> targetBoardUnitList){
        if(targetBoardUnitList.isEmpty()){
            return null;
        }
        BoardUnit currentLeftBound = targetBoardUnitList.get(0);
        for (int indexInTheSet = 0; indexInTheSet < targetBoardUnitList.size(); indexInTheSet++) {
            if(targetBoardUnitList.get(indexInTheSet).getyCoordinate()>currentLeftBound.getyCoordinate()){
                currentLeftBound = targetBoardUnitList.get(indexInTheSet);
            }
        }
        return currentLeftBound;
    }

    static BoardUnit GetTheDownBoundOfTheNeighborBoardUnitSet(ArrayList<BoardUnit> targetBoardUnitList){
        if(targetBoardUnitList.isEmpty()){
            return null;
        }
        BoardUnit currentLeftBound = targetBoardUnitList.get(0);
        for (int indexInTheSet = 0; indexInTheSet < targetBoardUnitList.size(); indexInTheSet++) {
            if(targetBoardUnitList.get(indexInTheSet).getyCoordinate()<currentLeftBound.getyCoordinate()){
                currentLeftBound = targetBoardUnitList.get(indexInTheSet);
            }
        }
        return currentLeftBound;
    }


    void GetBoardUnitsInTheSameRow(){
        ArrayList<Integer> copyOfExistingYCoordinateAfterSort = new ArrayList<>();
        copyOfExistingYCoordinateAfterSort = existingYCoordinate;
        Collections.sort(copyOfExistingYCoordinateAfterSort);
        for (int indexInTheTotalRow = 0; indexInTheTotalRow < copyOfExistingYCoordinateAfterSort.size(); indexInTheTotalRow++) {
            ArrayList<BoardUnit> setOfBoardUnitInTheSameRow=new ArrayList<>();
            for (int sequenceInBoardLocationSet = 0; sequenceInBoardLocationSet < boardLocationSet.size(); sequenceInBoardLocationSet++) {
                if (boardLocationSet.get(sequenceInBoardLocationSet).getyCoordinate()==copyOfExistingYCoordinateAfterSort.get(indexInTheTotalRow)){
                    setOfBoardUnitInTheSameRow.add(boardLocationSet.get(sequenceInBoardLocationSet));
                }
            }
            setOfBoardUnitInTheSameRow = BoardUnit.SortTheBoardUnitSetInTheSameRowByColumn(setOfBoardUnitInTheSameRow);
            boardUnitsInTheSameRow.add(setOfBoardUnitInTheSameRow);
        }
    }

    void GetBoardUnitsInTheSameColumn(){
        ArrayList<Integer> copyOfExistingXCoordinateAfterSort = new ArrayList<>();
        copyOfExistingXCoordinateAfterSort = existingXCoordinate;
        Collections.sort(copyOfExistingXCoordinateAfterSort);
        for (int indexInTheTotalColumn = 0; indexInTheTotalColumn < copyOfExistingXCoordinateAfterSort.size(); indexInTheTotalColumn++) {
            ArrayList<BoardUnit> setOfBoardUnitInTheSameColumn=new ArrayList<>();
            for (int sequenceInBoardLocationSet = 0; sequenceInBoardLocationSet < boardLocationSet.size(); sequenceInBoardLocationSet++) {
                if (boardLocationSet.get(sequenceInBoardLocationSet).getxCoordinate()==copyOfExistingXCoordinateAfterSort.get(indexInTheTotalColumn)){
                    setOfBoardUnitInTheSameColumn.add(boardLocationSet.get(sequenceInBoardLocationSet));
                }
            }
            setOfBoardUnitInTheSameColumn = BoardUnit.SortTheBoardUnitSetInTheSameColumnByRow(setOfBoardUnitInTheSameColumn);
            boardUnitsIntheSameColumn.add(setOfBoardUnitInTheSameColumn);
        }
    }
    void GetNeighborBoardUnitInRow(){
        ArrayList<BoardUnit> currentNeighborSet = new ArrayList<>();
        currentNeighborSet.add(boardUnitsInTheSameRow.get(0).get(0));
        BoardUnit lastBoardUnit = boardUnitsInTheSameRow.get(0).get(0);
        for (int indexInTheTotalRowList = 0; indexInTheTotalRowList < existingYCoordinate.size(); indexInTheTotalRowList++) {
            for (int indexInTheParticularRow = 0; indexInTheParticularRow < boardUnitsInTheSameRow.get(indexInTheTotalRowList).size(); indexInTheParticularRow++) {
                BoardUnit currentBoardUnit = boardUnitsInTheSameRow.get(indexInTheTotalRowList).get(indexInTheParticularRow);
                if(indexInTheParticularRow==0&&indexInTheTotalRowList==0){
                    continue;
                }
                if(currentBoardUnit.getyCoordinate()==lastBoardUnit.getyCoordinate()&&currentBoardUnit.getxCoordinate()==lastBoardUnit.getxCoordinate()+1){
                    currentNeighborSet.add(currentBoardUnit);
                }else {
                    neighborBoardUnitsInRow.add(currentNeighborSet);
                    currentNeighborSet = new ArrayList<>();
                    currentNeighborSet.add(currentBoardUnit);
                }
                lastBoardUnit = boardUnitsInTheSameRow.get(indexInTheTotalRowList).get(indexInTheParticularRow);
            }
        }
        neighborBoardUnitsInRow.add(currentNeighborSet);
    }
    void GetNeighborBoardUnitInColumn(){
        ArrayList<BoardUnit> currentNeighborSet = new ArrayList<>();
        currentNeighborSet.add(boardUnitsIntheSameColumn.get(0).get(0));
        BoardUnit lastBoardUnit = boardUnitsIntheSameColumn.get(0).get(0);
        for (int indexInTheTotalColumnList = 0; indexInTheTotalColumnList < existingXCoordinate.size(); indexInTheTotalColumnList++) {
            for (int indexInTheParticularColumn = 0; indexInTheParticularColumn < boardUnitsIntheSameColumn.get(indexInTheTotalColumnList).size(); indexInTheParticularColumn++) {
                BoardUnit currentBoardUnit = boardUnitsIntheSameColumn.get(indexInTheTotalColumnList).get(indexInTheParticularColumn);
                if(indexInTheParticularColumn==0&&indexInTheTotalColumnList==0){
                    continue;
                }
                if(currentBoardUnit.getxCoordinate()==lastBoardUnit.getxCoordinate()&&currentBoardUnit.getyCoordinate()==lastBoardUnit.getyCoordinate()+1){
                    currentNeighborSet.add(currentBoardUnit);
                }else {
                    neighborBoardUnitsInColumn.add(currentNeighborSet);
                    currentNeighborSet = new ArrayList<>();
                    currentNeighborSet.add(currentBoardUnit);
                }
                lastBoardUnit = boardUnitsIntheSameColumn.get(indexInTheTotalColumnList).get(indexInTheParticularColumn);
            }
        }
        neighborBoardUnitsInColumn.add(currentNeighborSet);
    }
}
