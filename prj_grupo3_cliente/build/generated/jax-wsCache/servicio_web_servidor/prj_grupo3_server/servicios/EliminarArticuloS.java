
package prj_grupo3_server.servicios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para eliminarArticuloS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="eliminarArticuloS"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Codigo_Articulo" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eliminarArticuloS", propOrder = {
    "codigoArticulo"
})
public class EliminarArticuloS {

    @XmlElement(name = "Codigo_Articulo")
    protected int codigoArticulo;

    /**
     * Obtiene el valor de la propiedad codigoArticulo.
     * 
     */
    public int getCodigoArticulo() {
        return codigoArticulo;
    }

    /**
     * Define el valor de la propiedad codigoArticulo.
     * 
     */
    public void setCodigoArticulo(int value) {
        this.codigoArticulo = value;
    }

}
