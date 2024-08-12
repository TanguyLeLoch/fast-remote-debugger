package com.natu.remotedebugger.breakpoint;

import com.sun.jdi.Field;
import com.sun.jdi.LocalVariable;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.StackFrame;

import java.util.List;

public class TopLevelVariable extends Variable {
    private final StackFrame frame;
    private final LocalVariable localVar;

    public TopLevelVariable(StackFrame frame, LocalVariable variable) {
        super(variable.name(), variable.typeName(),
                frame.getValue(variable).toString());
        this.frame = frame;
        this.localVar = variable;
        initFields();
    }

    @Override
    void initFields() {
        if (frame.getValue(localVar) instanceof ObjectReference reference) {
            List<Field> f = reference.referenceType().fields();
            for (Field field : f) {
                FieldVariable fieldVariable = new FieldVariable(
                        reference, field);
                fields.add(fieldVariable);
            }
        }
    }

}
