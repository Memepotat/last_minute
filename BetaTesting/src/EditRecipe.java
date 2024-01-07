import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.nio.file.Files;

public class EditRecipe {
    public static int[] num = new int[1];

    // * Method to separate the api response into separate recipes & Files */
    public static void subFile() {
        String path = System.getProperty("user.dir");
        path = path + "/recipesTEST.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            int fileCounter = 1;
            StringBuilder currentSection = new StringBuilder();

            // * */ Read each line from the file until the end of the file is reached
            while ((line = reader.readLine()) != null) {
                if ((line.trim().equals("===") || line.contains("===")) && fileCounter > 1) {
                    // * */ When "===" is encountered, create a new file with the current section
                    saveSectionToFile(fileCounter, currentSection.toString());
                    currentSection = new StringBuilder();
                    fileCounter++;
                }
                if (line.trim().equals("===")) {
                    fileCounter++;
                } else {
                    // *Append the line to the current section
                    currentSection.append(line).append("\n");
                }
            }

            // *Save the last section (if any) to a file
            if (currentSection.length() > 0) {
                saveSectionToFile(fileCounter, currentSection.toString());
            }
            num[0] = fileCounter / 2;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // * Method used for writing recipes to the files (used by subFile) */
    private static void saveSectionToFile(int fileCounter, String content) throws IOException {

        fileCounter = fileCounter / 2;

        // * */ Specify the path to your output files
        String path = System.getProperty("user.dir");
        path = path + "/recipes";
        File file = new File(path);
        if (!file.exists()) // * Check if Directory exists */
            Files.createDirectories(Paths.get(path)); // *Create folder recipes */

        path = path + "/recipe_" + fileCounter;
        File file2 = new File(path);
        if (!file2.exists()) { // *Check if directory exists */
            Files.createDirectories(Paths.get(path)); // *Create folder for each recipe */
        }
        String outputFilePath = path + "/recipe_" + fileCounter + ".txt";

        try (FileWriter writer = new FileWriter(outputFilePath)) {
            // * */ Write the content of the section to the new file
            writer.write(content);
        } catch (IOException e) {
            // * */ Handle IOException if any
            e.printStackTrace();
        }
    }

    // *Method used to split each recipe into name, difficulty, time, ingredients,
    // * */ instructions */
    public static void splitRecipes() {
        String Initialpath = System.getProperty("user.dir");
        Initialpath = Initialpath + "/recipes";

        int recipeNum = num[0];

        for (int i = 1; i <= recipeNum; i++) {

            // * path for the file to read */
            String path = Initialpath;
            path = path + "/recipe_" + i + "/recipe_" + i + ".txt";

            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                // * */ Read the first line from the file, which is the name of the recipe
                String line;
                int lineCounter = 1;
                boolean instructions = false; // * trigers reading the ingredients */

                while ((line = reader.readLine()) != null) {
                    // * */ Specify the path to your output file
                    String outputFilePath = Initialpath;
                    outputFilePath = outputFilePath + "/recipe_" + i;

                    if (lineCounter == 1) { // ! Create Name file
                        outputFilePath = outputFilePath + "/Name.txt";
                        try (FileWriter writer = new FileWriter(outputFilePath)) {
                            writer.write(line);
                        } catch (IOException e) {
                            // * */ Handle IOException if any
                            e.printStackTrace();
                        }
                        if (!line.trim().isEmpty()) {
                            lineCounter++;
                        }

                    } else if (lineCounter == 2) { // ! Create Difficulty file
                        outputFilePath = outputFilePath + "/Difficulty.txt";
                        try (FileWriter writer = new FileWriter(outputFilePath)) {
                            writer.write(line);
                        } catch (IOException e) {
                            // * */ Handle IOException if any
                            e.printStackTrace();
                        }
                        lineCounter++;

                    } else if (lineCounter == 3) { // ! Create Time file
                        outputFilePath = outputFilePath + "/TimeEstimate.txt";
                        try (FileWriter writer = new FileWriter(outputFilePath)) {
                            writer.write(line);
                        } catch (IOException e) {
                            // * */ Handle IOException if any
                            e.printStackTrace();
                        }
                        lineCounter++;

                    } else if (lineCounter > 3 && !line.trim().equals("Instructions:") && instructions == false) { // !
                                                                                                                   // Create
                        // Ingredients
                        // file
                        outputFilePath = outputFilePath + "/Ingredients.txt";
                        try (FileWriter writer = new FileWriter(outputFilePath, true)) {
                            writer.write(line + "\n");
                        } catch (IOException e) {
                            // * */ Handle IOException if any
                            e.printStackTrace();
                        }
                        lineCounter++;

                    } else if (instructions == true || line.trim().equals("Instructions:")
                            || line.equals("Instructions: ")) { // ! Create Instructions
                        // file
                        outputFilePath = outputFilePath + "/Instructions.txt";
                        try (FileWriter writer = new FileWriter(outputFilePath, true)) {
                            writer.write(line + "\n");
                            instructions = true;

                        } catch (IOException e) {
                            // * */ Handle IOException if any
                            e.printStackTrace();
                        }
                        lineCounter++;
                    }

                }
            } catch (IOException e) {
                // * */ Handle IOException if any
                e.printStackTrace();
            }
        }
    }

}
