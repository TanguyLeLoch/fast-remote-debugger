package com.natu.remotedebugger.breakpoint;

import com.sun.jdi.Field;
import com.sun.jdi.ObjectReference;

public class FieldVariable {
    private final Field field;
    private final ObjectReference objectReference;


    public FieldVariable(ObjectReference objectReference, Field field) {
        this.field = field;
        this.objectReference = objectReference;
    }

    public String getName() {
        return field.name();
    }

    public String getType() {
        return field.typeName();
    }

    public String getValue() {
        return objectReference.getValue(field).toString();
    }
}
