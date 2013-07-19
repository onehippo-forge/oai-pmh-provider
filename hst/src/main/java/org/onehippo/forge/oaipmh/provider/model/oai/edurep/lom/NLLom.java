package org.onehippo.forge.oaipmh.provider.model.oai.edurep.lom;

import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version "$Id$"
 */
public class NLLom {

    protected static final String LOMV1_0 = "LOMv1.0";
    protected static final String NL_LOM_V1P0_0 = "nl_lom_v1p0.0";
    protected static final String HTTP_PURL_EDUSTANDAARD_NL_VDEX_COPYRIGHTSANDOTHERRESTRICTIONS_LOMV1P0_20060628_XML = "http://purl.edustandaard.nl/vdex_copyrightsandotherrestrictions_lomv1p0_20060628.xml";
    protected static final String HTTP_PURL_EDUSTANDAARD_NL_VDEX_CLASSIFICATION_PURPOSE_CZP_20060628_XML = "http://purl.edustandaard.nl/vdex_classification_purpose_czp_20060628.xml";
    protected static final String X_NONE = "x-none";
    protected static final String HTTP_PURL_EDUSTANDAARD_NL_VDEX_COST_LOMV1P0_20060628_XML = "http://purl.edustandaard.nl/vdex_cost_lomv1p0_20060628.xml";
    protected static final String HTTP_PURL_EDUSTANDAARD_NL_VDEX_CONTEXT_CZP_20060628_XML = "http://purl.edustandaard.nl/vdex_context_czp_20060628.xml";
    protected static final String HTTP_PURL_EDUSTANDAARD_NL_VDEX_INTENDEDENDUSERROLE_LOMV1P0_20060628_XML = "http://purl.edustandaard.nl/vdex_intendedenduserrole_lomv1p0_20060628.xml";
    protected static final String HTTP_PURL_EDUSTANDAARD_NL_VDEX_AGGREGATIONLEVEL_CZP_20060628_XML = "http://purl.edustandaard.nl/vdex_aggregationlevel_czp_20060628.xml";
    protected static final String NL = "nl";
    protected static final String AGGREGATION_LEVEL_VALUE = "2";
    protected static final String NO = "no";
    protected static final String YES = "yes";
    protected static final String ANDERS_DE_INTERACTIEVE_ANIMATIES_MOGEN_ALLEEN_BINNEN_HET_ONDERWIJS_WORDEN_GEBRUIKT_ZIE_OOK_DE_GEBRUIKSVOORWAARDEN = "Anders, de interactieve animaties mogen alleen binnen het onderwijs worden gebruikt. Zie ook de gebruiksvoorwaarden";

    private static Logger log = LoggerFactory.getLogger(NLLom.class);

    private static final ObjectFactory factory = new ObjectFactory();

    private LomType lomType = factory.createLomType();
    private final GeneralType generalType = factory.createGeneralType();
    private final MetametadataType metametadataType = factory.createMetametadataType();
    private final EducationalType educationalType = factory.createEducationalType();
    private final RightsType rightsType = factory.createRightsType();
    private final TechnicalType technicalType = factory.createTechnicalType();
    private final LifecycleType lifecycleType = factory.createLifecycleType();


    public NLLom() {
        lomType.setGeneral(generalType);
        lomType.setEducational(educationalType);
        lomType.setRights(rightsType);
        lomType.setMetametadata(metametadataType);
        lomType.setTechnical(technicalType);
        lomType.setLifecycle(lifecycleType);
    }

    public void setCatalogEntry(final String catalogEntryCatalog, final String catalogEntryEntryLangString, final String lang) {
        final CatalogentryType catalogentryType = factory.createCatalogentryType();
        catalogentryType.getContent().add(factory.createCatalog(catalogEntryCatalog));

        final LangstringType langstringType = factory.createLangstringType();
        langstringType.setValue(catalogEntryEntryLangString);
        langstringType.setLang(lang);

        final EntryType entryType = new EntryType();
        entryType.getLangstring().add(langstringType);

        catalogentryType.getContent().add(factory.createEntry(entryType));

        generalType.getContent().add(factory.createCatalogentry(catalogentryType));
    }

