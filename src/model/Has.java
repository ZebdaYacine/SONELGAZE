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
public class Has {

    private int id;
    private int idDocument;
    private int idService;
    private String serviceName;
    private String documentName;

    public Has(int idDocument, int idService) {
        this.idDocument = idDocument;
        this.idService = idService;
        this.serviceName = serviceName;
        this.documentName = documentName;
    }

    public Has(int id, int idDocument, int idService) {
        this.id = id;
        this.idDocument = idDocument;
        this.idService = idService;
        this.serviceName = serviceName;
        this.documentName = documentName;
    }

    public Has(int id, String serviceName, String documentName) {
        this.id = id;
        this.serviceName = serviceName;
        this.documentName = documentName;
    }

    public Has(int idService, String serviceName) {
        this.idService = idService;
    }
    
    
    public Has(int id) {
        this.id = id;
    }

    public Has() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int idDocument) {
        this.idDocument = idDocument;
    }

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
    
    

}
