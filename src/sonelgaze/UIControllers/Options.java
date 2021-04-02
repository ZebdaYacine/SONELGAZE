/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.UIControllers;

import com.sun.deploy.association.Action;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 *
 * @author zed
 */
public class Options implements Initializable {

    public static void information(String str) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Information Dialog");
        a.setHeaderText(null);
        a.setContentText(str);
        a.showAndWait();
    }

    public static boolean attention(String str) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("Attention");
        a.setHeaderText(null);
        a.setContentText(str);
        Optional<ButtonType> action = a.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public static void error(String str) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error...!!");
        alert.setContentText("les données inserées sont vide");
        alert.showAndWait();
    }

    public static void Alert(String str) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Information");
        alert.setHeaderText("");
        alert.setContentText(str);
        alert.showAndWait();
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
