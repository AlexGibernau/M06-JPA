package org.agcpag.pojos;

import org.agcpag.annotations.Column;
import org.agcpag.annotations.Entity;

@Entity(name = "departments")
public class Department {

    @Column(name = "_id")
    private long id;

    @Column(name = "name")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
