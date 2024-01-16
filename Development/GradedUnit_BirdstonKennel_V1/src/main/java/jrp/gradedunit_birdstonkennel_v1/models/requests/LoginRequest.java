package jrp.gradedunit_birdstonkennel_v1.models.requests;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * This class creates login requests
 */
@RequiredArgsConstructor
@Data
public class LoginRequest {
    /**
     * Stores the username entered
     */
    private String username;
    /**
     * Stores the password entered
     */
    private String password;
}
