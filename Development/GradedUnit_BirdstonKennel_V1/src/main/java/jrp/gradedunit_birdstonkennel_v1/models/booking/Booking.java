package jrp.gradedunit_birdstonkennel_v1.models.booking;

import jakarta.persistence.*;
import jrp.gradedunit_birdstonkennel_v1.models.booking.enums.Status;
import jrp.gradedunit_birdstonkennel_v1.models.payment.Payment;
import jrp.gradedunit_birdstonkennel_v1.models.dog.Dog;
import jrp.gradedunit_birdstonkennel_v1.models.kennel.Kennel;
import jrp.gradedunit_birdstonkennel_v1.models.user.Customer;
import jrp.gradedunit_birdstonkennel_v1.models.user.Employee;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class holds all the information of a booking objects
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "BOOKINGS")
public class Booking {

    /**
     * Stores the id of the booking
     */
    @SequenceGenerator(name = "booking_sequence", sequenceName = "booking_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_sequence")
    @Id
    private Long id;
    /**
     * Stores the time the booking was created
     */
    @DateTimeFormat(pattern = "dd-MM-YYYY : HH:mm")
    private LocalDateTime timeOfBooking;
    /**
     * Stores the drop-off date on the booking
     */
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate arrival;
    /**
     * Stores the pickup date on the booking
     */
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate departure;
    /**
     * Stores if grooming will be included in the booking or not
     */
    private boolean groomingIncluded;
    /**
     * Stores the total of the booking
     */
    private double total;
    /**
     * Stores the status of the booking
     */
    private Status status;
    /**
     * Stores the customers id
     */
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "customer_id"
    )
    private Customer customer;
    /**
     * Stores the dogs in this bookings
     */
    @ManyToMany
    @JoinTable(
            name = "bookings_dogs",
            joinColumns = {@JoinColumn(name = "booking_id")},
            inverseJoinColumns = {@JoinColumn(name = "dog_id")}
    )
    private Collection<Dog> dogs;
    /**
     * Stores the kennels booked for this booking
     */
    @ManyToMany
    @JoinTable(
            name = "bookings_kennels",
            joinColumns = {@JoinColumn(name = "booking_id")},
            inverseJoinColumns = {@JoinColumn(name = "kennel_id")}
    )
    private Collection<Kennel> kennels;
    /**
     * Sores the payment for this booking
     */
    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "payment_id"
    )
    private Payment payment;


    /**
     * This constructor will make a booking object with the id, drop-off and pickup dates,
     * and whether grooming is included or not
     *
     * @param arrival          The booking drop-off date
     * @param departure        The booking pickup date
     * @param groomingIncluded Whether grooming is included or not
     * @param customer         The customers id
     * @param payment          The payment for this booking
     */
    public Booking(LocalDate arrival, LocalDate departure, boolean groomingIncluded, Customer customer, Payment payment) {
        this.arrival = arrival;
        this.departure = departure;
        this.groomingIncluded = groomingIncluded;
        this.customer = customer;
        this.payment = payment;
    }


    public Booking(LocalDate arrival, LocalDate departure, Customer customer, Boolean groomingIncluded) {
        this.arrival = arrival;
        this.departure = departure;
        this.customer = customer;
        this.groomingIncluded = groomingIncluded;
        this.dogs = new ArrayList<>();
        this.kennels = new ArrayList<>();
        this.timeOfBooking = LocalDateTime.now();
    }

    /**
     * @return the booking in the form of a string
     */
    @Override
    public String toString() {
        return "Booking\n{" +
                "\n\tarrivalDate='" + this.arrival + '\'' +
                "\n\tdepartureDate='" + this.departure + '\'' +
                "\n\tgroomingIncluded='" + this.groomingIncluded + '\'' +
                "\n}";
    }

    public String dogsToString(){
        StringBuilder returnString = new StringBuilder();
        int i = 0;
        for(Dog d : this.dogs){
            if(i<1){
                returnString.append(d.getName());
                i++;
            }else
                returnString.append(", ").append(d.getName());
        }
        return String.valueOf(returnString);
    }
    public String kennelsToString(){
        StringBuilder returnString = new StringBuilder();
        int i = 0;
        for(Kennel k : this.kennels){
            if(i<1){
                returnString.append(k.getId());
                i++;
            }else
                returnString.append(", ").append(k.getId());
        }
        return String.valueOf(returnString);
    }


}


























