package Competition;

import GameVisual.TotalGameFrame;
import MultiUserSupply.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

public class Server {
    TotalGameFrame totalGameFrame;
    User user;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    boolean whetherStart;
    boolean whetherEnemyStart;
    boolean whetherDie;
    boolean whetherEnemyDie;
    boolean whetherShowDiePage;
    int enemyScore;
    public boolean whetherWon;
    public boolean whetherSame;
    public Server(User user, TotalGameFrame totalGameFrame) {
        this.whetherSame = false;
        this.whetherWon = false;
        this.whetherDie = false;
        this.whetherEnemyDie = false;
        this.whetherStart = false;
        this.whetherEnemyStart = false;
        this.whetherShowDiePage = false;
        this.totalGameFrame = totalGameFrame;
        this.user = user;
        try {
            serverSocket = new ServerSocket(7656);
            System.out.println("Server started. Waiting for client connection...");
            WaitingForClient();
            EstablishConnectionWithClient();
            ExchangeNameWithClient();
            FetchCommandToStartTheGame();
            this.InGameInformationTransportation();
//            handleClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setWhetherDie(boolean whetherDie) {
        this.whetherDie = whetherDie;
    }

    public void setWhetherStart(boolean whetherStart) {
        this.whetherStart = whetherStart;
    }

    public void WaitingForClient() {
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void EstablishConnectionWithClient() {
        try {
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient() {
        try {
            while (true) {
                String receivedString = dataInputStream.readUTF();
                if (receivedString.equalsIgnoreCase("exit")) {
                    System.out.println("Client requested to exit.");
                    break;
                }
                System.out.println("Received from client: " + receivedString);

                String responseString = "Server received: " + receivedString;
                dataOutputStream.writeUTF(responseString);
                dataOutputStream.flush();
            }

            clientSocket.close();
            System.out.println("Client disconnected.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void ExchangeNameWithClient(){
        try {
            totalGameFrame.clientName = dataInputStream.readUTF();
            dataOutputStream.writeUTF(user.getUserName());
            dataOutputStream.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
        totalGameFrame.whetherSuccessfullyConnected = true;
    }
    private void FetchCommandToStartTheGame() throws IOException {
        while (!totalGameFrame.whetherStartTheMultiPlayerGame) {
            if (whetherStart) {
                whetherEnemyStart = dataInputStream.readBoolean();
                dataOutputStream.writeBoolean(true);
                dataOutputStream.flush();
            }
            totalGameFrame.whetherStartTheMultiPlayerGame = true;
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
        while (whetherEnemyDie&&whetherDie){
                enemyScore = dataInputStream.readInt();
                dataOutputStream.writeInt(totalGameFrame.getControllingCenter().getCurrentGameScore());
                dataOutputStream.flush();
            whetherShowDiePage =true;
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
