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
import model.Document;
import model.Service;
import sonelgaze.BackEnd.ClientController;
import sonelgaze.BackEnd.DocumentController;
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
    
    
}
