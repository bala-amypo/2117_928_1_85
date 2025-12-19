package com.example.demo.controller;

import com.example.demo.entity.RatingLog;
import com.example.demo.service.RatingLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/logs")
@Tag(name = "Rating Logs")
public class RatingLogController {

    private final RatingLogService service;

    public RatingLogController(RatingLogService service) {
        this.service = service;
    }

    @PostMapping("/{propertyId}")
    @Operation(summary = "Add rating log")
    public RatingLog add(@PathVariable Long propertyId,
                         @RequestParam String message) {
        return service.addLog(propertyId, message);
    }

    @GetMapping("/property/{propertyId}")
    @Operation(summary = "View logs for property")
    public List<RatingLog> logs(@PathVariable Long propertyId) {
        return service.getLogs(propertyId);
    }
}
