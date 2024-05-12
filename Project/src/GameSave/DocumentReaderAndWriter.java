package GameSave;

import GameElement.BoardUnit;
import GameElement.ControllingCenter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class DocumentReaderAndWriter {
    ControllingCenter controllingCenter;

    public DocumentReaderAndWriter(ControllingCenter controllingCenter,String userName) {
        this.controllingCenter = controllingCenter;
    }

    public DocumentReaderAndWriter() {
    }

    public List<String> arrayListTransform(){
        ArrayList<BoardUnit> currentPlayingBoard = controllingCenter.getBoardUnitInformation();
        int maxSize = currentPlayingBoard.size();
        List<String> boardString = new ArrayList<>();
        for (int i = 0; i < maxSize; i++) {
            String x = currentPlayingBoard.get(i).getxCoordinate()+"";
            String y = currentPlayingBoard.get(i).getyCoordinate()+"";
            int value = 0;
            if (currentPlayingBoard.get(i).getCell()!=null) {
                value = currentPlayingBoard.get(i).getCell().getValue();
            }
            String valueString = value + "";
            String information = x +" " + y + " " + valueString;
            boardString.add(information);
        }
        boardString.add(controllingCenter.getCurrentGameScore()+"");
        return boardString;
    }

    public void saveTheGame(){
        List<String> boarString = this.arrayListTransform();
        try{
            Files.write(Path.of("src/GameSave/userInformation.txt"), boarString, Charset.defaultCharset());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void saveUserLoginInformation(String userLoginName,String userLoginPassword){
        try {
            Files.write(Path.of("src/UserInformation/GeneralInformation/UserLoginInformation/UserName.txt"),
                    (userLoginName+"\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            String hashedPassword = hashPassword(userLoginPassword);
            Files.write(Path.of("src/UserInformation/GeneralInformation/UserLoginInformation/UserPassWord.txt"),
                    (hashedPassword+"\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String hashPassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedPasswordBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte byteInHashedPassword : hashedPasswordBytes){
                String hexByteInHashedPassword = Integer.toHexString(0xff & byteInHashedPassword);
                if (hexByteInHashedPassword.length()==1){
                    hexString.append('0');
                }
                hexString.append(hexByteInHashedPassword);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<String> GetCurrentUserList(){
        ArrayList<String> currentUserList = new ArrayList<String>();
        String pathOfUserNameTxt="src/UserInformation/GeneralInformation/UserLoginInformation/UserName.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathOfUserNameTxt));
            String currentLine;
            while((currentLine = reader.readLine())!=null){
                currentUserList.add(currentLine);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentUserList;
    }
    public ArrayList<String> GetCurrentUserPasswordList(){
        ArrayList<String> currentUserPasswordList = new ArrayList<String>();
        String pathOfUserNameTxt="src/UserInformation/GeneralInformation/UserLoginInformation/UserPassWord.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathOfUserNameTxt));
            String currentLine;
            while((currentLine = reader.readLine())!=null){
                currentUserPasswordList.add(currentLine);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentUserPasswordList;
    }
}