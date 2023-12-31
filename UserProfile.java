import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private List<String> savedRecipes;

    public UserProfile(String username, String password) {
        this.username = username;
        this.password = password;
        this.savedRecipes = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getSavedRecipes() {
        return savedRecipes;
    }

    public void saveRecipe(String recipe) {
        savedRecipes.add(recipe);
    }

    public void saveUserProfileToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(username + ".dat"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserProfile loadUserProfile(String username) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(username + ".dat"))) {
            return (UserProfile) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions
            return null;
        }
    }
}

