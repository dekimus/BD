
package Controlador;

import Modelo.Conectar;
import Vista.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

public class ControladorVentanaPrincipal implements ActionListener{
    
    private VentanaPrincipal ventana;
    DefaultTableModel modeloTabla;
    
    public ControladorVentanaPrincipal(VentanaPrincipal v){
       
        this.ventana = v;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object origen = e.getSource();
        
        if(origen == ventana.m_11){// conectar
            new SubventanaConexion(this.ventana).setVisible(true);
            ventana.setEnabled(false);
            ventana.infoTxt.setText("Conectado...");
        
        }else if(origen == ventana.m_12){ // Desconectar
            System.out.println("desconectado");
            Conectar.desconectar();
            this.desconectar();
            
        }else if(origen == ventana.m_21){ //Abrir base de dato
            try{
                ventana.comboModel.removeAllElements(); // limpia el comboBox
                // Crea un array con las tablas de la base seleccionada
                // y las añade al comboBox
                String[] tablas = Conectar.abrirBaseDatos(ventana.nombreBase.getText());
                for(String s: tablas){
                    ventana.comboModel.addElement(s);
                }
                ventana.comboTablas.setEnabled(true);
                ventana.infoData.setText(ventana.nombreBase.getText());
                ventana.infoData.setEnabled(true);
                ventana.m_31.setEnabled(true);
                ventana.nombreBase.setText("");
                ventana.infoTxt.setText("BD "+ventana.infoData.getText()+" en uso.");
                ventana.tabla.setEnabled(true);
                ventana.nombreTabla.setText(ventana.comboTablas.getSelectedItem().toString());
                ventana.nombreTabla.setEnabled(true);
            } catch (java.sql.SQLException ex) {
                ventana.lanzarError(ex.getMessage());// Muestra JOptionPane
            } 
        }else if(origen==ventana.m_31){ // mostrar tabla
            // Crea un modelo limpio de jtable
            modeloTabla = new DefaultTableModel();
            try {
                ventana.nombreTabla.setText(ventana.comboTablas.getSelectedItem().toString());
                // rellena el modelo
                Conectar.abrirTabla(ventana.comboTablas.getSelectedItem().toString(), modeloTabla);
                // asigna el modelo
                ventana.tablas.setModel(modeloTabla);
                ventana.infoTxt.setText("Tabla "+ventana.comboTablas.getSelectedItem().toString());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());// Muestra JOptionPane
            }
            
        }else if(origen == ventana.comboTablas){ // cambio en el ComboBox
            if(ventana.comboTablas.getSelectedItem()!= null){
                ventana.nombreTabla.setText(ventana.comboTablas.getSelectedItem().toString());
            }
            
        }
  
    }
    
    private void desconectar(){ // vuelve todo al estado original
        // zona conectar
        ventana.m_11.setEnabled(true);
        ventana.m_12.setEnabled(false);
        // zona datos
        ventana.conexion.setEnabled(false);
        ventana.user.setText("");
        ventana.port.setText("");
        ventana.con.setText("DESCONECTADO");
        ventana.con.setForeground(Color.red);
        // barra tareas
        ventana.m_21.setEnabled(false);
        ventana.m_31.setEnabled(false);
        // resto paneles
        ventana.bd.setEnabled(false);
        ventana.tabla.setEnabled(false);
        ventana.nombreBase.setEnabled(false);
        ventana.nombreBase.setText("");
        ventana.infoData.setText("");
        ventana.nombreTabla.setEnabled(false);
        ventana.comboTablas.removeAllItems();//borramos los items
        ventana.comboTablas.addItem("Escoge Tabla");//añadimos el default
        ventana.comboTablas.setEnabled(false);
        ventana.tablas.setModel(new DefaultTableModel());//borramos la Jtable
        ventana.nombreTabla.setText("");
        ventana.infoTxt.setText("");
    }    
}
