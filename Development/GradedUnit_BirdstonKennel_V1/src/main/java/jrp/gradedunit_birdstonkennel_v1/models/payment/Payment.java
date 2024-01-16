package jrp.gradedunit_birdstonkennel_v1.models.payment;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * This class holds all the information of a payment object used when making a booking
 */
@Data
@Entity
@EqualsAndHashCode
@Table(name = "PAYMENTS")
public class Payment {

    /**
     * Stores the payment id
     */
    @SequenceGenerator(name = "payment_sequence", sequenceName = "payment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_sequence")
    @Id
    private Long id;
    /**
     * Stores the id from the payment intent
     */
    @Column(nullable = false)
    private String intentId;
    /**
     * Stores the payment total
     */
    @Column(nullable = false)
    private double total;
    /**
     * Stores the price paid
     */
    @Column(nullable = false)
    private double pricePaid;
    /**
     * Stores the number of the card used to pay [WILL BE HASHED LATER]
     */
    @Column(nullable = false)
    private String paymentEmail;

    /**
     * This constructor creates a default Payment object
     */
    public Payment() {
    }

    /**
     * This contractor makes a payment object to be saved in the database
     *
     * @param intentId the id of the stripe payment intent
     * @param total The total of the order
     * @param pricePaid The total paid
     * @param paymentEmail the email used when paying
     */
    public Payment(String intentId, double total, double pricePaid, String paymentEmail) {
        this.intentId = intentId;
        this.total = total;
        this.pricePaid = pricePaid;
        this.paymentEmail = paymentEmail;
    }
}
