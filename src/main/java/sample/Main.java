package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        System.setProperty("prism.text", "t2k");
        System.setProperty("prism.lcdtext", "true");

        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Firebase Sender FX");
        primaryStage.setScene(new Scene(root, 512, 256));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


}
