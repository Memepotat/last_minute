import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Display {

    public static String path = System.getProperty("user.dir") + "\\recipes"; // * Path for recipes */
    public static String Spath = System.getProperty("user.dir") + "\\Saved"; // * Path for Saved recipes */

    // ! Method to get the Name of a recipe
    public static String Name(int Rn) { // * Pass the number of the recipe you want the name to */
        String Npath = path + "\\recipe_" + Rn + "\\Name.txt";
        StringBuilder Name = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(Npath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // * */ Append each line to the StringBuilder
                Name.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // * */ Convert the StringBuilder to a String
        return Name.toString();
    }

    // ! Method to get the name of a Saved recipe
    public static String SName(int Rn) { // * Pass the number of the recipe you want the name to */
        String Npath = Spath + "\\saved_" + Rn + "\\Name.txt";
        StringBuilder Name = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(Npath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // * */ Append each line to the StringBuilder
                Name.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // * */ Convert the StringBuilder to a String
        return Name.toString();
    }

    // ! Method to get the Difficulty of a recipe
    public static String Difficulty(int Rn) { // * Pass the number of the recipe you want the name to */
        String Dpath = path + "\\recipe_" + Rn + "\\Difficulty.txt";

        StringBuilder Difficulty = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(Dpath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // * */ Append each line to the StringBuilder
                Difficulty.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // * */ Convert the StringBuilder to a String
        return Difficulty.toString();
    }

    // ! Method to get the Difficulty of a Saved recipe
    public static String SDifficulty(int Rn) { // * Pass the number of the recipe you want the name to */
        String Dpath = Spath + "\\saved_" + Rn + "\\Difficulty.txt";
        StringBuilder SDifficulty = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(Dpath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // * */ Append each line to the StringBuilder
                SDifficulty.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // * */ Convert the StringBuilder to a String
        return SDifficulty.toString();
    }

    // ! Method to get the Time of a recipe
    public static String Time(int Rn) { // * Pass the number of the recipe you want the name to */
        String Tpath = path + "\\recipe_" + Rn + "\\TimeEstimate.txt";

        StringBuilder Time = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(Tpath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // * */ Append each line to the StringBuilder
                Time.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // * */ Convert the StringBuilder to a String
        return Time.toString();
    }

    // ! Method to get the Time of a Saved recipe
    public static String STime(int Rn) { // * Pass the number of the recipe you want the name to */
        String Tpath = Spath + "\\saved_" + Rn + "\\TimeEstimate.txt";
        StringBuilder STime = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(Tpath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // * */ Append each line to the StringBuilder
                STime.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // * */ Convert the StringBuilder to a String
        return STime.toString();
    }

    // ! Method to get the Ingredients of a recipe
    public static String Ingredients(int Rn) { // * Pass the number of the recipe you want the name to */
        String Ingpath = path + "\\recipe_" + Rn + "\\Ingredients.txt";

        StringBuilder Ingredients = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(Ingpath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // * */ Append each line to the StringBuilder
                Ingredients.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // * */ Convert the StringBuilder to a String
        return Ingredients.toString();
    }

    // ! Method to get the Ingredients of a Saved recipe
    public static String SIngredients(int Rn) { // * Pass the number of the recipe you want the name to */
        String Ingpath = Spath + "\\saved_" + Rn + "\\Ingredients.txt";
        StringBuilder SIngredients = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(Ingpath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // * */ Append each line to the StringBuilder
                SIngredients.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // * */ Convert the StringBuilder to a String
        return SIngredients.toString();
    }

    // ! Method to get the Instructions of a recipe
    public static String Instructions(int Rn) { // * Pass the number of the recipe you want the name to */
        String Inspath = path + "\\recipe_" + Rn + "\\Instructions.txt";

        StringBuilder Instructions = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(Inspath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // * */ Append each line to the StringBuilder
                Instructions.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // * */ Convert the StringBuilder to a String
        return Instructions.toString();
    }

    // ! Method to get the Ingredients of a Saved recipe
    public static String SInstructions(int Rn) { // * Pass the number of the recipe you want the name to */
        String Inspath = Spath + "\\saved_" + Rn + "\\Instructions.txt";
        StringBuilder SInstructions = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(Inspath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // * */ Append each line to the StringBuilder
                SInstructions.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // * */ Convert the StringBuilder to a String
        return SInstructions.toString();
    }

}
