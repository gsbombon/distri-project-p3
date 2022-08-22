/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prj_grupo3_server.Modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author lizpu
 */
@Entity
public class CabeceraInventario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int numCabecera;
    private int movimiento;
    private String fecha;

    public CabeceraInventario(int numCabecera, int movimiento, String fecha) {
        this.numCabecera = numCabecera;
        this.movimiento = movimiento;
        this.fecha = fecha;
    }

    public CabeceraInventario() {
    }
    
    public int getNumCabecera() {
        return numCabecera;
    }

    public void setNumCabecera(int numCabecera) {
        this.numCabecera = numCabecera;
    }

    public int getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(int movimiento) {
        this.movimiento = movimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
    
}
