package com.ems.controller;

import com.ems.entity.Event;
import com.ems.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StaffController {

    @Autowired
    private EventService eventService;

    @GetMapping("/api/staff/event-details/{eventId}")
    public ResponseEntity<Event> getEventDetails(@PathVariable Long eventId) {
        Event event = eventService.getEventDetails(eventId);
        if (event != null) {
            return ResponseEntity.status(HttpStatus.OK).body(event);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/api/staff/update-setup/{eventId}")
    public ResponseEntity<Event> updateEventSetup(@PathVariable Long eventId, @RequestBody Event updatedEvent) {
        Event existingEvent = eventService.getEventDetails(eventId);
        if (existingEvent != null) {
            updatedEvent.setEventID(existingEvent.getEventID());
            Event savedEvent = eventService.updateEventSetup(eventId, updatedEvent);
            return ResponseEntity.status(HttpStatus.OK).body(savedEvent);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
