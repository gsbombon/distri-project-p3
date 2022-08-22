package prj_grupo3_server.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import prj_grupo3_server.Modelo.Ciudad;
import prj_grupo3_server.Modelo.Cliente;

public class OracleConection {

    static Connection con = null;

    public static Connection ConectarO() {

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
        ConectarO();
    }

    // METHODS FOR LOGIN
    public static void singInOrc(String user, String pass) {

    }

    //CRUD FOR CIUDAD
    public static void insertarCiudadOrc(String nombre) throws SQLException {
        String sql = "INSERT INTO ciudad(nombre_ciu) VALUES (?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombre);
        int rowsInserted = statement.executeUpdate();
        System.out.println("CIUDAD INGRESANDO: " + nombre + " ROW: " + rowsInserted);
        if (rowsInserted > 0) {
            System.out.println("Insertado correctamente !");
        }
    }

    public static void actualizarCiudadOrc(String codigo, String nombre) throws SQLException {
        String sql = "UPDATE ciudad SET nombre_ciu=? WHERE codigo_ciu=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombre);
        statement.setString(2, codigo);
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Actualizado correctamente!");
        }
    }

    public static void eliminarCiudadOrc(int codigo) throws SQLException {
        String sql = "DELETE FROM ciudad WHERE codigo_ciu=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codigo);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Eliminado correctamente !");
        }
    }

    public static ArrayList<Ciudad> listarCiudadOrc() throws SQLException {
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        String sql = "SELECT * FROM ADMIN.ciudad";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int codigo = result.getInt(1);
            String nombre = result.getString(2);

            Ciudad ciudad = new Ciudad();
            String cod = String.valueOf(codigo);
            ciudad.setCodigo_Ciudad(cod);
            ciudad.setNombre_Ciudad(nombre);

            System.out.println(ciudad.getCodigo_Ciudad() + "-" + ciudad.getNombre_Ciudad());
            ciudades.add(ciudad);
        }
        return ciudades;
    }

    public static Ciudad buscarCiudadOrc(int codigo) throws SQLException {
        Ciudad ciudadBuscada = new Ciudad();
        String sql = "SELECT * FROM ADMIN.ciudad WHERE codigo_ciu=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codigo);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int codigoR = result.getInt(1);
            String nombreR = result.getString(2);
            String cod = String.valueOf(codigoR);
            ciudadBuscada.setCodigo_Ciudad(cod);
            ciudadBuscada.setNombre_Ciudad(nombreR);
        }
        return ciudadBuscada;
    }

    //CRUD FOR CLIENTE
    public static void insertarClienteOrc(String ruc, String nombre, String dir) throws SQLException {
        String sql = "INSERT INTO cliente(ruc_cli,nom_cli,dir_cli) VALUES (?,?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, ruc);
        statement.setString(2, nombre);
        statement.setString(3, dir);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Insertado correctamente !");
        }
    }

    public static void actualizarClienteOrc(String ruc, String nombre, String dir) throws SQLException {
        String sql = "UPDATE cliente SET nom_cli=?,dir_cli=? WHERE ruc_cli=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombre);
        statement.setString(2, dir);
        statement.setString(3, ruc);
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Actualizado correctamente!");
        }
    }

    public static void eliminarClienteOrc(String ruc) throws SQLException {
        String sql = "DELETE FROM cliente WHERE ruc_cli=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, ruc);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Eliminado correctamente !");
        }
    }
    
    public static ArrayList<Cliente> listarClienteOrc() throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int codigo = result.getInt(1);
            String ruc = result.getString(2);
            String nombre = result.getString(3);
            String direccion = result.getString(4);
            
            Cliente cli = new Cliente();
            
            cli.setId(codigo);
            cli.setRuc_Cliente(ruc);
            cli.setNombre_Cliente(nombre);
            cli.setDireccion_Cliente(direccion);
            
            clientes.add(cli);
        }
        return clientes;
    }
    
    public static Cliente buscarClienteOrc(String ruc) throws SQLException {
        Cliente clienteBuscar = new Cliente();
        String sql = "SELECT * FROM cliente WHERE ruc_cli=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, ruc);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int codigoR = result.getInt(1);
            String rucR = result.getString(2);
            String nombreR = result.getString(3);
            String direccionR = result.getString(4);
            
            clienteBuscar.setId(codigoR);
            clienteBuscar.setRuc_Cliente(rucR);
            clienteBuscar.setNombre_Cliente(nombreR);
            clienteBuscar.setDireccion_Cliente(direccionR);
        }
        return clienteBuscar;
    }

}
