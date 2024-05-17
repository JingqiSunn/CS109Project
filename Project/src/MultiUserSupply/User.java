package MultiUserSupply;

public class User {
    String userName;
    int historicalWinningTimeInCompetitionWithoutLimitation;
    int bestScoreInCompetitionWithoutTimeLimit;
    int bestScoreInCompetitionWithTimeLimit;
    public User(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getHistoricalWinningTimeInCompetitionWithoutLimitation() {
        return historicalWinningTimeInCompetitionWithoutLimitation;
    }

    public int getBestScoreInCompetitionWithoutTimeLimit() {
        return bestScoreInCompetitionWithoutTimeLimit;
    }
    public void UpdateUserInformationForCompetition(){
        UserManger userManger = new UserManger();
        bestScoreInCompetitionWithoutTimeLimit = userManger.GetBestScoreForWithoutTimeLimitCompetition(this);
        historicalWinningTimeInCompetitionWithoutLimitation = userManger.GetHistoricalWinningNumberForWithoutTimeLimitCompetition(this);
        bestScoreInCompetitionWithTimeLimit = userManger.GetBestScoreForWithTimeLimitCompetition(this);
    }

    public int getBestScoreInCompetitionWithTimeLimit() {
        return bestScoreInCompetitionWithTimeLimit;
    }

    public void addOneGameTotalGameNumberForCompetitionWithoutLimit(){
        UserManger userManger = new UserManger();
        userManger.IncreaseOneGameNumberInCompetitionWithoutTimeLimit(this);
    }
    public void addOneGameTotalGameNumberForCompetitionWithLimit(){
        UserManger userManger = new UserManger();
        userManger.IncreaseOneGameNumberInCompetitionWithTimeLimit(this);
    }
    public void UpdateBestFiveScoreForCompetitionWithoutTimeLimit(int currentScore){
        UserManger userManger = new UserManger();
        userManger.UpdateBestFiveScoresForCompetitionWithoutTimeLimit(this,currentScore);
    }
    public void UpdateBestFiveScoreForCompetitionWithTimeLimit(int currentScore){
        UserManger userManger = new UserManger();
        userManger.UpdateBestFiveScoresForCompetitionWithTimeLimit(this,currentScore);
    }
    public void IncreaseWinningTimeInCompetitionWithoutTimeLimit(){
        UserManger userManger = new UserManger();
        userManger.IncreaseWinningTimeInCompetitionWithoutTimeLimit(this);
    }
    public void IncreaseSevenThousandTimeInCompetitionWithoutTimeLimit(){
        UserManger userManger = new UserManger();
        userManger.IncreaseSevenThousandTimeInCompetitionWithoutTimeLimit(this);
    }
    public void IncreaseFourteenThousandTimeInCompetitionWithoutTimeLimit(){
        UserManger userManger = new UserManger();
        userManger.IncreaseFourteenThousandTimeInCompetitionWithoutTimeLimit(this);
    }
    public void UpdateTheAverageScoreForStartOfGameCompetitionWithTimeLimit(){
        UserManger userManger = new UserManger();
        userManger.UpdateTheAverageScoreForStartOfGameCompetitionWithTimeLimit(this);
    }
    public void UpdateTheAverageScoreForEndOfGameCompetitionWithTimeLimit(int newScore){
        UserManger userManger = new UserManger();
        userManger.UpdateTheAverageScoreForEndOfGameCompetitionWithTimeLimit(this,newScore);
    }
}
