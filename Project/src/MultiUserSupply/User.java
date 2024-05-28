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
    int totalGameNumberWithout;
    int totalGameNumberWith;
    int bestOneWithout;
    int bestTwoWithout;
    int bestThreeWithout;
    int bestFourWithout;
    int bestFiveWithout;
    int bestOneWith;
    int bestTwoWith;
    int bestThreeWith;
    int bestFourWith;
    int bestFiveWith;
    int averageWithout;
    int averageWith;
    int rateOverSevenThousand;
    int rateOverFourteenThousand;
    int numberOverFourteenThousand;
    int numberOverSevenThousand;
    int totalWinTimeWithout;
    int totalWinRateWithout;
    public User(String userName){
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getTotalWinRateWithout() {
        return totalWinRateWithout;
    }

    public void setTotalWinRateWithout(int totalWinRateWithout) {
        this.totalWinRateWithout = totalWinRateWithout;
    }

    public void setTotalGameNumberWithout(int totalGameNumberWithout) {
        this.totalGameNumberWithout = totalGameNumberWithout;
    }

    public int getTotalWinTimeWithout() {
        return totalWinTimeWithout;
    }

    public void setTotalWinTimeWithout(int totalWinTimeWithout) {
        this.totalWinTimeWithout = totalWinTimeWithout;
    }

    public void setTotalGameNumberWith(int totalGameNumberWith) {
        this.totalGameNumberWith = totalGameNumberWith;
    }

    public void setBestOneWithout(int bestOneWithout) {
        this.bestOneWithout = bestOneWithout;
    }

    public void setBestTwoWithout(int bestTwoWithout) {
        this.bestTwoWithout = bestTwoWithout;
    }

    public void setBestThreeWithout(int bestThreeWithout) {
        this.bestThreeWithout = bestThreeWithout;
    }

    public void setBestFourWithout(int bestFourWithout) {
        this.bestFourWithout = bestFourWithout;
    }

    public void setBestFiveWithout(int bestFiveWithout) {
        this.bestFiveWithout = bestFiveWithout;
    }

    public void setBestOneWith(int bestOneWith) {
        this.bestOneWith = bestOneWith;
    }

    public void setBestTwoWith(int bestTwoWith) {
        this.bestTwoWith = bestTwoWith;
    }

    public void setBestThreeWith(int bestThreeWith) {
        this.bestThreeWith = bestThreeWith;
    }

    public void setBestFourWith(int bestFourWith) {
        this.bestFourWith = bestFourWith;
    }

    public void setBestFiveWith(int bestFiveWith) {
        this.bestFiveWith = bestFiveWith;
    }

    public void setAverageWithout(int averageWithout) {
        this.averageWithout = averageWithout;
    }

    public void setAverageWith(int averageWith) {
        this.averageWith = averageWith;
    }

    public void setRateOverSevenThousand(int rateOverSevenThousand) {
        this.rateOverSevenThousand = rateOverSevenThousand;
    }

    public void setRateOverFourteenThousand(int rateOverFourteenThousand) {
        this.rateOverFourteenThousand = rateOverFourteenThousand;
    }

    public void setNumberOverFourteenThousand(int numberOverFourteenThousand) {
        this.numberOverFourteenThousand = numberOverFourteenThousand;
    }

    public void setNumberOverSevenThousand(int numberOverSevenThousand) {
        this.numberOverSevenThousand = numberOverSevenThousand;
    }

    public int getTotalGameNumberWithout() {
        return totalGameNumberWithout;
    }

    public int getTotalGameNumberWith() {
        return totalGameNumberWith;
    }

    public int getBestOneWithout() {
        return bestOneWithout;
    }

    public int getBestTwoWithout() {
        return bestTwoWithout;
    }

    public int getBestThreeWithout() {
        return bestThreeWithout;
    }

    public int getBestFourWithout() {
        return bestFourWithout;
    }

    public int getBestFiveWithout() {
        return bestFiveWithout;
    }

    public int getBestOneWith() {
        return bestOneWith;
    }

    public int getBestTwoWith() {
        return bestTwoWith;
    }

    public int getBestThreeWith() {
        return bestThreeWith;
    }

    public int getBestFourWith() {
        return bestFourWith;
    }

    public int getBestFiveWith() {
        return bestFiveWith;
    }

    public int getAverageWithout() {
        return averageWithout;
    }

    public int getAverageWith() {
        return averageWith;
    }

    public int getRateOverSevenThousand() {
        return rateOverSevenThousand;
    }

    public int getRateOverFourteenThousand() {
        return rateOverFourteenThousand;
    }

    public int getNumberOverFourteenThousand() {
        return numberOverFourteenThousand;
    }

    public int getNumberOverSevenThousand() {
        return numberOverSevenThousand;
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
    public void DeleteArchiveInList(String archiveName){
        UserManger userManger = new UserManger();
        userManger.DeleteArchiveInList(this,archiveName);
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
    public ControllingCenter BuildControllingCenterBasedOnTheArchive(String archiveName,ControllingCenter controllingCenter){
        UserManger userManger = new UserManger();
        return userManger.BuildControllingCenterBasedOnTheArchive(archiveName,this,controllingCenter);
    }
    public void UpdateUserInformationForRecord(){
        UserManger userManger = new UserManger();
        userManger.UpdateUserInformationForRecord(this);
    }
    public boolean WhetherInvalidlyModified(String archiveName){
        UserManger userManger = new UserManger();
        return userManger.WhetherInvalidlyModified(this,archiveName);
    }
}
