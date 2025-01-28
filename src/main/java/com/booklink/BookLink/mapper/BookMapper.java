package com.booklink.BookLink.mapper;

import com.booklink.BookLink.model.dto.BookRequest;
import com.booklink.BookLink.model.dto.BookResponse;
import com.booklink.BookLink.model.entity.Book;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target="owner.id", source="ownerId")
    Book toEntity(BookRequest bookRequest);

    @Mapping(target = "ownerName", source = "owner.name")
    BookResponse toResponse(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(BookRequest bookRequest, @MappingTarget Book book);

    List<BookResponse> toResponseList(List<Book> books);

}