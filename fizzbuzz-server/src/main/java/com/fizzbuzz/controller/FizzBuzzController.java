package com.fizzbuzz.controller;

import com.fizzbuzz.models.FizzBuzzResponse;
import com.fizzbuzz.models.PageOption;
import com.fizzbuzz.service.FizzBuzzPlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@Value("${invalid.input.message}")
	private String invalidInputMessage;

	@Value("${invalid.value.in.page.param}")
	private String invalidValueInPageParam;

	@Value("${results.limit}")
	private int resultsLimit;

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
	public ResponseEntity<FizzBuzzResponse> play(@PathVariable("inputNumber") int inputNumber,
												 @RequestParam(name = "page", required = false) Integer page) {
			/*FizzBuzzResponse fizzBuzzResponse = null;
			fizzBuzzPlayService.getFizzBuzzSequence(inputNumber, page);
			if (!fizzBuzzPlayService.validateInput(inputNumber)) {
				fizzBuzzResponse = new FizzBuzzResponse(inputNumber, invalidInputMessage);
			} else {
				PageOption pageOption = fizzBuzzPlayService.getPageOption(inputNumber, page);
				if (pageOption != null) {
					fizzBuzzResponse = new FizzBuzzResponse(inputNumber, pageOption.getCurrentPage(), resultsLimit, fizzBuzzPlayService.play(pageOption.getStart(), pageOption.getEnd()));
				} else {
					fizzBuzzResponse = new FizzBuzzResponse(inputNumber, invalidValueInPageParam);
				}
			}*/
			return new ResponseEntity<>(fizzBuzzPlayService.getFizzBuzzSequence(inputNumber, page), HttpStatus.OK);
	}
}
