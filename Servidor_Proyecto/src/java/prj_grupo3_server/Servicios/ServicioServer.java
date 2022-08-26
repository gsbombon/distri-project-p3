package prj_grupo3_server.Servicios;

import java.sql.SQLException;

import static prj_grupo3_server.Conexion.Conexion.Conectar;

import static prj_grupo3_server.Conexion.Conexion.actualizarCobrador;
import static prj_grupo3_server.Conexion.Conexion.buscarCobrador;
import static prj_grupo3_server.Conexion.Conexion.buscarFormaPago;
import static prj_grupo3_server.Conexion.Conexion.eliminarCobrador;
import static prj_grupo3_server.Conexion.Conexion.eliminarFormaPago;
import static prj_grupo3_server.Conexion.Conexion.insertarCobrador;
import static prj_grupo3_server.Conexion.Conexion.insertarFormaPago;
import static prj_grupo3_server.Conexion.Conexion.listarCobrador;
import static prj_grupo3_server.Conexion.Conexion.listarFormaPago;
import prj_grupo3_server.Modelo.Ciudad;
import prj_grupo3_server.Modelo.Cliente;
import prj_grupo3_server.Modelo.Cobrador;
import prj_grupo3_server.Modelo.FormaPago;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import static prj_grupo3_server.Conexion.Conexion.actualizarStockArticulo;
import static prj_grupo3_server.Conexion.Conexion.agregarPaga;
import static prj_grupo3_server.Conexion.Conexion.agregarProducto;
import static prj_grupo3_server.Conexion.Conexion.buscarCabeceraFactura;
import static prj_grupo3_server.Conexion.Conexion.buscarCabeceraFacturaPorRuc;
import static prj_grupo3_server.Conexion.Conexion.buscarDetalleFactura;
import static prj_grupo3_server.Conexion.Conexion.buscarDetalleFacturacxc;
import static prj_grupo3_server.Conexion.Conexion.buscarFactura;
import static prj_grupo3_server.Conexion.Conexion.crearDetalleFactura;
import static prj_grupo3_server.Conexion.Conexion.crearDetalleFacturacxc;
import static prj_grupo3_server.Conexion.Conexion.crearFactura;
import static prj_grupo3_server.Conexion.Conexion.eliminarCabeceraFactura;
import static prj_grupo3_server.Conexion.Conexion.eliminarDetalleFactura;
import static prj_grupo3_server.Conexion.Conexion.eliminarDetalleFacturacxc;
import static prj_grupo3_server.Conexion.Conexion.eliminarFactura;
import static prj_grupo3_server.Conexion.Conexion.listarFactura;
import static prj_grupo3_server.Conexion.Conexion.singIn;
import static prj_grupo3_server.Conexion.OracleConection.ConectarO;
import static prj_grupo3_server.Conexion.OracleConection.actualizarArticuloOrc;
import static prj_grupo3_server.Conexion.OracleConection.actualizarMovimientoOrc;
import static prj_grupo3_server.Conexion.OracleConection.buscarArticuloNOrc;
import static prj_grupo3_server.Conexion.OracleConection.buscarArticuloOrc;
import static prj_grupo3_server.Conexion.OracleConection.buscarMovimientoNOrc;
import static prj_grupo3_server.Conexion.OracleConection.buscarMovimientoOrc;
import static prj_grupo3_server.Conexion.OracleConection.crearCabeceraInventarioOrc;
import static prj_grupo3_server.Conexion.OracleConection.eliminarArticuloOrc;
import static prj_grupo3_server.Conexion.OracleConection.eliminarMovimientoOrc;
import static prj_grupo3_server.Conexion.OracleConection.insertarArticuloOrc;
import static prj_grupo3_server.Conexion.OracleConection.insertarMovimientoOrc;
import static prj_grupo3_server.Conexion.OracleConection.insertarUsuarioOrc;
import static prj_grupo3_server.Conexion.OracleConection.listarArticuloOrc;
import static prj_grupo3_server.Conexion.OracleConection.listarMovimientoOrc;
import static prj_grupo3_server.Conexion.OracleConection.loginOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.ConectarOF;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.actualizarCabeceraFacturaOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.actualizarCiudadOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.actualizarClienteOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.agregarArticuloFacturaOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.buscarCabeceraFacturaPorRucOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.buscarCiudadOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.buscarClienteOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.buscarDetalleFacturaOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.cargarColaInventarioOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.crearCabeceraFacturaOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.crearFacturaOrcCola;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.eliminarCabeceraFacturaOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.eliminarCiudadOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.eliminarClienteOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.eliminarDetalleFacturaOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.insertarCiudadOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.insertarClienteOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.listarCiudadOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.listarClienteOrc;
import static prj_grupo3_server.Conexion.OracleConectionFacturacion.listarFacturaOrc;
import prj_grupo3_server.Modelo.Articulo;
import prj_grupo3_server.Modelo.CabeceraFactura;
import prj_grupo3_server.Modelo.DetalleFactura;
import prj_grupo3_server.Modelo.DetalleFacturacxc;
import prj_grupo3_server.Modelo.Factura;
import prj_grupo3_server.Modelo.ItemFactura;
import prj_grupo3_server.Modelo.ItemFacturacxc;
import prj_grupo3_server.Modelo.Movimiento;

