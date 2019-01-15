package org.remipassmoilesel.javafxexperiments.controllers;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DateService {

    public Date getDate(){
        return new Date();
    }

}
