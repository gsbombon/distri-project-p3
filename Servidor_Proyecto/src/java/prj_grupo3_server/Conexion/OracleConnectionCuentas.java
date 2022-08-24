
package prj_grupo3_server.Conexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import prj_grupo3_server.Modelo.CabeceraFactura;
import prj_grupo3_server.Modelo.Cobrador;
import prj_grupo3_server.Modelo.Factura;
import prj_grupo3_server.Modelo.FormaPago;


public class OracleConnectionCuentas {

    static Connection con = null;

    public static Connection ConectarOC() {

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:XE";
            con = DriverManager.getConnection(BaseDeDatos, "admi", "admin");
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
          //////////////COBRADOR  05/07/22////////////
    public static void insertarCobradorOrc( String nombre, String dir,String cedula) throws SQLException  {         
      
    
             String sql = "Insert INTO cobrador(nom_cob,dir_cob,cedula_cob) VALUES (?,?,?)";
            
            PreparedStatement statement = con.prepareStatement(sql);
       
        statement.setString(1, nombre);
        statement.setString(2, dir);
         statement.setString(3, cedula);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Insertado correctamente !");
        }
       
        
    }


    public static void actualizarCobradorOrc(String cedula, String nombre, String dir)throws SQLException  {
        
            String sql = "UPDATE cobrador SET NOM_COB = ?, DIR_COB = ? WHERE CEDULA_COB = ? ";
           
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, dir);
            pst.setString(3, cedula);
           int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Actualizado correctamente!");
        }
       
    }
    
     public static void eliminarCobradorOrc(String cedula) throws SQLException{
        
             String sql  = "DELETE FROM cobrador WHERE CEDULA_COB = ? ";
           
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cedula);
            int rowsDeleted = pst.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Eliminado correctamente !");
        }
        
    }

    public static ArrayList<Cobrador> listarCobradorORC() throws SQLException {
       ArrayList<Cobrador> cobrador = new ArrayList<>();
        String sql = "SELECT * FROM cobrador";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int codigo = result.getInt(1);
          String nombre = result.getString(2);
            String direccion = result.getString(3);
              String cedula = result.getString(4);

            Cobrador cob = new Cobrador();
            cob.setId(codigo);
            cob.setNombre_Cobrador(nombre);
            cob.setDireccion_Cobrador(direccion);
            cob.setCedula_Cobrador(cedula);
            

            cobrador.add(cob);
        }
        return cobrador;
    }
    
    public static Cobrador buscarCobradorOrc(String cedula) throws SQLException {
        Cobrador cobradorBuscar = new Cobrador();
        String sql = "SELECT * FROM cobrador WHERE CEDULA_COB=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, cedula);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int codigoR = result.getInt(1);
           
            String nombreR = result.getString(2);
            String direccionR = result.getString(3);
             String cedulaR = result.getString(4);
            cobradorBuscar.setId(codigoR);
            cobradorBuscar.setNombre_Cobrador(nombreR);
            cobradorBuscar.setDireccion_Cobrador(direccionR);
            cobradorBuscar.setCedula_Cobrador(cedulaR);
           
        }
        return cobradorBuscar;
    }
    //CRUD Forma Pago
     public static void insertarFPOrc(String nombre) throws SQLException {
        String sql = "INSERT INTO formapago(nom_fp) VALUES (?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombre);
        int rowsInserted = statement.executeUpdate();
        System.out.println("CIUDAD INGRESANDO: " + nombre + " ROW: " + rowsInserted);
        if (rowsInserted > 0) {
            System.out.println("Insertado correctamente !");
        }
    }
       public static void actualizarFPOrc(String codigo, String nombre) throws SQLException {
        String sql = "UPDATE formapago SET nom_fp=? WHERE codigo_fp=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombre);
        statement.setString(2, codigo);
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Actualizado correctamente!");
        }
    }
         public static void eliminarFPOrc(int codigo) throws SQLException {
        String sql = "DELETE FROM formapago WHERE codigo_fp=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codigo);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Eliminado correctamente !");
        }
    }
          public static ArrayList<FormaPago> listarFPOrc() throws SQLException {
        ArrayList<FormaPago> FP = new ArrayList<>();
        String sql = "SELECT * FROM ADMI.formapago";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int codigo = result.getInt(1);
            String nombre = result.getString(2);

            FormaPago fp = new FormaPago();
           
            String cod = String.valueOf(codigo);
            fp.setId(codigo);
            fp.setCodigo(cod);
            fp.setNombre_FP(nombre);
         

            System.out.println(fp.getCodigo() + "-" + fp.getNombre_FP());
             FP.add(fp);
        }
        return FP;
    }
        public static FormaPago buscarFPOrc(int codigo) throws SQLException {
        FormaPago FPBuscada = new FormaPago();
        String sql = "SELECT * FROM ADMI.formapago WHERE codigo_fp=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codigo);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int codigoR = result.getInt(1);
            String nombreR = result.getString(2);
            String cod = String.valueOf(codigoR);
            FPBuscada.setCodigo(cod);
            FPBuscada.setNombre_FP(nombreR);
            
        }
        return FPBuscada;
    }
         public static ArrayList<CabeceraFactura> listarFacturaORC()throws SQLException {
        ArrayList<CabeceraFactura> facturas = new ArrayList<>();
         String sql = "SELECT * FROM CABECERA_FACTURA";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);
      
       while (result.next()) {
            int codigo = result.getInt(1);
            int codigo_cli = result.getInt(2);
            int codigo_ciu = result.getInt(3);
            String fecha=  result.getString(4);
            String total= result.getString(5);
            

            double totalFac = Double.parseDouble(total);
            CabeceraFactura fac = new CabeceraFactura();
            fac.setNumCabecera(String.valueOf(codigo));
            fac.setCodCiudad(String.valueOf(codigo_ciu));
            fac.setFecha(fecha);
            fac.setRucCliente(String.valueOf(codigo_cli));
            fac.setPrecioTotal(String.valueOf(totalFac));
         

            facturas.add(fac);
        }
        return facturas;
    }
         
}
