import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LastMinute {

    public static void main(String[] args) throws URISyntaxException, IOException {

        try{

            cleanupExistingFiles();

            UserProfile currentUser = promptUserAuthentication();

            // * Test to see that the prompt is created correctly */
            Ingredients ing = new Ingredients();
            Map<String, String> userInput = ing.getUserInput();

            String Prompt = ing.PromptCreator(
                userInput.get("ingredients"),
                userInput.get("time"),
                userInput.get("difficulty"));
            
            // * Call the api */
            List<String> generatedRecipes = GPT_API.getRecipes(Prompt);
            EditRecipe.subFile();
            EditRecipe.splitRecipes();

            saveRecipe(currentUser, generatedRecipes);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void cleanupExistingFiles() throws IOException {
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
    }

    public static UserProfile promptUserAuthentication() {
        Scanner sc = new Scanner(System.in);
        UserManager userManager = new UserManager();

        System.out.println("1. Register\n2. Login");
        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.println("Enter a username: ");
            String username = sc.next();
            System.out.println("Enter a password: ");
            String password = sc.next();
            UserProfile currentUser = userManager.registerUser(username, password);
            System.out.println("Registration successful!");
            return currentUser;
        } else if (choice == 2) {
            System.out.println("Enter your username: ");
            String username = sc.next();
            System.out.println("Enter your password: ");
            String password = sc.next();
            UserProfile currentUser = userManager.loginUser(username, password);

            if (currentUser != null) {
                System.out.println("Login successful! Welcome, " + currentUser.getUsername());
            } else {
                System.out.println("Invalid username or password. Exiting.");
                System.exit(0);
            }
            return currentUser;
        } else {
            System.out.println("Invalid choice. Exiting.");
            System.exit(0);
        }
        return null; // This line is not reachable, but included to satisfy the compiler
    }

    public static void saveRecipe(UserProfile currentUser, List<String> generatedRecipes) {

        try(Scanner sc = new Scanner(System.in);) {

            if (currentUser != null) {
                System.out.println("Do you want to save a recipe? (yes/no)");
                String response = sc.next();

                if (response.equalsIgnoreCase("yes")) {
                    System.out.println("Enter the recipe number to save: ");
                    int recipeNumber = sc.nextInt();

                    if (recipeNumber >= 1 && recipeNumber <= generatedRecipes.size()) {
                        currentUser.saveRecipe(generatedRecipes.get(recipeNumber - 1));
                        System.out.println("Recipe saved!");
                    } else {
                        System.out.println("Invalid recipe number.");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
