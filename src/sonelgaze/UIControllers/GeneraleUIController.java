/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.UIControllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class GeneraleUIController implements Initializable {

    @FXML
    private BorderPane main;

    @FXML
    private JFXButton logoutBtn;

    private void loadClient() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sonelgaze/FrontEnd/ClientUI.fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadClient();
        } catch (IOException ex) {
            Logger.getLogger(GeneraleUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadClientUI(ActionEvent event) throws IOException {
        loadClient();
    }

    @FXML
    private void loadServicesUI(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sonelgaze/FrontEnd/ServicesListUI.fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @FXML
    private void loadDocsUI(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sonelgaze/FrontEnd/DocsListUI.fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @FXML
    private void loadFoldersUI(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sonelgaze/FrontEnd/HasListUI.fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @FXML
    private void loadEnterproUI(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sonelgaze/FrontEnd/EntreproUI.fxml"));
        main.setCenter(null);
        if (root != null) {
            main.setCenter(root);
        }
    }

    @FXML
    private void loadDemends(ActionEvent event) throws IOException {

    }

    @FXML
    private void loadProjects(ActionEvent event) throws IOException {

    }

    @FXML
    private void loadDos(ActionEvent event) throws IOException {

    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        stage.close();
    }

}
