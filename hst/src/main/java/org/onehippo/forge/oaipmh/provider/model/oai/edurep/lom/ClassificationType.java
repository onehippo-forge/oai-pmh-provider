
package org.onehippo.forge.oaipmh.provider.model.oai.edurep.lom;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for classificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="classificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}purpose" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}taxonpath" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}description" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}keyword" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;group ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}grp.any"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "classificationType", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", propOrder = {
    "content"
})
public class ClassificationType {

    @XmlElementRefs({
        @XmlElementRef(name = "taxonpath", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class),
        @XmlElementRef(name = "keyword", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class),
        @XmlElementRef(name = "purpose", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class),
        @XmlElementRef(name = "description", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class)
    })
    @XmlMixed
    @XmlAnyElement(lax = true)
    protected List<Object> content;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * {@link Object }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link TaxonpathType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link PurposeType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link KeywordType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link DescriptionType }{@code >}
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

}
