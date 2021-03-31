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
public class Service {
    private int id;
    private String name;

    public Service(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Service(String name) {
        this.name = name;
    }

    public Service(int id) {
        this.id = id;
    }
    
    public Service() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
