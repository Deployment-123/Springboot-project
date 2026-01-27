package com.ems.services;

import com.ems.entity.Allocation;
import com.ems.entity.Event;
import com.ems.entity.Resource;
import com.ems.repository.AllocationRepository;
import com.ems.repository.EventRepository;
import com.ems.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private AllocationRepository allocationRepository;

    @Autowired
    private EventRepository eventRepository;

    // Add a new resource
    public Resource addResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    // Get all resources
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    // Allocate resource to an event
    public void allocateResources(Long eventId, Long resourceId, Allocation allocation) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with ID: " + eventId));

        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new EntityNotFoundException("Resource not found with ID: " + resourceId));

        allocation.setEvent(event);
        allocation.setResource(resource);

        // Save allocation
        allocationRepository.save(allocation);

        // Add allocation to the event's allocation list if not null
        if (event.getAllocations() != null) {
            event.getAllocations().add(allocation);
       }

        eventRepository.save(event);

        // Mark resource as unavailable
        resource.setAvailability(false);
        resourceRepository.save(resource);
    }
}
