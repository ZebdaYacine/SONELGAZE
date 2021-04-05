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
import model.Project;
import model.Service;
import static sonelgaze.BackEnd.DemandController.getClientNameFromId;
import static sonelgaze.BackEnd.DemandController.getServiceNameFromId;
import static sonelgaze.SONELGAZE.con;

/**
 *
 * @author Zed-Yacine
 */
public class ProjectController {
    
    private static void alterIdProject(int id) throws SQLException {
        String sql = "ALTER TABLE `project` AUTO_INCREMENT =" + id + "";
        PreparedStatement stm = (PreparedStatement) con.prepareStatement(sql);
        stm.execute();
        stm.close();
    }
    
    public static Results.Rstls addProject(Project prjt) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement(""
                    + "insert into project (idDemand,name) values (?,?)");
            stm.setInt(1, prjt.getIdDemand());
            stm.setString(2, prjt.getName());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.PROJECT_INSERTED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.PROJECT_NOT_INSERTED;
        }
    }
    
    public static Results.Rstls updateProject(Project prjt) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("UPDATE "
                    + "project SET idDemand = ? , name =? WHERE id = ? ");
            stm.setInt(1, prjt.getIdDemand());
            stm.setString(2, prjt.getName());
            stm.setInt(3, prjt.getId());
            stm.executeUpdate();
            stm.close();
            return Results.Rstls.PROJECT_UPDATED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.PROJECT_NOT_UPDATED;
        }
    }
    
    public static Results.Rstls deleteProject(Project prjt) {
        try {
            PreparedStatement stm = (PreparedStatement) con.prepareStatement("DELETE FROM "
                    + "project WHERE id = ?");
            stm.setInt(1, prjt.getId());
            stm.executeUpdate();
            alterIdProject(prjt.getId());
            stm.close();
            return Results.Rstls.DEMAND_DELETED;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Results.Rstls.DEMAND_NOT_DELETED;
        }
    }
    
    
    public static Object getProject(Project prjt) {
        String query;
        if (prjt.getIdDemand() == 0) {
            query = "SELECT * FROM project";
        } else {
            query = "SELECT * FROM project where idDemand = " + prjt.getIdDemand();
        }
        ObservableList<Project> listProject = FXCollections.observableArrayList(new Project());
        listProject.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Project p = new Project();
                p.setId(rs.getInt("id"));
                p.setIdDemand(rs.getInt("idDemand"));
                p.setName(rs.getString("name"));
                listProject.add(p);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listProject;
    }
    
    public static int getProjectIdFromName(String name) {
        String query = "SELECT id FROM project where name = '" + name+"'";
        int projectId = 0;
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                projectId = rs.getInt("id");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return projectId;
    }
    
     public static Object getAllProjectsName() {
        String query;
        query = "SELECT name FROM project ";
        ObservableList<Project> listProject = FXCollections.observableArrayList(new Project());
        listProject.remove(0);
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Project projects = new Project();
                projects.setName(rs.getString("name"));
                listProject.add(projects);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return listProject;
    }
    
     public static String getProjectNameFromId(int id) {
        String query = "SELECT name FROM project where id = "+id;
        String project = "";
        try {
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                project = rs.getString("name");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return project;
    }
    
}
