package jrp.gradedunit_birdstonkennel_v1.controllers.booking;

import com.stripe.exception.StripeException;
import jrp.gradedunit_birdstonkennel_v1.models.booking.Booking;
import jrp.gradedunit_birdstonkennel_v1.models.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller is used for handling booking actions
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/booking")
public class BookingController {
    /**
     * Stores the stripePublicKey
     */
    @Value("${stripe.public.key}")
    private String stripePublicKey;

    private final BookingService bookingService;

    @GetMapping("/confirm_booking")
    public String confirmBooking(Model model) {

        model.addAttribute("stripePublicKey", this.stripePublicKey);
        model.addAttribute("total", bookingService.getCurrentBooking().getTotal());

        return "/customer/payment/checkout";
    }

    @PostMapping("/cancel_booking/{id}")
    public String cancelBooking(@PathVariable Long id) throws StripeException {

        String response = bookingService.cancelBooking(id);

        //TODO : implement Stripe refund && warning that if the booking is three days out no refund will be issued

        return "redirect:/customer/view_booking/{id}"+response;
    }


}
