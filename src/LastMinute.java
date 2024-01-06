import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class LastMinute {
    public static void main(String[] args) throws URISyntaxException, IOException {

        // * Check to see if recipes folder & recipesTEST exist, and if so deletes them
        // before creating the new ones */
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
            }
        }

        // * Test to see that the prompt is created correctly */
        Ingredients ing = new Ingredients();
        String Prompt = ing.PormptCreator(ing.IngredientList());

        // * Call the api */
        GPT_API.getRecipes(Prompt);
        EditRecipe.subFile();
        EditRecipe.splitRecipes();

    }

}
