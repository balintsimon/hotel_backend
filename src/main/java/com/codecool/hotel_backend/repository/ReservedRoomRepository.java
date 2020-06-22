package com.codecool.hotel_backend.repository;

import com.codecool.hotel_backend.entity.Reservation;
import com.codecool.hotel_backend.entity.ReservedRoom;
import com.codecool.hotel_backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ReservedRoomRepository extends JpaRepository<ReservedRoom, Long> {

    @Query("select reserve, room, reserved from ReservedRoom reserve " +
            "join reserve.room room join reserve.reservation reserved")
    List<ReservedRoom> findAll();

    @Query("select reservedRoom from ReservedRoom reservedRoom where reservedRoom.reservation.id = :reservationId")
    ReservedRoom findByReservationId(@Param("reservationId") Long reservationId);

    @Modifying
    @Transactional
    @Query(value = "update RESERVED_ROOM set RESERVED_ROOM.ROOM_ID = :roomId where RESERVED_ROOM.ID = :id", nativeQuery = true)
    void updateReservedRoom(@Param("id")Long id, @Param("roomId") Long roomId);
}
