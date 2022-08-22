
package prj_grupo3_server.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;


public class OracleConnectionCuentas {

    static Connection con = null;

    public static Connection ConectarOC() {

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:XE";
            con = DriverManager.getConnection(BaseDeDatos, "admin", "admin");
            if (con != null) {
                System.out.println("Conexion exitosa a esquema DISTRI");
            } else {
                System.out.println("Conexion fallida");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
        ConectarOC();
    }
    
}
