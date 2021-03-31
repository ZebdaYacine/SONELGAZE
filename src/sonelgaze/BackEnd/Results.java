/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.BackEnd;

/**
 *
 * @author Zed-Yacine
 */
public class Results {

    enum Rstls {
        CLIENT_INSERTED,
        CLIENT_NOT_INSERTED,
        CLIENT_UPDATED,
        CLIENT_NOT_UPDATED,
        CLIENT_DELETED,
        CLIENT_NOT_DELETED,
        
        
        DOCUMENT_INSERTED,
        DOCUMENT_NOT_INSERTED,
        DOCUMENT_UPDATED,
        DOCUMENT_NOT_UPDATED,
        DOCUMENT_DELETED,
        DOCUMENT_NOT_DELETED,
        
        
        SERVICE_INSERTED,
        SERVICE_NOT_INSERTED,
        SERVICE_UPDATED,
        SERVICE_NOT_UPDATED,
        SERVICE_DELETED,
        SERVICE_NOT_DELETED,
        
        
        HAS_INSERTED,
        HAS_NOT_INSERTED,
        HAS_UPDATED,
        HAS_NOT_UPDATED,
        HAS_DELETED,
        HAS_NOT_DELETED,
        
        DEMAND_INSERTED,
        DEMAND_NOT_INSERTED,
        DEMAND_UPDATED,
        DEMAND_NOT_UPDATED,
        DEMAND_DELETED,
        DEMAND_NOT_DELETED
    }

}
