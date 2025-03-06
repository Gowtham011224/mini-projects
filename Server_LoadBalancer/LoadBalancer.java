import java.util.List;

public class LoadBalancer {
    private final List<Server> servers;

    public LoadBalancer(List<Server> servers) {
        this.servers = servers;
    }

    public synchronized void sendRequest(int requestWeight) {
        // Find the server with the least weighted connections
        Server bestServer = null;
        double minLoadFactor = Double.MAX_VALUE;

        for (Server server : servers) {
            double loadFactor = (double) server.getCurrentLoad() / server.getWeight();
            if (loadFactor < minLoadFactor) {
                minLoadFactor = loadFactor;
                bestServer = server;
            }
        }

        if (bestServer != null) {
            bestServer.handleRequest(requestWeight);
        } else {
            System.out.println("No available server to handle the request.");
        }
    }
}
