import javax.swing.*;
import java.awt.*;

public class PanelPestenas extends JPanel {
    private JTabbedPane tabbedPane;
    private PanelInsertar panelInsertar;
    private PanelActualizar panelActualizar;
    private PanelConsultar panelConsultar;
    private PanelEliminar panelEliminar;


    public PanelPestenas() {
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        panelInsertar = new PanelInsertar();
        panelConsultar = new PanelConsultar();
        panelEliminar = new PanelEliminar();
        panelActualizar = new PanelActualizar();

        tabbedPane.setSize(800,600);
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        tabbedPane.addTab("Insertar", panelInsertar);
        tabbedPane.addTab("Consultar", panelConsultar);
        tabbedPane.addTab("Modificar", panelActualizar);
        tabbedPane.addTab("Eliminar", panelEliminar);

        add(tabbedPane,BorderLayout.CENTER);
    }
}
