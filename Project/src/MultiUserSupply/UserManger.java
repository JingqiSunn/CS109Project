package MultiUserSupply;

import java.io.File;

public class UserManger {
    public UserManger(){}
    String feedBackForUserName;
    String feedBackForPassWords;
    String feedbackForConfirmPasswords;

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
        String directoryPath = "src/UserInformation/PersonalInformation"+ userName;
        File folder = new File(directoryPath);
    }
    public boolean ExamineValidityOfUserName(String userName) {
        boolean whetherValid = true;
        feedBackForUserName = null;
        if(userName.isEmpty()){
            whetherValid = false;
            feedBackForUserName = "User name can not be null!";
        }else if (userName.length()<4||userName.length()>16){
            whetherValid = false;
            feedBackForUserName ="The username must consist of 4 to 16 characters composed of English letters!";
        } else if(!ExamineUsernameWhetherOnlyLetterNumberAndDash(userName)){
            whetherValid = false;
            System.out.println("hi");
            feedBackForUserName = "There exists invalid chars in your user name!";
        }
        return whetherValid;
    }

    public boolean ExamineValidityOfPassWords(String passWords){
        boolean whetherValid = true;
        feedBackForPassWords = null;
        if (passWords == null){
            whetherValid = false;
            feedBackForPassWords = "The passwords can not be null!";
        } else if (passWords.length()<4||passWords.length()>16){
            whetherValid = false;
            feedBackForPassWords ="The passwords must consist of 4 to 16 characters composed of English letters!";
        } else if(!ExaminePassWordsWhetherOnlyLetterNumberAndDash(passWords)){
            whetherValid = false;
            feedBackForPassWords = "There exists invalid chars in your user name!";
        } else if(!ExamineWhetherUseThreeKindsInPassWords(passWords)){
            whetherValid = false;
            feedBackForPassWords = "You can use only uppercase and lowercase English letters, as well as Arabic numerals!";
        }
        return whetherValid;
    }

    public boolean ExamineValidityOfAgainPasswords(String againPasswords, String passWords){
        boolean whetherValid = true;
        feedBackForPassWords = null;
        if (againPasswords == null){
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
    private boolean ExaminePassWordsWhetherOnlyLetterNumberAndDash(String userName){
        boolean whetherValid = true;
        boolean whetherValidThisTerm = false;
        String availableLetters = "1234567890zxcvbnmasdfghjklqwertyuiopZXCVBNMASDFGHJKLQWERTYUIOP";
        for (int indexInTargetString = 0; indexInTargetString < userName.length(); indexInTargetString++) {
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
                if (whetherValidTermOne){
                    break;
                }
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
                if (whetherValidTermTwo){
                    break;
                }
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
                if (whetherValidTermThree){
                    break;
                }
            }
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
}
