package com.fizzbuzz.service;

import com.fizzbuzz.models.FizzBuzzResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * FizzBuzzPlayServiceTests class where unit tests have been implemented for FizzBuzzPlayService class.
 */
@SpringBootTest
public class FizzBuzzPlayServiceTests {

    @Autowired
    private FizzBuzzPlayService fizzBuzzPlayService;

    @Value("${text.fizz}")
    private String textFizz;

    @Value("${text.buzz}")
    private String textBuzz;

    @Value("${text.fizz.buzz}")
    private String textFizzBuzz;

    /**
     * test case for checking if user input is valid i.e ranging from 1 till value defined in max.input.number property
     */
    @Test
    public void shouldValidateInput() {
        assertFalse(fizzBuzzPlayService.validateInput(0));
        assertFalse(fizzBuzzPlayService.validateInput(2147483647));
        assertTrue(fizzBuzzPlayService.validateInput(3));
        assertTrue(fizzBuzzPlayService.validateInput(104050));
    }

    /**
     * test case for checking if user input is divisible by 3
     */
    @Test
    public void testIsDividableBy3() {
        assertTrue(fizzBuzzPlayService.isDivisible(3, 3));
        assertTrue(fizzBuzzPlayService.isDivisible(87, 3));
        assertTrue(fizzBuzzPlayService.isDivisible(729, 3));
        assertFalse(fizzBuzzPlayService.isDivisible(17, 3));
    }

    /**
     * test case for checking if user input is divisible by 5
     */
    @Test
    public void testIsDividableBy5() {
        assertTrue(fizzBuzzPlayService.isDivisible(5, 5));
        assertTrue(fizzBuzzPlayService.isDivisible(25, 5));
        assertFalse(fizzBuzzPlayService.isDivisible(24, 5));
    }

    /**
     * test case for checking if user input is divisible by 15
     * it will test (divisibleByThree && divisibleByFive) condition
     */
    @Test
    public void testIsDividableBy15() {
        assertTrue(fizzBuzzPlayService.isDivisible(15, 15));
        assertTrue(fizzBuzzPlayService.isDivisible(30, 15));
        assertTrue(fizzBuzzPlayService.isDivisible(45, 15));
        assertTrue(fizzBuzzPlayService.isDivisible(135, 15));
        assertFalse(fizzBuzzPlayService.isDivisible(243, 15));
    }

    /**
     * test case for checking that getFizzBuzzValue method returns "Fizz" when the input number is divisible by 3
     */
    @Test
    public void shouldReturnFizz() {
        assertEquals(textFizz, fizzBuzzPlayService.getFizzBuzzValue(3));
        assertEquals(textFizz, fizzBuzzPlayService.getFizzBuzzValue(87));
        assertEquals(textFizz, fizzBuzzPlayService.getFizzBuzzValue(729));
    }

    /**
     * test case for checking that getFizzBuzzValue method returns "Buzz" when the input number is divisible by 5
     */
    @Test
    public void shouldReturnBuzz() {
        assertEquals(textBuzz, fizzBuzzPlayService.getFizzBuzzValue(5));
        assertEquals(textBuzz, fizzBuzzPlayService.getFizzBuzzValue(7525));
    }

    /**
     * test case for checking that getFizzBuzzValue method returns "Fizz Buzz" when the input number is divisible by both 3 and 5
     */
    @Test
    public void shouldReturnFizzValue() {
        assertEquals(textFizzBuzz, fizzBuzzPlayService.getFizzBuzzValue(15));
        assertEquals(textFizzBuzz, fizzBuzzPlayService.getFizzBuzzValue(30));
        assertEquals(textFizzBuzz, fizzBuzzPlayService.getFizzBuzzValue(45));
        assertEquals(textFizzBuzz, fizzBuzzPlayService.getFizzBuzzValue(135));
    }

    /**
     * test case for checking that getFizzBuzzValue method returns original number when fizzbuzz criteria is not met
     */
    @Test
    public void shouldReturnOriginalNumberAsString() {
        assertEquals("1", fizzBuzzPlayService.getFizzBuzzValue(1));
        assertEquals("7", fizzBuzzPlayService.getFizzBuzzValue(7));
    }

    /**
     * test case for checking that getFizzBuzzValue method returns correct values based on fizzbuzz rules
     */
    @Test
    public void testWithRandomInputs() {
        assertEquals("1", fizzBuzzPlayService.getFizzBuzzValue(1));
        assertEquals(textFizzBuzz, fizzBuzzPlayService.getFizzBuzzValue(75));
        assertEquals("4678", fizzBuzzPlayService.getFizzBuzzValue(4678));
        assertEquals("979", fizzBuzzPlayService.getFizzBuzzValue(979));
        assertEquals(textBuzz, fizzBuzzPlayService.getFizzBuzzValue(100));
        assertEquals("1862", fizzBuzzPlayService.getFizzBuzzValue(1862));
        assertEquals(textBuzz, fizzBuzzPlayService.getFizzBuzzValue(7525));
        assertEquals(textFizz, fizzBuzzPlayService.getFizzBuzzValue(2727));
    }

    /**
     * test case for checking that play method returns correct fizzbuzz sequence
     */
    @Test
    public void testPlaySequence() {
        List<String> numbers = Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "Fizz Buzz");
        assertIterableEquals(numbers, fizzBuzzPlayService.play(15));
    }

    /**
     * test case for checking that getFizzBuzzResponse gives correct warning message when user input is invalid
     */
    @Test
    public void testGetFizzBuzzResponseForInvalidInput() {
        FizzBuzzResponse fizzBuzzResponse = fizzBuzzPlayService.getFizzBuzzResponse(0);
        assertEquals("Please enter a valid number between 1 to 200000", fizzBuzzResponse.getMessage());

        FizzBuzzResponse fizzBuzzResponseForNegativeNumber = fizzBuzzPlayService.getFizzBuzzResponse(-1);
        assertEquals("Please enter a valid number between 1 to 200000", fizzBuzzResponseForNegativeNumber.getMessage());

        FizzBuzzResponse fizzBuzzResponseForBigNumber = fizzBuzzPlayService.getFizzBuzzResponse(12345678);
        assertEquals("Please enter a valid number between 1 to 200000", fizzBuzzResponseForBigNumber.getMessage());
    }

    /**
     * test case for checking that getFizzBuzzResponse gives correct sequence message when user input is valid
     */
    @Test
    public void testGetFizzBuzzResponseForValidInput() {
        List<String> numbers = Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "Fizz Buzz");
        FizzBuzzResponse fizzBuzzResponse = fizzBuzzPlayService.getFizzBuzzResponse(15);
        assertIterableEquals(numbers, fizzBuzzResponse.getFizzBuzzSequence());
    }
}
