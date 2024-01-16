package jrp.gradedunit_birdstonkennel_v1.models.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * This interface allows us to query the User table
 */
@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /**
     * Finds a user in the users table based on the username
     *
     * @param email The email being searched for
     * @return The user with the requested email
     */
    Optional<AppUser> findByEmail(String email);

    boolean existsAppUserByEmail(String email);


    /**
     * This method is used to enable a user based on the email
     *
     * @param email The email of the user being enabled
     * @return The number of rows updated
     */
    @Transactional
    @Modifying
    @Query("UPDATE AppUser a SET a.emailConfirmed = TRUE WHERE a.email = ?1")
    void enableAppUser(String email);

    /**
     * This method updates a user's details in the database
     *
     * @param id                     The id of the user being updated
     * @param firstName              The updated firstname
     * @param lastName               The updated lastName
     * @param dob                    The updated date of birth
     * @param phoneNumber            The updated phone number
     * @param addressLine1           The updated address line 1
     * @param addressLine2           The updated address line 2
     * @param town                   The updated town
     * @param postcode               The updated postcode
     * @param emergencyContactName   The updated emergency contact name
     * @param emergencyContactNumber The updated emergency contact number
     * @return the number of rows updated
     */
    @Transactional
    @Modifying
    @Query("UPDATE AppUser a SET " +
                "a.firstName = ?2, " +
                "a.lastName = ?3, " +
                "a.dob = ?4, " +
                "a.phoneNumber = ?5, " +
                "a.addressLine1 = ?6, " +
                "a.addressLine2 = ?7, " +
                "a.town = ?8, " +
                "a.postcode = ?9, " +
                "a.emergencyContactName = ?10, " +
                "a.emergencyContactNumber = ?11 " +
            "where a.id =?1")
    void updateUser(Long id, String firstName, String lastName, LocalDate dob, String phoneNumber, String addressLine1,
                   String addressLine2, String town, String postcode, String emergencyContactName,
                   String emergencyContactNumber);

    @Query("UPDATE AppUser a SET a.password = ?2 WHERE a.id = ?1")
    @Modifying
    @Transactional
    void updateUserPassword(Long id, String password);

    @Query("UPDATE Employee e SET e.password = ?2, e.passwordDefault = false WHERE e.id = ?1")
    @Modifying
    @Transactional
    void updateEmployeeDefaultPassword(Long id, String password);
}
