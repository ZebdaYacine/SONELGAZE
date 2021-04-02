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
import model.Document;
import model.Has;
import model.Service;
import sonelgaze.BackEnd.HasController;
import sonelgaze.BackEnd.ServiceController;


/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class HasListUIController implements Initializable {

   @FXML
    private TableColumn ServiceColumn, idColumn,DocumentColumn;

    @FXML
    private TableView HasTable;

    @FXML
    private JFXTextField searchText;
    
    public static TableColumn Column1, Column2,Column3;
    public static TableView table;
    

    public void loadData(Has has) {  
        try {
            SuperController.refrechHas(HasTable,idColumn,ServiceColumn,DocumentColumn,has);
        } catch (SQLException ex) {
            Logger.getLogger(HasListUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Column1=idColumn;
        Column2=ServiceColumn;
        Column3=DocumentColumn;
        table=HasTable;
        loadData(new Has());
    }

    @FXML
    private void enableSearch(ActionEvent event) throws IOException {
    }

    @FXML
    public void search(KeyEvent ky) throws SQLException {
        if (searchText.getText().isEmpty()) {
            loadData(new Has());
        } else {
            int idService=ServiceController.getServiceIdFromName(searchText.getText());
            loadData(new Has(idService,""));
        }
    }

    @FXML
    private void loadFoldersUI(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sonelgaze/FrontEnd/AddHasUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("nouvelle Dossier");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ClientUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectDocs(MouseEvent event) throws IOException {
        Has has = (Has) HasTable.getSelectionModel().getSelectedItem();
        if (has == null) {
            Options.information("aucun service sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sonelgaze/FrontEnd/EditHasUI.fxml"));
            Parent root = loader.load();
            EditHasUIController controller = loader.getController();
            controller.intiFileds(has);
            Stage stage = new Stage();
            stage.setTitle("information de Dossier");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
