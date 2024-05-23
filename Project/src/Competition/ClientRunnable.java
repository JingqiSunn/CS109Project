package Competition;

import GameVisual.TotalGameFrame;
import MultiUserSupply.User;

public class ClientRunnable implements Runnable {
    private int port;
    String IPAddress;
    User user;
    Client client;
TotalGameFrame totalGameFrame;
    public ClientRunnable(String IPAddress, User user, TotalGameFrame totalGameFrame) {
        this.IPAddress = IPAddress;
        this.user = user;
        this.totalGameFrame = totalGameFrame;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public void run() {

        client= new Client(IPAddress,user,totalGameFrame);
    }
}
