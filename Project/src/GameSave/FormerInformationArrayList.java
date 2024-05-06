package GameSave;

import GameElement.BoardUnit;
import GameElement.Cell;
import GameElement.ControllingCenter;

import java.util.ArrayList;

public class FormerInformationArrayList {
    ArrayList<FormerInformation> formerInformationArrayList;

    public FormerInformationArrayList(ArrayList<FormerInformation> formerInformationArrayList){
        this.formerInformationArrayList = formerInformationArrayList;
    }

    public ArrayList<FormerInformation> getFormerInformationArrayList() {

        return formerInformationArrayList;
    }

    public void setFormerInformationArrayList(ArrayList<FormerInformation> formerInformationArrayList) {
        this.formerInformationArrayList = formerInformationArrayList;
    }

    public void addMembersAndCreateFiles(String userName,  ArrayList<BoardUnit> currentPlayingBoard,ControllingCenter controllingCenter,int highestScore){
        ControllingCenter controllingCenterTemp = cloneControllingCenter(currentPlayingBoard,controllingCenter);
        for (int i = 0; i < formerInformationArrayList.size(); i++) {
            if (formerInformationArrayList.get(i).getUserName().equals(userName)){
                formerInformationArrayList.get(i).setControllingCenter(controllingCenterTemp);
            }else{
                FormerInformation newUser = new FormerInformation(userName,controllingCenterTemp,highestScore);
                formerInformationArrayList.add(newUser);
            }
        }
    }

    private ControllingCenter cloneControllingCenter(ArrayList<BoardUnit> currentBoard, ControllingCenter controllingCenter){
        ControllingCenter controllingCenterTemp = new ControllingCenter();
        ArrayList<Integer> cloneOfTheCoordinates = new ArrayList<>();
        for (int indexInBoardUnitSet = 0; indexInBoardUnitSet < currentBoard.size(); indexInBoardUnitSet++) {
            cloneOfTheCoordinates.add(currentBoard.get(indexInBoardUnitSet).getxCoordinate());
            cloneOfTheCoordinates.add(currentBoard.get(indexInBoardUnitSet).getyCoordinate());
        }
        controllingCenterTemp.setInformationOfAllTheCoordinateOfTheBoardUnit(cloneOfTheCoordinates);
        controllingCenterTemp.SetThePlayingBoard();
        for (int indexInTheBoardLocationSet = 0; indexInTheBoardLocationSet < controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().size(); indexInTheBoardLocationSet++) {
            if (controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().get(indexInTheBoardLocationSet).getCell()!=null){
                Cell clonedCell = new Cell(controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().get(indexInTheBoardLocationSet).getCell().getValue(),controllingCenterTemp.getCurrentPlayingBoard().getBoardLocationSet().get(indexInTheBoardLocationSet));
                controllingCenterTemp.getCurrentPlayingBoard().getBoardLocationSet().get(indexInTheBoardLocationSet).setCell(clonedCell);
            }
        }
        controllingCenterTemp.getCurrentPlayingBoard().setCurrentScore(controllingCenter.getCurrentPlayingBoard().getCurrentScore());
        return controllingCenterTemp;
    }


}
