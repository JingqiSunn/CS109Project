package GameVisual;
import GameElement.BoardUnit;
import GameElement.ControllingCenter;

import java.util.ArrayList;
import java.util.Scanner;

public class InitBoard {
    ArrayList<BoardUnit> boardInformation;
    ControllingCenter controllingCenter=new ControllingCenter();

    public void setBoardInformation(ArrayList<BoardUnit> boardInformation) {
        this.boardInformation = boardInformation;
    }

    public ArrayList<BoardUnit> getBoardInformation() {
        return boardInformation;
    }

    public void setControllingCenter(ControllingCenter controllingCenter) {
        this.controllingCenter = controllingCenter;
    }

    public ControllingCenter getControllingCenter() {
        return controllingCenter;
    }

    InitBoard() {
        Scanner fetch = new Scanner(System.in);
        controllingCenter.GetTheSetUpInformationOfTheBoard(fetch);
        controllingCenter.SetThePlayingBoard();
        boardInformation = controllingCenter.getBoardUnitInformation();
    }
}

