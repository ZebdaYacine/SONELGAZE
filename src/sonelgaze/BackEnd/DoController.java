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
import model.Do;
import static sonelgaze.SONELGAZE.con;

/**
 *
 * @author Zed-Yacine
 */
public class DoController {

    private static void alterIdDo(int id) throws SQLException {
        String sql = "ALTER TABLE `do` AUTO_INCREMENT =" + id + "";
        PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
        stm.execute();
        stm.close();
    }

    public static Results.Rstls addDo(Do d) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into do (idProject,idEntreprenor,status) values (?,?,?)");
            stm.setInt(1, d.getIdProject());
            stm.setInt(2, d.getIdEtrepo());
            stm.setString(3, d.getStatus());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DO_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DO_NOT_INSERTED;
        }
    }

    public static Results.Rstls updateDO(Do d) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + "do SET idProject = ? , idEntreprenor =? , status=?  WHERE id = ? ");
            stm.setInt(1, d.getId());
            stm.setInt(2, d.getIdEtrepo());
            stm.setString(3, d.getStatus());
            stm.setInt(4, d.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.DO_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DO_NOT_UPDATED;
        }
    }

    public static Results.Rstls deleteDO(Do d) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + "do WHERE id = ?");
            stm.setInt(1, d.getId());
            stm.executeUpdate();
            alterIdDo(d.getId());
            stm.close();
            return Results.Rstls.DO_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DO_NOT_DELETED;
        }
    }

    public static Object getDO(Do d) {
        String query;
        if (d.getIdProject() == 0) {
            query = "SELECT * FROM do";
        } else {
            query = "SELECT * FROM do where idProject = '" + d.getIdProject() + "'";
        }
        ObservableList<Do> listDo = FXCollections.observableArrayList(new Do());
        listDo.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Do obj = new Do();
                obj.setId(rs.getInt("id"));
                obj.setProjectName(getProjectNameFromId(rs.getInt("idProject")));
                obj.setEtrepoName(getEntreproNameFromId(rs.getInt("idEntreprenor")));
                obj.setStatus(rs.getString("status"));
                listDo.add(obj);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listDo;
    }

    public static String getProjectNameFromId(int id) {
        String query = "SELECT name FROM project where id = " + id;
        String projectName = "";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                projectName = rs.getString("name");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return projectName;
    }

    public static String getEntreproNameFromId(int id) {
        String query = "SELECT name FROM entreprenor where id = " + id;
        String entreproName = "";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                entreproName = rs.getString("name");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return entreproName;
    }

}
