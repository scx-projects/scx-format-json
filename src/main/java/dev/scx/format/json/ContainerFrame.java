package dev.scx.format.json;

import dev.scx.node.ContainerNode;

import java.util.Iterator;

/// ContainerFrame
///
/// @author scx567888
public record ContainerFrame(ContainerNode container, Iterator<?> iterator) {

    public ContainerFrame(ContainerNode container) {
        this(container, null);
    }

}
