package com.iispiridis.visitservice.services;

import com.iispiridis.visitservice.repositories.UserRepository;
import com.iispiridis.visitservice.models.CustomUserDetails;
import com.iispiridis.visitservice.models.User;
import com.iispiridis.visitservice.models.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByusername(username);


        user.orElseThrow(() -> new UsernameNotFoundException("Username was nout found: " + username));


        return user.map(CustomUserDetails::new).get();
    }

    public User save(User user)
    {
        return userRepository.save(user);
    }

    public User save(UserRegistrationDto userrdto)
    {
        User user = new User();
        user.setPassword(userrdto.getPassword());
        user.setRoles(userrdto.getRole());
        user.setUsername(userrdto.getName());

        return userRepository.save(user);
    }

    public User getCurrentUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = findByUsername(auth.getName());
        User u = user.get();
        return u;
    }

    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByusername(username);
    }

}
