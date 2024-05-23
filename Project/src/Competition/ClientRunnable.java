package Competition;

import GameVisual.TotalGameFrame;
import MultiUserSupply.User;

import java.io.IOException;

public class ClientRunnable implements Runnable {
    private String IPAddress;
    private User user;
    private Client client;
    private TotalGameFrame totalGameFrame;

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
        try {
            client = new Client(IPAddress, user, totalGameFrame);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        if (client != null) {
            client.stop();
        }
    }
}
