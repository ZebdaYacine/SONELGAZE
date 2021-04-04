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
import model.Demand;
import sonelgaze.BackEnd.ClientController;


/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class DemandListUIController implements Initializable {

   @FXML
    private TableColumn ClientColumn,ServiceColumn,DateColumn,idColumn,StatusColumn;

    @FXML
    private TableView DemandTable;

    @FXML
    private JFXTextField searchText;
    
    public static TableColumn Column1, Column2,Column3,Column4, Column5;
    public static TableView table;
    

    public void loadData(Demand demand) {  
        try {
            SuperController.refrechDemand(DemandTable, idColumn, ClientColumn, ServiceColumn, DateColumn, StatusColumn, demand);
        } catch (SQLException ex) {
            Logger.getLogger(DemandListUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Column1=idColumn;
        Column2=ClientColumn;
        Column3=ServiceColumn;
        Column4=DateColumn;
        Column5=StatusColumn;
        table=DemandTable;
        loadData(new Demand());
    }

    @FXML
    private void enableSearch(ActionEvent event) throws IOException {
    }

    @FXML
    public void search(KeyEvent ky) throws SQLException {
        if (searchText.getText().isEmpty()) {
            loadData(new Demand());
        } else {
            int idClient=ClientController.getClientIdFromName(searchText.getText(),"client");
            loadData(new Demand(idClient,""));
        }
    }

    @FXML
    private void loadDemandsUI(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sonelgaze/FrontEnd/AddDemandUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("nouvelle Demend");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ClientUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectDemands(MouseEvent event) throws IOException {
        Demand d = (Demand) DemandTable.getSelectionModel().getSelectedItem();
        if (d == null) {
            Options.information("aucun demand sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sonelgaze/FrontEnd/EditDemandUI.fxml"));
            Parent root = loader.load();
            EditDemandUIController controller = loader.getController();
            controller.intiFileds(d);
            Stage stage = new Stage();
            stage.setTitle("information de Dossier");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
