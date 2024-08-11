package com.natu.remotedebugger.breakpoint;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Breakpoint")
public class BreakpointResponse {
    private final Breakpoint breakpoint;

    public BreakpointResponse(Breakpoint breakpoint) {
        this.breakpoint = breakpoint;
    }

    public String getFileReference() {
        return breakpoint.getFileReference();
    }

    public int getLineNumber() {
        return breakpoint.getLineNumber();
    }
}
