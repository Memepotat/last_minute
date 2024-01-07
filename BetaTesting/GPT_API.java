import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class GPT_API {
    private static final String API_KEY = "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";
    private static final String url = "https://api.openai.com/v1/chat/completions";
    private static final String MODEL = "gpt-3.5-turbo";

    public static List<String> getRecipes(String prompt) throws URISyntaxException {
        List<String> generatedRecipes = new ArrayList<>();

        try {
            // * Create the HTTP Post request *
            URL obj = new URI(url).toURL();
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + API_KEY);
            con.setRequestProperty("Content-Type", "application/json");

            // * Build the request body */
            String apiRequestBody = "{\"model\": \"" + MODEL + "\", \"messages\": [{\"role\": \"user\", \"content\": \""
                    + prompt + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(apiRequestBody);
            writer.flush();
            writer.close();

            // * Get response */
            int responseCode = con.getResponseCode();
            System.out.println("Response Code:\n" + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // * Format response */
            String recipes = extractContentFromResponse(response.toString());
            generatedRecipes.add(recipes);

            // * Write to txt file */
            GPT_API.writeRecipes(recipes);
            GPT_API.reCompose();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedRecipes;
    }

    // * Method used for formating API response */
    private static String extractContentFromResponse(String response) {
        // Extract content from the response
        int startMarker = response.indexOf("content") + 11;
        int endMarker = response.indexOf("\"", startMarker);
        return response.substring(startMarker, endMarker);
    }

    // * Method to write response to a file */
    public static void writeRecipes(String recipes) throws URISyntaxException {

        try {
            // * Get the path where the file will be created */
            String path = System.getProperty("user.dir");
            path = path + "/recipesTEST1.txt";

            // * Create the file at the specified location */
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
                // *Split the string in lines
                String[] lines = recipes.split(Pattern.quote("\n"));
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine(); // * Add a new line
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // * Read & re-write the file correctly */
    public static void reCompose() {

        // * */ Specify the input and output file paths
        String inputFilePath = "recipesTEST1.txt";
        String outputFilePath = "recipesTEST.txt";

        // * */ Use try-with-resources to auto-close the BufferedReader and
        // BufferedWriter
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;

            // * */ Read each line from the input file
            while ((line = reader.readLine()) != null) {
                // *Replace \n with a newline
                line = line.replace("\\n", "\n");

                // * */ Write the modified line to the output file
                writer.write(line);
                writer.newLine(); // * */ Add a newline
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
