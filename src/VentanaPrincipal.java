import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class VentanaPrincipal extends JFrame {

    private PanelPestenas  panelPestenas;

    public VentanaPrincipal() throws HeadlessException {

        setTitle("Gestión de anumales");
        setSize(800,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        /*try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }*/

        panelPestenas= new PanelPestenas();
        panelPestenas.setBackground(Color.LIGHT_GRAY);
        add(panelPestenas,BorderLayout.CENTER);

        // ConexionDataSource.conectar();

    }
}
