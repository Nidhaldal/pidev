package edu.nidhal.gui;

import edu.nidhal.tools.MyConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class CommandeStatsController {
    @FXML
    private Label resultLabel;

    @FXML
    private LineChart<String, Number> montantLineChart;
    @FXML
private LineChart<String, Number> commandsLineChart;

    @FXML
    private void calculateDailyCommands(ActionEvent event) {
        int dailyCommands = getDailyCommands();
        resultLabel.setText("Daily Commands: " + dailyCommands);
    }

    @FXML
    private void calculateDailyTotalAmount(ActionEvent event) {
        double dailyTotalAmount = getDailyTotalAmount();
        resultLabel.setText("Daily Total Amount: $" + dailyTotalAmount);
    }

    private int getDailyCommands() {
        try {
            // Establish a database connection using MyConnection class
            Connection connection = MyConnection.getInstance().getCnx();
            Statement statement = connection.createStatement();

            // Replace "commande" with your table name
            String sqlQuery = "SELECT COUNT(*) FROM commande WHERE date = CURRENT_DATE";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private double getDailyTotalAmount() {
        try {
            // Establish a database connection using MyConnection class
            Connection connection = MyConnection.getInstance().getCnx();
            Statement statement = connection.createStatement();

            // Replace "commande" with your table name
            String sqlQuery = "SELECT SUM(montant) FROM commande WHERE date = CURRENT_DATE";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    @FXML
public void initialize() {
    populateMontantLineChart();
    populateCommandsLineChart();
}

    // Method to populate the LineChart with montant data
    private void populateMontantLineChart() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Montant Evolution");

        try {
            // Establish a database connection using MyConnection class
            Connection connection = MyConnection.getInstance().getCnx();
            Statement statement = connection.createStatement();

            // Replace "commande" with your table name
            String sqlQuery = "SELECT date, SUM(montant) FROM commande GROUP BY date";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                String date = new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("date"));
                double montant = resultSet.getDouble("SUM(montant)");
                series.getData().add(new XYChart.Data<>(date, montant));
            }

            montantLineChart.getData().add(series);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void populateCommandsLineChart() {
    XYChart.Series<String, Number> series = new XYChart.Series<>();
    series.setName("Commands Per Day");

    try {
        // Establish a database connection using MyConnection class
        Connection connection = MyConnection.getInstance().getCnx();
        Statement statement = connection.createStatement();

        // Replace "commande" with your table name
        String sqlQuery = "SELECT date, COUNT(*) FROM commande GROUP BY date";
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while (resultSet.next()) {
            String date = new SimpleDateFormat("yyyy-MM-dd").format(resultSet.getDate("date"));
            int commandCount = resultSet.getInt("COUNT(*)");
            series.getData().add(new XYChart.Data<>(date, commandCount));
        }

        commandsLineChart.getData().add(series);

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
