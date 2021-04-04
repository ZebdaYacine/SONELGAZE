/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.BackEnd;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;
import model.Entreprenor;
import static sonelgaze.SONELGAZE.con;

/**
 *
 * @author Zed-Yacine
 */
public class EntreprenorController extends ClientController {

    public static Object getAllEntreproneurName() {
        String query;
        query = "SELECT name FROM entreprenor ";
        ObservableList<Entreprenor> listClients = FXCollections.observableArrayList(new Entreprenor());
        listClients.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Entreprenor entr = new Entreprenor();
                entr.setName(rs.getString("name"));
                listClients.add(entr);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listClients;
    }
}
