package com.manage.bookstore.user;

import com.manage.bookstore.book.Book;
import com.manage.bookstore.exception.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void createLoginUser() {
        userRepository.save(new User("ali1", "ali123", "kumru4290@hotmail.com", "05071836262"));
        userRepository.save(new User("ali2", "ali123", "kumru4291@hotmail.com", "05071836262"));
        userRepository.save(new User("ali3", "ali123", "kumru4292@hotmail.com", "05071836262"));
        userRepository.save(new User("ali4", "ali123", "kumru4293@hotmail.com", "05071836262"));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.valueOf(1)).get();
        org.springframework.security.core.userdetails.User loginUser = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
        return loginUser;
    }

    public void registerUser(User user) throws RegistrationException {

        if (user.getEmail() == null)
            throw new RegistrationException("1001", "User email can not be empty");
        if (user.getUsername() == null)
            throw new RegistrationException("1001", "Username can not be empty");
        if (user.getPassword() == null)
            throw new RegistrationException("1001", "Password can not be empty");
        if (user.getPhone() == null)
            throw new RegistrationException("1001", "Phone can not be empty");

        userRepository.save(user);
    }
}
