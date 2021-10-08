package com.manage.bookstore.statistic;

import com.manage.bookstore.order.Order;
import com.manage.bookstore.user.UserController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/statistic")
@RequestMapping(StatisticController.STATISTIC_PATH)
public class StatisticController {

    public static final String STATISTIC_PATH = "/api/statistic";

    @GetMapping(path = "/{user-id}")
    public ResponseEntity<Statistic> getStatistics(@PathVariable(name = "user-id") String userId) {
        System.out.println("1");
        return null;
    }

    @GetMapping(path = "/{user-id}", params = {"since"})
    public ResponseEntity<Statistic> getStatisticsSince(@PathVariable(name = "user-id") String userId, @RequestParam(name = "since") String since) {
        System.out.println("2");
        return null;
    }

    @GetMapping(path = "/{user-id}", params = {"since", "to"})
    public ResponseEntity<Statistic> getStatisticsSinceTo(@PathVariable(name = "user-id") String userId, @RequestParam(name = "since") String begin, @RequestParam(name = "to") String to) {
        System.out.println("3");
        return null;
    }
}
