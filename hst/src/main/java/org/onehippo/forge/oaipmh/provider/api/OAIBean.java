package org.onehippo.forge.oaipmh.provider.api;

import java.util.Calendar;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;

/**
 * @version "$Id$"
 */
@Node(jcrType = "oai:document")
@HippoEssentialsGenerated(internalName = "oai:document", allowModifications = false)
public interface OAIBean extends HippoDocumentBean {
    Calendar getPublicationDate();
}
