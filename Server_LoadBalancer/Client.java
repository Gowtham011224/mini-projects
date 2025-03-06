import javax.swing.*;
import java.util.Random;

public class Client implements Runnable {
    private LoadBalancer loadBalancer;
    private JTextArea logArea;
    private volatile boolean running = true; // Flag to control thread execution

    public Client(LoadBalancer lb, JTextArea logArea) {
        this.loadBalancer = lb;
        this.logArea = logArea;
    }

    @Override
    public void run() {
        try {
            Random rand = new Random();
            while (running) {
                int requestWeight = rand.nextInt(10) + 1;
                logArea.append("Client sending request with weight: " + requestWeight + "\n");
                loadBalancer.sendRequest(requestWeight);
                Thread.sleep(rand.nextInt(3000) + 1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logArea.append("Client has stopped sending requests.\n");
    }

    public void stop() {
        running = false; // Set the flag to false
    }
}
