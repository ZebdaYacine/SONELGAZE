/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.UIControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.Client;
import sonelgaze.BackEnd.ClientController;
import static sonelgaze.UIControllers.EntreproUIController.*;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class EditEntreproUIController implements Initializable {

    @FXML
    private JFXTextField name, phone, id;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void deleteClientUI(ActionEvent event) throws IOException, SQLException {
        Client client = new Client(Integer.parseInt(id.getText()),name.getText(), phone.getText());
        Options.information(ClientController.deleteClient(client, "entreprenor") + "");
        refrechData();
    }

    @FXML
    private void updateClientUI(ActionEvent event) throws IOException, SQLException {
        Client client = new Client(Integer.parseInt(id.getText()),name.getText(), phone.getText());
        boolean statusData = Client.isDataEmpty(client);
        if (statusData) {
            Options.information(ClientController.updateClient(client, "entreprenor") + "");
            refrechData();
        } else {
            Options.error("les champs sont vides");
        }
    }

    private void refrechData() {
        try {
            SuperController.refrechClients(table, Column1, Column2, Column3, new Client(),"entreprenor");
        } catch (SQLException ex) {
            Logger.getLogger(ClientUIController1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void intiFileds(Client client) {
        id.setText(client.getId() + "");
        name.setText(client.getName());
        phone.setText(client.getPhone());
    }
}
