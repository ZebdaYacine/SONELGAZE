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
import sonelgaze.BackEnd.ClientController;
import static sonelgaze.UIControllers.ClientUIController.*;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class ClientUIController1 implements Initializable {

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
        Client client = new Client(name.getText(), phone.getText());
        boolean statusData = Client.isDataEmpty(client);
        if (statusData) {
            Options.information(ClientController.addClient(client, "client") + "");
            refrechData();
        } else {
            Options.error("les champs sont vides");
        }
    }

    public void refrechData() {
        try {
            SuperController.refrechClients(table, Column1, Column2, Column3, new Client());
        } catch (SQLException ex) {
            Logger.getLogger(ClientUIController1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
