package com.fizzbuzz.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fizzbuzz.models.FizzBuzzResponse;
import com.fizzbuzz.models.PageOption;
import com.fizzbuzz.service.FizzBuzzPlayService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * FizzBuzzControllerTest class where unit tests have been implemented for FizzBuzzController class.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = FizzBuzzController.class)
@AutoConfigureMockMvc
public class FizzBuzzControllerTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FizzBuzzPlayService fizzBuzzPlayService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * test case for valid input ranging from 1 till value defined in max.input.number property
     */
   /* @Test
    public void testPlayWithValidInput() throws Exception {
        List<String> fizzBuzzSequence = Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "Fizz Buzz");

        FizzBuzzResponse fizzBuzzResponse = new FizzBuzzResponse();
        fizzBuzzResponse.setCurrentPage(1);
        fizzBuzzResponse.setResultsLimit(1000);
        fizzBuzzResponse.setInput(15);
        fizzBuzzResponse.setFizzBuzzSequence(fizzBuzzSequence);

        *//*PageOption pageOption = mock(PageOption.class);
        pageOption.setCurrentPage(1);
        pageOption.setStart(1);
        pageOption.setEnd(15);*//*

        // Mockito.when(fizzBuzzPlayService.validateInput(Mockito.anyInt())).thenReturn(true);
        //Mockito.when(fizzBuzzPlayService.getPageOption(Mockito.anyInt(),Mockito.anyInt())).thenReturn(pageOption);
        // Mockito.when(fizzBuzzPlayService.getFizzBuzzSequence(Mockito.anyInt(), Mockito.anyInt())).thenReturn((FizzBuzzResponse) fizzBuzzResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/fizzbuzz/play/15");
        *//*MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{\"input\":15,\"fizzBuzzSequence\":[\"1\",\"2\",\"Fizz\",\"4\",\"Buzz\",\"Fizz\",\"7\",\"8\",\"Fizz\",\"Buzz\",\"11\",\"Fizz\",\"13\",\"14\",\"Fizz Buzz\"]}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);*//*
        mockMvc.perform(get("/fizzbuzz/play/15")).andExpect(status().isOk())
                .andDo(print());
                //.andExpect(jsonPath("$.currentPage").value(fizzBuzzResponse.getCurrentPage()));
               *//* .andExpect(jsonPath("$.title").value(tutorial.getTitle()))
                .andExpect(jsonPath("$.description").value(tutorial.getDescription()))
                .andExpect(jsonPath("$.published").value(tutorial.isPublished()));*//*

    }*/

    /**
     * test case for invalid input such as  0, -12 or value greater than max.input.number property
     */
    /*@Test
    public void testPlayWithInvalidInput() throws Exception {
        Mockito.when(fizzBuzzPlayService.validateInput(Mockito.anyInt())).thenReturn(false);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/fizzbuzz/play/0");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{\"input\":0,\"message\": \"Please enter a valid number between 1 to 200000\"}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }*/

    /**
     * test case for invalid data type such as string, float
     */
    @Test
    public void testPlayWithInvalidInputDataType() throws Exception {
        RequestBuilder requestBuilderString = MockMvcRequestBuilders.get(
                "/fizzbuzz/play/abs");
        mockMvc.perform(requestBuilderString).andExpect(status().isBadRequest());

        RequestBuilder requestBuilderFloat = MockMvcRequestBuilders.get(
                "/fizzbuzz/play/0.6");
        mockMvc.perform(requestBuilderFloat).andExpect(status().isBadRequest());
    }
}
