package org.onehippo.forge.oaipmh.provider.api;

import java.util.Calendar;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;

/**
 * @version "$Id$"
 */
@Node(jcrType = "oai:document")
public interface OAIBean extends HippoDocumentBean {

    public Calendar getPublicationDate();

}
