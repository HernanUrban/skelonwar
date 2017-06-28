package com.urban.skelonwar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.urban.skelonwar.domain.User;
import com.urban.skelonwar.helper.CopyPropertiesHelper;
import com.urban.skelonwar.repository.UserRepository;

/**
 * Created by hernan.urban on 6/13/2017.
 */
@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        DefaultUserDetails userDetails = new DefaultUserDetails();
        User user = repository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("Unable to find user " + s));
        CopyPropertiesHelper.copyNonNullProperties(user, userDetails);
        return userDetails;
    }
}