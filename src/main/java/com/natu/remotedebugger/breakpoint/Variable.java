package com.natu.remotedebugger.breakpoint;

import com.sun.jdi.Field;
import com.sun.jdi.LocalVariable;
import com.sun.jdi.ObjectReference;
import com.sun.jdi.StackFrame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Variable {
    private final String name;
    private final String type;
    private final String value;
    //    private final String toString;
    private final Map<String, FieldVariable> fields;

    public Variable(StackFrame frame, LocalVariable var) {
        this.name = var.name();
        this.fields = new HashMap<>();
        if (frame.getValue(var) instanceof ObjectReference objectReference) {
            this.value = objectReference.referenceType().name();
            this.type = objectReference.referenceType().name();
            List<Field> fields = objectReference.referenceType().fields();
            for (Field field : fields) {
                FieldVariable fieldVariable = new FieldVariable(objectReference,
                        field);
                this.fields.put(field.name(), fieldVariable);
            }
        } else {
            this.value = frame.getValue(var).toString();
            this.type = var.typeName();
        }

    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public Map<String, FieldVariable> getFields() {
        return fields;
    }
}

