
package org.onehippo.forge.oaipmh.provider.model.oai.edurep.lom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for centityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="centityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}vcard"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "centityType", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", propOrder = {
    "vcard"
})
public class CentityType {

    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", required = true)
    protected String vcard;

    /**
     * Gets the value of the vcard property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVcard() {
        return vcard;
    }

    /**
     * Sets the value of the vcard property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVcard(String value) {
        this.vcard = value;
    }

}
