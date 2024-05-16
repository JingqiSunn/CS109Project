package MultiUserSupply;

public class User {
    String userName;
    int historicalWinningTimeInCompetition;
    int bestScoreInCompetitionWithTimeLimit;
    public User(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getHistoricalWinningTimeInCompetition() {
        return historicalWinningTimeInCompetition;
    }

    public int getBestScoreInCompetitionWithTimeLimit() {
        return bestScoreInCompetitionWithTimeLimit;
    }
    public void UpdateUserInformationForCompetitionWithoutLimitation(){
        UserManger userManger = new UserManger();
        bestScoreInCompetitionWithTimeLimit= userManger.GetBestScoreForTimeLimitCompetition(this);
        historicalWinningTimeInCompetition = userManger.GetHistoricalWinningNumberForTimeLimitCompetition(this);
    }
    public void addOneGameTotalGameNumberForCompetitionWithoutLimit(){
        UserManger userManger = new UserManger();
        userManger.IncreaseOneGameNumberInCompetitionWithTimeLimit(this);
    }
    public void UpdateBestFiveScoreForCompetitionWithoutTimeLimit(int currentScore){
        UserManger userManger = new UserManger();
        userManger.UpdateBestFiveScoresForCompetitionWithTimeLimit(this,currentScore);
    }
    public void IncreaseWinningTimeInCompetitionWithTimeLimit(){
        UserManger userManger = new UserManger();
        userManger.IncreaseWinningTimeInCompetitionWithTimeLimit(this);
    }
    public void IncreaseSevenThousandTimeInCompetitionWithTimeLimit(){
        UserManger userManger = new UserManger();
        userManger.IncreaseSevenThousandTimeInCompetitionWithTimeLimit(this);
    }
    public void IncreaseFourteenThousandTimeInCompetitionWithTimeLimit(){
        UserManger userManger = new UserManger();
        userManger.IncreaseFourteenThousandTimeInCompetitionWithTimeLimit(this);
    }
}
