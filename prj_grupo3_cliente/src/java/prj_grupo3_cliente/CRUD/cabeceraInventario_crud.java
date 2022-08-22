/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prj_grupo3_cliente.CRUD;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import prj_grupo3_server.servicios.Articulo;
//import prj_grupo3_server.servicios.CabeceraInventario;
//import prj_grupo3_server.servicios.DetalleInventario;
import prj_grupo3_server.servicios.ItemFactura;
import prj_grupo3_server.servicios.Movimiento;
import prj_grupo3_server.servicios.ServicioServer;
import prj_grupo3_server.servicios.ServicioWebServidor;

/**
 *
 * @author lizpu
 */
@ManagedBean()
@SessionScoped
public class cabeceraInventario_crud {

    private ServicioWebServidor service = new ServicioWebServidor();
    ServicioServer port = service.getServicioServerPort();
    public String numCabecera = "";
    public String movimiento = "";
    public String fecha = "";
    public String mensajeItem = "Esperando...";
    public String mensajeCabecera = "";
    public String mensajeFactura = "";
   /* public CabeceraInventario cabeceraFactura;
    public DetalleInventario detalleFactura;
    public detalleInventario_crud df_crud = new detalleInventario_crud();
   */ //public itemFactura_crud if_crud = new itemFactura_crud();
    //public articulo_crud art_crud = new articulo_crud();
    public ArrayList<Articulo> articulos = (ArrayList<Articulo>) port.listarArticuloS();
    public ArrayList<String> listaNombreArticulo = cmbNombreArticulos();
    public ArrayList<String> listaNombreMovimientos = this.listarNombreMovimientos();
    public movimiento_crud mov_crud = new movimiento_crud();
    public ArrayList<Movimiento> movimientos = (ArrayList<Movimiento>) port.listarMovimientoS();

    public String nombreItem = "";
    public String cantidadItem = "0";
    public String precioItem = "0";
    public String precioTotalItem = "0";

    public ArrayList<String> getListaNombreMovimientos() {
        return listaNombreMovimientos;
    }

    public void setListaNombreMovimientos(ArrayList<String> listaNombreMovimientos) {
        this.listaNombreMovimientos = listaNombreMovimientos;
    }

    public void listarMovimiento() {
        try{
            movimientos = (ArrayList<Movimiento>) port.listarMovimientoS();
        }catch(Exception e){
            System.out.println(""+e.getMessage());
        }
       
    }

    public ArrayList<String> listarNombreMovimientos() {
        ArrayList<String> result = new ArrayList<>();
        this.listarMovimiento();
        try {
            for (Movimiento v : movimientos) {
                result.add(v.getNombre());
            }
        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());
        }

