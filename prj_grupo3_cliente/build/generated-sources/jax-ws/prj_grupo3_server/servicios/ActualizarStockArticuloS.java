
package prj_grupo3_server.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for actualizarStockArticuloS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="actualizarStockArticuloS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nomArticulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nuevoStock" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actualizarStockArticuloS", propOrder = {
    "nomArticulo",
    "nuevoStock"
})
public class ActualizarStockArticuloS {

    protected String nomArticulo;
    protected String nuevoStock;

    /**
     * Gets the value of the nomArticulo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomArticulo() {
        return nomArticulo;
    }

    /**
     * Sets the value of the nomArticulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomArticulo(String value) {
        this.nomArticulo = value;
    }

    /**
     * Gets the value of the nuevoStock property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNuevoStock() {
        return nuevoStock;
    }

    /**
     * Sets the value of the nuevoStock property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNuevoStock(String value) {
        this.nuevoStock = value;
    }

}
