package dev.scx.format.json;

import tools.jackson.core.ErrorReportConfiguration;
import tools.jackson.core.SerializableString;
import tools.jackson.core.StreamReadConstraints;
import tools.jackson.core.io.CharacterEscapes;
import tools.jackson.core.util.DefaultPrettyPrinter;

import static tools.jackson.core.StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION;
import static tools.jackson.core.StreamWriteFeature.WRITE_BIGDECIMAL_AS_PLAIN;
import static tools.jackson.core.json.JsonReadFeature.*;
import static tools.jackson.core.json.JsonWriteFeature.*;

/// JsonNodeConvertConfig (本质上是 具备 setter 的 JsonNodeConvertOptions)
///
/// @author scx567888
public final class JsonNodeConvertConfig implements JsonNodeConvertOptions {

    private final static SerializableString DEFAULT_ROOT_VALUE_SEPARATOR = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
    private final static char DEFAULT_QUOTE_CHAR = '"';

    // _streamReadFeatures 相关
    private boolean includeSourceInLocation;

    // _streamWriteFeatures 相关
    private boolean writeBigDecimalAsPlain;

    // _formatReadFeatures 相关
    private boolean allowJavaComments;
    private boolean allowYamlComments;
    private boolean allowSingleQuotes;
    private boolean allowUnquotedPropertyNames;
    private boolean allowLeadingDecimalPointForNumbers;
    private boolean allowLeadingPlusSignForNumbers;
    private boolean allowLeadingZerosForNumbers;
    private boolean allowNonNumericNumbers;
    private boolean allowTrailingDecimalPointForNumbers;
    private boolean allowMissingValues;
    private boolean allowTrailingComma;

    // _formatWriteFeatures 相关
    private boolean quotePropertyNames;
    private boolean writeNanAsStrings;
    private boolean escapeNonAscii;
    private boolean writeNumbersAsStrings;

    // StreamReadConstraints/StreamWriteConstraints 相关
    private int maxNestingDepth;
    private long maxDocumentLength;
    private long maxTokenCount;
    private int maxNumberLength;
    private int maxStringLength;
    private int maxNameLength;

    // ErrorReportConfiguration 相关
    private ErrorReportConfiguration errorReportConfiguration;

    // 自定义配置
    private DuplicateFieldPolicy duplicateFieldPolicy;
    private boolean prettyPrint;

    // other
    private CharacterEscapes characterEscapes;
    private SerializableString rootValueSeparator;
    private int highestNonEscapedChar;
    private char quoteChar;

