import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PanelFormularioConsultar extends JPanel {

    private JButton recargar;
    private JButton ordenar;

    public PanelFormularioConsultar() {

        Dimension d = getPreferredSize();
        d.width = 300;
        setPreferredSize(d);

        Border interior = BorderFactory.createTitledBorder("Consultas");
        Border exterior = BorderFactory.createEmptyBorder(15,15,15,15);
        setBorder(BorderFactory.createCompoundBorder(exterior, interior));

        setBackground(new Color(232,232,232));
        recargar = new JButton("Recargar datos");
        ordenar = new JButton("Ordenar por id");

        add(recargar);
        add(ordenar);

        recargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mostrarDatos();
            }
        });

        ordenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ordenaDatos();
            }
        });
    }

    public void mostrarDatos() {
        Connection con = Conexion.conectar();
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

            PanelConsultar.getTabla().setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al actualizar los datos"+e.getMessage());
        }
    }

    public void ordenaDatos() {
        Connection con = Conexion.conectar();
        String[] titulos = {"ID","ALIAS","SEXO","PESO"};
        String[] elementos = new String[4];
        String sql = "SELECT * FROM animales ORDER BY id";
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

            PanelConsultar.getTabla().setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al actualizar los datos"+e.getMessage());
        }
    }
}
