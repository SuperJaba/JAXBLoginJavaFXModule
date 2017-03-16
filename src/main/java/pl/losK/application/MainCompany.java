package pl.losK.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by m.losK on 2017-03-15.
 */
public class MainCompany extends Application {
    private Stage primaryStage;
    private BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {
//        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/ApplicationMainView.fxml"));
//        StackPane stackPane = loader.load();
//        Scene scene = new Scene(stackPane);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Company");
//        primaryStage.show();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Company Application");
        showMainView();
        showMainItems();

    }

    private void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/ApplicationMainView.fxml"));
        mainLayout = loader.load();
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMainItems() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/MainItems.fxml"));
        BorderPane mainItems = loader.load();
        mainLayout.setCenter(mainItems);
    }

    public void showCompanyCreateView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/CompanyCreateView"));
        BorderPane companyCreateView = loader.load();
        mainLayout.setCenter(companyCreateView);
    }
}
