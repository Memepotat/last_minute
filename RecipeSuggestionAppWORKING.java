import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecipeSuggestionApp {

    private static final String API_KEY = "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";
    private static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";
    private static final String MODEL = "gpt-3.5-turbo";

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // Ask the user for available ingredients
            System.out.println("Enter the ingredients you have (separated by commas):");
            String userIngredients = reader.readLine();

            // Get recipe suggestions
            String recipes = getRecipeSuggestions(userIngredients);

            // Display the suggested recipes
            System.out.println("\nSuggested Recipes:");
            System.out.println(recipes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getRecipeSuggestions(String userIngredients) {
        try {
            // Create the HTTP POST request
            URL obj = new URL(API_ENDPOINT);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + API_KEY);
            con.setRequestProperty("Content-Type", "application/json");

            // Build the request body with user ingredients
            String modelInput = "Suggest recipes using the following ingredients: " + userIngredients;
            String apiRequestBody = "{\"model\": \"" + MODEL + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + modelInput + "\"}]}";
            System.out.println("Request Body: " + apiRequestBody);  // Debug print statement
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(apiRequestBody);
            writer.flush();
            writer.close();

            // Get the response
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);  // Debug print statement

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println("Response: " + response.toString());  // Debug print statement

            // Extract and return the content from the response
            return extractContentFromResponse(response.toString());

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static String extractContentFromResponse(String response) {
        // Extract content from the response
        int startMarker = response.indexOf("content") + 11;
        int endMarker = response.indexOf("\"", startMarker);
        return response.substring(startMarker, endMarker);
    }
}