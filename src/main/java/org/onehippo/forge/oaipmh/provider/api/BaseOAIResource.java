package org.onehippo.forge.oaipmh.provider.api;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.ws.WebServiceException;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.onehippo.forge.oaipmh.provider.model.oai.GetRecordType;
import org.onehippo.forge.oaipmh.provider.model.oai.HeaderType;
import org.onehippo.forge.oaipmh.provider.model.oai.IdentifyType;
import org.onehippo.forge.oaipmh.provider.model.oai.ListIdentifiersType;
import org.onehippo.forge.oaipmh.provider.model.oai.ListMetadataFormatsType;
import org.onehippo.forge.oaipmh.provider.model.oai.ListRecordsType;
import org.onehippo.forge.oaipmh.provider.model.oai.ListSetsType;
import org.onehippo.forge.oaipmh.provider.model.oai.ListType;
import org.onehippo.forge.oaipmh.provider.model.oai.MetadataFormatType;
import org.onehippo.forge.oaipmh.provider.model.oai.MetadataType;
import org.onehippo.forge.oaipmh.provider.model.oai.OAIPMHerrorType;
import org.onehippo.forge.oaipmh.provider.model.oai.OAIPMHerrorcodeType;
import org.onehippo.forge.oaipmh.provider.model.oai.OAIPMHtype;
import org.onehippo.forge.oaipmh.provider.model.oai.RecordType;
import org.onehippo.forge.oaipmh.provider.model.oai.RequestType;
import org.onehippo.forge.oaipmh.provider.model.oai.SetType;
import org.onehippo.forge.oaipmh.provider.model.oai.VerbType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
@Produces({MediaType.APPLICATION_XML})
@Path("/oai")
public abstract class BaseOAIResource extends AbstractResource {

    private static Logger log = LoggerFactory.getLogger(BaseOAIResource.class);

    protected static final String PARAM_VERB = "verb";
    protected static final String PARAM_META_PREFIX = "metadataPrefix";
    protected static final String PARAM_IDENTIFIER = "identifier";
    protected static final String PARAM_RESUMPTION_TOKEN = "resumptionToken";
    protected static final String PARAM_SET = "set";

    protected static final String HIPPOSTDPUBWF_PUBLICATION_DATE = "hippostdpubwf:publicationDate";

    private static final String BAD_VERB_NO_VERB = "The request includes illegal arguments, is missing required arguments, includes a repeated argument, or values for arguments have an illegal syntax. No \"verb\" argument found.";
    private static final String BAD_VERB_NOT_LEGAL = "Value of the verb argument is not a legal OAI-PMH verb, the verb argument is missing, or the verb argument is repeated.";


    protected static final Set<String> VERB_VALUES = new ImmutableSet.Builder<String>()
            .add("Identify")
            .add("ListMetadataFormats")
            .add("ListRecords")
            .add("ListIdentifiers")
            .add("GetRecord")
            .add("ListSets")
            .build();

    protected static final Map<String, Class<? extends OAIBean>> SETMAP = new HashMap<String, Class<? extends OAIBean>>();

