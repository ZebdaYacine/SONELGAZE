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
import model.Service;
import static sonelgaze.SONELGAZE.con;

/**
 *
 * @author Zed-Yacine
 */
public class ServiceController {
    
    private static void alterIdService(int id) throws SQLException {
        String sql = "ALTER TABLE `service` AUTO_INCREMENT =" + id + "";
        PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
        stm.execute();
        stm.close();
    }
    
    public static Results.Rstls addService(Service service) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into service (name) values (?)");
            stm.setString(1, service.getName());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.SERVICE_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.SERVICE_NOT_INSERTED;
        }
    }
    
    public static Results.Rstls updateService(Service service) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + "service SET name = ? WHERE id = ? ");
            stm.setString(1, service.getName());
            stm.setInt(2, service.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.SERVICE_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.SERVICE_NOT_UPDATED;
        }
    }
    
    public static Results.Rstls deleteServices(Service service) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + "service WHERE id = ?");
            stm.setInt(1, service.getId());
            stm.executeUpdate();
            alterIdService(service.getId());
            stm.close();
            return Results.Rstls.SERVICE_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.SERVICE_NOT_DELETED;
        }
    }
    
    public static Object getService(Service service) {
        String query ;
        if (service.getName() == null) {
            query = "SELECT * FROM service";
        } else {
            query = "SELECT * FROM service where name = '"+service.getName()+"'";
        }
        ObservableList<Service> listService = FXCollections.observableArrayList(new Service());
        listService.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Service ser = new Service();
                ser.setId(rs.getInt("id"));
                ser.setName(rs.getString("name"));
                listService.add(ser);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listService;
    }
    
    public static int getServiceIdFromName(String name) {
        String query = "SELECT id FROM service where name = '" + name+ "'";
        int serviceid = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                serviceid = rs.getInt("id");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return serviceid;
    }
    
}
