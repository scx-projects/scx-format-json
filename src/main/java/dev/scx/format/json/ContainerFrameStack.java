package dev.scx.format.json;

import java.util.Arrays;

/// ContainerFrameStack
///
/// @author scx567888
final class ContainerFrameStack {

    private ContainerFrame[] _stack;
    private int _top;
    private int _end;

    public ContainerFrameStack() {

    }

    // Not used yet but useful for limits (fail at [some high depth])
    public int size() {
        return _top;
    }

    public void push(ContainerFrame frame) {
        if (_top < _end) {
            _stack[_top] = frame; // lgtm [java/dereferenced-value-may-be-null]
            _top = _top + 1;
            return;
        }
        if (_stack == null) {
            _end = 10;
            _stack = new ContainerFrame[_end];
        } else {
            // grow by 50%, for most part
            _end += Math.min(4000, Math.max(20, _end / 2));
            _stack = Arrays.copyOf(_stack, _end);
        }
        _stack[_top] = frame;
        _top = _top + 1;
    }

    public ContainerFrame popOrNull() {
        if (_top == 0) {
            return null;
        }
        // note: could clean up stack but due to usage pattern, should not make
        // any difference -- all nodes joined during and after construction and
        // after construction the whole stack is discarded
        _top = _top - 1;
        return _stack[_top];
    }

}
