package jrp.gradedunit_birdstonkennel_v1.models.registration.token;

import jrp.gradedunit_birdstonkennel_v1.models.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * This is a repository for handling the confirmation tokens for user registration
 */
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    /**
     * Finds the confirmation token in the database based on the token
     *
     * @param token The token being searched for
     * @return The information relating to the token from the confirmation token table
     */
    Optional<ConfirmationToken> findByToken(String token);

    /**
     * Used to find a confirmation token in the database using the user object
     *
     * @param appUser The user being searched for
     * @return The users confirmation token
     */
    Optional<ConfirmationToken> findByAppUser(AppUser appUser);

    /**
     * Updates a confirmation token in the database to set the confirmed at date and time
     *
     * @param token       The token to update
     * @param confirmedAt The date and time the token was confirmed
     * @return The result of the token being confirmed
     */
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c SET c.confirmedAt = ?2 WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
