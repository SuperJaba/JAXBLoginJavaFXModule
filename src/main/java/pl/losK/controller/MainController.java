package pl.losK.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private Stage stage;
    private FXMLLoader loader;

    public void loadView(String viewName, StackPane mainStackPane) {
        loader = new FXMLLoader(this.getClass().getResource("/" + viewName + ".fxml"));

        try {
            StackPane pane = loader.load();
            Controller controller = loader.getController();
            controller.setMainController(this);
            mainStackPane.getChildren().clear();
            mainStackPane.getChildren().add(pane);
//            loader.setController(loginController);
//            Scene scene = new Scene(mainStackPane);
//            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadView(String viewName) {
        loadView(viewName, mainStackPane);
    }

    @FXML
    public void initialize() {
        loadView("LoginView");
    }


    public MainController(Stage primaryStage) {
        setStage(primaryStage);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public StackPane getMainStackPane() {
        return mainStackPane;
    }

    @FXML
    private StackPane mainStackPane;

}