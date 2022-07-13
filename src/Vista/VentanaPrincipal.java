
package Vista;

import Controlador.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class VentanaPrincipal extends JFrame{
    
    public JPanel conexion, bd, tabla, mostrarTabla;
    public JMenuBar  barraSuperior;
    private JMenu m_1, m_2, m_3;
    public JMenuItem m_11, m_12, m_21, m_31;
    public JLabel user, port, con, infoData, infoTxt, infoError = new JLabel();
    public JTextField nombreBase, nombreTabla;
    public JComboBox comboTablas;
    public JTable tablas;
    public DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel();
   
    public VentanaPrincipal(){
        initComponents();
    }

    private void initComponents() {
    
        // Frame principal
        this.setSize(900, 450);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Aplicación Base de Datos");
        this.getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        
        // Barra superior
        barraSuperior = new JMenuBar();
        m_1 = new JMenu("Servidor");
        m_2 = new JMenu("Base de Datos");
        m_3 = new JMenu("Tablas");
        barraSuperior.add(m_1);
        barraSuperior.add(m_2);
        barraSuperior.add(m_3);
        m_11 = new JMenuItem("Conectar");
        m_12 = new JMenuItem("Desconectar");
        m_21 = new JMenuItem("Abrir");
        m_21.setEnabled(false);
        m_31 = new JMenuItem("Mostrar");
        m_31.setEnabled(false);
        m_12.setEnabled(false);
        m_1.add(m_11);
        m_1.add(m_12);
        m_2.add(m_21);
        m_3.add(m_31);
        m_11.addActionListener(new ControladorVentanaPrincipal(this));
        m_12.addActionListener(new ControladorVentanaPrincipal(this));
        m_21.addActionListener(new ControladorVentanaPrincipal(this));
        m_31.addActionListener(new ControladorVentanaPrincipal(this));
        this.setJMenuBar(barraSuperior);
        
        // JPanel conexión
        conexion = new JPanel();
        conexion.setBounds(10,10,860,40);
        conexion.setLayout(new GridLayout(0, 5));
        conexion.setBorder(BorderFactory.createTitledBorder("Datos de Conexión"));
        JLabel usuario = new JLabel("Usuario:");
        JLabel puerto = new JLabel("Puerto:");
        user = new JLabel("");
        port = new JLabel("");
        user.setForeground(Color.BLUE);
        port.setForeground(Color.BLUE);
        con = new JLabel("DESCONECTADO");
        con.setForeground(Color.red);
        conexion.add(usuario);
        conexion.add(user);
        conexion.add(puerto);
        conexion.add(port);
        conexion.add(con);
        conexion.setEnabled(false);
        this.getContentPane().add(conexion);
        
        //JPanel Base de datos
        bd = new JPanel();
        bd.setBounds(10, 50, 860, 40);
        bd.setLayout(new BoxLayout(bd, BoxLayout.X_AXIS));
        bd.setBorder(BorderFactory.createTitledBorder("Base de Datos"));
        JLabel nombre = new JLabel("Nombre:");
        infoData = new JLabel("");
        nombreBase = new JTextField();
        nombreBase.setEnabled(false);
        infoData.setForeground(Color.BLUE);
        bd.add(nombre);
        bd.add(nombreBase);
        bd.add(infoData);
        bd.setEnabled(false);
        this.getContentPane().add(bd);
        
        //JPanel Tabla
        tabla = new JPanel();
        tabla.setBounds(10, 95, 860, 40);
        tabla.setLayout(new BoxLayout(tabla, BoxLayout.X_AXIS));
        tabla.setBorder(BorderFactory.createTitledBorder("Tablas"));
        nombreTabla = new JTextField();
        nombreTabla.setEnabled(false);
        JLabel nombre2 = new JLabel("Nombre:");
        tabla.add(nombre2);
        tabla.add(nombreTabla);
        tabla.setEnabled(false);
        this.getContentPane().add(tabla);
        
        //JPanel Mostrar Tablas
        mostrarTabla = new JPanel(); //panel principal
        mostrarTabla.setBounds(10, 140, 860, 180);
        mostrarTabla.setLayout(null);      
        mostrarTabla.setBorder(BorderFactory.createTitledBorder("Mostrar Tabla"));
        //Combo box
        comboTablas = new JComboBox();
        comboTablas.setEnabled(false);
        comboTablas.setBounds(20,30,105,25);
        comboTablas.setModel(comboModel);
        comboTablas.addItem("Escoge Tabla");
        comboTablas.addActionListener(new ControladorVentanaPrincipal(this));
        //Panel que contiene el JScrollpane y el JTable
        JPanel panelTablas = new JPanel();
        panelTablas.setBounds(150, 15, 700, 160);
        panelTablas.setBackground(Color.white);
        //El JTable
        tablas = new JTable();
        tablas.setPreferredScrollableViewportSize(new Dimension(670,130));
        //El JScrollPane
        JScrollPane scrollTablas = new JScrollPane(tablas);//le pasamos la tabla
        panelTablas.add(scrollTablas);// añadimos el JScroll al Panel 2
        mostrarTabla.add(comboTablas);// añadimos el combo
        mostrarTabla.add(panelTablas);//añadimos el panel 2
        mostrarTabla.setEnabled(false);
        this.getContentPane().add(mostrarTabla);//añadimos el panel principal
        
        //JPanel Información
        JPanel info = new JPanel();
        info.setBounds(10,320,860,50);
        info.setBorder(BorderFactory.createTitledBorder("Información"));
        info.setLayout(new FlowLayout());
        infoTxt = new JLabel("");
        info.add(infoTxt);
        this.getContentPane().add(info);
    }
    
    // informa sobre los errores 
    public void lanzarError(String msg){
        infoError.setText(msg);
        JOptionPane.showInternalMessageDialog(null, infoError.getText(),
                    "Error", JOptionPane.ERROR_MESSAGE);
    }
    
}

