package GameElement;

import java.util.ArrayList;

public class ArtificialIntelligenceSupply {
    ControllingCenter controllingCenter;
    boolean isAlive;
    int currentScore;
    int count;
    ArrayList<BoardUnit> currentBoard;
    ControllingCenter controllingCenterTemp;
    ControllingCenter controllingCenterTemp1;
    ControllingCenter controllingCenterTemp2;
    Board board;

    public int twoCombinedTogether(ControllingCenter controllingCenter){
        int finalDirection = -1;
        int direction1 = mostScoresEarned(controllingCenter);
        int direction2 = getBestMove(controllingCenter);
        if (direction1 >= 0 ){
            finalDirection = direction1;
        }else{
            finalDirection=direction2;
        }
        if (finalDirection < 0 ){
            finalDirection = 0;
        }
        return finalDirection;
    }
    //贪心算法
    public int mostScoresEarned(ControllingCenter controllingCenter) {
        this.controllingCenter = controllingCenter;
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
                controllingCenterTemp = CloneTheControllingCenter(controllingCenter);
                controllingCenterTemp.UpdateGameValidity();
                controllingCenterTemp.DownAction();
                score1 = controllingCenterTemp.getCurrentGameScore();
                controllingCenterTemp.UpdateGameValidity();
                for (int j = 0; j < 4; j++) {
                    if (j == 0 && controllingCenterTemp.getGameValidity()){
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.DownAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score1 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    } else if (j == 1 && controllingCenterTemp.getGameValidity()) {
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.UpAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score1 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    } else if (j == 2 && controllingCenterTemp.getGameValidity()) {
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.LeftAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score1 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    } else if (j==3 && controllingCenterTemp.getGameValidity()) {
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.RightAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score1 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    }
                }

            } else if (i == 1 && isAlive) {
                controllingCenterTemp = CloneTheControllingCenter(controllingCenter);
                controllingCenterTemp.UpdateGameValidity();
                controllingCenterTemp.UpAction();
                score2 = controllingCenterTemp.getCurrentGameScore();
                controllingCenterTemp.UpdateGameValidity();
                for (int j = 0; j < 4; j++) {
                    if (j == 0 && controllingCenterTemp.getGameValidity()){
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.DownAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score2 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    } else if (j == 1 && controllingCenterTemp.getGameValidity()) {
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.UpAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score2 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    } else if (j == 2 && controllingCenterTemp.getGameValidity()) {
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.LeftAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score2 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    } else if (j==3 && controllingCenterTemp.getGameValidity()) {
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.RightAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score2 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    }
                }
            } else if (i == 2 && isAlive) {
                controllingCenterTemp = CloneTheControllingCenter(controllingCenter);
                controllingCenterTemp.UpdateGameValidity();
                controllingCenterTemp.LeftAction();
                score3 = controllingCenterTemp.getCurrentGameScore();
                controllingCenterTemp.UpdateGameValidity();
                for (int j = 0; j < 4; j++) {
                    if (j == 0 && controllingCenterTemp.getGameValidity()){
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.DownAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score3 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    } else if (j == 1 && controllingCenterTemp.getGameValidity()) {
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.UpAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score3 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    } else if (j == 2 && controllingCenterTemp.getGameValidity()) {
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.LeftAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score3 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    } else if (j==3 && controllingCenterTemp.getGameValidity()) {
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.RightAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score3 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    }
                }
            } else if (i == 3 && isAlive) {
                controllingCenterTemp = CloneTheControllingCenter(controllingCenter);
                controllingCenterTemp.UpdateGameValidity();
                controllingCenterTemp.RightAction();
                score4 = controllingCenterTemp.getCurrentGameScore();
                controllingCenterTemp.UpdateGameValidity();
                for (int j = 0; j < 4; j++) {
                    if (j == 0 && controllingCenterTemp.getGameValidity()){
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.DownAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score4 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    } else if (j == 1 && controllingCenterTemp.getGameValidity()) {
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.UpAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score4 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    } else if (j == 2 && controllingCenterTemp.getGameValidity()) {
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.LeftAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score4 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    } else if (j==3 && controllingCenterTemp.getGameValidity()) {
                        controllingCenterTemp1 = CloneTheControllingCenter(controllingCenterTemp);
                        controllingCenterTemp1.UpdateGameValidity();
                        controllingCenterTemp1.RightAction();
                        if (controllingCenterTemp1.getCurrentGameScore() > score1){
                            score4 = controllingCenterTemp1.getCurrentGameScore();
                        }
                    }
                }
            }
        }
        int [] scoreList = {score1,score2,score3,score4};
        for (int i = 0; i < 4; i++) {
            for (int j = 3; j >0 ; j--) {
                if (scoreList[j] > scoreList[j-1]){
                    int temp = scoreList[j];
                    scoreList[j] = scoreList[j-1];
                    scoreList[j-1] = temp;
                }
            }
        }
        currentScore = controllingCenter.getCurrentGameScore();
        int count = currentScore / 250;
        if (count % 2 == 0) {
            if (scoreList[0] == score1) {
                return 2;
            } else if (scoreList[0] == score3) {
                return 3;
            } else if (scoreList[0] == score4) {
                return 1;
            } else {
                return -1;
            }
        }else{
            if (scoreList[0] == score1) {
                return 2;
            } else if (scoreList[0] == score4) {
                return 1;
            } else if (scoreList[0] == score3) {
                return 3;
            } else {
                return -1;
            }
        }
        /*if (score1 >= score2 && score1 >= score3 && score1 >= score4) {
            return "Down";
        } else if (score2 >= score1 && score2 >= score3 && score2 >= score4 ) {
            return "Up";
        } else if (score3 >= score1 && score3 >= score2 && score3 >= score4 ) {
            return "Left";
        } else{
            return "Right";
        }*/

    }

    public int getBestMove(ControllingCenter controllingCenter){
        int[] directionPriority = {0,1,2,3};   //上、右、下、左
        this.controllingCenter = controllingCenter;
        controllingCenterTemp = new ControllingCenter();
        currentBoard = controllingCenter.getBoardUnitInformation();
        isAlive = controllingCenter.getGameValidity();
        int bestMove = -1;
        int bestScore = -1;
        for (int Direction : directionPriority){
            if (Direction == 0 && isAlive){
                controllingCenterTemp = CloneTheControllingCenter(controllingCenter);
                controllingCenterTemp.UpdateGameValidity();
                if (controllingCenterTemp.getGameValidity()) {
                    controllingCenterTemp.UpAction();
                    int score = evaluateGrid(controllingCenterTemp);
                    if (score > bestScore){
                        bestMove=Direction;
                        bestScore = score;
                    }
                }
            }else if(Direction == 1 && isAlive){
                controllingCenterTemp = CloneTheControllingCenter(controllingCenter);
                controllingCenterTemp.UpdateGameValidity();
                if (controllingCenterTemp.getGameValidity()) {
                    controllingCenterTemp.RightAction();
                    int score = evaluateGrid(controllingCenterTemp);
                    if (score > bestScore){
                        bestMove=Direction;
                        bestScore = score;
                    }
                }
            } else if (Direction == 2 && isAlive) {
                controllingCenterTemp = CloneTheControllingCenter(controllingCenter);
                controllingCenterTemp.UpdateGameValidity();
                if (controllingCenterTemp.getGameValidity()) {
                    controllingCenterTemp.DownAction();
                    int score = evaluateGrid(controllingCenterTemp);
                    if (score > bestScore){
                        bestMove=Direction;
                        bestScore = score;
                    }
                }
            } else if (Direction == 3 && isAlive) {
                controllingCenterTemp = CloneTheControllingCenter(controllingCenter);
                controllingCenterTemp.UpdateGameValidity();
                if (controllingCenterTemp.getGameValidity()) {
                    controllingCenterTemp.LeftAction();
                    int score = evaluateGrid(controllingCenterTemp);
                    if (score > bestScore){
                        bestMove=Direction;
                        bestScore = score;
                    }
                }
            }
        }
        return bestMove;
    }

    public int evaluateGrid(ControllingCenter controllingCenter){
        int [][] grid = new int[4][4];
        ArrayList<BoardUnit> gridArray = controllingCenter.getBoardUnitInformation();
        for (int i = 0; i < gridArray.size(); i++) {
            int x = gridArray.get(i).getxCoordinate();
            int y = gridArray.get(i).getyCoordinate();
            int value;
            if (gridArray.get(i).getCell()!=null) {
                value = gridArray.get(i).getCell().getValue();
            }else{
                value = 0;
            }
            grid[y][x] = value;
        }
        int score = 0;
        int weight = 16;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                score+=grid[i][j] * weight;
            }
            weight--;
        }
        return score;
    }

    private ControllingCenter CloneTheControllingCenter(ControllingCenter controllingCenter){
        ControllingCenter controllingCenterTemp = new ControllingCenter();
        ArrayList<Integer> cloneOfTheCoordinates = new ArrayList<>();
        for (int indexInBoardUnitSet = 0; indexInBoardUnitSet < controllingCenter.getBoardUnitInformation().size(); indexInBoardUnitSet++) {
            cloneOfTheCoordinates.add(controllingCenter.getBoardUnitInformation().get(indexInBoardUnitSet).getxCoordinate());
            cloneOfTheCoordinates.add(controllingCenter.getBoardUnitInformation().get(indexInBoardUnitSet).getyCoordinate());
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

    private int eachDirectionToGo(ControllingCenter controllingCenter, int score){
        for (int j = 0; j < 4; j++) {
            if (j == 0 && controllingCenter.getGameValidity()) {
                controllingCenterTemp = CloneTheControllingCenter(controllingCenter);
                controllingCenterTemp.UpdateGameValidity();
                controllingCenterTemp.DownAction();
                if (controllingCenterTemp.getCurrentGameScore() > score) {
                    score = controllingCenterTemp.getCurrentGameScore();
                }
            } else if (j == 1 && controllingCenter.getGameValidity()) {
                controllingCenterTemp = CloneTheControllingCenter(controllingCenter);
                controllingCenterTemp.UpdateGameValidity();
                controllingCenterTemp.UpAction();
                if (controllingCenterTemp.getCurrentGameScore() > score) {
                    score = controllingCenterTemp.getCurrentGameScore();
                }
            } else if (j == 2 && controllingCenter.getGameValidity()) {
                controllingCenterTemp= CloneTheControllingCenter(controllingCenter);
                controllingCenterTemp.UpdateGameValidity();
                controllingCenterTemp.LeftAction();
                if (controllingCenterTemp.getCurrentGameScore() > score) {
                    score = controllingCenterTemp.getCurrentGameScore();
                }
            } else if (j == 3 && controllingCenter.getGameValidity()) {
                controllingCenterTemp = CloneTheControllingCenter(controllingCenter);
                controllingCenterTemp.UpdateGameValidity();
                controllingCenterTemp.RightAction();
                if (controllingCenterTemp.getCurrentGameScore() > score) {
                    score = controllingCenterTemp.getCurrentGameScore();
                }
            }
        }
        return score;
    }
}
