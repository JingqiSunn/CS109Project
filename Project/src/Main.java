import GameElement.ControllingCenter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner fetch = new Scanner(System.in);
        ControllingCenter controllingCenter = new ControllingCenter();
        controllingCenter.GetTheSetUpInformationOfTheBoard(fetch);
        controllingCenter.SetThePlayingBoard();
    }
}
