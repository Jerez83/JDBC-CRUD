import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PanelConsultar extends JPanel {
    private static JTable tabla;
    private Connection con = Conexion.conectar();
    private PanelFormularioConsultar panelFormularioConsultar;

    public PanelConsultar() {
        setLayout(new BorderLayout());
        tabla = new JTable();
        panelFormularioConsultar = new PanelFormularioConsultar();

        add(panelFormularioConsultar,BorderLayout.WEST);
        add(new JScrollPane(tabla),BorderLayout.CENTER);
        mostrarDatos();
    }

    public void mostrarDatos() {
        String[] titulos = {"ID","ALIAS","SEXO","PESO"};
        String[] elementos = new String[4];
        String sql = "SELECT * FROM animales";
        DefaultTableModel model = new DefaultTableModel(null, titulos);

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())  {

                elementos[0] = rs.getString("id");
                elementos[1] = rs.getString("alias");
                elementos[2] = rs.getString("sexo");
                elementos[3] = rs.getString("peso");

                model.addRow(elementos);
            }

            tabla.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al actualizar los datos"+e.getMessage());
        }
    }



    public static JTable getTabla() {
        return tabla;
    }
}
