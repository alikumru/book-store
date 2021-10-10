package com.manage.bookstore.order;

import com.manage.bookstore.exception.OrderException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {

    private OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public OrderDetails save(OrderDetails orderDetails) throws OrderException {
        return orderDetailsRepository.save(orderDetails);
    }

}
