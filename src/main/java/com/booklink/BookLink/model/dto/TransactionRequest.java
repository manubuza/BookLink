package com.booklink.BookLink.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionRequest {
    @NotNull(message = "Book ID is required")
    private Long bookId;

    @NotNull(message = "Requester ID is required")
    private Long requesterId;

    @NotNull(message = "Owner ID is required")
    private Long ownerId;

    @NotNull(message = "Type is required")
    private String type;

    @NotNull(message = "Status is required")
    private String status;

    private BigDecimal amount;

}