    private JsonNodeConvertConfig() {
        // _streamReadFeatures 相关
        this.includeSourceInLocation = INCLUDE_SOURCE_IN_LOCATION.enabledByDefault();
        // _streamWriteFeatures 相关
        this.writeBigDecimalAsPlain = WRITE_BIGDECIMAL_AS_PLAIN.enabledByDefault();
        // _formatReadFeatures 相关
        this.allowJavaComments = ALLOW_JAVA_COMMENTS.enabledByDefault();
        this.allowYamlComments = ALLOW_YAML_COMMENTS.enabledByDefault();
        this.allowSingleQuotes = ALLOW_SINGLE_QUOTES.enabledByDefault();
        this.allowUnquotedPropertyNames = ALLOW_UNQUOTED_PROPERTY_NAMES.enabledByDefault();
        this.allowLeadingDecimalPointForNumbers = ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.enabledByDefault();
        this.allowLeadingPlusSignForNumbers = ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS.enabledByDefault();
        this.allowLeadingZerosForNumbers = ALLOW_LEADING_ZEROS_FOR_NUMBERS.enabledByDefault();
        this.allowNonNumericNumbers = ALLOW_NON_NUMERIC_NUMBERS.enabledByDefault();
        this.allowTrailingDecimalPointForNumbers = ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS.enabledByDefault();
        this.allowMissingValues = ALLOW_MISSING_VALUES.enabledByDefault();
        this.allowTrailingComma = ALLOW_TRAILING_COMMA.enabledByDefault();
        // _formatWriteFeatures 相关
        this.quotePropertyNames = QUOTE_PROPERTY_NAMES.enabledByDefault();
        this.writeNanAsStrings = WRITE_NAN_AS_STRINGS.enabledByDefault();
        this.escapeNonAscii = ESCAPE_NON_ASCII.enabledByDefault();
        this.writeNumbersAsStrings = WRITE_NUMBERS_AS_STRINGS.enabledByDefault();
        // StreamReadConstraints/StreamWriteConstraints 相关
        this.maxNestingDepth = StreamReadConstraints.DEFAULT_MAX_DEPTH;
        this.maxDocumentLength = StreamReadConstraints.DEFAULT_MAX_DOC_LEN;
        this.maxTokenCount = StreamReadConstraints.DEFAULT_MAX_TOKEN_COUNT;
        this.maxNumberLength = StreamReadConstraints.DEFAULT_MAX_NUM_LEN;
        this.maxStringLength = StreamReadConstraints.DEFAULT_MAX_STRING_LEN;
        this.maxNameLength = StreamReadConstraints.DEFAULT_MAX_NAME_LEN;
        // ErrorReportConfiguration 相关
        this.errorReportConfiguration = ErrorReportConfiguration.defaults();
        // 自定义配置
        this.duplicateFieldPolicy = DuplicateFieldPolicy.USE_NEW;
        this.prettyPrint = false;
        // other
        this.characterEscapes = null;
        this.rootValueSeparator = DEFAULT_ROOT_VALUE_SEPARATOR;
        this.highestNonEscapedChar = 0; // disabled
        this.quoteChar = DEFAULT_QUOTE_CHAR;
    }

    public static JsonNodeConvertConfig of() {
        return new JsonNodeConvertConfig();
    }

    public static JsonNodeConvertConfig copyOf(JsonNodeConvertOptions options) {
        var config = new JsonNodeConvertConfig();
        config.includeSourceInLocation(options.includeSourceInLocation());
        config.writeBigDecimalAsPlain(options.writeBigDecimalAsPlain());
        config.allowJavaComments(options.allowJavaComments());
        config.allowYamlComments(options.allowYamlComments());
        config.allowSingleQuotes(options.allowSingleQuotes());
        config.allowUnquotedPropertyNames(options.allowUnquotedPropertyNames());
        config.allowLeadingDecimalPointForNumbers(options.allowLeadingDecimalPointForNumbers());
        config.allowLeadingPlusSignForNumbers(options.allowLeadingPlusSignForNumbers());
        config.allowLeadingZerosForNumbers(options.allowLeadingZerosForNumbers());
        config.allowNonNumericNumbers(options.allowNonNumericNumbers());
        config.allowTrailingDecimalPointForNumbers(options.allowTrailingDecimalPointForNumbers());
        config.allowMissingValues(options.allowMissingValues());
        config.allowTrailingComma(options.allowTrailingComma());
        config.quotePropertyNames(options.quotePropertyNames());
        config.writeNanAsStrings(options.writeNanAsStrings());
        config.escapeNonAscii(options.escapeNonAscii());
        config.writeNumbersAsStrings(options.writeNumbersAsStrings());
        config.maxNestingDepth(options.maxNestingDepth());
        config.maxDocumentLength(options.maxDocumentLength());
        config.maxTokenCount(options.maxTokenCount());
        config.maxNumberLength(options.maxNumberLength());
        config.maxStringLength(options.maxStringLength());
        config.maxNameLength(options.maxNameLength());
        config.errorReportConfiguration(options.errorReportConfiguration());
        config.duplicateFieldPolicy(options.duplicateFieldPolicy());
        config.prettyPrint(options.prettyPrint());
        config.characterEscapes(options.characterEscapes());
        config.rootValueSeparator(options.rootValueSeparator());
        config.highestNonEscapedChar(options.highestNonEscapedChar());
        config.quoteChar(options.quoteChar());
        return config;
    }

    @Override
    public boolean includeSourceInLocation() {
        return includeSourceInLocation;
    }

    public JsonNodeConvertConfig includeSourceInLocation(boolean includeSourceInLocation) {
        this.includeSourceInLocation = includeSourceInLocation;
        return this;
    }

