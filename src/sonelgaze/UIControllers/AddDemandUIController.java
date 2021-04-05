/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.UIControllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.Client;
import model.Demand;
import model.Document;
import model.Service;
import sonelgaze.BackEnd.ClientController;
import sonelgaze.BackEnd.DemandController;
import sonelgaze.BackEnd.DocumentController;
import sonelgaze.BackEnd.HasController;
import sonelgaze.BackEnd.ServiceController;
import static sonelgaze.UIControllers.DemandListUIController.*;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class AddDemandUIController implements Initializable {

    @FXML
    private JFXComboBox CmbClient, CmbService, CmbStatus;

    @FXML
    private JFXDatePicker date;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Service> services = (ObservableList<Service>) HasController.getAllServicesNmae();
        ObservableList<Client> clients = (ObservableList<Client>) ClientController.getAllClientsName();
        CmbService.getItems().clear();
        CmbClient.getItems().clear();
        for (Service item : services) {
            CmbService.getItems().add(item.getName());
        }
        CmbService.getSelectionModel().selectFirst();

        for (Client item : clients) {
            CmbClient.getItems().add(item.getName());
        }
        CmbClient.getSelectionModel().selectFirst();
        ObservableList<String> status
                = FXCollections.observableArrayList(
                        "not yet",
                        "accepte",
                        "refused",
                        "payment"
                );
        for (String item : status) {
            CmbStatus.getItems().add(item);
        }
    }

    @FXML
    private void addDemand(ActionEvent event) throws IOException, SQLException {
        String status = (String) CmbStatus.getSelectionModel().getSelectedItem();
        LocalDate dte = date.getValue();
        if (dte != null && status != "") {
            int idService = ServiceController.getServiceIdFromName((String) CmbService.getSelectionModel().getSelectedItem());
            int idClient = ClientController.getClientIdFromName((String) CmbClient.getSelectionModel().getSelectedItem(), "client");
            Demand d = new Demand(idClient, idService, status, Date.valueOf(dte));
            Options.information(DemandController.addDemand(d) + "");
            refrechData();
            d.setServiceName((String) CmbService.getSelectionModel().getSelectedItem());
            d.setClientName((String) CmbClient.getSelectionModel().getSelectedItem());
            d.setId(DemandController.getDemandId(d));
            new Thread(() -> {
                Bill.demandBill(d,ClientController.getClientPhoneFromId(idClient,"client"));
            }).start();
        } else {
            Options.information("les champssont vide");
        }
    }

    public void refrechData() {
        try {
            SuperController.refrechDemand(table, Column1, Column2, Column3, Column4, Column5, new Demand());
        } catch (SQLException ex) {
            Logger.getLogger(DemandListUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
