package org.onehippo.forge.oaipmh.provider.model.oai.toolkit;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.onehippo.query.model package.
 * <p>An ToolkitObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ToolkitObjectFactory {
    private final static QName _Toolkit_QNAME = new QName("http://oai.dlib.vt.edu/OAI/metadata/toolkit", "toolkit");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.onehippo.query.model
     */
    public ToolkitObjectFactory() {
    }

    /**
     * Create an instance of {@link AuthorType }
     */
    public AuthorType createAuthorType() {
        return new AuthorType();
    }

    /**
     * Create an instance of {@link ToolkitType }
     */
    public ToolkitType createToolkitType() {
        return new ToolkitType();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link ToolkitType }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oai.dlib.vt.edu/OAI/metadata/toolkit", name = "toolkit")
    public JAXBElement<ToolkitType> createToolkit(ToolkitType value) {
        return new JAXBElement<ToolkitType>(_Toolkit_QNAME, ToolkitType.class, null, value);
    }

}