    protected static final String THE_COMBINATION_OF_THE_VALUES_OF_THE_FROM_UNTIL_SET_AND_METADATA_PREFIX_ARGUMENTS_RESULTS_IN_AN_EMPTY_LIST = "The combination of the values of the from, until, set and metadataPrefix arguments results in an empty list.";
    protected static final String THE_VALUE_OF_THE_RESUMPTION_TOKEN_ARGUMENT_IS_INVALID_OR_EXPIRED = "The value of the resumptionToken argument is invalid or expired.";
    protected static final String THE_METADATA_FORMAT_IDENTIFIED_BY_THE_VALUE_GIVEN_FOR_THE_METADATA_PREFIX_ARGUMENT_IS_NOT_SUPPORTED_BY_THE_ITEM_OR_BY_THE_REPOSITORY = "The metadata format identified by the value given for the metadataPrefix argument is not supported by the item or by the repository.";
    protected static final String THE_REQUEST_INCLUDES_ILLEGAL_ARGUMENTS_IS_MISSING_REQUIRED_ARGUMENTS_INCLUDES_A_REPEATED_ARGUMENT_OR_VALUES_FOR_ARGUMENTS_HAVE_AN_ILLEGAL_SYNTAX_ARGUMENT_S_S_IS_ARE_ILLEGAL = "The request includes illegal arguments, is missing required arguments, includes a repeated argument, or values for arguments have an illegal syntax. Argument(s) %s is/are illegal.";
    protected static final String THE_REQUEST_INCLUDES_ILLEGAL_ARGUMENTS_IS_MISSING_REQUIRED_ARGUMENTS_INCLUDES_A_REPEATED_ARGUMENT_OR_VALUES_FOR_ARGUMENTS_HAVE_AN_ILLEGAL_SYNTAX_RESUMPTION_TOKEN_ARGUMENT_MAY_ONLY_BE_USED_EXCLUSIVELY = "The request includes illegal arguments, is missing required arguments, includes a repeated argument, or values for arguments have an illegal syntax. \"resumptionToken\" argument may only be used exclusively.";
    protected static final String THE_REQUEST_INCLUDES_ILLEGAL_ARGUMENTS_IS_MISSING_REQUIRED_ARGUMENTS_INCLUDES_A_REPEATED_ARGUMENT_OR_VALUES_FOR_ARGUMENTS_HAVE_AN_ILLEGAL_SYNTAX_MISSING_ARGUMENT_S_IDENTIFIER_AND_OR_METADATAPREFIX = "The request includes illegal arguments, is missing required arguments, includes a repeated argument, or values for arguments have an illegal syntax. Missing argument(s) \"identifier\" and or \"metadataprefix\" .";
    protected static final String THE_VALUE_OF_THE_IDENTIFIER_ARGUMENT_IS_UNKNOWN_OR_ILLEGAL_IN_THIS_REPOSITORY = "The value of the identifier argument is unknown or illegal in this repository.";
    protected static final String THE_REQUEST_INCLUDES_ILLEGAL_ARGUMENTS_IS_MISSING_REQUIRED_ARGUMENTS_INCLUDES_A_REPEATED_ARGUMENT_OR_VALUES_FOR_ARGUMENTS_HAVE_AN_ILLEGAL_SYNTAX = "The request includes illegal arguments, is missing required arguments, includes a repeated argument, or values for arguments have an illegal syntax.";
    protected static final String SLASH = "/";

    @GET
    @Path("/")
    public OAIPMHtype oaiDelegate(@Context HttpServletRequest request, @Context HttpServletResponse response, @Context UriInfo uriInfo,
                                  @QueryParam(PARAM_VERB) String verb,
                                  @QueryParam(PARAM_META_PREFIX) String metaPrefix,
                                  @QueryParam(PARAM_IDENTIFIER) String identifier,
                                  @QueryParam(PARAM_RESUMPTION_TOKEN) String resumptionToken,
                                  @QueryParam(PARAM_SET) String set
    ) {
        OAIPMHtype oaipmHtype = new OAIPMHtype();
        RestContext context = new RestContext(this, request, response, uriInfo);

        try {
            processRoot(context, oaipmHtype, verb);
            validateVerb(verb);

            switch (VerbType.fromValue(verb)) {
                case IDENTIFY:
                    identify(context, oaipmHtype);
                    break;
                case LIST_RECORDS:
                    listRecords(context, oaipmHtype, metaPrefix, resumptionToken, set);
                    break;
                case LIST_IDENTIFIERS:
                    listIdentifiers(context, oaipmHtype, metaPrefix, resumptionToken, set);
                    break;
                case GET_RECORD:
                    getRecord(context, oaipmHtype, metaPrefix, identifier);
                    break;
                case LIST_METADATA_FORMATS:
                    listMetadataFormats(context, oaipmHtype, identifier);
                    break;
                case LIST_SETS:
                    listSets(context, oaipmHtype);
                    break;
            }
        } catch (OAIException e) {
            processError(oaipmHtype, e);
            log.warn("OAI exception {}", e);
        } catch (Exception e) {
            log.error("Exception {}", e);
        }

        return oaipmHtype;
    }

