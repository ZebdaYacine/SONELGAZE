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
import javafx.fxml.Initializable;
import model.Client;
import model.Document;
import model.Service;
import sonelgaze.BackEnd.ClientController;
import sonelgaze.BackEnd.DocumentController;
import sonelgaze.BackEnd.ServiceController;
import static sonelgaze.UIControllers.DocsListUIController.*;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class EditDocsUIController implements Initializable {

    @FXML
    private JFXTextField name, id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void deleteDocsUI(ActionEvent event) throws IOException, SQLException {
        Document docs = new Document(Integer.parseInt(id.getText()), name.getText());
        Options.information(DocumentController.deleteDocuments(docs) + "");
        refrechData();
    }

    @FXML
    private void updateDocsUI(ActionEvent event) throws IOException, SQLException {
        Document docs = new Document(Integer.parseInt(id.getText()), name.getText());
        boolean statusData = Document.isDataEmpty(docs);
        if (statusData) {
            Options.information(DocumentController.updateDocument(docs) + "");
            refrechData();
        } else {
            Options.error("les champs sont vides");
        }
    }

    private void refrechData() {
        try {
            SuperController.refrechDocs(table, Column1, Column2, new Document());
        } catch (SQLException ex) {
            Logger.getLogger(EditDocsUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void intiFileds(Document docs) {
        id.setText(docs.getId() + "");
        name.setText(docs.getName());
    }
}
