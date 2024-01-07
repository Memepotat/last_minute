
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RecipeSuggestionApp {

    private static final String API_KEY = "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";
    private static final String API_ENDPOINT = "https://api.openai.com/v1/chat/completions";

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

    private static String getRecipeSuggestions(String userIngredients) throws IOException {
        // Prepare the API request
        String modelInput = "Suggest recipes using the following ingredients: " + userIngredients;
        String apiRequestBody = "{\"messages\": [{\"role\": \"system\",\"content\": \"You are a helpful assistant that suggests recipes based on user-provided ingredients.\"},{\"role\": \"user\",\"content\": \"" + modelInput + "\"}]}";
        String encodedRequestBody = URLEncoder.encode(apiRequestBody, "UTF-8");

        // Create the API request
        URL url = new URL(API_ENDPOINT);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setDoOutput(true);

        // Send the request
        connection.getOutputStream().write(encodedRequestBody.getBytes("UTF-8"));

        // Get the response
        int responseCode = connection.getResponseCode();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        // Close the connection
        connection.disconnect();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            return response.toString();
        } else {
            throw new IOException("Error in API request. Response Code: " + responseCode + ", Response: " + response.toString());
        }
    }
}
