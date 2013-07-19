
package org.onehippo.forge.oaipmh.provider.model.oai.edurep.lom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for valueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="valueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}langstring"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "valueType", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", propOrder = {
    "langstring"
})
public class ValueType {

    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", required = true)
    protected LangstringType langstring;

    /**
     * Gets the value of the langstring property.
     * 
     * @return
     *     possible object is
     *     {@link LangstringType }
     *     
     */
    public LangstringType getLangstring() {
        return langstring;
    }

    /**
     * Sets the value of the langstring property.
     * 
     * @param value
     *     allowed object is
     *     {@link LangstringType }
     *     
     */
    public void setLangstring(LangstringType value) {
        this.langstring = value;
    }

}
