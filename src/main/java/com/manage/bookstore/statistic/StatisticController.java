package com.manage.bookstore.statistic;

import com.manage.bookstore.exception.OrderException;
import com.manage.bookstore.order.Order;
import com.manage.bookstore.order.OrderService;
import com.manage.bookstore.response.Response;
import com.manage.bookstore.response.SuccessResponse;
import com.manage.bookstore.user.UserController;
import com.manage.bookstore.util.DateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController("/api/statistic")
@RequestMapping(StatisticController.STATISTIC_PATH)
public class StatisticController {

    public static final String STATISTIC_PATH = "/api/statistic";
    private OrderService orderService;

    public StatisticController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path = "/{user-id}")
    public ResponseEntity<Response> getStatistics(@PathVariable(name = "user-id") long userId) throws OrderException {
        List<Order> orders = orderService.getOrdersByUser(userId);
        return new ResponseEntity<Response>(new SuccessResponse("Orders retrieved successfully", new Date(), orders), HttpStatus.OK);
    }

    @GetMapping(path = "/{user-id}", params = {"since"})
    public ResponseEntity<Response> getStatisticsSince(@PathVariable(name = "user-id") long userId, @RequestParam(name = "since") String since) throws OrderException, ParseException {
        List<Order> orders = orderService.getOrdersSince(userId, DateUtil.getSqlDate(since));
        return new ResponseEntity<Response>(new SuccessResponse("Orders retrieved successfully", new Date(), orders), HttpStatus.OK);
    }

    @GetMapping(path = "/{user-id}", params = {"since", "to"})
    public ResponseEntity<Response> getStatisticsSinceTo(@PathVariable(name = "user-id") long userId, @RequestParam(name = "since") String since, @RequestParam(name = "to") String to) throws ParseException, OrderException {
        List<Order> orders = orderService.getOrdersSinceTo(userId, DateUtil.getSqlDate(since), DateUtil.getSqlDate(to));
        return new ResponseEntity<Response>(new SuccessResponse("Orders retrieved successfully", new Date(), orders), HttpStatus.OK);
    }

    @GetMapping(path = "/monthly/")
    public ResponseEntity<Response> getAllMonthlyStatistic() throws ParseException {

        return new ResponseEntity<Response>(new SuccessResponse("Orders retrieved successfully", new Date(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/monthly/{month}")
    public ResponseEntity<Response> getMonthlyStatistic(@PathVariable(name = "month") String month) throws ParseException {

        return new ResponseEntity<Response>(new SuccessResponse("Orders retrieved successfully", new Date(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/monthly", params = {"since"})
    public ResponseEntity<Response> getMonthlyStatisticSince(@RequestParam(name = "since") String since) throws ParseException {
        return new ResponseEntity<Response>(new SuccessResponse("Orders retrieved successfully", new Date(), null), HttpStatus.OK);
    }

    @GetMapping(path = "/monthly", params = {"since", "to"})
    public ResponseEntity<Response> getMonthlyStatisticSinceTo(@RequestParam(name = "since") String since, @RequestParam(name = "to") String to) throws ParseException {

        return new ResponseEntity<Response>(new SuccessResponse("Orders retrieved successfully", new Date(), null), HttpStatus.OK);
    }
}
