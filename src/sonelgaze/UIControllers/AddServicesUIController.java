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
import model.Service;
import sonelgaze.BackEnd.ClientController;
import sonelgaze.BackEnd.ServiceController;
import static sonelgaze.UIControllers.ServicesListUIController.*;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class AddServicesUIController implements Initializable {

    @FXML
    private JFXTextField name;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addService(ActionEvent event) throws IOException, SQLException {
        Service service = new Service(name.getText());
        boolean statusData = Service.isDataEmpty(service);
        if (statusData) {
            Options.information(ServiceController.addService(service)+"");
            refrechData();
        } else {
            Options.error("les champs sont vides");
        }
    }

    public void refrechData() {
        try {
            SuperController.refrechServices(table, Column1, Column2, new Service());
        } catch (SQLException ex) {
            Logger.getLogger(AddServicesUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