    protected static final Set<String> METADATAFORMATS_ALLOWED = new ImmutableSet.Builder<String>()
            .add("verb")
            .add("identifier")
            .build();

    private void listMetadataFormats(final RestContext context, final OAIPMHtype oaipmHtype, final String identifier) throws OAIException {
        processBase(context, METADATAFORMATS_ALLOWED);
        if (StringUtils.isNotEmpty(identifier)) {
            throw new OAIException(OAIPMHerrorcodeType.ID_DOES_NOT_EXIST, THE_VALUE_OF_THE_IDENTIFIER_ARGUMENT_IS_UNKNOWN_OR_ILLEGAL_IN_THIS_REPOSITORY);
        }

        final ListMetadataFormatsType formats = new ListMetadataFormatsType();
        final List<MetadataFormatType> metadataFormatTypes = getMetadataFormatTypes();
        if (metadataFormatTypes != null && !metadataFormatTypes.isEmpty()) {
            for (MetadataFormatType type : metadataFormatTypes) {
                formats.getMetadataFormat().add(type);
            }
        }
        oaipmHtype.setListMetadataFormats(formats);
    }


    protected static final Set<String> GETRECORD_ALLOWED = new ImmutableSet.Builder<String>()
            .add("verb")
            .add("metadataPrefix")
            .add("identifier")
            .build();

    private void getRecord(final RestContext context, final OAIPMHtype oaipmHtype, final String metaPrefix, final String identifier) throws OAIException, QueryException {
        processBase(context, GETRECORD_ALLOWED);
        if (Strings.isNullOrEmpty(metaPrefix) || Strings.isNullOrEmpty(identifier)) {
            throw new OAIException(OAIPMHerrorcodeType.BAD_ARGUMENT, THE_REQUEST_INCLUDES_ILLEGAL_ARGUMENTS_IS_MISSING_REQUIRED_ARGUMENTS_INCLUDES_A_REPEATED_ARGUMENT_OR_VALUES_FOR_ARGUMENTS_HAVE_AN_ILLEGAL_SYNTAX_MISSING_ARGUMENT_S_IDENTIFIER_AND_OR_METADATAPREFIX);
        }
        validateMetaDataPrefix(metaPrefix);

        HstQuery query = generateHstQuery(context, OAIBean.class, true);
        applyGetRecordFilter(query, identifier, metaPrefix);
        query.setLimit(1); // there should be only one result
        HstQueryResult result = query.execute();
        final int totalSize = result.getTotalSize();

        if (totalSize <= 0) {
            throw new OAIException(OAIPMHerrorcodeType.ID_DOES_NOT_EXIST, THE_VALUE_OF_THE_IDENTIFIER_ARGUMENT_IS_UNKNOWN_OR_ILLEGAL_IN_THIS_REPOSITORY);
        }

        final GetRecordType recordType = new GetRecordType();
        oaipmHtype.setGetRecord(recordType);

        HippoBeanIterator hippoBeans = result.getHippoBeans();

        while (hippoBeans.hasNext()) {
            final OAIBean bean = (OAIBean) hippoBeans.nextHippoBean();

            final RecordType record = new RecordType();
            recordType.setRecord(record);

            final String beanIdentifier = populateIdentifier(context, bean, metaPrefix);
            final Calendar publicationDate = bean.getPublicationDate();

            // header
            final HeaderType headerType = new HeaderType();

            headerType.setIdentifier(beanIdentifier);
            headerType.setDatestamp(getDate(publicationDate));

            final List<String> setSpec = headerType.getSetSpec();
            setSpec.add(StringUtils.substringAfter(bean.getClass().getAnnotation(Node.class).jcrType(), ":"));

            record.setHeader(headerType);
            // meta
            final MetadataType meta = new MetadataType();
            record.setMetadata(meta);
            // populate meta:
            meta.setAny(populateMetaData(context, bean, metaPrefix));
        }


    }


