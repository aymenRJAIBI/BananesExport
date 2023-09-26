package com.exportBanane.controllers;

import com.exportBanane.Service.OrderService;
import com.exportBanane.Service.RecipientService;
import com.exportBanane.dto.RecipientDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import net.bytebuddy.pool.TypePool;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.Invocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(RecipientController.class)
@ExtendWith(SpringExtension.class)
public class RecipientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipientService recipientService;

    @MockBean
    private OrderService orderService;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void givenRecipientObject_whenSave_thenRecipientSaved() throws Exception {
        //given - precondition or setup
        RecipientDto recipientDto = RecipientDto.builder().id(1).name("Test1").city("Antony").address("123 rue abc").country("france").build();
        given(recipientService.save(any(RecipientDto.class))).willReturn(recipientDto.getId());

        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/recipients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipientDto)));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void givenListRecipients_whenFindAll_thenReturnRecipientsList() throws Exception {
        //given - precondition or setup
        List<RecipientDto> listOfRecipients = new ArrayList<>();
        listOfRecipients.add(RecipientDto.builder().name("Test1").city("Antony").address("123 rue abc").country("france").build());
        listOfRecipients.add(RecipientDto.builder().name("Test2").city("Antony").address("1234 rue abc").country("france").build());
        given(recipientService.findAll()).willReturn(listOfRecipients);
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/recipients/"));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(listOfRecipients.size())));

    }

    // positive scenario
    @Test
    public void givenIdRecipient_whenFindRecipientById_thenReturnRecipientObject() throws Exception {
        //given - precondition or setup
        RecipientDto recipientDto = RecipientDto.builder().id(1).name("Test1").city("Antony").address("123 rue abc").country("france").build();
        given(recipientService.findById(recipientDto.getId())).willReturn(recipientDto);

        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/recipients/{id}", recipientDto.getId()));
        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(recipientDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.city", CoreMatchers.is(recipientDto.getCity())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", CoreMatchers.is(recipientDto.getAddress())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.country", CoreMatchers.is(recipientDto.getCountry())));

    }

    @Test
    public void givenInvalidIdRecipient_whenFindRecipientById_thenReturnException() throws Exception {
        //given - precondition or setup
        RecipientDto recipientDto = RecipientDto.builder().id(1).name("Test1").city("Antony").address("123 rue abc").country("france").build();
        given(recipientService.findById(recipientDto.getId())).willThrow(new EntityNotFoundException("Recipient not found"));

        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/recipients/{id}", recipientDto.getId()));
        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMessage", CoreMatchers.is("Recipient not found")));
    }

    //positive scenario
    @Test
    public void givenUpdatedRecipient_whenUpdateRecipient_thenUpdatedRecipientObject() throws Exception {
        //given - precondition or setup
        RecipientDto savedRecipient = RecipientDto.builder().id(1).name("Test1").city("Antony").address("123 rue abc").country("france").build();
        RecipientDto updatedRecipientDto = RecipientDto.builder().id(1).name("Test").city("Antony").address("1234 rue abc").country("france").build();
        given(recipientService.update(updatedRecipientDto)).willReturn(savedRecipient.getId());
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/recipients/{id}", savedRecipient.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString((updatedRecipientDto))));
        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        //.andExpect(MockMvcResultMatchers.jsonPath("$.name",CoreMatchers.is(updatedRecipientDto.getName())))
        //.andExpect(MockMvcResultMatchers.jsonPath("$.city",CoreMatchers.is(updatedRecipientDto.getCity())))
        //.andExpect(MockMvcResultMatchers.jsonPath("$.address",CoreMatchers.is(updatedRecipientDto.getAddress())))
        //.andExpect(MockMvcResultMatchers.jsonPath("$.country",CoreMatchers.is(updatedRecipientDto.getCountry())));


    }

    //negative scenraio
    @Test
    public void givenUpdatedRecipient_whenUpdateRecipient_thenReturnNotFound() throws Exception {
        //given - precondition or setup
        RecipientDto savedRecipient = RecipientDto.builder().id(1).name("Test1").city("Antony").address("123 rue abc").country("france").build();
        RecipientDto updatedRecipientDto = RecipientDto.builder().id(1).name("Test").city("Antony").address("1234 rue abc").country("france").build();
        // given(recipientService.update(updatedRecipientDto)).willThrow(new EntityNotFoundException("Recipient not found"));

       doThrow(new EntityNotFoundException("Recipient not found")).when(recipientService).update(any());
        // when - action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.put("/recipients/{id}", savedRecipient.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString((updatedRecipientDto))));
        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorMessage", CoreMatchers.is("Recipient not found")));


    }

}
