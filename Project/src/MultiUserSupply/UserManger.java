package MultiUserSupply;

import GameSave.DocumentReaderAndWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class UserManger {
    public UserManger(){}
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

    public void CreateDirectoryForSpecificUser(String userName){
        String directoryPath = "src/UserInformation/PersonalInformation/"+ userName;
        File directory = new File(directoryPath);
        directory.mkdirs();
    }
    public boolean ExamineValidityOfUserNameWhenLogin(String userName){
        this.FetchUserGeneralInformation();
        boolean whetherValid = true;
        feedBackForUserNameInLogin = null;
        if (userName.isEmpty()){
            whetherValid = false;
            feedBackForUserNameInLogin = "User name can not be null!";
        } else if (!ExamineWhetherExistedUserName(userName)){
            whetherValid = false;
            feedBackForUserNameInLogin = "No existing user name!";
        }
        return whetherValid;
    }
    public boolean ExamineValidityOfPassWordWhenLogin(String userName, String passWord){
        this.FetchUserGeneralInformation();
        boolean whetherValid = true;
        feedBackForPasswordInLogin = null;
        if (passWord.isEmpty()){
            whetherValid = false;
            feedBackForPasswordInLogin = "Password can not be null!";
        } else if (!ExamineWhetherCorrectPassword(userName,passWord)){
            whetherValid = false;
            feedBackForPasswordInLogin = "Wrong passwords!";
        }
        return whetherValid;
    }

    public boolean ExamineValidityOfUserName(String userName) {
        this.FetchUserGeneralInformation();
        boolean whetherValid = true;
        feedBackForUserName = null;
        if(userName.isEmpty()){
            whetherValid = false;
            feedBackForUserName = "User name can not be null!";
        }else if (userName.length()<4||userName.length()>16){
            whetherValid = false;
            feedBackForUserName ="The username must consist of 4 to 16 characters!";
        } else if(!ExamineUsernameWhetherOnlyLetterNumberAndDash(userName)){
            whetherValid = false;
            feedBackForUserName = "There exists invalid chars in your user name!";
        } else if (ExamineWhetherExistedUserName(userName)) {
            whetherValid = false;
            feedBackForUserName = "Username is already in use!";
        }
        return whetherValid;
    }

    public boolean ExamineValidityOfPassWords(String passWords){
        boolean whetherValid = true;
        feedBackForPassWords = null;
        if (passWords.isEmpty()){
            whetherValid = false;
            feedBackForPassWords = "The passwords can not be null!";
        } else if (passWords.length()<4||passWords.length()>16){
            whetherValid = false;
            feedBackForPassWords ="The passwords must consist of 4 to 16 characters!";
        } else if(!ExaminePassWordsWhetherOnlyLetterNumber(passWords)){
            whetherValid = false;
            feedBackForPassWords = "There exists invalid chars in your user name!";
        } else if(!ExamineWhetherUseThreeKindsInPassWords(passWords)){
            whetherValid = false;
        }
        return whetherValid;
    }

    public boolean ExamineValidityOfAgainPasswords(String againPasswords, String passWords){
        boolean whetherValid = true;
        feedBackForPassWords = null;
        if (againPasswords.isEmpty()){
            whetherValid = false;
            feedbackForConfirmPasswords = "Confirming passwords can not be null!";
        } else if(!WhetherTwoStringsAreTheSame(againPasswords,passWords)){
            whetherValid = false;
            feedbackForConfirmPasswords = "Two passwords are different!";
        }
        return whetherValid;
    }
    private boolean ExamineUsernameWhetherOnlyLetterNumberAndDash(String userName){
        boolean whetherValid = true;
        boolean whetherValidThisTerm = false;
        String availableLetters = "1234567890_zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP";
        for (int indexInTargetString = 0; indexInTargetString < userName.length(); indexInTargetString++) {
            whetherValidThisTerm = false;
            char targetChar = userName.charAt(indexInTargetString);
            for (int indexInValidSet = 0; indexInValidSet < availableLetters.length(); indexInValidSet++) {
                char availableChar = availableLetters.charAt(indexInValidSet);
                if (targetChar == availableChar){
                    whetherValidThisTerm = true;
                    break;
                }
            }
            if (!whetherValidThisTerm){
                whetherValid =false;
                return whetherValid;
            }
        }
        return whetherValid;
    }
    private boolean ExaminePassWordsWhetherOnlyLetterNumber(String userName){
        boolean whetherValid = true;
        boolean whetherValidThisTerm = false;
        String availableLetters = "1234567890zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP";
        for (int indexInTargetString = 0; indexInTargetString < userName.length(); indexInTargetString++) {
            whetherValidThisTerm = false;
            char targetChar = userName.charAt(indexInTargetString);
            for (int indexInValidSet = 0; indexInValidSet < availableLetters.length(); indexInValidSet++) {
                char availableChar = availableLetters.charAt(indexInValidSet);
                if (targetChar == availableChar){
                    whetherValidThisTerm = true;
                    break;
                }
            }
            if (!whetherValidThisTerm){
                whetherValid =false;
                break;
            }
        }
        return whetherValid;
    }
    private boolean ExamineWhetherUseThreeKindsInPassWords(String passWords){
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
                if (currentChar == standardChar){
                    whetherValidTermOne = true;
                    break;
                }
            }
            if (whetherValidTermOne){
                break;
            }
        }
        for (int indexInStandard = 0; indexInStandard < availableLettersTwo.length(); indexInStandard++) {
            char standardChar = availableLettersTwo.charAt(indexInStandard);
            for (int indexInPassWords = 0; indexInPassWords < passWords.length(); indexInPassWords++) {
                char currentChar = passWords.charAt(indexInPassWords);
                if (currentChar == standardChar){
                    whetherValidTermTwo = true;
                    break;
                }
            }
            if (whetherValidTermTwo){
                break;
            }
        }
        for (int indexInStandard = 0; indexInStandard < availableLettersThree.length(); indexInStandard++) {
            char standardChar = availableLettersThree.charAt(indexInStandard);
            for (int indexInPassWords = 0; indexInPassWords < passWords.length(); indexInPassWords++) {
                char currentChar = passWords.charAt(indexInPassWords);
                if (currentChar == standardChar){
                    whetherValidTermThree = true;
                    break;
                }
            }
            if (whetherValidTermThree){
                break;
            }
        }
        if (!whetherValidTermOne){
            feedBackForPassWords = "Numbers are needed";
        } else if(!whetherValidTermTwo){
            feedBackForPassWords = "Lower cased English letters are needed";
        } else if(!whetherValidTermThree){
            feedBackForPassWords = "Upper cased English letters are needed";

        }
        if(whetherValidTermOne&&whetherValidTermThree&&whetherValidTermTwo){
            whetherValid = true;
        }
        return whetherValid;
    }
    private boolean WhetherTwoStringsAreTheSame(String stringOne, String stringTwo){
        boolean whetherTheSame = true;
        if (!(stringOne.length()==stringTwo.length())){
            whetherTheSame = false;
            return whetherTheSame;
        }
        for (int indexInString = 0; indexInString < stringOne.length(); indexInString++) {
            if (stringOne.charAt(indexInString)!=stringTwo.charAt(indexInString)){
                whetherTheSame = false;
                return whetherTheSame;
            }
        }
        return whetherTheSame;
    }
    private boolean ExamineWhetherExistedUserName(String userName){
        boolean whetherUsedUserName = false;
        if (userList.isEmpty()){
            return false;
        }
        for (int indexInExistedUserNameList = 0; indexInExistedUserNameList < userList.size(); indexInExistedUserNameList++) {
            if (Objects.equals(userName, userList.get(indexInExistedUserNameList))){
                whetherUsedUserName = true;
                return whetherUsedUserName;
            }
        }
        return whetherUsedUserName;
    }
    private void FetchUserGeneralInformation(){
        userList = new ArrayList<String>();
        passwordListInHash = new ArrayList<String>();
        DocumentReaderAndWriter documentReaderAndWriter = new DocumentReaderAndWriter();
        userList = documentReaderAndWriter.GetCurrentUserList();
        passwordListInHash = documentReaderAndWriter.GetCurrentUserPasswordList();
    }
    private boolean ExamineWhetherCorrectPassword(String userName, String passWord){
        String passWordInHash = null;
        DocumentReaderAndWriter documentReaderAndWriter = new DocumentReaderAndWriter();
        passWordInHash = documentReaderAndWriter.hashPassword(passWord);
        int indexInPlayer = 0;
        boolean whetherCorrect = false;
        this.FetchUserGeneralInformation();
        for (int indexInUserNameList = 0; indexInUserNameList < userList.size(); indexInUserNameList++) {
            if (userName.equals(userList.get(indexInUserNameList))){
                indexInPlayer = indexInUserNameList;
                break;
            }
        }
        String targetPassword = passwordListInHash.get(indexInPlayer);
        if (passWordInHash.equals(targetPassword)){
            whetherCorrect = true;
        }
        return whetherCorrect;
    }
}
