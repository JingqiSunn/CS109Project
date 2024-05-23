package Competition;

import GameVisual.TotalGameFrame;
import MultiUserSupply.User;

public class ServerRunnable implements Runnable {
    private TotalGameFrame totalGameFrame;
    private User user;
    private Server server;
    private volatile boolean running = true;

    public ServerRunnable(User user, TotalGameFrame totalGameFrame) {
        this.totalGameFrame = totalGameFrame;
        this.user = user;
    }

    public Server getServer() {
        return server;
    }

    @Override
    public void run() {
        server = new Server(user, totalGameFrame);
        while (running && !Thread.currentThread().isInterrupted()) {
        }
    }

    public void stop() {
        running = false;
        if (server != null) {
            server.stop();
        }
    }
}
