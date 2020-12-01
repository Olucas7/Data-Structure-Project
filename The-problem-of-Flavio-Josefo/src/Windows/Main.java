/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Windows;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Jeanca
 */
public class Main extends Application {
    
       @Override
    public void start(Stage stage) throws Exception{
        
        MainWindow scene= new MainWindow(stage);
        stage.setScene(scene.getScene());
        stage.setTitle("GAME");
        stage.show();   
    }
    /**
     * @param args
     */
    public static void main (String[]args){
        Application.launch(args);
    }
    
}
