package org.onehippo.forge.beans;

import java.util.Calendar;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoHtmlAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoGalleryImageAdapter;
import org.onehippo.forge.oaipmh.provider.api.OAIBean;

@XmlRootElement(name = "newsdocument")
@XmlAccessorType(XmlAccessType.NONE)
@HippoEssentialsGenerated(internalName = "oaipmhdemo:newsdocument")
@Node(jcrType = "oaipmhdemo:newsdocument")
public class NewsDocument extends HippoDocument implements OAIBean {
    /** 
     * The document type of the news document.
     */
    public static final String DOCUMENT_TYPE = "oaipmhdemo:newsdocument";
    private static final String TITLE = "oaipmhdemo:title";
    private static final String DATE = "oaipmhdemo:date";
    private static final String INTRODUCTION = "oaipmhdemo:introduction";
    private static final String IMAGE = "oaipmhdemo:image";
    private static final String CONTENT = "oaipmhdemo:content";
    private static final String LOCATION = "oaipmhdemo:location";
    private static final String AUTHOR = "oaipmhdemo:author";
    private static final String SOURCE = "oaipmhdemo:source";

    /** 
     * Get the title of the document.
     * @return the title
     */
    @XmlElement
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:title")
    public String getTitle() {
        return getProperty(TITLE);
    }

    /** 
     * Get the date of the document.
     * @return the date
     */
    @XmlElement
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:date")
    public Calendar getDate() {
        return getProperty(DATE);
    }

    /** 
     * Get the introduction of the document.
     * @return the introduction
     */
    @XmlElement
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:introduction")
    public String getIntroduction() {
        return getProperty(INTRODUCTION);
    }

    /** 
     * Get the image of the document.
     * @return the image
     */
    @XmlJavaTypeAdapter(HippoGalleryImageAdapter.class)
    @XmlElement
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:image")
    public HippoGalleryImageSet getImage() {
        return getLinkedBean(IMAGE, HippoGalleryImageSet.class);
    }

    /** 
     * Get the main content of the document.
     * @return the content
     */
    @XmlJavaTypeAdapter(HippoHtmlAdapter.class)
    @XmlElement
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:content")
    public HippoHtml getContent() {
        return getHippoHtml(CONTENT);
    }

    /** 
     * Get the location of the document.
     * @return the location
     */
    @XmlElement
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:location")
    public String getLocation() {
        return getProperty(LOCATION);
    }

    /** 
     * Get the author of the document.
     * @return the author
     */
    @XmlElement
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:author")
    public String getAuthor() {
        return getProperty(AUTHOR);
    }

    /** 
     * Get the source of the document.
     * @return the source
     */
    @XmlElement
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:source")
    public String getSource() {
        return getProperty(SOURCE);
    }

    @Override
    public Calendar getPublicationDate() {
        return getProperty("hippostdpubwf:publicationDate");
    }
}
