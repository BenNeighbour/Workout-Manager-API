package com.api.benneighbour.workoutManager.user.entity.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

// Entity annotation to flag to java.persistence
@Entity
// Table annotation to give the Entity a name when it creates all of the tables inside the Database
@Table(name = "role")
// Jackson annotation to hide it's lazy initialization handler, which interfered with the JSON response
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role implements Serializable {

    // Serializable version id for the class
    private static final long serialVersionUID = 3693677907638193522L;

    // The rid field for each role, connected to the corresponding uid
    @Id
    @Column(name = "rid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    // The name field for each user
    @Column(name = "name")
    private String name = "USER";

    // Default constructor with all it's fields
    public Role(String name, Long rid) {
        this.name = name;
        this.rid = rid;
    }

    /*
        Setters and Getters for the Role class so that the values in it's instance can be seen by
        the other classes in the service and security layers.
    */


    // The statements for the Role names
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    // The statements for the Role rids
    public Long getRid() {
        return rid;
    }
    public void setRid(Long rid) {
        this.rid = rid;
    }
    
}