    public void setCatalogEntry(final String catalogEntryCatalog, final String catalogEntryEntryLangString) {
        setCatalogEntry(catalogEntryCatalog, catalogEntryEntryLangString, null);
    }


    public void setTitle(final String title) {
        setTitle(title, NL);
    }

    public void setTitle(final String title, final String lang) {
        final LangstringType langstringType = factory.createLangstringType();
        langstringType.setValue(title);
        langstringType.setLang(lang);

        final TitleType titleType = factory.createTitleType();
        titleType.getLangstring().add(langstringType);

        generalType.getContent().add(factory.createTitle(titleType));
    }

    public void addLanguage(final String language) {
        generalType.getContent().add(factory.createLanguage(language));
    }

    public void setDescription(final String description) {
        setDescription(description, NL);
    }

    public void setDescription(final String description, final String lang) {
        final LangstringType langstringType = factory.createLangstringType();
        langstringType.setValue(description);
        langstringType.setLang(lang);

        final DescriptionType descriptionType = factory.createDescriptionType();
        descriptionType.getLangstring().add(langstringType);

        generalType.getContent().add(factory.createDescription(descriptionType));
    }

    public void addKeyword(final String keyword) {
        addKeyword(keyword, NL);
    }

    public void addKeyword(final String keyword, final String lang) {
        final LangstringType langstringType = factory.createLangstringType();
        langstringType.setValue(keyword);
        langstringType.setLang(lang);

        final KeywordType keywordType = factory.createKeywordType();
        keywordType.getLangstring().add(langstringType);

        generalType.getContent().add(factory.createKeyword(keywordType));
    }

    public void setAggregationLevel(final String aggregationLevelValue) {
        setAggregationLevel(HTTP_PURL_EDUSTANDAARD_NL_VDEX_AGGREGATIONLEVEL_CZP_20060628_XML, aggregationLevelValue, X_NONE);
    }

    public void setAggregationLevel() {
        setAggregationLevel(HTTP_PURL_EDUSTANDAARD_NL_VDEX_AGGREGATIONLEVEL_CZP_20060628_XML, AGGREGATION_LEVEL_VALUE, X_NONE);
    }

    public void setAggregationLevel(final String aggregationLevelSource, final String aggregationLevelValue, final String lang) {
        final LangstringType langstringTypeSource = factory.createLangstringType();
        langstringTypeSource.setValue(aggregationLevelSource);
        langstringTypeSource.setLang(lang);

        final LangstringType langstringTypeValue = factory.createLangstringType();
        langstringTypeValue.setValue(aggregationLevelValue);
        langstringTypeValue.setLang(lang);

        final SourceType sourceType = factory.createSourceType();
        sourceType.setLangstring(langstringTypeSource);

        final ValueType valueType = factory.createValueType();
        valueType.setLangstring(langstringTypeValue);

        final AggregationlevelType aggregationlevelType = factory.createAggregationlevelType();
        aggregationlevelType.setSource(sourceType);
        aggregationlevelType.setValue(valueType);

        generalType.getContent().add(factory.createAggregationlevel(aggregationlevelType));
    }

    public void addIntendedEndUserRole(final String role) {
        addIntendedEndUserRole(HTTP_PURL_EDUSTANDAARD_NL_VDEX_INTENDEDENDUSERROLE_LOMV1P0_20060628_XML, role, X_NONE);
    }

    public void addIntendedEndUserRole(final String source, final String role, final String lang) {
        final LangstringType langstringTypeSource = factory.createLangstringType();
        langstringTypeSource.setValue(source);
        langstringTypeSource.setLang(lang);

        final LangstringType langstringTypeValue = factory.createLangstringType();
        langstringTypeValue.setValue(role);
        langstringTypeValue.setLang(lang);

        final SourceType sourceType = factory.createSourceType();
        sourceType.setLangstring(langstringTypeSource);

        final ValueType valueType = factory.createValueType();
        valueType.setLangstring(langstringTypeValue);

        final IntendedenduserroleType intendedenduserroleType = factory.createIntendedenduserroleType();
        intendedenduserroleType.setSource(sourceType);
        intendedenduserroleType.setValue(valueType);

        educationalType.getContent().add(factory.createIntendedenduserrole(intendedenduserroleType));
    }

