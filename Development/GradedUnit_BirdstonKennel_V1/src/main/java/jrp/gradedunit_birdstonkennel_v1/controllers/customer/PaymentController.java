package jrp.gradedunit_birdstonkennel_v1.controllers.customer;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.net.RequestOptions;
import com.stripe.param.PaymentIntentCreateParams;
import jrp.gradedunit_birdstonkennel_v1.models.booking.Booking;
import jrp.gradedunit_birdstonkennel_v1.models.booking.BookingService;
import jrp.gradedunit_birdstonkennel_v1.models.stripe.CreatePayment;
import jrp.gradedunit_birdstonkennel_v1.models.stripe.CreatePaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "payment")
@RequiredArgsConstructor
public class PaymentController {

    private final BookingService bookingService;

    @PostMapping(path = "/create-payment-intent")
    public CreatePaymentResponse createPaymentIntent(@RequestBody CreatePayment createPayment) throws StripeException {


        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("gbp")
                .setAmount((long) (bookingService.getCurrentBooking().getTotal() * 100L)) // create payment...what user wants to buy...how much
                .build();

        // Create a PaymentIntent with the order amount and currency
        PaymentIntent intent = PaymentIntent.create(createParams);

        return new CreatePaymentResponse(intent.getClientSecret());
    }

}