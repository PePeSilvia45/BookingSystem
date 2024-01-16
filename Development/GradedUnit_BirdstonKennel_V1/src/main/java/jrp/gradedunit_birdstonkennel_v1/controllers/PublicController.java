package jrp.gradedunit_birdstonkennel_v1.controllers;

import jrp.gradedunit_birdstonkennel_v1.models.booking.BookingService;
import jrp.gradedunit_birdstonkennel_v1.models.requests.AvailabilityRequest;
import jrp.gradedunit_birdstonkennel_v1.models.requests.ContactMessageRequest;
import jrp.gradedunit_birdstonkennel_v1.models.contact.ContactMessageService;
import jrp.gradedunit_birdstonkennel_v1.models.user.AppUser;
import jrp.gradedunit_birdstonkennel_v1.models.user.AppUserService;
import jrp.gradedunit_birdstonkennel_v1.models.user.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * This controller is used for home navigational and unregistered user actions
 */
@Controller
@AllArgsConstructor
@RequestMapping({"/", "public"})
public class PublicController {

    /**
     * Stores an instance of the ContactMessageService
     */
    private final ContactMessageService contactMessageService;
    /**
     * Stores an instance of the BookingService
     */
    private final BookingService bookingService;
    /**
     * Stores an instance of the AppUserService
     */
    private final AppUserService appUserService;

    /**
     * This method maps to the unregistered home page and will direct the user there on opening the website
     *
     * @param model Information to be sent to the front end to help handle contact messages and availability checking
     * @param error Catches any errors sent to the home page
     * @param msg   Catches any messages sent to the home page
     * @return The url for the home page
     */
    @GetMapping({"/home", "/"})
    public String about(Model model, String error, String msg) {

        if (appUserService.getCurrentRole() != null &&
                appUserService.getCurrentRole() != Role.CUSTOMER)
            return "redirect:/employee/dashboard";

        model.addAttribute("contact_form", new ContactMessageRequest());
        model.addAttribute("error", error);
        model.addAttribute("msg", msg);
        model.addAttribute("availability_check", new AvailabilityRequest());
        return "public/home";
    }


    /**
     * Takes the incoming contact message and passes it to the service to be saved
     *
     * @param request The message being passed through the contact us form
     * @return The unregistered user to the unregistered home page
     */
    @PostMapping(path = "/contact_us")
    public String contactUs(@ModelAttribute("contact_form") ContactMessageRequest request) {

        String validation = this.contactMessageService.saveContactMessage(request);
        if (validation != null)
            return validation;

        return "public/home";
    }

    @PostMapping("/check_availability")
    public String checkAvailability(@ModelAttribute("availability_check") AvailabilityRequest request) {

        String result = bookingService.checkAvailability(request);

        return "redirect:/public/home?" + result + "#availability";
    }

}
