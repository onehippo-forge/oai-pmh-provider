package org.onehippo.forge.oaipmh.repository.deriveddata;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Value;

import org.hippoecm.repository.ext.DerivedDataFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class OAIPMHDateFunction extends DerivedDataFunction {

    private static final Logger log = LoggerFactory.getLogger(OAIPMHDateFunction.class);

    @SuppressWarnings("unused")
    static final long serialVersionUID = 1;

    private static final String PUBLICATION_DATE = "publicationDate";
    private static final String OAI_PUBLICATION_DATE = "oaipublicationDate";
    private static final String DATE_FORMAT = "yyyyMMddhhmmss";

    @Override
    public Map<String, Value[]> compute(final Map<String, Value[]> parameters) {
        try {
            if (parameters.containsKey(PUBLICATION_DATE)) {
                final Date publicationDate = parameters.get(PUBLICATION_DATE)[0].getDate().getTime();
                final String dateString = new SimpleDateFormat(DATE_FORMAT).format(publicationDate);
                parameters.put(OAI_PUBLICATION_DATE, new Value[]{getValueFactory().createValue(dateString)});
            }
        } catch (RepositoryException repositoryException) {
            log.error("Error computing publication date string. Parameters are: " + parameters, repositoryException);
        }
        return parameters;
    }

}