    protected static final Set<String> LISTSETS_ALLOWED = new ImmutableSet.Builder<String>()
            .add("verb")
            .add("resumptionToken")
            .build();

    private void loadListSets(final RestContext context) {
        final List<Class<? extends HippoBean>> annotatedClasses = getAnnotatedClasses(context.getHstRequestContext());
        for (Class<? extends HippoBean> clazz : annotatedClasses) {
            if (OAIBean.class.isAssignableFrom(clazz) && !clazz.equals(OAIBean.class)) {
                if (clazz.isAnnotationPresent(Node.class)) {
                    SETMAP.put(StringUtils.substringAfter(clazz.getAnnotation(Node.class).jcrType(), ":"), (Class<? extends OAIBean>) clazz);
                }
            }
        }
    }

    private void listSets(final RestContext context, final OAIPMHtype oaipmHtype) throws OAIException {
        processBase(context, LISTSETS_ALLOWED);
        final ListSetsType listSetsType = new ListSetsType();
        final List<SetType> setTypes = listSetsType.getSet();
        final List<Class<? extends HippoBean>> annotatedClasses = getAnnotatedClasses(context.getHstRequestContext());
        for (Class<? extends HippoBean> clazz : annotatedClasses) {
            if (OAIBean.class.isAssignableFrom(clazz) && !clazz.equals(OAIBean.class)) {
                if (clazz.isAnnotationPresent(Node.class)) {
                    final SetType setType = new SetType();
                    setType.setSetSpec(StringUtils.substringAfter(clazz.getAnnotation(Node.class).jcrType(), ":"));
                    if (clazz.isAnnotationPresent(OAI.class)) {
                        setType.setSetName(clazz.getAnnotation(OAI.class).setName());
                    } else {
                        setType.setSetName(setType.getSetSpec());
                    }
                    SETMAP.put(setType.getSetSpec(), (Class<? extends OAIBean>) clazz);
                    setTypes.add(setType);
                }
            }
        }
        oaipmHtype.setListSets(listSetsType);
    }


    protected static final Set<String> LISTRECORDIDENTIFIER_ALLOWED = new ImmutableSet.Builder<String>()
            .add("verb")
            .add("metadataPrefix")
            .add("resumptionToken")
                    //.add("until")
                    // .add("from")
            .add("set")
            .build();

    private void listRecords(final RestContext context, final OAIPMHtype oaipmHtype, String metaPrefix, final String resumptionToken, final String set) throws OAIException, QueryException {
        listRecordsOrIdentifiers(context, oaipmHtype, metaPrefix, resumptionToken, set, false);
    }

    private void listIdentifiers(final RestContext context, final OAIPMHtype oaipmHtype, final String metaPrefix, final String resumptionToken, final String set) throws OAIException, QueryException {
        listRecordsOrIdentifiers(context, oaipmHtype, metaPrefix, resumptionToken, set, true);
    }

