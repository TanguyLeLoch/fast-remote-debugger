package com.natu.remotedebugger.breakpoint;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.Location;

import java.util.Collections;
import java.util.List;

public class BreakpointHit {
    private final Location location;
    private final List<Variable> localVariable;

    public BreakpointHit(Location location,
            List<Variable> localVariable) {
        this.location = location;
        this.localVariable = localVariable;
    }

    public String getSourceName() {
        try {
            return location.sourceName();
        } catch (AbsentInformationException e) {
            return "unknown source name";
        }
    }

    public int getLineNumber() {
        return location.lineNumber();
    }

    public String getMethodName() {
        return location.method().name();
    }

    public List<Variable> getLocalVariable() {
        return Collections.unmodifiableList(localVariable);
    }
}
