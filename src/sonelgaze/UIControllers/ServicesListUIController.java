/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.UIControllers;

import com.jfoenix.controls.JFXTextField;
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
import model.Service;


/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class ServicesListUIController implements Initializable {

   @FXML
    private TableColumn nameColumn, idColumn;

    @FXML
    private TableView ServicesTable;

    @FXML
    private JFXTextField searchText;
    
    public static TableColumn Column1, Column2;
    public static TableView table;
    

    public void loadData(Service service) {  
        try {
            SuperController.refrechServices(ServicesTable,idColumn,nameColumn,service);
        } catch (SQLException ex) {
            Logger.getLogger(ServicesListUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Column1=idColumn;
        Column2=nameColumn;
        table=ServicesTable;
        loadData(new Service());
    }

    @FXML
    private void enableSearch(ActionEvent event) throws IOException {
    }

    @FXML
    public void search(KeyEvent ky) throws SQLException {
        if (searchText.getText().isEmpty()) {
            loadData(new Service());
        } else {
            loadData(new Service(searchText.getText()));
        }
    }

    @FXML
    private void loadServiceUI(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sonelgaze/FrontEnd/AddServicesUI.fxml"));
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
    private void selectService(MouseEvent event) throws IOException {
        Service service = (Service) ServicesTable.getSelectionModel().getSelectedItem();
        if (service == null) {
            Options.information("aucun service sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sonelgaze/FrontEnd/EditServiceUI.fxml"));
            Parent root = loader.load();
            EditServiceUIController controller = loader.getController();
            controller.intiFileds(service);
            Stage stage = new Stage();
            stage.setTitle("information de servcie");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
