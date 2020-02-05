package sparksoniq.jsoniq.runtime.iterator.functions.durations.components;

import org.rumbledb.api.Item;
import org.rumbledb.exceptions.IteratorFlowException;

import sparksoniq.jsoniq.ExecutionMode;
import sparksoniq.jsoniq.item.ItemFactory;
import sparksoniq.jsoniq.runtime.iterator.RuntimeIterator;
import sparksoniq.jsoniq.runtime.iterator.functions.base.LocalFunctionCallIterator;
import sparksoniq.jsoniq.runtime.metadata.IteratorMetadata;
import sparksoniq.semantics.DynamicContext;

import java.util.List;

public class MonthsFromDurationFunctionIterator extends LocalFunctionCallIterator {

    private static final long serialVersionUID = 1L;
    private Item _durationItem = null;

    public MonthsFromDurationFunctionIterator(
            List<RuntimeIterator> arguments,
            ExecutionMode executionMode,
            IteratorMetadata iteratorMetadata
    ) {
        super(arguments, executionMode, iteratorMetadata);
    }

    @Override
    public Item next() {
        if (this._hasNext) {
            this._hasNext = false;
            return ItemFactory.getInstance().createIntegerItem(_durationItem.getDurationValue().getMonths());
        } else
            throw new IteratorFlowException(
                    RuntimeIterator.FLOW_EXCEPTION_MESSAGE + " months-from-duration function",
                    getMetadata()
            );
    }

    @Override
    public void open(DynamicContext context) {
        super.open(context);
        _durationItem = this._children.get(0).materializeFirstItemOrNull(_currentDynamicContextForLocalExecution);
        this._hasNext = _durationItem != null;
    }
}
