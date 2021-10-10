package com.manage.bookstore.test;

import com.manage.bookstore.jwt.JwtUtil;
import com.manage.bookstore.order.Order;
import com.manage.bookstore.order.OrderService;
import com.manage.bookstore.response.Response;
import com.manage.bookstore.response.SuccessResponse;
import com.manage.bookstore.user.User;
import com.manage.bookstore.user.UserController;
import com.manage.bookstore.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
class BookstoreApplicationTests {

    @InjectMocks
    private UserController userController;
    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserService userService;
    @Mock
    private OrderService orderService;

    @Before
    public void setup() throws Exception {
        userController = new UserController(jwtUtil, authenticationManager, userService, orderService);
    }

    @Test
    public void testCreateNewCustomer() throws Exception {
        User user = new User("ali1", "ali123", "kumru4290@hotmail.com", "05071836262");
        ResponseEntity<Response> response = userController.register(user);
        Response successResponse =(Response) response.getBody();
        assertNotNull(successResponse);
        assertEquals(successResponse.body, "ali1");
    }

    @Test
    public void testAllOrdersOfCustomer() throws Exception {
        List<Order> orders = orderService.getOrdersByUser(2);
    }

    @Test
    public void testCreateNewBook() throws Exception {
    }

    @Test
    public void testUpdateBook() throws Exception {
    }

    @Test
    public void testCreateNewOrder() throws Exception {
    }

    @Test
    public void testGetOrderById() throws Exception {
    }

    @Test
    public void testListOrders() throws Exception {
    }

    @Test
    public void testCustomerTotalOrderCount() throws Exception {
    }

    @Test
    public void testTotalPurchasedAmount() throws Exception {
    }

    @Test
    public void testTotalBookCount() throws Exception {
    }
}
