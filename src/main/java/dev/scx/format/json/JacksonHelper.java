package dev.scx.format.json;

import tools.jackson.core.*;
import tools.jackson.core.json.JsonReadFeature;
import tools.jackson.core.json.JsonWriteFeature;

import static tools.jackson.core.StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION;
import static tools.jackson.core.StreamWriteFeature.WRITE_BIGDECIMAL_AS_PLAIN;
import static tools.jackson.core.json.JsonReadFeature.*;
import static tools.jackson.core.json.JsonWriteFeature.*;

/// JacksonHelper
///
/// @author scx567888
final class JacksonHelper {

    private final static int DEFAULT_FACTORY_FEATURE_FLAGS = TokenStreamFactory.Feature.collectDefaults();
    private final static int DEFAULT_STREAM_READ_FEATURE_FLAGS = StreamReadFeature.collectDefaults();
    private final static int DEFAULT_STREAM_WRITE_FEATURE_FLAGS = StreamWriteFeature.collectDefaults();
    private final static int DEFAULT_JSON_PARSER_FEATURE_FLAGS = JsonReadFeature.collectDefaults();
    private final static int DEFAULT_JSON_GENERATOR_FEATURE_FLAGS = JsonWriteFeature.collectDefaults();

    public static int enable(TokenStreamFactory.Feature f, int features) {
        features |= f.getMask();
        return features;
    }

    public static int disable(TokenStreamFactory.Feature f, int features) {
        features &= ~f.getMask();
        return features;
    }

    public static int configure(TokenStreamFactory.Feature f, boolean state, int features) {
        return state ? enable(f, features) : disable(f, features);
    }

    public static int enable(StreamReadFeature f, int features) {
        features |= f.getMask();
        return features;
    }

    public static int disable(StreamReadFeature f, int features) {
        features &= ~f.getMask();
        return features;
    }

    public static int configure(StreamReadFeature f, boolean state, int features) {
        return state ? enable(f, features) : disable(f, features);
    }

    public static int enable(StreamWriteFeature f, int features) {
        features |= f.getMask();
        return features;
    }

    public static int disable(StreamWriteFeature f, int features) {
        features &= ~f.getMask();
        return features;
    }

    public static int configure(StreamWriteFeature f, boolean state, int features) {
        return state ? enable(f, features) : disable(f, features);
    }

    public static int enable(JsonReadFeature f, int features) {
        features |= f.getMask();
        return features;
    }

    public static int disable(JsonReadFeature f, int features) {
        features &= ~f.getMask();
        return features;
    }

    public static int configure(JsonReadFeature f, boolean state, int features) {
        return state ? enable(f, features) : disable(f, features);
    }

    public static int enable(JsonWriteFeature f, int features) {
        features |= f.getMask();
        return features;
    }

    public static int disable(JsonWriteFeature f, int features) {
        features &= ~f.getMask();
        return features;
    }

    public static int configure(JsonWriteFeature f, boolean state, int features) {
        return state ? enable(f, features) : disable(f, features);
    }

    public static int toFactoryFeatures(JsonNodeConvertOptions options) {
        // 对于 FactoryFeatures 我们永远返回默认值.
        return DEFAULT_FACTORY_FEATURE_FLAGS;
    }

    public static int toStreamReadFeatures(JsonNodeConvertOptions options) {
        var _streamReadFeatures = DEFAULT_STREAM_READ_FEATURE_FLAGS;
        _streamReadFeatures = configure(INCLUDE_SOURCE_IN_LOCATION, options.includeSourceInLocation(), _streamReadFeatures);
        return _streamReadFeatures;
    }

    public static int toStreamWriteFeature(JsonNodeConvertOptions options) {
        var _streamWriteFeatures = DEFAULT_STREAM_WRITE_FEATURE_FLAGS;
        _streamWriteFeatures = configure(WRITE_BIGDECIMAL_AS_PLAIN, options.writeBigDecimalAsPlain(), _streamWriteFeatures);
        return _streamWriteFeatures;
    }

    public static int toFormatReadFeatures(JsonNodeConvertOptions options) {
        var _formatReadFeatures = DEFAULT_JSON_PARSER_FEATURE_FLAGS;
        _formatReadFeatures = configure(ALLOW_JAVA_COMMENTS, options.allowJavaComments(), _formatReadFeatures);
        _formatReadFeatures = configure(ALLOW_YAML_COMMENTS, options.allowYamlComments(), _formatReadFeatures);
        _formatReadFeatures = configure(ALLOW_SINGLE_QUOTES, options.allowSingleQuotes(), _formatReadFeatures);
        _formatReadFeatures = configure(ALLOW_UNQUOTED_PROPERTY_NAMES, options.allowUnquotedPropertyNames(), _formatReadFeatures);
        _formatReadFeatures = configure(ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS, options.allowLeadingDecimalPointForNumbers(), _formatReadFeatures);
        _formatReadFeatures = configure(ALLOW_LEADING_PLUS_SIGN_FOR_NUMBERS, options.allowLeadingPlusSignForNumbers(), _formatReadFeatures);
        _formatReadFeatures = configure(ALLOW_LEADING_ZEROS_FOR_NUMBERS, options.allowLeadingZerosForNumbers(), _formatReadFeatures);
        _formatReadFeatures = configure(ALLOW_NON_NUMERIC_NUMBERS, options.allowNonNumericNumbers(), _formatReadFeatures);
        _formatReadFeatures = configure(ALLOW_TRAILING_DECIMAL_POINT_FOR_NUMBERS, options.allowTrailingDecimalPointForNumbers(), _formatReadFeatures);
        _formatReadFeatures = configure(ALLOW_MISSING_VALUES, options.allowMissingValues(), _formatReadFeatures);
        _formatReadFeatures = configure(ALLOW_TRAILING_COMMA, options.allowTrailingComma(), _formatReadFeatures);
        return _formatReadFeatures;
    }

    public static int toFormatWriteFeatures(JsonNodeConvertOptions options) {
        var _formatWriteFeatures = DEFAULT_JSON_GENERATOR_FEATURE_FLAGS;
        _formatWriteFeatures = configure(QUOTE_PROPERTY_NAMES, options.quotePropertyNames(), _formatWriteFeatures);
        _formatWriteFeatures = configure(WRITE_NAN_AS_STRINGS, options.writeNanAsStrings(), _formatWriteFeatures);
        _formatWriteFeatures = configure(ESCAPE_NON_ASCII, options.escapeNonAscii(), _formatWriteFeatures);
        _formatWriteFeatures = configure(WRITE_NUMBERS_AS_STRINGS, options.writeNumbersAsStrings(), _formatWriteFeatures);
        return _formatWriteFeatures;
    }

    public static StreamReadConstraints toStreamReadConstraints(JsonNodeConvertOptions options) {
        return StreamReadConstraints.builder()
            .maxNestingDepth(options.maxNestingDepth())
            .maxDocumentLength(options.maxDocumentLength())
            .maxTokenCount(options.maxTokenCount())
            .maxNumberLength(options.maxNumberLength())
            .maxStringLength(options.maxStringLength())
            .maxNameLength(options.maxNameLength())
            .build();
    }

    public static StreamWriteConstraints toStreamWriteConstraints(JsonNodeConvertOptions options) {
        return StreamWriteConstraints.builder()
            .maxNestingDepth(options.maxNestingDepth())
            .build();
    }

}
