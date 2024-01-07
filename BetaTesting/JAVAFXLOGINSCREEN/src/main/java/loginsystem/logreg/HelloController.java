package loginsystem.logreg;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private  Button loginButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private  PasswordField passwordPasswordField;


    public void loginButtonClicked() {
        boolean accountfound = database.Login(usernameTextField.getText(), passwordPasswordField.getText()); //TRUE = Account found //False = Account not found

        System.out.println(accountfound);

        if (accountfound)
        {
            Stage stage = (Stage) loginButton.getScene().getWindow();

            stage.close();
        }

    }

    public  void registerButtonClicked() {
        if (!database.Register(usernameTextField.getText(), passwordPasswordField.getText())) //TRUE = Account found //False = Account created
        {
            Stage stage = (Stage) loginButton.getScene().getWindow();

            stage.close();
        }
    }

}