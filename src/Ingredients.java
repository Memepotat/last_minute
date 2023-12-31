import java.util.Scanner;

public class Ingredients {

    // * Aquire Available Ingredients */
    public String IngredientList() {
        // * Message to enter ingredients */
        System.out.println("Enter your available ingredients (seperated by commas): ");

        // * Get ingredients List */
        Scanner sc = new Scanner(System.in);
        String IngredientList = sc.nextLine();
        sc.close();

        // * Return Ingredients List */
        return IngredientList;
    }

    public String PormptCreator(String Ingredients) {
        System.out.println("These are the ing: " + Ingredients + " End of ingredients");
        String prompt = "Provide me with up to five (5) recipes using either all or some of the following ingredients:"
                + Ingredients
                + ". Structure the result as so: Signal the beginning of a recipe by placing === at the first line" +
                "at the start. Under the first line is the title of the recipe followed by a difficulty level one line below"
                + "(easy, medium, hard). The third line should be a time estimate for the recipe in minutes. Under that line give me "
                +
                "the ingredients needed. Under the ingredients give me the instructions. "
                +
                "This is an example of how I want the result to be structured(I will be using /n to signal a linebreak) do not leave gaps between the lines and do not number the recipes, just have their title: === /n. Recipe Title. /n (Difficulty)./n Time estimate: minutes to "
                +
                "make the recipe. /n Ingredients: List of all ingredients used. /n Instructions: Instructions for the recipe";

        return prompt;
    }
}
