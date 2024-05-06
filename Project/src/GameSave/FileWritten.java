package GameSave;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileWritten {
    String fileName = "userInformation.txt";
    public void fileWritten(ArrayList<FormerInformation> formerInformationArrayList){
        for (int i = 0; i < formerInformationArrayList.size(); i++) {
            String userName = formerInformationArrayList.get(i).getUserName();
            String currentScore = formerInformationArrayList.get(i).getControllingCenter().getCurrentGameScore() + "";
            String highestScore = formerInformationArrayList.get(i).getHighestScore() + "";
            String informationList = userName + " " +currentScore + " " + highestScore;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(informationList);
                writer.newLine(); // 写入换行符
            } catch (IOException e) {
            }
        }
    }
}
