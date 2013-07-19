
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
 * <p>Java class for educationalType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="educationalType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}interactivitytype" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}learningresourcetype" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}interactivitylevel" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}semanticdensity" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}intendedenduserrole" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}context" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}typicalagerange" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}difficulty" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}typicallearningtime" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}description" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}language" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "educationalType", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", propOrder = {
    "content"
})
public class EducationalType {

    @XmlElementRefs({
        @XmlElementRef(name = "difficulty", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class),
        @XmlElementRef(name = "typicalagerange", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class),
        @XmlElementRef(name = "typicallearningtime", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class),
        @XmlElementRef(name = "interactivitylevel", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class),
        @XmlElementRef(name = "intendedenduserrole", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class),
        @XmlElementRef(name = "language", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class),
        @XmlElementRef(name = "learningresourcetype", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class),
        @XmlElementRef(name = "interactivitytype", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class),
        @XmlElementRef(name = "semanticdensity", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class),
        @XmlElementRef(name = "description", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class),
        @XmlElementRef(name = "context", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", type = JAXBElement.class)
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
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link DifficultyType }{@code >}
     * {@link String }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link String }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link LearningresourcetypeType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link ContextType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link TypicalagerangeType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link TypicallearningtimeType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link InteractivitylevelType }{@code >}
     * {@link Object }
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link IntendedenduserroleType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link InteractivitytypeType }{@code >}
     * {@link javax.xml.bind.JAXBElement }{@code <}{@link SemanticdensityType }{@code >}
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
