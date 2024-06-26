package MultiUserSupply;

import GameElement.Board;
import GameElement.BoardUnit;
import GameElement.Cell;
import GameElement.ControllingCenter;
import GameSave.DocumentReaderAndWriter;
import GameVisual.Panels.BreakTheRecordDiePage;
import GameVisual.Panels.InGamePageWithoutTimeLimit;
import GameVisual.Panels.UserPracticeWithoutLimitDiePage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class UserManger {
    User user;

    public UserManger(User user) {
        this.user = user;
    }

    public UserManger() {
    }

    String feedBackForUserNameInLogin;
    String feedBackForPasswordInLogin;
    String feedBackForUserName;
    String feedBackForPassWords;
    String feedbackForConfirmPasswords;
    ArrayList<String> userList;
    ArrayList<String> passwordListInHash;

    public String getFeedBackForUserNameInLogin() {
        return feedBackForUserNameInLogin;
    }

    public String getFeedBackForPasswordInLogin() {
        return feedBackForPasswordInLogin;
    }

    public String getFeedBackForUserName() {
        return feedBackForUserName;
    }

    public String getFeedBackForPassWords() {
        return feedBackForPassWords;
    }

    public String getFeedbackForConfirmPasswords() {
        return feedbackForConfirmPasswords;
    }

    public void CreateDirectoryForSpecificUser(String userName) {
        String directoryPath = "src/UserInformation/PersonalInformation/" + userName;
        File directory = new File(directoryPath);
        directory.mkdirs();
        this.CreateInnerDirectoriesForSpecificUser(directoryPath);
    }

    public boolean ExamineValidityOfUserNameWhenLogin(String userName) {
        this.FetchUserGeneralInformation();
        boolean whetherValid = true;
        feedBackForUserNameInLogin = null;
        if (userName.isEmpty()) {
            whetherValid = false;
            feedBackForUserNameInLogin = "Username can not be null!";
        } else if (!ExamineWhetherExistedUserName(userName)) {
            whetherValid = false;
            feedBackForUserNameInLogin = "No existing username!";
        }
        return whetherValid;
    }

    public boolean ExamineValidityOfPassWordWhenLogin(String userName, String passWord) {
        this.FetchUserGeneralInformation();
        boolean whetherValid = true;
        feedBackForPasswordInLogin = null;
        if (passWord.isEmpty()) {
            whetherValid = false;
            feedBackForPasswordInLogin = "Password can not be null!";
        } else if (!ExamineWhetherCorrectPassword(userName, passWord)) {
            whetherValid = false;
            feedBackForPasswordInLogin = "Wrong passwords!";
        }
        return whetherValid;
    }

    public boolean ExamineValidityOfUserName(String userName) {
        this.FetchUserGeneralInformation();
        boolean whetherValid = true;
        feedBackForUserName = null;
        if (userName.isEmpty()) {
            whetherValid = false;
            feedBackForUserName = "Username can not be null!";
        } else if (userName.length() < 4 || userName.length() > 16) {
            whetherValid = false;
            feedBackForUserName = "The username must consist of 4 to 16 characters!";
        } else if (!ExamineUsernameWhetherOnlyLetterNumberAndDash(userName)) {
            whetherValid = false;
            feedBackForUserName = "There exists invalid chars in your username!";
        } else if (ExamineWhetherExistedUserName(userName)) {
            whetherValid = false;
            feedBackForUserName = "Username is already in use!";
        }
        return whetherValid;
    }

    public boolean ExamineValidityOfPassWords(String passWords) {
        boolean whetherValid = true;
        feedBackForPassWords = null;
        if (passWords.isEmpty()) {
            whetherValid = false;
            feedBackForPassWords = "The passwords can not be null!";
        } else if (passWords.length() < 4 || passWords.length() > 16) {
            whetherValid = false;
            feedBackForPassWords = "The passwords must consist of 4 to 16 characters!";
        } else if (!ExaminePassWordsWhetherOnlyLetterNumber(passWords)) {
            whetherValid = false;
            feedBackForPassWords = "There exists invalid chars in your passwords!";
        } else if (!ExamineWhetherUseThreeKindsInPassWords(passWords)) {
            whetherValid = false;
        }
        return whetherValid;
    }

    public boolean ExamineValidityOfAgainPasswords(String againPasswords, String passWords) {
        boolean whetherValid = true;
        feedBackForPassWords = null;
        if (againPasswords.isEmpty()) {
            whetherValid = false;
            feedbackForConfirmPasswords = "Confirming passwords can not be null!";
        } else if (!WhetherTwoStringsAreTheSame(againPasswords, passWords)) {
            whetherValid = false;
            feedbackForConfirmPasswords = "Two passwords are different!";
        }
        return whetherValid;
    }

    private boolean ExamineUsernameWhetherOnlyLetterNumberAndDash(String userName) {
        boolean whetherValid = true;
        boolean whetherValidThisTerm = false;
        String availableLetters = "1234567890_zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP";
        for (int indexInTargetString = 0; indexInTargetString < userName.length(); indexInTargetString++) {
            whetherValidThisTerm = false;
            char targetChar = userName.charAt(indexInTargetString);
            for (int indexInValidSet = 0; indexInValidSet < availableLetters.length(); indexInValidSet++) {
                char availableChar = availableLetters.charAt(indexInValidSet);
                if (targetChar == availableChar) {
                    whetherValidThisTerm = true;
                    break;
                }
            }
            if (!whetherValidThisTerm) {
                whetherValid = false;
                return whetherValid;
            }
        }
        return whetherValid;
    }

    public boolean ExaminePassWordsWhetherOnlyLetterNumber(String userName) {
        boolean whetherValid = true;
        boolean whetherValidThisTerm = false;
        String availableLetters = "1234567890zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP";
        for (int indexInTargetString = 0; indexInTargetString < userName.length(); indexInTargetString++) {
            whetherValidThisTerm = false;
            char targetChar = userName.charAt(indexInTargetString);
            for (int indexInValidSet = 0; indexInValidSet < availableLetters.length(); indexInValidSet++) {
                char availableChar = availableLetters.charAt(indexInValidSet);
                if (targetChar == availableChar) {
                    whetherValidThisTerm = true;
                    break;
                }
            }
            if (!whetherValidThisTerm) {
                whetherValid = false;
                break;
            }
        }
        return whetherValid;
    }

    private boolean ExamineWhetherUseThreeKindsInPassWords(String passWords) {
        boolean whetherValid = false;
        boolean whetherValidTermOne = false;
        boolean whetherValidTermTwo = false;
        boolean whetherValidTermThree = false;
        String availableLettersOne = "1234567890";
        String availableLettersTwo = "zxcvbnmasdfghjklqwertyuiop";
        String availableLettersThree = "ZXCVBNMASDFGHJKLQWERTYUIOP";
        for (int indexInStandard = 0; indexInStandard < availableLettersOne.length(); indexInStandard++) {
            char standardChar = availableLettersOne.charAt(indexInStandard);
            for (int indexInPassWords = 0; indexInPassWords < passWords.length(); indexInPassWords++) {
                char currentChar = passWords.charAt(indexInPassWords);
                if (currentChar == standardChar) {
                    whetherValidTermOne = true;
                    break;
                }
            }
            if (whetherValidTermOne) {
                break;
            }
        }
        for (int indexInStandard = 0; indexInStandard < availableLettersTwo.length(); indexInStandard++) {
            char standardChar = availableLettersTwo.charAt(indexInStandard);
            for (int indexInPassWords = 0; indexInPassWords < passWords.length(); indexInPassWords++) {
                char currentChar = passWords.charAt(indexInPassWords);
                if (currentChar == standardChar) {
                    whetherValidTermTwo = true;
                    break;
                }
            }
            if (whetherValidTermTwo) {
                break;
            }
        }
        for (int indexInStandard = 0; indexInStandard < availableLettersThree.length(); indexInStandard++) {
            char standardChar = availableLettersThree.charAt(indexInStandard);
            for (int indexInPassWords = 0; indexInPassWords < passWords.length(); indexInPassWords++) {
                char currentChar = passWords.charAt(indexInPassWords);
                if (currentChar == standardChar) {
                    whetherValidTermThree = true;
                    break;
                }
            }
            if (whetherValidTermThree) {
                break;
            }
        }
        if (!whetherValidTermOne) {
            feedBackForPassWords = "Numbers are needed";
        } else if (!whetherValidTermTwo) {
            feedBackForPassWords = "Lower cased English letters are needed";
        } else if (!whetherValidTermThree) {
            feedBackForPassWords = "Upper cased English letters are needed";

        }
        if (whetherValidTermOne && whetherValidTermThree && whetherValidTermTwo) {
            whetherValid = true;
        }
        return whetherValid;
    }

    private boolean WhetherTwoStringsAreTheSame(String stringOne, String stringTwo) {
        boolean whetherTheSame = true;
        if (!(stringOne.length() == stringTwo.length())) {
            whetherTheSame = false;
            return whetherTheSame;
        }
        for (int indexInString = 0; indexInString < stringOne.length(); indexInString++) {
            if (stringOne.charAt(indexInString) != stringTwo.charAt(indexInString)) {
                whetherTheSame = false;
                return whetherTheSame;
            }
        }
        return whetherTheSame;
    }

    private boolean ExamineWhetherExistedUserName(String userName) {
        boolean whetherUsedUserName = false;
        if (userList.isEmpty()) {
            return false;
        }
        for (int indexInExistedUserNameList = 0; indexInExistedUserNameList < userList.size(); indexInExistedUserNameList++) {
            if (Objects.equals(userName, userList.get(indexInExistedUserNameList))) {
                whetherUsedUserName = true;
                return whetherUsedUserName;
            }
        }
        return whetherUsedUserName;
    }

    private void FetchUserGeneralInformation() {
        userList = new ArrayList<String>();
        passwordListInHash = new ArrayList<String>();
        DocumentReaderAndWriter documentReaderAndWriter = new DocumentReaderAndWriter();
        userList = documentReaderAndWriter.GetCurrentUserList();
        passwordListInHash = documentReaderAndWriter.GetCurrentUserPasswordList();
    }

    private boolean ExamineWhetherCorrectPassword(String userName, String passWord) {
        String passWordInHash = null;
        DocumentReaderAndWriter documentReaderAndWriter = new DocumentReaderAndWriter();
        passWordInHash = documentReaderAndWriter.hashPassword(passWord);
        int indexInPlayer = 0;
        boolean whetherCorrect = false;
        this.FetchUserGeneralInformation();
        for (int indexInUserNameList = 0; indexInUserNameList < userList.size(); indexInUserNameList++) {
            if (userName.equals(userList.get(indexInUserNameList))) {
                indexInPlayer = indexInUserNameList;
                break;
            }
        }
        String targetPassword = passwordListInHash.get(indexInPlayer);
        if (passWordInHash.equals(targetPassword)) {
            whetherCorrect = true;
        }
        return whetherCorrect;
    }

    private void CreateInnerDirectoriesForSpecificUser(String directoryPath) {
        File directory = new File(directoryPath + "/MultiPlayer");
        directory.mkdirs();
        directory = new File(directoryPath + "/Record");
        directory.mkdirs();
        directory = new File(directoryPath + "/SinglePlayer");
        directory.mkdirs();
        directory = new File(directoryPath + "/SinglePlayer/Competition");
        directory.mkdirs();
        directory = new File(directoryPath + "/SinglePlayer/Practice");
        directory.mkdirs();
        directory = new File(directoryPath + "/SinglePlayer/Practice/WithoutTimeLimitation");
        directory.mkdirs();
        directory = new File(directoryPath + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive");
        directory.mkdirs();
        directory = new File(directoryPath + "/SinglePlayer/Practice/WithoutTimeLimitation/CurrentGame");
        directory.mkdirs();
        directory = new File(directoryPath + "/SinglePlayer/Practice/WithTimeLimitation");
        directory.mkdirs();
        directory = new File(directoryPath + "/SinglePlayer/Practice/WithTimeLimitation/HistoricalArchive");
        directory.mkdirs();
        directory = new File(directoryPath + "/SinglePlayer/Practice/WithTimeLimitation/CurrentGame");
        directory.mkdirs();
        directory = new File(directoryPath + "/SinglePlayer/Competition/InThreeMinutes");
        directory.mkdirs();
        directory = new File(directoryPath + "/SinglePlayer/Competition/WithoutTimeLimit");
        directory.mkdirs();
        directory = new File(directoryPath + "/SinglePlayer/Competition/WithoutTimeLimit");
        directory.mkdirs();
        File file = new File(directoryPath + "/SinglePlayer/Competition/WithoutTimeLimit", "CompetitionInformationWithoutTimeLimit.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.SetUpInitialKeySetsInTxtCompetitionWithoutTimeLimit(directoryPath);
        file = new File(directoryPath + "/SinglePlayer/Competition/InThreeMinutes", "CompetitionInformationWithTimeLimit.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.SetUpInitialKeySetsInTxtCompetitionWithTimeLimit(directoryPath);
        file = new File(directoryPath + "/SinglePlayer/Practice/WithoutTimeLimitation/CurrentGame", "CurrentGameInformation.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file = new File(directoryPath + "/SinglePlayer/Practice/WithTimeLimitation/CurrentGame", "CurrentGameInformation.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file = new File(directoryPath + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive", "ArchiveNameList.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void SetUpInitialKeySetsInTxtCompetitionWithoutTimeLimit(String directoryPath) {
        try {
            Properties properties = new Properties();
            properties.setProperty("TotalGameNumber", "0");
            properties.setProperty("TotalWinTime", "0");
            properties.setProperty("BestScoreOne", "0");
            properties.setProperty("BestScoreTwo", "0");
            properties.setProperty("BestScoreThree", "0");
            properties.setProperty("BestScoreFour", "0");
            properties.setProperty("BestScoreFive", "0");
            properties.setProperty("TimeOverSevenThousand", "0");
            properties.setProperty("TimeOverFourteenThousand", "0");
            properties.setProperty("AverageScore", "0");
            properties.store(new FileWriter(directoryPath + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt"), "UserCompetitionWithoutTimeLimit");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void SetUpInitialKeySetsInTxtCompetitionWithTimeLimit(String directoryPath) {
        try {
            Properties properties = new Properties();
            properties.setProperty("TotalGameNumber", "0");
            properties.setProperty("BestScoreOne", "0");
            properties.setProperty("BestScoreTwo", "0");
            properties.setProperty("BestScoreThree", "0");
            properties.setProperty("BestScoreFour", "0");
            properties.setProperty("BestScoreFive", "0");
            properties.setProperty("AverageScore", "0");
            properties.store(new FileWriter(directoryPath + "/SinglePlayer/Competition/InThreeMinutes/CompetitionInformationWithTimeLimit.txt"), "UserCompetitionWithTimeLimit");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int GetBestScoreForWithoutTimeLimitCompetition(User user) {
        int bestScore = 0;
        try {
            FileReader reader = new FileReader("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(reader);
            bestScore = Integer.parseInt(properties.getProperty("BestScoreOne"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bestScore;
    }

    public int GetBestScoreForWithTimeLimitCompetition(User user) {
        int bestScore = 0;
        try {
            FileReader reader = new FileReader("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/InThreeMinutes/CompetitionInformationWithTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(reader);
            bestScore = Integer.parseInt(properties.getProperty("BestScoreOne"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bestScore;
    }

    public int GetHistoricalWinningNumberForWithoutTimeLimitCompetition(User user) {
        int winningNumber = 0;
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            winningNumber = Integer.parseInt(properties.getProperty("TotalWinTime"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return winningNumber;
    }


    public void IncreaseOneGameNumberInCompetitionWithoutTimeLimit(User user) {
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            int totalGameNumber = Integer.parseInt(properties.getProperty("TotalGameNumber"));
            totalGameNumber += 1;
            properties.setProperty("TotalGameNumber", String.valueOf(totalGameNumber));
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void IncreaseOneGameNumberInCompetitionWithTimeLimit(User user) {
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/InThreeMinutes/CompetitionInformationWithTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            int totalGameNumber = Integer.parseInt(properties.getProperty("TotalGameNumber"));
            totalGameNumber += 1;
            properties.setProperty("TotalGameNumber", String.valueOf(totalGameNumber));
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/InThreeMinutes/CompetitionInformationWithTimeLimit.txt");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void IncreaseWinningTimeInCompetitionWithoutTimeLimit(User user) {
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            int totalWinningTime = Integer.parseInt(properties.getProperty("TotalWinTime"));
            totalWinningTime += 1;
            properties.setProperty("TotalWinTime", String.valueOf(totalWinningTime));
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void IncreaseSevenThousandTimeInCompetitionWithoutTimeLimit(User user) {
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            int totalSevenThousandTime = Integer.parseInt(properties.getProperty("TimeOverSevenThousand"));
            totalSevenThousandTime += 1;
            properties.setProperty("TimeOverSevenThousand", String.valueOf(totalSevenThousandTime));
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void IncreaseFourteenThousandTimeInCompetitionWithoutTimeLimit(User user) {
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            int totalFourteenThousandTime = Integer.parseInt(properties.getProperty("TimeOverSevenThousand"));
            totalFourteenThousandTime += 1;
            properties.setProperty("TimeOverFourteenThousand", String.valueOf(totalFourteenThousandTime));
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void UpdateBestFiveScoresForCompetitionWithoutTimeLimit(User user, int newGameScore) {
        ArrayList<String> fiveBestScoreAndOneCurrentScore = new ArrayList<String>();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(fileInputStream);
            fiveBestScoreAndOneCurrentScore.add(properties.getProperty("BestScoreOne"));
            fiveBestScoreAndOneCurrentScore.add(properties.getProperty("BestScoreTwo"));
            fiveBestScoreAndOneCurrentScore.add(properties.getProperty("BestScoreThree"));
            fiveBestScoreAndOneCurrentScore.add(properties.getProperty("BestScoreFour"));
            fiveBestScoreAndOneCurrentScore.add(properties.getProperty("BestScoreFive"));
            fiveBestScoreAndOneCurrentScore.add(String.valueOf(newGameScore));
            fileInputStream.close();
            Comparator comparator = new Comparator<String>() {
                @Override
                public int compare(String stringOne, String stringTwo) {
                    if (Integer.parseInt(stringOne) >= Integer.parseInt(stringTwo)) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            };
            Collections.sort(fiveBestScoreAndOneCurrentScore, comparator);
            properties.setProperty("BestScoreOne", fiveBestScoreAndOneCurrentScore.get(0));
            properties.setProperty("BestScoreTwo", fiveBestScoreAndOneCurrentScore.get(1));
            properties.setProperty("BestScoreThree", fiveBestScoreAndOneCurrentScore.get(2));
            properties.setProperty("BestScoreFour", fiveBestScoreAndOneCurrentScore.get(3));
            properties.setProperty("BestScoreFive", fiveBestScoreAndOneCurrentScore.get(4));
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateBestFiveScoresForCompetitionWithTimeLimit(User user, int newGameScore) {
        ArrayList<String> fiveBestScoreAndOneCurrentScore = new ArrayList<String>();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/InThreeMinutes/CompetitionInformationWithTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(fileInputStream);
            fiveBestScoreAndOneCurrentScore.add(properties.getProperty("BestScoreOne"));
            fiveBestScoreAndOneCurrentScore.add(properties.getProperty("BestScoreTwo"));
            fiveBestScoreAndOneCurrentScore.add(properties.getProperty("BestScoreThree"));
            fiveBestScoreAndOneCurrentScore.add(properties.getProperty("BestScoreFour"));
            fiveBestScoreAndOneCurrentScore.add(properties.getProperty("BestScoreFive"));
            fiveBestScoreAndOneCurrentScore.add(String.valueOf(newGameScore));
            fileInputStream.close();
            Comparator comparator = new Comparator<String>() {
                @Override
                public int compare(String stringOne, String stringTwo) {
                    if (Integer.parseInt(stringOne) >= Integer.parseInt(stringTwo)) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            };
            Collections.sort(fiveBestScoreAndOneCurrentScore, comparator);
            properties.setProperty("BestScoreOne", fiveBestScoreAndOneCurrentScore.get(0));
            properties.setProperty("BestScoreTwo", fiveBestScoreAndOneCurrentScore.get(1));
            properties.setProperty("BestScoreThree", fiveBestScoreAndOneCurrentScore.get(2));
            properties.setProperty("BestScoreFour", fiveBestScoreAndOneCurrentScore.get(3));
            properties.setProperty("BestScoreFive", fiveBestScoreAndOneCurrentScore.get(4));
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/InThreeMinutes/CompetitionInformationWithTimeLimit.txt");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int GetAverageScoreOfUSerCompetitionWithTimeLimit(User user) {
        int averageScore = 0;
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/InThreeMinutes/CompetitionInformationWithTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            averageScore = Integer.parseInt(properties.getProperty("AverageScore"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return averageScore;
    }

    private int GetAverageScoreOfUSerCompetitionWithoutTimeLimit(User user) {
        int averageScore = 0;
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            averageScore = Integer.parseInt(properties.getProperty("AverageScore"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return averageScore;
    }

    private int GetTotalGameNumberOfUSerCompetitionWithTimeLimit(User user) {
        int gameNumber = 0;
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/InThreeMinutes/CompetitionInformationWithTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            gameNumber = Integer.parseInt(properties.getProperty("TotalGameNumber"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameNumber;
    }

    private int GetTotalGameNumberOfUSerCompetitionWithoutTimeLimit(User user) {
        int gameNumber = 0;
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            gameNumber = Integer.parseInt(properties.getProperty("TotalGameNumber"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameNumber;
    }

    public void UpdateTheAverageScoreForStartOfGameCompetitionWithTimeLimit(User user) {
        int averageScore = GetAverageScoreOfUSerCompetitionWithTimeLimit(user);
        int totalGameNumber = GetTotalGameNumberOfUSerCompetitionWithTimeLimit(user);
        averageScore = (int) (((double) averageScore * (double) totalGameNumber) / (double) (totalGameNumber + 1));
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/InThreeMinutes/CompetitionInformationWithTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            properties.setProperty("AverageScore", String.valueOf(averageScore));
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/InThreeMinutes/CompetitionInformationWithTimeLimit.txt");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void UpdateTheAverageScoreForStartOfGameCompetitionWithoutTimeLimit(User user) {
        int averageScore = GetAverageScoreOfUSerCompetitionWithoutTimeLimit(user);
        int totalGameNumber = GetTotalGameNumberOfUSerCompetitionWithoutTimeLimit(user);
        averageScore = (int) (((double) averageScore * (double) totalGameNumber) / (double) (totalGameNumber + 1));
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            properties.setProperty("AverageScore", String.valueOf(averageScore));
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void UpdateTheAverageScoreForEndOfGameCompetitionWithTimeLimit(User user, int score) {
        int averageScore = GetAverageScoreOfUSerCompetitionWithTimeLimit(user);
        int totalGameNumber = GetTotalGameNumberOfUSerCompetitionWithTimeLimit(user);
        averageScore = (int) ((((double) averageScore * (double) totalGameNumber) + (double) score) / (double) (totalGameNumber));
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/InThreeMinutes/CompetitionInformationWithTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            properties.setProperty("AverageScore", String.valueOf(averageScore));
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/InThreeMinutes/CompetitionInformationWithTimeLimit.txt");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void UpdateTheAverageScoreForEndOfGameCompetitionWithoutTimeLimit(User user, int score) {
        int averageScore = GetAverageScoreOfUSerCompetitionWithoutTimeLimit(user);
        int totalGameNumber = GetTotalGameNumberOfUSerCompetitionWithoutTimeLimit(user);
        averageScore = (int) ((((double) averageScore * (double) totalGameNumber) + (double) score) / (double) (totalGameNumber));
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            properties.setProperty("AverageScore", String.valueOf(averageScore));
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveGameBoardToASpecificArchivePracticeWithoutTimeLimit(User user, String archiveName, ControllingCenter controllingCenter) {
        ArrayList<BoardUnit> blockLocationSet = controllingCenter.getCurrentPlayingBoard().getBoardLocationSet();
        StringBuilder boardUnitLocationSet = new StringBuilder();
        for (int indexInBoardUnits = 0; indexInBoardUnits < blockLocationSet.size(); indexInBoardUnits++) {
            boardUnitLocationSet.append(String.valueOf(blockLocationSet.get(indexInBoardUnits).getxCoordinate()));
            boardUnitLocationSet.append(String.valueOf(blockLocationSet.get(indexInBoardUnits).getyCoordinate()));
        }
        File file = new File("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive", archiveName + ".txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            properties.setProperty("BoardUnitLocationSet", String.valueOf(boardUnitLocationSet));
            properties.setProperty("TargetScore", String.valueOf(controllingCenter.getTargetWinningScore()));
            properties.setProperty("WhetherWon","false");
            properties.setProperty("CurrentStepNumber","0");
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
            properties.store(outputStream, null);
            outputStream.close();
            this.UpdateLastModifiedTime(user,archiveName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DocumentReaderAndWriter documentReaderAndWriter = new DocumentReaderAndWriter();
        documentReaderAndWriter.saveUserArchiveInformation(archiveName, user);
    }

    public void SavingCellValueSavingForCurrentStep(User user, String archiveName, ControllingCenter controllingCenter) {
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            properties.setProperty("Step" + String.valueOf(controllingCenter.getNumberOfStep()), controllingCenter.GetTheValueSetForBlockUnitSet());
            properties.setProperty("Score" + String.valueOf(controllingCenter.getNumberOfStep()), String.valueOf(controllingCenter.getCurrentGameScore()));
            properties.setProperty("CurrentStepNumber" , String.valueOf(controllingCenter.getNumberOfStep()));
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
            properties.store(outputStream, null);
            outputStream.close();
            this.UpdateLastModifiedTime(user,archiveName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void GoingOneStepBackWards(User user, String archiveName, ControllingCenter controllingCenter) {
        if (controllingCenter.getNumberOfStep() > 0) {
            try {
                FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
                Properties properties = new Properties();
                properties.load(inputStream);
                inputStream.close();
                properties.remove("Step" + String.valueOf(controllingCenter.getNumberOfStep()));
                properties.remove("Score" + String.valueOf(controllingCenter.getNumberOfStep()));
                FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
                properties.store(outputStream, null);
                outputStream.close();
                this.UpdateLastModifiedTime(user,archiveName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            int[] cellValueSetInIntArraylist = new int[0];
            int score = 0;
            controllingCenter.setNumberOfStep(controllingCenter.getNumberOfStep() - 1);
            String cellValueSet = new String();
            try {
                FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
                Properties properties = new Properties();
                properties.load(inputStream);
                inputStream.close();
                cellValueSet = properties.getProperty("Step" + String.valueOf(controllingCenter.getNumberOfStep()));
                score = Integer.parseInt(properties.getProperty("Score" + String.valueOf(controllingCenter.getNumberOfStep())));
                properties.setProperty("CurrentStepNumber" , String.valueOf(controllingCenter.getNumberOfStep()));
                String[] cellValueSetInStringArraylist = cellValueSet.split(" ");
                cellValueSetInIntArraylist = new int[cellValueSetInStringArraylist.length];
                for (int indexInValueArraylist = 0; indexInValueArraylist < cellValueSetInStringArraylist.length; indexInValueArraylist++) {
                    cellValueSetInIntArraylist[indexInValueArraylist] = Integer.parseInt(cellValueSetInStringArraylist[indexInValueArraylist]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            controllingCenter.CleanValueOfCellInThePlayingBoard();
            controllingCenter.setCurrentGameScore(score);
            controllingCenter.getCurrentPlayingBoard().setCurrentScore(score);
            for (int indexInValueSet = 0; indexInValueSet < controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().size(); indexInValueSet++) {
                if (cellValueSetInIntArraylist[indexInValueSet] != 0) {
                    controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().get(indexInValueSet).setCell(new Cell(0,controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().get(indexInValueSet)));
                    controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().get(indexInValueSet).getCell().setValue(cellValueSetInIntArraylist[indexInValueSet]);
                }
            }
        }
    }
    public boolean ExamineWhetherArchiveAlreadyExisted(User user,String newArchiveName){
        DocumentReaderAndWriter documentReaderAndWriter = new DocumentReaderAndWriter();
        ArrayList<String> archiveNameAlreadyExistedList = new ArrayList<>();
        archiveNameAlreadyExistedList = documentReaderAndWriter.getCurrentUserArchiveList(user);
        if (archiveNameAlreadyExistedList.isEmpty()){
            return false;
        }
        boolean whetherAlreadyExisted = false;
        for (int indexInExistedArchiveNameList = 0; indexInExistedArchiveNameList < archiveNameAlreadyExistedList.size(); indexInExistedArchiveNameList++) {
            if (newArchiveName.equals(archiveNameAlreadyExistedList.get(indexInExistedArchiveNameList))) {
                whetherAlreadyExisted = true;
            }
        }
        return whetherAlreadyExisted;
    }
    public void DeleteCompleteArchive(User user, String archiveName){
        DocumentReaderAndWriter documentReaderAndWriter = new DocumentReaderAndWriter();
        ArrayList<String> archiveNameAlreadyExistedList = new ArrayList<>();
        archiveNameAlreadyExistedList = documentReaderAndWriter.getCurrentUserArchiveList(user);
        archiveNameAlreadyExistedList.remove(archiveName);
        try {
            FileWriter fileWriter = new FileWriter("src/UserInformation/PersonalInformation/"+user.getUserName()+"/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/ArchiveNameList.txt", false);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int indexInArchiveNameList = 0; indexInArchiveNameList < archiveNameAlreadyExistedList.size(); indexInArchiveNameList++) {
            documentReaderAndWriter.saveUserArchiveInformation(archiveNameAlreadyExistedList.get(indexInArchiveNameList),user);
        }
        String filePath = "src/UserInformation/PersonalInformation/"+user.getUserName()+"/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/"+archiveName+".txt";
        Path path = Paths.get(filePath);
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void DeleteArchiveInList(User user, String archiveName){
        DocumentReaderAndWriter documentReaderAndWriter = new DocumentReaderAndWriter();
        ArrayList<String> archiveNameAlreadyExistedList = new ArrayList<>();
        archiveNameAlreadyExistedList = documentReaderAndWriter.getCurrentUserArchiveList(user);
        archiveNameAlreadyExistedList.remove(archiveName);
        try {
            FileWriter fileWriter = new FileWriter("src/UserInformation/PersonalInformation/"+user.getUserName()+"/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/ArchiveNameList.txt", false);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int indexInArchiveNameList = 0; indexInArchiveNameList < archiveNameAlreadyExistedList.size(); indexInArchiveNameList++) {
            documentReaderAndWriter.saveUserArchiveInformation(archiveNameAlreadyExistedList.get(indexInArchiveNameList),user);
        }
    }
    public void SetWhetherWonToBeTrue(User user, String archiveName){
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            properties.setProperty("WhetherWon","true");
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ControllingCenter BuildControllingCenterBasedOnTheArchive(String archiveName, User user,ControllingCenter controllingCenter){
        int currentStepNumber=0;
        int targetScore=0;
        boolean whetherAlreadyWon=false;
        int currentScore = 0;
        ArrayList<Integer> informationOfBoardUnitLocation = new ArrayList<>();
        int[] informationOfCellValue = null;
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            currentStepNumber = Integer.parseInt(properties.getProperty("CurrentStepNumber"));
            targetScore = Integer.parseInt(properties.getProperty("TargetScore"));
            currentScore = Integer.parseInt(properties.getProperty("Score" +String.valueOf(currentStepNumber)));
            if (properties.getProperty("WhetherWon").equals(false)){
                whetherAlreadyWon = false;
            } else {
                whetherAlreadyWon = true;
            }
            String boardLocationInformation = properties.getProperty("BoardUnitLocationSet");
            for (int indexInTheStringLocationSet = 0; indexInTheStringLocationSet < boardLocationInformation.length(); indexInTheStringLocationSet++) {
                informationOfBoardUnitLocation.add(Character.getNumericValue(boardLocationInformation.charAt(indexInTheStringLocationSet)));
            }
            String cellValueInformationSet = properties.getProperty("Step" +String.valueOf(currentStepNumber));
            String[] cellValueSetInStringArraylist =cellValueInformationSet .split(" ");
            informationOfCellValue = new int[cellValueSetInStringArraylist.length];
            for (int indexInValueArraylist = 0; indexInValueArraylist < cellValueSetInStringArraylist.length; indexInValueArraylist++) {
                informationOfCellValue[indexInValueArraylist] = Integer.parseInt(cellValueSetInStringArraylist[indexInValueArraylist]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        controllingCenter.setInformationOfAllTheCoordinateOfTheBoardUnit(informationOfBoardUnitLocation);
        controllingCenter.getCurrentPlayingBoard().SetThePlayingBoard(controllingCenter.getInformationOfAllTheCoordinateOfTheBoardUnit());
        controllingCenter.getCurrentPlayingBoard().GetBoardUnitsInTheSameColumn();
        controllingCenter.getCurrentPlayingBoard().GetBoardUnitsInTheSameRow();
        controllingCenter.getCurrentPlayingBoard().GetNeighborBoardUnitInColumn();
        controllingCenter.getCurrentPlayingBoard().GetNeighborBoardUnitInRow();
        for (int indexInValueSet = 0; indexInValueSet < controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().size(); indexInValueSet++) {
            if (informationOfCellValue[indexInValueSet] != 0) {
                controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().get(indexInValueSet).setCell(new Cell(0,controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().get(indexInValueSet)));
                controllingCenter.getCurrentPlayingBoard().getBoardLocationSet().get(indexInValueSet).getCell().setValue(informationOfCellValue[indexInValueSet]);
            }
        }
        controllingCenter.setArchiveName(archiveName);
        controllingCenter.setNumberOfStep(currentStepNumber);
        controllingCenter.setWhetherAlreadyShownWinningPage(whetherAlreadyWon);
        controllingCenter.setTargetWinningScore(targetScore);
        controllingCenter.setCurrentGameScore(currentScore);
        controllingCenter.getCurrentPlayingBoard().setCurrentScore(currentScore);
        controllingCenter.UpdateGameValidity();
        return controllingCenter;
    }
    void UpdateUserInformationForRecord(User user){
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            user.setBestOneWithout(Integer.parseInt(properties.getProperty("BestScoreOne")));
            user.setBestTwoWithout(Integer.parseInt(properties.getProperty("BestScoreTwo")));
            user.setBestThreeWithout(Integer.parseInt(properties.getProperty("BestScoreThree")));
            user.setBestFourWithout(Integer.parseInt(properties.getProperty("BestScoreFour")));
            user.setBestFiveWithout(Integer.parseInt(properties.getProperty("BestScoreFive")));
            user.setAverageWithout(Integer.parseInt(properties.getProperty("AverageScore")));
            user.setNumberOverSevenThousand(Integer.parseInt(properties.getProperty("TimeOverSevenThousand")));
            user.setNumberOverFourteenThousand(Integer.parseInt(properties.getProperty("TimeOverFourteenThousand")));
            user.setTotalGameNumberWithout(Integer.parseInt(properties.getProperty("TotalGameNumber")));
            user.setTotalWinTimeWithout(Integer.parseInt(properties.getProperty("TotalWinTime")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (user.getTotalGameNumberWithout() == 0){
            user.setRateOverSevenThousand(0);
            user.setRateOverFourteenThousand(0);
            user.setTotalWinRateWithout(0);
        } else {
            user.setRateOverSevenThousand(
                    (int)((double)(user.getNumberOverSevenThousand()*100)/(double)user.getTotalGameNumberWithout())
            );
            user.setRateOverFourteenThousand(
                    (int)((double)(user.getNumberOverFourteenThousand()*100)/(double)user.getTotalGameNumberWithout())
            );
            user.setTotalWinRateWithout(
                    (int)((double)(user.getTotalWinTimeWithout()*100)/(double)user.getTotalGameNumberWithout())
            );
        }
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/InThreeMinutes/CompetitionInformationWithTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            user.setBestOneWith(Integer.parseInt(properties.getProperty("BestScoreOne")));
            user.setBestTwoWith(Integer.parseInt(properties.getProperty("BestScoreTwo")));
            user.setBestThreeWith(Integer.parseInt(properties.getProperty("BestScoreThree")));
            user.setBestFourWith(Integer.parseInt(properties.getProperty("BestScoreFour")));
            user.setBestFiveWith(Integer.parseInt(properties.getProperty("BestScoreFive")));
            user.setAverageWith(Integer.parseInt(properties.getProperty("AverageScore")));
            user.setTotalGameNumberWith(Integer.parseInt(properties.getProperty("TotalGameNumber")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void UpdateLastModifiedTime(User user, String archiveName){
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            File file = new File("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
            DocumentReaderAndWriter documentReaderAndWriter = new DocumentReaderAndWriter();
            properties.setProperty("LastModifiedTime",documentReaderAndWriter.hashPassword(String.valueOf(file.lastModified()/1000)));
            FileOutputStream outputStream = new FileOutputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean WhetherInvalidlyModified(User user, String archiveName){
        boolean whetherInvalidlyModified = false;
        String properTime = null;
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            properTime=properties.getProperty("LastModifiedTime");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Practice/WithoutTimeLimitation/HistoricalArchive/" + archiveName + ".txt");
        DocumentReaderAndWriter documentReaderAndWriter = new DocumentReaderAndWriter();
        String actualTime = documentReaderAndWriter.hashPassword(String.valueOf(file.lastModified()/1000));
        if(!actualTime.equals(properTime)){
            whetherInvalidlyModified = true;
            return whetherInvalidlyModified;
        }
        return whetherInvalidlyModified;
    }
}