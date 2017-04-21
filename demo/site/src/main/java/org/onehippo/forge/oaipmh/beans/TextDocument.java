package org.onehippo.forge.oaipmh.beans;

import java.util.Calendar;

import javax.xml.bind.JAXBElement;

import org.hippoecm.hst.content.beans.Node;
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

@OAI(setName = "Text document")
@Node(jcrType="oaipmh.demo:textdocument")
public class TextDocument extends BaseDocument implements OAIBean{
    
    public String getTitle() {
        return getProperty("oaipmh.demo:title");
    }

    public String getSummary() {
        return getProperty("oaipmh.demo:summary");
    }
    
    public HippoHtml getHtml(){
        return getHippoHtml("oaipmh.demo:body");    
    }

    private static final ObjectFactory objectFactory = new ObjectFactory();

    @OAIDelegate(metadataPrefix = "oai_dc")
    public OaiDcType dublinCore(RestContext context) {
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
        lom.setDescription(getSummary());
        return lom.getLomType();
    }

    @OAIIdentifier
    public String getIdentifier(RestContext context) {
        return getCanonicalHandleUUID();
    }

    @Override
    public Calendar getPublicationDate() {
        return getProperty("hippostdpubwf:publicationDate");
    }
}
