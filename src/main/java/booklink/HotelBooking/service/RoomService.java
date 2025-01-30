package booklink.HotelBooking.service;

import booklink.HotelBooking.exception.RoomAlreadyExistsException;
import booklink.HotelBooking.exception.RoomOccupiedException;
import booklink.HotelBooking.model.entity.Room;
import booklink.HotelBooking.model.enums.RoomStatus;
import booklink.HotelBooking.mapper.RoomMapper;
import booklink.HotelBooking.model.dto.RoomDto;
import booklink.HotelBooking.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    /**
     * Adds a new room to the database.
     * Ensures the room number is unique.
     *
     * @param request DTO containing room details.
     * @return Saved Room entity.
     * @throws RoomAlreadyExistsException if room number already exists.
     */
    public RoomDto addRoom(RoomDto request) {
        if (roomRepository.findByRoomNumber(request.getRoomNumber()).isPresent()) {
            throw new RoomAlreadyExistsException("There is already an item with the same number");
        }

        Room room = roomMapper.toEntity(request);
        Room createdRoom = roomRepository.save(room);
        return roomMapper.toDto(createdRoom);
    }

    /**
     * Deletes a room only if its status is AVAILABLE.
     *
     * @param id Room ID.
     * @throws IllegalArgumentException if the room does not exist.
     * @throws RoomOccupiedException if the room is OCCUPIED.
     */
    @Transactional
    public void deleteRoom(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        if (room.getStatus() == RoomStatus.OCCUPIED) {
            throw new RoomOccupiedException("The room is occupied, it cannot be deleted.");
        }

        roomRepository.delete(room);
    }
}
