import java.net.URISyntaxException;

public class LastMinute {
    public static void main(String[] args) throws URISyntaxException {

        // * Test to see that the prompt is created correctly */
        Ingredients ing = new Ingredients();
        String Prompt = ing.PormptCreator(ing.IngredientList());

        // * Call the api */
        GPT_API.getRecipes(Prompt);
        EditRecipe.subFile();
        EditRecipe.splitRecipes();

    }

}
