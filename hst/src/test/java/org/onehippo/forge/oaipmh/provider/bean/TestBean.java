package org.onehippo.forge.oaipmh.provider.bean;

import java.util.Calendar;

import javax.xml.bind.JAXBElement;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.onehippo.forge.oaipmh.provider.api.OAI;
import org.onehippo.forge.oaipmh.provider.api.OAIBean;
import org.onehippo.forge.oaipmh.provider.api.OAIDelegate;
import org.onehippo.forge.oaipmh.provider.api.OAIIdentifier;
import org.onehippo.forge.oaipmh.provider.api.RestContext;
import org.onehippo.forge.oaipmh.provider.model.oai.dc.ElementType;
import org.onehippo.forge.oaipmh.provider.model.oai.dc.OaiDcType;
import org.onehippo.forge.oaipmh.provider.model.oai.dc.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
@OAI(setName = "Test Document")
@Node(jcrType = "test:bean")
public class TestBean extends HippoDocument implements OAIBean {

    private static final  ObjectFactory objectFactory = new ObjectFactory();

    private static Logger log = LoggerFactory.getLogger(TestBean.class);

    public String getTitle() {
        return getSingleProperty("test:title");
    }

    @Override
    public Calendar getPublicationDate() {
        return getSingleProperty("test:date");
    }

    @OAIDelegate(metadataPrefix = "dc")
    public OaiDcType getDublinCoreObject(RestContext context) {
        OaiDcType type = objectFactory.createOaiDcType();
        ElementType title = objectFactory.createElementType();
        title.setValue(getTitle());
        final JAXBElement<ElementType> title1 = objectFactory.createTitle(title);
        type.getTitleOrCreatorOrSubject().add(title1);
        return type;
    }

    @OAIIdentifier
    public String getIdentifier(RestContext context){
        return getCanonicalHandleUUID();
    }


}