    public void addContext(final String value) {
        addContext(HTTP_PURL_EDUSTANDAARD_NL_VDEX_CONTEXT_CZP_20060628_XML, value, X_NONE);
    }

    public void addContext(final String source, final String value, final String lang) {
        final LangstringType langstringTypeSource = factory.createLangstringType();
        langstringTypeSource.setValue(source);
        langstringTypeSource.setLang(lang);

        final LangstringType langstringTypeValue = factory.createLangstringType();
        langstringTypeValue.setValue(value);
        langstringTypeValue.setLang(lang);

        final SourceType sourceType = factory.createSourceType();
        sourceType.setLangstring(langstringTypeSource);

        final ValueType valueType = factory.createValueType();
        valueType.setLangstring(langstringTypeValue);

        final ContextType contextType = factory.createContextType();
        contextType.setSource(sourceType);
        contextType.setValue(valueType);

        educationalType.getContent().add(factory.createContext(contextType));
    }

    public void setTypicalDateRange(final String dateRange) {
        setTypicalDateRange(dateRange, X_NONE);
    }

    public void setTypicalDateRange(final String dateRange, final String lang) {
        final LangstringType langstringType = factory.createLangstringType();
        langstringType.setValue(dateRange);
        langstringType.setLang(lang);

        final TypicalagerangeType typicalagerangeType = factory.createTypicalagerangeType();
        typicalagerangeType.getLangstring().add(langstringType);

        educationalType.getContent().add(factory.createTypicalagerange(typicalagerangeType));
    }

    public void setCosts(final String source, final String value, final String lang) {
        final LangstringType langstringTypeSource = factory.createLangstringType();
        langstringTypeSource.setValue(source);
        langstringTypeSource.setLang(lang);

        final LangstringType langstringTypeValue = factory.createLangstringType();
        langstringTypeValue.setValue(value);
        langstringTypeValue.setLang(lang);

        final SourceType sourceType = factory.createSourceType();
        sourceType.setLangstring(langstringTypeSource);

        final ValueType valueType = factory.createValueType();
        valueType.setLangstring(langstringTypeValue);

        final CostType costType = factory.createCostType();
        costType.setSource(sourceType);
        costType.setValue(valueType);

        rightsType.getContent().add(factory.createCost(costType));

    }

    public void setCosts(final String value) {
        setCosts(HTTP_PURL_EDUSTANDAARD_NL_VDEX_COST_LOMV1P0_20060628_XML, value, X_NONE);

    }

    public void setCosts() {
        setCosts(HTTP_PURL_EDUSTANDAARD_NL_VDEX_COST_LOMV1P0_20060628_XML, NO, X_NONE);
    }

    public void setCopyRightAndOtherRestrictions(final String source, final String value, final String lang) {
        final LangstringType langstringTypeSource = factory.createLangstringType();
        langstringTypeSource.setValue(source);
        langstringTypeSource.setLang(lang);

        final LangstringType langstringTypeValue = factory.createLangstringType();
        langstringTypeValue.setValue(value);
        langstringTypeValue.setLang(lang);

        final SourceType sourceType = factory.createSourceType();
        sourceType.setLangstring(langstringTypeSource);

        final ValueType valueType = factory.createValueType();
        valueType.setLangstring(langstringTypeValue);

        final CopyrightandotherrestrictionsType copyrightandotherrestrictionsType = factory.createCopyrightandotherrestrictionsType();
        copyrightandotherrestrictionsType.setSource(sourceType);
        copyrightandotherrestrictionsType.setValue(valueType);

        rightsType.getContent().add(factory.createCopyrightandotherrestrictions(copyrightandotherrestrictionsType));
    }

    public void setCopyRightsDescription(final String value) {
        final LangstringType langstringType = factory.createLangstringType();
        langstringType.setValue(value);
        langstringType.setLang(NL);

        DescriptionType descriptionType = factory.createDescriptionType();
        descriptionType.getLangstring().add(langstringType);

        rightsType.getContent().add(factory.createDescription(descriptionType));
    }

