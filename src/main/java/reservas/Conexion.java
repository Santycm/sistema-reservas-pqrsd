package reservas;

import principal.*;
import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion{
    Connection conectar = null;
    
    String usuario = "UserTPH";
    String password = "7845";
    String bd = "ConjuntoResidencial";
    String ip = "localhost";
    String puerto = "1433";
    
    String cadena = "jdbc:sqlserver://" + ip + ":" + puerto + "/" + bd;
    
    public Connection establecerConexion() {

        try {
            String cadena = "jdbc:sqlserver://localhost:" + puerto + ";" + "databaseName=" + bd+ ";TrustServerCertificate=True";
            conectar = DriverManager.getConnection(cadena, usuario, password);
            System.out.println("conectado");
        } catch (Exception e) {
            System.out.println(e);
        }
        return conectar;
    }
}