    protected void listRecordsOrIdentifiers(final RestContext context, final OAIPMHtype oaipmHtype, String metaPrefix, final String resumptionToken, final String set, final boolean identfiersOnly) throws OAIException, QueryException {
        processBase(context, LISTRECORDIDENTIFIER_ALLOWED);
        boolean useResumptionToken = false;
        if (StringUtils.isNotEmpty(resumptionToken)) {
            if (!isExclusive(resumptionToken, metaPrefix, set)) {
                throw new OAIException(OAIPMHerrorcodeType.BAD_ARGUMENT, THE_REQUEST_INCLUDES_ILLEGAL_ARGUMENTS_IS_MISSING_REQUIRED_ARGUMENTS_INCLUDES_A_REPEATED_ARGUMENT_OR_VALUES_FOR_ARGUMENTS_HAVE_AN_ILLEGAL_SYNTAX_RESUMPTION_TOKEN_ARGUMENT_MAY_ONLY_BE_USED_EXCLUSIVELY);
            }
            if (!validResumptionToken(resumptionToken)) {
                throw new OAIException(OAIPMHerrorcodeType.BAD_RESUMPTION_TOKEN, THE_VALUE_OF_THE_RESUMPTION_TOKEN_ARGUMENT_IS_INVALID_OR_EXPIRED);
            }
            useResumptionToken = true;
            metaPrefix = getMetadataPrefixFromResumptionToken(resumptionToken);
        } else if (StringUtils.isEmpty(metaPrefix)) {
            throw new OAIException(OAIPMHerrorcodeType.BAD_ARGUMENT, THE_REQUEST_INCLUDES_ILLEGAL_ARGUMENTS_IS_MISSING_REQUIRED_ARGUMENTS_INCLUDES_A_REPEATED_ARGUMENT_OR_VALUES_FOR_ARGUMENTS_HAVE_AN_ILLEGAL_SYNTAX);
        }
        validateMetaDataPrefix(metaPrefix);

        final HstQuery query;
        if (SETMAP.isEmpty()) {
            loadListSets(context);
        }
        if (StringUtils.isNotEmpty(set)) {
            if (SETMAP.containsKey(set)) {
                query = generateHstQuery(context, SETMAP.get(set));
            } else {
                throw new OAIException(OAIPMHerrorcodeType.NO_RECORDS_MATCH, THE_COMBINATION_OF_THE_VALUES_OF_THE_FROM_UNTIL_SET_AND_METADATA_PREFIX_ARGUMENTS_RESULTS_IN_AN_EMPTY_LIST);
            }
        } else {
            query = generateHstQuery(context, OAIBean.class, true);
        }
        if (useResumptionToken) {
            processQueryBasedOnResumptionToken(query, resumptionToken);
        }
        query.setLimit(getPageSize());
        query.addOrderByAscending(HIPPOSTDPUBWF_PUBLICATION_DATE);
        final HstQueryResult queryResult = query.execute();
        final int totalSize = queryResult.getTotalSize();
        if (totalSize <= 0) {
            throw new OAIException(OAIPMHerrorcodeType.NO_RECORDS_MATCH, THE_COMBINATION_OF_THE_VALUES_OF_THE_FROM_UNTIL_SET_AND_METADATA_PREFIX_ARGUMENTS_RESULTS_IN_AN_EMPTY_LIST);
        }
        if (identfiersOnly) {
            populateListIdentifiers(context, queryResult, oaipmHtype, metaPrefix, resumptionToken, set);
        } else {
            populateListRecords(context, queryResult, oaipmHtype, metaPrefix, resumptionToken, set);
        }
    }


    protected void populateListIdentifiers(final RestContext context, final HstQueryResult queryResult, final OAIPMHtype oaipmHtype, final String metaPrefix, final String resumptionToken, final String set) throws OAIException {
        final ListIdentifiersType listIdentifiersType = new ListIdentifiersType();
        final List<HeaderType> headers = listIdentifiersType.getHeader();

        final HippoBeanIterator hippoBeans = queryResult.getHippoBeans();

        Calendar lastKnownPublicationDate = null;

        while (hippoBeans.hasNext()) {
            final OAIBean bean = (OAIBean) hippoBeans.nextHippoBean();
            final String identifier = populateIdentifier(context, bean, metaPrefix);
            final Calendar publicationDate = bean.getPublicationDate();

            // header
            final HeaderType headerType = new HeaderType();

            headerType.setIdentifier(identifier);
            headerType.setDatestamp(getDate(publicationDate));

            final List<String> setSpec = headerType.getSetSpec();
            setSpec.add(StringUtils.substringAfter(bean.getClass().getAnnotation(Node.class).jcrType(), ":"));

            headers.add(headerType);
            lastKnownPublicationDate = publicationDate;
        }
        oaipmHtype.setListIdentifiers(listIdentifiersType);

        final int totalSize = queryResult.getTotalSize();
        if (totalSize > getPageSize()) {
            processResumptionToken(context, listIdentifiersType, resumptionToken, lastKnownPublicationDate, metaPrefix, set);
        }
    }

