package booklink.HotelBooking.mapper;

import booklink.HotelBooking.model.dto.RoomDto;
import booklink.HotelBooking.model.entity.Room;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoomMapper {


    Room toEntity(RoomDto dto);

    RoomDto toDto(Room room);
}
