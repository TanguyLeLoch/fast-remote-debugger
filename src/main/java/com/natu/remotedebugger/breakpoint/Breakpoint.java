package com.natu.remotedebugger.breakpoint;

public class Breakpoint {
    private final String fileReference;
    private final int lineNumber;

    public Breakpoint(String fileReference, int lineNumber) {
        this.fileReference = fileReference;
        this.lineNumber = lineNumber;
    }

    public String getFileReference() {
        return fileReference;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}