    @Override
    public boolean writeBigDecimalAsPlain() {
        return writeBigDecimalAsPlain;
    }

    public JsonNodeConvertConfig writeBigDecimalAsPlain(boolean writeBigDecimalAsPlain) {
        this.writeBigDecimalAsPlain = writeBigDecimalAsPlain;
        return this;
    }

    @Override
    public boolean allowJavaComments() {
        return allowJavaComments;
    }

    public JsonNodeConvertConfig allowJavaComments(boolean allowJavaComments) {
        this.allowJavaComments = allowJavaComments;
        return this;
    }

    @Override
    public boolean allowYamlComments() {
        return allowYamlComments;
    }

    public JsonNodeConvertConfig allowYamlComments(boolean allowYamlComments) {
        this.allowYamlComments = allowYamlComments;
        return this;
    }

    @Override
    public boolean allowSingleQuotes() {
        return allowSingleQuotes;
    }

    public JsonNodeConvertConfig allowSingleQuotes(boolean allowSingleQuotes) {
        this.allowSingleQuotes = allowSingleQuotes;
        return this;
    }

    @Override
    public boolean allowUnquotedPropertyNames() {
        return allowUnquotedPropertyNames;
    }

    public JsonNodeConvertConfig allowUnquotedPropertyNames(boolean allowUnquotedPropertyNames) {
        this.allowUnquotedPropertyNames = allowUnquotedPropertyNames;
        return this;
    }

    @Override
    public boolean allowLeadingDecimalPointForNumbers() {
        return allowLeadingDecimalPointForNumbers;
    }

    public JsonNodeConvertConfig allowLeadingDecimalPointForNumbers(boolean allowLeadingDecimalPointForNumbers) {
        this.allowLeadingDecimalPointForNumbers = allowLeadingDecimalPointForNumbers;
        return this;
    }

    @Override
    public boolean allowLeadingPlusSignForNumbers() {
        return allowLeadingPlusSignForNumbers;
    }

    public JsonNodeConvertConfig allowLeadingPlusSignForNumbers(boolean allowLeadingPlusSignForNumbers) {
        this.allowLeadingPlusSignForNumbers = allowLeadingPlusSignForNumbers;
        return this;
    }

    @Override
    public boolean allowLeadingZerosForNumbers() {
        return allowLeadingZerosForNumbers;
    }

    public JsonNodeConvertConfig allowLeadingZerosForNumbers(boolean allowLeadingZerosForNumbers) {
        this.allowLeadingZerosForNumbers = allowLeadingZerosForNumbers;
        return this;
    }

    @Override
    public boolean allowNonNumericNumbers() {
        return allowNonNumericNumbers;
    }

    public JsonNodeConvertConfig allowNonNumericNumbers(boolean allowNonNumericNumbers) {
        this.allowNonNumericNumbers = allowNonNumericNumbers;
        return this;
    }

    @Override
    public boolean allowTrailingDecimalPointForNumbers() {
        return allowTrailingDecimalPointForNumbers;
    }

    public JsonNodeConvertConfig allowTrailingDecimalPointForNumbers(boolean allowTrailingDecimalPointForNumbers) {
        this.allowTrailingDecimalPointForNumbers = allowTrailingDecimalPointForNumbers;
        return this;
    }

    @Override
    public boolean allowMissingValues() {
        return allowMissingValues;
    }

    public JsonNodeConvertConfig allowMissingValues(boolean allowMissingValues) {
        this.allowMissingValues = allowMissingValues;
        return this;
    }

    @Override
    public boolean allowTrailingComma() {
        return allowTrailingComma;
    }

    public JsonNodeConvertConfig allowTrailingComma(boolean allowTrailingComma) {
        this.allowTrailingComma = allowTrailingComma;
        return this;
    }

    @Override
    public boolean quotePropertyNames() {
        return quotePropertyNames;
    }

    public JsonNodeConvertConfig quotePropertyNames(boolean quotePropertyNames) {
        this.quotePropertyNames = quotePropertyNames;
        return this;
    }

