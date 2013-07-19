package org.onehippo.forge.oaipmh.provider.model.oai.edurep.lom;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for lomType complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="lomType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}general" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}lifecycle" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}metametadata" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}technical" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}educational" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}rights" minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}relation" maxOccurs="unbounded"
 * minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}annotation" maxOccurs="unbounded"
 * minOccurs="0"/>
 *         &lt;element ref="{http://www.imsglobal.org/xsd/imsmd_v1p2}classification" maxOccurs="unbounded"
 * minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlRootElement(name = "lom")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lomType", namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2", propOrder = {
        "general",
        "lifecycle",
        "metametadata",
        "technical",
        "educational",
        "rights",
        "relation",
        "annotation",
        "classification"
})
public class LomType {

    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2")
    protected GeneralType general;
    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2")
    protected LifecycleType lifecycle;
    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2")
    protected MetametadataType metametadata;
    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2")
    protected TechnicalType technical;
    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2")
    protected EducationalType educational;
    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2")
    protected RightsType rights;
    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2")
    protected List<RelationType> relation;
    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2")
    protected List<AnnotationType> annotation;
    @XmlElement(namespace = "http://www.imsglobal.org/xsd/imsmd_v1p2")
    protected List<ClassificationType> classification;
    @XmlAttribute(name = "xsi:schemaLocation")
    protected String xmlns = "http://www.imsglobal.org/xsd/imsmd_v1p2 http://www.imsglobal.org/xsd/imsmd_v1p2p4.xsd";

    /**
     * Gets the value of the general property.
     *
     * @return possible object is {@link GeneralType }
     */
    public GeneralType getGeneral() {
        return general;
    }

    /**
     * Sets the value of the general property.
     *
     * @param value allowed object is {@link GeneralType }
     */
    public void setGeneral(GeneralType value) {
        this.general = value;
    }

    /**
     * Gets the value of the lifecycle property.
     *
     * @return possible object is {@link LifecycleType }
     */
    public LifecycleType getLifecycle() {
        return lifecycle;
    }

    /**
     * Sets the value of the lifecycle property.
     *
     * @param value allowed object is {@link LifecycleType }
     */
    public void setLifecycle(LifecycleType value) {
        this.lifecycle = value;
    }

    /**
     * Gets the value of the metametadata property.
     *
     * @return possible object is {@link MetametadataType }
     */
    public MetametadataType getMetametadata() {
        return metametadata;
    }

    /**
     * Sets the value of the metametadata property.
     *
     * @param value allowed object is {@link MetametadataType }
     */
    public void setMetametadata(MetametadataType value) {
        this.metametadata = value;
    }

    /**
     * Gets the value of the technical property.
     *
     * @return possible object is {@link TechnicalType }
     */
    public TechnicalType getTechnical() {
        return technical;
    }

    /**
     * Sets the value of the technical property.
     *
     * @param value allowed object is {@link TechnicalType }
     */
    public void setTechnical(TechnicalType value) {
        this.technical = value;
    }

    /**
     * Gets the value of the educational property.
     *
     * @return possible object is {@link EducationalType }
     */
    public EducationalType getEducational() {
        return educational;
    }

    /**
     * Sets the value of the educational property.
     *
     * @param value allowed object is {@link EducationalType }
     */
    public void setEducational(EducationalType value) {
        this.educational = value;
    }

    /**
     * Gets the value of the rights property.
     *
     * @return possible object is {@link RightsType }
     */
    public RightsType getRights() {
        return rights;
    }

    /**
     * Sets the value of the rights property.
     *
     * @param value allowed object is {@link RightsType }
     */
    public void setRights(RightsType value) {
        this.rights = value;
    }

    /**
     * Gets the value of the relation property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the relation property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelation().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list {@link RelationType }
     */
    public List<RelationType> getRelation() {
        if (relation == null) {
            relation = new ArrayList<RelationType>();
        }
        return this.relation;
    }

    /**
     * Gets the value of the annotation property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the annotation property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnnotation().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list {@link AnnotationType }
     */
    public List<AnnotationType> getAnnotation() {
        if (annotation == null) {
            annotation = new ArrayList<AnnotationType>();
        }
        return this.annotation;
    }

    /**
     * Gets the value of the classification property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to
     * the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE> method for
     * the classification property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClassification().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list {@link ClassificationType }
     */
    public List<ClassificationType> getClassification() {
        if (classification == null) {
            classification = new ArrayList<ClassificationType>();
        }
        return this.classification;
    }

}
