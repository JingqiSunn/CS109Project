package GameSave;

import GameElement.BoardUnit;
import GameElement.ControllingCenter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DocumentReaderAndWriter {
    ControllingCenter controllingCenter;

    public DocumentReaderAndWriter(ControllingCenter controllingCenter) {
        this.controllingCenter = controllingCenter;
    }

    public List<String> arraylistTransform(){
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

    public void save(){
        List<String> boarString = this.arraylistTransform();
        try{
            Files.write(Path.of("src/GameSave/userInformation.txt"), boarString, Charset.defaultCharset());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
