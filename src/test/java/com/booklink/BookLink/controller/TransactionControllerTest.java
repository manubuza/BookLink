package com.booklink.BookLink.controller;

import com.booklink.BookLink.controller.TransactionController;

import com.booklink.BookLink.model.dto.TransactionRequest;
import com.booklink.BookLink.model.dto.TransactionResponse;
import com.booklink.BookLink.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createTransaction_shouldReturnSuccess() throws Exception {
        // Arrange
        TransactionRequest transactionRequest = createTransactionRequest();
        TransactionResponse transactionResponse = createTransactionResponse();

        given(transactionService.createTransaction(any(TransactionRequest.class))).willReturn(transactionResponse);

        // Act & Assert
        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(transactionResponse.getId()))
                .andExpect(jsonPath("$.bookId").value(transactionResponse.getBookId()))
                .andExpect(jsonPath("$.requesterId").value(transactionResponse.getRequesterId()))
                .andExpect(jsonPath("$.ownerId").value(transactionResponse.getOwnerId()))
                .andExpect(jsonPath("$.type").value(transactionResponse.getType()))
                .andExpect(jsonPath("$.status").value(transactionResponse.getStatus()));
    }

    @Test
    void getAllTransactions_shouldReturnSuccess() throws Exception {
        // Arrange
        TransactionResponse transactionResponse1 = createTransactionResponse();
        TransactionResponse transactionResponse2 = createTransactionResponse();
        transactionResponse2.setId(2L);
        transactionResponse2.setBookId(2L);

        List<TransactionResponse> transactionResponses = Arrays.asList(transactionResponse1, transactionResponse2);
        given(transactionService.getAllTransactions()).willReturn(transactionResponses);

        // Act & Assert
        mockMvc.perform(get("/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(transactionResponse1.getId()))
                .andExpect(jsonPath("$[1].id").value(transactionResponse2.getId()));
    }

    @Test
    void getTransactionById_shouldReturnSuccess() throws Exception {
        // Arrange
        TransactionResponse transactionResponse = createTransactionResponse();

        given(transactionService.getTransactionById(1L)).willReturn(transactionResponse);

        // Act & Assert
        mockMvc.perform(get("/transactions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(transactionResponse.getId()))
                .andExpect(jsonPath("$.bookId").value(transactionResponse.getBookId()))
                .andExpect(jsonPath("$.requesterId").value(transactionResponse.getRequesterId()))
                .andExpect(jsonPath("$.ownerId").value(transactionResponse.getOwnerId()))
                .andExpect(jsonPath("$.type").value(transactionResponse.getType()))
                .andExpect(jsonPath("$.status").value(transactionResponse.getStatus()));
    }

    private TransactionRequest createTransactionRequest() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setBookId(1L);
        transactionRequest.setRequesterId(2L);
        transactionRequest.setOwnerId(3L);
        transactionRequest.setType("borrow");
        transactionRequest.setStatus("pending");
        return transactionRequest;
    }

    private TransactionResponse createTransactionResponse() {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(1L);
        transactionResponse.setBookId(1L);
        transactionResponse.setRequesterId(2L);
        transactionResponse.setOwnerId(3L);
        transactionResponse.setType("borrow");
        transactionResponse.setStatus("pending");
        return transactionResponse;
    }
}
