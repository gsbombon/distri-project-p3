
package prj_grupo3_server.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para agregarArticuloFacturaOrcS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="agregarArticuloFacturaOrcS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="numFactura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombreItem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="cantidadItem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "agregarArticuloFacturaOrcS", propOrder = {
    "numFactura",
    "nombreItem",
    "cantidadItem"
})
public class AgregarArticuloFacturaOrcS {

    protected String numFactura;
    protected String nombreItem;
    protected String cantidadItem;

    /**
     * Obtiene el valor de la propiedad numFactura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumFactura() {
        return numFactura;
    }

    /**
     * Define el valor de la propiedad numFactura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumFactura(String value) {
        this.numFactura = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreItem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreItem() {
        return nombreItem;
    }

    /**
     * Define el valor de la propiedad nombreItem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreItem(String value) {
        this.nombreItem = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadItem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCantidadItem() {
        return cantidadItem;
    }

    /**
     * Define el valor de la propiedad cantidadItem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCantidadItem(String value) {
        this.cantidadItem = value;
    }

}
