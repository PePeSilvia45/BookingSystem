package jrp.gradedunit_birdstonkennel_v1.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Creates the update request object
 */
@Data
@AllArgsConstructor
public class UpdateRequest{

    /**
     * The id of the user to be edited
     */
    private Long id;
    /**
     * The email for the user which will also be used as the username
     */
    private String email;
    /**
     * The first name of the user
     */
    private String firstName;
    /**
     * The last name of the user
     */
    private String lastName;
    /**
     * Stores the date of birth, of the employee
     */
    private String dob;
    /**
     * The phone number of the user
     */
    private String phoneNumber;
    /**
     * Stores the first address line of the users Address
     */
    private String addressLine1;
    /**
     * Stores the second address line of the users Address
     */
    private String addressLine2;
    /**
     * Stores the town of the users Address
     */
    private String town;
    /**
     * Stores the postcode of the users Address
     */
    private String postcode;
    /**
     * Stores the name of the users emergency contact
     */
    private String emergencyContactName;
    /**
     * Stores the number of the users emergency contact
     */
    private String emergencyContactNumber;

}
