package jrp.gradedunit_birdstonkennel_v1.models.booking;

import com.stripe.exception.StripeException;
import jrp.gradedunit_birdstonkennel_v1.models.booking.enums.Status;
import jrp.gradedunit_birdstonkennel_v1.models.dog.Dog;
import jrp.gradedunit_birdstonkennel_v1.models.dog.DogService;
import jrp.gradedunit_birdstonkennel_v1.models.dog.enums.Size;
import jrp.gradedunit_birdstonkennel_v1.models.kennel.Kennel;
import jrp.gradedunit_birdstonkennel_v1.models.kennel.KennelService;
import jrp.gradedunit_birdstonkennel_v1.models.payment.Payment;
import jrp.gradedunit_birdstonkennel_v1.models.payment.PaymentService;
import jrp.gradedunit_birdstonkennel_v1.models.requests.AvailabilityRequest;
import jrp.gradedunit_birdstonkennel_v1.models.requests.NewBookingRequest;
import jrp.gradedunit_birdstonkennel_v1.models.user.AppUserService;
import jrp.gradedunit_birdstonkennel_v1.models.user.Customer;
import jrp.gradedunit_birdstonkennel_v1.models.validation.DataValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * This service handles the booking process
 */
@Service
@RequiredArgsConstructor
public class BookingService {
    /**
     * Stores an instance of the BookingRepository object
     */
    private final BookingRepository bookingRepository;
    /**
     * Stores an instance of the DataValidator object
     */
    private final DataValidator validator;
    /**
     *
     */
    private final PaymentService paymentService;
    /**
     * Stores an instance of the AppUserService object
     */
    private final AppUserService appUserService;
    /**
     * Stores an instance of the dogService object
     */
    private final DogService dogService;
    /**
     * Stores an instance of the kennelService object
     */
    private final KennelService kennelService;

    /**
     * Stores the current booking being processed
     */
    private Booking currentBooking;


    public ArrayList<Booking> getAllBookings() {
        return (ArrayList<Booking>) bookingRepository.findAll();
    }

    public ArrayList<Booking> getTodaysArrivals() {
        return bookingRepository.findAllByArrival(LocalDate.now());
    }

    public ArrayList<Booking> getTodaysDepartures() {
        return bookingRepository.findAllByDeparture(LocalDate.now());
    }

    public double getMonthlyEarnings() {
        Month month = LocalDate.now().getMonth();
        double earnings = 0;

        for (Booking b : bookingRepository.findAllByTimeOfBookingBetween(
                LocalDateTime.now().withDayOfMonth(1),
                LocalDateTime.now().withDayOfMonth(month.length(
                        Year.of(LocalDateTime.now().getYear()).isLeap()))))
            earnings += b.getTotal();

        return earnings;
    }

    public double getYearlyEarnings(){

        double earnings = 0;

        for (Booking b : bookingRepository.findAllByTimeOfBookingBetween(
                LocalDateTime.now().withMonth(1).withDayOfMonth(1),
                LocalDateTime.now().withMonth(12).withDayOfMonth(Month.DECEMBER.length(
                        Year.of(LocalDateTime.now().getYear()).isLeap()))))
            earnings += b.getTotal();

        return earnings;
    }

    public ArrayList<Booking> getCurrentGuests(){
        return bookingRepository.findAllByArrivalBeforeAndDepartureAfter(
                LocalDate.now(),
                LocalDate.now()
        );
    }

    public Booking findBooking(Long id) {
        Optional<Booking> booking = this.bookingRepository.findById(id);
        return booking.orElse(null);
    }

    /**
     * gets the current booking
     *
     * @return The current booking
     */
    public Booking getCurrentBooking() {
        return this.currentBooking;
    }

    public ArrayList<Booking> getAllByCustomer(Customer customer) {
        try {
            return this.bookingRepository.findAllByCustomer(customer);
        } catch (Exception e) {
            return null;
        }
    }

    public String checkAvailability(AvailabilityRequest request) {

        if (request.getArrival() == null || request.getDeparture() == null)
            return "error=empty_date";

        if (this.validator.dateBeforeNow(request.getArrival()))
            return "error=check_arrival";
        if (this.validator.dateBeforeNow(request.getDeparture()) || request.getDeparture().isBefore(request.getArrival()))
            return "error=check_departure";

        if (this.findAvailableKennels(request.getArrival(), request.getDeparture()).size() < 1)
            return "error=not_available";

        return "msg=available";
    }

