package com.beginsecure.lastminute;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class HelloApplication extends Application {

    @Override
    public void start(Stage Stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("lastminutetry.fxml"));
        Stage.setTitle("Last minute");
        Stage.setScene(new Scene(root, 1200, 800));
        Stage.show();
    }

    public static void main(String[] args) {

        String path = System.getProperty("user.dir");

        String recipePath = path + "/recipesTEST.txt";
        File recipeTEST = new File(recipePath);
        if (recipeTEST.delete()) {
        } // *Delete recipeTEST file */

        recipePath = path + "/recipesTEST1.txt";
        File recipeTEST1 = new File(recipePath);
        if (recipeTEST1.delete()) {
        } // *Delete recipeTEST1 file */

        path = path + "/recipes";
        File file = new File(path);
        if (file.exists()) {
            // * Delete recipes directory */
            try (var dirStream = Files.walk(Paths.get(path))) {
                dirStream
                        .map(Path::toFile)
                        .sorted(Comparator.reverseOrder())
                        .forEach(File::delete);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        launch();
    }
}