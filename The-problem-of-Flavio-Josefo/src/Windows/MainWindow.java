/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Windows;

import DataStructure.CircularDouble;
import Objects.Person;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Jeanca
 */
public class MainWindow {

    public int comienzo;
    public int velocidad;
    public int desfase = 2;
    public CircularDouble<Person> personas;
    public int cantidadPersonas;
    public int personasVivas;
    public static Thread hilo;
    public static Runnable hiloControl;
    public ListIterator<Person> iter;

    //GUI
    private Label cant_personas;
    private TextField numeroPersonas;

    private Label posicion_comienzo;
    private TextField posicionInicial;
    private Button pausar;
    private Button reanudar;

    private StackPane PanelJuego;
    private Button left, right, start, restart;
    private Pane pane;
    private Scene scene;
    private Button crear;
    private Button mirar;
    String direccion;
    public boolean suspender;

    private BorderPane root;
    private VBox rightSide;
    private HBox buttons;
    
    private VBox buttons2;
    private VBox direc;

    public Image imagen_soldado_dorado;
    public Image imagen_soldado_azul;
    public Image imagen_soldado_muerto;

    public MainWindow(Stage stage) {
        HBox pyr = new HBox();

        HBox syr = new HBox();
        imagen_soldado_dorado = new Image("\\Imagens\\dorado.png");
        imagen_soldado_muerto = new Image("\\Imagens\\muerto1.png");
        pausar = new Button("Pausar");
        reanudar = new Button("Reanudar");
        mirar = new Button("Mirar");
        imagen_soldado_azul = new Image("\\Imagens\\imagen1.png");
        VBox arriba = new VBox();
        VBox medio = new VBox();
        crear = new Button("Crear");
        posicion_comienzo = new Label("Posición de la persona que comienza: ");
        cant_personas = new Label("Cantidad personas");
        numeroPersonas = new TextField();
        posicionInicial = new TextField();

        left = new Button("Izquierda");
        right = new Button("Derecha");
        start = new Button("Iniciar");
        restart = new Button("Reiniciar");

        buttons = new HBox();
        buttons.getChildren().addAll(left, right);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10.0);

        buttons2 = new VBox();
        buttons2.getChildren().addAll(start,restart);
        buttons2.setAlignment(Pos.CENTER);
        buttons2.setSpacing(10.0);
        
        medio.getChildren().addAll(posicion_comienzo, posicionInicial, mirar);
        medio.setAlignment(Pos.CENTER);
        medio.setSpacing(20.0);

        syr.getChildren().addAll(start, restart);
        syr.setAlignment(Pos.CENTER);
        syr.setSpacing(15);
        pyr.getChildren().addAll(pausar, reanudar);
        pyr.setAlignment(Pos.CENTER);
        pyr.setSpacing(15);

        rightSide = new VBox();
        arriba.getChildren().addAll(cant_personas, numeroPersonas, crear);
        arriba.setAlignment(Pos.CENTER);
        arriba.setSpacing(20);
        
        direc = new VBox();
        direc.getChildren().addAll( new Label("Direccion"), buttons);
        direc.setAlignment(Pos.CENTER);
        direc.setSpacing(20.0);
        
   
        pane = new StackPane();
        
        rightSide = new VBox();
        rightSide.getChildren().addAll(arriba,medio,direc, buttons2,syr,pyr);
        rightSide.setAlignment(Pos.CENTER);
        rightSide.setSpacing(60.0);
        rightSide.setId("derecho");
        eventos(stage);

        pane.setStyle("-fx-background-color:White");
        
        
         pane.setId("panel");
        root = new BorderPane();
        root.setCenter(pane);
        root.setRight(rightSide);

