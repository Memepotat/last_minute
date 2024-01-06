import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class Filter {

    public static String path = System.getProperty("user.dir");

    public ArrayList<Integer> Difficulty(int level) { // ! Difficulty Filter
        // * Set the path to the recipes folder */
        path = path + "/recipes";

        // * Count the number of recipes in the folder */
        int FileCount = FileCount(path);

        ArrayList<Integer> recipeDif = new ArrayList<Integer>();

        for (int i = 1; i <= FileCount; i++) {
            String Difpath = path + "/recipe_" + i + "/Difficulty.txt";

            // * Check if Easy, Medium or Hard exist in the Difficulty file of each recipe
            // */
            try {
                if ((new Scanner(new File(Difpath)).useDelimiter("\\Z").next().contains("Easy"))
                        || (new Scanner(new File(Difpath)).useDelimiter("\\Z").next().contains("easy"))) {
                    recipeDif.add(i);
                } else if ((new Scanner(new File(Difpath)).useDelimiter("\\Z").next().contains("Medium"))
                        || (new Scanner(new File(Difpath)).useDelimiter("\\Z").next().contains("medium"))) {
                    if (level >= 2) {
                        recipeDif.add(i);
                    }
                } else if ((new Scanner(new File(Difpath)).useDelimiter("\\Z").next().contains("Hard"))
                        || (new Scanner(new File(Difpath)).useDelimiter("\\Z").next().contains("hard"))) {
                    if (level == 3) {
                        recipeDif.add(i);

                    }
                }
            } catch (FileNotFoundException e) {
            }
        }
        return recipeDif;

    }

    public ArrayList<Integer> Time(int time) throws IOException { // ! Time filter
        // * Extract the numbers from the time estimate files */
        int fc = FileCount(path);

        ArrayList<Integer> recipeTime = new ArrayList<>();

        for (int i = 1; i <= fc; i++) {
            String Tpath = path + "/recipe_" + i + "/TimeEstimate.txt";

            StringBuilder result = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(Tpath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // * */ Use regular expression to match numbers in each line
                    Pattern pattern = Pattern.compile("\\d+");
                    Matcher matcher = pattern.matcher(line);

                    // * */ Append matched numbers to the result string
                    while (matcher.find()) {
                        result.append(matcher.group()).append(" ");
                    }
                }
            }
            if (time >= Integer.parseInt(result.toString().trim())) {
                recipeTime.add(i);
            }
        }

        return recipeTime;
    }

    public void Save(int Rn) throws IOException { // ! Save method
        String path = System.getProperty("user.dir");
        String parent = path + "/Saved";
        String source = path + "/Recipes/recipe_" + Rn;

        if (!Files.exists(Paths.get(parent))) {
            Files.createDirectories(Paths.get(parent)); // * Create the Saved directory if it doesn't exist */
        }

        int num = FileCount(parent); // * Count the number of saved recipes and add one */
        num += 1;
        String dest = parent + "/saved_" + num;

        Path sourceDir = Paths.get(source);
        Path destinationDir = Paths.get(dest);
        try {
            // * */ Use Files.walkFileTree to traverse the source directory recursively
            Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    // * */ Resolve the destination path relative to the source path
                    Path destination = destinationDir.resolve(sourceDir.relativize(file));

                    // *Create directories if they don't exist
                    Files.createDirectories(destination.getParent());

                    // * */ Copy the file to the destination
                    Files.copy(file, destination, StandardCopyOption.REPLACE_EXISTING);

                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    // * */ Handle the case where a file visit fails
                    System.err.println("Failed to visit file: " + file);
                    return FileVisitResult.CONTINUE;
                }
            });

            System.out.println("Directory copied successfully.");
        } catch (IOException e) {
            e.printStackTrace(); // * */ Handle the exception according to your needs
        }
    }

    public int FileCount(String path) { // ! File Counter
        File directory = new File(path);

        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("Invalid directory path");
            return -1;
        }

        File files[] = directory.listFiles(File::isDirectory);
        int fc = files.length;
        return fc;
    }
}
