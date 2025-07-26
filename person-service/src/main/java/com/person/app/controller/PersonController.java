package com.person.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/v1")
public class PersonController {

	@GetMapping("/{id}")
	public String getName(@PathVariable Integer id) {
		if(id==1) {
			return "Sam";
		}
		throw new IllegalArgumentException("User not found");
	}
}
