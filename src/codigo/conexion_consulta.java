/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;
import java.sql.*;
/**
 *
 * @author alexander
 */
public class conexion_consulta {
    static Connection conexion=null;
    static Statement sentencia;
    static ResultSet resultado;
    public static void conectar(){
        String ruta="jdbc:mysql://localhost/Prueba";
        String user="root";
        String pass="182411";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection(ruta,user,pass); 
            sentencia= conexion.createStatement();
            System.out.println("Conectado");
        } catch (Exception e) {
            System.out.println("No conectado");
        }
    }
    public static variables buscar_reg(String Nombre){
        variables r = null;
        String q = "SELECT * FROM Datos" + " WHERE Nombre='"+Nombre+"'";
        try {
            resultado = sentencia.executeQuery(q);
            System.out.println("Correcto");
        } catch (Exception e) {
            System.out.println(" No Correcto");
        }
        r= asignar();
        return r;
                
    }
    public static variables asignar(){
      variables r = null;
      String Nombre;
      String Apellido;
        try {
            if(resultado.last()){
                Nombre= resultado.getString("Nombre");
                Apellido= resultado.getString("Apellido");
                r= new variables (Nombre, Apellido);
            }
        } catch (Exception e) {
        }
      
      return r;
                
    }
    public static void eliminar_reg(String Nombre){
        String q= "DELETE FROM Datos WHERE Nombre='"+Nombre+"'";
        ejecutar(q);
    }

    public static void ejecutar(String q){
        try {
            sentencia.executeUpdate(q);
            System.out.println("Correcto");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
