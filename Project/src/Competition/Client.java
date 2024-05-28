package Competition;

import GameVisual.TotalGameFrame;
import MultiUserSupply.User;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Client {
    User user;
    TotalGameFrame totalGameFrame;
    String IPAddress;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Scanner scanner;
    boolean whetherStart;
    boolean whetherEnemyStart;
    boolean whetherDie;
    boolean whetherEnemyDie;
    int enemyScore;
    public boolean whetherWon;
    public boolean whetherSame;
    public Client(String IPAddress, User user, TotalGameFrame totalGameFrame) throws IOException {
        this.whetherSame = false;
        this.whetherWon = false;
        this.whetherDie = false;
        this.whetherEnemyDie = false;
        this.whetherStart = false;
        this.whetherEnemyStart = false;
        this.totalGameFrame = totalGameFrame;
        this.user = user;
        this.IPAddress =  IPAddress;
        this.TryConnectToServer();
        this.ExchangeNameWithServer();
        this.FetchCommandToStartTheGame();
//        this.InGameInformationTransportation();
    }
    public Client(int totalScore,String IPAddress, User user, TotalGameFrame totalGameFrame) throws IOException {
        this.whetherSame = false;
        this.whetherWon = false;
        this.whetherDie = false;
        this.whetherEnemyDie = false;
        this.whetherStart = false;
        this.whetherEnemyStart = false;
        this.totalGameFrame = totalGameFrame;
        this.user = user;
        this.IPAddress =  IPAddress;
        this.TryConnectToServerSecond();
        this.ExchangeScoreWithClient();
//        this.InGameInformationTransportation();
    }

    public void setWhetherStart(boolean whetherStart) {
        this.whetherStart = whetherStart;
    }

    public void setWhetherDie(boolean whetherDie) {
        this.whetherDie = whetherDie;
    }

    public void TryConnectToServer() {
        try {
            socket = new Socket(IPAddress, 7656);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            scanner = new Scanner(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void TryConnectToServerSecond() {
        try {
            socket = new Socket(IPAddress, 8885);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            scanner = new Scanner(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ExchangeNameWithServer() {
        try {
            dataOutputStream.writeUTF(user.getUserName());
            dataOutputStream.flush();
            totalGameFrame.serverName = dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        totalGameFrame.whetherSuccessfullyConnected = true;
    }

    public void CommunicationWithServer() {
        while (true) {
            try {
                System.out.print("Enter message: ");
                String message = scanner.nextLine();
                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();
                System.out.println("Sent to server: " + message);

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }
                String response = dataInputStream.readUTF();
                System.out.println("Received from server: " + response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void FetchCommandToStartTheGame() throws IOException {
        while (!totalGameFrame.whetherStartTheMultiPlayerGame) {
            if (whetherStart) {
                dataOutputStream.writeBoolean(true);
                dataOutputStream.flush();
                whetherEnemyStart = dataInputStream.readBoolean();
                totalGameFrame.whetherStartTheMultiPlayerGame = true;
            }
        }
    }
    private void ExchangeScoreWithClient(){
        try {
            dataOutputStream.writeInt(totalGameFrame.getControllingCenter().getCurrentGameScore());
            dataOutputStream.flush();
            totalGameFrame.enemyScore = dataInputStream.readInt();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void InGameInformationTransportation() throws IOException {
        ListenToDieMessageFromEnemy listenToDieMessageFromEnemy= new ListenToDieMessageFromEnemy();
        listenToDieMessageFromEnemy.start();
        CallDieMessageToEnemy callDieMessageToEnemy = new CallDieMessageToEnemy();
        callDieMessageToEnemy.start();
        while (!whetherEnemyDie&&!whetherDie){

        }
        while (whetherEnemyDie&&!whetherDie){

        }
        while (whetherEnemyDie&&whetherDie){;
            dataOutputStream.writeInt(totalGameFrame.getControllingCenter().getCurrentGameScore());
            dataOutputStream.flush();
            enemyScore = dataInputStream.readInt();
        }
        if (enemyScore<totalGameFrame.getControllingCenter().getCurrentGameScore()){
            whetherWon = true;
        } else if (enemyScore > totalGameFrame.getControllingCenter().getCurrentGameScore()){
            whetherWon = false;
        } else {
            whetherSame = true;
        }
        totalGameFrame.whetherMultiGameOver = true;
    }
    class ListenToDieMessageFromEnemy extends Thread {
        public void run() {
            super.run();
            try {
                whetherEnemyDie = dataInputStream.readBoolean();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    class CallDieMessageToEnemy extends Thread {
        public void run() {
            super.run();
            while (!whetherDie){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                dataOutputStream.writeBoolean(true);
                dataOutputStream.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
