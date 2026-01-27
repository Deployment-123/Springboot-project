package com.ems.services;

import com.ems.entity.Event;
import com.ems.entity.User;
import com.ems.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // Create a new event
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get details of a specific event by ID
    public Event getEventDetails(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    // Update event setup
    public Event updateEventSetup(Long eventId, Event updatedEvent) {
        Event existingEvent = eventRepository.findById(eventId).orElse(null);
        if (existingEvent != null) {
            //updatedEvent.setEventID(existingEvent.getEventID());
            return eventRepository.save(updatedEvent);
        }
        return null;
    }
    

	



    
}
  