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
public class Demand {

    private int id;
    private int idClient;
    private int idService;
    private boolean status;
    private boolean date;

    public Demand(int id, int idClient, int idService, boolean status, boolean date) {
        this.id = id;
        this.idClient = idClient;
        this.idService = idService;
        this.status = status;
        this.date = date;
    }

    public Demand(int idClient, int idService, boolean status, boolean date) {
        this.idClient = idClient;
        this.idService = idService;
        this.status = status;
        this.date = date;
    }

    public Demand(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDate() {
        return date;
    }

    public void setDate(boolean date) {
        this.date = date;
    }

    
    

}
