package com.api.benneighbour.workoutManager.user;

import com.api.benneighbour.workoutManager.user.entity.User;
import com.api.benneighbour.workoutManager.user.entity.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends User implements UserDetails, Serializable {

    private static final long serialVersionUID = -4798879123296061889L;

    public CustomUserDetails(User user) {
        super(user);
    }

    public CustomUserDetails() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities;
        grantedAuthorities = new ArrayList<>();

        for (Role role : super.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return grantedAuthorities;
    }

    @Override
    public Long getUid() {
        return super.getUid();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return super.isAccountEnabled();
    }
}
