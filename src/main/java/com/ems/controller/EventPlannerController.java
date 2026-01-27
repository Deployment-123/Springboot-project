package com.ems.controller;

import com.ems.entity.Allocation;
import com.ems.entity.Event;
import com.ems.entity.Resource;
import com.ems.entity.User;
import com.ems.services.EventService;
import com.ems.services.ResourceService;
import com.ems.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/planner")
public class EventPlannerController {

    @Autowired
    private EventService eventService;
    
    @Autowired
    private UserService userservice;

    @Autowired
    private ResourceService resourceService;

    // Create a new event
    @PostMapping("/event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvent);
    }
    
    @GetMapping("/eventbyname/{user}")
    public User  fetchbyname(@PathVariable String user) {
    	
    	return userservice.fetchbyname(user);
    	
    }

    // Get all events
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    // Add a new resource
    @PostMapping("/resource")
    public ResponseEntity<Resource> addResource(@RequestBody Resource resource) {
        Resource addedResource = resourceService.addResource(resource);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedResource);
    }

    // Get all resources
    @GetMapping("/resources")
    public ResponseEntity<List<Resource>> getAllResources() {
        List<Resource> resources = resourceService.getAllResources();
        return ResponseEntity.ok(resources);
    }

    // Allocate resources to an event
    @PostMapping("/allocate-resources")
    public ResponseEntity<String> allocateResources(
            @RequestParam Long eventId,
            @RequestParam Long resourceId,
            @RequestBody Allocation allocation) {

        try {
            resourceService.allocateResources(eventId, resourceId, allocation);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("{\"message\": \"Resource allocated successfully for Event ID: " + eventId + "\"}");
        } catch (Exception e) {
            e.printStackTrace();   // 👈 THIS IS CRITICAL
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }

    

	    
    
}
