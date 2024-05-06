package GameSave;

import GameElement.ControllingCenter;

public class FormerInformation {
    String userName;
    ControllingCenter controllingCenter;
    int highestScore;


    public FormerInformation(String userName, ControllingCenter controllingCenter, int highestScore){
        this.userName = userName;
        this.controllingCenter = controllingCenter;
        this.highestScore = highestScore;
    }
    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public String getUserName() {
        return userName;
    }

    public ControllingCenter getControllingCenter() {
        return controllingCenter;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setControllingCenter(ControllingCenter controllingCenter) {
        this.controllingCenter = controllingCenter;
    }

}
