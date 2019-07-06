import javax.swing.*;
import java.awt.*;

public class PanelEliminar extends JPanel {

    private static JTable tabla;
    private PanelFormularioEliminar panelFormularioEliminar;

    public PanelEliminar() {
        setLayout(new BorderLayout());

        tabla = new JTable();
        add(new JScrollPane(tabla),BorderLayout.CENTER);

        panelFormularioEliminar = new PanelFormularioEliminar();
        add(panelFormularioEliminar, BorderLayout.WEST);



    }

    public static JTable getTabla() {
        return tabla;
    }
}
