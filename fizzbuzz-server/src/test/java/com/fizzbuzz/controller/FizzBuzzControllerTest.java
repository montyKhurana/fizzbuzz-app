package com.fizzbuzz.controller;

import com.fizzbuzz.models.FizzBuzzResponse;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    /**
     * test case for valid input ranging from 1 till value defined in max.input.number property
     */
    @Test
    public void testPlayWithValidInput() throws Exception {
        List<String> fizzBuzzSequence = Arrays.asList("1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz", "11", "Fizz", "13", "14", "Fizz Buzz");
        FizzBuzzResponse fizzBuzzResponse = new FizzBuzzResponse(15, fizzBuzzSequence);
        Mockito.when(fizzBuzzPlayService.getFizzBuzzResponse(Mockito.anyInt())).thenReturn(fizzBuzzResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/fizzbuzz/play/15");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{\"input\":15,\"fizzBuzzSequence\":[\"1\",\"2\",\"Fizz\",\"4\",\"Buzz\",\"Fizz\",\"7\",\"8\",\"Fizz\",\"Buzz\",\"11\",\"Fizz\",\"13\",\"14\",\"Fizz Buzz\"]}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /**
     * test case for invalid input such as  0, -12 or value greater than max.input.number property
     */
    @Test
    public void testPlayWithInvalidInput() throws Exception {
        FizzBuzzResponse fizzBuzzResponse = new FizzBuzzResponse(0, "Please enter a valid number between 1 to 200000");
        Mockito.when(fizzBuzzPlayService.getFizzBuzzResponse(Mockito.anyInt())).thenReturn(fizzBuzzResponse);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/fizzbuzz/play/0");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{\"input\":0,\"message\": \"Please enter a valid number between 1 to 200000\"}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

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
