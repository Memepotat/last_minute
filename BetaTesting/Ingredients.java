import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ingredients {

public Map<String, String> getUserInput() {

        Scanner sc = new Scanner(System.in);
        Map<String, String> userInput = new HashMap<>();

        // Message to enter ingredients
        System.out.println("Enter your available ingredients (separated by commas): ");
        String ingredients = sc.nextLine();
        userInput.put("ingredients", ingredients);

        // Message to enter difficulty level
        System.out.println("Enter the difficulty level (Easy, Medium, Hard): ");
        String difficulty = sc.nextLine();
        userInput.put("difficulty", difficulty);

        // Message to enter time estimate
        System.out.println("Enter the time estimate in minutes: ");
        String time = sc.nextLine();
        userInput.put("time", time);

        sc.close();
        return userInput;
    }


    public String PromptCreator(String ingredients, String difficulty, String time) {

        String prompt = "Provide me with up to five (5) recipes using either all or some of the following ingredients: "
        + ingredients + " , with the following difficulty level: " + difficulty + " and within the following or a smaller time limit: " + time + " . Structure the result as follows: Signal the beginning of a recipe by placing '===' at the first line" +
        " at the start. Under the first line is the title of the recipe followed by the difficulty level one line below"
        + ". Difficulty: . The third line should be a time estimate for the recipe in minutes: . Under that line, give me "
        +
        "the ingredients needed. Under the ingredients, give me the instructions. "
        +
        "This is an example of how I want the result to be structured (I will be using /n to signal a linebreak) do not leave gaps between the lines and do not number the recipes, just have their title: === /n. Recipe Title. /n Difficulty: /n Time estimate: minutes to "
        +
        "make the recipe. /n Ingredients: List of all ingredients used. /n Instructions: Instructions for the recipe";
        
        return prompt;

    }
}
