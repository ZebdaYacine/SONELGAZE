/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.UIControllers;

import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Document;
import model.Has;
import model.Service;
import sonelgaze.BackEnd.DocumentController;
import sonelgaze.BackEnd.HasController;
import sonelgaze.BackEnd.ServiceController;
import static sonelgaze.UIControllers.HasListUIController.*;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class AddHasUIController implements Initializable {

    @FXML
    private JFXComboBox CmbService, CmbDocs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Service> services = (ObservableList<Service>) HasController.getAllServicesNmae();
        ObservableList<Document> docs = (ObservableList<Document>) HasController.getAllDocsNmae();
        CmbService.getItems().clear();
        CmbDocs.getItems().clear();
        for (Service item : services) {
            CmbService.getItems().add(item.getName());
        }
        CmbService.getSelectionModel().selectFirst();

        for (Document item : docs) {
            CmbDocs.getItems().add(item.getName());
        }
        CmbDocs.getSelectionModel().selectFirst();

    }

    @FXML
    private void addHas(ActionEvent event) throws IOException, SQLException {
        int idService = ServiceController.getServiceIdFromName((String) CmbService.getSelectionModel().getSelectedItem());
        int idDocs = DocumentController.getDocsIdFromName((String) CmbDocs.getSelectionModel().getSelectedItem());
        Has h = new Has(idDocs, idService);
        Options.information(HasController.addHas(h) + "");
        refrechData();
    }

    public void refrechData() {
        try {
            SuperController.refrechHas(table, Column1, Column2, Column3, new Has());
        } catch (SQLException ex) {
            Logger.getLogger(AddHasUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
