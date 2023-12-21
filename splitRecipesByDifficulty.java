    // Method to split recipes and filter by difficulty
    public static List<String> splitRecipesByDifficulty(String difficultyFilter) {
        List<String> filteredRecipes = new ArrayList<>();

        String initialPath = System.getProperty("user.dir");
        initialPath = initialPath + "/recipes";

        int recipeNum = num[0];

        for (int i = 1; i <= recipeNum; i++) {
            String path = initialPath;
            path = path + "/recipe_" + i + "/recipe_" + i + ".txt";

            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                int lineCounter = 1;
                boolean instructions = false;

                StringBuilder currentRecipe = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    currentRecipe.append(line).append("\n");

                    if (line.trim().equals("===") && lineCounter > 1) {
                        String recipeDifficulty = getRecipeDifficulty(currentRecipe.toString());
                        if (recipeDifficulty.equalsIgnoreCase(difficultyFilter)) {
                            filteredRecipes.add(currentRecipe.toString());
                        }

                        currentRecipe = new StringBuilder();
                    }

                    lineCounter++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return filteredRecipes;
    }

    // Helper method to extract difficulty from a recipe
    private static String getRecipeDifficulty(String recipe) {
    // Look for the line that contains "Difficulty:"
    String[] lines = recipe.split("\n");
    for (String line : lines) {
        if (line.trim().startsWith("Difficulty:")) {
            // Extract the difficulty level (assuming it's everything after "Difficulty:")
            String[] parts = line.split(":");
            if (parts.length > 1) {
                return parts[1].trim();
            }
        }
    }

    // Return a default difficulty if not found (you can modify this)
    return "not found";
}
