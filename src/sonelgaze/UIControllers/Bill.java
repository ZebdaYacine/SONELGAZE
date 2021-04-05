/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.UIControllers;

import model.Demand;
import model.Do;
import model.Entreprenor;
import model.Project;

/**
 *
 * @author Zed-Yacine
 */
public class Bill {

    public static void demandBill(Demand d,String phone) {
        String header
                = ";*********************************** bon de réception ***********************************\n;"
                + ";reference de demand : "+d.getId()+"\n;"
                + ";nom de client : "+d.getClientName()+"\n;"
                + ";numiro de telephone : "+phone+"\n;"
                + ";Service demender :"+d.getServiceName()+"\n;"
                + ";etas de demnad : ;"+d.isStatus()+"\n;"
                + ";Date de demand : ;"+d.getDate()+"\n;"
                + ";*********************************** Receipt ***********************************\n;";
        PrintAgent.printCard(header);
    }
    
     public static void doingProjectBill(Project p,Do d,Entreprenor e,int nbrDemend) {
        String header
                = ";*********************************** Receipt ***********************************\n;"
                + ";code de projet : "+p.getId()+"\n;"
                + ";nom de projet : "+p.getName()+"\n;"
                + ";nom de entreproneur : "+e.getName()+"\n;"
                + ";numiro de telephone de entreproneur: "+e.getPhone()+"\n;"
                + ";nombre de demendes :"+nbrDemend+"\n;"
                + ";etas de projet : ;"+d.getStatus()+"\n;"
                + ";*********************************** Receipt ***********************************\n;";
        PrintAgent.printCard(header);
    }

}
