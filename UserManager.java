import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, UserProfile> userProfiles;

    public UserManager() {
        this.userProfiles = new HashMap<>();
    }

    public UserProfile registerUser(String username, String password) {
        UserProfile userProfile = new UserProfile(username, password);
        userProfiles.put(username, userProfile);
        return userProfile;
    }

    public UserProfile loginUser(String username, String password) {
        UserProfile userProfile = userProfiles.get(username);
        if (userProfile != null && userProfile.getPassword().equals(password)) {
            return userProfile;
        }
        return null; // Invalid username or password
    }
}
