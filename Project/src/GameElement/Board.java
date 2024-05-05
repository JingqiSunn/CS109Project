package GameElement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Board {
    private ArrayList<ArrayList<BoardUnit>> neighborBoardUnitsInRow;
    private ArrayList<ArrayList<BoardUnit>> neighborBoardUnitsInColumn;

    private ArrayList<ArrayList<BoardUnit>> boardUnitsInTheSameRow;
    private ArrayList<ArrayList<BoardUnit>> boardUnitsIntheSameColumn;
    private int[] availableDirectionSet;
    private ArrayList<BoardUnit> emptyBoardUnitSet;
    private ArrayList<BoardUnit> boardLocationSet;
    private ControllingCenter controllingCenter;
    private ArrayList<Integer> existingXCoordinate;
    private ArrayList<Integer> existingYCoordinate;
    private int currentScore;
    private static int scoreThisTerm;

    Board() {
        existingXCoordinate = new ArrayList<>();
        existingYCoordinate = new ArrayList<>();
        boardUnitsInTheSameRow = new ArrayList<>();
        boardUnitsIntheSameColumn = new ArrayList<>();
        this.boardLocationSet = new ArrayList<>();
        controllingCenter = null;
        neighborBoardUnitsInColumn = new ArrayList<>();
        neighborBoardUnitsInRow = new ArrayList<>();
        emptyBoardUnitSet = new ArrayList<>();
        availableDirectionSet = new int[4];
        Arrays.fill(availableDirectionSet, 0);
        currentScore = 0;
        scoreThisTerm = 0;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    ArrayList<BoardUnit> getBoardLocationSet() {
        return boardLocationSet;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    ControllingCenter getControllingCenter() {
        return controllingCenter;
    }

    public int[] getAvailableDirectionSet() {
        return availableDirectionSet;
    }

    public void setBoardLocationSet(ArrayList<BoardUnit> boardLocationSet) {
        this.boardLocationSet = boardLocationSet;
    }

    void setControllingCenter(ControllingCenter controllingCenter) {
        this.controllingCenter = controllingCenter;
    }

    void addBoardUnitToCurrentBoard(BoardUnit additionBoardUnit) {
        boardLocationSet.add(additionBoardUnit);
    }

    Board SetThePlayingBoard(ArrayList<Integer> theSetOfLocationOfBoardUnit) {
        boolean whetherValidInput = true;
        Boolean whetherAlreadyExistX = false;
        Boolean whetherAlreadyExistY = false;
        for (int locationInTheSet = 0; locationInTheSet < theSetOfLocationOfBoardUnit.size(); locationInTheSet++) {
            if (theSetOfLocationOfBoardUnit.get(locationInTheSet) < 0) {
                whetherValidInput = false;
                System.out.println("The Coordinate you offered is invalid, there can't be negative number.");
                return this;
            }
        }
        if (theSetOfLocationOfBoardUnit.size() % 2 != 0) {
            whetherValidInput = false;
            System.out.println("The Coordinate you offered is invalid, the total number should be even");
            return this;
        } else {
            for (int locationInTheInputPair = 0; locationInTheInputPair < theSetOfLocationOfBoardUnit.size() / 2; locationInTheInputPair++) {
                boardLocationSet.add(new BoardUnit(theSetOfLocationOfBoardUnit.get(2 * locationInTheInputPair), theSetOfLocationOfBoardUnit.get(2 * locationInTheInputPair + 1)));
                boardLocationSet.get(locationInTheInputPair).setCurrentPlayingBoard(this);
                if (locationInTheInputPair == 0) {
                    existingXCoordinate.add(theSetOfLocationOfBoardUnit.get(0));
                    existingYCoordinate.add(theSetOfLocationOfBoardUnit.get(1));
                    continue;
                }

                for (int indexInTheExistingSet = 0; indexInTheExistingSet < existingXCoordinate.size(); indexInTheExistingSet++) {
                    if (existingXCoordinate.get(indexInTheExistingSet).equals(theSetOfLocationOfBoardUnit.get(2 * locationInTheInputPair))) {
                        whetherAlreadyExistX = true;
                        break;
                    }
                }
                for (int indexInTheExistingSet = 0; indexInTheExistingSet < existingYCoordinate.size(); indexInTheExistingSet++) {
                    if (existingYCoordinate.get(indexInTheExistingSet).equals(theSetOfLocationOfBoardUnit.get(2 * locationInTheInputPair + 1))) {
                        whetherAlreadyExistY = true;
                        break;
                    }
                }
                if (!whetherAlreadyExistX) {
                    existingXCoordinate.add(theSetOfLocationOfBoardUnit.get(2 * locationInTheInputPair));
                }
                if (!whetherAlreadyExistY) {
                    existingYCoordinate.add(theSetOfLocationOfBoardUnit.get(2 * locationInTheInputPair + 1));
                }
                whetherAlreadyExistX = false;
                whetherAlreadyExistY = false;
            }
        }
        emptyBoardUnitSet = boardLocationSet;
        return this;
    }

    static Board RemoveTheSameBoardUnit(Board originalBoard) {
        Board theCopyOfTheOriginalBoard = originalBoard;
        for (int indexInTheTotalUnit = 0; indexInTheTotalUnit < originalBoard.getBoardLocationSet().size(); indexInTheTotalUnit++) {
            for (int indexInRestTotalUnit = indexInTheTotalUnit + 1; indexInRestTotalUnit < originalBoard.getBoardLocationSet().size(); indexInRestTotalUnit++)
                if (BoardUnit.whetherTwoBoardUnitsTheSameWhenSetUp(theCopyOfTheOriginalBoard.getBoardLocationSet().get(indexInTheTotalUnit), theCopyOfTheOriginalBoard.getBoardLocationSet().get(indexInRestTotalUnit)) == true) {
                    theCopyOfTheOriginalBoard.getBoardLocationSet().remove(indexInRestTotalUnit);
                    indexInRestTotalUnit--;
                }
        }
        return theCopyOfTheOriginalBoard;
    }

    static BoardUnit GetTheLeftBoundOfTheNeighborBoardUnitSet(ArrayList<BoardUnit> targetBoardUnitList) {
        if (targetBoardUnitList.isEmpty()) {
            return null;
        }
        BoardUnit currentLeftBound = targetBoardUnitList.get(0);
        for (int indexInTheSet = 0; indexInTheSet < targetBoardUnitList.size(); indexInTheSet++) {
            if (targetBoardUnitList.get(indexInTheSet).getxCoordinate() < currentLeftBound.getxCoordinate()) {
                currentLeftBound = targetBoardUnitList.get(indexInTheSet);
            }
        }
        return currentLeftBound;
    }

    static BoardUnit GetTheRightBoundOfTheNeighborBoardUnitSet(ArrayList<BoardUnit> targetBoardUnitList) {
        if (targetBoardUnitList.isEmpty()) {
            return null;
        }
        BoardUnit currentLeftBound = targetBoardUnitList.get(0);
        for (int indexInTheSet = 0; indexInTheSet < targetBoardUnitList.size(); indexInTheSet++) {
            if (targetBoardUnitList.get(indexInTheSet).getxCoordinate() > currentLeftBound.getxCoordinate()) {
                currentLeftBound = targetBoardUnitList.get(indexInTheSet);
            }
        }
        return currentLeftBound;
    }

    static BoardUnit GetTheUpBoundOfTheNeighborBoardUnitSet(ArrayList<BoardUnit> targetBoardUnitList) {
        if (targetBoardUnitList.isEmpty()) {
            return null;
        }
        BoardUnit currentLeftBound = targetBoardUnitList.get(0);
        for (int indexInTheSet = 0; indexInTheSet < targetBoardUnitList.size(); indexInTheSet++) {
            if (targetBoardUnitList.get(indexInTheSet).getyCoordinate() > currentLeftBound.getyCoordinate()) {
                currentLeftBound = targetBoardUnitList.get(indexInTheSet);
            }
        }
        return currentLeftBound;
    }

    static BoardUnit GetTheDownBoundOfTheNeighborBoardUnitSet(ArrayList<BoardUnit> targetBoardUnitList) {
        if (targetBoardUnitList.isEmpty()) {
            return null;
        }
        BoardUnit currentLeftBound = targetBoardUnitList.get(0);
        for (int indexInTheSet = 0; indexInTheSet < targetBoardUnitList.size(); indexInTheSet++) {
            if (targetBoardUnitList.get(indexInTheSet).getyCoordinate() < currentLeftBound.getyCoordinate()) {
                currentLeftBound = targetBoardUnitList.get(indexInTheSet);
            }
        }
        return currentLeftBound;
    }


    void GetBoardUnitsInTheSameRow() {
        ArrayList<Integer> copyOfExistingYCoordinateAfterSort = new ArrayList<>();
        copyOfExistingYCoordinateAfterSort = existingYCoordinate;
        Collections.sort(copyOfExistingYCoordinateAfterSort);
        for (int indexInTheTotalRow = 0; indexInTheTotalRow < copyOfExistingYCoordinateAfterSort.size(); indexInTheTotalRow++) {
            ArrayList<BoardUnit> setOfBoardUnitInTheSameRow = new ArrayList<>();
            for (int sequenceInBoardLocationSet = 0; sequenceInBoardLocationSet < boardLocationSet.size(); sequenceInBoardLocationSet++) {
                if (boardLocationSet.get(sequenceInBoardLocationSet).getyCoordinate() == copyOfExistingYCoordinateAfterSort.get(indexInTheTotalRow)) {
                    setOfBoardUnitInTheSameRow.add(boardLocationSet.get(sequenceInBoardLocationSet));
                }
            }
            setOfBoardUnitInTheSameRow = BoardUnit.SortTheBoardUnitSetInTheSameRowByColumn(setOfBoardUnitInTheSameRow);
            boardUnitsInTheSameRow.add(setOfBoardUnitInTheSameRow);
        }
    }

    void GetBoardUnitsInTheSameColumn() {
        ArrayList<Integer> copyOfExistingXCoordinateAfterSort = new ArrayList<>();
        copyOfExistingXCoordinateAfterSort = existingXCoordinate;
        Collections.sort(copyOfExistingXCoordinateAfterSort);
        for (int indexInTheTotalColumn = 0; indexInTheTotalColumn < copyOfExistingXCoordinateAfterSort.size(); indexInTheTotalColumn++) {
            ArrayList<BoardUnit> setOfBoardUnitInTheSameColumn = new ArrayList<>();
            for (int sequenceInBoardLocationSet = 0; sequenceInBoardLocationSet < boardLocationSet.size(); sequenceInBoardLocationSet++) {
                if (boardLocationSet.get(sequenceInBoardLocationSet).getxCoordinate() == copyOfExistingXCoordinateAfterSort.get(indexInTheTotalColumn)) {
                    setOfBoardUnitInTheSameColumn.add(boardLocationSet.get(sequenceInBoardLocationSet));
                }
            }
            setOfBoardUnitInTheSameColumn = BoardUnit.SortTheBoardUnitSetInTheSameColumnByRow(setOfBoardUnitInTheSameColumn);
            boardUnitsIntheSameColumn.add(setOfBoardUnitInTheSameColumn);
        }
    }

    void GetNeighborBoardUnitInRow() {
        ArrayList<BoardUnit> currentNeighborSet = new ArrayList<>();
        currentNeighborSet.add(boardUnitsInTheSameRow.get(0).get(0));
        BoardUnit lastBoardUnit = boardUnitsInTheSameRow.get(0).get(0);
        for (int indexInTheTotalRowList = 0; indexInTheTotalRowList < existingYCoordinate.size(); indexInTheTotalRowList++) {
            for (int indexInTheParticularRow = 0; indexInTheParticularRow < boardUnitsInTheSameRow.get(indexInTheTotalRowList).size(); indexInTheParticularRow++) {
                BoardUnit currentBoardUnit = boardUnitsInTheSameRow.get(indexInTheTotalRowList).get(indexInTheParticularRow);
                if (indexInTheParticularRow == 0 && indexInTheTotalRowList == 0) {
                    continue;
                }
                if (currentBoardUnit.getyCoordinate() == lastBoardUnit.getyCoordinate() && currentBoardUnit.getxCoordinate() == lastBoardUnit.getxCoordinate() + 1) {
                    currentNeighborSet.add(currentBoardUnit);
                } else {
                    neighborBoardUnitsInRow.add(currentNeighborSet);
                    currentNeighborSet = new ArrayList<>();
                    currentNeighborSet.add(currentBoardUnit);
                }
                lastBoardUnit = boardUnitsInTheSameRow.get(indexInTheTotalRowList).get(indexInTheParticularRow);
            }
        }
        neighborBoardUnitsInRow.add(currentNeighborSet);
    }

    void GetNeighborBoardUnitInColumn() {
        ArrayList<BoardUnit> currentNeighborSet = new ArrayList<>();
        currentNeighborSet.add(boardUnitsIntheSameColumn.get(0).get(0));
        BoardUnit lastBoardUnit = boardUnitsIntheSameColumn.get(0).get(0);
        for (int indexInTheTotalColumnList = 0; indexInTheTotalColumnList < existingXCoordinate.size(); indexInTheTotalColumnList++) {
            for (int indexInTheParticularColumn = 0; indexInTheParticularColumn < boardUnitsIntheSameColumn.get(indexInTheTotalColumnList).size(); indexInTheParticularColumn++) {
                BoardUnit currentBoardUnit = boardUnitsIntheSameColumn.get(indexInTheTotalColumnList).get(indexInTheParticularColumn);
                if (indexInTheParticularColumn == 0 && indexInTheTotalColumnList == 0) {
                    continue;
                }
                if (currentBoardUnit.getxCoordinate() == lastBoardUnit.getxCoordinate() && currentBoardUnit.getyCoordinate() == lastBoardUnit.getyCoordinate() + 1) {
                    currentNeighborSet.add(currentBoardUnit);
                } else {
                    neighborBoardUnitsInColumn.add(currentNeighborSet);
                    currentNeighborSet = new ArrayList<>();
                    currentNeighborSet.add(currentBoardUnit);
                }
                lastBoardUnit = boardUnitsIntheSameColumn.get(indexInTheTotalColumnList).get(indexInTheParticularColumn);
            }
        }
        neighborBoardUnitsInColumn.add(currentNeighborSet);
    }

    boolean WhetherCanBeEliminated(ArrayList<BoardUnit> targetBoardUnitList) {
        boolean EliminationValidity = false;
        for (int indexInTheTargetBoardUnitList = 0; indexInTheTargetBoardUnitList < targetBoardUnitList.size() - 1; indexInTheTargetBoardUnitList++) {
            if (targetBoardUnitList.get(indexInTheTargetBoardUnitList).getCell() != null && targetBoardUnitList.get(indexInTheTargetBoardUnitList + 1).getCell() != null) {
                if (targetBoardUnitList.get(indexInTheTargetBoardUnitList).getCell().getValue() == targetBoardUnitList.get(indexInTheTargetBoardUnitList + 1).getCell().getValue()) {
                    EliminationValidity = true;
                }
            }
        }
        return EliminationValidity;
    }

    boolean WhetherAlreadyFullyArrangedOnRightForARow(ArrayList<BoardUnit> neighborBoardUnitInTheSameRow) {
        boolean whetherFullyArrangedOnRight = true;
        if (neighborBoardUnitInTheSameRow.size() == 1) {
            whetherFullyArrangedOnRight = true;
            return whetherFullyArrangedOnRight;
        }
        boolean whetherAllEmpty = true;
        for (int indexInTheArrayList = 0; indexInTheArrayList < neighborBoardUnitInTheSameRow.size(); indexInTheArrayList++) {
            if (neighborBoardUnitInTheSameRow.get(indexInTheArrayList).getCell() != null) {
                whetherAllEmpty = false;
                break;
            }
        }
        if (whetherAllEmpty == true) {
            whetherFullyArrangedOnRight = true;
        }
        if (whetherAllEmpty == false) {
            for (int indexInTheArrayList = 0; indexInTheArrayList < neighborBoardUnitInTheSameRow.size() - 1; indexInTheArrayList++) {
                if (neighborBoardUnitInTheSameRow.get(indexInTheArrayList).getCell() != null && neighborBoardUnitInTheSameRow.get(indexInTheArrayList + 1).getCell() == null) {
                    whetherFullyArrangedOnRight = false;
                }
            }
        }
        return whetherFullyArrangedOnRight;
    }

    boolean WhetherAlreadyFullyArrangedOnLeftForARow(ArrayList<BoardUnit> neighborBoardUnitInTheSameRow) {
        boolean whetherFullyArrangedOnLeft = true;
        if (neighborBoardUnitInTheSameRow.size() == 1) {
            whetherFullyArrangedOnLeft = true;
            return whetherFullyArrangedOnLeft;
        }
        boolean whetherAllEmpty = true;
        for (int indexInTheArrayList = 0; indexInTheArrayList < neighborBoardUnitInTheSameRow.size(); indexInTheArrayList++) {
            if (neighborBoardUnitInTheSameRow.get(indexInTheArrayList).getCell() != null) {
                whetherAllEmpty = false;
                break;
            }
        }
        if (whetherAllEmpty == true) {
            whetherFullyArrangedOnLeft = true;
        }
        if (whetherAllEmpty == false) {
            for (int indexInTheArrayList = neighborBoardUnitInTheSameRow.size() - 1; indexInTheArrayList > 0; indexInTheArrayList--) {
                if (neighborBoardUnitInTheSameRow.get(indexInTheArrayList).getCell() != null && neighborBoardUnitInTheSameRow.get(indexInTheArrayList - 1).getCell() == null) {
                    whetherFullyArrangedOnLeft = false;
                }
            }
        }
        return whetherFullyArrangedOnLeft;
    }

    boolean WhetherAlreadyFullyArrangedByTopForAColumn(ArrayList<BoardUnit> neighborBoardUnitInTheSameColumn) {
        boolean whetherFullyArrangedByTop = true;
        if (neighborBoardUnitInTheSameColumn.size() == 1) {
            whetherFullyArrangedByTop = true;
            return whetherFullyArrangedByTop;
        }
        boolean whetherAllEmpty = true;
        for (int indexInTheArrayList = 0; indexInTheArrayList < neighborBoardUnitInTheSameColumn.size(); indexInTheArrayList++) {
            if (neighborBoardUnitInTheSameColumn.get(indexInTheArrayList).getCell() != null) {
                whetherAllEmpty = false;
                break;
            }
        }
        if (whetherAllEmpty == true) {
            whetherFullyArrangedByTop = true;
        }
        if (whetherAllEmpty == false) {
            for (int indexInTheArrayList = 0; indexInTheArrayList < neighborBoardUnitInTheSameColumn.size() - 1; indexInTheArrayList++) {
                if (neighborBoardUnitInTheSameColumn.get(indexInTheArrayList).getCell() != null && neighborBoardUnitInTheSameColumn.get(indexInTheArrayList + 1).getCell() == null) {
                    whetherFullyArrangedByTop = false;
                }
            }
        }
        return whetherFullyArrangedByTop;
    }

    boolean WhetherAlreadyFullyArrangedByBottomForAColumn(ArrayList<BoardUnit> neighborBoardUnitInTheSameColumn) {
        boolean whetherFullyArrangedByBottom = true;
        if (neighborBoardUnitInTheSameColumn.size() == 1) {
            whetherFullyArrangedByBottom = true;
            return whetherFullyArrangedByBottom;
        }
        boolean whetherAllEmpty = true;
        for (int indexInTheArrayList = 0; indexInTheArrayList < neighborBoardUnitInTheSameColumn.size(); indexInTheArrayList++) {
            if (neighborBoardUnitInTheSameColumn.get(indexInTheArrayList).getCell() != null) {
                whetherAllEmpty = false;
                break;
            }
        }
        if (whetherAllEmpty == true) {
            whetherFullyArrangedByBottom = true;
        }
        if (whetherAllEmpty == false) {
            for (int indexInTheArrayList = neighborBoardUnitInTheSameColumn.size() - 1; indexInTheArrayList > 0; indexInTheArrayList--) {
                if (neighborBoardUnitInTheSameColumn.get(indexInTheArrayList).getCell() != null && neighborBoardUnitInTheSameColumn.get(indexInTheArrayList - 1).getCell() == null) {
                    whetherFullyArrangedByBottom = false;
                }
            }
        }
        return whetherFullyArrangedByBottom;
    }

    void UpdateTheValidityForEveryDirection() {
        Arrays.fill(availableDirectionSet, 0);
        for (int indexInNeighborBoardUnitsInRow = 0; indexInNeighborBoardUnitsInRow < neighborBoardUnitsInRow.size(); indexInNeighborBoardUnitsInRow++) {
            if (this.WhetherAlreadyFullyArrangedOnLeftForARow(neighborBoardUnitsInRow.get(indexInNeighborBoardUnitsInRow)) == false || this.WhetherCanBeEliminated(neighborBoardUnitsInRow.get(indexInNeighborBoardUnitsInRow)) == true) {
                availableDirectionSet[2] = 1;
                break;
            }
        }
        for (int indexInNeighborBoardUnitsInRow = 0; indexInNeighborBoardUnitsInRow < neighborBoardUnitsInRow.size(); indexInNeighborBoardUnitsInRow++) {
            if (this.WhetherAlreadyFullyArrangedOnRightForARow(neighborBoardUnitsInRow.get(indexInNeighborBoardUnitsInRow)) == false || this.WhetherCanBeEliminated(neighborBoardUnitsInRow.get(indexInNeighborBoardUnitsInRow)) == true) {
                availableDirectionSet[3] = 1;
                break;
            }
        }
        for (int indexInNeighborBoardUnitsInColumn = 0; indexInNeighborBoardUnitsInColumn < neighborBoardUnitsInColumn.size(); indexInNeighborBoardUnitsInColumn++) {
            if (this.WhetherAlreadyFullyArrangedByTopForAColumn(neighborBoardUnitsInColumn.get(indexInNeighborBoardUnitsInColumn)) == false || this.WhetherCanBeEliminated(neighborBoardUnitsInColumn.get(indexInNeighborBoardUnitsInColumn)) == true) {
                availableDirectionSet[0] = 1;
                break;
            }
        }
        for (int indexInNeighborBoardUnitsInColumn = 0; indexInNeighborBoardUnitsInColumn < neighborBoardUnitsInColumn.size(); indexInNeighborBoardUnitsInColumn++) {
            if (this.WhetherAlreadyFullyArrangedByBottomForAColumn(neighborBoardUnitsInColumn.get(indexInNeighborBoardUnitsInColumn)) == false || this.WhetherCanBeEliminated(neighborBoardUnitsInColumn.get(indexInNeighborBoardUnitsInColumn)) == true) {
                availableDirectionSet[1] = 1;
                break;
            }
        }
    }

    void ReIdentifyEmptyBoardUnits() {
        emptyBoardUnitSet = new ArrayList<>();
        for (int indexInBoardLocationSet = 0; indexInBoardLocationSet < boardLocationSet.size(); indexInBoardLocationSet++) {
            if (boardLocationSet.get(indexInBoardLocationSet).getCell() == null) {
                emptyBoardUnitSet.add(boardLocationSet.get(indexInBoardLocationSet));
            }
        }
    }

    static ArrayList<BoardUnit> GetItFullyRightArranged(ArrayList<BoardUnit> originalBoardUnitSet) {
        ArrayList<BoardUnit> copyOfOriginalBoardUnitSet = new ArrayList<>();
        copyOfOriginalBoardUnitSet = originalBoardUnitSet;
        ArrayList<Cell> recordOfExistingCell = new ArrayList<>();
        for (int indexInTheCopyOfOriginalBoardUnitSet = 0; indexInTheCopyOfOriginalBoardUnitSet < copyOfOriginalBoardUnitSet.size(); indexInTheCopyOfOriginalBoardUnitSet++) {
            if (copyOfOriginalBoardUnitSet.get(indexInTheCopyOfOriginalBoardUnitSet).getCell() != null) {
                recordOfExistingCell.add(copyOfOriginalBoardUnitSet.get(indexInTheCopyOfOriginalBoardUnitSet).getCell());
                copyOfOriginalBoardUnitSet.get(indexInTheCopyOfOriginalBoardUnitSet).setCell(null);
            }
        }
        for (int indexInTheRecordOfExistingCell = recordOfExistingCell.size() - 1; indexInTheRecordOfExistingCell > -1; indexInTheRecordOfExistingCell--) {
            int sequenceInTheOriginalSet = copyOfOriginalBoardUnitSet.size() - (recordOfExistingCell.size() - 1 - indexInTheRecordOfExistingCell) - 1;
            copyOfOriginalBoardUnitSet.get(sequenceInTheOriginalSet).setCell(recordOfExistingCell.get(indexInTheRecordOfExistingCell));
        }
        return copyOfOriginalBoardUnitSet;
    }

    static ArrayList<BoardUnit> GetItFullyLeftArranged(ArrayList<BoardUnit> originalBoardUnitSet) {
        ArrayList<BoardUnit> copyOfOriginalBoardUnitSet = new ArrayList<>();
        copyOfOriginalBoardUnitSet = originalBoardUnitSet;
        ArrayList<Cell> recordOfExistingCell = new ArrayList<>();
        for (int indexInTheCopyOfOriginalBoardUnitSet = 0; indexInTheCopyOfOriginalBoardUnitSet < copyOfOriginalBoardUnitSet.size(); indexInTheCopyOfOriginalBoardUnitSet++) {
            if (copyOfOriginalBoardUnitSet.get(indexInTheCopyOfOriginalBoardUnitSet).getCell() != null) {
                recordOfExistingCell.add(copyOfOriginalBoardUnitSet.get(indexInTheCopyOfOriginalBoardUnitSet).getCell());
                copyOfOriginalBoardUnitSet.get(indexInTheCopyOfOriginalBoardUnitSet).setCell(null);
            }
        }
        for (int indexInTheRecordOfExistingCell = 0; indexInTheRecordOfExistingCell < recordOfExistingCell.size(); indexInTheRecordOfExistingCell++) {
            int sequenceInTheOriginalSet = indexInTheRecordOfExistingCell;
            copyOfOriginalBoardUnitSet.get(sequenceInTheOriginalSet).setCell(recordOfExistingCell.get(indexInTheRecordOfExistingCell));
        }
        return copyOfOriginalBoardUnitSet;
    }

    static ArrayList<BoardUnit> GetItFullyTopArranged(ArrayList<BoardUnit> originalBoardUnitSet) {
        ArrayList<BoardUnit> copyOfOriginalBoardUnitSet = new ArrayList<>();
        copyOfOriginalBoardUnitSet = originalBoardUnitSet;
        ArrayList<Cell> recordOfExistingCell = new ArrayList<>();
        for (int indexInTheCopyOfOriginalBoardUnitSet = 0; indexInTheCopyOfOriginalBoardUnitSet < copyOfOriginalBoardUnitSet.size(); indexInTheCopyOfOriginalBoardUnitSet++) {
            if (copyOfOriginalBoardUnitSet.get(indexInTheCopyOfOriginalBoardUnitSet).getCell() != null) {
                recordOfExistingCell.add(copyOfOriginalBoardUnitSet.get(indexInTheCopyOfOriginalBoardUnitSet).getCell());
                copyOfOriginalBoardUnitSet.get(indexInTheCopyOfOriginalBoardUnitSet).setCell(null);
            }
        }
        for (int indexInTheRecordOfExistingCell = recordOfExistingCell.size() - 1; indexInTheRecordOfExistingCell > -1; indexInTheRecordOfExistingCell--) {
            int sequenceInTheOriginalSet = copyOfOriginalBoardUnitSet.size() - (recordOfExistingCell.size() - 1 - indexInTheRecordOfExistingCell) - 1;
            copyOfOriginalBoardUnitSet.get(sequenceInTheOriginalSet).setCell(recordOfExistingCell.get(indexInTheRecordOfExistingCell));
        }
        return copyOfOriginalBoardUnitSet;
    }

    static ArrayList<BoardUnit> GetItFullyBottomArranged(ArrayList<BoardUnit> originalBoardUnitSet) {
        ArrayList<BoardUnit> copyOfOriginalBoardUnitSet = new ArrayList<>();
        copyOfOriginalBoardUnitSet = originalBoardUnitSet;
        ArrayList<Cell> recordOfExistingCell = new ArrayList<>();
        for (int indexInTheCopyOfOriginalBoardUnitSet = 0; indexInTheCopyOfOriginalBoardUnitSet < copyOfOriginalBoardUnitSet.size(); indexInTheCopyOfOriginalBoardUnitSet++) {
            if (copyOfOriginalBoardUnitSet.get(indexInTheCopyOfOriginalBoardUnitSet).getCell() != null) {
                recordOfExistingCell.add(copyOfOriginalBoardUnitSet.get(indexInTheCopyOfOriginalBoardUnitSet).getCell());
                copyOfOriginalBoardUnitSet.get(indexInTheCopyOfOriginalBoardUnitSet).setCell(null);
            }
        }
        for (int indexInTheRecordOfExistingCell = 0; indexInTheRecordOfExistingCell < recordOfExistingCell.size(); indexInTheRecordOfExistingCell++) {
            int sequenceInTheOriginalSet = indexInTheRecordOfExistingCell;
            copyOfOriginalBoardUnitSet.get(sequenceInTheOriginalSet).setCell(recordOfExistingCell.get(indexInTheRecordOfExistingCell));
        }
        return copyOfOriginalBoardUnitSet;
    }

    void RandomlyGenerateCellInEmptyBoardUnits() {
        this.ReIdentifyEmptyBoardUnits();
        int randomValueForTheNextRandomCell = 0;
        Random random = new Random();
        if (emptyBoardUnitSet != null) {
            randomValueForTheNextRandomCell = random.nextBoolean() ? 2 : 4;
        }
        if (emptyBoardUnitSet != null) {
            int randomIndexInTheEmptyBoardSet = random.nextInt(emptyBoardUnitSet.size());
            Cell randomNewCell = new Cell(randomValueForTheNextRandomCell, emptyBoardUnitSet.get(randomIndexInTheEmptyBoardSet));
            emptyBoardUnitSet.get(randomIndexInTheEmptyBoardSet).setCell(randomNewCell);
            ReIdentifyEmptyBoardUnits();
        }
    }

    static ArrayList<BoardUnit> DeleteAllTheCellWithTrue(ArrayList<BoardUnit> originalSetOFBoardUnit) {
        ArrayList<BoardUnit> originalBoardUnitSetAfterFullyArranged = new ArrayList<>();
        originalBoardUnitSetAfterFullyArranged = originalSetOFBoardUnit;
        for (int indexInTheOriginalSetOfBoardUnit = 0; indexInTheOriginalSetOfBoardUnit < originalBoardUnitSetAfterFullyArranged.size(); indexInTheOriginalSetOfBoardUnit++) {
            if (originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalSetOfBoardUnit).getCell() != null && originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalSetOfBoardUnit).getCell().getWhetherNeedToDisappear()) {
                originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalSetOfBoardUnit).setCell(null);
            }
        }
        return originalBoardUnitSetAfterFullyArranged;
    }

    static ArrayList<BoardUnit> FullyEliminateWhenRightwards(ArrayList<BoardUnit> originalBoardUnitSet) {
        scoreThisTerm = 0;
        ArrayList<BoardUnit> originalBoardUnitSetAfterFullyArranged = new ArrayList<>();
        originalBoardUnitSetAfterFullyArranged = originalBoardUnitSet;
        int indexWhenItStartsToHaveCell = 0;
        boolean whetherContinue = true;
        boolean whetherempty = true;
        Boolean whetherSkip = false;
        for (int indexInTheOriginalBoardUnit = 0; indexInTheOriginalBoardUnit < originalBoardUnitSet.size(); indexInTheOriginalBoardUnit++) {
            if (originalBoardUnitSet.get(indexInTheOriginalBoardUnit).getCell() != null) {
                whetherempty = false;
                originalBoardUnitSetAfterFullyArranged = new ArrayList<>();
                break;
            }
        }
        if (!whetherempty) {
            originalBoardUnitSetAfterFullyArranged = Board.GetItFullyRightArranged(originalBoardUnitSet);
            for (int indexInTheOriginalBoardUnitSetAfterFullyArranged = 0; indexInTheOriginalBoardUnitSetAfterFullyArranged < originalBoardUnitSetAfterFullyArranged.size(); indexInTheOriginalBoardUnitSetAfterFullyArranged++) {
                if (originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell() != null) {
                    indexWhenItStartsToHaveCell = indexInTheOriginalBoardUnitSetAfterFullyArranged;
                    break;
                }
            }
            for (int indexInTheOriginalBoardUnitSetAfterFullyArranged = originalBoardUnitSetAfterFullyArranged.size() - 1; indexInTheOriginalBoardUnitSetAfterFullyArranged > indexWhenItStartsToHaveCell; indexInTheOriginalBoardUnitSetAfterFullyArranged--) {
                if(whetherSkip){
                    whetherSkip = false;
                    continue;
                }
                if (originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell().getValue() == originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged - 1).getCell().getValue()) {
                    int originalValue = originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell().getValue();
                    originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell().setValue(originalValue * 2);
                    originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged - 1).getCell().setWhetherNeedToDisappear(true);
                    scoreThisTerm += originalValue*2;
                    whetherSkip = true;
                }
            }
            originalBoardUnitSetAfterFullyArranged = Board.DeleteAllTheCellWithTrue(originalBoardUnitSetAfterFullyArranged);
            originalBoardUnitSetAfterFullyArranged = Board.GetItFullyRightArranged(originalBoardUnitSetAfterFullyArranged);
        }
        return originalBoardUnitSetAfterFullyArranged;
    }

    static ArrayList<BoardUnit> FullyEliminateWhenLeftwards(ArrayList<BoardUnit> originalBoardUnitSet) {
        scoreThisTerm = 0;
        ArrayList<BoardUnit> originalBoardUnitSetAfterFullyArranged = new ArrayList<>();
        originalBoardUnitSetAfterFullyArranged = originalBoardUnitSet;
        int indexWhenItStartsToHaveCell = 0;
        boolean whetherContinue = true;
        boolean whetherempty = true;
        Boolean whetherSkip = false;
        for (int indexInTheOriginalBoardUnit = 0; indexInTheOriginalBoardUnit < originalBoardUnitSet.size(); indexInTheOriginalBoardUnit++) {
            if (originalBoardUnitSet.get(indexInTheOriginalBoardUnit).getCell() != null) {
                whetherempty = false;
                originalBoardUnitSetAfterFullyArranged = new ArrayList<>();
                break;
            }
        }
        if (!whetherempty) {
            originalBoardUnitSetAfterFullyArranged = Board.GetItFullyLeftArranged(originalBoardUnitSet);
            for (int indexInTheOriginalBoardUnitSetAfterFullyArranged = originalBoardUnitSetAfterFullyArranged.size() - 1; indexInTheOriginalBoardUnitSetAfterFullyArranged > 0; indexInTheOriginalBoardUnitSetAfterFullyArranged--) {
                if (originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell() != null) {
                    indexWhenItStartsToHaveCell = indexInTheOriginalBoardUnitSetAfterFullyArranged;
                    break;
                }
            }
            for (int indexInTheOriginalBoardUnitSetAfterFullyArranged = 0; indexInTheOriginalBoardUnitSetAfterFullyArranged < indexWhenItStartsToHaveCell; indexInTheOriginalBoardUnitSetAfterFullyArranged++) {
                if(whetherSkip){
                    whetherSkip = false;
                    continue;
                }
                if (originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell().getValue() == originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged + 1).getCell().getValue()) {
                    int originalValue = originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell().getValue();
                    originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell().setValue(originalValue * 2);
                    originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged + 1).getCell().setWhetherNeedToDisappear(true);
                    scoreThisTerm += originalValue*2;
                    whetherSkip = true;
                }
            }
            originalBoardUnitSetAfterFullyArranged = Board.DeleteAllTheCellWithTrue(originalBoardUnitSetAfterFullyArranged);
            originalBoardUnitSetAfterFullyArranged = Board.GetItFullyLeftArranged(originalBoardUnitSetAfterFullyArranged);
        }
        return originalBoardUnitSetAfterFullyArranged;
    }

    static ArrayList<BoardUnit> FullyEliminateWhenUpwards(ArrayList<BoardUnit> originalBoardUnitSet) {
        scoreThisTerm = 0;
        ArrayList<BoardUnit> originalBoardUnitSetAfterFullyArranged = new ArrayList<>();
        originalBoardUnitSetAfterFullyArranged = originalBoardUnitSet;
        int indexWhenItStartsToHaveCell = 0;
        boolean whetherContinue = true;
        boolean whetherempty = true;
        Boolean whetherSkip = false;
        for (int indexInTheOriginalBoardUnit = 0; indexInTheOriginalBoardUnit < originalBoardUnitSet.size(); indexInTheOriginalBoardUnit++) {
            if (originalBoardUnitSet.get(indexInTheOriginalBoardUnit).getCell() != null) {
                whetherempty = false;
                originalBoardUnitSetAfterFullyArranged = new ArrayList<>();
                break;
            }
        }
        if (!whetherempty) {
            originalBoardUnitSetAfterFullyArranged = Board.GetItFullyTopArranged(originalBoardUnitSet);
            for (int indexInTheOriginalBoardUnitSetAfterFullyArranged = 0; indexInTheOriginalBoardUnitSetAfterFullyArranged < originalBoardUnitSetAfterFullyArranged.size(); indexInTheOriginalBoardUnitSetAfterFullyArranged++) {
                if (originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell() != null) {
                    indexWhenItStartsToHaveCell = indexInTheOriginalBoardUnitSetAfterFullyArranged;
                    break;
                }
            }
            for (int indexInTheOriginalBoardUnitSetAfterFullyArranged = originalBoardUnitSetAfterFullyArranged.size() - 1; indexInTheOriginalBoardUnitSetAfterFullyArranged > indexWhenItStartsToHaveCell; indexInTheOriginalBoardUnitSetAfterFullyArranged--) {
                    if(whetherSkip){
                        whetherSkip = false;
                        continue;
                    }
                    if (originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell().getValue() == originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged - 1).getCell().getValue()) {
                        int originalValue = originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell().getValue();
                        originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell().setValue(originalValue * 2);
                        originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged - 1).getCell().setWhetherNeedToDisappear(true);
                        scoreThisTerm += originalValue*2;
                        whetherSkip = true;
                    }
            }
            originalBoardUnitSetAfterFullyArranged = Board.DeleteAllTheCellWithTrue(originalBoardUnitSetAfterFullyArranged);
            originalBoardUnitSetAfterFullyArranged = Board.GetItFullyTopArranged(originalBoardUnitSetAfterFullyArranged);
        }
        return originalBoardUnitSetAfterFullyArranged;
    }

    static ArrayList<BoardUnit> FullyEliminateWhenDownwards(ArrayList<BoardUnit> originalBoardUnitSet) {
        scoreThisTerm = 0;
        ArrayList<BoardUnit> originalBoardUnitSetAfterFullyArranged = new ArrayList<>();
        originalBoardUnitSetAfterFullyArranged = originalBoardUnitSet;
        int indexWhenItStartsToHaveCell = 0;
        boolean whetherContinue = true;
        boolean whetherempty = true;
        Boolean whetherSkip = false;
        for (int indexInTheOriginalBoardUnit = 0; indexInTheOriginalBoardUnit < originalBoardUnitSet.size(); indexInTheOriginalBoardUnit++) {
            if (originalBoardUnitSet.get(indexInTheOriginalBoardUnit).getCell() != null) {
                whetherempty = false;
                originalBoardUnitSetAfterFullyArranged = new ArrayList<>();
                break;
            }
        }
        if (!whetherempty) {
            originalBoardUnitSetAfterFullyArranged = Board.GetItFullyBottomArranged(originalBoardUnitSet);
            for (int indexInTheOriginalBoardUnitSetAfterFullyArranged = originalBoardUnitSetAfterFullyArranged.size() - 1; indexInTheOriginalBoardUnitSetAfterFullyArranged > 0; indexInTheOriginalBoardUnitSetAfterFullyArranged--) {
                if (originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell() != null) {
                    indexWhenItStartsToHaveCell = indexInTheOriginalBoardUnitSetAfterFullyArranged;
                    break;
                }
            }
            for (int indexInTheOriginalBoardUnitSetAfterFullyArranged = 0; indexInTheOriginalBoardUnitSetAfterFullyArranged < indexWhenItStartsToHaveCell; indexInTheOriginalBoardUnitSetAfterFullyArranged++) {
                if(whetherSkip){
                    whetherSkip = false;
                    continue;
                }
                if (originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell().getValue() == originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged + 1).getCell().getValue()) {
                    int originalValue = originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell().getValue();
                    originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged).getCell().setValue(originalValue * 2);
                    originalBoardUnitSetAfterFullyArranged.get(indexInTheOriginalBoardUnitSetAfterFullyArranged + 1).getCell().setWhetherNeedToDisappear(true);
                    scoreThisTerm += originalValue*2;
                    whetherSkip = true;
                }
            }
            originalBoardUnitSetAfterFullyArranged = Board.DeleteAllTheCellWithTrue(originalBoardUnitSetAfterFullyArranged);
            originalBoardUnitSetAfterFullyArranged = Board.GetItFullyBottomArranged(originalBoardUnitSetAfterFullyArranged);
        }
        return originalBoardUnitSetAfterFullyArranged;
    }

    void BoardRightMove() {
        ArrayList<ArrayList<BoardUnit>> newOfNeighborBoardUnitsInRow = new ArrayList<>();
        for (int indexInTheNeighborBoardsUnitInRow = 0; indexInTheNeighborBoardsUnitInRow < neighborBoardUnitsInRow.size(); indexInTheNeighborBoardsUnitInRow++) {
            newOfNeighborBoardUnitsInRow.add(Board.FullyEliminateWhenRightwards(neighborBoardUnitsInRow.get(indexInTheNeighborBoardsUnitInRow)));
            currentScore += scoreThisTerm;
        }
        neighborBoardUnitsInRow = newOfNeighborBoardUnitsInRow;
    }

    void BoardLeftMove() {
        ArrayList<ArrayList<BoardUnit>> newOfNeighborBoardUnitsInRow = new ArrayList<>();
        for (int indexInTheNeighborBoardsUnitInRow = 0; indexInTheNeighborBoardsUnitInRow < neighborBoardUnitsInRow.size(); indexInTheNeighborBoardsUnitInRow++) {
            newOfNeighborBoardUnitsInRow.add(Board.FullyEliminateWhenLeftwards(neighborBoardUnitsInRow.get(indexInTheNeighborBoardsUnitInRow)));
            currentScore += scoreThisTerm;
        }
        neighborBoardUnitsInRow = newOfNeighborBoardUnitsInRow;
    }

    void BoardUpMove() {
        ArrayList<ArrayList<BoardUnit>> newOfNeighborBoardUnitsInColumn = new ArrayList<>();
        for (int indexInTheNeighborBoardsUnitInColumn = 0; indexInTheNeighborBoardsUnitInColumn < neighborBoardUnitsInColumn.size(); indexInTheNeighborBoardsUnitInColumn++) {
            newOfNeighborBoardUnitsInColumn.add(Board.FullyEliminateWhenUpwards(neighborBoardUnitsInColumn.get(indexInTheNeighborBoardsUnitInColumn)));
            currentScore += scoreThisTerm;
        }
        neighborBoardUnitsInColumn = newOfNeighborBoardUnitsInColumn;
    }

    void BoardDownMove() {
        ArrayList<ArrayList<BoardUnit>> newOfNeighborBoardUnitsInColumn = new ArrayList<>();
        for (int indexInTheNeighborBoardsUnitInColumn = 0; indexInTheNeighborBoardsUnitInColumn < neighborBoardUnitsInColumn.size(); indexInTheNeighborBoardsUnitInColumn++) {
            newOfNeighborBoardUnitsInColumn.add(Board.FullyEliminateWhenDownwards(neighborBoardUnitsInColumn.get(indexInTheNeighborBoardsUnitInColumn)));
            currentScore += scoreThisTerm;
        }
        neighborBoardUnitsInColumn = newOfNeighborBoardUnitsInColumn;
    }
}
