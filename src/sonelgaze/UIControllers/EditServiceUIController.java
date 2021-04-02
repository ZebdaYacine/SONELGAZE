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
public class EditServiceUIController implements Initializable {

    @FXML
    private JFXTextField name, id;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void deleteServiceUI(ActionEvent event) throws IOException, SQLException {
        Service service = new Service(Integer.parseInt(id.getText()),name.getText());
        Options.information(ServiceController.deleteServices(service)+"");
        refrechData();
    }

    @FXML
    private void updateServiceUI(ActionEvent event) throws IOException, SQLException {
        Service service = new Service(Integer.parseInt(id.getText()),name.getText());
        boolean statusData = Service.isDataEmpty(service);
        if (statusData) {
            Options.information(ServiceController.updateService(service)+"");
            refrechData();
        } else {
            Options.error("les champs sont vides");
        }
    }

    private void refrechData() {
        try {
            SuperController.refrechServices(table, Column1, Column2, new Service());
        } catch (SQLException ex) {
            Logger.getLogger(EditServiceUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void intiFileds(Service service) {
        id.setText(service.getId() + "");
        name.setText(service.getName());
    }
}
