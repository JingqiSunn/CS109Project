package Competition;

import GameVisual.TotalGameFrame;
import MultiUserSupply.User;

import java.util.concurrent.CountDownLatch;

public class ServerRunnable implements Runnable {
    TotalGameFrame totalGameFrame;
    User user;
    Server server;
    public ServerRunnable( User user, TotalGameFrame totalGameFrame) {
        this.totalGameFrame = totalGameFrame;
        this.user = user;
    }

    public Server getServer() {
        return server;
    }

    @Override
    public void run() {
        server= new Server(user,totalGameFrame);
    }
}
