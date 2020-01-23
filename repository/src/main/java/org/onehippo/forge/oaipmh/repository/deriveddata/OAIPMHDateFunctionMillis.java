package org.onehippo.forge.oaipmh.repository.deriveddata;

public class OAIPMHDateFunctionMillis extends OAIPMHDateFunction {

    private static final String DATE_FORMAT_MILLIS = "yyyyMMddHHmmssSSS";

    @Override
    protected String getDateFormat() {
        return DATE_FORMAT_MILLIS;
    }

}