    protected void populateListRecords(final RestContext context, final HstQueryResult queryResult, final OAIPMHtype oaipmHtype, final String metaPrefix, final String resumptionToken, final String set) throws OAIException {
        final ListRecordsType listRecordType = new ListRecordsType();
        final List<RecordType> records = listRecordType.getRecord();

        final HippoBeanIterator hippoBeans = queryResult.getHippoBeans();

        Calendar lastKnownPublicationDate = null;

        while (hippoBeans.hasNext()) {
            final OAIBean bean = (OAIBean) hippoBeans.nextHippoBean();

            final RecordType record = new RecordType();
            records.add(record);


            final String identifier = populateIdentifier(context, bean, metaPrefix);
            final Calendar publicationDate = bean.getPublicationDate();

            // header
            final HeaderType headerType = new HeaderType();

            headerType.setIdentifier(identifier);
            headerType.setDatestamp(getDate(publicationDate));

            final List<String> setSpec = headerType.getSetSpec();
            setSpec.add(StringUtils.substringAfter(bean.getClass().getAnnotation(Node.class).jcrType(), ":"));

            record.setHeader(headerType);

            // meta
            final MetadataType meta = new MetadataType();
            record.setMetadata(meta);
            // populate meta:
            //if (!identfiersOnly) {
            meta.setAny(populateMetaData(context, bean, metaPrefix));
            //}

            lastKnownPublicationDate = publicationDate;
        }
        oaipmHtype.setListRecords(listRecordType);

        final int totalSize = queryResult.getTotalSize();
        if (totalSize > getPageSize()) {
            processResumptionToken(context, listRecordType, resumptionToken, lastKnownPublicationDate, metaPrefix, set);
        }
    }

    /**
     * Important method, should overwrite for specific cases
     * @param context
     * @param bean
     * @param metaPrefix
     * @return
     */
    public String populateIdentifier(final RestContext context, final OAIBean bean, final String metaPrefix) {
        return OAIUtil.getInstance().getIdentifier(bean, context);
    }

    /***
     * Important method, should overwrite for specific cases
     * @param context
     * @param bean
     * @param metaPrefix
     * @return
     */
    public Object populateMetaData(final RestContext context, final OAIBean bean, final String metaPrefix) {
        return OAIUtil.getInstance().delegate(bean, metaPrefix, context);
    }


    protected static final Set<String> IDENTIFY_ALLOWED = new ImmutableSet.Builder<String>()
            .add("verb")
            .build();

    private void identify(final RestContext context, final OAIPMHtype root) throws OAIException {
        processBase(context, IDENTIFY_ALLOWED);
        root.setIdentify(getIdentifyType(context));
    }


    /**
     * ABSTACTS:
     */

    protected abstract List<MetadataFormatType> getMetadataFormatTypes();

    public abstract IdentifyType getIdentifyType(final RestContext context);

    public abstract boolean validResumptionToken(String resumptionToken);

    public abstract int getPageSize();

    protected abstract void processQueryBasedOnResumptionToken(final HstQuery query, final String resumptionToken) throws OAIException;

    protected abstract void processResumptionToken(final RestContext context, final ListType listType, String resumptionToken, final Calendar lastKnownPublicationDate, final String metaPrefix, final String set) throws OAIException;

