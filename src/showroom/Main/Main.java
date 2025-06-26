package showroom.Main;

import com.formdev.flatlaf.FlatDarculaLaf;
import showroom.view.LoginView;
import showroom.util.DatabaseConnection; // Import the DatabaseConnection

public class Main {

    public static void main(String[] args) {

 
        DatabaseConnection.initializeDatabase();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }
}
