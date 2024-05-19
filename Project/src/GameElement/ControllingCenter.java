package GameElement;

import java.util.ArrayList;
import java.util.Scanner;

public class ControllingCenter {
    private boolean gameValidity;
    private int currentGameScore;
    public int timeLimitation;
    public boolean whetherTimeLimitationMode;
    public boolean whetherAnimated;
    int targetWinningScore;
    boolean whetherReachedTheTargetScore;

    public void setCurrentPlayingBoard(Board currentPlayingBoard) {
        this.currentPlayingBoard = currentPlayingBoard;
    }

    private Board currentPlayingBoard;
    boolean whetherAlreadyShownWinningPage;
    boolean skin;
    int numberOfStep;
    boolean inCompetition;
    int scoreEarnLastMove;
    boolean whetherHasArchiveName;
    String archiveName;

    public boolean getGameValidity() {
        return gameValidity;
    }

    public int getCurrentGameScore() {
        return currentGameScore;
    }

    public Board getCurrentPlayingBoard() {
        return currentPlayingBoard;
    }

    public ArrayList<Integer> getInformationOfAllTheCoordinateOfTheBoardUnit() {
        return informationOfAllTheCoordinateOfTheBoardUnit;
    }

    public void setTargetWinningScore(int targetWinningScore) {
        this.targetWinningScore = targetWinningScore;
    }

    private ArrayList<Integer> informationOfAllTheCoordinateOfTheBoardUnit;

    public ControllingCenter() {
        archiveName = null;
        whetherHasArchiveName = false;
        scoreEarnLastMove = 0;
        inCompetition = false;
        numberOfStep = 0;
        skin = false;
        whetherAlreadyShownWinningPage = false;
        whetherReachedTheTargetScore = false;
        targetWinningScore = 2048;
        whetherAnimated = false;
        timeLimitation = 0;
        whetherTimeLimitationMode = false;
        this.gameValidity = true;
        this.currentGameScore = 0;
        currentPlayingBoard = new Board();
        informationOfAllTheCoordinateOfTheBoardUnit = new ArrayList<>();
        currentPlayingBoard.setControllingCenter(this);
    }

    public void setNumberOfStep(int numberOfStep) {
        this.numberOfStep = numberOfStep;
    }

    public String getArchiveName() {
        return archiveName;
    }

    public void setArchiveName(String archiveName) {
        this.archiveName = archiveName;
    }

    public int getScoreEarnLastMove() {
        return scoreEarnLastMove;
    }

    public boolean GetInCompetition() {
        return inCompetition;
    }

    public void setInCompetition(boolean inCompetition) {
        this.inCompetition = inCompetition;
    }

    public int getNumberOfStep() {
        return numberOfStep;
    }

    public boolean GetSkin() {
        return skin;
    }

    public void setSkin(boolean skin) {
        this.skin = skin;
    }

    public int getTargetWinningScore() {
        return targetWinningScore;
    }

    public void setWhetherAlreadyShownWinningPage(boolean whetherAlreadyShownWinningPage) {
        this.whetherAlreadyShownWinningPage = whetherAlreadyShownWinningPage;
    }

    public boolean GetWhetherReachedTheTargetScore() {
        return whetherReachedTheTargetScore;
    }

    public boolean GetWhetherAlreadyShownWinningPage() {
        return whetherAlreadyShownWinningPage;
    }

    public void setWhetherReachedTheTargetScore(boolean whetherReachedTheTargetScore) {
        this.whetherReachedTheTargetScore = whetherReachedTheTargetScore;
    }

    public void setInformationOfAllTheCoordinateOfTheBoardUnit(ArrayList<Integer> informationOfAllTheCoordinateOfTheBoardUnit) {
        this.informationOfAllTheCoordinateOfTheBoardUnit = informationOfAllTheCoordinateOfTheBoardUnit;
    }

    public void SetThePlayingBoard() {
        ArrayList<Integer> theSetOfLocationOfBoardUnit = informationOfAllTheCoordinateOfTheBoardUnit;
        Board copyOfTheInitialBoard = currentPlayingBoard.SetThePlayingBoard(theSetOfLocationOfBoardUnit);
        currentPlayingBoard = Board.RemoveTheSameBoardUnit(currentPlayingBoard);
        currentPlayingBoard.GetBoardUnitsInTheSameColumn();
        currentPlayingBoard.GetBoardUnitsInTheSameRow();
        currentPlayingBoard.GetNeighborBoardUnitInColumn();
        currentPlayingBoard.GetNeighborBoardUnitInRow();
    }