    public void setCopyRightsDescription() {
        final LangstringType langstringType = factory.createLangstringType();
        langstringType.setValue(ANDERS_DE_INTERACTIEVE_ANIMATIES_MOGEN_ALLEEN_BINNEN_HET_ONDERWIJS_WORDEN_GEBRUIKT_ZIE_OOK_DE_GEBRUIKSVOORWAARDEN);
        langstringType.setLang(NL);

        DescriptionType descriptionType = factory.createDescriptionType();
        descriptionType.getLangstring().add(langstringType);

        rightsType.getContent().add(factory.createDescription(descriptionType));
    }

    public void setCopyRightAndOtherRestrictions(final String value) {
        setCopyRightAndOtherRestrictions(HTTP_PURL_EDUSTANDAARD_NL_VDEX_COPYRIGHTSANDOTHERRESTRICTIONS_LOMV1P0_20060628_XML, value, X_NONE);
    }

    public void setCopyRightAndOtherRestrictions() {
        setCopyRightAndOtherRestrictions(HTTP_PURL_EDUSTANDAARD_NL_VDEX_COPYRIGHTSANDOTHERRESTRICTIONS_LOMV1P0_20060628_XML, YES, X_NONE);
    }

    public void addClassification(final String purpose, final String taxonPathSource, final Map<String, String> term) {
        addClassification(X_NONE, HTTP_PURL_EDUSTANDAARD_NL_VDEX_CLASSIFICATION_PURPOSE_CZP_20060628_XML, purpose, taxonPathSource, term);
    }

    public void addClassification(final String lang, final String source, final String purpose, final String taxonPathSource, final Map<String, String> term) {
        final ClassificationType classificationType = factory.createClassificationType();

        final LangstringType langstringTypeSource = factory.createLangstringType();
        langstringTypeSource.setValue(source);
        langstringTypeSource.setLang(lang);

        final LangstringType langstringTypeValue = factory.createLangstringType();
        langstringTypeValue.setValue(purpose);
        langstringTypeValue.setLang(lang);

        final SourceType sourceType = factory.createSourceType();
        sourceType.setLangstring(langstringTypeSource);

        final ValueType valueType = factory.createValueType();
        valueType.setLangstring(langstringTypeValue);

        final PurposeType purposeType = factory.createPurposeType();
        purposeType.setSource(sourceType);
        purposeType.setValue(valueType);

        classificationType.getContent().add(factory.createPurpose(purposeType));

        final LangstringType langstringTaxonSourceTypeSource = factory.createLangstringType();
        langstringTaxonSourceTypeSource.setValue(taxonPathSource);
        langstringTaxonSourceTypeSource.setLang(lang);

        final SourceType taxonPathSourceType = factory.createSourceType();
        taxonPathSourceType.setLangstring(langstringTaxonSourceTypeSource);

        final TaxonpathType taxonpathType = factory.createTaxonpathType();
        taxonpathType.setSource(taxonPathSourceType);

        for (Map.Entry<String, String> taxon : term.entrySet()) {
            final TaxonType taxonType = new TaxonType();
            taxonType.setId(taxon.getKey());

            final LangstringType langstringType = factory.createLangstringType();
            langstringType.setLang(NL);
            langstringType.setValue(taxon.getValue());
            final EntryType entryType = factory.createEntryType();
            entryType.getLangstring().add(langstringType);
            taxonType.setEntry(entryType);
            taxonpathType.getTaxon().add(taxonType);
        }

        classificationType.getContent().add(factory.createTaxonpath(taxonpathType));

        lomType.getClassification().add(classificationType);
    }

    public void setMetametadata() {
        final JAXBElement<String> metadatascheme1 = factory.createMetadatascheme(LOMV1_0);
        final JAXBElement<String> metadatascheme2 = factory.createMetadatascheme(NL_LOM_V1P0_0);

        final List<Object> content = lomType.getMetametadata().getContent();

        content.add(metadatascheme1);
        content.add(metadatascheme2);
    }


    public JAXBElement<LomType> getLom() {
        return factory.createLom(lomType);
    }

    protected void cleanLomtype() {
        lomType = new LomType();
    }

    public LomType getLomType() {
        return lomType;
    }
}
