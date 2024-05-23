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
    String serverName;
    int targetPort;
    String serverAddress;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    Scanner scanner;
    private AtomicBoolean running = new AtomicBoolean(true);

    public Client(int targetPort, User user, TotalGameFrame totalGameFrame) {
        this.totalGameFrame = totalGameFrame;
        this.user = user;
        this.targetPort = targetPort;
        ListenForBroadcast();
    }

    public void TryConnectToServer() {
        try {
            if (serverAddress == null || serverAddress.isEmpty()) {
                throw new IllegalStateException("Server address is not available.");
            }
            socket = new Socket(serverAddress, targetPort);
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
            serverName = dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void ListenForBroadcast() {
        new Thread(() -> {
            try {
                DatagramSocket socket = new DatagramSocket(8888);
                socket.setSoTimeout(800);
                InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                while (running.get()) {
                    try {
                        System.out.println("Waiting for broadcast message...");
                        socket.receive(packet);
                        String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                        System.out.println("Received message content: " + receivedMessage);
                        serverAddress = receivedMessage.substring(receivedMessage.indexOf(":") + 1).trim();
                        System.out.println("Server address extracted from message: " + serverAddress);
                        running.set(false);
                        TryConnectToServer(); // Attempt to connect to server after receiving broadcast
                    } catch (SocketTimeoutException e) {
                        // Timeout exception, continue listening
                    }
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
