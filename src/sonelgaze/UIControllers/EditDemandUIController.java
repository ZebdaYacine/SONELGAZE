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
import model.Project;
import model.Service;
import sonelgaze.BackEnd.ClientController;
import sonelgaze.BackEnd.DemandController;
import sonelgaze.BackEnd.HasController;
import sonelgaze.BackEnd.ProjectController;
import sonelgaze.BackEnd.ServiceController;
import static sonelgaze.UIControllers.DemandListUIController.*;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class EditDemandUIController implements Initializable {

    @FXML
    private JFXComboBox CmbClient, CmbService, CmbStatus;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXTextField id;

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
    private void deleteDemandUI(ActionEvent event) throws IOException, SQLException {
        Demand d = new Demand(Integer.parseInt(id.getText()));
        Options.information(DemandController.deleteDemand(d) + "");
        refrechData();
    }

    @FXML
    private void updateDemandUI(ActionEvent event) throws IOException, SQLException {
        String str = "Cette demande sera supprimée du tableau des demandes et le calendrier du projet sera inclus,  vous êtes sûr de confimre cette transaction?";
        String status = (String) CmbStatus.getSelectionModel().getSelectedItem();
        LocalDate dte = date.getValue();
        int idService = ServiceController.getServiceIdFromName((String) CmbService.getSelectionModel().getSelectedItem());
        int idClient = ClientController.getClientIdFromName((String) CmbClient.getSelectionModel().getSelectedItem(), "client");
        if (dte != null && status != "") {
            if (status.equals("payment")) {
                if (Options.attention(str)) {
                    int d = Integer.parseInt(id.getText());
                    Options.information(ProjectController.addProject(new Project(d, "")) + "");
                    Demand d1 = new Demand(idClient, idService, status, Date.valueOf(dte));
                    d1.setServiceName((String) CmbService.getSelectionModel().getSelectedItem());
                    d1.setClientName((String) CmbClient.getSelectionModel().getSelectedItem());
                    d1.setId(DemandController.getDemandId(d1));
                    new Thread(() -> {
                        Bill.demandBill(d1, ClientController.getClientPhoneFromId(idClient, "client"));
                    }).start();
                } else {
                    Options.information("impossible de terminer cette transaction");
                }
            }
            Demand d = new Demand(Integer.parseInt(id.getText()), idClient, idService, status, Date.valueOf(dte));
            Options.information(DemandController.updateDemand(d) + "");
            refrechData();
        } else {
            Options.information("les champs sont vide");
        }
    }

    public void refrechData() {
        try {
            SuperController.refrechDemand(table, Column1, Column2, Column3, Column4, Column5, new Demand());
        } catch (SQLException ex) {
            Logger.getLogger(DemandListUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void intiFileds(Demand d) {
        id.setText(d.getId() + "");
        CmbClient.setValue(d.getClientName());
        CmbService.setValue(d.getServiceName());
//        date.setValue(LOCAL_DATE("01-05-2016"));
        CmbStatus.setValue(d.isStatus());
    }

    private LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
