package com.manage.bookstore.user;

import com.manage.bookstore.exception.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void createLoginUser() {
        User user = new User();
        user.setEmail("alikmru@gmail.com");
        user.setUsername("alikumru");
        user.setPassword("ali123");
        user.setPhone("05071836262");
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(Long.valueOf(1));

        return null;
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
