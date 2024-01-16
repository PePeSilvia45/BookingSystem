package jrp.gradedunit_birdstonkennel_v1.models.requests;

import jrp.gradedunit_birdstonkennel_v1.models.user.Role;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


/**
 * This class is used to capture the clients request to register a user
 */
@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class RegistrationRequest {
    /**
     * Stores the account type
     */
    private Role accountType;
    /**
     * Stores the firstName from the request
     */
    private String firstName;
    /**
     * Stores the lastName from the request
     */
    private String lastName;
    /**
     * Stores the dob from the request
     */
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;
    /**
     * Stores the email from the request
     */
    private String email;
    /**
     * Stores the password from the request
     */
    private String password;
    /**
     * Stores the confirmation password
     */
    private String confirmPassword;
    /**
     * Stores the phoneNumber from the request
     */
    private String phoneNumber;
    /**
     * Stores the addressLine1 from the request
     */
    private String addressLine1;
    /**
     * Stores the addressLine2 from the request
     */
    private String addressLine2;
    /**
     * Stores the town from the request
     */
    private String town;
    /**
     * Stores the postcode  from the request
     */
    private String postcode;
    /**
     * Stores the emergencyContactName from the request
     */
    private String emergencyContactName;
    /**
     * Stores the emergencyContactNumber from the request
     */
    private String emergencyContactNumber;

}
