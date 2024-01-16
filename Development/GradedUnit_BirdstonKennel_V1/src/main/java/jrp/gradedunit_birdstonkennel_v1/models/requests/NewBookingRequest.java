package jrp.gradedunit_birdstonkennel_v1.models.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

/**
 * This class tracks new booking requests
 */
@Data
@NoArgsConstructor
public class NewBookingRequest {
    /**
     * Stores the arrival date
     */
    private LocalDate arrival;
    /**
     * Stores the departure date
     */
    private LocalDate departure;
    /**
     * Stores the dogs involved in the booking
     */
    private Collection<Long> dogsOnBooking;
    /**
     * Stores whether grooming is included or not
     */
    private boolean addGrooming;

}
