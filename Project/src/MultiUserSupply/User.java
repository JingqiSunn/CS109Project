package MultiUserSupply;

public class User {
    String userName;
    int ownLeastStepToWinInCompetition;
    int historicalWinningTimeInCompetition=0;
    int bestScoreInCompetitionWithTimeLimit=0;
    public User(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getHistoricalWinningTimeInCompetition() {
        return historicalWinningTimeInCompetition;
    }

    public int getOwnLeastStepToWinInCompetition() {
        return ownLeastStepToWinInCompetition;
    }

    public int getBestScoreInCompetitionWithTimeLimit() {
        return bestScoreInCompetitionWithTimeLimit;
    }
}
