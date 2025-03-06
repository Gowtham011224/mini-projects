import javax.swing.*;

public class Server implements Runnable {
    private final int serverId;
    private final int weight;
    private int currentLoad = 0;
    private JTextArea logArea;
    private JProgressBar progressBar;
    private volatile boolean running = true; // Flag to control thread execution

    public Server(int serverId, int weight, JTextArea logArea, JProgressBar progressBar) {
        this.serverId = serverId;
        this.weight = weight;
        this.logArea = logArea;
        this.progressBar = progressBar;
    }

    public synchronized int getCurrentLoad() {
        return currentLoad;
    }

    public synchronized void handleRequest(int requestWeight) {
        currentLoad += requestWeight;
        updateProgressBar();

        logArea.append("Server " + serverId + " handling request. Current Load: " + currentLoad + "\n");
        new Thread(() -> {
            try {
                Thread.sleep(requestWeight * 1000);  // Simulate processing time
                finishRequest(requestWeight);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private synchronized void finishRequest(int requestWeight) {
        currentLoad -= requestWeight;
        updateProgressBar();
        logArea.append("Server " + serverId + " finished request. Current Load: " + currentLoad + "\n");
    }

    private void updateProgressBar() {
        int progress = (int) ((currentLoad / (double) weight) * 100);
        progressBar.setValue(progress);
        progressBar.setString("Load: " + currentLoad + "/" + weight);
    }

    public void stop() {
        running = false; // Set the flag to false
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);  // Keep the server alive
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logArea.append("Server " + serverId + " has stopped.\n");
    }

    public int getWeight() {
        return weight;
    }
}
