import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PanelFormularioModificar extends JPanel  {

    private JLabel alias;
    private JLabel sexo;
    private JLabel peso;
    private JTextField campoAlias;
    private JTextField campoPeso;
    private JRadioButton masculinoRadio;
    private JRadioButton femeninoRadio;
    private ButtonGroup generoGroup;
    private JButton actualizar;
    private JLabel textoInfo;




    public PanelFormularioModificar() {
        Dimension d = getPreferredSize();
        d.width = 300;
        setPreferredSize(d);

        Border interior = BorderFactory.createTitledBorder("Modificar Animales");
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
        actualizar = new JButton("Actualizar datos");
        actualizar.setEnabled(false);
        textoInfo = new JLabel("Seleccione un registro de la tabla para actualizarlo");
        textoInfo.setForeground(Color.RED);
        textoInfo.setFont(new Font("Verdana",0,9));

        masculinoRadio.setSelected(true);

        generoGroup.add(masculinoRadio);
        generoGroup.add(femeninoRadio);


        campoAlias = new JTextField(20);

        campoPeso = new JTextField(20);


        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0; // El logo empieza en la columna cero.
        constraints.gridy = 0; // El logo empieza en la fila cero
        constraints.gridwidth = 1; // El logo ocupa 6 columnas.
        constraints.gridheight = 1; // El logo ocupa 1 filas.
        //constraints.weightx = 0.5;                  // La fila 0 debe estirarse, le ponemos un 1.0
        constraints.insets = new Insets(10,0,0,0);    //  top, left, bottom, right
        // constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(alias,constraints);

        constraints.gridx = 1; // El logo empieza en la columna cero.
        constraints.gridy = 0; // El logo empieza en la fila cero
        constraints.gridwidth = 1; // El logo ocupa 6 columnas.
        constraints.gridheight = 1; // El logo ocupa 1 filas.
        //constraints.weighty = 0.5;                  // La fila 0 debe estirarse, le ponemos un 1.0
        constraints.insets = new Insets(10,0,0,0);    //  top, left, bottom, right
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        add(campoAlias,constraints);

        constraints.gridx = 0; // El logo empieza en la columna cero.
        constraints.gridy = 1; // El logo empieza en la fila cero
        constraints.gridwidth = 1; // El logo ocupa 6 columnas.
        constraints.gridheight = 1; // El logo ocupa 1 filas.
        //constraints.weighty = 0.5;                  // La fila 0 debe estirarse, le ponemos un 1.0
        constraints.insets = new Insets(10,0,0,0);    //  top, left, bottom, right
        add(sexo,constraints);

        constraints.gridx = 1; // El logo empieza en la columna cero.
        constraints.gridy = 1; // El logo empieza en la fila cero
        constraints.gridwidth = 1; // El logo ocupa 6 columnas.
        constraints.gridheight = 1; // El logo ocupa 1 filas.
        //constraints.anchor = GridBagConstraints.LINE_START;
        add(masculinoRadio,constraints);

        constraints.gridx = 1; // El logo empieza en la columna cero.
        constraints.gridy = 2; // El logo empieza en la fila cero
        constraints.gridwidth = 1; // El logo ocupa 6 columnas.
        constraints.gridheight = 1; // El logo ocupa 1 filas.
        constraints.insets = new Insets(0,0,0,0);    //  top, left, bottom, right
        add(femeninoRadio,constraints);

        constraints.gridx = 0; // El logo empieza en la columna cero.
        constraints.gridy = 3; // El logo empieza en la fila cero
        constraints.gridwidth = 1; // El logo ocupa 6 columnas.
        constraints.gridheight = 1; // El logo ocupa 1 filas.
        constraints.insets = new Insets(10,0,0,0);    //  top, left, bottom, right
        add(peso,constraints);

        constraints.gridx = 1; // El logo empieza en la columna cero.
        constraints.gridy = 3; // El logo empieza en la fila cero
        constraints.gridwidth = 1; // El logo ocupa 6 columnas.
        constraints.gridheight = 1; // El logo ocupa 1 filas.
        add(campoPeso,constraints);

        constraints.gridx = 0; // empieza en la columna .
        constraints.gridy = 4; // empieza en la fila
        constraints.gridwidth = 2; // ocupa columnas.
        constraints.gridheight = 1; // El logo ocupa 1 filas.
        constraints.anchor = GridBagConstraints.LINE_END;
        constraints.insets = new Insets(20,0,0,5);    //  top, left, bottom, right
        add(actualizar,constraints);

        constraints.gridx = 0; // empieza en la columna .
        constraints.gridy = 5; // empieza en la fila
        constraints.gridwidth = 2; // ocupa columnas.
        constraints.gridheight = 1; // El logo ocupa 1 filas.
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20,0,0,5);    //  top, left, bottom, right
        add(textoInfo,constraints);



        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                actualizarDatos();
                mostrarDatos();
            }
        });

       /* PanelActualizar.getTabla().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                JTable tabla = PanelActualizar.getTabla();
                int filaSeleccionada = tabla.getSelectedRow();

                campoAlias.setText(tabla.getValueAt(filaSeleccionada,1).toString());

                String sexo = tabla.getValueAt(filaSeleccionada,2).toString();
                if (sexo.equals("V")) masculinoRadio.setSelected(true);
                else femeninoRadio.setSelected(true);

                campoPeso.setText(tabla.getValueAt(filaSeleccionada,3).toString());

            }
        });*/



        PanelActualizar.getTabla().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                JTable tabla = PanelActualizar.getTabla();
                int filaSeleccionada = tabla.getSelectedRow();

                campoAlias.setText(tabla.getValueAt(filaSeleccionada,1).toString());

                String sexo = tabla.getValueAt(filaSeleccionada,2).toString();
                if (sexo.equals("V")) masculinoRadio.setSelected(true);
                else femeninoRadio.setSelected(true);

                campoPeso.setText(tabla.getValueAt(filaSeleccionada,3).toString());
                actualizar.setEnabled(true);
                textoInfo.setVisible(false);
            }
        });


        mostrarDatos();


    }

    public void actualizarDatos() {

        Connection con = Conexion.conectar();
        String sql = "UPDATE animales SET alias=? , sexo=? , peso=? WHERE id=? ";
        try {
            JTable tabla = PanelActualizar.getTabla();
            int filaSeleccionada = tabla.getSelectedRow();
            String id = (String) tabla.getValueAt(filaSeleccionada,0);

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,campoAlias.getText());
            ps.setString(2,generoGroup.getSelection().getActionCommand());
            ps.setInt(3, Integer.parseInt(campoPeso.getText()));
            ps.setInt(4, Integer.parseInt(id));

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"El registro se actualizo correctamente");

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

            PanelActualizar.getTabla().setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al actualizar los datos"+e.getMessage());
        }
    }



}
