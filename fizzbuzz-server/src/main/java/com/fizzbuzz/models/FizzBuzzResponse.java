package com.fizzbuzz.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 * This class represents the content of {@link FizzBuzzResponse} JSON object sent as response.
 * It is returned to the client application as JSON String.
 *
 * @author Monty Khurana
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FizzBuzzResponse {
    private int input;
    private int currentPage;
    private int resultsLimit;
    private String message;
    private List<String> fizzBuzzSequence;

    public FizzBuzzResponse() {
        super();
    }

    public FizzBuzzResponse(int input, String message) {
        this.input = input;
        this.message = message;
    }

    public FizzBuzzResponse(int input, List<String> fizzBuzzSequence) {
        this.input = input;
        this.fizzBuzzSequence = fizzBuzzSequence;
    }

    public FizzBuzzResponse(int input, int currentPage, int resultsLimit, List<String> fizzBuzzSequence) {
        this.input = input;
        this.currentPage = currentPage;
        this.resultsLimit = resultsLimit;
        this.fizzBuzzSequence = fizzBuzzSequence;
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getFizzBuzzSequence() {
        return fizzBuzzSequence;
    }

    public void setFizzBuzzSequence(List<String> fizzBuzzSequence) {
        this.fizzBuzzSequence = fizzBuzzSequence;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getResultsLimit() {
        return resultsLimit;
    }

    public void setResultsLimit(int resultsLimit) {
        this.resultsLimit = resultsLimit;
    }
}
