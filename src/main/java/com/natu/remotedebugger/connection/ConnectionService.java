package com.natu.remotedebugger.connection;

import com.natu.remotedebugger.RemoteDebuggerConnector;
import org.springframework.stereotype.Service;

@Service
public class ConnectionService {

    private final RemoteDebuggerConnector connector;

    public ConnectionService(RemoteDebuggerConnector connector) {
        this.connector = connector;
    }

    public void connect(String hostname, int port) {
        connector.connect(hostname, port);
    }
}
