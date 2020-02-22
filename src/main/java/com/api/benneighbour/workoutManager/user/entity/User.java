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

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements Serializable {

    private static final long serialVersionUID = -7217125720897487408L;

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(name = "username", nullable = false)
    @NotEmpty(message = "Please enter a username")
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please enter a valid email address")
    @NotEmpty(message = "Please enter an email address")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Please enter a password")
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "dateOfBirth")
    private LocalDate dob;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Role.class)
    private List<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity = Workout.class, orphanRemoval = true)
    private List<Workout> workoutList;

    @JsonIgnore
    @Column(name = "accountEnabled")
    private Boolean accountEnabled = false;

    @JsonIgnore
    @Column(name = "credentialsNonExpired")
    private Boolean credentialsNonExpired = true;

    @JsonIgnore
    @Column(name = "accountNonExpired")
    private Boolean accountNonExpired = true;

    @JsonIgnore
    @Column(name = "accountNonLocked")
    private Boolean accountNonLocked = true;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "dateCreated", updatable = false, nullable = false)
    private Date created;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "dateUpdated")
    private Date updated;


    public User() {
    }

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


    public Long getUid() {
        return uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }


    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }


    public Date getUpdated() {
        return updated;
    }
    public void setUpdated(Date updated) {
        this.updated = updated;
    }


    public boolean isAccountEnabled() {
        return accountEnabled;
    }
    public void setAccountEnabled(Boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }


    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }
    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }


    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }
    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }


    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }
    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }


    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public List<Workout> getWorkouts() {
        return workoutList;
    }
    public void setWorkouts(List<Workout> workoutList) {
        this.workoutList = workoutList;
    }

}
