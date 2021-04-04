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
import model.Demand;
import model.Do;
import model.Project;
import sonelgaze.BackEnd.ClientController;
import sonelgaze.BackEnd.DoController;
import sonelgaze.BackEnd.ProjectController;


/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class DoListUIController implements Initializable {

   @FXML
    private TableColumn idColumn,EntreproColumn,ProjectColumn,StatusColumn;

    @FXML
    private TableView DoTable;

    @FXML
    private JFXTextField searchText;
    
    public static TableColumn Column1, Column2,Column3,Column4;
    public static TableView table;
    

    public void loadData(Do d) {  
        try {
            SuperController.refrechDo(table, Column1, Column2, Column3, Column4, d);
        } catch (SQLException ex) {
            Logger.getLogger(DoListUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Column1=idColumn;
        Column2=EntreproColumn;
        Column3=ProjectColumn;
        Column4=StatusColumn;
        table=DoTable;
        loadData(new Do());
    }

    @FXML
    private void enableSearch(ActionEvent event) throws IOException {
    }

    @FXML
    public void search(KeyEvent ky) throws SQLException {
        if (searchText.getText().isEmpty()) {
            loadData(new Do());
        } else {
            int idProject=ProjectController.getProjectIdFromName(searchText.getText());
            loadData(new Do(idProject,""));
        }
    }

    @FXML
    private void loadDemandsUI(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sonelgaze/FrontEnd/AddDoUI.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("nouvelle realisation");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ClientUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void selectDemands(MouseEvent event) throws IOException {
        Do d = (Do) DoTable.getSelectionModel().getSelectedItem();
        if (d == null) {
            Options.information("aucun realisation sélectionné");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sonelgaze/FrontEnd/EditDoUI.fxml"));
            Parent root = loader.load();
            EditDoUIController controller = loader.getController();
            controller.intiFileds(d);
            Stage stage = new Stage();
            stage.setTitle("information de Dossier");
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
