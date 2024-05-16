package MultiUserSupply;

import GameSave.DocumentReaderAndWriter;

import java.io.*;
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

    private boolean ExaminePassWordsWhetherOnlyLetterNumber(String userName) {
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
        this.SetUpInitialKeySetsInTxt(directoryPath);
        file = new File(directoryPath + "/SinglePlayer/Competition/InThreeMinutes", "CompetitionInformationWithTimeLimit.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.SetUpInitialKeySetsInTxt(directoryPath);
    }

    public void SetUpInitialKeySetsInTxt(String directoryPath) {
        try {
            Properties properties = new Properties();
            properties.setProperty("TotalGameNumber", "0");
            properties.setProperty("TotalWinTime", "0");
            properties.setProperty("BestScoreOne", "0");
            properties.setProperty("BestScoreTwo", "0");
            properties.setProperty("BestScoreThree", "0");
            properties.setProperty("BestScoreFour", "0");
            properties.setProperty("BestScoreFive", "0");
            properties.setProperty("TimeOverSevenThousand","0");
            properties.setProperty("TimeOverFourteenThousand","0");
            properties.store(new FileWriter(directoryPath + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt"), "UserCompetitionWithTimeLimit");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> GetBestFiveScoresForTimeLimitCompetition(User user) {
        ArrayList<String> fiveBestScore = new ArrayList<String>();
        try {
            FileReader reader = new FileReader("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(reader);
            fiveBestScore.add(properties.getProperty("BestScoreOne"));
            fiveBestScore.add(properties.getProperty("BestScoreTwo"));
            fiveBestScore.add(properties.getProperty("BestScoreThree"));
            fiveBestScore.add(properties.getProperty("BestScoreFour"));
            fiveBestScore.add(properties.getProperty("BestScoreFive"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fiveBestScore;
    }

    public int GetBestScoreForTimeLimitCompetition(User user) {
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

    public int GetHistoricalWinningNumberForTimeLimitCompetition(User user) {
        int winningNumber = 0;
        try {
            FileInputStream inputStream = new FileInputStream("src/UserInformation/PersonalInformation/" + user.getUserName() + "/SinglePlayer/Competition/WithoutTimeLimit/CompetitionInformationWithoutTimeLimit.txt");
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            winningNumber = Integer.parseInt(properties.getProperty("TotalWinTime"));
        } catch (IOException e){
            e.printStackTrace();
        }
        return winningNumber;
    }

    public void IncreaseOneGameNumberInCompetitionWithTimeLimit(User user) {
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
    public void IncreaseWinningTimeInCompetitionWithTimeLimit(User user) {
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
    public void IncreaseSevenThousandTimeInCompetitionWithTimeLimit(User user) {
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
    public void IncreaseFourteenThousandTimeInCompetitionWithTimeLimit(User user) {
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

    public void UpdateBestFiveScoresForCompetitionWithTimeLimit(User user, int newGameScore) {
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
            properties.store(outputStream,null);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}