        return result;
    }

    public void listarArticulo() {
        articulos = (ArrayList<Articulo>) port.listarArticuloS();
    }

    public String nuevaCantidadItem() {
        int result = 0;
        String resultString = "0";
        for (Articulo a : articulos) {
            if (a.getNombreArticulo().equals(this.nombreItem)) {
                int cantidadDB = Integer.parseInt(a.getPrecioArticulo());
                int cantidadSelec = Integer.parseInt(this.cantidadItem);
                result = cantidadDB - cantidadSelec;
            }
        }
        resultString = String.valueOf(result);
        return resultString;
    }

    public ArrayList<String> cmbNombreArticulos() {
        ArrayList<String> nombresArticulos = new ArrayList<>();
        this.articulos.forEach((cli) -> {
            nombresArticulos.add(cli.getNombreArticulo());
        });
        return nombresArticulos;
    }

    public void agregarItem() {
        int resultado;
        try {
            precioItem = String.valueOf(this.obtenerPrecioItem());
            precioTotalItem = String.valueOf(this.ObtenerPrecioTotalItem());

            resultado = port.agregarProductoS(numCabecera, nombreItem, cantidadItem, precioItem, precioTotalItem);
            if (resultado == 1) {
                mensajeItem = "Articulo insertado correctamente";
            } else {
                mensajeItem = "No se pudo insertar";
            }
        } catch (Exception ex) {
            mensajeItem = "No se pudo insertar";
        }
    }

    public double obtenerPrecioItem() {
        double result = 0;
        for (Articulo a : articulos) {
            if (a.getNombreArticulo().equals(this.nombreItem)) {
                result = Double.parseDouble(a.getPrecioArticulo());
            }
        }
        return result;
    }

    public double ObtenerPrecioTotalItem() {
        double precio = Double.parseDouble(this.precioItem);
        double cantidad = Double.parseDouble(this.cantidadItem),
                precioTotal = 0;
        precioTotal = precio * cantidad;

        return precioTotal;
    }

    public void limpiarForm() {
        this.listarArticulo();
        this.listarMovimiento();
        this.movimiento = "";
        this.fecha = "";
        this.numCabecera = "";
     /*   this.df_crud.numCabecera = "";
        this.df_crud.precioTotal = 0;
        this.df_crud.itemsDetalle = new ArrayList<>();*/
        this.nombreItem = "";
        this.cantidadItem = "";
        this.precioItem = "";
        this.precioTotalItem = "";
        this.mensajeCabecera = "";
        this.mensajeFactura = "";
        this.mensajeItem = "";
    }

    /*  public void crearCabeceraInventario() {
        int resultado;
        try {
            resultado = port.crearCabeceraInventarioS(numCabecera, movimiento, fecha);
            if (resultado == 1) {
                this.limpiarForm();
                mensajeCabecera = "Creada correctamente";
            } else {
                mensajeCabecera = "No se pudo insertar";
            }
        } catch (Exception ex) {
            mensajeCabecera = "No se pudo insertar";
        }
    }

  public void eliminarCabeceraFacturaInventario() {
        int resultado;
        try {
            resultado = port.eliminarCabeceraInventarioS(numCabecera);
            if (resultado == 1) {
                this.limpiarForm();
                mensajeCabecera = "Se elimino satisfactoriamente";
            } else {
                mensajeCabecera = "No se pudo eliminar";
            }
        } catch (Exception ex) {
            mensajeCabecera = "No se pudo eliminar";
        }
    }

    public void actualizarCabeceraInventario() {
        int resultado;
        try {
            resultado = port.actualizarCabeceraInventarioS(numCabecera, movimiento, fecha);
            if (resultado == 1) {
                this.limpiarForm();
                mensajeCabecera = "Se actualizo satisfactoriamente";
            } else {
                mensajeCabecera = "No se pudo actualizar";
            }
        } catch (Exception ex) {
            mensajeCabecera = "No se pudo actualizar";
        }
    }

    public void buscarCabeceraInventario() {
        cabeceraFactura = port.buscarCabeceraInventarioS(numCabecera);
        movimiento = cabeceraFactura.getMovimiento();
        fecha = cabeceraFactura.getFecha();
        try {
            this.buscarDetalleInventario(numCabecera);
            mensajeCabecera = " Factura Cargada";
        } catch (Exception e) {
            mensajeCabecera = " No se encontro factura";
            System.out.println("ERROR NULL");
        }
    }

    public void buscarDetalleInventario(String numFactura) {
        detalleFactura = port.buscarDetalleInventarioS(numFactura);
        df_crud.numCabecera = numFactura;
        df_crud.itemsDetalle = (ArrayList<ItemFactura>) detalleFactura.getItemsDetalle();
        df_crud.precioTotal = this.precioTotalInventario(df_crud.itemsDetalle);
    }

    public double precioTotalInventario(ArrayList<ItemFactura> items) {
        double sum = 0;
        for (ItemFactura i : items) {
            sum += Double.parseDouble(i.getPrecioTotalItem());
        }
        return sum;
    }

    public movimiento_crud getMov_crud() {
        return mov_crud;
    }

    public void setMovimiento_crud(movimiento_crud mov_crud) {
        this.mov_crud = mov_crud;
    }

    public String getMensajeCabecera() {
        return mensajeCabecera;
    }

    public void setMensajeCabecera(String mensajeCabecera) {
        this.mensajeCabecera = mensajeCabecera;
    }

    public detalleInventario_crud getDf_crud() {
        return df_crud;
    }

    public String getMensajeItem() {
        return mensajeItem;
    }

    public void setMensajeItem(String mensajeItem) {
        this.mensajeItem = mensajeItem;
    }

    public void setDf_crud(detalleInventario_crud df_crud) {
        this.df_crud = df_crud;
    }
*/
    public cabeceraInventario_crud() {
    }

    public cabeceraInventario_crud(String numCabecera, String movimiento, String fecha) {
        this.numCabecera = numCabecera;
        this.movimiento = movimiento;
        this.fecha = fecha;
    }

    public ServicioWebServidor getService() {
        return service;
    }

    public void setService(ServicioWebServidor service) {
        this.service = service;
    }

    public ServicioServer getPort() {
        return port;
    }

    public void setPort(ServicioServer port) {
        this.port = port;
    }

    public String getNumCabecera() {
        return numCabecera;
    }

    public void setNumCabecera(String numCabecera) {
        this.numCabecera = numCabecera;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public String getCantidadItem() {
        return cantidadItem;
    }

    public void setCantidadItem(String cantidadItem) {
        this.cantidadItem = cantidadItem;
    }

    public String getPrecioItem() {
        return precioItem;
    }

    public void setPrecioItem(String precioItem) {
        this.precioItem = precioItem;
    }

    public String getPrecioTotalItem() {
        return precioTotalItem;
    }

    public void setPrecioTotalItem(String precioTotalItem) {
        this.precioTotalItem = precioTotalItem;
    }

    public String getMensajeFactura() {
        return mensajeFactura;
    }

    public void setMensajeFactura(String mensajeFactura) {
        this.mensajeFactura = mensajeFactura;
    }

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList<Articulo> articulos) {
        this.articulos = articulos;
    }

    public ArrayList<String> getListaNombreArticulo() {
        return listaNombreArticulo;
    }

    public void setListaNombreArticulo(ArrayList<String> listaNombreArticulo) {
        this.listaNombreArticulo = listaNombreArticulo;
    }

}
