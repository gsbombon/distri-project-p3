
package prj_grupo3_server.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for buscarArticuloS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="buscarArticuloS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Codigo_Articulo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buscarArticuloS", propOrder = {
    "codigoArticulo"
})
public class BuscarArticuloS {

    @XmlElement(name = "Codigo_Articulo")
    protected int codigoArticulo;

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

}
