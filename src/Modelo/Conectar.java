
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class Conectar {
    
    public static String url;
    public static String user;
    public static String pass;
    static Connection cnx;
    static PreparedStatement pst;
    static Statement st;
    static ResultSet rs;
    static ResultSetMetaData rsmd;
       
    // Establece la conexión
    public static void establecerConexion() throws SQLException{
        cnx = DriverManager.getConnection(url, user, pass);
    }
    //Desconecta
    public static void desconectar(){
        try{
            cnx.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }  
    }
    // Abre la base de datos seleccionada y devuelve las tablas en un Array
    public static String[] abrirBaseDatos(String base) throws SQLException{
        ArrayList<String> listaTablas = new ArrayList();
        st = cnx.createStatement();
        st.executeUpdate("USE "+base);
        rs = st.executeQuery("SHOW TABLES");
        while(rs.next()){
            listaTablas.add(rs.getString(1));
        }
        String[] salida = new String[listaTablas.size()];
        listaTablas.toArray(salida);
        return salida;
        
    }
    
    //Muestra la tabla seleccionada, recibe la tabla y la Table model
    public static void abrirTabla(String tabla, DefaultTableModel tableModel) throws SQLException{
        st = cnx.createStatement();
        rs = st.executeQuery("SELECT * FROM "+tabla);
        rsmd = rs.getMetaData();
        int col = rsmd.getColumnCount();
        for (int i = 0; i < col; i++) {
            tableModel.addColumn(rsmd.getColumnLabel(i+1));
        }
        
        while(rs.next()){
            Object[] fila = new Object[col];
            
            for (int j = 0; j < col; j++) {
                fila[j] = rs.getObject(j+1);
            }
            tableModel.addRow(fila);
        }        
    }
    
}
