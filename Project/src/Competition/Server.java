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
    private BroadcastThread broadcastThread;


    public Server(int portNumber, User user, TotalGameFrame totalGameFrame) {
        this.totalGameFrame = totalGameFrame;
        this.user = user;
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("Server started. Waiting for client connection...");
            broadcastThread = new BroadcastThread();
            broadcastThread.start();
            WaitingForClient();
            EstablishConnectionWithClient();
            ExchangeNameWithClient();
            System.out.println(totalGameFrame.clientName);
//            handleClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WaitingForClient() {
        try {
            clientSocket = serverSocket.accept();
            broadcastThread.stopThread();
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
    }

    private class BroadcastThread extends Thread {
        private volatile boolean running = true;

        @Override
        public void run() {
            try {
                DatagramSocket broadcastSocket = new DatagramSocket();
                broadcastSocket.setBroadcast(true);
                InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
                InetAddress localAddress = InetAddress.getLocalHost();
                String message = "Server IP Address is: " + localAddress.getHostAddress();
                DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), broadcastAddress, 8888);
                while (running) {
                    broadcastSocket.send(packet);
                    Thread.sleep(1000);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void stopThread() {
            running = false;
        }
    }
}
