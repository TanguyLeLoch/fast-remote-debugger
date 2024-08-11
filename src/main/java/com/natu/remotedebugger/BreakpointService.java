package com.natu.remotedebugger;

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

    public Map<String, String> getLocalVariable() {
        Map<String, String> localVariable = new HashMap<>();
        try {
            ThreadReference thread = getBlockedThread().orElseThrow(
                    RuntimeException::new);

            StackFrame frame = thread.frames().get(0);
            Location location = frame.location();
            System.out.println("At " + location.method()
                    .name() + " in " + location.sourceName() + " at line " + location.lineNumber());

            // Print local variables

            for (LocalVariable var : getVisibleVariables(frame)) {
                System.out.println(
                        var.name() + " = " + frame.getValue(var));
                localVariable.put(var.name(), frame.getValue(var).toString());
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

    public Map<String, Value> getAllBreakpoints() {
        return connector.getAllBreakpoints();
    }
}
