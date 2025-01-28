package com.booklink.BookLink.service;

import com.booklink.BookLink.mapper.TransactionMapper;
import com.booklink.BookLink.model.dto.TransactionRequest;
import com.booklink.BookLink.model.dto.TransactionResponse;
import com.booklink.BookLink.model.entity.Transaction;
import com.booklink.BookLink.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;



    public TransactionResponse createTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = transactionMapper.toEntity(transactionRequest);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.toResponse(savedTransaction);
    }

    public List<TransactionResponse> getAllTransactions() {
        return transactionRepository.findAll().stream().map(transactionMapper::toResponse).collect(Collectors.toList());
    }

    public TransactionResponse getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        return transactionMapper.toResponse(transaction);
    }
}
