package com.fizzbuzz.controller;

import com.fizzbuzz.models.FizzBuzzResponse;
import com.fizzbuzz.service.FizzBuzzPlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  {@link FizzBuzzController} is a REST API.
 *  It provides support for Http methods for obtaining a Fizz Buzz sequence
 *
 * @author Monty Khurana
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/fizzbuzz")
public class FizzBuzzController {

	private final FizzBuzzPlayService fizzBuzzPlayService;

	@Autowired
	public FizzBuzzController(FizzBuzzPlayService fizzBuzzPlayService) {
		this.fizzBuzzPlayService = fizzBuzzPlayService;
	}

	/**
	 * Generates JSON object for {@link FizzBuzzResponse}
	 *  which contains inputNumber
	 *  and either a fizzBuzz list or message if input is invalid
	 *
	 * @param inputNumber valid user input
	 * @return FizzBuzzResponse as JSON
	 */
	@GetMapping(value = "/play/{inputNumber}", produces = "application/json")
	public ResponseEntity<FizzBuzzResponse> play(@PathVariable("inputNumber") int inputNumber) {
			return new ResponseEntity<>(fizzBuzzPlayService.getFizzBuzzResponse(inputNumber), HttpStatus.OK);
	}
}
