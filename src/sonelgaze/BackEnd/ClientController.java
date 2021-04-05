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
import model.Service;
import static sonelgaze.SONELGAZE.con;

/**
 *
 * @author Zed-Yacine
 */
public class ClientController {

    private static void alterIdClient(int id, String tab) throws SQLException {
        String sql = "ALTER TABLE `" + tab + "` AUTO_INCREMENT =" + id + "";
        PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
        stm.execute();
        stm.close();
    }

    public static Results.Rstls addClient(Client client, String tab) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into " + tab + " (name,phone) values (?,?)");
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

    public static Results.Rstls updateClient(Client client, String tab) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + "" + tab + " SET name = ?, phone = ? WHERE id = ? ");
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

    public static Results.Rstls deleteClient(Client client, String tab) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + "" + tab + " WHERE id = ?");
            stm.setInt(1, client.getId());
            stm.executeUpdate();
            alterIdClient(client.getId(), tab);
            stm.close();
            return Results.Rstls.CLIENT_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.CLIENT_NOT_DELETED;
        }
    }

    public static Object getClients(Client client, String tab) {
        String query;
        if (client.getName() == null) {
            query = "SELECT * FROM " + tab + "";
        } else {
            query = "SELECT * FROM " + tab + " where name LIKE '" + client.getName() + "%'";
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

    public static int getClientIdFromName(String name, String tab) {
        String query = "SELECT id FROM " + tab + " where name = '" + name + " ' ";
        int clientId = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clientId = rs.getInt("id");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clientId;
    }

    public static Object getAllClientsName() {
        String query;
        query = "SELECT name FROM client ";
        ObservableList<Client> listClients = FXCollections.observableArrayList(new Client());
        listClients.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setName(rs.getString("name"));
                listClients.add(client);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listClients;
    }

    public static String getClientPhoneFromId(int id, String tab) {
        String query = "SELECT phone FROM " + tab + " where id = " + id;
        String clientPhone = "";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clientPhone = rs.getString("phone");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clientPhone;
    }
    
    public static String getClientNmaeFromId(int id, String tab) {
        String query = "SELECT name FROM " + tab + " where id = " + id ;
        String clientName = "";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clientName = rs.getString("name");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clientName;
    }

}
