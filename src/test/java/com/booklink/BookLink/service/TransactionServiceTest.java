package com.booklink.BookLink.service;

import com.booklink.BookLink.mapper.TransactionMapper;
import com.booklink.BookLink.model.dto.TransactionRequest;
import com.booklink.BookLink.model.dto.TransactionResponse;
import com.booklink.BookLink.model.entity.Transaction;
import com.booklink.BookLink.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransaction_success() {
        // Arrange
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setBookId(1L);
        transactionRequest.setRequesterId(2L);
        transactionRequest.setOwnerId(3L);
        transactionRequest.setType("borrow");
        transactionRequest.setStatus("pending");

        Transaction transaction = new Transaction();
        Transaction savedTransaction = new Transaction();
        TransactionResponse expectedResponse = new TransactionResponse();

        when(transactionMapper.toEntity(transactionRequest)).thenReturn(transaction);
        when(transactionRepository.save(transaction)).thenReturn(savedTransaction);
        when(transactionMapper.toResponse(savedTransaction)).thenReturn(expectedResponse);

        // Act
        TransactionResponse actualResponse = transactionService.createTransaction(transactionRequest);

        // Assert
        assertNotNull(actualResponse);
        verify(transactionMapper, times(1)).toEntity(transactionRequest);
        verify(transactionRepository, times(1)).save(transaction);
        verify(transactionMapper, times(1)).toResponse(savedTransaction);
    }

    @Test
    void getAllTransactions_success() {
        // Arrange
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();

        TransactionResponse response1 = new TransactionResponse();
        TransactionResponse response2 = new TransactionResponse();

        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction1, transaction2));
        when(transactionMapper.toResponse(transaction1)).thenReturn(response1);
        when(transactionMapper.toResponse(transaction2)).thenReturn(response2);

        // Act
        List<TransactionResponse> actualResponses = transactionService.getAllTransactions();

        // Assert
        assertEquals(2, actualResponses.size());
        verify(transactionRepository, times(1)).findAll();
        verify(transactionMapper, times(1)).toResponse(transaction1);
        verify(transactionMapper, times(1)).toResponse(transaction2);
    }

    @Test
    void getTransactionById_success() {
        // Arrange
        Long transactionId = 1L;
        Transaction transaction = new Transaction();
        TransactionResponse expectedResponse = new TransactionResponse();

        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));
        when(transactionMapper.toResponse(transaction)).thenReturn(expectedResponse);

        // Act
        TransactionResponse actualResponse = transactionService.getTransactionById(transactionId);

        // Assert
        assertNotNull(actualResponse);
        verify(transactionRepository, times(1)).findById(transactionId);
        verify(transactionMapper, times(1)).toResponse(transaction);
    }

    @Test
    void getTransactionById_notFound() {
        // Arrange
        Long transactionId = 1L;

        when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> transactionService.getTransactionById(transactionId));
        assertEquals("Transaction not found", exception.getMessage());
        verify(transactionRepository, times(1)).findById(transactionId);
        verifyNoInteractions(transactionMapper);
    }
}
