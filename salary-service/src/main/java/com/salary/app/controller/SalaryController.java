package com.salary.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/v1")
public class SalaryController {

    @GetMapping("/{id}")
    public String getSalary(@PathVariable Integer id) {
        if(id==1) {
            return "Rs 50L PM";
        }
        throw new IllegalArgumentException("User not found");
    }
}