package jrp.gradedunit_birdstonkennel_v1.models.user;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

/**
 * The use of this class is to create a User for the website to store all the data related to the user that is shared between every type of user
 */
@Data
@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "USERS")
@Inheritance(
        strategy = InheritanceType.SINGLE_TABLE
)
@DiscriminatorColumn(
        name = "user_type",
        discriminatorType = DiscriminatorType.STRING
)
public class AppUser implements UserDetails {


    /**
     * The id for the user this will always be unique to the user
     */
    @SequenceGenerator(name = "users_sequence", sequenceName = "users_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @Id
    private Long id;
    /**
     * The first name of the user
     */
    @Column(nullable = false)
    private String firstName;
    /**
     * The last name of the user
     */
    @Column(nullable = false)
    private String lastName;
    /**
     * Stores the date of birth of the employee
     */
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-YYYY")
    private LocalDate dob;
    /**
     * The email for the user which will also be used as the username
     */
    @Column(nullable = false)
    private String email;
    /**
     * The password for the user
     */
    @Column(nullable = false)
    private String password;
    /**
     * The phone number of the user
     */
    @Column(nullable = false)
    private String phoneNumber;
    /**
     * Stores whether the account has been confirmed or not
     */
    @Column(nullable = false)
    private boolean emailConfirmed;
    /**
     * Stores whether the account is locked or not
     */
    @Column(nullable = false)
    private boolean accountLocked;
    /**
     * Stores the first address line of the users Address
     */
    @Column(nullable = false)
    private String addressLine1;
    /**
     * Stores the second address line of the users Address
     */
    private String addressLine2;
    /**
     * Stores the town of the users Address
     */
    @Column(nullable = false)
    private String town;
    /**
     * Stores the postcode of the users Address
     */
    @Column(nullable = false)
    private String postcode;
    /**
     * Stores the name of the users emergency contact
     */
    @Column(nullable = false)
    private String emergencyContactName;
    /**
     * Stores the number of the users emergency contact
     */
    @Column(nullable = false)
    private String emergencyContactNumber;
    /**
     * This is the role of the user
     */
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * This constructor is used to create a user object.
     * The emailConfirmed and accountLocked attributes will be set to false by default
     *
     * @param firstName              The users first name
     * @param lastName               The users last name
     * @param dob                    The user's date of birth
     * @param email                  The user's email which is also their username
     * @param password               The user's password
     * @param phoneNumber            The user's phone number
     * @param addressLine1           This first line of the users address
     * @param addressLine2           This second line of the users address
     * @param town                   This town of the users address
     * @param postcode               This postcode of the users address
     * @param emergencyContactName   The name of the users emergency contact
     * @param emergencyContactNumber The contact number of the users emergency contact
     * @param role                   The user's role
     */
    public AppUser(String firstName, String lastName, LocalDate dob, String email, String password, String phoneNumber,
                   String addressLine1, String addressLine2, String town, String postcode,
                   String emergencyContactName, String emergencyContactNumber, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.town = town;
        this.postcode = postcode;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;

        this.emailConfirmed = false;
        this.accountLocked = false;

        this.role = role;
    }


    /**
     * Used to retrieve all the authorities this user has on the site depending on the users role
     *
     * @return a collection of the authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role.toString());
        return Collections.singleton(authority);
    }

    /**
     * @return users password
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * @return the users email which is being used as the username
     */
    @Override
    public String getUsername() {
        return this.email;
    }

    /**
     * @return false because the account won't expire at this stage
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return true if account is not locked
     */
    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    /**
     * @return true always because the account credentials won't expire at this stage
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return whether the account is confirmed or not
     */
    @Override
    public boolean isEnabled() {
        return this.emailConfirmed;
    }

}
