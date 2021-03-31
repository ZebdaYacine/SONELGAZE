/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.BackEnd;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;
import static sonelgaze.SONELGAZE.con;

/**
 *
 * @author Zed-Yacine
 */
public class ClientController {

    private static void alterIdClient(int id) throws SQLException {
        String sql = "ALTER TABLE `client` AUTO_INCREMENT =" + id + "";
        PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
        stm.execute();
        stm.close();
    }

    public static Results.Rstls addClient(Client client) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into client (name,phone) values (?,?)");
            stm.setString(1, client.getName());
            stm.setString(2, client.getPhone());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.CLIENT_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.CLIENT_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateClient(Client client) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + "client SET name = ?, phone = ? WHERE id = ? ");
            stm.setString(1, client.getName());
            stm.setString(2, client.getPhone());
            stm.setInt(3, client.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.CLIENT_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.CLIENT_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteClient(Client client) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + "client WHERE id = ?");
            stm.setInt(1, client.getId());
            stm.executeUpdate();
            alterIdClient(client.getId());
            stm.close();
            return Results.Rstls.CLIENT_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.CLIENT_NOT_DELETED;
        }
    }

    public static Object getClients(Client client) {
        String query ;
        if (client.getName() == null) {
            query = "SELECT * FROM client";
        } else {
            query = "SELECT * FROM client where name = '"+client.getName()+"'";
        }
        ObservableList<Client> listClient = FXCollections.observableArrayList(new Client());
        listClient.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Client clnt = new Client();
                clnt.setId(rs.getInt("id"));
                clnt.setName(rs.getString("name"));
                clnt.setPhone(rs.getString("phone"));
                listClient.add(clnt);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listClient;
    }

}
