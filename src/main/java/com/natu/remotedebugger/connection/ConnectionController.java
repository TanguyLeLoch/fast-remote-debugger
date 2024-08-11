package com.natu.remotedebugger.connection;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connections")
public class ConnectionController {

    private final ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PostMapping(value = "/connect")
    public void connect(
            @RequestParam("hostname") String hostname,
            @RequestParam("port") int port) {
        connectionService.connect(hostname, port);
    }

    @PostMapping(value = "/disconnect")
    public void disconnect() {
        connectionService.disconnect();
    }

    @GetMapping(
            value = "/isConnected",
            produces = "application/json"
    )
    public SuccessResponse isConnected() {
        return new SuccessResponse(connectionService.isConnected());
    }
}
