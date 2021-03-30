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
import model.Document;
import static sonelgaze.SONELGAZE.con;

/**
 *
 * @author Zed-Yacine
 */
public class DocumentController {
    
    private static void alterIdClient(int id) throws SQLException {
        String sql = "ALTER TABLE `document` AUTO_INCREMENT =" + id + "";
        PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
        stm.execute();
        stm.close();
    }

    public static Results.Rstls addDocument(Document docs) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into document (name) values (?)");
            stm.setString(1, docs.getName());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DOCUMENT_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DOCUMENT_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateDocument(Document docs) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + "document SET name = ? WHERE id = ? ");
            stm.setString(1, docs.getName());
            stm.setInt(2, docs.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DOCUMENT_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DOCUMENT_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteDocuments(Document docs) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + "document WHERE id = ?");
            stm.setInt(1, docs.getId());
            stm.executeUpdate();
            alterIdClient(docs.getId());
            stm.close();
            return Results.Rstls.DOCUMENT_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DOCUMENT_NOT_DELETED;
        }
    }

    public static Object getDocuments(Document docs) {
        String query ;
        if (docs.getId() == 0) {
            query = "SELECT * FROM document";
        } else {
            query = "SELECT * FROM document where id = "+docs.getId();
        }
        ObservableList<Document> listDocs = FXCollections.observableArrayList(new Document());
        listDocs.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Document doc = new Document();
                doc.setId(rs.getInt("id"));
                doc.setName(rs.getString("name"));
                listDocs.add(doc);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listDocs;
    }
}
