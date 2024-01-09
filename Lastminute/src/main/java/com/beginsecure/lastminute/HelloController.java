package com.beginsecure.lastminute;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.TextFlow;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ArrayList;

public class HelloController {
    @FXML
    private Pane mainscreen;

    @FXML
    private ScrollPane ingredientScrollPane,executionScrollPane;

    @FXML
    private TextField ingredientinput,Username;

    @FXML
    private ChoiceBox<String> skillBox;

    @FXML
    private ChoiceBox<String> timeBox;

    @FXML
    private ImageView gifImageView;

    @FXML
    private Button Buttonforrecipes,Exitapplication,Next,Previous,SaveRecipe,ShowSavedRecipes,Exittofirstscreen,Loginbutton,RegisterButton,GuestButton;

    @FXML
    private Label wait;

    @FXML
    private PasswordField Password;

    @FXML
    private TextFlow recipeingredients,recipeexecution;

    private int i = 0,max=1000, numberofrecipe=0;
    private boolean[] saved = {false,false,false,false,false};
    private boolean New = true;
    private String Title,Skill,Duration,Ingredients,Execution;

    private ArrayList<Integer> commonFilters = new ArrayList<>();

    void ReplaceGif() {
        // Load a new image and set it to the ImageView
        Image newImage = new Image(getClass().getResourceAsStream("/com/beginsecure/lastminute/penguin-frame.png"));
        gifImageView.setImage(newImage);
    }
    void Initiateappuse(){
        ingredientinput.clear(); // Clear the text field
        ingredientinput.setDisable(false); // Enable the ingredient input field
        Buttonforrecipes.setDisable(false); // Enable the button
        ShowSavedRecipes.setDisable(false);
        skillBox.setDisable(false);
        timeBox.setDisable(false);
        Loginbutton.setDisable(true);
        RegisterButton.setDisable(true);
        GuestButton.setDisable(true);
        Username.setDisable(true);
        Password.setDisable(true);
        recipeexecution.getChildren().clear();
    }


    @FXML
    void OnLogintoapp(ActionEvent event) throws Exception {
        recipeexecution.getChildren().clear();
        if(!Username.getText().isEmpty() && !Password.getText().isEmpty()){
            LMUserDAO log = new LMUserDAO();
            try {
                if (log.authenticate(Username.getText(), Password.getText())) {
                    Initiateappuse();
                }
            }catch (Exception e){

                recipeexecution.getChildren().clear();
                Text executionText5 = new Text("Username or password are WRONG please enter again!");
                recipeexecution.getChildren().add(executionText5);

                // Set content for the ScrollPane
                executionScrollPane.setContent(recipeexecution);

            }
        }else{
            recipeexecution.getChildren().clear();
            Text executionText6 = new Text("Username or password are Empty please enter again!");
            recipeexecution.getChildren().add(executionText6);

            // Set content for the ScrollPane
            executionScrollPane.setContent(recipeexecution);
        }


    }

    @FXML
    void OnRegistertoapp(ActionEvent event) throws Exception {
        if(!Username.getText().isEmpty() && !Password.getText().isEmpty()) {
            LMUserDAO log1 = new LMUserDAO();
            log1.register(Username.getText(),Password.getText());
            recipeexecution.getChildren().clear();
            Username.clear();
            Password.clear();
            Text executionText3 = new Text("Welcome to our community, Please re-enter your credentials and login.");
            recipeexecution.getChildren().add(executionText3);

            // Set content for the ScrollPane
            executionScrollPane.setContent(recipeexecution);
        }else{
            recipeexecution.getChildren().clear();
            Text executionText4 = new Text("Username or password are Empty please enter again!");
            recipeexecution.getChildren().add(executionText4);

            // Set content for the ScrollPane
            executionScrollPane.setContent(recipeexecution);
        }
    }
    @FXML
    void OnContinueasGuest(ActionEvent event) {
        Initiateappuse();
    }



    @FXML
    public void initialize() {
        // Populate choice boxes
        skillBox.setValue("Beginner"); //set standard value
        skillBox.getItems().addAll("Beginner", "Intermediate", "Advanced");
        timeBox.setValue("<30 minutes"); //set standard value
        timeBox.getItems().addAll("<30 minutes", "<1 hour", "> 1 hour");


    }

