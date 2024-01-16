package jrp.gradedunit_birdstonkennel_v1.models.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jrp.gradedunit_birdstonkennel_v1.models.dog.Dog;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;

/**
 * This class is for creating an instance of a customer. It extends the user class
 */
@Data
@Entity
@ToString()
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CUSTOMER")
@EqualsAndHashCode(callSuper = true)
public class Customer extends AppUser {

    /**
     * This contractor creates an instance of this object with all its inherited attributes
     *
     * @param firstName              The first name
     * @param lastName               The lastname
     * @param dob                    The date of birth
     * @param email                  The email address
     * @param password               The password
     * @param phoneNumber            The user's Phone number
     * @param addressLine1           The users first address line
     * @param addressLine2           The users second address line
     * @param town                   The user's town
     * @param postcode               The users postcode
     * @param emergencyContactName   The users emergency contact name
     * @param emergencyContactNumber The users emergency contact number
     * @param role                   The role of the user
     */
    public Customer(String firstName, String lastName, LocalDate dob, String email, String password, String phoneNumber, String addressLine1, String addressLine2, String town, String postcode, String emergencyContactName, String emergencyContactNumber, Role role) {
        super(firstName, lastName, dob, email, password, phoneNumber, addressLine1, addressLine2, town, postcode, emergencyContactName, emergencyContactNumber, role);

    }

    /**
     * Stores the dogs belonging to this customer
     */
    @OneToMany(mappedBy = "owner")
    private Collection<Dog> dogs;
}
