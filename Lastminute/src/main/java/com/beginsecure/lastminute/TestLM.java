package com.beginsecure.lastminute;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.Assert.*;
public class TestLM {
        @Test
        public void TestgetUsername() {
            LMUser test = new LMUser("Vaggelis", "blablabla");
            assertEquals("Vaggelis", test.getUsername());
        }
        @Test
        public void TestgetPassword() {
            LMUser test = new LMUser("Vaggelis", "blablabla");
            assertEquals("Vaggelis", test.getPassword());
        }
        @Test
        public void TestgetConnection() {
        DBconne test = new DBconne();
        Connection connection = test.getConnection();
        assertNotNull("Connection should not be null", connection);
        }

        @Test
        public void TestregisternotNew(){
            LMUserDAO test = new LMUserDAO();
            assertThrows("Sorry, username already registered",test.register("Marios","blabla"));
        }
        @Test
        public void testauthenticate(){
            LMUserDAO test = new LMUserDAO();
            assertThrows("Wrong username or password",test.authenticate("Marois","blabla"));
        }

        @Test
        public void testAIngredients(){
            Ingredients ingredients = new Ingredients();
            assertEquals("Provide me with up to five (5) recipes using either all or some of the following ingredients: Banannas . Structure the result as so: Signal the beginning of a recipe by placing === at the first line at the start. Under the first line is the title of the recipe followed by a difficulty level one line below (easy, medium, hard). The third line should be a time estimate for the recipe in minutes. Under that line give me the ingredients needed. Under the ingredients give me the instructions. This is an example of how I want the result to be structured(I will be using /n to signal a linebreak) do not leave gaps between the lines and do not number the recipes, just have their title: === /n. Recipe Title. /n (Difficulty)./n Time estimate: minutes to make the recipe. /n Ingredients: List of all ingredients used. /n Instructions: Instructions for the recipe",ingredients.PormptCreator(Bannana));
        }
        //test for the display class
        @Test
        public void testName(){
            String test = Display.Name(1);
            assertNotNull(test);
            assertFalse(test.isEmpty());
        }
        @Test
        public void testDifficulty(){
            String test = Display.Difficulty(1);
            assertNotNull(test);
            assertFalse(test.isEmpty());
        }
        @Test
            public void testTime(){
            String test = Display.Time(1);
            assertNotNull(test);
            assertFalse(test.isEmpty());
        }
        @Test
        public void testIngredients(){
            String test = Display.Ingredients(1);
            assertNotNull(test);
            assertFalse(test.isEmpty());
        }
        @Test
        public void testInstructions(){
            String test = Display.Instructions(1);
            assertNotNull(test);
            assertFalse(test.isEmpty());
        }
        @Test
        public void testSName() {
            String test = Display.SName(1);
            assertNotNull(test);
            assertFalse(test.isEmpty());
            assertEquals("Banana-Chocolate Smoothie", test.trim());
        }
        @Test
        public void testSDifficulty() {
            String test = Display.SDifficulty(1);
            assertNotNull(test);
            assertFalse(test.isEmpty());
            assertEquals("Easy", test.trim());
        }
        @Test
        public void testSTime() {
            String test = Display.STime(1);
            assertNotNull(test);
            assertFalse(test.isEmpty());
            assertEquals("5 minutes", test.trim());
        }
        @Test
        public void testSIngredients() {
            String test = Display.SIngredients(1);
            assertNotNull(test);
            assertFalse(test.isEmpty());
            assertEquals("- 1 ripe banana \n - 1 cup milk \n - 2 tablespoons cocoa powder \n - 1 tablespoon honey \n - Ice cubes (Optional)", test.trim());
        }
        @Test
        public void testSInstructions() {
            String test = Display.SInstructions(1);
            assertNotNull(test);
            assertFalse(test.isEmpty());
            assertEquals("1. Peel the banana and break it into chunks. \n 2. Place the banana chunks, milk, cocoa powder, and honey in a blender. \n 3. Blend until smooth and creamy. \n 4. If desired, add a few ice cubes and blend again until well combined. \n 5. Pour into a glass and serve immediatly.", test.trim());
        }
        //test for Filter class
        @Test
        public void testFDifficulty(){
            Filter filter = new Filter();
            ArrayList<Integer> test = filter.Difficulty(2);
            assertNotNull(test);
            assertFalse(test.isEmpty());
            assertTrue(test.contains(1));
        }
        @Test
        public void testFTime(){
            Filter filter = new Filter();
            ArrayList<Integer> test = filter.Time(30);
            assertNotNull(test);
            assertFalse(test.isEmpty());
        }
        @Test
        public void testSaveMethod() {
            Filter filter = new Filter();
            try {
                filter.Save(1);
            } catch (IOException e) {
                fail("Exception thrown while saving recipe: " + e.getMessage());
            }
            String RecipePath = System.getProperty("user.dir") + "/Saved/saved_1"; // Adjust the path based on your test data
            assertTrue(Files.exists(Paths.get(RecipePath)));

        }
        //test for GPT_AP class
        @Test
        public  void testextractContentFromResponse() {
            String response = "{\"id\":123,\"content\":\"This is the extracted content\",\"timestamp\":1622784686}";
            String extractedContent = GPT_API.extractContentFromResponse(response);
            assertEquals("This is the extracted content", extractedContent);
        }
    @Test
    public void testWriteRecipes() {
        String recipes = "Recipe 1\nRecipe 2";
        try {
            GPT_API.writeRecipes(recipes);
            assertTrue(Files.exists(Paths.get("recipesTEST1.txt")));
            assertEquals(recipes, readFile("recipesTEST1.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        @Test
        private String readFile(String filePath) throws IOException {
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }
            return content.toString().trim();
        }
}





