package com.booklink.HotelBooking.mapper;

import com.booklink.HotelBooking.model.dto.TransactionRequest;
import com.booklink.HotelBooking.model.dto.TransactionResponse;
import com.booklink.HotelBooking.model.entity.Transaction;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction toEntity(TransactionRequest transactionRequest);

    TransactionResponse toResponse(Transaction transaction);
}
