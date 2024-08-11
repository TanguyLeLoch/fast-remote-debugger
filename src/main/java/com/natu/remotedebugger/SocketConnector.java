package com.natu.remotedebugger;

import com.sun.jdi.*;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.jdi.request.BreakpointRequest;
import com.sun.jdi.request.EventRequestManager;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class SocketConnector {
    private final AttachingConnector connector;

    private VirtualMachine vm = null;

    public SocketConnector() {
        AttachingConnector connector = null;
        for (AttachingConnector c : Bootstrap.virtualMachineManager()
                .attachingConnectors()) {
            if (c.name().equals("com.sun.jdi.SocketAttach")) {
                connector = c;
                break;
            }
        }
        if (connector == null) {
            throw new RuntimeException(
                    "Could not find SocketAttach connector");
        }
        this.connector = connector;


    }

    public void connect(String hostname, int port) {
        try {
            Map<String, Connector.Argument> arguments = connector.defaultArguments();
            arguments.get("hostname").setValue(hostname);
            arguments.get("port").setValue(String.valueOf(port));
            vm = connector.attach(arguments);
        } catch (IllegalConnectorArgumentsException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<ThreadReference> getCurrentThread() {
        if (vm == null) {
            throw new RuntimeException("Connect using connect() first");
        }
        return vm.allThreads();
    }

    public void setBreakpoint(String fileReference, int lineNumber) {
        if (vm == null) {
            throw new RuntimeException("Connect using connect() first");
        }
        try {


            // Get the EventRequestManager to create breakpoints
            EventRequestManager erm = vm.eventRequestManager();

            // Find the reference to the desired class
            ReferenceType refType = null;
            for (ReferenceType type : vm.allClasses()) {
                if (type.name().equals(fileReference)) {
                    refType = type;
                    break;
                }
            }

            // Set a breakpoint at line 14
            Location location = refType.locationsOfLine(lineNumber).get(0);
            BreakpointRequest bpReq = erm.createBreakpointRequest(location);
            bpReq.enable();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Map<String, Value> getAllBreakpoints() {
        return null;
    }
}
