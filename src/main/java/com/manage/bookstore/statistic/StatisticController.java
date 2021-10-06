package com.manage.bookstore.statistic;

import com.manage.bookstore.user.UserController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(StatisticController.STATISTIC_PATH)
public class StatisticController {

    public static final String STATISTIC_PATH = "/api/statistic";
}
