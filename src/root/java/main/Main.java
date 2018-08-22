package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.application.Application.launch;


public class Main extends Application {

    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("AutomataBuilder.fxml"));

        Scene scene = new Scene(root, 600, 800);

        stage.setTitle("Automata Builder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args)
    {
        launch(args);
    }
}
