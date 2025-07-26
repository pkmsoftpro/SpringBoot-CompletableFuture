package com.commonservice.common_service.controller;

import com.commonservice.common_service.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("app/v1/common")
public class CommonServiceController {

    @Value("${spring.service.name}")
    String nameUrl;

    @Value("${spring.service.salary}")
    String salaryUrl;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{id}")
    public Person getSalary(@PathVariable Integer id) throws InterruptedException {

        Person person = new Person();
        CompletableFuture.allOf(
            CompletableFuture.supplyAsync(()->restTemplate.exchange(nameUrl+id, HttpMethod.GET, null, String.class))
                    .thenAccept(name->person.setName(name.getBody())),
            CompletableFuture.supplyAsync(()->restTemplate.exchange(salaryUrl+id, HttpMethod.GET, null, String.class))
                        .thenAccept(sal->person.setSalary(sal.getBody()))
        ).join();
        return person;
    }
}