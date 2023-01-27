package com.test.developertest.service;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class DateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String date) throws Exception {
        return LocalDate.parse(date);
    }

    @Override
    public String marshal(LocalDate date) throws Exception {
        return date.toString();
    }
}
