package com.natu.remotedebugger.breakpoint;

import java.util.Map;

public class BreakpointHitResponse {
    private BreakpointHit hit;

    public BreakpointHitResponse(BreakpointHit hit) {
        this.hit = hit;
    }

    public String getSourceName() {
        return hit.getSourceName();
    }

    public int getLineNumber() {
        return hit.getLineNumber();
    }

    public String getMethodName() {
        return hit.getMethodName();
    }


    public Map<String, Variable> getLocalVariable() {
        return hit.getLocalVariable();
    }

}
