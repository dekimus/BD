
package Vista;

import Controlador.ControlSubventanaConexion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SubventanaConexion extends JFrame{
    
    VentanaPrincipal vp;
    public JTextField user,port;
    public JPasswordField pass;
    public JButton botonAceptar, botonConexion;
    public JLabel estadoBD;
    public JPanel estado;
    
    public SubventanaConexion(VentanaPrincipal vp){
        this.vp = vp;
        initComponents();
    }

    private void initComponents() {
        //JFrame principal
        this.setSize(400,300);
        
        // función de salida custom
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exit();
                 }
            });
        
        
        this.setResizable(false);
        this.setTitle("Servidor");
        this.getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);
        
        
        //JPanel Datos Conexión
        JPanel datos = new JPanel();
        datos.setLayout(new GridLayout(3,2));
        datos.setBounds(10,10,360,90);
        JLabel usuario = new JLabel("Usuario:");
        JLabel password = new JLabel("Password:");
        JLabel puerto = new JLabel("Puerto:");
        user = new JTextField("root");
        pass = new JPasswordField();
        port = new JTextField("3306");
        datos.add(usuario);
        datos.add(user);
        datos.add(password);
        datos.add(pass);
        datos.add(puerto);
        datos.add(port);
        add(datos);
        
        //JPanel Estado Conexión
        estado = new JPanel();
        estado.setLayout(new FlowLayout());
        estado.setBounds(10,110,360,90);
        estado.setBackground(Color.CYAN);
        estadoBD = new JLabel("ESTADO");
        estado.add(estadoBD);
        this.getContentPane().add(estado);
        
        //J
        JPanel botones = new JPanel();
        botones.setBounds(10,210,360,90);
        botonConexion = new JButton("Conectar");
        botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(new ControlSubventanaConexion(this, vp));
        botonConexion.addActionListener(new ControlSubventanaConexion(this, vp));
        botones.add(botonConexion);
        botones.add(botonAceptar);
        
        this.getContentPane().add(botones);
    }
    
    //activa la ventana principal y elimina la de conexión
    private void exit(){
        vp.setEnabled(true);
        this.dispose();
    }
    
}

