/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Zed-Yacine
 */
public class Do {
    private int id;
    private int idEtrepo;
    private int idProject;
    private String etrepoName;
    private String projectName;
    private String status;

    public Do() {
    }

    public Do(int id) {
        this.id = id;
    }

    public Do(int idProject, String args) {
        this.idProject = idProject;
    }

    public Do(int idEtrepo, int idProject, String status) {
        this.idEtrepo = idEtrepo;
        this.idProject = idProject;
        this.status = status;
    }

    public Do(int id, int idEtrepo, int idProject, String status) {
        this.id = id;
        this.idEtrepo = idEtrepo;
        this.idProject = idProject;
        this.status = status;
    }
    
    public Do(int id, String etrepoName, String projectName, String status) {
        this.id = id;
        this.etrepoName = etrepoName;
        this.projectName = projectName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEtrepo() {
        return idEtrepo;
    }

    public void setIdEtrepo(int idEtrepo) {
        this.idEtrepo = idEtrepo;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getEtrepoName() {
        return etrepoName;
    }

    public void setEtrepoName(String etrepoName) {
        this.etrepoName = etrepoName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
 
}
