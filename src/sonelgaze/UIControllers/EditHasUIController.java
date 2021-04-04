/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.UIControllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import static sonelgaze.UIControllers.HasListUIController.Column1;
import static sonelgaze.UIControllers.HasListUIController.Column2;
import static sonelgaze.UIControllers.HasListUIController.Column3;
import static sonelgaze.UIControllers.HasListUIController.table;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class EditHasUIController implements Initializable {

    @FXML
    private JFXComboBox CmbService, CmbDocs;

    @FXML
    private JFXTextField id;

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
    private void deleteDocsUI(ActionEvent event) throws IOException, SQLException {
        Has has = new Has(Integer.parseInt(id.getText()));
        Options.information(HasController.deleteHas(has) + "");
        refrechData();
    }

    @FXML
    private void updateDocsUI(ActionEvent event) throws IOException, SQLException {
        int idService = ServiceController.getServiceIdFromName((String) CmbService.getSelectionModel().getSelectedItem());
        int idDocs = DocumentController.getDocsIdFromName((String) CmbDocs.getSelectionModel().getSelectedItem());
        Has has = new Has(Integer.parseInt(id.getText()), idDocs, idService);
        Options.information(HasController.updateHas(has) + "");
        refrechData();
    }

    public void refrechData() {
        try {
            SuperController.refrechHas(table, Column1, Column2, Column3, new Has());
        } catch (SQLException ex) {
            Logger.getLogger(AddHasUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void intiFileds(Has has) {
        id.setText(has.getId() + "");
        CmbDocs.setValue(has.getDocumentName());
        CmbService.setValue(has.getServiceName());
    }
}
