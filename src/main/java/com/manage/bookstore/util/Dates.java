package com.manage.bookstore.util;

import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum Dates {

    JANUARY("01"), FEBRUARY("02"), MARCH("03"), APRIL("04"), MAY("05"), JUNE("06"), JULY("07"),
    AUGUST("08"), SEPTEMBER("09"), OCTOBER("10"), NOVEMBER("11"), DECEMBER("12");

    private static Map<String, String> allMonths = Collections.unmodifiableMap(initializeMonthMap());

    String month;

    private Dates(String month) {
        this.month = month;
    }

    private static Map<String, String> initializeMonthMap() {
        Map<String, String> map = new HashMap<String, String>();
        for (Dates month : Dates.values()) {
            map.put(month.toString(), month.getMonth());
        }
        return map;
    }
    public static void main(String[] args) {
        System.out.println(allMonths);
    }
}
