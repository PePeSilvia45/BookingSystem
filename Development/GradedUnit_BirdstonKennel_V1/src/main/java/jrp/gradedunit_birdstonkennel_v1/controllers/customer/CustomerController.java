package jrp.gradedunit_birdstonkennel_v1.controllers.customer;

import jrp.gradedunit_birdstonkennel_v1.models.booking.Booking;
import jrp.gradedunit_birdstonkennel_v1.models.booking.BookingService;
import jrp.gradedunit_birdstonkennel_v1.models.dog.Dog;
import jrp.gradedunit_birdstonkennel_v1.models.dog.DogService;
import jrp.gradedunit_birdstonkennel_v1.models.payment.PaymentService;
import jrp.gradedunit_birdstonkennel_v1.models.requests.NewBookingRequest;
import jrp.gradedunit_birdstonkennel_v1.models.requests.NewDogRequest;
import jrp.gradedunit_birdstonkennel_v1.models.requests.UpdateDogRequest;
import jrp.gradedunit_birdstonkennel_v1.models.requests.UpdateRequest;
import jrp.gradedunit_birdstonkennel_v1.models.user.AppUserService;
import jrp.gradedunit_birdstonkennel_v1.models.user.Customer;
import jrp.gradedunit_birdstonkennel_v1.models.vet.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This controller handles customer views
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/customer")
public class CustomerController {

    /**
     * Stores an instance of the AppUserService object
     */
    private final AppUserService appUserService;
    /**
     * Stores an instance of the DogService object
     */
    private final DogService dogService;
    /**
     * Stores an instance of the VetService object
     */
    private final VetService vetService;
    /**
     * Stores an instance of the BookingService object
     */
    private final BookingService bookingService;
    /**
     * Stores an instance of the PaymentService object
     */
    private final PaymentService paymentService;


//======================================================================================================================
//              CUSTOMER PROFILE                                                                                  ======
//======================================================================================================================
    /**
     * This method adds the current users details to the model then passes it to the profile page
     *
     * @param model The model being passed to the profile view
     * @param msg   Catches any messages being sent
     * @return direction to the profile view
     */
    @GetMapping(path = "/my_profile")
    public String myProfile(Model model, String msg, String error) {

        Customer currentUser = appUserService.getCurrentCustomerFromContext();
        if (currentUser == null)
            return "redirect:/user/logout";

        currentUser.setDogs(dogService.getDogs(currentUser));

        Collection<Dog> dogs = currentUser.getDogs();

        model.addAttribute("current_user", currentUser);
        model.addAttribute("dogs", dogs);
        model.addAttribute("msg", msg);
        model.addAttribute("error", error);


        return "shared/myProfile";
    }

    /**
     * This is the get method for the edit profile page
     *
     * @param id    The id of the user to be edited
     * @param model The model being sent to the view
     * @param error Catches any errors passed to the view
     * @return The editProfile view
     */
    @GetMapping(path = "/edit_profile/{id}")
    public String editProfile(@PathVariable Long id, Model model, String error) {

        Customer userToEdit = (Customer) appUserService.loadUserById(id);
        model.addAttribute("error", error);
        model.addAttribute("update_request", new UpdateRequest(
                id,
                userToEdit.getEmail(),
                userToEdit.getFirstName(),
                userToEdit.getLastName(),
                userToEdit.getDob().toString(),
                userToEdit.getPhoneNumber(),
                userToEdit.getAddressLine1(),
                userToEdit.getAddressLine2(),
                userToEdit.getTown(),
                userToEdit.getPostcode(),
                userToEdit.getEmergencyContactName(),
                userToEdit.getEmergencyContactNumber()
        ));

        return "customer/editProfile";
    }


//======================================================================================================================
//              DOGS                                                                                              ======
//======================================================================================================================
    @GetMapping(path = "/add_dog")
    public String addDog(Model model, String error) {
        model.addAttribute("new_dog_request", new NewDogRequest());
        if (error != null)
            model.addAttribute("error", error);
        return "customer/addNewDog";
    }

