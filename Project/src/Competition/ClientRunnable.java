package Competition;

import GameVisual.TotalGameFrame;
import MultiUserSupply.User;

public class ClientRunnable implements Runnable {
    private int port;
    String IPAddress;
    User user;
TotalGameFrame totalGameFrame;
    public ClientRunnable(String IPAddress, User user, TotalGameFrame totalGameFrame) {
        this.IPAddress = IPAddress;
        this.user = user;
        this.totalGameFrame = totalGameFrame;
    }

    @Override
    public void run() {
        new Client(IPAddress,user,totalGameFrame);
    }
}