    public void GetTheSetUpInformationOfTheBoard(Scanner fetch) {
        ArrayList<Integer> coordinateInformationList = new ArrayList<>();
        System.out.println("How many board unit do you want: ");
        int totalNumberOfInput = 2 * fetch.nextInt();
        System.out.println("Please give me all the coordinate of board unit you want now one by one?");
        for (int sequenceInTheInformation = 0; sequenceInTheInformation < totalNumberOfInput; sequenceInTheInformation++) {
            System.out.println("What about the next?");
            coordinateInformationList.add(fetch.nextInt());
        }
        System.out.println("The information collection is over.");
        informationOfAllTheCoordinateOfTheBoardUnit = coordinateInformationList;
    }

    public ArrayList<BoardUnit> GetTheBoardUnitSet() {
        return currentPlayingBoard.getBoardLocationSet();
    }

    public void AddTheBoard(Board targetBoard) {
        targetBoard.setControllingCenter(this);
    }

    public void RightAction() {
        int scoreLastTerm = currentGameScore;
        if (currentPlayingBoard.getAvailableDirectionSet()[3] == 1) {
            currentPlayingBoard.BoardRightMove();
            currentGameScore = currentPlayingBoard.getCurrentScore();
            currentPlayingBoard.RandomlyGenerateCellInEmptyBoardUnits();
            numberOfStep += 1;
        }
        scoreLastTerm = currentGameScore - scoreLastTerm;
    }

    public void LeftAction() {

        int scoreLastTerm = currentGameScore;
        if (currentPlayingBoard.getAvailableDirectionSet()[2] == 1) {
            currentPlayingBoard.BoardLeftMove();
            currentGameScore = currentPlayingBoard.getCurrentScore();
            currentPlayingBoard.RandomlyGenerateCellInEmptyBoardUnits();
            numberOfStep += 1;
        }
        scoreLastTerm = currentGameScore - scoreLastTerm;

    }

    public void DownAction() {
        int scoreLastTerm = currentGameScore;
        if (currentPlayingBoard.getAvailableDirectionSet()[1] == 1) {
            currentPlayingBoard.BoardDownMove();
            currentGameScore = currentPlayingBoard.getCurrentScore();
            currentPlayingBoard.RandomlyGenerateCellInEmptyBoardUnits();
            numberOfStep += 1;
        }
        scoreLastTerm = currentGameScore - scoreLastTerm;

    }

    public void UpAction() {
        int scoreLastTerm = currentGameScore;
        if (currentPlayingBoard.getAvailableDirectionSet()[0] == 1) {
            currentPlayingBoard.BoardUpMove();
            currentGameScore = currentPlayingBoard.getCurrentScore();
            currentPlayingBoard.RandomlyGenerateCellInEmptyBoardUnits();
            numberOfStep += 1;
        }
        scoreLastTerm = currentGameScore - scoreLastTerm;

    }

    public int getTimeLimitation() {
        return timeLimitation;
    }

    public boolean GetWhetherTimeLimitationMode() {
        return whetherTimeLimitationMode;
    }

    public void setTimeLimitation(int timeLimitation) {
        this.timeLimitation = timeLimitation;
    }

    public void setWhetherTimeLimitationMode(boolean whetherTimeLimitationMode) {
        this.whetherTimeLimitationMode = whetherTimeLimitationMode;
    }

    public void setCurrentGameScore(int currentGameScore) {
        this.currentGameScore = currentGameScore;
        currentPlayingBoard.setCurrentScore(currentGameScore);
    }

    public void setGameValidity(boolean gameValidity) {
        this.gameValidity = gameValidity;
    }

    public void UpdateGameValidity() {
        currentPlayingBoard.UpdateTheValidityForEveryDirection();
        boolean whetherValid = false;
        for (int indexInDirection = 0; indexInDirection < 4; indexInDirection++) {
            if (currentPlayingBoard.getAvailableDirectionSet()[indexInDirection] == 1) {
                whetherValid = true;
            }
        }
        gameValidity = whetherValid;
    }

    public ArrayList<BoardUnit> getBoardUnitInformation() {
        return currentPlayingBoard.getBoardLocationSet();
    }

