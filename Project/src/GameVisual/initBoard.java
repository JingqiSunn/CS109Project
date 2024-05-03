package GameVisual;
import GameElement.BoardUnit;
import GameElement.ControllingCenter;

import java.util.ArrayList;
import java.util.Scanner;

public class initBoard {
    ArrayList<BoardUnit> boardInformation;
    ControllingCenter controllingCenter=new ControllingCenter();

    initBoard() {
        Scanner fetch = new Scanner(System.in);
        controllingCenter.GetTheSetUpInformationOfTheBoard(fetch);
        controllingCenter.SetThePlayingBoard();
        boardInformation = controllingCenter.getBoardUnitInformation();
    }

    public void setBoardInformation(ArrayList<BoardUnit> boardInformation) {
        this.boardInformation = boardInformation;
    }
    public void setControllingCenter(ControllingCenter controllingCenter) {
        this.controllingCenter = controllingCenter;
    }

    public ArrayList<BoardUnit> getBoardInformation() {
        return boardInformation;
    }
    public ControllingCenter getControllingCenter() {
        return controllingCenter;
    }
}

