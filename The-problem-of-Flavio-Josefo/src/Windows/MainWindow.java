/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Windows;

import Objects.Person;
import Objects.Simulacion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 *
 * @author Jeanca
 */
public class MainWindow {

    private VBox medio;
    private VBox direction;
    private HBox cw;
    private HBox ccw;
    private RadioButton bcw;
    private RadioButton bccw;
    private Label ldirection;
    private HBox elem;
    private VBox per;
    static final String STRINGIMAGE = "/Imagens/";
    private final Simulacion simu;
    private Button start, leave;
    private BorderPane root;
    private HBox buttons;
    private Scene scene;
    private Label title;
    public ImageView imagen;
    private Pane rounds;

    public MainWindow(Stage stage) {
        medio = new VBox();
        elem = new HBox();
        simu = new Simulacion();
        start = new Button("START");
        start.setPrefSize(180, 50);
        leave = new Button("LEAVE");
        leave.setPrefSize(180, 50);

        title = new Label("EL PROBLEMA DE FLAVIO JOSEFO");
        title.setId("title-titulo");
        title.setAlignment(Pos.CENTER);

        // imagen = mostrarImagen("Imagens", "main2.png", 400, 400);
        buttons = new HBox();
        buttons.getChildren().addAll(start, leave);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(40.0);

        root = new BorderPane();
        root.setBottom(buttons);
        root.setTop(title);

        createOp();
        scene = new Scene(root, 800, 800);
        scene.getStylesheets().add("Windows/Viper.css");

        eventos(stage);
    }

    public Scene getScene() {
        return scene;
    }

    private void eventos(Stage stage) {
        leave.setOnAction(e -> {
            stage.close();
        });
        start.setOnAction(e -> {
            SimulationWindow v = new SimulationWindow(stage);
            stage.setScene(v.getScene());
        });

    }

    private VBox createPersonas() {
        Label t = new Label("Numero de Personas: ");
        TextField cantidad_personas = new TextField();
        Button b = new Button("Crear");
        b.setOnMouseClicked((e) -> {
            rounds.getChildren().clear();
            showPersonas(Integer.parseInt(cantidad_personas.getText()));

        });

        return new VBox(t, cantidad_personas, b);
    }

    private void showPersonas(int quantity) {
        int grados =0;

        double angle = Math.toRadians(Simulacion.GRADES / quantity);
        for (int i = 0; i < quantity; i++) {
            Person p = new Person(i + 1, angle * i);
            simu.personas.addLast(p);
            ImageView iv = new ImageView(new Image(getClass().getResource(STRINGIMAGE + "imagen1" + ".png").toString()));

            iv.setFitHeight(75);
            iv.setFitWidth(75);
            iv.setLayoutX(p.getPosition()[0]);
            iv.setLayoutY(p.getPosition()[1]);
            iv.setRotate(iv.getRotate() + grados+90);
            grados += Simulacion.GRADES / quantity;
            rounds.getChildren().addAll(iv);
        }
    }

    private void createOp() {
        rounds = new Pane();
        rounds.setMaxSize(650, 450);
        rounds.setMinSize(650, 450);
        rounds.setStyle("-fx-background-color:White");
        per = createPersonas();
        per.setSpacing(10);

        direction = createDirections();
        direction.setSpacing(10);

        elem.getChildren().addAll(per, direction);
        elem.setAlignment(Pos.CENTER);
        elem.setMinHeight(110);
        elem.setSpacing(60);
        elem.setPadding(new Insets(20, 20, 20, 20));
        medio.getChildren().addAll(elem, rounds);
        medio.setAlignment(Pos.CENTER);
        root.setCenter(medio);
        //elem.setStyle("-fx-background-color:#DAC1B7");
    }

    private VBox createDirections() {
        ldirection = new Label("Sentido: ");
        ToggleGroup group = new ToggleGroup();
        bcw = new RadioButton("Horario");
        bcw.setSelected(true);
        bcw.setToggleGroup(group);
        bccw = new RadioButton("Antihorario");
        bccw.setToggleGroup(group);

        cw = new HBox(bcw);
        cw.setSpacing(63);
        ccw = new HBox(bccw);
        ccw.setSpacing(20);
        return new VBox(ldirection, cw, ccw);
    }
}
