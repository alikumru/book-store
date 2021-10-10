package com.manage.bookstore.user;

import com.manage.bookstore.exception.OrderException;
import com.manage.bookstore.exception.RegistrationException;
import com.manage.bookstore.exception.UserException;
import com.manage.bookstore.jwt.JwtUtil;
import com.manage.bookstore.order.Order;
import com.manage.bookstore.order.OrderService;
import com.manage.bookstore.response.ErrorResponse;
import com.manage.bookstore.response.Response;
import com.manage.bookstore.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(UserController.USER_PATH)
public class UserController {

    public static final String USER_PATH = "/api/user";

    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private OrderService orderService;

    @Autowired
    public UserController(JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserService userService, OrderService orderService) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.orderService = orderService;
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorret username or password", ex);
        }
        final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

        return jwtUtil.generateToken(userDetails);
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User user) throws Exception, RegistrationException {
        try {
            userService.registerUser(user);
        } catch (RegistrationException registrationException) {
            return new ResponseEntity<Response>(new ErrorResponse(registrationException.getErrorCode(), null, registrationException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new ErrorResponse("-1", null, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Response>(new SuccessResponse("Register Successfull!", new Date(), user.getUsername()), HttpStatus.OK);
    }

    @GetMapping("/orders/{user_id}")
    public ResponseEntity<Response> orders(@PathVariable(name = "user_id") long userId) throws Exception, OrderException {
        List<Order> order;
        try {
            order = orderService.getOrdersByUser(userId);
            if (order == null) {
                return new ResponseEntity<>(new ErrorResponse("1008", null, "Order could not found!"),
                        HttpStatus.BAD_REQUEST);
            }
        } catch (OrderException orderException) {
            return new ResponseEntity<>(new ErrorResponse("2004", null, orderException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("9999", null, e.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Response>(new SuccessResponse("Register Successfull!", new Date(), order), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Response> getUsers() throws Exception {
        List<User> users = null;
        try {
            users = userService.findAll();
            if (users == null)
                throw new UserException("3001", "Users not found");
        } catch (Exception e) {

        }
        return new ResponseEntity<Response>(new SuccessResponse("Users retrived succesfully !", new Date(), users), HttpStatus.OK);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<Response> getUserById(@PathVariable(name = "user-id") long userId) throws Exception {
        User user = null;
        try {
            user = userService.findUserById(userId);
            if (user == null)
                return new ResponseEntity<Response>(new ErrorResponse("3999", "User not found", null), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Response>(new ErrorResponse("3999", "User not found", null), HttpStatus.OK);
        }
        return new ResponseEntity<Response>(new SuccessResponse("Users retrived succesfully !", new Date(), user), HttpStatus.OK);
    }

}
