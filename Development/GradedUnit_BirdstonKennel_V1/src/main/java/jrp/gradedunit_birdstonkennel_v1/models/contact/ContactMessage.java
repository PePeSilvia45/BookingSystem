package jrp.gradedunit_birdstonkennel_v1.models.contact;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

import java.time.LocalDate;

/**
 * This class is used to create a contact object created when a user fills in the contact us form
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "CONTACT_MESSAGES")
public class ContactMessage {

    /**
     * Stores  the contact message id
     */
    @Id
    @SequenceGenerator(name = "contact_msg_sequence", sequenceName = "contact_msg_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_msg_sequence")
    private Long id;
    /**
     * Stores the email on the contact form
     */
    @Column(nullable = false)
    @NotBlank
    private String email;
    /**
     * Stores the topic on the contact form
     */
    @Column(nullable = false)
    @NotBlank
    private String topic;
    /**
     * Stores the message on the contact form
     */
    @Column(nullable = false)
    @NotBlank
    private String message;
    /**
     * Stores the date the message was sent
     */
    @Column(nullable = false)
    @NotBlank
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateSent;


    /**
     * This constructor makes a contact message object
     *
     * @param email    the email on the message form
     * @param topic    the topic on the message form
     * @param message  the message on the message form
     * @param dateSent the date the message form was sent
     */
    public ContactMessage(String email, String topic, String message, LocalDate dateSent) {
        this.email = email;
        this.topic = topic;
        this.message = message;
        this.dateSent = dateSent;
    }
}
