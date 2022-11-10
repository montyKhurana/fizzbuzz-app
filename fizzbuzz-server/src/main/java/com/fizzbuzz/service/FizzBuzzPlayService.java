package com.fizzbuzz.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *  {@link FizzBuzzPlayService} service class which is used to validate input and provide correct fizz buzz list.
 *
 * @author Monty Khurana
 */
@Service
public class FizzBuzzPlayService {

    @Value("${max.input.number}")
    private int maxInputNumber;

    @Value("${text.fizz}")
    private String textFizz;

    @Value("${text.buzz}")
    private String textBuzz;

    @Value("${text.fizz.buzz}")
    private String textFizzBuzz;

    /**
     * This method is the used for validating user input number
     * Valid user input should be in range from 1 to the value defined in max.input.number configuration property
     *
     * @param input user input number
     * @return boolean true/false if number is valid or not
     */
    public boolean validateInput(int input) {
        if (input <= 0) {
            return false;
        } else return input <= maxInputNumber;
    }

    /**
     * This method is the used for creating a valid fizzBuzz list based on user input number
     *
     * @param inputNumber valid input number
     * @return list of a numbers implementing fizzbuzz rules
     */
    public List<String> play(int inputNumber) {
        return IntStream.rangeClosed(1, inputNumber)
                .mapToObj(this::getFizzBuzzValue)
                .collect(Collectors.toList());
    }

    /**
     * Checks if a number (dividend) is divisible by another number (divisor)
     *
     * @param dividend - number being checked if divisible by some other number (divisor)
     * @param divisor  {number} - what is used to determine if a number (dividend) is divisible
     * @return boolean  true/false if dividend is divisible
     */
    boolean isDivisible(int dividend, int divisor) {
        return dividend % divisor == 0;
    }

    /**
     * This method implements the fizzbuzz criteria and send valid response
     *
     * @param inputNumber valid input number
     * @return string value
     *  * if number is divisible by 3, return value will be "Fizz"
     *  * if number is divisible by 5, return value will be "Buzz"
     *  * if number is divisible by both 3 and 5 (i.e. 15), return value will be "Fizz Buzz"
     */
    String getFizzBuzzValue(int inputNumber) {
        boolean divisibleByThree = isDivisible(inputNumber, 3);
        boolean divisibleByFive = isDivisible(inputNumber, 5);
        // Check if value is divisible by both 3 and 5 first
        if (divisibleByThree && divisibleByFive) {
            return textFizzBuzz;
        }
        // It is important to check if number is divisible 3 after checking if divisible by 3 and 5
        if (divisibleByThree) {
            return textFizz;
        }
        // At last check if number is divisible 5
        if (divisibleByFive) {
            return textBuzz;
        }
        // Default case: return numeric value as a string
        return String.valueOf(inputNumber);
    }
}
