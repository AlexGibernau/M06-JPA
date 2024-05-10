package org.agcpag.aaaother.entities;

import org.agcpag.annotations.Column;
import org.agcpag.annotations.Entity;
import org.agcpag.annotations.Id;

@Entity(name = "departments")
public class Department {
    @Id
    private long id;
    
    @Column(name = "name")
    private String name;

    // GETTERS AND SETTERS
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
