package Competition;

import GameVisual.TotalGameFrame;
import MultiUserSupply.User;

public class ClientRunnable implements Runnable {
    private int port;
    User user;
TotalGameFrame totalGameFrame;
    public ClientRunnable(int port, User user, TotalGameFrame totalGameFrame) {
        this.port = port;
        this.user = user;
        this.totalGameFrame = totalGameFrame;
    }

    @Override
    public void run() {
        new Client(port,user,totalGameFrame);
    }
}
