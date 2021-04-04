/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze.UIControllers;

import model.Demand;

/**
 *
 * @author Zed-Yacine
 */
public class Bill {

    public static void demandBill(Demand d,String phone) {
        String header
                = ";*********************************** Receipt ***********************************\n;"
                + ";nom de client : "+d.getClientName()+"\n;"
                + ";numiro de telephone : "+phone+"\n;"
                + ";Service demender :"+d.getServiceName()+"\n;"
                + ";etas de demnad : ;"+d.isStatus()+"\n;"
                + ";Date de demand : ;"+d.getDate()+"\n;"
                + ";*********************************** Receipt ***********************************\n;";
        PrintAgent.printCard(header);
    }

}
