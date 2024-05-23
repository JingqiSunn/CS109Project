package Competition;

import GameVisual.TotalGameFrame;
import MultiUserSupply.User;

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
    public Server(User user, TotalGameFrame totalGameFrame) {
        this.whetherStart = false;
        this.whetherEnemyStart = false;
        this.totalGameFrame = totalGameFrame;
        this.user = user;
        try {
            serverSocket = new ServerSocket(7656);
            System.out.println("Server started. Waiting for client connection...");
            WaitingForClient();
            EstablishConnectionWithClient();
            ExchangeNameWithClient();
            FetchCommandToStartTheGame();
//            handleClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
