/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.UIControllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import model.Demand;
import model.Do;
import model.Document;
import model.Has;
import model.Project;
import model.Service;
import sonelgaze.BackEnd.ClientController;
import sonelgaze.BackEnd.DemandController;
import sonelgaze.BackEnd.DoController;
import sonelgaze.BackEnd.DocumentController;
import sonelgaze.BackEnd.HasController;
import sonelgaze.BackEnd.ProjectController;
import sonelgaze.BackEnd.ServiceController;

/**
 *
 * @author Zed-Yacine
 */
public class SuperController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public static void refrechClients(TableView table, TableColumn Column1, TableColumn Column2, TableColumn Column3,
            Object obj, String tab)
            throws SQLException {
        ObservableList<Client> clients = (ObservableList<Client>) ClientController.getClients((Client) obj, tab);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("phone")
        );

        table.setItems(clients);
    }

    public static void refrechServices(TableView table, TableColumn Column1, TableColumn Column2, Service service)
            throws SQLException {
        ObservableList<Service> services = (ObservableList<Service>) ServiceController.getService(service);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        table.setItems(services);
    }

    public static void refrechDocs(TableView table, TableColumn Column1, TableColumn Column2, Document docs)
            throws SQLException {
        ObservableList<Document> d = (ObservableList<Document>) DocumentController.getDocuments(docs);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        table.setItems(d);
    }

    public static void refrechHas(TableView table, TableColumn Column1, TableColumn Column2, TableColumn Column3, Has has)
            throws SQLException {
        ObservableList<Has> d = (ObservableList<Has>) HasController.getHas(has);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("serviceName")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("documentName")
        );
        table.setItems(d);
    }

    public static void refrechDemand(TableView table, TableColumn Column1, TableColumn Column2, TableColumn Column3,
            TableColumn Column4, TableColumn Column5, Demand dmd)
            throws SQLException {
        ObservableList<Demand> d = (ObservableList<Demand>) DemandController.getDemands(dmd);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("clientName")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("serviceName")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        Column5.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );
        table.setItems(d);
    }

    public static void refrechProject(TableView table, TableColumn Column1, TableColumn Column2, TableColumn Column3, Project project)
            throws SQLException {
        ObservableList<Project> p = (ObservableList<Project>) ProjectController.getProject(project);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("idDemand")
        );
        table.setItems(p);
    }
    
    public static void refrechDo(TableView table, TableColumn Column1, TableColumn Column2, TableColumn Column3,
            TableColumn Column4,Do d)
            throws SQLException {
        ObservableList<Do> doing = (ObservableList<Do>) DoController.getDO(d);
        Column1.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        Column2.setCellValueFactory(
                new PropertyValueFactory<>("etrepoName")
        );
        Column3.setCellValueFactory(
                new PropertyValueFactory<>("projectName")
        );
        Column4.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );
        table.setItems(doing);
    }

}
