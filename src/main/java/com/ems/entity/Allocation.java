package com.ems.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "allocations")
public class Allocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allocationID;

    @ManyToOne
    @JoinColumn(name = "eventid", referencedColumnName = "eventid", nullable = false)
    @JsonIgnore
    private Event event;

    @ManyToOne
    @JoinColumn(name = "resourceid", referencedColumnName = "resourceid", nullable = false)
    private Resource resource;

    private int quantity;

    public Allocation() {}

    public Allocation(Long allocationID, Event event, Resource resource, int quantity) {
        this.allocationID = allocationID;
        this.event = event;
        this.resource = resource;
        this.quantity = quantity;
    }

    public Long getAllocationID() { return allocationID; }
    public void setAllocationID(Long allocationID) { this.allocationID = allocationID; }

    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }

    public Resource getResource() { return resource; }
    public void setResource(Resource resource) { this.resource = resource; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
