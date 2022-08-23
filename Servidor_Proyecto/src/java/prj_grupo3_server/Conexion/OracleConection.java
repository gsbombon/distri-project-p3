package prj_grupo3_server.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import prj_grupo3_server.Modelo.Articulo;
import prj_grupo3_server.Modelo.Movimiento;

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

    
        // CRUD ARTICULO//
    public static void insertarArticuloOrc(String nombre, String precio, int stock) throws SQLException {
        String sql = "INSERT INTO articulo (nombre_art,precio_art,stock_art) VALUES (?,?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombre);
        statement.setString(2, precio);
        statement.setInt(3, stock);
        int rowsInserted = statement.executeUpdate();
        System.out.println("ARTICULO INGRESANDO: " + nombre + "--" + precio + "--" + stock + " ROW: " + rowsInserted);
        if (rowsInserted > 0) {
            System.out.println("Insertado correctamente !");
        }
    }

    public static void actualizarArticuloOrc(int codigo, String nombre, String precio , int stock) throws SQLException {
        String sql = "UPDATE articulo SET nombre_art=?, precio_art=?, stock_art=? WHERE codigo_art=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombre);
        statement.setString(2, precio);
        statement.setInt(3, stock);
        statement.setInt(4, codigo);
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Actualizado correctamente!");
        }
    }

    public static void eliminarArticuloOrc(int codigo) throws SQLException {
        String sql = "DELETE FROM articulo WHERE codigo_art=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codigo);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Eliminado correctamente !");
        }
    }

    public static ArrayList<Articulo> listarArticuloOrc() throws SQLException {
        ArrayList<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM ADMIN.articulo";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int codigo = result.getInt(1);
            String nombre = result.getString(2);
            String precio = result.getString(3);
            String stock = result.getString(4);

            Articulo articulo = new Articulo();
            String cod = String.valueOf(codigo);
            articulo.setCodigo_Articulo(cod);
            articulo.setNombre_Articulo(nombre);
            articulo.setPrecio_Articulo(precio);
            articulo.setStock_Articulo(stock);

            System.out.println(articulo.getCodigo_Articulo() + "-" + articulo.getNombre_Articulo() + "-" + articulo.getPrecio_Articulo() + "-" + articulo.getStock_Articulo());
            articulos.add(articulo);
        }
        return articulos;
    }

    public static Articulo buscarArticuloOrc(int codigo) throws SQLException {
        Articulo articuloBuscada = new Articulo();
        String sql = "SELECT * FROM ADMIN.articulo WHERE codigo_art=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codigo);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int codigoR = result.getInt(1);
            String nombreR = result.getString(2);
            String precioR = result.getString(3);
            String stockR = result.getString(4);

            String cod = String.valueOf(codigoR);
            articuloBuscada.setCodigo_Articulo(cod);
            articuloBuscada.setNombre_Articulo(nombreR);
            articuloBuscada.setPrecio_Articulo(precioR);
            articuloBuscada.setStock_Articulo(stockR);
        }
        return articuloBuscada;
    }

    public static Articulo buscarArticuloNOrc(String nombre) throws SQLException {
        Articulo articuloBuscada = new Articulo();
        String sql = "SELECT * FROM ADMIN.articulo WHERE nombre_art=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombre);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int codigoR = result.getInt(1);
            String nombreR = result.getString(2);
            String precioR = result.getString(3);
            String stockR = result.getString(4);

            String cod = String.valueOf(codigoR);
            articuloBuscada.setCodigo_Articulo(cod);
            articuloBuscada.setNombre_Articulo(nombreR);
            articuloBuscada.setPrecio_Articulo(precioR);
            articuloBuscada.setStock_Articulo(stockR);
        }
        return articuloBuscada;
    }

    // CRUD TIPO MOVIMIENTO//
    public static void insertarMovimientoOrc(String nombre, String signo) throws SQLException {
        String sql = "INSERT INTO tipomovimiento (nombre_tmov, signo_tmov) VALUES (?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombre);
        statement.setString(2, signo);
        int rowsInserted = statement.executeUpdate();
        System.out.println("MOVIMIENTO INGRESANDO: " + nombre + "--" + signo + " ROW: " + rowsInserted);
        if (rowsInserted > 0) {
            System.out.println("Insertado correctamente !");
        }
    }

    public static void actualizarMovimientoOrc(int codigo, String nombre , String signo) throws SQLException {
        String sql = "UPDATE tipomovimiento SET nombre_tmov=?, signo_tmov=? WHERE codigo_tmov=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombre);
        statement.setString(2, signo);
        statement.setInt(3, codigo);
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Actualizado correctamente!");
        }
    }

    public static void eliminarMovimientoOrc(int codigo) throws SQLException {
        String sql = "DELETE FROM tipomovimiento WHERE codigo_tmov=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codigo);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Eliminado correctamente !");
        }
    }

    public static ArrayList<Movimiento> listarMovimientoOrc() throws SQLException {
        ArrayList<Movimiento> movimientos = new ArrayList<>();
        String sql = "SELECT * FROM ADMIN.tipomovimiento";
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int codigo = result.getInt(1);
            String nombre = result.getString(2);
            String signo = result.getString(3);
            String cod = String.valueOf(codigo);
            Movimiento mov = new Movimiento();
            mov.setCodigo(cod);
            mov.setNombre(nombre);
            mov.setSigno(signo);

            System.out.println(mov.getCodigo() + "-" + mov.getNombre() + "-" + mov.getSigno());
            movimientos.add(mov);
        }
        return movimientos;
    }

    public static Movimiento buscarMovimientoOrc(int codigo) throws SQLException {
        Movimiento movimientoBuscada = new Movimiento();
        String sql = "SELECT * FROM ADMIN.tipomovimiento WHERE codigo_tmov=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codigo);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int codigoR = result.getInt(1);
            String nombreR = result.getString(2);
            String signoR = result.getString(3);

            String cod = String.valueOf(codigoR);
            movimientoBuscada.setCodigo(cod);
            movimientoBuscada.setNombre(nombreR);
            movimientoBuscada.setSigno(signoR);
        }
        return movimientoBuscada;
    }

    public static Movimiento buscarMovimientoNOrc(String nombre) throws SQLException {
        Movimiento movimientoBuscada = new Movimiento();
        String sql = "SELECT * FROM ADMIN.tipomovimiento WHERE nombre_tmov=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombre);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int codigoR = result.getInt(1);
            String nombreR = result.getString(2);
            String signoR = result.getString(3);

            String cod = String.valueOf(codigoR);
            movimientoBuscada.setCodigo(cod);
            movimientoBuscada.setNombre(nombreR);
            movimientoBuscada.setSigno(signoR);
        }
        return movimientoBuscada;
    }
   
        //CRUD CABECERA INVENTARIO
    public static void crearCabeceraInventarioOrc(int movimiento, String fecha) throws SQLException {
        String sql = "INSERT INTO  CABE_C_INVENTARIO (CODIGO_TMOV, FECHA_CABE_INVEN) VALUES (?, TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS'))";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, movimiento);
        statement.setString(2, fecha);
        int rowsInserted = statement.executeUpdate();
        System.out.println("CABEZERA INGRESANDO: " + movimiento + "--" + fecha + " ROW: " + rowsInserted);
        if (rowsInserted > 0) {
            System.out.println("Insertado correctamente !");
        }
    }

    public static void actualizarCabeceraInventarioOrc(int codigo, String nombre, String fecha) throws SQLException {
        String sql = "UPDATE CABE_C_INVENTARIO SET CODIGO_TMOV=?, FECHA_CABE_INVEN=? WHERE NUMERO_CABE_INVEN=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombre);
        statement.setString(2, fecha);
        statement.setInt(3, codigo);
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Actualizado correctamente!");
        }
    }

    public static void eliminarCabeceraInventarioOrc(int codigo) throws SQLException {
        String sql = "DELETE FROM tipomovimiento WHERE codigo_tmov=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codigo);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Eliminado correctamente !");
        }
    }
    
}
