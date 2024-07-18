package org.example.numbergenerateservice.utils;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class FormatDate {

    private static Calendar calendar;

    public FormatDate() {
        calendar = Calendar.getInstance();
    }

    public String getDate(){
        Date date = new Date();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return year + String.valueOf(month) + day;
    }
}
