package org.onehippo.forge.beans;

import java.util.Calendar;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoHtmlAdapter;
import org.onehippo.forge.oaipmh.provider.api.OAIBean;

@XmlRootElement(name = "contentdocument")
@XmlAccessorType(XmlAccessType.NONE)
@HippoEssentialsGenerated(internalName = "oaipmhdemo:contentdocument")
@Node(jcrType = "oaipmhdemo:contentdocument")
public class ContentDocument extends BaseDocument implements OAIBean {
    @XmlElement
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:introduction")
    public String getIntroduction() {
        return getProperty("oaipmhdemo:introduction");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:title")
    public String getTitle() {
        return getProperty("oaipmhdemo:title");
    }

    @XmlJavaTypeAdapter(HippoHtmlAdapter.class)
    @XmlElement
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:content")
    public HippoHtml getContent() {
        return getHippoHtml("oaipmhdemo:content");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "oaipmhdemo:publicationdate")
    public Calendar getPublicationDate() {
        return getProperty("oaipmhdemo:publicationdate");
    }
}
