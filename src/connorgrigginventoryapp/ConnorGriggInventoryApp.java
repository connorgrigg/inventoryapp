/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connorgrigginventoryapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.*;
import java.io.*;
/**
 *
 * @author connor
 */
public class ConnorGriggInventoryApp extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(ConnorGriggInventoryApp.class.getResource("/View_Controller/MainScreen.fxml"));
       Parent root = loader.load();  
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }

    /**
     * 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
