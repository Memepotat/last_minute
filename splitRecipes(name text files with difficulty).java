public static void splitRecipes() {
    String initialPath = System.getProperty("user.dir") + "/recipes";
    int recipeNum = num[0];

    for (int i = 1; i <= recipeNum; i++) {
        String path = initialPath + "/recipe_" + i + "/recipe_" + i + ".txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            int lineCounter = 1;
            boolean instructions = false;

            String difficulty = ""; // New variable to store the difficulty of the recipe

            while ((line = reader.readLine()) != null) {
                String outputFilePath = initialPath + "/recipe_" + i;

                if (lineCounter == 2) {
                    difficulty = line.trim(); // Save the difficulty
                }

                if (lineCounter == 1) {
                    outputFilePath += "/Name.txt";
                } else if (lineCounter == 2) {
                    outputFilePath += "/Difficulty_" + difficulty + ".txt"; // Include difficulty in the filename
                } else if (lineCounter == 3) {
                    outputFilePath += "/TimeEstimate.txt";
                } else if (lineCounter > 3 && !line.trim().equals("Instructions:") && !instructions) {
                    outputFilePath += "/Ingredients.txt";
                } else if (instructions || line.trim().equals("Instructions:") || line.equals("Instructions: ")) {
                    outputFilePath += "/Instructions.txt";
                    instructions = true;
                }

                if (lineCounter <= 3) { // Create files only for the first three lines
                    try (FileWriter writer = new FileWriter(outputFilePath)) {
                        writer.write(line);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (!line.trim().equals("Instructions:") && !instructions) {
                    try (FileWriter writer = new FileWriter(outputFilePath, true)) {
                        writer.write(line + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (instructions) {
                    try (FileWriter writer = new FileWriter(outputFilePath, true)) {
                        writer.write(line + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                lineCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}