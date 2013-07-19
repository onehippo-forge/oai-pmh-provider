
package org.onehippo.forge.oaipmh.provider.model.oai.edurep.lom;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for titleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="titleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}langstring" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "titleType", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", propOrder = {
    "langstring"
})
public class TitleType {

    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", required = true)
    protected List<LangstringType> langstring;

    /**
     * Gets the value of the langstring property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the langstring property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLangstring().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LangstringType }
     * 
     * 
     */
    public List<LangstringType> getLangstring() {
        if (langstring == null) {
            langstring = new ArrayList<LangstringType>();
        }
        return this.langstring;
    }

}
