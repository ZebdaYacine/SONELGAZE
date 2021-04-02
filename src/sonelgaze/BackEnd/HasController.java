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
import model.Document;
import model.Has;
import model.Service;
import static sonelgaze.SONELGAZE.con;

/**
 *
 * @author Zed-Yacine
 */
public class HasController {

    private static void alterIdHas(int id) throws SQLException {
        String sql = "ALTER TABLE `has` AUTO_INCREMENT =" + id + "";
        PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
        stm.execute();
        stm.close();
    }

    public static Results.Rstls addHas(Has has) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into has (idDocument,idService) values (?,?)");
            stm.setInt(1, has.getIdDocument());
            stm.setInt(2, has.getIdService());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.HAS_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.HAS_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateHas(Has has) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + "has SET idDocument = ? , idService = ? WHERE id = ? ");
            stm.setInt(1, has.getIdDocument());
            stm.setInt(2, has.getIdService());
            stm.setInt(3, has.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.SERVICE_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.SERVICE_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteHas(Has has) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + "has WHERE id = ?");
            stm.setInt(1, has.getId());
            stm.executeUpdate();
            alterIdHas(has.getId());
            stm.close();
            return Results.Rstls.SERVICE_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.SERVICE_NOT_DELETED;
        }
    }

    public static Object getHas(Has has) {
        String query;
        if (has.getIdService() != 0) {
            query = "SELECT * FROM has where idService = " + has.getIdService();
        } else {
            query = "SELECT * FROM has ";
        }
        ObservableList<Has> listHas = FXCollections.observableArrayList(new Has());
        listHas.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Has h = new Has();
                h.setId(rs.getInt("id"));
                h.setDocumentName(getDocumentNameFromId(rs.getInt("idDocument")));
                h.setServiceName(getServiceNameFromId(rs.getInt("idService")));
                listHas.add(h);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listHas;
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

    public static String getDocumentNameFromId(int id) {
        String query = "SELECT name FROM document where id = " + id;
        String docsName = "";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                docsName = rs.getString("name");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return docsName;
    }

    public static Object getAllServicesNmae() {
        String query;
        query = "SELECT name FROM service ";
        ObservableList<Service> listservices = FXCollections.observableArrayList(new Service());
        listservices.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Service services = new Service();
                services.setName(rs.getString("name"));
                listservices.add(services);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listservices;
    }

    public static Object getAllDocsNmae() {
        String query;
        query = "SELECT name FROM document ";
        ObservableList<Document> listDocs = FXCollections.observableArrayList(new Document());
        listDocs.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Document docs = new Document();
                docs.setName(rs.getString("name"));
                listDocs.add(docs);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listDocs;
    }

}
