import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random; 
import java.util.ArrayList;
import java.util.List;

public class LoadBalancerGUI {
    private JFrame frame;
    private JTextArea logArea;
    private List<Server> servers;
    private LoadBalancer loadBalancer;
    private List<JProgressBar> serverProgressBars;
    private List<Client> clients; // Store client instances
    private Random random;
    
    public LoadBalancerGUI() {
        random = new Random();
        initialize();
        setupLoadBalancer();
    }

    private void initialize() {
        frame = new JFrame("Load Balancer Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        frame.add(new JScrollPane(logArea), BorderLayout.CENTER);

        // Panel for server load indicators
        JPanel serverPanel = new JPanel();
        serverPanel.setLayout(new GridLayout(3, 2));  // 3 servers, 2 columns
        serverProgressBars = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            JLabel label = new JLabel("Server " + (i + 1));
            JProgressBar progressBar = new JProgressBar();
            progressBar.setStringPainted(true);
            serverProgressBars.add(progressBar);
            panel.add(label, BorderLayout.NORTH);
            panel.add(progressBar, BorderLayout.CENTER);
            serverPanel.add(panel);
        }

        frame.add(serverPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start Simulation");
        JButton stopButton = new JButton("Stop Simulation");

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for buttons
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSimulation();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopSimulation();
            }
        });

        frame.setVisible(true);
    }

    private void setupLoadBalancer() {
        servers = new ArrayList<>();
        servers.add(new Server(1, random.nextInt(10) + 1, logArea, serverProgressBars.get(0)));  // Server 1
        servers.add(new Server(2, random.nextInt(10) + 1, logArea, serverProgressBars.get(1))); // Server 2
        servers.add(new Server(3, random.nextInt(10) + 1, logArea, serverProgressBars.get(2)));  // Server 3

        loadBalancer = new LoadBalancer(servers);
        clients = new ArrayList<>(); // Initialize the clients list
    }

    private void startSimulation() {
        for (Server server : servers) {
            new Thread(server).start();
        }
        // Start clients in separate threads
        for (int i = 0; i < 5; i++) {  // Example with 5 clients
            Client client = new Client(loadBalancer, logArea);
            clients.add(client); // Store the client instance
            new Thread(client).start();
        }
        logArea.append("Simulation started...\n");
    }

    private void stopSimulation() {
        logArea.append("Stopping simulation...\n");
        // Stop all clients
        for (Client client : clients) {
            client.stop();
        }
        // Stop all servers
        for (Server server : servers) {
            server.stop();
        }
        logArea.append("Simulation stopped.\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoadBalancerGUI::new);
    }
}
