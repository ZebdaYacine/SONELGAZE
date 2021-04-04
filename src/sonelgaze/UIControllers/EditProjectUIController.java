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
import model.Project;
import sonelgaze.BackEnd.ProjectController;
import static sonelgaze.UIControllers.ProjectListUIController.Column1;
import static sonelgaze.UIControllers.ProjectListUIController.Column2;
import static sonelgaze.UIControllers.ProjectListUIController.Column3;
import static sonelgaze.UIControllers.ProjectListUIController.table;

/**
 * FXML Controller class
 *
 * @author Zed-Yacine
 */
public class EditProjectUIController implements Initializable {

    @FXML
    private JFXTextField projectName, idDemand, id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void updateProjetUI(ActionEvent event) throws IOException, SQLException {
        if (projectName.getText().equals("")) {
            Options.error("le nom deproject doit etre no vide");
        }else{
            Project project = new Project(Integer.parseInt(id.getText()),Integer.parseInt(idDemand.getText()),projectName.getText());
            ProjectController.updateProject(project);
            refrechData();
        }

    }

    public void refrechData() {
       try {
            SuperController.refrechProject(table, Column1, Column2, Column3, new Project());
        } catch (SQLException ex) {
            Logger.getLogger(ProjectListUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void intiFileds(Project p) {
        id.setText(p.getId() + "");
        idDemand.setText(p.getIdDemand() + "");
        projectName.setText(p.getName());
    }

}
