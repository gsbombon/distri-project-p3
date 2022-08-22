
package prj_grupo3_server.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for actualizarArticuloS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="actualizarArticuloS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Codigo_Articulo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Nombre_Articulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Precio_Articulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PStock_Articulo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actualizarArticuloS", propOrder = {
    "codigoArticulo",
    "nombreArticulo",
    "precioArticulo",
    "pStockArticulo"
})
public class ActualizarArticuloS {

    @XmlElement(name = "Codigo_Articulo")
    protected int codigoArticulo;
    @XmlElement(name = "Nombre_Articulo")
    protected String nombreArticulo;
    @XmlElement(name = "Precio_Articulo")
    protected String precioArticulo;
    @XmlElement(name = "PStock_Articulo")
    protected int pStockArticulo;

    /**
     * Gets the value of the codigoArticulo property.
     * 
     */
    public int getCodigoArticulo() {
        return codigoArticulo;
    }

    /**
     * Sets the value of the codigoArticulo property.
     * 
     */
    public void setCodigoArticulo(int value) {
        this.codigoArticulo = value;
    }

    /**
     * Gets the value of the nombreArticulo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreArticulo() {
        return nombreArticulo;
    }

    /**
     * Sets the value of the nombreArticulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreArticulo(String value) {
        this.nombreArticulo = value;
    }

    /**
     * Gets the value of the precioArticulo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrecioArticulo() {
        return precioArticulo;
    }

    /**
     * Sets the value of the precioArticulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrecioArticulo(String value) {
        this.precioArticulo = value;
    }

    /**
     * Gets the value of the pStockArticulo property.
     * 
     */
    public int getPStockArticulo() {
        return pStockArticulo;
    }

    /**
     * Sets the value of the pStockArticulo property.
     * 
     */
    public void setPStockArticulo(int value) {
        this.pStockArticulo = value;
    }

}
