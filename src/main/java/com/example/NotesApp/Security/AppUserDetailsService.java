package com.example.NotesApp.Security;


import com.example.NotesApp.Model.User;
import com.example.NotesApp.Repositary.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserRepository repo;
    public AppUserDetailsService(UserRepository repo) { this.repo = repo; }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = repo.findByUsername(username).orElseThrow();
        return org.springframework.security.core.userdetails.User.withUsername(u.getUsername()).password(u.getPassword()).authorities(Collections.emptyList()).build();
    }
}
