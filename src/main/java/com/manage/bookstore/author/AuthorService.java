package com.manage.bookstore.author;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthorService implements UserDetailsService {

    private Map<String, User> users = new HashMap<>();



    @PostConstruct
    public void initialize() {
        users.put("ali", new User("ali", "ali123",new ArrayList<>()));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return users.get(s);
    }

    public void registerUser(String username,String email,String password){

    }
}
