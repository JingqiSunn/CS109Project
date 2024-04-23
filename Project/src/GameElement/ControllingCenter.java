package GameElement;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ControllingCenter {
    private boolean gameValidity;
    private int currentGameScore;
    private Board currentPlayingBoard;


    private ArrayList<Integer> informationOfAllTheCoordinateOfTheBoardUnit;

    public ControllingCenter() {
        this.gameValidity = true;
        this.currentGameScore = 0;
        currentPlayingBoard = new Board();
        informationOfAllTheCoordinateOfTheBoardUnit=new ArrayList<>();
        currentPlayingBoard.setControllingCenter(this);
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
    public void AddTheBoard(Board targetBoard){
        targetBoard.setControllingCenter(this);
    }

    public void RightAction(){
        currentPlayingBoard.BoardRightMove();
        currentGameScore=currentPlayingBoard.getCurrentScore();
        currentPlayingBoard.RandomlyGenerateCellInEmptyBoardUnits();
    }
    public void LeftAction(){
        currentPlayingBoard.BoardLeftMove();
        currentGameScore=currentPlayingBoard.getCurrentScore();
        currentPlayingBoard.RandomlyGenerateCellInEmptyBoardUnits();
    }
    public void DownAction(){
        currentPlayingBoard.BoardDownMove();
        currentGameScore=currentPlayingBoard.getCurrentScore();
        currentPlayingBoard.RandomlyGenerateCellInEmptyBoardUnits();
    }
    public void UpAction(){
        currentPlayingBoard.BoardUpMove();
        currentGameScore=currentPlayingBoard.getCurrentScore();
        currentPlayingBoard.RandomlyGenerateCellInEmptyBoardUnits();
    }
    public void UpdateGameValidity(){
        currentPlayingBoard.ReIdentifyEmptyBoardUnits();
        boolean whetherValid = false;
        for (int indexInDirection = 0; indexInDirection < 4; indexInDirection++) {
            if(currentPlayingBoard.getAvailableDirectionSet()[indexInDirection]==1){
                whetherValid =true;
            }
        }
        gameValidity = whetherValid;
    }
}
