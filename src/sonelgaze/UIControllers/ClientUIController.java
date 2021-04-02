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

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */

public class ClientUIController implements Initializable {

    @FXML
    private TableColumn nameColumn, idColumn, phoneColumn;

    @FXML
    private TableView ClientsTable;

    @FXML
    private JFXTextField searchText;
    
    public static TableColumn Column1, Column2, Column3;
    public static TableView table;
    

    public void loadData(Client cln) {  
        try {
            SuperController.refrechClients(ClientsTable,idColumn,nameColumn, phoneColumn,cln);
        } catch (SQLException ex) {
            Logger.getLogger(ClientUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Column1=idColumn;
        Column2=nameColumn;
        Column3=phoneColumn;
        table=ClientsTable;
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
            Parent root = FXMLLoader.load(getClass().getResource("/sonelgaze/FrontEnd/ClientUI1.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("nouvelle Client");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ClientUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectClient(MouseEvent event) throws IOException {
        Client client = (Client) ClientsTable.getSelectionModel().getSelectedItem();
        if (client == null) {
            Options.information("aucun client sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sonelgaze/FrontEnd/EditClientUI.fxml"));
            Parent root = loader.load();
            EditClientUIController controller = loader.getController();
            controller.intiFileds(client);
            Stage stage = new Stage();
            stage.setTitle("information de client");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
    
    
}
