import java.io.Console;
import java.sql.*;


import javax.swing.*;

public class Principal {



    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal();
            }
        });

    }


}