        scene = new Scene(root, 1000, 800);
        scene.getStylesheets().add("Windows/Viper.css");

    }

    private void eventos(Stage stage) {

        mirar.setOnAction(e -> {
            pane.getChildren().clear();
            comienzo = Integer.parseInt(posicionInicial.getText());
            personas = new CircularDouble<Person>();

            llenarJuego(personas, cantidadPersonas);
            posicion_comienzo.setText("Posición de la persona que comienza: " + (comienzo + 1));

        });

        crear.setOnAction(e -> {
            pane.getChildren().clear();
            comienzo = 1;

            int p = Integer.parseInt(numeroPersonas.getText());
            cantidadPersonas = p;

            personas = new CircularDouble<Person>();

            llenarJuego(personas, cantidadPersonas);
            personas.clear();

        });
        start.setOnAction(e -> {
            pane.getChildren().clear();
            int p = Integer.parseInt(numeroPersonas.getText());
            initialize(p);

            iter = personas.IterarNode(comienzo);
            hilo.start();

        });

        left.setOnAction(e -> {
            left.setDisable(true);
            String d = left.getText();
            direccion = d;
            right.setDisable(false);

        });

        right.setOnAction(e -> {

            right.setDisable(true);
            String d = right.getText();
            direccion = d;
            left.setDisable(false);
        });

        restart.setOnAction(e -> {
            pane.getChildren().clear();
            numeroPersonas.clear();
            personas.clear();
            posicionInicial.clear();
            right.setDisable(false);
            left.setDisable(false);
        });

        pausar.setOnAction(e -> {
            suspender = true;
        });

        reanudar.setOnAction(e -> {
            suspender = false;
        });

    }

    public Scene getScene() {
        return scene;
    }

    private void comenzarEnDireccionIndicada(ListIterator<Person> iter, String direccion) {
        personasVivas = cantidadPersonas - 1;
        if (direccion.equals("Izquierda")) {
            try {
                while (iter.hasNext()) {
                    Person soldadoParticipante = iter.next();
                    Person muerto = null;
                    if (soldadoParticipante.isAlive()) {
                        int desfaseTemp = desfase - 1;
                        while (desfaseTemp > 0) {
                            muerto = iter.next();
                            desfaseTemp--;
                        }
                        personas.remove(muerto);
                        muerto.setPersona_imagen(imagen_soldado_muerto);
                        personasVivas -= 1;
                    }
                    Thread.sleep(velocidad);
                    synchronized (this) {
                        while (suspender) {
                            wait(500);
                        }
                    }

                    if (personasVivas == 0) {
                        break;
                    }
                }
            } catch (Exception e) {
                System.err.print(e);
            }

        } else if (direccion.equals("Derecha")) {
            try {
                while (iter.hasPrevious()) {
                    Person soldadoParticipante = iter.previous();
                    Person muerto = null;
                    if (soldadoParticipante.isAlive()) {
                        int desfaseTemp = desfase - 1;
                        while (desfaseTemp > 0) {
                            muerto = iter.previous();
                            desfaseTemp--;
                        }
                        personas.remove(muerto);
                        muerto.setPersona_imagen(imagen_soldado_muerto);
                        personasVivas -= 1;
                    }
                    Thread.sleep(velocidad);
                    synchronized (this) {
                        while (suspender) {
                            wait(500);
                        }
                    }

                    if (personasVivas == 0) {
                        break;
                    }
                }
            } catch (Exception ex) {
                System.err.println(ex);
            }

        }
    }

    private void llenarJuego(CircularDouble<Person> personas, int valor) {
        for (int i = 0; i < valor; i++) {
            ImageView actual = new ImageView(imagen_soldado_azul);
            actual.setFitWidth(75);
            actual.setFitHeight(75);
            actual.setPreserveRatio(false);
            actual.setTranslateX(250 * Math.cos((((360 / (double) valor) * Math.PI) / 180) * i));
            actual.setTranslateY(250 * Math.sin((((360 / (double) valor) * Math.PI) / 180) * i));
            if (i == comienzo) {
                actual.setImage(imagen_soldado_dorado);
            }
            actual.setRotate((360 / (double) valor) * i + 90);
            Person p = new Person(actual, true);
            personas.addLast(p);
        }

        ListIterator<Person> iter = personas.listIterator();
        while (iter.hasNext()) {
            pane.getChildren().add(iter.next().getPersona_imagen());
        }
    }

    public void initialize(int i) {
        cantidadPersonas = i;
        velocidad = 500;

        desfase = 2;
        personas = new CircularDouble<Person>();
        llenarJuego(personas, cantidadPersonas);

        cant_personas.setText("Cantidad de personas: " + cantidadPersonas);
        posicion_comienzo.setText("Posición de la persona que comienza: " + (comienzo + 1));

        hiloControl = new Runnable() {
            @Override
            public void run() {
                comenzarEnDireccionIndicada(iter, direccion);
            }
        };
        hilo = new Thread(hiloControl);
    }

}
