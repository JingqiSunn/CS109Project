package Competition;

import GameVisual.TotalGameFrame;
import MultiUserSupply.User;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    User user;
    TotalGameFrame totalGameFrame;
    String serverName;
    int targetPort;
    int serverAddress;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Scanner scanner;
    public Client(int targetPort, User user, TotalGameFrame totalGameFrame){
        this.totalGameFrame = totalGameFrame;
        this.user = user;
        this.targetPort = targetPort;
        ListenForBroadcast();
        this.TryConnectToServer();
        this.ExchangeNameWithServer();
        System.out.println(serverName);
        this.CommunicationWithServer();
    }
    public void TryConnectToServer(){
        try {
            socket = new Socket(String.valueOf(serverAddress), targetPort);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            scanner = new Scanner(System.in);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    private void ExchangeNameWithServer(){
        try {
            dataOutputStream.writeUTF(user.getUserName());
            dataOutputStream.flush();
            serverName = dataInputStream.readUTF();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void CommunicationWithServer(){
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
    private void ListenForBroadcast() {
        try {
            DatagramSocket socket = new DatagramSocket(8888);
            InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            String receivedMessage = new String(packet.getData(), 0, packet.getLength());
            serverAddress = Integer.parseInt(receivedMessage.substring(receivedMessage.lastIndexOf(":") + 1));
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