    @FXML
    private void initializeIngredients(int numofrec) {//needed for choosing recipe number
        if(New){
            Title= Display.Name(numofrec);
            Skill=  Display.Difficulty(numofrec);
            Duration= Display.Time(numofrec);
            Ingredients= Display.Ingredients(numofrec);

        } else{
            Title= Display.SName(numofrec);
            Skill=  Display.SDifficulty(numofrec);
            Duration= Display.STime(numofrec);
            Ingredients= Display.SIngredients(numofrec);
        }
        // Populating recipeingredients TextFlow
        Text ingredientText1 = new Text("Title: " +Title +Skill+ Duration + Ingredients);
        recipeingredients.getChildren().addAll(ingredientText1);

        // Set content for the ScrollPane
        ingredientScrollPane.setContent(recipeingredients);
    }

    @FXML
    private void initializeExecution(int numofrec) {//needed for choosing recipe number
        if(New){
            Execution= Display.Instructions(numofrec);
        } else{
            Execution= Display.SInstructions(numofrec);
        }
        // Populating recipeexecution TextFlow
        Text executionText = new Text(Execution);
        recipeexecution.getChildren().add(executionText);

        // Set content for the ScrollPane
        executionScrollPane.setContent(recipeexecution);
    }

    @FXML
    void OnCloseLastminute(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void OnGoonerecipeback(ActionEvent event) { //one recipe back
        recipeingredients.getChildren().clear();
        recipeexecution.getChildren().clear();

        i--; //i-1
        if(New) {
            numberofrecipe= commonFilters.get(i);
            initializeIngredients(numberofrecipe);
            initializeExecution(numberofrecipe);
            Previous.setDisable(i == 0);
        }else {
            initializeIngredients(i);
            initializeExecution(i);
            Previous.setDisable(i == 1);
        }
        SaveRecipe.setText("Save Recipe");
        Next.setDisable(i == max);

        if(New){
            SaveRecipe.setDisable(saved[i]);
            if (saved[i]) {
                SaveRecipe.setText("Saved");
            }
        }


    }

    @FXML
    void OnGoonerecipenext(ActionEvent event) { //one recipe forward
        recipeingredients.getChildren().clear();
        recipeexecution.getChildren().clear();


        if(i<max) i++; //i+1
        if(New) {
            numberofrecipe = commonFilters.get(i);
            initializeIngredients(numberofrecipe);
            initializeExecution(numberofrecipe);
            Previous.setDisable(i == 0);
        }else {
            initializeIngredients(i);
            initializeExecution(i);
            Previous.setDisable(i == 1);
        }
        SaveRecipe.setText("Save Recipe");
        Next.setDisable(i == max);

        if(New){
            SaveRecipe.setDisable(saved[i]);
            if (saved[i]) {
                SaveRecipe.setText("Saved");
            }
        }

    }

    @FXML
    void Onsaverecipetofile(ActionEvent event) throws IOException {
        if(!saved[i]) {
            //save to folder
            saved[i]=true;
            SaveRecipe.setDisable(saved[i]);
            SaveRecipe.setText("Saved");
            Filter fil2= new Filter();
            fil2.Save(numberofrecipe);
        }
    }

    @FXML
    void OnShowSavedRecipes(ActionEvent event) {
        ingredientinput.setDisable(true); // Disabling both button and text so user doesn't use them
        Buttonforrecipes.setDisable(true);
        ShowSavedRecipes.setDisable(true);
        Next.setDisable(false);
        skillBox.setDisable(true);
        timeBox.setDisable(true);

        New=false;
        Filter fil2 = new Filter();
        i=1;
        initializeIngredients(i);
        initializeExecution(i);
        String path = System.getProperty("user.dir") +"/Saved";
        max= fil2.FileCount(path); //as many as the saved recipes are
        Next.setDisable(i == max);
        ReplaceGif();
        Exittofirstscreen.setDisable(false);
        wait.setText("");

    }


    @FXML
    void onButtonClickNewRecipeCreation(ActionEvent event) {
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
        ingredientinput.clear(); // Clear the text field
        ingredientinput.setDisable(false); // Enable the ingredient input field
        Buttonforrecipes.setDisable(false); // Enable the button
        ShowSavedRecipes.setDisable(false);
        skillBox.setDisable(false);
        timeBox.setDisable(false);
        SaveRecipe.setText("Save Recipe");
        Next.setDisable(true);
        wait.setText("Please keep in mind avarege response time 30sec!"); // Clear the wait label
        Buttonforrecipes.setText("Press for last minute recipe"); // Reset button text

        // Clear or reset the TextFlow elements
        recipeingredients.getChildren().clear();
        recipeexecution.getChildren().clear();
        skillBox.setValue("Beginner");
        timeBox.setValue("<30 minutes");
        i=0;
        New=true;
        Previous.setDisable(true);
        SaveRecipe.setDisable(true);
        Arrays.fill(saved, false);
        Exittofirstscreen.setDisable(true);


    }


    @FXML
    public void onButtonClickRecipeCreation(ActionEvent event) throws IOException, URISyntaxException {

            if(!ingredientinput.getText().isEmpty()) {
                recipeingredients.getChildren().clear();
                recipeexecution.getChildren().clear();
                Ingredients ing = new Ingredients();
                String Prompt = ing.PormptCreator(ingredientinput.getText());
                GPT_API.getRecipes(Prompt);
                EditRecipe.subFile();
                EditRecipe.splitRecipes();
                int skillnumber = 0;
                String skillLevel = skillBox.getValue();
                switch (skillLevel) {
                    case "Beginner" -> skillnumber = 1;
                    case "Intermediate" -> skillnumber = 2;
                    case "Advanced" -> skillnumber = 3;
                }
                Filter fil = new Filter();
                ArrayList<Integer> difficultyfilter = fil.Difficulty(skillnumber);
                //string for the input for the skill level
                String timeAvailable = timeBox.getValue();  //string for the input for the time available
                int timenumber = 0;
                switch (timeAvailable) {
                    case "<30 minutes" -> timenumber = 30;
                    case "<1 hour" -> timenumber = 60;
                    case "> 1 hour" -> timenumber = 240;
                }
                ArrayList<Integer> timefilter = fil.Time(timenumber);


                for (int difficultyValue : difficultyfilter) {
                    for (int timeValue : timefilter) {
                        if (difficultyValue == timeValue) {
                            commonFilters.add(difficultyValue); // Add the matching value to the common filters list
                        }
                    }
                }

                // Perform some logic here based on the inputs
                //String output = "Ingredients: " + ingredients + "\nSkill Level: " + skillLevel + "\nTime Available: " + timeAvailable;

                // Set the output to the label
                ingredientinput.setDisable(true); // Disabling both button and text so user doesn't use them
                skillBox.setDisable(true);
                timeBox.setDisable(true);
                Buttonforrecipes.setDisable(true);
                ShowSavedRecipes.setDisable(true);
                wait.setText("");
                Buttonforrecipes.setText("last minute recipes coming!");
                i = 0; //whatever the first recipe is
                numberofrecipe = commonFilters.get(i);
                initializeIngredients(numberofrecipe);
                initializeExecution(numberofrecipe);
                max = commonFilters.size() - 1;
                Next.setDisable(false);
                Next.setDisable(i == max);
                SaveRecipe.setDisable(false);
                ReplaceGif();
                Exittofirstscreen.setDisable(false);
            } else {
                recipeingredients.getChildren().clear();
                recipeexecution.getChildren().clear();
                Text executionText2 = new Text("Please enter ingredients");
                recipeexecution.getChildren().add(executionText2);

                // Set content for the ScrollPane
                executionScrollPane.setContent(recipeexecution);

                Text ingredientText2 = new Text("Please enter ingredients");
                recipeingredients.getChildren().addAll(ingredientText2);

                // Set content for the ScrollPane
                ingredientScrollPane.setContent(recipeingredients);

            }
    }
    /*@FXML
    private void handleButtonAction (ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        if(event.getSource()==Buttonforrecipes){
            stage = (Stage) Buttonforrecipes.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("testforsecondscreen.fxml"));
        }
        else{
            stage = (Stage) Goback.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("lastminutetry.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }*/


}


