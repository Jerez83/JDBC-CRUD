import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class PanelFormularioEliminar extends JPanel {


    private JLabel alias;
    private JLabel sexo;
    private JLabel peso;
    private JTextField campoAlias;
    private JTextField campoPeso;
    private JRadioButton masculinoRadio;
    private JRadioButton femeninoRadio;
    private ButtonGroup generoGroup;
    private JButton eliminar;
    private JLabel textoInfo;




    public PanelFormularioEliminar() {
        Dimension d = getPreferredSize();
        d.width = 300;
        setPreferredSize(d);

        Border interior = BorderFactory.createTitledBorder("Eliminar Animales");
        Border exterior = BorderFactory.createEmptyBorder(15,15,15,15);
        //setBackground(Color.darkGray);
        setBorder(BorderFactory.createCompoundBorder(exterior, interior));

        setBackground(new Color(232,232,232));
        setLayout(new GridBagLayout());

        alias = new JLabel("Alias: ");
        sexo = new JLabel("Sexo: ");
        peso = new JLabel("Peso: ");
        masculinoRadio = new JRadioButton("V");
        masculinoRadio.setBackground(new Color(232,232,232));
        masculinoRadio.setActionCommand("V");
        femeninoRadio = new JRadioButton("F");
        femeninoRadio.setBackground(new Color(232,232,232));
        femeninoRadio.setActionCommand("F");
        generoGroup = new ButtonGroup();
        eliminar = new JButton("Eliminar");
        eliminar.setEnabled(false);
        textoInfo = new JLabel("Seleccione un registro de la tabla para eliminarlo");
        textoInfo.setForeground(Color.RED);
        textoInfo.setFont(new Font("Verdana",0,9));

        masculinoRadio.setSelected(true);
        masculinoRadio.setEnabled(false);
        femeninoRadio.setEnabled(false);

        generoGroup.add(masculinoRadio);
        generoGroup.add(femeninoRadio);

        campoAlias = new JTextField(20);
        campoAlias.setEnabled(false);
        campoPeso = new JTextField(20);
        campoPeso.setEnabled(false);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;                                       // El logo empieza en la columna cero.
        constraints.gridy = 0;                                       // El logo empieza en la fila cero
        constraints.gridwidth = 1;                                   // El logo ocupa 1 columnas.
        constraints.gridheight = 1;                                  // El logo ocupa 1 filas.
        //constraints.weightx = 0.5;                                 // La fila 0 debe estirarse, le ponemos un 1.0
        constraints.insets = new Insets(10,0,0,0);    //  top, left, bottom, right
        // constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(alias,constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        //constraints.weighty = 0.5;
        constraints.insets = new Insets(10,0,0,0);    //  top, left, bottom, right
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        add(campoAlias,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        //constraints.weighty = 0.5;
        constraints.insets = new Insets(10,0,0,0);    //  top, left, bottom, right
        add(sexo,constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        //constraints.anchor = GridBagConstraints.LINE_START;
        add(masculinoRadio,constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(0,0,0,0);    //  top, left, bottom, right
        add(femeninoRadio,constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(10,0,0,0);    //  top, left, bottom, right
        add(peso,constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        add(campoPeso,constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.insets = new Insets(20,0,0,5);    //  top, left, bottom, right
        add(eliminar,constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20,0,0,5);    //  top, left, bottom, right
        add(textoInfo,constraints);



        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                eliminarDatos();
                mostrarDatos();
            }
        });





        PanelEliminar.getTabla().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                JTable tabla = PanelEliminar.getTabla();
                int filaSeleccionada = tabla.getSelectedRow();

                campoAlias.setText(tabla.getValueAt(filaSeleccionada,1).toString());

                String sexo = tabla.getValueAt(filaSeleccionada,2).toString();
                if (sexo.equals("V")) masculinoRadio.setSelected(true);
                else femeninoRadio.setSelected(true);

                campoPeso.setText(tabla.getValueAt(filaSeleccionada,3).toString());
                eliminar.setEnabled(true);
                textoInfo.setVisible(false);
            }
        });


        mostrarDatos();


    }

    public void eliminarDatos() {

        Connection con = Conexion.conectar();
        String sql = "DELETE FROM animales WHERE id=? ";
        try {
            JTable tabla = PanelEliminar.getTabla();
            int filaSeleccionada = tabla.getSelectedRow();
            String id = (String) tabla.getValueAt(filaSeleccionada,0);

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id));

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"El registro se elimino correctamente");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error al modificar la base de datos: "+e.getMessage());

        }



        mostrarDatos();

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

            PanelEliminar.getTabla().setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al actualizar los datos"+e.getMessage());
        }
    }
}
