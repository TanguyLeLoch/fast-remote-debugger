package com.natu.remotedebugger.breakpoint;

import com.natu.remotedebugger.RemoteDebuggerConnector;
import com.natu.remotedebugger.common.exception.NoContentException;
import com.natu.remotedebugger.common.exception.TechnicalException;
import com.sun.jdi.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BreakpointService {
    RemoteDebuggerConnector connector;
    boolean isConnected = false;

    public BreakpointService(RemoteDebuggerConnector connector) {
        this.connector = connector;
    }

    public void connect(String hostname, int port) {
        if (isConnected) {
            return;
        }
        connector.connect(hostname, port);
        isConnected = true;
    }

    public void currentThread() {
        System.out.println(connector.getCurrentThread());
    }

    private Optional<ThreadReference> getBlockedThread() {
        var threads = connector.getCurrentThread();
        for (var thread : threads) {
            if (thread.isSuspended() && thread.isAtBreakpoint()) {
                return Optional.of(thread);
            }
        }
        return Optional.empty();
    }

    public void setBreakpoint(String fileReference, int lineNumber) {
        connector.setBreakpoint(fileReference, lineNumber);
    }

    public Map<String, Variable> getLocalVariable() {
        Map<String, Variable> localVariable = new HashMap<>();
        try {
            ThreadReference thread = getBlockedThread().orElseThrow(
                    RuntimeException::new);

            StackFrame frame = thread.frames().get(0);
            printLocation(frame);

            // Print local variables

            for (LocalVariable var : getVisibleVariables(frame)) {
                Variable v = new Variable(frame, var);
                localVariable.put(v.getName(), v);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return localVariable;
    }

    private static List<LocalVariable> getVisibleVariables(StackFrame frame) {
        try {
            return frame.visibleVariables();
        } catch (AbsentInformationException e) {
            return new ArrayList<>();
        }

    }

    public List<Breakpoint> getAllBreakpoints() {
        return connector.getAllBreakpoints();
    }

    public BreakpointHit getHit() {

        Optional<ThreadReference> threadOpt = getBlockedThread();
        if (threadOpt.isEmpty()) {
            throw new NoContentException("No breakpoint hit");
        }
        ThreadReference thread = threadOpt.get();

        var frames = getFrames(thread);
        var frame = frames.get(0);
        printLocation(frame);

        Map<String, Variable> localVariable = getLocalVariable();

        return new BreakpointHit(frame.location(), localVariable);

    }

    private static void printLocation(StackFrame frame) {
        Location location = frame.location();
        try {
            System.out.println("At " + location.method()
                    .name() + " in " + location.sourceName() + " at line " + location.lineNumber());
        } catch (AbsentInformationException e) {
            throw new TechnicalException("unable to get location", e);
        }
    }

    private static List<StackFrame> getFrames(ThreadReference thread) {
        try {
            return thread.frames();
        } catch (IncompatibleThreadStateException e) {
            throw new TechnicalException("unable to get frames", e);
        }
    }
}
