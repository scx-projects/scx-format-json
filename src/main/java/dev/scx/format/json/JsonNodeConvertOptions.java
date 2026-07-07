package dev.scx.format.json;

import dev.scx.format.FormatNodeConvertOptions;
import tools.jackson.core.ErrorReportConfiguration;
import tools.jackson.core.SerializableString;
import tools.jackson.core.io.CharacterEscapes;

/// JsonNodeConvertOptions
///
/// @author scx567888
public interface JsonNodeConvertOptions extends FormatNodeConvertOptions {

    boolean includeSourceInLocation();

    boolean writeBigDecimalAsPlain();

    boolean allowJavaComments();

    boolean allowYamlComments();

    boolean allowSingleQuotes();

    boolean allowUnquotedPropertyNames();

    boolean allowLeadingDecimalPointForNumbers();

    boolean allowLeadingPlusSignForNumbers();

    boolean allowLeadingZerosForNumbers();

    boolean allowNonNumericNumbers();

    boolean allowTrailingDecimalPointForNumbers();

    boolean allowMissingValues();

    boolean allowTrailingComma();

    boolean quotePropertyNames();

    boolean writeNanAsStrings();

    boolean escapeNonAscii();

    boolean writeNumbersAsStrings();

    int maxNestingDepth();

    long maxDocumentLength();

    long maxTokenCount();

    int maxNumberLength();

    int maxStringLength();

    int maxNameLength();

    ErrorReportConfiguration errorReportConfiguration();

    DuplicateFieldPolicy duplicateFieldPolicy();

    boolean prettyPrint();

    CharacterEscapes characterEscapes();

    SerializableString rootValueSeparator();

    int highestNonEscapedChar();

    char quoteChar();

}
