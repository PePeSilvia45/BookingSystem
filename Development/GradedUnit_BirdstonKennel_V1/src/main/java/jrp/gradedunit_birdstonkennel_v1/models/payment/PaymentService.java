package jrp.gradedunit_birdstonkennel_v1.models.payment;


import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.RefundCreateParams;
import jrp.gradedunit_birdstonkennel_v1.models.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentService  {

    private final PaymentRepository paymentRepository;


    public void save(Payment payment){
        this.paymentRepository.save(payment);
    }

    public String refundPayment(String id) throws StripeException {


        RefundCreateParams params = RefundCreateParams.builder().setPaymentIntent(id).build();

        if(params == null) {
            return "error=noIntent";
        }

        Refund refund = Refund.create(params);

        return null;
    }

}
