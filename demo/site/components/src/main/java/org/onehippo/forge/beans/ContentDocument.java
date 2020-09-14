package org.onehippo.forge.beans;
/*
 * Copyright 2014-2020 Bloomreach (http://www.bloomreach.com)
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
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
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

@HippoEssentialsGenerated(internalName = "oaipmhdemo:contentdocument", allowModifications = false)
@Node(jcrType = "oaipmhdemo:contentdocument")
@OAI(setName = "ContentDocument")
public class ContentDocument extends BaseDocument implements OAIBean {

    private static final ObjectFactory objectFactory = new ObjectFactory();

    @HippoEssentialsGenerated(internalName = "oaipmhdemo:introduction", allowModifications = false)
    public String getIntroduction() {
        return getSingleProperty("oaipmhdemo:introduction");
    }

    @HippoEssentialsGenerated(internalName = "oaipmhdemo:title", allowModifications = false)
    public String getTitle() {
        return getSingleProperty("oaipmhdemo:title");
    }

    @HippoEssentialsGenerated(internalName = "oaipmhdemo:content", allowModifications = false)
    public HippoHtml getContent() {
        return getHippoHtml("oaipmhdemo:content");
    }

    @HippoEssentialsGenerated(internalName = "oaipmhdemo:publicationdate", allowModifications = false)
    public Calendar getPublicationDate() {
        return getSingleProperty("oaipmhdemo:publicationdate");
    }

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
