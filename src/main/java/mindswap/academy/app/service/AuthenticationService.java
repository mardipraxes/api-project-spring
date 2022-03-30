package mindswap.academy.app.service;


import mindswap.academy.app.persistance.model.Role;
import mindswap.academy.app.persistance.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepo userRepo;

    public boolean isValidUser(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password) != null;
    }


    public Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return authorities;
    }
}
