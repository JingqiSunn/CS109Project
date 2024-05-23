package Competition;

import GameVisual.TotalGameFrame;
import MultiUserSupply.User;

public class ServerRunnable implements Runnable {
    TotalGameFrame totalGameFrame;
    User user;
    Server server;
    private volatile boolean running = true;
    public ServerRunnable( User user, TotalGameFrame totalGameFrame) {
        this.totalGameFrame = totalGameFrame;
        this.user = user;
    }

    public Server getServer() {
        return server;
    }
    public void stop() {
        running = false;
        if (server != null) {
            server.stopServer();
        }
        if (thread != null) {
            thread.interrupt();
        }
    }

    @Override
    public void run() {
        server= new Server(user,totalGameFrame);
    }
}
