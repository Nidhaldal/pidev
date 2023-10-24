/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.nidhal.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PanierItemApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
       FXMLLoader loader = new FXMLLoader(getClass().getResource("PanierItemWindow.fxml"));
       Parent root = loader.load();
Scene scene = new Scene(root);
primaryStage.setScene(scene);
primaryStage.setTitle("Panier Item Window");
primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
