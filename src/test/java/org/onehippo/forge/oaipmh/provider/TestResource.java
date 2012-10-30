package org.onehippo.forge.oaipmh.provider;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import com.google.common.base.Strings;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.exceptions.FilterException;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.query.filter.NodeTypeFilter;
import org.hippoecm.hst.content.beans.query.filter.PrimaryNodeTypeFilterImpl;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.forge.oaipmh.provider.api.BaseOAIResource;
import org.onehippo.forge.oaipmh.provider.api.OAIBean;
import org.onehippo.forge.oaipmh.provider.api.OAIException;
import org.onehippo.forge.oaipmh.provider.api.OAIUtil;
import org.onehippo.forge.oaipmh.provider.api.RestContext;
import org.onehippo.forge.oaipmh.provider.model.oai.DeletedRecordType;
import org.onehippo.forge.oaipmh.provider.model.oai.GranularityType;
import org.onehippo.forge.oaipmh.provider.model.oai.IdentifyType;
import org.onehippo.forge.oaipmh.provider.model.oai.ListType;
import org.onehippo.forge.oaipmh.provider.model.oai.MetadataFormatType;
import org.onehippo.forge.oaipmh.provider.model.oai.OAIPMHerrorcodeType;
import org.onehippo.forge.oaipmh.provider.model.oai.ResumptionTokenType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class TestResource extends BaseOAIResource {

    private static Logger log = LoggerFactory.getLogger(TestResource.class);



    @Override
    protected List<MetadataFormatType> getMetadataFormatTypes() {
        final List<MetadataFormatType> metaDataFormatType = new ArrayList<MetadataFormatType>();
        final MetadataFormatType lom = new MetadataFormatType();
        lom.setMetadataPrefix("lom");
        lom.setSchema("http://www.imsglobal.org/xsd/imsmd_v1p2p4.xsd");
        lom.setMetadataNamespace("http://www.imsglobal.org/xsd/imsmd_v1p2");
        metaDataFormatType.add(lom);
        final MetadataFormatType dc = new MetadataFormatType();
        dc.setMetadataPrefix("dc");
        dc.setSchema("");
        dc.setMetadataNamespace("");
        metaDataFormatType.add(dc);
        return metaDataFormatType;
    }

    protected static final String ADMIN_EMAIL = "k.salic@onehippo.com";
    protected static final String REPOSITORY_NAME = "HIPPO repository";
    protected static final String PROTOCOL_VERSION = "2.0";

    @Override
    public IdentifyType getIdentifyType(final RestContext context) {
        final IdentifyType identifyType = new IdentifyType();
        identifyType.setRepositoryName(REPOSITORY_NAME);
        identifyType.setBaseURL(resolveRequestUrl(context));
        identifyType.setProtocolVersion(PROTOCOL_VERSION);
        identifyType.getAdminEmail().add(ADMIN_EMAIL);
        final Calendar earliestDate = Calendar.getInstance();
        earliestDate.setTime(new Date(0));
        identifyType.setEarliestDatestamp(getDate(earliestDate));
        identifyType.setDeletedRecord(DeletedRecordType.NO);
        identifyType.setGranularity(GranularityType.YYYY_MM_DD_THH_MM_SS_Z);
        return identifyType;
    }


    @Override
    protected String getMetadataPrefixFromResumptionToken(final String resumptionToken) throws OAIException {
        final Matcher matcher = TOKEN_PATTERN.matcher(resumptionToken);
        if (matcher.matches()) {
            final String metadataPrefix = matcher.group(2);

            return metadataPrefix;

        }
        throw new OAIException(OAIPMHerrorcodeType.BAD_RESUMPTION_TOKEN, "The value of the resumptionToken argument is invalid or expired.");
    }

    @Override
    public String populateIdentifier(final RestContext context, final OAIBean bean, final String metaPrefix) {
        return OAIUtil.getInstance().getIdentifier(bean, context);
    }

    @Override
    public Object populateMetaData(final RestContext context, final OAIBean bean, final String metaPrefix) {
        return OAIUtil.getInstance().delegate(bean, metaPrefix, context);
    }

    protected static final Pattern TOKEN_PATTERN = Pattern.compile("tx([0-9]+)xmx([a-z[0-9]]+)xpx(.*)frm(.*)utl(.*)");


    @Override
    public boolean validResumptionToken(final String resumptionToken) {
        return TOKEN_PATTERN.matcher(resumptionToken).matches();
    }

    @Override
    public int getPageSize() {
        return 10;
    }

    @Override
    protected void processQueryBasedOnResumptionToken(final HstQuery query, final String resumptionToken) throws OAIException {
        final Calendar calendarFromResumptionToken = getCalendarFromResumptionToken(resumptionToken);
        final Filter filter = getFilter(query);
        try {
            final String from = getFromResumptionTokenOrUseDefaultNull(resumptionToken, 4, null);
            final String until = getFromResumptionTokenOrUseDefaultNull(resumptionToken, 5, null);

            Calendar fromCalendar;
            Calendar untilCalendar;
            try {
                fromCalendar = getSimpleDate(from);
                untilCalendar = getSimpleDate(until);
            } catch (ParseException e) {
                throw new OAIException(OAIPMHerrorcodeType.BAD_ARGUMENT, THE_REQUEST_INCLUDES_ILLEGAL_ARGUMENTS_IS_MISSING_REQUIRED_ARGUMENTS_INCLUDES_A_REPEATED_ARGUMENT_OR_VALUES_FOR_ARGUMENTS_HAVE_AN_ILLEGAL_SYNTAX_FROM_ARGUMENT_MUST_BE_SMALLER_THAN_UNTIL_ARGUMENT);
            }
            if (fromCalendar != null && untilCalendar != null) {
                if (fromCalendar.getTimeInMillis() > untilCalendar.getTimeInMillis()) {
                    throw new OAIException(OAIPMHerrorcodeType.BAD_ARGUMENT, THE_REQUEST_INCLUDES_ILLEGAL_ARGUMENTS_IS_MISSING_REQUIRED_ARGUMENTS_INCLUDES_A_REPEATED_ARGUMENT_OR_VALUES_FOR_ARGUMENTS_HAVE_AN_ILLEGAL_SYNTAX_FROM_ARGUMENT_MUST_BE_SMALLER_THAN_UNTIL_ARGUMENT);
                }
            }
            if (fromCalendar != null) {
                filter.addGreaterOrEqualThan(HIPPOSTDPUBWF_PUBLICATION_DATE, fromCalendar);
                query.setFilter(filter);
            }
            if (untilCalendar != null) {
                filter.addLessOrEqualThan(HIPPOSTDPUBWF_PUBLICATION_DATE, untilCalendar);
                query.setFilter(filter);
            }
            filter.addGreaterThan(HIPPOSTDPUBWF_PUBLICATION_DATE, calendarFromResumptionToken);
        } catch (FilterException e) {
            throw new OAIException(OAIPMHerrorcodeType.BAD_RESUMPTION_TOKEN, "The value of the resumptionToken argument is invalid or expired.");
        }
        final String setFromResumptionToken = getSetFromResumptionToken(resumptionToken);
        if (StringUtils.isNotEmpty(setFromResumptionToken)) {
            if (!SETMAP.containsKey(setFromResumptionToken)) {
                throw new OAIException(OAIPMHerrorcodeType.BAD_RESUMPTION_TOKEN, "The value of the resumptionToken argument is invalid or expired.");
            }
            final Class<? extends OAIBean> aClass = SETMAP.get(setFromResumptionToken);
            if (aClass.isAnnotationPresent(Node.class)) {
                final String primaryNodeTypeNameForBean = aClass.getAnnotation(Node.class).jcrType();
                NodeTypeFilter primaryNodeTypeFilter = new PrimaryNodeTypeFilterImpl(primaryNodeTypeNameForBean);
                filter.addAndFilter(primaryNodeTypeFilter);
            }
        }
        query.setFilter(filter);
    }

    private static final String RESUMPTION_TOKEN_FORMAT = "tx%sxmx%sxpx%sfrm%sutl%s";

    @Override
    protected void processResumptionToken(final RestContext context, final ListType listType, final String resumptionToken, final Calendar lastKnownPublicationDate, final String metaPrefix, final String set, String from, String until) throws OAIException {
        final ResumptionTokenType resumptionTokenType = new ResumptionTokenType();
        final long time = lastKnownPublicationDate.getTimeInMillis();

        String resToken = String.format(RESUMPTION_TOKEN_FORMAT, time, getFromResumptionTokenOrUseDefault(resumptionToken, 2, metaPrefix), getFromResumptionTokenOrUseDefault(resumptionToken, 3, set), getFromResumptionTokenOrUseDefault(resumptionToken, 4, from), getFromResumptionTokenOrUseDefault(resumptionToken, 5, until));
        resumptionTokenType.setValue(resToken);

        listType.setResumptionToken(resumptionTokenType);
    }

    public String getFromResumptionTokenOrUseDefault(String resumptionToken, int group, String defaultString) {
        if (StringUtils.isNotEmpty(resumptionToken)) {
            final Matcher matcher = TOKEN_PATTERN.matcher(resumptionToken);
            if (matcher.matches()) {
                return matcher.group(group);
            }
        }
        return defaultString == null ? "" : defaultString;
    }

    public String getFromResumptionTokenOrUseDefaultNull(String resumptionToken, int group, String defaultString) {
        if (StringUtils.isNotEmpty(resumptionToken)) {
            final Matcher matcher = TOKEN_PATTERN.matcher(resumptionToken);
            if (matcher.matches()) {
                return StringUtils.isEmpty(matcher.group(group)) ? defaultString : matcher.group(group);
            }
        }
        return defaultString;
    }


    @Override
    protected void applyGetRecordFilter(final HstQuery query, final String identifier, final String metaPrefix) throws OAIException {
        String id = parseId(identifier);
        if (StringUtils.isEmpty(id)) {
            throw new OAIException(OAIPMHerrorcodeType.ID_DOES_NOT_EXIST, "The value of the identifier argument is unknown or illegal in this repository.");
        }
        //todo
    }




    private static final Pattern ID_MATCHER = Pattern.compile("(?:.*/)([0-9]+)(?:\\..*)");


    private String parseId(String identifier) {
        if (!Strings.isNullOrEmpty(identifier)) {
            Matcher matcher = ID_MATCHER.matcher(identifier);
            if (matcher.matches() && matcher.groupCount() == 1) {
                return matcher.group(1);
            }
        }
        return null;
    }

    protected Calendar getCalendarFromResumptionToken(final String resumptionToken) throws OAIException {
        final Matcher matcher = TOKEN_PATTERN.matcher(resumptionToken);
        if (matcher.matches()) {
            final String timeInMillis = matcher.group(1);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Long.valueOf(timeInMillis));
            return calendar;

        }
        throw new OAIException(OAIPMHerrorcodeType.BAD_RESUMPTION_TOKEN, "The value of the resumptionToken argument is invalid or expired.");
    }

    protected String getSetFromResumptionToken(final String resumptionToken) throws OAIException {
        final Matcher matcher = TOKEN_PATTERN.matcher(resumptionToken);
        if (matcher.matches()) {
            final String setType = matcher.group(3);
            return setType;
        }
        return null;
    }

}