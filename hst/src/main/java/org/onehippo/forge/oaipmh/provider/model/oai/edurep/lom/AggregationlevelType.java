
package org.onehippo.forge.oaipmh.provider.model.oai.edurep.lom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for aggregationlevelType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="aggregationlevelType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}source"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}value"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "aggregationlevelType", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", propOrder = {
    "source",
    "value"
})
public class AggregationlevelType {

    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", required = true)
    protected SourceType source;
    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", required = true)
    protected ValueType value;

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link SourceType }
     *     
     */
    public SourceType getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link SourceType }
     *     
     */
    public void setSource(SourceType value) {
        this.source = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link ValueType }
     *     
     */
    public ValueType getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValueType }
     *     
     */
    public void setValue(ValueType value) {
        this.value = value;
    }

}
