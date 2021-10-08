package com.manage.bookstore.user;

import com.manage.bookstore.exception.RegistrationException;
import com.manage.bookstore.jwt.JwtUtil;
import com.manage.bookstore.response.ErrorResponse;
import com.manage.bookstore.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(UserController.USER_PATH)
public class UserController {

    public static final String USER_PATH = "/api/user";

    private JwtUtil jwtUtil;

    private AuthenticationManager authenticationManager;

    private UserService userService;

    @Autowired
    public UserController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody User userModel) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userModel.getUsername(), userModel.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorret username or password", ex);
        }
        final UserDetails userDetails = userService.loadUserByUsername(userModel.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return jwt;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody User user) throws Exception, RegistrationException {
        try {
            userService.registerUser(user);
        } catch (RegistrationException registrationException) {
            return new ResponseEntity<>(new ErrorResponse(registrationException.getErrorCode(), registrationException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("-1", e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>(new SuccessResponse("Register Successfull!", new Date()), HttpStatus.OK);
    }

}
