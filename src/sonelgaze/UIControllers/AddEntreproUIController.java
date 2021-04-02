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
import model.Entreprenor;
import sonelgaze.BackEnd.ClientController;
import sonelgaze.BackEnd.EntreprenorController;
import static sonelgaze.UIControllers.EntreproUIController.*;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class AddEntreproUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField name, phone;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addClient(ActionEvent event) throws IOException, SQLException {
        Entreprenor entrepo = new Entreprenor(name.getText(), phone.getText());
        boolean statusData = Entreprenor.isDataEmpty(entrepo);
        if (statusData) {
            Options.information(EntreprenorController.addClient(entrepo, "entreprenor") + "");
            refrechData();
        } else {
            Options.error("les champs sont vides");
        }
    }

    public void refrechData() {
        try {
            SuperController.refrechClients(table, Column1, Column2, Column3, new Client(),"entreprenor");
        } catch (SQLException ex) {
            Logger.getLogger(AddEntreproUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