    /**
     * Validates the data passed to the request
     *
     * @param request The new booking request
     * @return The name of the error or null if no errors found
     */
    public String validateRequest(NewBookingRequest request) {

        request.getDogsOnBooking().removeIf(Objects::isNull);

        if (this.validator.dateBeforeNow(request.getArrival()))
            return "arrival";

        if (this.validator.dateBeforeNow(request.getDeparture()) || request.getDeparture().isBefore(request.getArrival()))
            return "departure";

        if (request.getDogsOnBooking().size() < 1)
            return "dogs";

        return null;
    }

    public String createBooking(NewBookingRequest request) {

        if (this.currentBooking != null)
            this.currentBooking = null;

        this.currentBooking = new Booking(
                request.getArrival(),
                request.getDeparture(),
                this.appUserService.getCurrentCustomerFromContext(),
                request.isAddGrooming()
        );

        for (Long dogId : request.getDogsOnBooking()) {
            Dog d = this.dogService.getDogById(dogId);
            this.currentBooking.getDogs().add(d);
        }

        List<Kennel> availableKennels = findAvailableKennels(request.getArrival(), request.getDeparture());

        if (availableKennels == null || availableKennels.size() < 1)
            return "availability";

        int capReq = 0;
        int availableCap = 0;
        for (Kennel k : availableKennels)
            availableCap += k.getCapacity();
        for (Dog d : this.currentBooking.getDogs())
            if (d.getSize() == Size.SMALL || d.getSize() == Size.MEDIUM)
                capReq++;
            else
                capReq += 2;
        if (availableCap < capReq)
            return "availability";


        for (Kennel k : availableKennels) {
            capReq -= k.getCapacity();
            this.currentBooking.getKennels().add(k);
            if (capReq < 1)
                break;
        }

        double total = (this.currentBooking.getDogs().size() * availableKennels.get(0).getCostPerDay()) *
                ChronoUnit.DAYS.between(this.currentBooking.getArrival(), this.currentBooking.getDeparture());
        if (this.currentBooking.isGroomingIncluded())
            total += 10 * this.currentBooking.getDogs().size();
        this.currentBooking.setTotal(total);

        return null;
    }


    public List<Kennel> findAvailableKennels(LocalDate arrival, LocalDate departure) {
        List<Kennel> allKennels = this.kennelService.findAll();
        List<Kennel> availableKennels = new ArrayList<>();


        for (Kennel k : allKennels) {
            //TODO : add kennels to available list if they are not found in any bookings over the chosen dates
            boolean isAvailable = true;

            ArrayList<Booking> kennelsBookings = this.bookingRepository.findBookingByKennelsContaining(k);

            for (Booking b : kennelsBookings) {
                if ((arrival.isAfter(b.getArrival().minusDays(1)) && arrival.isBefore(b.getDeparture().plusDays(1))) ||
                        (departure.isAfter(b.getArrival().minusDays(1)) && departure.isBefore(b.getDeparture().plusDays(1))) ||
                        (b.getArrival().isAfter(arrival) && b.getArrival().isBefore(departure)) ||
                        (b.getDeparture().isAfter(arrival) && b.getDeparture().isBefore(departure))
                ) {
                    isAvailable = false;
                    break;
                }
            }

            if (isAvailable)
                availableKennels.add(k);
        }

        return availableKennels;
    }

    public void finalizeBooking() {
        this.currentBooking.setStatus(Status.COMPLETE);
        this.bookingRepository.save(this.currentBooking);
    }


    public void paymentSucceeded(Payment payment) {
        if (payment == null)
            throw new IllegalStateException("No payment sent");

        this.getCurrentBooking().setPayment(payment);

        this.paymentService.save(payment);

        this.finalizeBooking();
    }

    public String cancelBooking(Long id) throws StripeException {

        Booking bookingToCancel = this.findBooking(id);

        String error = null;

        if (LocalDate.now().plusDays(3).isBefore(bookingToCancel.getArrival()))
            error = this.paymentService.refundPayment(bookingToCancel.getPayment().getIntentId());

        if (error != null)
            return error;

        this.bookingRepository.updateBooking(Status.CANCELED, id);

        return "?msg=bookingCanceled";
    }
}
