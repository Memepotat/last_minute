package loginsystem.logreg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //APPLICATION WINDOW
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("loginorregister.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 400, 300);

        stage.setTitle("");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}