package MultiUserSupply;

import GameElement.ControllingCenter;
import GameSave.DocumentReaderAndWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

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
    public void UpdateTheAverageScoreForStartOfGameCompetitionWithoutTimeLimit(){
        UserManger userManger = new UserManger();
        userManger.UpdateTheAverageScoreForStartOfGameCompetitionWithoutTimeLimit(this);
    }
    public void UpdateTheAverageScoreForEndOfGameCompetitionWithoutTimeLimit(int newScore){
        UserManger userManger = new UserManger();
        userManger.UpdateTheAverageScoreForEndOfGameCompetitionWithoutTimeLimit(this,newScore);
    }
    public void SavingCellValueSavingForCurrentStep(String archiveName, ControllingCenter controllingCenter){
        UserManger userManger = new UserManger();
        userManger.SavingCellValueSavingForCurrentStep(this,archiveName,controllingCenter);
    }
    public void GoingOneStepBackWards(String archiveName, ControllingCenter controllingCenter){
        UserManger userManger = new UserManger();
        userManger.GoingOneStepBackWards(this,archiveName,controllingCenter);
    }
    public void DeleteCompleteArchive(String archiveName){
        UserManger userManger = new UserManger();
        userManger.DeleteCompleteArchive(this,archiveName);
    }
    public void SetWhetherWonToBeTrue(String archiveName){
        UserManger userManger = new UserManger();
        userManger.SetWhetherWonToBeTrue(this,archiveName);
    }
    public void SaveGameBoardToASpecificArchivePracticeWithoutTimeLimit(String archiveName,ControllingCenter controllingCenter){
        UserManger userManger = new UserManger();
        userManger.SaveGameBoardToASpecificArchivePracticeWithoutTimeLimit(this,archiveName,controllingCenter);
    }
    public boolean ExamineWhetherArchiveAlreadyExisted(String archiveName){
        UserManger userManger = new UserManger();
        return userManger.ExamineWhetherArchiveAlreadyExisted(this,archiveName);
    }
}
