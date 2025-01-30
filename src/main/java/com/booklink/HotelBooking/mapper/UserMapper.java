package com.booklink.HotelBooking.mapper;

import com.booklink.HotelBooking.model.dto.UserRequest;
import com.booklink.HotelBooking.model.dto.UserResponse;
import com.booklink.HotelBooking.model.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequest userRequest);

    UserResponse toResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UserRequest userRequest, @MappingTarget User user);
}
