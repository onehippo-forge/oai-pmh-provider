package org.onehippo.forge.oaipmh.provider.api;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
@Node(jcrType = "oai:identify")
public class Identify extends HippoDocument {

    private static Logger log = LoggerFactory.getLogger(Identify.class);

    public String getRepositoryName(){
        return getProperty("oai:reponame");
    }

    public String getAdminEmail(){
        return getProperty("oai:email");
    }

}
