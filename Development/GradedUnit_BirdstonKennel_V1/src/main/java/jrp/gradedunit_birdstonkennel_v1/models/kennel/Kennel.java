package jrp.gradedunit_birdstonkennel_v1.models.kennel;

import jakarta.persistence.*;
import jrp.gradedunit_birdstonkennel_v1.models.booking.Booking;
import jrp.gradedunit_birdstonkennel_v1.models.user.Employee;
import lombok.*;

import java.util.Collection;

/**
 * This class is used to hold all the information of a kennel object
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "KENNELS")
public class Kennel {

    /**
     * Stores the kennels id
     */
    @SequenceGenerator(name = "kennels_sequence", sequenceName = "kennels_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kennels_sequence")
    @Id
    private int id;
    /**
     * Stores the cost per dog per day
     */
    @Column(nullable = false)
    private double costPerDay;
    /**
     * Stores kennel capacity
     */
    private int capacity;
    /**
     * Stores the keepers id
     */
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "employee_id"
    )
    private Employee keeper;

    @ManyToMany(mappedBy = "kennels")
    private Collection<Booking> bookings;


    /**
     * This constructor is used to make a Kennel object
     *
     * @param capacity the kennel capacity
     * @param keeper   the keepers of this kennel
     */
    public Kennel(int capacity, Employee keeper) {
        this.capacity = capacity;
        this.keeper = keeper;
    }

}