    @RequestMapping("/add_dog")
    public String addDog(Model model, @ModelAttribute("new_dog_request") NewDogRequest request) {

        if (request == null)
            throw new IllegalStateException("no request received");

        String error = dogService.validateRequest(request);
        if (error != null)
            return "redirect:/customer/add_dog?error=" + error;


        dogService.addDog(request);

        return "redirect:/customer/my_profile?msg=newDogAdded";
    }

    @GetMapping("/edit_dog/{id}")
    public String editDog(@PathVariable Long id, Model model, String error){

        Dog dogToEdit = dogService.getDogById(id);

        model.addAttribute("dog_id", id);
        model.addAttribute("dog_update_request", new UpdateDogRequest(
                dogToEdit.getName(),
                dogToEdit.getBreed(),
                dogToEdit.getAge(),
                dogToEdit.getSex(),
                dogToEdit.getSize(),
                dogToEdit.isSpayedNeutered(),
                dogToEdit.isVaccinated(),
                dogToEdit.getWeight(),
                dogToEdit.getInsuranceProvider(),
                dogToEdit.getMicrochipNumber(),
                dogToEdit.getMedicalNotes(),
                dogToEdit.getBehaviouralNotes()
        ));

        return "/customer/editDog";
    }

    @PostMapping("/edit_dog/{id}")
    public String editDog(@ModelAttribute("dog") UpdateDogRequest request, @PathVariable Long id){

        String error = dogService.updateDog(request, id);

        return "redirect:/customer/my_profile?error="+error;
    }

    @GetMapping("/delete_dog/{id}")
    public String deleteDog(@PathVariable Long id){

        dogService.setDogInactive(id);

        return "redirect:/customer/my_profile?msg=dogRemoved";
    }

//======================================================================================================================
//              BOOKINGS                                                                                          ======
//======================================================================================================================
    /**
     * This is the get method for the makeBooking view
     *
     * @param model The model being passed to the view
     * @return direction to the makeBooking view
     */
    @GetMapping(path = "/make_booking")
    public String makeBooking(Model model, String error) {

        model.addAttribute("error", error);
        model.addAttribute("new_booking_request", new NewBookingRequest());
        model.addAttribute(
                "dogs",
                dogService.getDogs(appUserService.getCurrentCustomerFromContext())
        );

        return "customer/makeBooking";
    }

    @RequestMapping("/make_booking")
    public String makeBooking(@ModelAttribute("new_booking_request") NewBookingRequest request) {

        String error;

        error = bookingService.validateRequest(request);
        if (error != null)
            return "redirect:/customer/make_booking?error=" + error;

        error = bookingService.createBooking(request);
        if(error != null)
            return "redirect:/customer/make_booking?error="+error;


        return "redirect:/customer/confirm_booking";
    }

    @GetMapping("/confirm_booking")
    public String confirmBooking(Model model, String msg, String error){

        boolean addGrooming = false;

        model.addAttribute("booking", bookingService.getCurrentBooking());
        model.addAttribute("add_grooming", addGrooming);

        return "customer/confirmBooking";
    }

    @GetMapping("/view_bookings")
    public String viewAllBookings(Model model){

        ArrayList<Booking> allBookings = bookingService.getAllByCustomer(appUserService.getCurrentCustomerFromContext());

        model.addAttribute("all_bookings", allBookings);

        return "shared/viewAllBookings";
    }

    @GetMapping("/view_booking/{id}")
    public String viewBooking(Model model, @PathVariable Long id, String msg, String error){

        Booking booking = bookingService.findBooking(id);
        model.addAttribute("payment", booking.getPayment());
        model.addAttribute("booking", booking);
        model.addAttribute("msg", msg);
        model.addAttribute("error", error);
        model.addAttribute("canCancel", (
                !booking.getStatus().name().equals("CANCELED") &&
                booking.getArrival().isAfter(LocalDate.now())) );


        return "shared/viewBooking";
    }


}
