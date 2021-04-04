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
import model.Do;
import model.Project;
import model.Service;
import sonelgaze.BackEnd.ClientController;
import sonelgaze.BackEnd.DemandController;
import sonelgaze.BackEnd.DoController;
import sonelgaze.BackEnd.EntreprenorController;
import sonelgaze.BackEnd.HasController;
import sonelgaze.BackEnd.ProjectController;
import sonelgaze.BackEnd.ServiceController;
import static sonelgaze.UIControllers.DemandListUIController.*;
import static sonelgaze.UIControllers.DoListUIController.Column1;
import static sonelgaze.UIControllers.DoListUIController.Column2;
import static sonelgaze.UIControllers.DoListUIController.Column3;
import static sonelgaze.UIControllers.DoListUIController.Column4;
import static sonelgaze.UIControllers.DoListUIController.table;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class EditDoUIController implements Initializable {
    
    @FXML
    private JFXComboBox CmbEntrepro, CmbProject, CmbStatus;
    
    @FXML
    private JFXTextField id;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Project> projects = (ObservableList<Project>) ProjectController.getAllProjectsName();
        ObservableList<Client> clients = (ObservableList<Client>) EntreprenorController.getAllEntreproneurName();
        CmbProject.getItems().clear();
        CmbEntrepro.getItems().clear();
        for (Project item : projects) {
            CmbProject.getItems().add(item.getName());
        }
        CmbProject.getSelectionModel().selectFirst();
        
        for (Client item : clients) {
            CmbEntrepro.getItems().add(item.getName());
        }
        CmbEntrepro.getSelectionModel().selectFirst();
        ObservableList<String> status
                = FXCollections.observableArrayList(
                        "not yet",
                        "fini"
                );
        for (String item : status) {
            CmbStatus.getItems().add(item);
        }
    }
    
    @FXML
    private void deleteDoUI(ActionEvent event) throws IOException, SQLException {
        Do d = new Do(Integer.parseInt(id.getText()));
        Options.information(DoController.deleteDO(d) + "");
        refrechData();
    }
    
    @FXML
    private void updateDoUI(ActionEvent event) throws IOException, SQLException {
        String status = (String) CmbStatus.getSelectionModel().getSelectedItem();
        int idProject = ProjectController.getProjectIdFromName((String) CmbProject.getSelectionModel().getSelectedItem());
        int idEntrepro = EntreprenorController.getClientIdFromName((String) CmbEntrepro.getSelectionModel().getSelectedItem(), "entreprenor");
        if (status != "") {
            Do d = new Do(Integer.parseInt(id.getText()), idEntrepro, idProject, status);
            Options.information(DoController.updateDO(d) + "");
            refrechData();
        } else {
            Options.information("les champs sont vide");
        }
    }
    
    public void refrechData() {
        try {
            SuperController.refrechDo(table, Column1, Column2, Column3, Column4, new Do());
        } catch (SQLException ex) {
            Logger.getLogger(DoListUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void intiFileds(Do d) {
        id.setText(d.getId() + "");
        CmbEntrepro.setValue(d.getEtrepoName());
        CmbProject.setValue(d.getProjectName());
        CmbStatus.setValue(d.getStatus());
    }
    
}
