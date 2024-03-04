package it.unicam.cs.followme.app;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/SwarmSimulation.fxml")));
        Image icon=new Image("/images/icon.png");
        stage.getIcons().add(icon);
        Scene scene = new Scene(root);
        stage.setTitle("Robot Swarm Simulation");
        stage.setScene(scene);
        stage.show();
    }
}
