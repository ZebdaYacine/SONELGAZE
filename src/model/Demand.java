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
public class Demand {

    private int id;
    private int idClient;
    private int idService;
    private String status;
    private Date date;
    private String clientName;
    private String serviceName;

    public Demand(int id, int idClient, int idService, String status, Date date) {
        this.id = id;
        this.idClient = idClient;
        this.idService = idService;
        this.status = status;
        this.date = date;
    }

    public Demand(int id,String clientName, String serviceName, String status, Date date ) {
        this.id = id;
        this.status = status;
        this.date = date;
        this.clientName = clientName;
        this.serviceName = serviceName;
    }
    
    
    public Demand(int idClient, int idService, String status, Date date) {
        this.idClient = idClient;
        this.idService = idService;
        this.status = status;
        this.date = date;
    }

    public Demand(int id) {
        this.id = id;
    }
    
    public Demand(int idClient,String arg) {
        this.idClient = idClient;
    }

    public Demand() {
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

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    

    
    

}
