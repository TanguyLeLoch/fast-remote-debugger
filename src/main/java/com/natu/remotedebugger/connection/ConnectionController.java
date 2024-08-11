package com.natu.remotedebugger.connection;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connections")
public class ConnectionController {

    private final ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @PostMapping("/connect")
    public String connect(
            @RequestParam("hostname") String hostname,
            @RequestParam("port") int port) {
        connectionService.connect(hostname, port);
        return "ok";
    }

    @PostMapping("/disconnect")
    public String disconnect() {
        connectionService.disconnect();
        return "ok";
    }

    @GetMapping("/isConnected")
    public boolean isConnected() {
        return connectionService.isConnected();
    }
}
