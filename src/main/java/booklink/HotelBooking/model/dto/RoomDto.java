package booklink.HotelBooking.model.dto;

import booklink.HotelBooking.model.enums.ComfortLevel;
import booklink.HotelBooking.model.enums.RoomStatus;
import booklink.HotelBooking.model.enums.RoomType;
import jakarta.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDto {

    private Long id;

    @NotNull(message = "Room number is required")
    @Positive(message = "Room number must be positive")
    private Integer roomNumber;

    @NotNull(message = "Room type is required")
    private RoomType type;

    @NotNull(message = "Comfort level is required")
    private ComfortLevel comfort;

    @NotNull(message = "Price per night is required")
    @Positive(message = "Price per night must be greater than zero")
    private Double pricePerNight;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 4, message = "Capacity cannot be more than 4")
    private Integer capacity;

    @NotNull(message = "Room status is required")
    private RoomStatus status;

    @Size(max = 500, message = "Description cannot be longer than 500 characters")
    private String description;
}
