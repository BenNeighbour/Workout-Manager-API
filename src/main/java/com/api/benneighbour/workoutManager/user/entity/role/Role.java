package com.api.benneighbour.workoutManager.user.entity.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role implements Serializable {

    private static final long serialVersionUID = 3693677907638193522L;

    @Id
    @Column(name = "rid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    @Column(name = "name")
    private String name = "USER";


    Role() {}

    public Role(String name, Long rid) {
        this.name = name;
        this.rid = rid;
    }



    // Setters and Getters for the Entity properties

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }



    public Long getRid() {
        return rid;
    }
    public void setRid(Long rid) {
        this.rid = rid;
    }


}
