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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
                        System.out.println(con);
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
        launch(args);
    }
    
}
