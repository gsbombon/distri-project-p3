package prj_grupo3_server.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public static int loginOrc(String usuario, String contrasena) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE usuario=? AND pass=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, usuario);
        statement.setString(2, contrasena);
        ResultSet result = statement.executeQuery();

        int i = 0;

        while (result.next()) {
            i++;
        }

        if (i > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void insertarUsuarioOrc(String nombre, String password) throws SQLException {
        String sql = "INSERT INTO usuarios(usuario,pass) VALUES (?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombre);
        statement.setString(2, password);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Insertado correctamente !");
        }
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

    // FACTURACION
    public static void crearCabeceraFacturaOrc(String rucCliente, String nomCiudad, String fechaS) throws SQLException, ParseException {
        int codCiudad = codigoCiudad(nomCiudad);
        int codCliente = codigoCliente(rucCliente);
        Date fecha = stringToDate(fechaS);
        String valor = "0";
        String sql = "INSERT INTO cabecera_factura(codigo_cli,codigo_ciu,fecha_cabecera_factu,valor_cabecera_facu) VALUES (?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codCliente);
        statement.setInt(2, codCiudad);
        statement.setDate(3, (java.sql.Date) fecha);
        statement.setString(4, valor);
        
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Insertado correctamente !");
        }
    }
    
    public static void actualizarCabeceraFacturaOrc(String numCabecera, String rucCliente, String nomCiudad, String fecha) throws SQLException, ParseException{
        int idCabecera = Integer.parseInt(numCabecera);
        int codCliente = codigoCliente(rucCliente);
        int codCiudad = codigoCiudad(nomCiudad);
        Date fechaR = stringToDate(fecha);
        String valor = "0";
        String sql = "UPDATE cabecera_factura SET codigo_cli=?,codigo_ciu=?,fecha_cabecera_factu=?,valor_cabecera_facu=? WHERE =?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codCliente);
        statement.setInt(2, codCiudad);
        statement.setDate(3, (java.sql.Date) fechaR);
        statement.setString(4, valor);
        statement.setInt(5, idCabecera);
        
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Actualizado correctamente!");
        }
    }
    
    
    //METODOS NO CONTABLES
    
    public static Date stringToDate(String fecha) throws ParseException{
        Date fechaD=new SimpleDateFormat("dd/MM/yyyy").parse(fecha);  
        return fechaD;
    }
    
    public static int codigoCiudad(String nombreCiudad) throws SQLException{
        int codigo=0;
        String sql = "SELECT codigo_ciu FROM ciudad WHERE nombre_ciu=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombreCiudad);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            codigo = result.getInt(1);
        }
        return codigo;
    }
    
    public static int codigoCliente(String rucCliente) throws SQLException{
        int codigo=0;
        String sql = "SELECT codigo_cli FROM cliente WHERE ruc_cli=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, rucCliente);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            codigo = result.getInt(1);
        }
        return codigo;
    }
    
    
}
