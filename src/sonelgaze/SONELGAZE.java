/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze;

import com.mysql.jdbc.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Client;
import model.Demand;
import model.Entreprenor;
import model.Has;
import sonelgaze.BackEnd.ClientController;
import sonelgaze.BackEnd.DemandController;
import sonelgaze.BackEnd.EntreprenorController;
import sonelgaze.BackEnd.HasController;

/**
 *
 * @author Zed-Yacine
 */
public class SONELGAZE extends Application {

    public static Connection con;
    public CountDownLatch count = new CountDownLatch(1);
    public static Scene scene;
    public static AnchorPane root;

    public SONELGAZE() {
        try {
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String url = "jdbc:mysql://localhost:3306/SONELGAZE";
                        String user = "root";
                        String Password = "0658185867";
                        Class.forName("com.mysql.jdbc.Driver");
                        con = (Connection) DriverManager.getConnection(url, user, Password);
                        System.out.println("Connection successfly");
                        count.countDown();
                    } catch (Exception ex) {
                        ex.getStackTrace();
                        count.countDown();
                    }
                }
            });
            th.start();
            count.await();
            th.stop();
        } catch (InterruptedException ex) {
            Logger.getLogger(SONELGAZE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/sonelgaze/FrontEnd/RootUI.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
//        SONELGAZE SNG = new SONELGAZE();

//        System.err.println(ClientController.addClient(new Client("client5", "0658185867"),"client"));
//        ObservableList<Client> listClient = (ObservableList<Client>) ClientController.getClients(new Client(),"client");
//        System.err.println(listClient.size());
//        System.err.println(DocumentController.addDocument(new Document("docs1")));
//        System.err.println(DocumentController.updateDocument(new Document(1,"docs2")));
//        ObservableList<Document> ListDocs = (ObservableList<Document>) DocumentController.getDocuments(new Document("docs1"));
//        System.err.println(ListDocs.size());
//        System.err.println(DocumentController.deleteDocuments(new Document(1)));
//        System.err.println(ServiceController.addService(new Service("service03")));
//        System.err.println(ServiceController.updateService(new Service(1,"service01")));
//        ObservableList<Service> ListServices = (ObservableList<Service>) ServiceController.getService(new Service("service02"));
//        System.err.println(ListServices.size());
//        System.err.println(ServiceController.deleteServices(new Service(3)));
//        System.err.println(HasController.addHas(new Has(1,3)));
//        System.err.println(HasController.updateHas(new Has(9,1, 2)));
//        ObservableList<Has> listHas = (ObservableList<Has>) HasController.getHas(new Has());
//        System.err.println(listHas.size());
//        for (Has has : listHas){
//            System.out.println(has.getId()  +"|"+  has.getDocumentName()  +"|"+has.getServiceName());
//        }
//        System.err.println(HasController.deleteHas(new Has(10)));
//        System.err.println(HasController.getServiceNameFromId(3));
//        System.err.println(HasController.getDocumentNameFromId(1));
//        System.err.println(DemandController.addDemand(new Demand(2, 2,"not yet", Date.valueOf("2015-03-31"))));
//        System.err.println(DemandController.updateDemand(new Demand(8,1, 3, "not yet", Date.valueOf("2016-03-31"))));
//        System.err.println(DemandController.deleteDemand(new Demand(5)));
//          ObservableList<Demand> listDemand = (ObservableList<Demand>) DemandController.getDemands(new Demand(1,""));
//          System.err.println(listDemand.size());
//          for (Demand demand : listDemand){
//              System.out.println(demand.getId()  +"|"+  demand.getClientName()+"|"+demand.getServiceName()
//              +" | "+demand.isStatus()+" | "+demand.getDate());
//          }
//        System.err.println(ProjectController.addProject(new Project(7,"prjct2")));
//        System.err.println(ProjectController.updateProject(new Project(1, 7, "projct1")));
//        System.err.println(ProjectController.deleteProject(new Project(1)));
//        ObservableList<Project> listProject = (ObservableList<Project>) ProjectController.getProject(new Project(6,""));
//        System.err.println(listProject.size());
//        for (Project prj : listProject){
//             System.out.println(prj.getId()+" |  "+prj.getName()+" | "+prj.getIdDemand());
//         }


//        System.err.println(EntreprenorController.addClient(new Entreprenor("entrper4", "0658185867"),"entreprenor"));
//        System.err.println(EntreprenorController.deleteClient(new Entreprenor(2),"entreprenor"));
//        ObservableList<Client> listClient = (ObservableList<Client>) ClientController.getClients(new Client("entrper3"),"entreprenor");
//        System.err.println(listClient.size());


//        System.err.println(DoController.addDo(new Do(2, 2,"not yet")));
//        System.err.println(DoController.updateDO(new Do(3,2,1,"yes")));
//          System.err.println(DoController.deleteDO(new Do(4)));
//          ObservableList<Do> listDo = (ObservableList<Do>) DoController.getDO(new Do());
//          System.err.println(listDo.size());
//          for (Do d : listDo){
//              System.out.println(d.getId()  +"|"+  d.getEtrepoName()+"|"+d.getProjectName()+" | "+d.getStatus());
//          }
//        System.err.println(ClientController.getClientIdFromName("client4","client"));
//        System.err.println(ProjectController.getProjectIdFromName("prjct2"));
//        System.err.println(EntreprenorController.getClientIdFromName("entrper3","entreprenor"));
//          System.err.println(ServiceController.getServiceIdFromName("service02"));

    }

}
