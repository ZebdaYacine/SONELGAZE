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
public class AddDocsUIController implements Initializable {

    @FXML
    private JFXTextField name;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addDocs(ActionEvent event) throws IOException, SQLException {
        Document docs = new Document(name.getText());
        boolean statusData = Document.isDataEmpty(docs);
        if (statusData) {
            Options.information(DocumentController.addDocument(docs)+"");
            refrechData();
        } else {
            Options.error("les champs sont vides");
        }
    }

    public void refrechData() {
        try {
            SuperController.refrechDocs(table, Column1, Column2, new Document());
        } catch (SQLException ex) {
            Logger.getLogger(AddDocsUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
