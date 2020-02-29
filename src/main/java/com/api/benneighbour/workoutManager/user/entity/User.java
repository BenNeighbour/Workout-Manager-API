package com.api.benneighbour.workoutManager.user.entity;

import com.api.benneighbour.workoutManager.user.entity.role.Role;
import com.api.benneighbour.workoutManager.workout.entity.Workout;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

// Entity annotation to flag to java.persistence
@Entity
// Table annotation to give the Entity a name when it creates all of the tables inside the Database
@Table(name = "user")
// Jackson annotation to hide it's lazy initialization handler, which interfered with the JSON response
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {

    // Serializable version id for the class
    private static final long serialVersionUID = -7217125720897487408L;

    // The uid field for each user
    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    // The username field for each workout
    @Column(name = "username", nullable = false)
    @NotEmpty(message = "Please enter a username")
    private String username;

    // The email field for each workout
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please enter a valid email address")
    @NotEmpty(message = "Please enter an email address")
    private String email;

    // The password field for each workout
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Please enter a password")
    private String password;

    // The id field for each workout
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "dateOfBirth")
    private LocalDate dob;

    // The roles field that belongs to each user
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Role.class)
    private List<Role> roles;

    // The collection of workouts that belongs to each user
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity = Workout.class)
    private List<Workout> workoutList;

    // The enabled boolean for the OAuth 2.0 configuration
    @JsonIgnore
    @Column(name = "accountEnabled")
    private Boolean accountEnabled = false;

    // The credentials boolean for the OAuth 2.0 configuration
    @JsonIgnore
    @Column(name = "credentialsNonExpired")
    private Boolean credentialsNonExpired = true;

    // The expired boolean for the OAuth 2.0 configuration
    @JsonIgnore
    @Column(name = "accountNonExpired")
    private Boolean accountNonExpired = true;

    // The locked boolean for the OAuth 2.0 configuration
    @JsonIgnore
    @Column(name = "accountNonLocked")
    private Boolean accountNonLocked = true;

    // The creation timestamp field for each workout
    @JsonIgnore
    @CreationTimestamp
    @Column(name = "dateCreated", updatable = false, nullable = false)
    private Date created;

    // The update timestamp field for each workout
    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "dateUpdated")
    private Date updated;

    // Empty default class constructor
    public User() {
    }

    // Default constructor with all it's fields
    public User(User user) {
        this.uid = user.getUid();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.dob = user.getDob();

        this.roles = user.getRoles();
        this.accountEnabled = user.isAccountEnabled();
        this.accountNonExpired = user.isAccountNonExpired();
        this.credentialsNonExpired = user.isCredentialsNonExpired();
        this.accountNonLocked = user.isAccountNonLocked();

        this.created = user.getCreated();
        this.updated = user.getUpdated();

        this.workoutList = user.getWorkouts();
    }

    /*
        Setters and Getters for the User class so that the values in it's instance can be seen by
        the other classes in the service and security layers.
    */


    // The statements for the User uid
    public Long getUid() {
        return uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
    }


    // The statements for the User username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    // The statements for the User password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    // The statements for the User email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    // The statements for the User creation date timestamps
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }


    // The statements for the User dateOfBirth dates
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }


    // The statements for the User update date timestamps
    public Date getUpdated() {
        return updated;
    }
    public void setUpdated(Date updated) {
        this.updated = updated;
    }


    // The statements for the User enabled OAuth 2.0 privileges
    public boolean isAccountEnabled() {
        return accountEnabled;
    }
    public void setAccountEnabled(Boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }


    // The statements for the User credentials OAuth 2.0 privileges
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }


    // The statements for the User expiration OAuth 2.0 privileges
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }
    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }


    // The statements for the User locked OAuth 2.0 privileges
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }


    // The statements for the User roles, used by OAuth 2.0 UserDetails interface
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    // The statements for the User workouts
    public List<Workout> getWorkouts() {
        return workoutList;
    }
    public void setWorkouts(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

}
