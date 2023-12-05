import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class LastMinute {
    public static void main(String[] args) throws URISyntaxException, IOException {

        // * Call the api */
        // GPT_API.getRecipes(Prompt);
        EditRecipe.subFile();
        EditRecipe.splitRecipes();

    }

}
