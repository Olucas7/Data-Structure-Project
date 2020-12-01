/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Windows;

import Objects.Person;
import Objects.Simulacion;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Jeanca
 */
public class SimulationWindow {
    private Button back;
    private VBox root ;
     private BorderPane borderPane;
     private Scene scene;
     public SimulationWindow(Stage stage){
        back = new Button("Back");
        root=new VBox();
        root.getChildren().addAll(back);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(8.0);
        
        borderPane = new BorderPane();
        borderPane.setCenter(root);
        
        scene=new Scene(borderPane,800, 800);
        scene.getStylesheets().add("Windows/Viper.css");
        eventos(stage);
    }

    public Scene getScene() {
        return scene;
    }

     
    private void eventos(Stage stage) {
        back.setOnAction(e ->{
            MainWindow v = new MainWindow(stage);
            stage.setScene(v.getScene());
        });
    }
    

     
}
