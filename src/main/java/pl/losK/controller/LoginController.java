package pl.losK.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import pl.losK.model.User;
import pl.losK.service.UserService;


/**
 * Created by m.losK on 2017-03-14.
 */
public class LoginController extends Controller {

    private StackPane stackPane;

    @FXML
    private Button submit;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField loginTextField;

    public LoginController() {
    }

    public LoginController(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    @FXML
    void submitOnAction() {
        UserService userService = new UserService();
        User user = new User(loginTextField.getText(), passwordTextField.getText());

        if (userService.authenticate(user)) {
            getMainController().loadView("MenuView");
        } else {
            showErrorAlert("Sorry! Wrong data");
        }
    }

    @FXML
    void submitOnEnter(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            submitOnAction();
        }
    }
}
