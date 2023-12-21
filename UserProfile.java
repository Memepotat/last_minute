import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private List<String> savedRecipes;

    public UserProfile(String username) {
        this.username = username;
        this.savedRecipes = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<String> getSavedRecipes() {
        return savedRecipes;
    }

    public void saveRecipe(String recipe) {
        savedRecipes.add(recipe);
    }
}
