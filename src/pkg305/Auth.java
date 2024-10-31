package pkg305;

import javax.swing.*;
import java.awt.*;

public class Auth {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Dashboard dashboard; // Pass the existing dashboard object

    public Auth(JPanel mainPanel, CardLayout cardLayout, Dashboard dashboard) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.dashboard = dashboard; // Store the existing dashboard
    }

    // Create login screen
    public JPanel createLoginScreen() {
        JPanel loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/image/background.png"));
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        loginPanel.setLayout(null);

        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(new Color(255, 255, 255, 150));
        whitePanel.setBounds(300, 150, 400, 250);
        whitePanel.setLayout(null);
        whitePanel.setOpaque(true);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.TYPE1_FONT, 16));
        emailLabel.setBounds(50, 50, 100, 25);
        JTextField emailField = new JTextField();
        emailField.setBounds(150, 50, 200, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.TYPE1_FONT, 16));
        passwordLabel.setBounds(50, 100, 100, 25);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 200, 25);

        JButton loginBtn = new JButton("LOG IN");
        loginBtn.setFont(new Font("Arial", Font.TYPE1_FONT, 16));
        loginBtn.setBackground(Color.getHSBColor(20, 100, 10));
        loginBtn.setBounds(150, 180, 100, 30);

        loginBtn.addActionListener(e -> {
            // Show the existing dashboard
            dashboard.showDashboard(); 
            cardLayout.show(mainPanel, "dashboardScreen");
        });

        whitePanel.add(emailLabel);
        whitePanel.add(emailField);
        whitePanel.add(passwordLabel);
        whitePanel.add(passwordField);
        whitePanel.add(loginBtn);

        loginPanel.add(whitePanel);

        return loginPanel;
    }

    // Create signup screen
    public JPanel createSignupScreen() {
        JPanel signupPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/image/background.png"));
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        signupPanel.setLayout(null);

        JPanel whitePanel = new JPanel();
        whitePanel.setBackground(new Color(255, 255, 255, 150));
        whitePanel.setBounds(300, 160, 400, 350);
        whitePanel.setLayout(null);
        whitePanel.setOpaque(true);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.TYPE1_FONT, 16));
        nameLabel.setBounds(50, 50, 100, 25);
        JTextField nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 25);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setFont(new Font("Arial", Font.TYPE1_FONT, 16));
        phoneLabel.setBounds(50, 100, 100, 25);
        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 100, 200, 25);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.TYPE1_FONT, 16));
        emailLabel.setBounds(50, 150, 100, 25);
        JTextField emailField = new JTextField();
        emailField.setBounds(150, 150, 200, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.TYPE1_FONT, 16));
        passwordLabel.setBounds(50, 200, 100, 25);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 200, 200, 25);

        JButton signupBtn = new JButton("SIGN UP");
        signupBtn.setFont(new Font("Arial", Font.TYPE1_FONT, 16));
        signupBtn.setBackground(Color.getHSBColor(20, 100, 10));
        signupBtn.setBounds(150, 300, 100, 30);

        whitePanel.add(nameLabel);
        whitePanel.add(nameField);
        whitePanel.add(phoneLabel);
        whitePanel.add(phoneField);
        whitePanel.add(emailLabel);
        whitePanel.add(emailField);
        whitePanel.add(passwordLabel);
        whitePanel.add(passwordField);
        whitePanel.add(signupBtn);

        signupPanel.add(whitePanel);

        return signupPanel;
    }
}
