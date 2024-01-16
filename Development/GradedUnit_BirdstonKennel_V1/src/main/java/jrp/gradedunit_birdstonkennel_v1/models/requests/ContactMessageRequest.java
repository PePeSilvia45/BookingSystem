package jrp.gradedunit_birdstonkennel_v1.models.requests;

import lombok.*;

/**
 * This class takes in information relating to a new contact message to be stored.
 * These messages should be sent to a company email
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ContactMessageRequest {
    /**
     * Stores the email on the contact form
     */
    private String email;
    /**
     * Stores the topic on the contact form
     */
    private String topic;
    /**
     * Stores the message on the contact form
     */
    private String message;

}
