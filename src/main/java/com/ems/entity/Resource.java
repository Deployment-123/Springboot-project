package com.ems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resourceid") // Matches DB
    private Long resourceID;

    private String name;
    private String type;
    private boolean availability = true;

    public Resource() {}

    public Resource(Long resourceID, String name, String type, boolean availability) {
        this.resourceID = resourceID;
        this.name = name;
        this.type = type;
        this.availability = availability;
    }

    public Long getResourceID() { return resourceID; }
    public void setResourceID(Long resourceID) { this.resourceID = resourceID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isAvailability() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }
}
