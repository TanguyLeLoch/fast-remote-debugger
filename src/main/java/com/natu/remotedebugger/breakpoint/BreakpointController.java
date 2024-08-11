package com.natu.remotedebugger.breakpoint;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/breakpoints")
public class BreakpointController {


    private final BreakpointService breakpointService;

    public BreakpointController(BreakpointService breakpointService) {
        this.breakpointService = breakpointService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BreakpointResponse> getBreakpoints() {
        List<Breakpoint> breakpoints = breakpointService.getAllBreakpoints();
        return breakpoints.stream().map(BreakpointResponse::new)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public BreakpointHitResponse getHits() {
        BreakpointHit hit = breakpointService.getHit();
        return new BreakpointHitResponse(hit);
    }


    @Operation(summary = "Add a breakpoint", description = "Sets a breakpoint in the specified file at the given line number.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Breakpoint added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping()
    public ResponseEntity<Void> addBreakpoint(
            @RequestParam String fileReference,
            @RequestParam int lineNumber) {
        breakpointService.setBreakpoint(fileReference, lineNumber);
        return ResponseEntity.ok().build();
    }


}
