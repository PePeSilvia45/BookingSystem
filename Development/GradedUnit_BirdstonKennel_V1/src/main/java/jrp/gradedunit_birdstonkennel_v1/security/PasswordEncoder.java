package jrp.gradedunit_birdstonkennel_v1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * This class is used to encode the password of a user
 */
@Configuration
public class PasswordEncoder {

    //--------------------------------METHODS--------------------------------------

    /**
     * This method creates a new password encoder
     *
     * @return the newly created password encoder object
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
