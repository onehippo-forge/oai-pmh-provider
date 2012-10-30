package org.onehippo.forge.oaipmh.provider.api;

import org.onehippo.forge.oaipmh.provider.model.oai.OAIPMHerrorcodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class OAIException extends Exception {

    private static Logger log = LoggerFactory.getLogger(OAIException.class);

    private OAIPMHerrorcodeType type;

    public OAIException(final OAIPMHerrorcodeType type, final String message) {
        super(message);
        this.type = type;
    }

    public OAIPMHerrorcodeType getType() {
        return type;
    }
}
