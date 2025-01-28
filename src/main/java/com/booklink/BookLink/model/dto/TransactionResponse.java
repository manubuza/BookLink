package com.booklink.BookLink.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private Long id;
    private Long bookId;
    private Long requesterId;
    private Long ownerId;
    private String type;
    private String status;
    private LocalDateTime transactionDate;
    private BigDecimal amount;
}