    @Override
    public boolean writeNanAsStrings() {
        return writeNanAsStrings;
    }

    public JsonNodeConvertConfig writeNanAsStrings(boolean writeNanAsStrings) {
        this.writeNanAsStrings = writeNanAsStrings;
        return this;
    }

    @Override
    public boolean escapeNonAscii() {
        return escapeNonAscii;
    }

    public JsonNodeConvertConfig escapeNonAscii(boolean escapeNonAscii) {
        this.escapeNonAscii = escapeNonAscii;
        return this;
    }

    @Override
    public boolean writeNumbersAsStrings() {
        return writeNumbersAsStrings;
    }

    public JsonNodeConvertConfig writeNumbersAsStrings(boolean writeNumbersAsStrings) {
        this.writeNumbersAsStrings = writeNumbersAsStrings;
        return this;
    }

    @Override
    public int maxNestingDepth() {
        return maxNestingDepth;
    }

    public JsonNodeConvertConfig maxNestingDepth(int maxNestingDepth) {
        this.maxNestingDepth = maxNestingDepth;
        return this;
    }

    @Override
    public long maxDocumentLength() {
        return maxDocumentLength;
    }

    public JsonNodeConvertConfig maxDocumentLength(long maxDocumentLength) {
        this.maxDocumentLength = maxDocumentLength;
        return this;
    }

    @Override
    public long maxTokenCount() {
        return maxTokenCount;
    }

    public JsonNodeConvertConfig maxTokenCount(long maxTokenCount) {
        this.maxTokenCount = maxTokenCount;
        return this;
    }

    @Override
    public int maxNumberLength() {
        return maxNumberLength;
    }

    public JsonNodeConvertConfig maxNumberLength(int maxNumberLength) {
        this.maxNumberLength = maxNumberLength;
        return this;
    }

    @Override
    public int maxStringLength() {
        return maxStringLength;
    }

    public JsonNodeConvertConfig maxStringLength(int maxStringLength) {
        this.maxStringLength = maxStringLength;
        return this;
    }

    @Override
    public int maxNameLength() {
        return maxNameLength;
    }

    public JsonNodeConvertConfig maxNameLength(int maxNameLength) {
        this.maxNameLength = maxNameLength;
        return this;
    }

    @Override
    public ErrorReportConfiguration errorReportConfiguration() {
        return errorReportConfiguration;
    }

    public JsonNodeConvertConfig errorReportConfiguration(ErrorReportConfiguration errorReportConfiguration) {
        this.errorReportConfiguration = errorReportConfiguration;
        return this;
    }

    @Override
    public DuplicateFieldPolicy duplicateFieldPolicy() {
        return duplicateFieldPolicy;
    }

    public JsonNodeConvertConfig duplicateFieldPolicy(DuplicateFieldPolicy duplicateFieldPolicy) {
        this.duplicateFieldPolicy = duplicateFieldPolicy;
        return this;
    }

    @Override
    public boolean prettyPrint() {
        return prettyPrint;
    }

    public JsonNodeConvertConfig prettyPrint(boolean prettyPrint) {
        this.prettyPrint = prettyPrint;
        return this;
    }

    @Override
    public CharacterEscapes characterEscapes() {
        return characterEscapes;
    }

    public JsonNodeConvertConfig characterEscapes(CharacterEscapes characterEscapes) {
        this.characterEscapes = characterEscapes;
        return this;
    }

    @Override
    public SerializableString rootValueSeparator() {
        return rootValueSeparator;
    }

    public JsonNodeConvertConfig rootValueSeparator(SerializableString rootValueSeparator) {
        this.rootValueSeparator = rootValueSeparator;
        return this;
    }

    @Override
    public int highestNonEscapedChar() {
        return highestNonEscapedChar;
    }

    public JsonNodeConvertConfig highestNonEscapedChar(int highestNonEscapedChar) {
        this.highestNonEscapedChar = highestNonEscapedChar;
        return this;
    }

    @Override
    public char quoteChar() {
        return quoteChar;
    }

    public JsonNodeConvertConfig quoteChar(char quoteChar) {
        this.quoteChar = quoteChar;
        return this;
    }

}
