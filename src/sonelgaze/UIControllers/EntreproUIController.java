/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.UIControllers;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Client;
import model.Entreprenor;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */

public class EntreproUIController implements Initializable {

    @FXML
    private TableColumn nameColumn, idColumn, phoneColumn;

    @FXML
    private TableView EntreproTable;

    @FXML
    private JFXTextField searchText;
    
    public static TableColumn Column1, Column2, Column3;
    public static TableView table;
    

    public void loadData(Client cln) {  
        try {
            SuperController.refrechClients(EntreproTable,idColumn,nameColumn, phoneColumn,cln,"entreprenor");
        } catch (SQLException ex) {
            Logger.getLogger(EntreproUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Column1=idColumn;
        Column2=nameColumn;
        Column3=phoneColumn;
        table=EntreproTable;
        loadData(new Client());
    }

    @FXML
    private void enableSearch(ActionEvent event) throws IOException {
    }

    @FXML
    public void search(KeyEvent ky) throws SQLException {
        if (searchText.getText().isEmpty()) {
            loadData(new Client());
        } else {
            loadData(new Client(searchText.getText()));
        }
    }

    @FXML
    private void loadClientUI(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sonelgaze/FrontEnd/AddEntreproUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("nouvelle Entreproneur");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EntreproUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectClient(MouseEvent event) throws IOException {
        Client client = (Client) EntreproTable.getSelectionModel().getSelectedItem();
        if (client == null) {
            Options.information("aucun entreproneur sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sonelgaze/FrontEnd/EditEntreproUI.fxml"));
            Parent root = loader.load();
            EditEntreproUIController controller = loader.getController();
            controller.intiFileds(client);
            Stage stage = new Stage();
            stage.setTitle("information de entreproneur");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    
    
}
