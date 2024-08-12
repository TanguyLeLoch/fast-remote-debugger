package com.natu.remotedebugger.breakpoint;

import jakarta.validation.constraints.NotNull;

import java.util.Collections;
import java.util.List;

public class BreakpointHitResponse {
    private final BreakpointHit hit;

    public BreakpointHitResponse(BreakpointHit hit) {
        this.hit = hit;
    }

    @NotNull
    public String getSourceName() {
        return hit.getSourceName();
    }

    @NotNull
    public int getLineNumber() {
        return hit.getLineNumber();
    }

    @NotNull
    public String getMethodName() {
        return hit.getMethodName();
    }


    @NotNull
    public List<Variable> getLocalVariable() {
        return Collections.unmodifiableList(hit.getLocalVariable());
    }
}
