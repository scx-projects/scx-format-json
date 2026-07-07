package dev.scx.format.json;

import tools.jackson.core.base.DecorableTSFactory.DecorableTSFBuilder;
import tools.jackson.core.json.JsonFactoryBuilder;
import tools.jackson.core.sym.ByteQuadsCanonicalizer;
import tools.jackson.core.util.BufferRecycler;
import tools.jackson.core.util.RecyclerPool;

import static dev.scx.format.json.JacksonHelper.*;

/// 轻量级的 JsonFactory 创建器.
///
/// @author scx567888
/// @see JsonFactoryBuilder
final class LightJsonFactoryBuilder extends DecorableTSFBuilder<LightJsonFactory, LightJsonFactoryBuilder> {

    final JsonNodeConvertOptions _jacksonConfig;
    final ByteQuadsCanonicalizer _byteSymbolCanonicalizer;

    public LightJsonFactoryBuilder(JsonNodeConvertOptions options, ByteQuadsCanonicalizer byteSymbolCanonicalizer, RecyclerPool<BufferRecycler> recyclerPool) {
        super(
            toStreamReadConstraints(options),
            toStreamWriteConstraints(options),
            options.errorReportConfiguration(),
            toFormatReadFeatures(options),
            toFormatWriteFeatures(options)
        );
        this._jacksonConfig = options;
        this._byteSymbolCanonicalizer = byteSymbolCanonicalizer;
        this._recyclerPool = recyclerPool;
        this._factoryFeatures = toFactoryFeatures(options);
        this._streamReadFeatures = toStreamReadFeatures(options);
        this._streamWriteFeatures = toStreamWriteFeature(options);
    }

    @Override
    public LightJsonFactory build() {
        return new LightJsonFactory(this);
    }

}
