package jrp.gradedunit_birdstonkennel_v1.controllers.customer;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.*;
import com.stripe.net.Webhook;
import jrp.gradedunit_birdstonkennel_v1.models.booking.BookingService;
import jrp.gradedunit_birdstonkennel_v1.models.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StripeWebhookController {

    @Value("${stripe.webhook.secret}")
    private String endpointSecret;

    private final BookingService bookingService;

    private final Logger logger = LoggerFactory.getLogger(StripeWebhookController.class);

    @PostMapping("stripe/events")
    public String handleStripeEvents(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {

        if (sigHeader == null)
            return "";

        // Only verify the event if you have an endpoint secret defined.
        // Otherwise, use the basic event deserialized with GSON.
        Event event;
        try {
            event = Webhook.constructEvent(
                    payload, sigHeader, endpointSecret
            );
        } catch (SignatureVerificationException e) {
            // Invalid signature
            logger.info("Webhook error while validating signature.");
            return "";
        }

        // Deserialize the nested object inside the event
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
            logger.info("Deserialization failed : possibly due to API version mismatch");
        }
        // Handle the event
        switch (event.getType()) {
            case "payment_intent.succeeded":
                PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                logger.info("Payment for id={}, {} succeeded.", paymentIntent.getId(), paymentIntent.getAmount());
                // Then define and call a method to handle the successful payment intent.
                // handlePaymentIntentSucceeded(paymentIntent); DATABASE

                this.bookingService.paymentSucceeded(new Payment(
                        paymentIntent.getId(),
                        paymentIntent.getAmount(),
                        paymentIntent.getAmountReceived(),
                        paymentIntent.getReceiptEmail()
                ));

                return "";
            case "payment_method.attached":
                PaymentMethod paymentMethod = (PaymentMethod) stripeObject;
                // Then define and call a method to handle the successful attachment of a PaymentMethod.
                // handlePaymentMethodAttached(paymentMethod);
                break;
            default:
                logger.warn("Unhandled event type: {}", event.getType());
                break;
        }
        return "";
    }

}