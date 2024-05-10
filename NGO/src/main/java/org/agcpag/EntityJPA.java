package org.agcpag;

import org.agcpag.annotations.Column;
import org.agcpag.annotations.Entity;
import org.agcpag.annotations.Id;

@Entity(name = "entityName")
public class EntityJPA {
    @Id
    private int id;

    @Column(name = "columnName")
    private String name;

}
