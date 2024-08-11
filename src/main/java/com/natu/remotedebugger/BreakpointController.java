package com.natu.remotedebugger;

import com.sun.jdi.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/breakpoints")
public class BreakpointController {


    private final BreakpointService breakpointService;

    public BreakpointController(BreakpointService breakpointService) {
        this.breakpointService = breakpointService;
    }

    @GetMapping
    public Map<String, String> getBreakpoint() {
        breakpointService.connect("localhost", 5005);
        return breakpointService.getLocalVariable();

    }

    @GetMapping("all")
    public Map<String, Value> getBreakpoints() {
        breakpointService.connect("localhost", 5005);
        return breakpointService.getAllBreakpoints();
    }


    @PostMapping
    public String addBreakpoint(@RequestParam String fileReference,
            @RequestParam int lineNumber) {
        breakpointService.connect("localhost", 5005);
        breakpointService.setBreakpoint(fileReference, lineNumber);
        return "Breakpoint add";
    }


}
