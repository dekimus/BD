
package Controlador;

import Vista.*;
import Modelo.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ControlSubventanaConexion implements ActionListener{
    SubventanaConexion venCon; 
    VentanaPrincipal vp;
    // recibimos en el constructor las dos ventanas para acceder a sus variables
    public ControlSubventanaConexion(SubventanaConexion svc, VentanaPrincipal vp){
        this.venCon = svc; 
        this.vp = vp;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object origen = e.getSource();//comprobamos el origen del evento
        
        if(origen == venCon.botonAceptar){ // sies aceptar cierra la ventana
            venCon.dispose();
            vp.setEnabled(true);
            vp.toFront();
        }
        if(origen == venCon.botonConexion){ // realiza la conexion
            Conectar.url = "jdbc:mysql://localhost:"+venCon.port.getText()+"/";
            Conectar.user = venCon.user.getText();
            Conectar.pass = venCon.pass.getText();
            try{    
               Conectar.establecerConexion();
               venCon.estadoBD.setText("Conectado");
               venCon.estado.setBackground(Color.green);
               vp.con.setText("Conectado");
               vp.con.setForeground(Color.green);
               vp.conexion.setEnabled(true);
               vp.m_21.setEnabled(true);
               vp.bd.setEnabled(true);
               vp.m_11.setEnabled(false);
               vp.m_12.setEnabled(true);
               vp.user.setText(venCon.user.getText());
               vp.port.setText(venCon.port.getText());
               vp.nombreBase.setEnabled(true);
                
            }catch(SQLException sqlE){
                venCon.estadoBD.setText("Error de conexión");
                
                venCon.estado.setBackground(Color.red);
            }   
        }
    }   
}
