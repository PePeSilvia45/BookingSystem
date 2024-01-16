package jrp.gradedunit_birdstonkennel_v1.models.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * This service is for handling the confirmation token
 */
@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    /**
     * Stores the ConfirmationTokenRepository object
     */
    private final ConfirmationTokenRepository confirmationTokenRepository;


    /**
     * Saves the confirmation token to the database
     *
     * @param confirmationToken The confirmation token being saved
     */
    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    /**
     * Gets the confirmation token from the database based oin the token
     *
     * @param token the token being searched for
     * @return the confirmation token information from the database
     */
    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    /**
     * Sets the confirmation date and time of the confirmation token
     *
     * @param token The token being updated
     * @return The result of the token being updated
     */
    public int setConfirmedAt(String token){
        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

}
