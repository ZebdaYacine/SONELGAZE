/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Zed-Yacine
 */
public class Project {

    private int id;
    private int idDemand;
    private String name;

    public Project(int id, int idDemand, String name) {
        this.id = id;
        this.idDemand = idDemand;
        this.name = name;
    }

    public Project(int idDemand, String name) {
        this.idDemand = idDemand;
        this.name = name;
    }

    public Project(int id) {
        this.id = id;
    }
    
    

    public Project() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDemand() {
        return idDemand;
    }

    public void setIdDemand(int idDemand) {
        this.idDemand = idDemand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
