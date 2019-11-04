package org.onehippo.forge.beans;
/*
 * Copyright 2014-2019 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.Calendar;

import javax.xml.bind.JAXBElement;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.hippoecm.repository.HippoStdPubWfNodeType;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.onehippo.forge.oaipmh.provider.api.OAIBean;
import org.onehippo.forge.oaipmh.provider.api.OAIDelegate;
import org.onehippo.forge.oaipmh.provider.api.OAIIdentifier;
import org.onehippo.forge.oaipmh.provider.api.RestContext;
import org.onehippo.forge.oaipmh.provider.model.oai.dc.ElementType;
import org.onehippo.forge.oaipmh.provider.model.oai.dc.OaiDcType;
import org.onehippo.forge.oaipmh.provider.model.oai.dc.ObjectFactory;
import org.onehippo.forge.oaipmh.provider.model.oai.edurep.lom.LomType;
import org.onehippo.forge.oaipmh.provider.model.oai.edurep.lom.NLLom;

@HippoEssentialsGenerated(internalName = "oaipmhdemo:newsdocument", allowModifications = false)
@Node(jcrType=NewsDocument.DOCUMENT_TYPE)
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
     *
     * @return the title
     */
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:title", allowModifications = false)
    public String getTitle() {
        return getSingleProperty(TITLE);
    }

    /**
     * Get the date of the document.
     *
     * @return the date
     */
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:date", allowModifications = false)
    public Calendar getDate() {
        return getSingleProperty(DATE);
    }

    /**
     * Get the introduction of the document.
     *
     * @return the introduction
     */
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:introduction", allowModifications = false)
    public String getIntroduction() {
        return getSingleProperty(INTRODUCTION);
    }

    /**
     * Get the image of the document.
     *
     * @return the image
     */
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:image", allowModifications = false)
    public HippoGalleryImageSet getImage() {
        return getLinkedBean(IMAGE, HippoGalleryImageSet.class);
    }

    /**
     * Get the main content of the document.
     *
     * @return the content
     */
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:content", allowModifications = false)
    public HippoHtml getContent() {
        return getHippoHtml(CONTENT);
    }

    /**
     * Get the location of the document.
     *
     * @return the location
     */
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:location", allowModifications = false)
    public String getLocation() {
        return getSingleProperty(LOCATION);
    }

    /**
     * Get the author of the document.
     *
     * @return the author
     */
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:author", allowModifications = false)
    public String getAuthor() {
        return getSingleProperty(AUTHOR);
    }

    /**
     * Get the source of the document.
     *
     * @return the source
     */
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:source", allowModifications = false)
    public String getSource() {
        return getSingleProperty(SOURCE);
    }

    @Override
    public Calendar getPublicationDate() {
        return getSingleProperty(HippoStdPubWfNodeType.HIPPOSTDPUBWF_PUBLICATION_DATE);
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