    protected abstract void applyGetRecordFilter(final HstQuery query, final String identifier, final String metaPrefix) throws OAIException;

    protected abstract String getMetadataPrefixFromResumptionToken(final String resumptionToken) throws OAIException;

    /**
     * UTILITIES:
     */
    private boolean isExclusive(String value, String... othervalues) {
        if (StringUtils.isEmpty(value)) {
            return false;
        } else {
            for (String otherValue : othervalues) {
                if (StringUtils.isNotEmpty(otherValue)) {
                    return false;
                }
            }
        }
        return true;
    }


    public Filter getFilter(HstQuery query) {
        if (query.getFilter() != null && query.getFilter() instanceof Filter) {
            return (Filter) query.getFilter();
        }
        return query.createFilter();
    }

    private void validateMetaDataPrefix(final String metaPrefix) throws OAIException {
        final List<MetadataFormatType> metadataFormatTypes = getMetadataFormatTypes();
        boolean metaPrefixExists = false;
        for (MetadataFormatType type : metadataFormatTypes) {
            if (type.getMetadataPrefix().equals(metaPrefix)) {
                metaPrefixExists = true;
                break;
            }
        }
        if (!metaPrefixExists) {
            throw new OAIException(OAIPMHerrorcodeType.CANNOT_DISSEMINATE_FORMAT, THE_METADATA_FORMAT_IDENTIFIED_BY_THE_VALUE_GIVEN_FOR_THE_METADATA_PREFIX_ARGUMENT_IS_NOT_SUPPORTED_BY_THE_ITEM_OR_BY_THE_REPOSITORY);
        }
    }

    private void processBase(final RestContext context, final Set<String> allowed) throws OAIException {
        final MultivaluedMap<String, String> pathParameters = context.getUriInfo().getQueryParameters();
        List<String> list = new ArrayList<String>();
        for (String key : pathParameters.keySet()) {
            if (!allowed.contains(key)) {
                list.add(key);
            }
        }
        if (!list.isEmpty()) {
            throw new OAIException(OAIPMHerrorcodeType.BAD_ARGUMENT, String.format(THE_REQUEST_INCLUDES_ILLEGAL_ARGUMENTS_IS_MISSING_REQUIRED_ARGUMENTS_INCLUDES_A_REPEATED_ARGUMENT_OR_VALUES_FOR_ARGUMENTS_HAVE_AN_ILLEGAL_SYNTAX_ARGUMENT_S_S_IS_ARE_ILLEGAL, StringUtils.join(list.toArray(), ",")));
        }
    }

    private void processRoot(final RestContext context, final OAIPMHtype root, final String verb) throws InvocationTargetException, IllegalAccessException {
        setResponseDate(root);
        setResponseType(context, root, verb);
    }

