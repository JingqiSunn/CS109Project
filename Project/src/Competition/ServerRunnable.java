package Competition;

import GameVisual.TotalGameFrame;
import MultiUserSupply.User;

public class ServerRunnable implements Runnable {
    TotalGameFrame totalGameFrame;
    private int port;
    User user;
    Server server;
    public ServerRunnable(int port, User user, TotalGameFrame totalGameFrame) {
        this.totalGameFrame = totalGameFrame;
        this.port = port;
        this.user = user;
    }

    public Server getServer() {
        return server;
    }

    @Override
    public void run() {
        server= new Server(port,user,totalGameFrame);
    }
}
