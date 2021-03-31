/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonelgaze;

import com.mysql.jdbc.Connection;
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
import model.*;
import sonelgaze.BackEnd.ClientController;
import sonelgaze.BackEnd.DocumentController;
import sonelgaze.BackEnd.HasController;
import sonelgaze.BackEnd.ServiceController;

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
        Parent root = FXMLLoader.load(getClass().getResource("/sonelgaze/FrontEnd/Root.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
        SONELGAZE SNG = new SONELGAZE();

//        System.err.println(ClientController.addClient(new Client("client3", "0658185867")));
//        ObservableList<Client> listClient=(ObservableList<Client>) ClientController.getClients(new Client());
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
//        ObservableList<Has> listHas = (ObservableList<Has>) HasController.getHas(new Has(3, ""));
//        System.err.println(listHas.size());
//        for (Has has : listHas){
//            System.out.println(has.getId()  +"|"+  has.getDocumentName()  +"|"+has.getServiceName());
//        }
//        System.err.println(HasController.deleteHas(new Has(10)));
//        System.err.println(HasController.getServiceNameFromId(3));
//        System.err.println(HasController.getDocumentNameFromId(1));

    }

}