    private void setResponseType(final RestContext context, final OAIPMHtype root, final String verb) throws InvocationTargetException, IllegalAccessException {
        final RequestType value = new RequestType();
        try {
            value.setVerb(VerbType.fromValue(verb));
        } catch (IllegalArgumentException e) {
            //do nothing
        }

        UriInfo uriInfo = context.getUriInfo();
        final MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();

        final Field[] fields = RequestType.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(XmlAttribute.class)) {
                final String name = field.getName();
                if (queryParameters.containsKey(name)) {
                    if (field.getType().equals(String.class)) {
                        BeanUtils.setProperty(value, name, queryParameters.get(name).get(0));
                    } else if (field.getType().equals(VerbType.class)) {
                        try {
                            BeanUtils.setProperty(value, name, VerbType.fromValue(queryParameters.get(name).get(0)));
                        } catch (IllegalArgumentException e) {
                            //do nothing
                        }

                    }
                }
            }
        }
        value.setValue(resolveRequestUrl(context));
        root.setRequest(value);
    }

    /**
     * Validates requests: if action type is not supported, exception {@code Response.Status.UNSUPPORTED_MEDIA_TYPE}
     * will be thrown
     *
     * @param verb
     */
    private boolean validateVerb(String verb) throws OAIException {
        if (verb == null) {
            throw new OAIException(OAIPMHerrorcodeType.BAD_VERB, BAD_VERB_NO_VERB);
        }
        final boolean valid = VERB_VALUES.contains(verb);
        if (!valid) {
            throw new OAIException(OAIPMHerrorcodeType.BAD_VERB, BAD_VERB_NOT_LEGAL);
        }
        return valid;
    }


    private void setResponseDate(OAIPMHtype root) {
        root.setResponseDate(createToday());
    }

    private static final String DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    protected static String getDate(Calendar calendar) {
        return FastDateFormat.getInstance(DATEFORMAT).format(calendar);
    }

    private String createToday() {
        Calendar c = Calendar.getInstance();
        return getDate(c);
    }


    private void processError(final OAIPMHtype root, OAIException e) {
        final OAIPMHerrorType oaipmHerrorType = new OAIPMHerrorType();
        oaipmHerrorType.setCode(e.getType());
        oaipmHerrorType.setValue(e.getMessage());
        root.getError().add(oaipmHerrorType);
    }


    protected String resolveRequestUrl(RestContext context) {
        Mount mount = context.getHstRequestContext().getResolvedMount().getMount();
        String host = mount.getScheme() + "://" + mount.getVirtualHost().getHostName();

        if (mount.isPortInUrl()) {
            int port = mount.getPort();
            if (port == 0) {
                // the Mount is port agnostic. Take port from current container url
                port = context.getHstRequestContext().getBaseURL().getPortNumber();
            }

            if (port != 80 && port != 443) {
                host += ":" + port;
            }
        }
        return host + context.getHstRequestContext().getBaseURL().getRequestPath();
    }

    public HstRequestContext getRequestContext(final HttpServletRequest request) {
        return super.getRequestContext(request);
    }

    /**
     * Subtype Detail method
     *
     * @param context
     * @param includeSubtypes
     * @param clazz
     * @return
     */
    protected final HstQuery generateHstQuery(final RestContext context, final Class<? extends HippoBean> clazz, boolean includeSubtypes) {
        return generateHstQuery(context, includeSubtypes, clazz);
    }

    protected HstQuery generateHstQuery(final RestContext context, final Class<? extends OAIBean> aClass) {
        return generateHstQuery(context, false, aClass);
    }


    private HstQuery generateHstQuery(final RestContext context, boolean includeSubtypes, final Class<? extends HippoBean>... clazz) {
        HstRequestContext requestContext = getRequestContext(context.getRequest());
        javax.jcr.Node scopeNode = getSiteBean(context.getRequest());
        HstQuery query = null;
        try {
            if (clazz.length == 1 && includeSubtypes) {
                query = getHstQueryManager(requestContext).createQuery(scopeNode, clazz[0], includeSubtypes);
            } else {
                query = getHstQueryManager(requestContext).createQuery(scopeNode, clazz);
            }
        } catch (QueryException e) {
            log.error("Error creating HST query", e);
        }
        if (query == null) {
            throw new WebServiceException("Query was null (failed to create it)");
        }
        return query;
    }

    private javax.jcr.Node getSiteBean(final HttpServletRequest request) {

        try {
            HstRequestContext requestContext = getRequestContext(request);
            Mount siteMount = requestContext.getMount(MOUNT_ALIAS_SITE);
            if (siteMount == null) {
                log.error("Couldn't find site mount for rest service");
                return null;
            }
            String contentPath = siteMount.getContentPath();
            if (contentPath != null) {
                String relativePath = StringUtils.removeStart(contentPath, SLASH);
                return requestContext.getSession().getRootNode().getNode(relativePath);
            }
        } catch (Exception e) {
            log.error("Error fetching siteBean", e);
        }
        return null;

    }
}
