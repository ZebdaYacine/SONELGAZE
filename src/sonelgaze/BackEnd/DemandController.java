/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.BackEnd;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Demand;
import static sonelgaze.SONELGAZE.con;

/**
 *
 * @author Zed-Yacine
 */
public class DemandController {

    private static void alterIdDemand(int id) throws SQLException {
        String sql = "ALTER TABLE `demand` AUTO_INCREMENT =" + id + "";
        PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
        stm.execute();
        stm.close();
    }

    public static Results.Rstls addDemand(Demand demand) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into demand (idClient,idService,status,date) values (?,?,?,?)");
            stm.setInt(1, demand.getIdClient());
            stm.setInt(2, demand.getIdService());
            stm.setString(3, demand.isStatus());
            stm.setDate(4, (Date) demand.getDate());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DEMAND_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DEMAND_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateDemand(Demand demand) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + "demand SET idClient = ? , idService =? , status=? , date =? WHERE id = ? ");
            stm.setInt(1, demand.getIdClient());
            stm.setInt(2, demand.getIdService());
            stm.setString(3, demand.isStatus());
            stm.setDate(4, (Date) demand.getDate());
            stm.setInt(5, demand.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DEMAND_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DEMAND_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteDemand(Demand demand) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + "demand WHERE id = ?");
            stm.setInt(1, demand.getId());
            stm.executeUpdate();
            alterIdDemand(demand.getId());
            stm.close();
            return Results.Rstls.DEMAND_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DEMAND_NOT_DELETED;
        }
    }

    public static Object getDemands(Demand demand) {
        String query;
        if (demand.getIdClient() == 0) {
            query = "SELECT * FROM demand where status !='payment'";
        } else {
            query = "SELECT * FROM demand where idClient = '" + demand.getIdClient() + "' and status !='payment'";
        }
        ObservableList<Demand> listDemand = FXCollections.observableArrayList(new Demand());
        listDemand.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Demand dmnd = new Demand();
                dmnd.setId(rs.getInt("id"));
                dmnd.setClientName(getClientNameFromId(rs.getInt("idClient")));
                dmnd.setServiceName(getServiceNameFromId(rs.getInt("idService")));
                dmnd.setStatus(rs.getString("status"));
                dmnd.setDate(rs.getDate("date"));
                listDemand.add(dmnd);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listDemand;
    }

    public static String getClientNameFromId(int id) {
        String query = "SELECT name FROM client where id = " + id;
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

    public static String getServiceNameFromId(int id) {
        String query = "SELECT name FROM service where id = " + id;
        String serviceName = "";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                serviceName = rs.getString("name");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return serviceName;
    }
    
   
}
