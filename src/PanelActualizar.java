import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelActualizar extends JPanel  {

    private static JTable tabla;
    private PanelFormularioModificar panelFormularioModificar;

    public PanelActualizar() {
        setLayout(new BorderLayout());
        tabla = new JTable();
        panelFormularioModificar = new PanelFormularioModificar();
        add(panelFormularioModificar,BorderLayout.WEST);
        add(new JScrollPane(tabla),BorderLayout.CENTER);
    }

    public static JTable getTabla() {
        return tabla;
    }

}
