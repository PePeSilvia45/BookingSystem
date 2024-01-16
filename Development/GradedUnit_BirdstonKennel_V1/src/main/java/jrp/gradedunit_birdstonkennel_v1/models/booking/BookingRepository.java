package jrp.gradedunit_birdstonkennel_v1.models.booking;

import jrp.gradedunit_birdstonkennel_v1.models.booking.enums.Status;
import jrp.gradedunit_birdstonkennel_v1.models.kennel.Kennel;
import jrp.gradedunit_birdstonkennel_v1.models.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    ArrayList<Booking> findAllByCustomer(Customer customer);

    ArrayList<Booking> findBookingByKennelsContaining(Kennel kennel);

    ArrayList<Booking> findAllByArrival(LocalDate date);

    ArrayList<Booking> findAllByDeparture(LocalDate date);

    ArrayList<Booking> findAllByTimeOfBookingBetween(LocalDateTime start, LocalDateTime end);

    ArrayList<Booking> findAllByArrivalBeforeAndDepartureAfter(LocalDate before, LocalDate after);


    @Modifying
    @Transactional
    @Query("UPDATE Booking b SET b.status = ?1 WHERE b.id = ?2")
    void updateBooking(Status status, Long id);

}
