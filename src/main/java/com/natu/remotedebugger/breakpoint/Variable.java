package com.natu.remotedebugger.breakpoint;

import jakarta.validation.constraints.NotNull;

public abstract class Variable {


    protected final Variables fields;
    private final String name;
    private final String type;
    private final String value;

    Variable(String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.fields = new Variables();
    }

    abstract void initFields();


    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getType() {
        return type;
    }

    @NotNull
    public String getValue() {
        return value;
    }

    @NotNull
    public Variables getFields() {
        return fields;
    }
}


