package com.natu.remotedebugger.breakpoint;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.Location;

import java.util.Map;

public class BreakpointHit {
    private final Location location;
    private final Map<String, Variable> localVariable;

    public BreakpointHit(Location location,
            Map<String, Variable> localVariable) {
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

    public Map<String, Variable> getLocalVariable() {
        return localVariable;
    }


}
