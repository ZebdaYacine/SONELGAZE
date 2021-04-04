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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Client;
import model.Document;
import model.Project;
import model.Service;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class ProjectListUIController implements Initializable {

    @FXML
    private TableColumn idColumn, projectColumn, demendColumn;

    @FXML
    private TableView ProjectTable;

    @FXML
    private JFXTextField searchText;

    public static TableColumn Column1, Column2, Column3;
    public static TableView table;

    public void loadData(Project project) {
        try {
            SuperController.refrechProject(table, Column1, Column2, Column3, project);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectListUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Column1 = idColumn;
        Column2 = projectColumn;
        Column3 = demendColumn;
        table = ProjectTable;
        loadData(new Project());
    }

    @FXML
    private void enableSearch(ActionEvent event) throws IOException {
    }

    @FXML
    public void search(KeyEvent ky) throws SQLException {
        if (searchText.getText().isEmpty()) {
            loadData(new Project());
        } else {
            String idD = searchText.getText();
            try {
                int id = Integer.parseInt(idD);
                loadData(new Project(id,""));
            } catch (Exception e) {
                Options.error("id Demend n'est pas jaust");
            }
        }
    }

    @FXML
    private void loadDocsUI(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sonelgaze/FrontEnd/AddDocsUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("nouvelle Client");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ClientUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectDocs(MouseEvent event) throws IOException {
        Project project = (Project) ProjectTable.getSelectionModel().getSelectedItem();
        if (project == null) {
            Options.information("aucun service sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sonelgaze/FrontEnd/EditProjectUI.fxml"));
            Parent root = loader.load();
            EditProjectUIController controller = loader.getController();
            controller.intiFileds(project);
            Stage stage = new Stage();
            stage.setTitle("information de projet");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
