
package prj_grupo3_server.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for crearCabeceraInventarioS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crearCabeceraInventarioS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo_tmov" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crearCabeceraInventarioS", propOrder = {
    "codigoTmov",
    "fecha"
})
public class CrearCabeceraInventarioS {

    @XmlElement(name = "codigo_tmov")
    protected int codigoTmov;
    protected String fecha;

    /**
     * Gets the value of the codigoTmov property.
     * 
     */
    public int getCodigoTmov() {
        return codigoTmov;
    }

    /**
     * Sets the value of the codigoTmov property.
     * 
     */
    public void setCodigoTmov(int value) {
        this.codigoTmov = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFecha(String value) {
        this.fecha = value;
    }

}
