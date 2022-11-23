package com.maksgir.cars_springboot.util;


import com.maksgir.cars_springboot.exception.BadEntityDataException;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Component
public class DateConverter {
    public Date convertStringDateToSQL(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date sql = new java.sql.Date(format.parse(date).getTime());

        return sql;
    }

    public String convertSQLDateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(date);
    }
}
