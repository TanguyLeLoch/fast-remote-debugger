package com.natu.remotedebugger.connection;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connections")
public class ConnectionController {

    private final ConnectionService connectionService;

    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @RequestMapping("/connect")
    public String connect(
            @RequestParam("hostname") String hostname,
            @RequestParam("port") int port) {
        connectionService.connect(hostname, port);
        return "ok";
    }
}
