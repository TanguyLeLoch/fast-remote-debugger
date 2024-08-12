package com.natu.remotedebugger.breakpoint;

import com.sun.jdi.Field;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.Value;

import java.util.List;

public class FieldVariable extends Variable {
    private final Field field;
    private final ObjectReference objectReference;

    public FieldVariable(ObjectReference objectReference, Field field) {
        super(field.name(), field.typeName(),
                objectReference.getValue(field).toString());
        this.field = field;
        this.objectReference = objectReference;
        initFields();
    }


    @Override
    void initFields() {
        Value value = this.objectReference.getValue(field);
        if (value instanceof ObjectReference reference) {
            List<Field> subFields = reference.referenceType().fields();
            for (Field subField : subFields) {
                FieldVariable fieldVariable = new FieldVariable(
                        reference, subField);
                fields.add(fieldVariable);
            }
        }
    }


}
