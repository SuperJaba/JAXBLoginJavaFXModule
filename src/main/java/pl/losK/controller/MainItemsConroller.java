package pl.losK.controller;

import javafx.fxml.FXML;
import pl.losK.application.MainCompany;

import java.io.IOException;

/**
 * Created by m.losK on 2017-03-16.
 */
public class MainItemsConroller {

    private MainCompany main;


    @FXML
    private void switchToCompanyCreateView() throws IOException {
        main.showCompanyCreateView();
    }
}
