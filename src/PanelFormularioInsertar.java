import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class PanelFormularioInsertar extends JPanel {

    private JLabel alias;
    private JLabel sexo;
    private JLabel peso;
    private JTextField campoAlias;
    private JTextField campoPeso;
    private JButton insertar;
    private JRadioButton masculinoRadio;
    private JRadioButton femeninoRadio;
    private ButtonGroup generoGroup;
    private JButton actualizar;




    public PanelFormularioInsertar() {
        Dimension d = getPreferredSize();
        d.width = 300;
        setPreferredSize(d);

        Border interior = BorderFactory.createTitledBorder("Insertar Animales");
        Border exterior = BorderFactory.createEmptyBorder(15,15,15,15);
        //setBackground(Color.darkGray);
        setBorder(BorderFactory.createCompoundBorder(exterior, interior));

        setBackground(new Color(232,232,232));
        setLayout(new GridBagLayout());

        alias = new JLabel("Alias: ");
        sexo = new JLabel("Sexo: ");
        peso = new JLabel("Peso: ");
        insertar = new JButton("Insertar");
        masculinoRadio = new JRadioButton("V");
        masculinoRadio.setActionCommand("V");
        femeninoRadio = new JRadioButton("F");
        femeninoRadio.setActionCommand("F");
        generoGroup = new ButtonGroup();
        actualizar = new JButton("Actualizar datos");

        masculinoRadio.setSelected(true);

        generoGroup.add(masculinoRadio);
        generoGroup.add(femeninoRadio);


        campoAlias = new JTextField(30);

        campoPeso = new JTextField(20);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;         // El alias empieza en la columna cero.
        constraints.gridy = 0;         // El alias empieza en la fila cero
        constraints.gridwidth = 1;     // El alias ocupa 1 columnas.
        constraints.gridheight = 1;    // El alias ocupa 1 filas.
        //constraints.weightx = 0.5;                  // La fila 0 debe estirarse, le ponemos un 1.0
        constraints.insets = new Insets(10,0,0,0);    //  top, left, bottom, right
       // constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        add(alias,constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(10,0,0,0);    //  top, left, bottom, right
        //constraints.fill = GridBagConstraints.HORIZONTAL;
        add(campoAlias,constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(10,0,0,0);    //  top, left, bottom, right
        add(sexo,constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
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
        add(insertar,constraints);



        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                insertarDatos();
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mostrarDatos();
            }
        });

        mostrarDatos();


    }

    public void insertarDatos() {

        Connection con = Conexion.conectar();
        String sql = "INSERT INTO animales(alias, sexo, peso ) VALUES(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,campoAlias.getText());
            ps.setString(2,generoGroup.getSelection().getActionCommand());
            ps.setInt(3, Integer.parseInt(campoPeso.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se inserto correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error al insertar en la base de datos"+e.getMessage());

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

            PanelInsertar.getTabla().setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al insertar un registro: " +
                    " "+e.getMessage());
        }
    }
}
