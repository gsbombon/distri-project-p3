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
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;
import prj_grupo3_server.Modelo.CabeceraFactura;
import prj_grupo3_server.Modelo.Ciudad;
import prj_grupo3_server.Modelo.Cliente;
import prj_grupo3_server.Modelo.DetalleFactura;
import prj_grupo3_server.Modelo.ItemFactura;

public class OracleConectionFacturacion implements ExceptionListener {

    static Connection con = null;

    public static Connection ConectarOF() {

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
        ConectarOF();
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
        String valor = "0";
        String sql = "INSERT INTO cabecera_factura(codigo_cli,codigo_ciu,fecha_cabecera_factu,valor_cabecera_facu) VALUES (?,?,TO_DATE(?, 'DD-MM-YYYY'),?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codCliente);
        statement.setInt(2, codCiudad);
        statement.setString(3, fechaS);
        statement.setString(4, valor);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Insertado correctamente !");
        }
    }

    // BUSCAR CABECERA DE FACTURA POR NOMBRE RUC
    public static CabeceraFactura buscarCabeceraFacturaPorRucOrc(String rucCliente) throws SQLException {
        CabeceraFactura cabFac = new CabeceraFactura();
        int codCliente = codigoCliente(rucCliente);
        String sql = "SELECT * FROM cabecera_factura WHERE codigo_cli=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codCliente);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String numCabeceraR = String.valueOf(result.getInt(1));
            int codClienteR = result.getInt(2);
            int codCiudadR = result.getInt(3);
            Date fechaR = result.getDate(4);
            String precioTotal = result.getString(5);

            String rucCli = rucCliente(codClienteR);
            String nomCiu = nombreCiudad(codCiudadR);
            String fechaCab = String.valueOf(fechaR);

            cabFac.setNumCabecera(numCabeceraR);
            cabFac.setCodCiudad(nomCiu);
            cabFac.setRucCliente(rucCli);
            cabFac.setFecha(fechaCab);
            cabFac.setPrecioTotal(precioTotal);
        }
        return cabFac;
    }

    public static void actualizarCabeceraFacturaOrc(String numCabecera,
            String rucCliente, String nomCiudad,
            String fecha) throws SQLException {
        System.out.println("actualizando ... ");
        int idFactura = Integer.parseInt(numCabecera);
        int codCliente = 0;
        int codCiudad = 0;
        try {
            codCliente = codigoCliente(rucCliente);
            codCiudad = codigoCiudad(nomCiudad);
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
        String valor = "0";
        String sql = "UPDATE cabecera_factura SET codigo_cli=?,codigo_ciu=?,fecha_cabecera_factu=?,valor_cabecera_facu=? "
                + "WHERE NUMERO_CABECERA_FACT_CXC=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codCliente);
        statement.setInt(2, codCiudad);
        statement.setString(3, fecha);
        statement.setString(4, valor);
        statement.setInt(5, idFactura);
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Actualizado correctamente!");
        }
    }

    public static void agregarArticuloFacturaOrc(String numCabecera, String nomArticulo, String cantidad) throws SQLException {
        int idCabecera = Integer.parseInt(numCabecera);
        int codArt = codigoArticulo(nomArticulo);
        int cant = Integer.parseInt(cantidad);
        double precioTotal = precioArticulo(nomArticulo) * cant;
        String pTotal = String.valueOf(precioTotal);

        String sql = "INSERT INTO dc_factura(NUMERO_CABECERA_FACT_CXC,CODIGO_ART,CANTIDAD_DC_FACTURA,PRECIO_DC_FACTURA) VALUES (?,?,?,?)";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, idCabecera);
        statement.setInt(2, codArt);
        statement.setString(3, cantidad);
        statement.setString(4, pTotal);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Insertado correctamente !");
        }
    }

    // BUSCAR DETALLE DE LA FACTURA
    public static DetalleFactura buscarDetalleFacturaOrc(String numFactura) throws SQLException {
        double precioTotalFactura = 0;
        int codFact = Integer.parseInt(numFactura);
        DetalleFactura detalleFac = new DetalleFactura();
        ArrayList<ItemFactura> itemsArray = new ArrayList<>();

        String sql = "SELECT * FROM dc_factura WHERE numero_cabecera_fact_cxc=?";

        PreparedStatement statement = con.prepareStatement(sql);

        statement.setInt(1, codFact);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int codArticulo = result.getInt("CODIGO_ART");
            String nomArticulo = nombreArticulo(codArticulo);
            String precio = String.valueOf(precioArticulo(nomArticulo));
            String cantArt = result.getString("CANTIDAD_DC_FACTURA");
            String precioTotalItem = result.getNString("PRECIO_DC_FACTURA");
            precioTotalFactura += Double.parseDouble(precioTotalItem);
            ItemFactura item = new ItemFactura(nomArticulo, precio, cantArt, precioTotalItem);
            itemsArray.add(item);
        }
        detalleFac.setNumCabecera(numFactura);
        detalleFac.setItemsDetalle(itemsArray);
        detalleFac.setPrecioTotal(precioTotalFactura);
        return detalleFac;
    }

    public static void crearFacturaOrcCola(String numCabecera, String rucCliente,
            String nomCiudad, String fecha, String precioFinal) throws JMSException {
        String datos = "UPDATE ..... ";
        OracleConectionFacturacion p = new OracleConectionFacturacion();
        p.processProducer("FACTURACION", datos);
    }

    // METODOS PARA COLAS
    void processProducer(String nomCola, String mensaje) throws JMSException {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            javax.jms.Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(nomCola);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage(mensaje);
            producer.send(message);
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    void processConsumer(String nomCola) {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            javax.jms.Connection connection = connectionFactory.createConnection();
            connection.setExceptionListener(this);
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(nomCola);
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(listener);
            connection.start();
            Thread.sleep(3000);
            connection.close();
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    MessageListener listener = new MessageListener() {
        public void onMessage(Message msg) {
            if (msg instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) msg;
                String text = null;
                try {
                    text = textMessage.getText();
                    //EJECUTAR SQL
                    
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                System.out.println("Recibido: " + text);
                
            } else {
                System.out.println("Recibido: " + msg);
            }
        }
    };

    //METODOS NO CONTABLES
    public static String nombreArticulo(int codArticulo) throws SQLException {
        String nombreArt = "";
        String sql = "SELECT nombre_art FROM articulo WHERE codigo_art=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codArticulo);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            nombreArt = result.getString(1);
        }
        return nombreArt;
    }

    public static double precioArticulo(String nombreArticulo) throws SQLException {
        double precio = 0;
        String precioS = "";
        String sql = "SELECT precio_art FROM articulo WHERE nombre_art=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombreArticulo);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            precioS = result.getString(1);
        }
        precio = Double.parseDouble(precioS);
        return precio;
    }

    public static int codigoArticulo(String nombreArticulo) throws SQLException {
        int codigo = 0;
        String sql = "SELECT codigo_art FROM articulo WHERE nombre_art=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombreArticulo);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            codigo = result.getInt(1);
        }
        return codigo;
    }

    public static String nombreCiudad(int codCiudad) throws SQLException {
        String nombre = "";
        String sql = "SELECT nombre_ciu FROM ciudad WHERE codigo_ciu=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codCiudad);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            nombre = result.getString(1);
        }
        return nombre;
    }

    public static int codigoCiudad(String nombreCiudad) throws SQLException {
        int codigo = 0;
        String sql = "SELECT codigo_ciu FROM ciudad WHERE nombre_ciu=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, nombreCiudad);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            codigo = result.getInt(1);
        }
        return codigo;
    }

    public static String rucCliente(int codCliente) throws SQLException {
        String ruc = "";
        String sql = "SELECT ruc_cli FROM cliente WHERE codigo_cli=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, codCliente);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            ruc = result.getString(1);
        }
        return ruc;
    }

    public static int codigoCliente(String rucCliente) throws SQLException {
        int codigo = 0;
        String sql = "SELECT codigo_cli FROM cliente WHERE ruc_cli=?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, rucCliente);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            codigo = result.getInt(1);
        }
        return codigo;
    }

    @Override
    public void onException(JMSException jmse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