@WebService(serviceName = "servicio_web_servidor")
public class ServicioServer {

    @WebMethod(operationName = "SingIn")
    public int SingIn(@WebParam(name = "user") String user, @WebParam(name = "pass") String pass) {
        System.out.println("1");
        try {
            ConectarO();
            singIn(user, pass);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "insertarCiudadS")
    public int insertarCiudadS(@WebParam(name = "Nombre_Ciudad") String Nombre_Ciudad) {
        try {
            ConectarOF();
            insertarCiudadOrc(Nombre_Ciudad);
            return 1;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return 2;
        }
    }

    @WebMethod(operationName = "insertarClienteS")
    public int insertarClienteS(@WebParam(name = "Ruc_Cliente") String Ruc_Cliente, @WebParam(name = "Nombre_Cliente") String Nombre_Cliente, @WebParam(name = "Direccion_Cliente") String Direccion_Cliente) {
        System.out.println("1");
        try {
            ConectarOF();
            insertarClienteOrc(Ruc_Cliente, Nombre_Cliente, Direccion_Cliente);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "actualizarCiudadS")
    public int actualizarCiudadS(@WebParam(name = "Codigo_Ciudad") String Codigo_Ciudad, @WebParam(name = "Nombre_Ciudad") String Nombre_Ciudad) {
        try {
            ConectarOF();
            actualizarCiudadOrc(Codigo_Ciudad, Nombre_Ciudad);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "actualizarClienteS")
    public int actualizarClienteS(@WebParam(name = "Ruc_Cliente") String Ruc_Cliente, @WebParam(name = "Nombre_Cliente") String Nombre_Cliente, @WebParam(name = "Direccion_Cliente") String Direccion_Cliente) {
        try {
            ConectarOF();
            actualizarClienteOrc(Ruc_Cliente, Nombre_Cliente, Direccion_Cliente);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "eliminarCiudadS")
    public int eliminarCiudadS(@WebParam(name = "Codigo_Ciudad") String Codigo_Ciudad) {
        try {
            ConectarOF();
            int cod = Integer.parseInt(Codigo_Ciudad);
            eliminarCiudadOrc(cod);
            return 1;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return 2;
        }
    }

    @WebMethod(operationName = "eliminarClienteS")
    public int eliminarClienteS(@WebParam(name = "Ruc_Cliente") String Ruc_Cliente) {
        try {
            ConectarOF();
            eliminarClienteOrc(Ruc_Cliente);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "listarCiudadS")
    public ArrayList<Ciudad> listarCiudadS() {
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        try {
            ConectarOF();
            ciudades = listarCiudadOrc();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
        return ciudades;
    }

    @WebMethod(operationName = "listarClienteS")
    public ArrayList<Cliente> listarClienteS() {

        ConectarOF();
        ArrayList<Cliente> clientess = new ArrayList<>();
        try {
            clientess = listarClienteOrc();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
        return clientess;
    }

    @WebMethod(operationName = "buscarCiudadS")
    public Ciudad buscarCiudadS(@WebParam(name = "Codigo_Ciudad") String Codigo_Ciudad) {
        ConectarOF();
        Ciudad ciudadB = new Ciudad();
        try {
            int cod_ciu = Integer.parseInt(Codigo_Ciudad);
            ciudadB = buscarCiudadOrc(cod_ciu);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ciudadB;
    }

    @WebMethod(operationName = "buscarClienteS")
    public Cliente buscarClienteS(@WebParam(name = "Ruc_Cliente") String Ruc_Cliente) {
        ConectarOF();
        Cliente cli = new Cliente();
        try {
            cli = buscarClienteOrc(Ruc_Cliente);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cli;
    }
//***********COBRADOR************

    @WebMethod(operationName = "insertarCobradorS")
    public int insertarCobradorS(@WebParam(name = "Cedula_Cobrador") String Cedula_Cobrador, @WebParam(name = "Nombre_Cobrador") String Nombre_Cobrador, @WebParam(name = "Direccion_Cobrador") String Direccion_Cobrador) {
        System.out.println("1");
        try {
            Conectar();
            insertarCobrador(Cedula_Cobrador, Nombre_Cobrador, Direccion_Cobrador);

            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "actualizarCobradorS")
    public int actualizarCobradorS(@WebParam(name = "Cedula_Cobrador") String Cedula_Cobrador, @WebParam(name = "Nombre_Cobrador") String Nombre_Cobrador, @WebParam(name = "Direccion_Cobrador") String Direccion_Cobrador) {
        try {
            Conectar();
            actualizarCobrador(Cedula_Cobrador, Nombre_Cobrador, Direccion_Cobrador);

            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "eliminarCobradorS")
    public int eliminarCobradorS(@WebParam(name = "Cedula_Cobrador") String Cedula_Cobrador) {
        try {
            Conectar();
            eliminarCobrador(Cedula_Cobrador);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "listarCobradorS")
    public ArrayList<Cobrador> listarCobradorS() {

        Conectar();
        ArrayList<Cobrador> cobradorr = new ArrayList<>();
        cobradorr = listarCobrador();
        return cobradorr;
    }

    @WebMethod(operationName = "buscarCobradorS")
    public Cobrador buscarCobradorS(@WebParam(name = "Cedula_Cobrador") String Cedula_Cobrador) {

        Conectar();
        Cobrador cobradorr = new Cobrador();
        cobradorr = buscarCobrador(Cedula_Cobrador);
        return cobradorr;
    }

    //***********FORMA PAGO************
    @WebMethod(operationName = "insertarFormaPagoS")
    public int insertarFormaPagoS(@WebParam(name = "Codigo_FP") String Codigo_FP, @WebParam(name = "Nombre_FP") String Nombre_FP) {
        System.out.println("1");
        try {
            Conectar();
            insertarFormaPago(Codigo_FP, Nombre_FP);

            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "actualizarFormaPagoS")
    public int actualizarFormaPagoS(@WebParam(name = "Codigo_FP") String Codigo_FP, @WebParam(name = "Nombre_FP") String Nombre_FP) {
        try {
            Conectar();
            actualizarFormaPago(Codigo_FP, Nombre_FP);

            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "eliminarFormaPagoS")
    public int eliminarFormaPagoS(@WebParam(name = "Codigo_FP") String Codigo_FP) {
        try {
            Conectar();
            eliminarFormaPago(Codigo_FP);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "listarFormaPagoS")
    public ArrayList<FormaPago> listarFormaPagoS() {

        Conectar();
        ArrayList<FormaPago> formapago = new ArrayList<>();
        formapago = listarFormaPago();
        return formapago;
    }

    @WebMethod(operationName = "buscarFormaPagoS")
    public FormaPago buscarFormaPagoS(@WebParam(name = "Codigo_FP") String Codigo_FP) {

        Conectar();
        FormaPago formapago = new FormaPago();
        formapago = buscarFormaPago(Codigo_FP);
        return formapago;
    }

    private void actualizarFormaPago(String Codigo_FP, String Nombre_FP) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*----------CRUD ARTICULOS------------*/
    @WebMethod(operationName = "insertarArticuloS")
    public int insertarArticuloS(@WebParam(name = "Nombre_Articulo") String nombre,
            @WebParam(name = "Precio_Articulo") String precio,
            @WebParam(name = "Stock_Articulo") int cantidad) {
        System.out.println("1");
        try {
            ConectarO();
            insertarArticuloOrc(nombre, precio, cantidad);
            return 1;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return 2;
        }
    }

    @WebMethod(operationName = "actualizarArticuloS")
    public int actualizarArticuloS(@WebParam(name = "Codigo_Articulo") int codigo, @WebParam(name = "Nombre_Articulo") String nombre, @WebParam(name = "Precio_Articulo") String precio, @WebParam(name = "PStock_Articulo") int cantidad) {
        try {
            ConectarO();
            actualizarArticuloOrc(codigo, nombre, precio, cantidad);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "eliminarArticuloS")
    public int eliminarArticuloS(@WebParam(name = "Codigo_Articulo") int codigo) {
        try {
            ConectarO();
            eliminarArticuloOrc(codigo);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "listarArticuloS")
    public ArrayList<Articulo> listarArticuloS() {

        ConectarO();
        ArrayList<Articulo> art = new ArrayList<>();
        try {
            art = listarArticuloOrc();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
        return art;
    }

    @WebMethod(operationName = "buscarArticuloS")
    public Articulo buscarArticuloS(@WebParam(name = "Codigo_Articulo") int codigo) {

        ConectarO();
        Articulo art = new Articulo();
        try {
            art = buscarArticuloOrc(codigo);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return art;
    }

    @WebMethod(operationName = "buscarArticuloSN")
    public Articulo buscarArticuloSN(@WebParam(name = "Nombre_Articulo") String nombre) {

        ConectarO();
        Articulo art = new Articulo();
        try {
            art = buscarArticuloNOrc(nombre);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return art;
    }

    /*----------CRUD TIPO MOVIMIENTO------------*/
    @WebMethod(operationName = "insertarMovimientoS")
    public int insertarMovimientoS(@WebParam(name = "nombre") String nombre, @WebParam(name = "signo") String signo) {
        System.out.println("1");
        try {
            ConectarO();
            insertarMovimientoOrc(nombre, signo);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "actualizarMovimientoS")
    public int actualizarMovimientoS(@WebParam(name = "codigo") int codigo, @WebParam(name = "nombre") String nombre, @WebParam(name = "signo") String signo) {
        try {
            ConectarO();
            actualizarMovimientoOrc(codigo, nombre, signo);

            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "eliminarMovimientoS")
    public int eliminarMovimientoS(@WebParam(name = "codigo") int codigo) {
        try {
            ConectarO();
            eliminarMovimientoOrc(codigo);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "listarMovimientoS")
    public ArrayList<Movimiento> listarMovimientoS() {
        ConectarO();
        ArrayList<Movimiento> mov = new ArrayList<>();
        try {
            mov = listarMovimientoOrc();
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
        return mov;
    }

    @WebMethod(operationName = "buscarMovimientoS")
    public Movimiento buscarMovimientoS(@WebParam(name = "codigo") int codigo) {

        ConectarO();
        Movimiento mov = new Movimiento();
        try {
            mov = buscarMovimientoOrc(codigo);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mov;
    }

    @WebMethod(operationName = "buscarMovimientoSN")
    public Movimiento buscarMovimientoSN(@WebParam(name = "nombre") String nombre) {

        ConectarO();
        Movimiento mov = new Movimiento();
        try {
            mov = buscarMovimientoNOrc(nombre);
        } catch (SQLException ex) {
            Logger.getLogger(ServicioServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mov;
    }

    @WebMethod(operationName = "crearCabeceraInventarioS")
    public int crearCabeceraInventarioS(@WebParam(name = "codigo_tmov") int movimiento,
            @WebParam(name = "fecha") String fecha) {
        try {
            ConectarO();
            crearCabeceraInventarioOrc(movimiento, fecha);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "crearCabeceraFacturaS")
    public int crearCabeceraFacturaS(@WebParam(name = "rucCliente") String rucCliente,
            @WebParam(name = "fecha") String fecha, @WebParam(name = "codCiudad") String codCiudad) {
        try {
            ConectarOF();
            crearCabeceraFacturaOrc(rucCliente, codCiudad, fecha);
            return 1;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return 2;
        }
    }

    @WebMethod(operationName = "crearDetalleFacturaS")
    public int crearDetalleFacturaS(@WebParam(name = "numFactura") String numFactura) {
        try {
            Conectar();
            crearDetalleFactura(numFactura);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "crearDetalleFacturacxcS")
    public int crearDetalleFacturacxcS(@WebParam(name = "numFactura") String numFactura) {
        try {
            Conectar();
            crearDetalleFacturacxc(numFactura);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "eliminarDetalleFacturaS")
    public int eliminarDetalleFacturaS(@WebParam(name = "numFactura") String numFactura) {
        try {
            ConectarOF();
            eliminarDetalleFacturaOrc(numFactura);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "eliminarDetalleFacturacxcS")
    public int eliminarDetalleFacturacxcS(@WebParam(name = "numFactura") String numFactura) {
        try {
            Conectar();
            eliminarDetalleFacturacxc(numFactura);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "actualizarCabeceraFacturaOrcS")
    public int actualizarCabeceraFacturaOrcS(@WebParam(name = "numFactura") String numFactura, @WebParam(name = "rucCliente") String rucCliente,
            @WebParam(name = "fecha") String fecha, @WebParam(name = "codCiudad") String codCiudad) {
        try {
            ConectarOF();
            actualizarCabeceraFacturaOrc(numFactura, rucCliente, codCiudad, fecha);
            return 1;
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
            return 2;
        }
    }

    @WebMethod(operationName = "eliminarCabeceraFacturaS")
    public int eliminarCabeceraFacturaS(@WebParam(name = "numCabecera") String numCabecera) {
        try {
            ConectarOF();
            eliminarDetalleFacturaOrc(numCabecera);
            eliminarCabeceraFacturaOrc(numCabecera);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "buscarCabeceraFacturaS")
    public CabeceraFactura buscarCabeceraFacturaS(@WebParam(name = "numCabecera") String numCabecera) {
        ConectarOF();
        CabeceraFactura cf = new CabeceraFactura();
        cf = buscarCabeceraFactura(numCabecera);
        return cf;
    }

    @WebMethod(operationName = "buscarFacturaS")
    public Factura buscarFacturaS(@WebParam(name = "numCabecera") String numCabecera) {
        Conectar();
        Factura cf = new Factura();
        cf = buscarFactura(numCabecera);
        return cf;
    }

    @WebMethod(operationName = "buscarCabeceraFacturaPorRucS")
    public CabeceraFactura buscarCabeceraFacturaPorRucS(@WebParam(name = "rucCliente") String rucCliente) {
        ConectarOF();
        CabeceraFactura cf = new CabeceraFactura();
        try {
            cf = buscarCabeceraFacturaPorRucOrc(rucCliente);
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
        return cf;
    }

    @WebMethod(operationName = "buscarDetalleFacturaS")
    public DetalleFactura buscarDetalleFacturaOrcS(@WebParam(name = "numCabecera") String numCabecera) {
        ConectarOF();
        DetalleFactura detalleFac = new DetalleFactura();
        try {
            detalleFac = buscarDetalleFacturaOrc(numCabecera);
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
        return detalleFac;
    }

    @WebMethod(operationName = "buscarDetalleFacturacxcS")
    public DetalleFacturacxc buscarDetalleFacturacxcS(@WebParam(name = "numCabecera") String numCabecera) {
        Conectar();
        DetalleFacturacxc detalleFac = new DetalleFacturacxc();
        detalleFac = buscarDetalleFacturacxc(numCabecera);
        return detalleFac;
    }

    @WebMethod(operationName = "crearFacturaS")
    public int crearFacturaS(@WebParam(name = "numFactura") String numFactura, @WebParam(name = "rucCliente") String rucCliente,
            @WebParam(name = "fecha") String fecha,
            @WebParam(name = "codCiudad") String codCiudad,
            @WebParam(name = "precioFinal") String precioFinal) {
        try {
            ConectarOF();
            crearFacturaOrcCola(numFactura, rucCliente, codCiudad, fecha, precioFinal);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "agregarProductoS")
    public int agregarProductoS(@WebParam(name = "numFactura") String numFactura,
            @WebParam(name = "nombreItem") String nombreItem,
            @WebParam(name = "cantidadItem") String cantidadItem,
            @WebParam(name = "precioItem") String precioItem,
            @WebParam(name = "precioTotalItem") String precioTotalItem) {
        try {
            Conectar();
            ItemFactura item = new ItemFactura(nombreItem, precioItem, cantidadItem, precioTotalItem);
            agregarProducto(numFactura, item);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "agregarArticuloFacturaOrcS")
    public int agregarArticuloFacturaOrcS(@WebParam(name = "numFactura") String numFactura,
            @WebParam(name = "nombreItem") String nombreItem,
            @WebParam(name = "cantidadItem") String cantidadItem) {
        try {
            ConectarOF();
            agregarArticuloFacturaOrc(numFactura, nombreItem, cantidadItem);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "agregarPagaS")
    public int agregarPagaS(@WebParam(name = "numFactura") String numFactura,
            @WebParam(name = "fechapagoItem") String fechapagoItem,
            @WebParam(name = "formapagoItem") String formapagoItem,
            @WebParam(name = "valorpagarItem") String valorpagarItem,
            @WebParam(name = "cobradorItem") String cobradorItem) {
        try {
            Conectar();
            ItemFacturacxc item = new ItemFacturacxc(formapagoItem, cobradorItem, valorpagarItem, fechapagoItem);
            agregarPaga(numFactura, item);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "loginS")
    public int loginS(@WebParam(name = "Usuario") String Usuario, @WebParam(name = "Contrasena") String Contrasena) {
        ConectarO();
        int op = 0;
        try {
            op = loginOrc(Usuario, Contrasena);
        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }
        return op;
    }

    @WebMethod(operationName = "crearUsuarioS")
    public int crearUsuarioS(@WebParam(name = "user") String user, @WebParam(name = "pass") String pass) {
        try {
            ConectarO();
            insertarUsuarioOrc(user, pass);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "cargarColaInventarioOrcS")
    public int cargarColaInventarioOrcS() {
        try {
            ConectarOF();
            cargarColaInventarioOrc();
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @WebMethod(operationName = "listarFacturasOrcS")
    public ArrayList<Factura> listarFacturasOrcS() {       
        ArrayList<Factura> art = new ArrayList<>();
        try {
            ConectarOF();
            art = listarFacturaOrc();
        } catch (SQLException ex) {
            System.out.println(""+ex.getMessage());
        }
        return art;
    }

    @WebMethod(operationName = "actualizarStockArticuloS")
    public int actualizarStockArticuloS(@WebParam(name = "nomArticulo") String nomArticulo,
            @WebParam(name = "nuevoStock") String nuevoStock) {
        try {
            Conectar();
            actualizarStockArticulo(nomArticulo, nuevoStock);
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

}
