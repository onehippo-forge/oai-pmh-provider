package org.onehippo.forge.beans;

import java.util.Calendar;

import javax.xml.bind.JAXBElement;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.forge.oaipmh.provider.api.OAI;
import org.onehippo.forge.oaipmh.provider.api.OAIBean;
import org.onehippo.forge.oaipmh.provider.api.OAIDelegate;
import org.onehippo.forge.oaipmh.provider.api.OAIIdentifier;
import org.onehippo.forge.oaipmh.provider.api.RestContext;
import org.onehippo.forge.oaipmh.provider.model.oai.dc.ElementType;
import org.onehippo.forge.oaipmh.provider.model.oai.dc.OaiDcType;
import org.onehippo.forge.oaipmh.provider.model.oai.dc.ObjectFactory;
import org.onehippo.forge.oaipmh.provider.model.oai.edurep.lom.LomType;
import org.onehippo.forge.oaipmh.provider.model.oai.edurep.lom.NLLom;

@OAI(setName = "NewsDocument")
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

    private static final ObjectFactory objectFactory = new ObjectFactory();

    /** 
     * Get the title of the document.
     * @return the title
     */
    public String getTitle() {
        return getProperty(TITLE);
    }

    /** 
     * Get the date of the document.
     * @return the date
     */
    public Calendar getDate() {
        return getProperty(DATE);
    }

    /** 
     * Get the introduction of the document.
     * @return the introduction
     */
    public String getIntroduction() {
        return getProperty(INTRODUCTION);
    }

    /** 
     * Get the image of the document.
     * @return the image
     */
    public HippoGalleryImageSet getImage() {
        return getLinkedBean(IMAGE, HippoGalleryImageSet.class);
    }

    /** 
     * Get the main content of the document.
     * @return the content
     */
    public HippoHtml getContent() {
        return getHippoHtml(CONTENT);
    }

    /** 
     * Get the location of the document.
     * @return the location
     */
    public String getLocation() {
        return getProperty(LOCATION);
    }

    /** 
     * Get the author of the document.
     * @return the author
     */
    public String getAuthor() {
        return getProperty(AUTHOR);
    }

    /** 
     * Get the source of the document.
     * @return the source
     */
    public String getSource() {
        return getProperty(SOURCE);
    }

    @Override
    public Calendar getPublicationDate() {
        return getProperty("hippostdpubwf:publicationDate");
    }

    /**
     * OAI PMH Examples dublin core + NL LOM for edurep
     */
    @OAIDelegate(metadataPrefix = "oai_dc")
    public OaiDcType dublinCore(RestContext context){
        OaiDcType type = objectFactory.createOaiDcType();
        ElementType titleElement = objectFactory.createElementType();
        titleElement.setValue(getTitle());
        final JAXBElement<ElementType> title = objectFactory.createTitle(titleElement);
        type.getTitleOrCreatorOrSubject().add(title);
        return type;
    }

    @OAIDelegate(metadataPrefix = "lom")
    public LomType getNLLomType(RestContext context) {
        NLLom lom = new NLLom();
        lom.setAggregationLevel();
        lom.setCopyRightAndOtherRestrictions();
        lom.setCosts();
        lom.setTitle(getTitle());
        lom.setDescription(getIntroduction());
        return lom.getLomType();
    }

    @OAIIdentifier
    public String getIdentifier(RestContext context) {
        return getCanonicalHandleUUID();
    }
}
