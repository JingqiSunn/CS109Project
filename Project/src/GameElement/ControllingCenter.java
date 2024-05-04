package GameElement;

import java.util.ArrayList;
import java.util.Scanner;

public class ControllingCenter {
    private boolean gameValidity;
    private int currentGameScore;
    private Board currentPlayingBoard;

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

    private ArrayList<Integer> informationOfAllTheCoordinateOfTheBoardUnit;

    public ControllingCenter() {
        this.gameValidity = true;
        this.currentGameScore = 0;
        currentPlayingBoard = new Board();
        informationOfAllTheCoordinateOfTheBoardUnit=new ArrayList<>();
        currentPlayingBoard.setControllingCenter(this);
    }

    public void setInformationOfAllTheCoordinateOfTheBoardUnit(ArrayList<Integer> informationOfAllTheCoordinateOfTheBoardUnit) {
        this.informationOfAllTheCoordinateOfTheBoardUnit = informationOfAllTheCoordinateOfTheBoardUnit;
    }

    public void SetThePlayingBoard(){
        ArrayList<Integer> theSetOfLocationOfBoardUnit = informationOfAllTheCoordinateOfTheBoardUnit;
        Board copyOfTheInitialBoard =currentPlayingBoard.SetThePlayingBoard(theSetOfLocationOfBoardUnit);
        currentPlayingBoard = Board.RemoveTheSameBoardUnit(currentPlayingBoard);
        currentPlayingBoard.GetBoardUnitsInTheSameColumn();
        currentPlayingBoard.GetBoardUnitsInTheSameRow();
        currentPlayingBoard.GetNeighborBoardUnitInColumn();
        currentPlayingBoard.GetNeighborBoardUnitInRow();
    }
    public void GetTheSetUpInformationOfTheBoard(Scanner fetch){
        ArrayList<Integer> coordinateInformationList = new ArrayList<>();
        System.out.println("How many board unit do you want: ");
        int totalNumberOfInput = 2*fetch.nextInt();
        System.out.println("Please give me all the coordinate of board unit you want now one by one?");
        for (int sequenceInTheInformation = 0; sequenceInTheInformation < totalNumberOfInput; sequenceInTheInformation++) {
            System.out.println("What about the next?");
            coordinateInformationList.add(fetch.nextInt());
        }
        System.out.println("The information collection is over.");
        informationOfAllTheCoordinateOfTheBoardUnit = coordinateInformationList;
    }
    public ArrayList<BoardUnit> GetTheBoardUnitSet(){
        return currentPlayingBoard.getBoardLocationSet();
    }
    public void AddTheBoard(Board targetBoard){
        targetBoard.setControllingCenter(this);
    }

    public void RightAction(){
        if(currentPlayingBoard.getAvailableDirectionSet()[3]==1){
        currentPlayingBoard.BoardRightMove();
        currentGameScore=currentPlayingBoard.getCurrentScore();
        currentPlayingBoard.RandomlyGenerateCellInEmptyBoardUnits();}
    }
    public void LeftAction(){

        if(currentPlayingBoard.getAvailableDirectionSet()[2]==1){
        currentPlayingBoard.BoardLeftMove();
        currentGameScore=currentPlayingBoard.getCurrentScore();
        currentPlayingBoard.RandomlyGenerateCellInEmptyBoardUnits();}
    }
    public void DownAction(){
        if(currentPlayingBoard.getAvailableDirectionSet()[1]==1){
        currentPlayingBoard.BoardDownMove();
        currentGameScore=currentPlayingBoard.getCurrentScore();
        currentPlayingBoard.RandomlyGenerateCellInEmptyBoardUnits();}
    }
    public void UpAction(){
        if(currentPlayingBoard.getAvailableDirectionSet()[0]==1){
        currentPlayingBoard.BoardUpMove();
        currentGameScore=currentPlayingBoard.getCurrentScore();
        currentPlayingBoard.RandomlyGenerateCellInEmptyBoardUnits();}
    }

    public void setCurrentGameScore(int currentGameScore) {
        this.currentGameScore = currentGameScore;
        currentPlayingBoard.setCurrentScore(currentGameScore);
    }

    public void setGameValidity(boolean gameValidity) {
        this.gameValidity = gameValidity;
    }

    public void UpdateGameValidity(){
        currentPlayingBoard.UpdateTheValidityForEveryDirection();
        boolean whetherValid = false;
        for (int indexInDirection = 0; indexInDirection < 4; indexInDirection++) {
            if(currentPlayingBoard.getAvailableDirectionSet()[indexInDirection]==1){
                whetherValid =true;
            }
        }
        gameValidity = whetherValid;
    }
    public ArrayList <BoardUnit> getBoardUnitInformation (){
        return currentPlayingBoard.getBoardLocationSet();
    }
    public void RandomlyGenerateCellInEmptyBoardUnits(){
        currentPlayingBoard.ReIdentifyEmptyBoardUnits();
        currentPlayingBoard.RandomlyGenerateCellInEmptyBoardUnits();
    }
    public void setSpecificCellToBeNull(int indexInTheSetOfCell){
        currentPlayingBoard.getBoardLocationSet().get(indexInTheSetOfCell).setCell(null);
    }
    public void ReIdentifyEmptyBoardUnitSet(){
        this.getCurrentPlayingBoard().ReIdentifyEmptyBoardUnits();
    }
public void UpdateTheAvailableDirectionSet(){
        currentPlayingBoard.UpdateTheValidityForEveryDirection();
}
public int FindTheMaxXCoordinate(){
        int maxXCoordinate = 0;
    for (int indexInPoints = 0; indexInPoints < this.getInformationOfAllTheCoordinateOfTheBoardUnit().size()/2; indexInPoints++) {
        if(this.getInformationOfAllTheCoordinateOfTheBoardUnit().get(indexInPoints*2)>maxXCoordinate){
            maxXCoordinate = this.getInformationOfAllTheCoordinateOfTheBoardUnit().get(indexInPoints*2);
        }
    }
    return maxXCoordinate;
}
    public int FindTheMaxYCoordinate(){
        int maxYCoordinate = 0;
        for (int indexInPoints = 0; indexInPoints < this.getInformationOfAllTheCoordinateOfTheBoardUnit().size()/2; indexInPoints++) {
            if(this.getInformationOfAllTheCoordinateOfTheBoardUnit().get(indexInPoints*2+1)>maxYCoordinate){
                maxYCoordinate = this.getInformationOfAllTheCoordinateOfTheBoardUnit().get(indexInPoints*2+1);
            }
        }
        return maxYCoordinate;
    }
    public void SetUpTheControllingCenterForDIY(){
        currentPlayingBoard.SetThePlayingBoard(informationOfAllTheCoordinateOfTheBoardUnit);
        currentPlayingBoard = Board.RemoveTheSameBoardUnit(currentPlayingBoard);
        currentPlayingBoard.GetBoardUnitsInTheSameColumn();
        currentPlayingBoard.GetBoardUnitsInTheSameRow();
        currentPlayingBoard.GetNeighborBoardUnitInColumn();
        currentPlayingBoard.GetNeighborBoardUnitInRow();
    }
}

