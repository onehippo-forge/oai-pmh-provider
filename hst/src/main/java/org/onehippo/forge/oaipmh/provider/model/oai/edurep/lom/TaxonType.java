
package org.onehippo.forge.oaipmh.provider.model.oai.edurep.lom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for taxonType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="taxonType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}id" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}entry" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "taxonType", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", propOrder = {
    "id",
    "entry"
})
public class TaxonType {

    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2")
    protected String id;
    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2")
    protected EntryType entry;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the entry property.
     * 
     * @return
     *     possible object is
     *     {@link EntryType }
     *     
     */
    public EntryType getEntry() {
        return entry;
    }

    /**
     * Sets the value of the entry property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntryType }
     *     
     */
    public void setEntry(EntryType value) {
        this.entry = value;
    }

}
