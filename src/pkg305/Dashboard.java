package pkg305;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dashboard {

    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel dashboardPanel;
    private VBox resultArea;

    public Dashboard(JPanel mainPanel, CardLayout cardLayout, JPanel dashboardPanel) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        this.dashboardPanel = dashboardPanel;
    }

    // Create the main screen for the app
    public JPanel createMainScreen() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/image/background.png"));
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        JLabel sportLabel = new JLabel("SPORT");
        sportLabel.setFont(new Font("Arial", Font.TYPE1_FONT, 50));
        sportLabel.setForeground(Color.BLACK);
        sportLabel.setBounds(400, 100, 210, 240);
        panel.add(sportLabel);

        JLabel tournamentLabel = new JLabel("TOURNAMENT");
        tournamentLabel.setFont(new Font("Arial", Font.TYPE1_FONT, 50));
        tournamentLabel.setForeground(Color.BLACK);
        tournamentLabel.setBounds(300, 200, 360, 200);
        panel.add(tournamentLabel);

        JButton loginButton = new JButton("LOG IN");
        loginButton.setBounds(310, 400, 150, 40);
        loginButton.setFont(new Font("Arial", Font.TYPE1_FONT, 16));
        loginButton.setBackground(Color.getHSBColor(20, 100, 10));
        loginButton.addActionListener(e -> cardLayout.show(mainPanel, "loginScreen"));
        panel.add(loginButton);

        JButton signupButton = new JButton("SIGN UP");
        signupButton.setBounds(500, 400, 150, 40);
        signupButton.setFont(new Font("Arial", Font.TYPE1_FONT, 16));
        signupButton.setBackground(Color.getHSBColor(20, 100, 10));
        signupButton.addActionListener(e -> cardLayout.show(mainPanel, "signupScreen"));
        panel.add(signupButton);

        return panel;
    }

    // Show dashboard method
    public void showDashboard() {
        JFXPanel fxPanel = new JFXPanel();
        dashboardPanel.add(fxPanel, BorderLayout.CENTER);

        Platform.runLater(() -> {
            BorderPane mainLayout = new BorderPane();
            Image bgImage = new Image(getClass().getResource("/image/2.png").toString());

            if (bgImage != null) {
                BackgroundImage backgroundImage = new BackgroundImage(bgImage,
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
                mainLayout.setBackground(new Background(backgroundImage));
            }

            VBox sidebar = createSidebar();
            mainLayout.setLeft(sidebar);

            VBox mainContent = new VBox(10);
            mainContent.setPadding(new Insets(20));
            mainContent.setAlignment(Pos.TOP_CENTER);

            Label header = new Label("Dashboard");
            header.setStyle("-fx-font-size: 30px; -fx-text-fill: #a3c1a4; -fx-font-weight: bold;");
            mainContent.getChildren().add(header);

            resultArea = new VBox();
            resultArea.setPadding(new Insets(10));
            resultArea.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); -fx-border-color: #BDC3C7; -fx-border-radius: 5px;");
            resultArea.setPrefHeight(400);
            mainContent.getChildren().add(resultArea);

            mainLayout.setCenter(mainContent);

            Scene scene = new Scene(mainLayout, 1000, 600);
            fxPanel.setScene(scene);
        });
    }

    // Sidebar creation with custom buttons
    private VBox createSidebar() {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: rgba(179, 210, 150, 1); -fx-border-radius: 5px;");

        // Add the buttons to the sidebar
        sidebar.getChildren().add(createSidebarButton("Home", e -> showHome()));
        sidebar.getChildren().add(createSidebarButton("Teams", e -> viewTeams()));
        sidebar.getChildren().add(createSidebarButton("Add Team", e -> showAddTeamForm()));
        sidebar.getChildren().add(createSidebarButton("Match Results", e -> viewMatchResults()));
        sidebar.getChildren().add(createSidebarButton("Schedule Match", e -> scheduleMatch()));

        return sidebar;
    }

    // Custom styled sidebar buttons
    private javafx.scene.control.Button createSidebarButton(String text, EventHandler<javafx.event.ActionEvent> action) {
        javafx.scene.control.Button button = new javafx.scene.control.Button(text);

        // Style the button as per your original design
        button.setStyle("-fx-background-color: rgb(252, 244, 204); -fx-text-fill: #a3c1a4; -fx-font-size: 16px; -fx-padding: 10px; -fx-background-radius: 20px;");
        button.setOnAction(action);

        // Add hover effect
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: rgba(230, 230, 150, 1); -fx-text-fill: #a3c1a4; -fx-font-size: 16px; -fx-padding: 10px; -fx-background-radius: 20px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: rgb(252, 244, 204); -fx-text-fill: #a3c1a4; -fx-font-size: 16px; -fx-padding: 10px; -fx-background-radius: 20px;"));

        button.setPrefWidth(200);  // Set width of the button to ensure uniformity
        button.setAlignment(Pos.CENTER_LEFT);  // Align text to the left

        return button;
    }

    // Show home screen
    private void showHome() {
        Platform.runLater(() -> {
            VBox homeLayout = new VBox(20);
            homeLayout.setPadding(new Insets(20));
            homeLayout.setAlignment(Pos.TOP_CENTER);

            // Title label
            Label titleLabel = new Label("WELCOME TO SPORT TOURNAMENT APPLICATION !!");
            titleLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold; -fx-text-fill: #a3c1a4;");
            homeLayout.getChildren().add(titleLabel);

            // Recent Match Box
            VBox matchBox = new VBox(10);
            matchBox.setAlignment(Pos.CENTER);
            matchBox.setPadding(new Insets(20));
            matchBox.setStyle("-fx-background-color: rgba(240, 240, 240, 0.7); -fx-border-color: #BDC3C7; -fx-border-radius: 5px;");

            Label recentMatchLabel = new Label("RECENT MATCH");
            recentMatchLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

            HBox matchContent = new HBox(20);
            matchContent.setAlignment(Pos.CENTER);

            Image team1Logo = new Image(getClass().getResource("/image/Team1.png").toString());
            Image team2Logo = new Image(getClass().getResource("/image/Team2.png").toString());

            ImageView team1ImageView = new ImageView(team1Logo);
            team1ImageView.setFitHeight(90);
            team1ImageView.setFitWidth(70);

            ImageView team2ImageView = new ImageView(team2Logo);
            team2ImageView.setFitHeight(90);
            team2ImageView.setFitWidth(95);

            Label matchResult = new Label("  2 - 1");
            matchResult.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

            matchContent.getChildren().addAll(team1ImageView, matchResult, team2ImageView);
            matchBox.getChildren().addAll(recentMatchLabel, matchContent);
            homeLayout.getChildren().add(matchBox);

            // Best Coach Section
            VBox coachBox = new VBox(10);
            coachBox.setAlignment(Pos.CENTER);
            coachBox.setPadding(new Insets(20));
            coachBox.setStyle("-fx-background-color: rgba(240, 240, 240, 0.7); -fx-border-color: #BDC3C7; -fx-border-radius: 5px;");

            Label bestCoachLabel = new Label("BEST COACH");
            bestCoachLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

            HBox coachDetails = new HBox(20);
            coachDetails.setAlignment(Pos.CENTER);

            // Coach Name and Team Name
            TextField coachName = new TextField("Coach Name");
            coachName.setPrefWidth(200);
            coachName.setStyle("-fx-background-color: rgba(200, 200, 200, 0.5); -fx-font-size: 18px; -fx-font-weight: bold;");
            coachName.setEditable(false); // Non-editable field

            TextField teamName = new TextField("Team");
            teamName.setPrefWidth(200);
            teamName.setStyle("-fx-background-color: rgba(200, 200, 200, 0.5); -fx-font-size: 18px; -fx-font-weight: bold;");
            teamName.setEditable(false); // Non-editable field

            coachDetails.getChildren().addAll(coachName, teamName);

            // Add Best Coach details to the layout
            coachBox.getChildren().addAll(bestCoachLabel, coachDetails);
            homeLayout.getChildren().add(coachBox);

            // Add the home layout to the result area
            resultArea.getChildren().clear();
            resultArea.getChildren().add(homeLayout);
        });
    }

    // Add Team Form
    private VBox createAddTeamForm() {
        VBox form = new VBox(10);
        form.setPadding(new Insets(20));
        form.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #BDC3C7; -fx-border-radius: 5px; -fx-padding: 15px;");
        form.setAlignment(Pos.CENTER);

        Label title = new Label("Add Team");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #a3c1a4;");
        form.getChildren().add(title);

        TextField nameField = new TextField();
        nameField.setPromptText("Team Name");
        nameField.setMaxWidth(300);

        TextField coachField = new TextField();
        coachField.setPromptText("Coach Name");
        coachField.setMaxWidth(300);

        TextField playersField = new TextField();
        playersField.setPromptText("Number of Players");
        playersField.setMaxWidth(300);

        TextField matchTypeField = new TextField();
        matchTypeField.setPromptText("Match Type");
        matchTypeField.setMaxWidth(300);

        Button addButton = new Button("Add Team");
        addButton.setStyle("-fx-background-color: #e9f5e9; -fx-text-fill: #4a4a4a; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px; -fx-background-radius: 5px;");
        addButton.setMaxWidth(150);
        addButton.setAlignment(Pos.CENTER);

        // Action to add team to the database
        addButton.setOnAction(e -> {
            String teamName = nameField.getText();
            String coachName = coachField.getText();
            int numberOfPlayers = Integer.parseInt(playersField.getText());
            String matchType = matchTypeField.getText();

            // Add the team to the database
            try (Connection connection = DatabaseConnection.getConnection();
                    PreparedStatement statement = connection.prepareStatement("INSERT INTO Teams (team_name, coach_name, number_of_players, sport_type) VALUES (?, ?, ?, ?)")) {

                statement.setString(1, teamName);
                statement.setString(2, coachName);
                statement.setInt(3, numberOfPlayers);
                statement.setString(4, matchType);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(null, "Team added successfully!!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to add team to the database.");
            }
        });

        form.getChildren().addAll(nameField, coachField, playersField, matchTypeField, addButton);
        return form;
    }

    private void showAddTeamForm() {
        Platform.runLater(() -> {
            VBox form = createAddTeamForm();
            resultArea.getChildren().clear();
            resultArea.getChildren().add(form);
        });
    }

    // View Teams
    private void viewTeams() {
        Platform.runLater(() -> {
            VBox teamLayout = new VBox(20);
            teamLayout.setPadding(new Insets(20));
            teamLayout.setAlignment(Pos.TOP_CENTER);

            Label titleLabel = new Label("Team List");
            titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #a3c1a4;");

            TableView<Team> teamTable = new TableView<>();
            teamTable.setPrefSize(400, 400);

            TableColumn<Team, String> nameColumn = new TableColumn<>("Team Name");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            nameColumn.setPrefWidth(150);

            TableColumn<Team, String> coachColumn = new TableColumn<>("Coach Name");
            coachColumn.setCellValueFactory(new PropertyValueFactory<>("coach"));
            coachColumn.setPrefWidth(150);

            TableColumn<Team, Integer> playersColumn = new TableColumn<>("Players");
            playersColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfPlayers"));
            playersColumn.setPrefWidth(150);

            TableColumn<Team, String> sportColumn = new TableColumn<>("Sport Type");
            sportColumn.setCellValueFactory(new PropertyValueFactory<>("matchType"));
            sportColumn.setPrefWidth(150);

            teamTable.getColumns().addAll(nameColumn, coachColumn, playersColumn, sportColumn);

            teamLayout.getChildren().addAll(titleLabel, teamTable);

            resultArea.getChildren().clear();
            resultArea.getChildren().add(teamLayout);
        });
    }

    // View Match Results
    private void viewMatchResults() {
        Platform.runLater(() -> {
            VBox matchResultsLayout = new VBox(10);
            matchResultsLayout.setPadding(new Insets(20));
            matchResultsLayout.setAlignment(Pos.TOP_CENTER);

            Label titleLabel = new Label("Match History");
            titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #a3c1a4;");

            Image image = new Image(getClass().getResource("/image/Cup2.png").toString());
            ImageView awardIcon = new ImageView(image);
            awardIcon.setFitHeight(75);
            awardIcon.setFitWidth(60);

            HBox headerBox = new HBox(10);
            headerBox.setAlignment(Pos.CENTER);
            headerBox.getChildren().addAll(titleLabel, awardIcon);

            VBox matchList = new VBox(10);
            matchList.setPadding(new Insets(10));
            matchList.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); -fx-border-color: #BDC3C7; -fx-border-radius: 5px;");
            matchList.setPrefHeight(400);

            for (int i = 1; i <= 5; i++) {
                VBox matchBox = new VBox(5);
                matchBox.setStyle("-fx-background-color: rgba(240, 240, 240, 1); -fx-border-color: #BDC3C7; -fx-border-radius: 5px;");
                matchBox.setPadding(new Insets(10));
                matchBox.setAlignment(Pos.CENTER);

                Label matchLabel = new Label("Match " + i + ": Team A vs Team B - Result: " + i + " - " + (i + 1));
                matchLabel.setStyle("-fx-font-size: 16px;");

                matchBox.getChildren().add(matchLabel);
                matchList.getChildren().add(matchBox);
            }

            resultArea.getChildren().clear();
            resultArea.getChildren().addAll(headerBox, matchList);
        });
    }

    // Schedule Match
    private void scheduleMatch() {
        Platform.runLater(() -> {
            VBox scheduleMatchLayout = new VBox(15);
            scheduleMatchLayout.setPadding(new Insets(20));
            scheduleMatchLayout.setAlignment(Pos.TOP_CENTER);
            scheduleMatchLayout.setStyle("-fx-background-color: #f7f7f7; -fx-border-color: #a3c1a4; -fx-border-radius: 10px; -fx-padding: 20px;");

            Label titleLabel = new Label("Schedule a Match");
            titleLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: #4a4a4a; -fx-font-weight: bold;");

            TextField sportTypeField = new TextField();
            sportTypeField.setPromptText("Enter Sport Type");
            sportTypeField.setMaxWidth(400);
            sportTypeField.setMinHeight(40);
            sportTypeField.setStyle("-fx-padding: 10px; -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #4a4a4a; -fx-background-color: #FFFFFF; -fx-border-color: #a3c1a4; -fx-border-radius: 10px;");

            ComboBox<String> team1ComboBox = new ComboBox<>();
            ComboBox<String> team2ComboBox = new ComboBox<>();
            team1ComboBox.getItems().addAll("Team A", "Team B", "Team C");
            team2ComboBox.getItems().addAll("Team A", "Team B", "Team C");
            team1ComboBox.setPromptText("Select Team 1");
            team2ComboBox.setPromptText("Select Team 2");
            team1ComboBox.setMaxWidth(400);
            team2ComboBox.setMaxWidth(400);
            team1ComboBox.setMinHeight(40);
            team2ComboBox.setMinHeight(40);

            HBox teamSelectionBox = new HBox(20);
            teamSelectionBox.setAlignment(Pos.CENTER);
            teamSelectionBox.getChildren().addAll(team1ComboBox, team2ComboBox);

            DatePicker datePicker = new DatePicker();
            datePicker.setPromptText("Select Match Date");
            datePicker.setMaxWidth(400);
            datePicker.setMinHeight(40);

            ComboBox<Integer> hourComboBox = new ComboBox<>();
            ComboBox<Integer> minuteComboBox = new ComboBox<>();
            for (int i = 0; i < 24; i++) {
                hourComboBox.getItems().add(i);
            }
            for (int i = 0; i < 60; i += 5) {
                minuteComboBox.getItems().add(i);
            }

            hourComboBox.setPromptText("Hour");
            minuteComboBox.setPromptText("Minute");
            hourComboBox.setMaxWidth(150);
            minuteComboBox.setMaxWidth(150);

            HBox timeBox = new HBox(20);
            timeBox.setAlignment(Pos.CENTER);
            timeBox.getChildren().addAll(hourComboBox, minuteComboBox);

            Button saveButton = new Button("Save Match Schedule");
            saveButton.setStyle("-fx-background-color: #e9f5e9; -fx-text-fill: #4a4a4a; -fx-font-size: 16px; -fx-font-weight: bold; -fx-border-radius: 10px; -fx-padding: 10px;");
            saveButton.setMaxWidth(250);

            saveButton.setOnAction(e -> {
                String team1 = team1ComboBox.getValue();
                String team2 = team2ComboBox.getValue();
                LocalDate matchDate = datePicker.getValue();
                Integer matchHour = hourComboBox.getValue();
                Integer matchMinute = minuteComboBox.getValue();
                String sportType = sportTypeField.getText();

                // Logic for saving match schedule goes here
            });

            scheduleMatchLayout.getChildren().addAll(titleLabel, sportTypeField, teamSelectionBox, datePicker, timeBox, saveButton);

            resultArea.getChildren().clear();
            resultArea.getChildren().add(scheduleMatchLayout);
        });
    }
}
