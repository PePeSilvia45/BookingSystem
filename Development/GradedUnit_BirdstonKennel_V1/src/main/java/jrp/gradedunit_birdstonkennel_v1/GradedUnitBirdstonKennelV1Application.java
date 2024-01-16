package jrp.gradedunit_birdstonkennel_v1;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main method of the program which owns the main method
 */
@SpringBootApplication
public class GradedUnitBirdstonKennelV1Application {

    /**
     * Stores the injected Stripe api key
     */
    @Value("${stripe.api.key}")
    private String stripeApiKey;

    /**
     * Sets up the stripe API key
     */
    @PostConstruct
    public void setup(){
        Stripe.apiKey = stripeApiKey;
    }

    /**
     * This is the start point of the application
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(GradedUnitBirdstonKennelV1Application.class, args);
    }

}
