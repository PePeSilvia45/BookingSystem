package jrp.gradedunit_birdstonkennel_v1.models.registration.token;

import jakarta.persistence.*;
import jrp.gradedunit_birdstonkennel_v1.models.user.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * This class is for creating confirmation tokens at the time of customer registration to be sent to customers email to activate account
 * The token will expire after 15 mins
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "CONFIRMATION_TOKENS")
public class ConfirmationToken {

    /**
     * Stores the tokens ID
     */
    @Id
    @SequenceGenerator(name = "confirmation_tokens_sequence", sequenceName = "confirmation_tokens_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_tokens_sequence")
    private Long id;
    /**
     * Stores the token
     */
    @Column(nullable = false)
    private String token;
    /**
     * Stores the time the token was created
     */
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime createdAt;
    /**
     * Stores the time the token will expire
     */
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime expiresAt;
    /**
     * Stores when the token was confirmed
     */
    @Column(nullable = true)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime confirmedAt;
    /**
     * Stores the user that this token belongs to
     */
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private AppUser appUser;

    /**
     * This is the constructor for creating an instance of a token object
     *
     * @param token     The token
     * @param createdAt The time the token was created
     * @param expiresAt The time the token will expire
     * @param appUser   The user this confirmation token belongs to
     */
    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }


}
