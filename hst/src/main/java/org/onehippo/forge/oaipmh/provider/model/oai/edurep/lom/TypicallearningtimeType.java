
package org.onehippo.forge.oaipmh.provider.model.oai.edurep.lom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for typicallearningtimeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="typicallearningtimeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}datetime" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}description" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "typicallearningtimeType", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", propOrder = {
    "datetime",
    "description"
})
public class TypicallearningtimeType {

    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2")
    protected String datetime;
    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2")
    protected DescriptionType description;

    /**
     * Gets the value of the datetime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * Sets the value of the datetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatetime(String value) {
        this.datetime = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link DescriptionType }
     *     
     */
    public DescriptionType getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptionType }
     *     
     */
    public void setDescription(DescriptionType value) {
        this.description = value;
    }

}
