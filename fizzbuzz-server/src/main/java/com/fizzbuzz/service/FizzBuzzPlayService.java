package com.fizzbuzz.service;

import com.fizzbuzz.models.FizzBuzzResponse;
import com.fizzbuzz.models.PageOption;
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

    @Value("${results.limit}")
    private int resultsLimit;

    @Value("${invalid.input.message}")
    private String invalidInputMessage;

    @Value("${invalid.value.in.page.param}")
    private String invalidValueInPageParam;

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


    public PageOption getPageOption(int inputNumber, Integer page) {
        if (inputNumber > resultsLimit) {
            if (page != null && page != 0) {
                if (!isTotalPageCountMoreThanPageParam(inputNumber, page)) {
                    return null;
                }
                int previousNumberResults = (page - 1) * resultsLimit;
                int start = previousNumberResults + 1;
                int end = (inputNumber - previousNumberResults) <= resultsLimit ? inputNumber : (previousNumberResults + resultsLimit);
                return new PageOption(page, start, end);
            }
            return new PageOption(1, 1, resultsLimit);
        }
        return new PageOption(1, 1, inputNumber);

    }


    /**
     * This method is the used for creating a valid fizzBuzz list based on user input number
     *
     * @param inputNumber valid input number
     * @return list of a numbers implementing fizzbuzz rules
     */
    public List<String> play(int start, int inputNumber) {
        return IntStream.rangeClosed(start, inputNumber)
                .mapToObj(this::getFizzBuzzValue)
                .collect(Collectors.toList());
    }

    public FizzBuzzResponse getFizzBuzzSequence(int inputNumber, Integer page) {
        FizzBuzzResponse fizzBuzzResponse = null;
        if (!validateInput(inputNumber)) {
            return new FizzBuzzResponse(inputNumber, invalidInputMessage);
        } else {
            PageOption pageOption = getPageOption(inputNumber, page);
            if (pageOption != null) {
                return new FizzBuzzResponse(inputNumber, pageOption.getCurrentPage(), resultsLimit, play(pageOption.getStart(), pageOption.getEnd()));
            } else {
                return new FizzBuzzResponse(inputNumber, invalidValueInPageParam);
            }
        }
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

    boolean isTotalPageCountMoreThanPageParam(int inputNumber, int page) {
        if (page <= 0) {
            return false;
        }
        long pagesCount = (long) Math.ceil((double) inputNumber / resultsLimit);
        return pagesCount >= page;
    }
}
