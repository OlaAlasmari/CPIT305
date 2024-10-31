package pkg305;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel dashboardPanel;
    private Dashboard dashboard;

    public Main() {
        setTitle("Sport Tournament");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize CardLayout and main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Initialize Dashboard
        dashboardPanel = new JPanel(new BorderLayout());
        dashboard = new Dashboard(mainPanel, cardLayout, dashboardPanel);

        // Initialize Auth class for login and signup and pass the dashboard
        Auth auth = new Auth(mainPanel, cardLayout, dashboard);
        mainPanel.add(auth.createLoginScreen(), "loginScreen");
        mainPanel.add(auth.createSignupScreen(), "signupScreen");

        // Add the main screen from the dashboard
        mainPanel.add(dashboardPanel, "dashboardScreen");
        mainPanel.add(dashboard.createMainScreen(), "mainScreen");

        add(mainPanel);
        cardLayout.show(mainPanel, "mainScreen");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main gui = new Main();
            gui.setVisible(true);
        });
    }
}