    public void RandomlyGenerateCellInEmptyBoardUnits() {
        currentPlayingBoard.ReIdentifyEmptyBoardUnits();
        currentPlayingBoard.RandomlyGenerateCellInEmptyBoardUnits();
    }

    public void RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp() {
        currentPlayingBoard.ReIdentifyEmptyBoardUnits();
        currentPlayingBoard.RandomlyGenerateTwoCellInEmptyBoardUnitsForSetUp();
    }

    public void setSpecificCellToBeNull(int indexInTheSetOfCell) {
        currentPlayingBoard.getBoardLocationSet().get(indexInTheSetOfCell).setCell(null);
    }

    public void ReIdentifyEmptyBoardUnitSet() {
        this.getCurrentPlayingBoard().ReIdentifyEmptyBoardUnits();
    }

    public void UpdateTheAvailableDirectionSet() {
        currentPlayingBoard.UpdateTheValidityForEveryDirection();
    }

    public int FindTheMaxXCoordinate() {
        int maxXCoordinate = 0;
        for (int indexInPoints = 0; indexInPoints < this.getInformationOfAllTheCoordinateOfTheBoardUnit().size() / 2; indexInPoints++) {
            if (this.getInformationOfAllTheCoordinateOfTheBoardUnit().get(indexInPoints * 2) > maxXCoordinate) {
                maxXCoordinate = this.getInformationOfAllTheCoordinateOfTheBoardUnit().get(indexInPoints * 2);
            }
        }
        return maxXCoordinate;
    }

    public int FindTheMaxYCoordinate() {
        int maxYCoordinate = 0;
        for (int indexInPoints = 0; indexInPoints < this.getInformationOfAllTheCoordinateOfTheBoardUnit().size() / 2; indexInPoints++) {
            if (this.getInformationOfAllTheCoordinateOfTheBoardUnit().get(indexInPoints * 2 + 1) > maxYCoordinate) {
                maxYCoordinate = this.getInformationOfAllTheCoordinateOfTheBoardUnit().get(indexInPoints * 2 + 1);
            }
        }
        return maxYCoordinate;
    }

    public void SetUpTheControllingCenterForDIY() {
        currentPlayingBoard.SetThePlayingBoard(informationOfAllTheCoordinateOfTheBoardUnit);
        currentPlayingBoard = Board.RemoveTheSameBoardUnit(currentPlayingBoard);
        currentPlayingBoard.GetBoardUnitsInTheSameColumn();
        currentPlayingBoard.GetBoardUnitsInTheSameRow();
        currentPlayingBoard.GetNeighborBoardUnitInColumn();
        currentPlayingBoard.GetNeighborBoardUnitInRow();
    }

    public void CleanThePlayingBoardForRestart() {
        for (int indexInBoardUnitSet = 0; indexInBoardUnitSet < currentPlayingBoard.getBoardLocationSet().size(); indexInBoardUnitSet++) {
            if (currentPlayingBoard.getBoardLocationSet().get(indexInBoardUnitSet).getCell() != null) {
                currentPlayingBoard.getBoardLocationSet().get(indexInBoardUnitSet).setCell(null);
            }
        }
        currentGameScore = 0;
        currentPlayingBoard.setCurrentScore(0);
        numberOfStep = 0;
    }

    public String GetTheValueSetForBlockUnitSet() {
        StringBuilder valueSetInString = new StringBuilder();
        for (int indexInBlockUnits = 0; indexInBlockUnits < currentPlayingBoard.getBoardLocationSet().size(); indexInBlockUnits++) {
            if (currentPlayingBoard.getBoardLocationSet().get(indexInBlockUnits).getCell() == null) {
                valueSetInString.append(String.valueOf(0) + " ");
            } else {
                valueSetInString.append(String.valueOf(currentPlayingBoard.getBoardLocationSet().get(indexInBlockUnits).getCell().getValue()) + " ");
            }
        }
        return String.valueOf(valueSetInString);
    }

    public void CleanValueOfCellInThePlayingBoard() {
        for (int indexInBoardUnitSet = 0; indexInBoardUnitSet < currentPlayingBoard.getBoardLocationSet().size(); indexInBoardUnitSet++) {
            if (currentPlayingBoard.getBoardLocationSet().get(indexInBoardUnitSet).getCell() != null) {
                currentPlayingBoard.getBoardLocationSet().get(indexInBoardUnitSet).setCell(null);
            }
        }
    }
}

