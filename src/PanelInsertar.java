import javax.swing.*;
import java.awt.*;

public class PanelInsertar extends JPanel {
    private static JTable tabla;
    private PanelFormularioInsertar panelFormularioInsertar;

    public PanelInsertar() {
        setLayout(new BorderLayout());


        tabla = new JTable();
        tabla.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panelFormularioInsertar = new PanelFormularioInsertar();
        add(new JScrollPane(tabla),BorderLayout.CENTER);
        add(panelFormularioInsertar,BorderLayout.WEST);
    }

    public static JTable getTabla() {
        return tabla;
    }
}
