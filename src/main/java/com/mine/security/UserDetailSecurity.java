package com.mine.security;

import com.mine.entity.Role;
import com.mine.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailSecurity implements UserDetails {

    private static final long serialVersionUID = 1L;
    private User user;

    public User getUser() {
        return user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRoles().isEmpty()) {
            authorities.add(new SimpleGrantedAuthority("USER"));
        } else {
            for (Role role : this.user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
//				if(!group.getPermission().isEmpty() || group.getPermission() != null){
//					for(Permission permission : group.getPermission()){
//						authorities.add(new SimpleGrantedAuthority(permission.getUri()));
//					}
//				}
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
