package GameElement;

import java.util.ArrayList;

public class ArtificialIntelligenceSupply {
    ControllingCenter controllingCenter;
    boolean isAlive;
    int currentScore;
    int count;
    ArrayList<BoardUnit> currentBoard;
    ControllingCenter controllingCenterTemp;
    Board board;

    public String mostScoresEarned(ControllingCenter controllingCenter) {
        this.controllingCenter = controllingCenter;
        controllingCenterTemp = new ControllingCenter();
        currentBoard = controllingCenter.getBoardUnitInformation();
        board = controllingCenter.getCurrentPlayingBoard();
        currentScore = controllingCenter.getCurrentGameScore();

        isAlive = controllingCenter.getGameValidity();
        int score1 = 0;
        int score2 = 0;
        int score3 = 0;
        int score4 = 0;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        for (int i = 0; i < 4; i++) {
            if (i == 0 && isAlive) {
                this.CloneTheControllingCenter();
                controllingCenterTemp.UpdateGameValidity();
                controllingCenterTemp.DownAction();
                score1 = controllingCenterTemp.getCurrentGameScore();
                controllingCenterTemp.UpdateGameValidity();
                /*for (int j = 0; i < 4; i++) {
                    if (isAlive) {
                        flag1 = true;
                        if (j == 0) {
                            controllingCenter.DownAction();
                            score1 = controllingCenter.getCurrentGameScore();
                        } else if (j == 1) {
                            controllingCenter.UpAction();
                            score1 = controllingCenter.getCurrentGameScore();
                        } else if (j == 2) {
                            controllingCenter.LeftAction();
                            score1 = controllingCenter.getCurrentGameScore();
                        } else if (j == 3) {
                            controllingCenter.RightAction();
                            score1 = controllingCenter.getCurrentGameScore();
                        }
                    }
                }*/
            } else if (i == 1 && isAlive) {
                this.CloneTheControllingCenter();
                controllingCenterTemp.UpdateGameValidity();
                controllingCenterTemp.UpAction();
                score2 = controllingCenterTemp.getCurrentGameScore();
                controllingCenterTemp.UpdateGameValidity();
                /*for (int j = 0; i < 4; i++) {
                    flag2 = true;
                    if (j == 0) {
                        controllingCenter.DownAction();
                        score2 = controllingCenter.getCurrentGameScore();
                    } else if (j == 1) {
                        controllingCenter.UpAction();
                        score2 = controllingCenter.getCurrentGameScore();
                    } else if (j == 2) {
                        controllingCenter.LeftAction();
                        score2 = controllingCenter.getCurrentGameScore();
                    } else if (j == 3) {
                        controllingCenter.RightAction();
                        score2 = controllingCenter.getCurrentGameScore();
                    }
                }*/
            } else if (i == 2 && isAlive) {
                this.CloneTheControllingCenter();
                controllingCenterTemp.UpdateGameValidity();
                controllingCenterTemp.LeftAction();
                score3 = controllingCenterTemp.getCurrentGameScore();
                controllingCenterTemp.UpdateGameValidity();
                /*for (int j = 0; i < 4; i++) {
                    flag3 = true;
                    if (j == 0) {
                        controllingCenter.DownAction();
                        score3 = controllingCenter.getCurrentGameScore();
                    } else if (j == 1) {
                        controllingCenter.UpAction();
                        score3 = controllingCenter.getCurrentGameScore();
                    } else if (j == 2) {
                        controllingCenter.LeftAction();
                        score3 = controllingCenter.getCurrentGameScore();
                    } else if (j == 3) {
                        controllingCenter.RightAction();
                        score3 = controllingCenter.getCurrentGameScore();
                    }
                }*/
            } else if (i == 3 && isAlive) {
                this.CloneTheControllingCenter();
                controllingCenterTemp.UpdateGameValidity();
                controllingCenterTemp.RightAction();
                score4 = controllingCenterTemp.getCurrentGameScore();
                controllingCenterTemp.UpdateGameValidity();
                /*for (int j = 0; i < 4; i++) {
                    flag4 = true;
                    if (j == 0) {
                        controllingCenter.DownAction();
                        score4 = controllingCenter.getCurrentGameScore();
                    } else if (j == 1) {
                        controllingCenter.UpAction();
                        score4 = controllingCenter.getCurrentGameScore();
                    } else if (j == 2) {
                        controllingCenter.LeftAction();
                        score4 = controllingCenter.getCurrentGameScore();
                    } else if (j == 3) {
                        controllingCenter.RightAction();
                        score4 = controllingCenter.getCurrentGameScore();
                    }
                }*/
            }

        }
        if (score1 >= score2 && score1 >= score3 && score1 >= score4&&controllingCenterTemp.getCurrentPlayingBoard().getAvailableDirectionSet()[1]==1 ) {
            return "Down";
        } else if (score2 >= score1 && score2 >= score3 && score2 >= score4&&controllingCenterTemp.getCurrentPlayingBoard().getAvailableDirectionSet()[0]==1 ) {
            return "Up";
        } else if (score3 >= score1 && score3 >= score2 && score3 >= score4 &&controllingCenterTemp.getCurrentPlayingBoard().getAvailableDirectionSet()[2]==1) {
            return "Left";
        } else{
            return "Right";
        }

    }

    public String mostBlocksCracked() {
        return "sss";
    }
    private void CloneTheControllingCenter(){
        controllingCenterTemp = new ControllingCenter();
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
    }
}
