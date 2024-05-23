package Competition;

import GameVisual.TotalGameFrame;
import MultiUserSupply.User;

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
        try {
            Thread.sleep(3000); // 等待1秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        server= new Server(user,totalGameFrame);
    }
}
