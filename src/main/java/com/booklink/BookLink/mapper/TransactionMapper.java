package com.booklink.BookLink.mapper;

import com.booklink.BookLink.model.dto.TransactionRequest;
import com.booklink.BookLink.model.dto.TransactionResponse;
import com.booklink.BookLink.model.entity.Transaction;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction toEntity(TransactionRequest transactionRequest);

    TransactionResponse toResponse(Transaction transaction);
}
