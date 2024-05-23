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
    private AtomicBoolean running = new AtomicBoolean(true);

    public Client(String IPAddress, User user, TotalGameFrame totalGameFrame) {
        this.totalGameFrame = totalGameFrame;
        this.user = user;
        this.IPAddress =  IPAddress;
        this.TryConnectToServer();
        this.ExchangeNameWithServer();
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
}
