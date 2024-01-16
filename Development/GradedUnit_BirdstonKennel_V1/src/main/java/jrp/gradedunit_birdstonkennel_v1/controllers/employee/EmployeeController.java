package jrp.gradedunit_birdstonkennel_v1.controllers.employee;

import jrp.gradedunit_birdstonkennel_v1.models.booking.Booking;
import jrp.gradedunit_birdstonkennel_v1.models.booking.BookingService;
import jrp.gradedunit_birdstonkennel_v1.models.registration.RegistrationService;
import jrp.gradedunit_birdstonkennel_v1.models.requests.ChangePasswordRequest;
import jrp.gradedunit_birdstonkennel_v1.models.requests.RegistrationRequest;
import jrp.gradedunit_birdstonkennel_v1.models.user.AppUserService;
import jrp.gradedunit_birdstonkennel_v1.models.user.Employee;
import jrp.gradedunit_birdstonkennel_v1.models.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final BookingService bookingService;

    private final AppUserService appUserService;
    private final RegistrationService registrationService;

    @GetMapping(path = "/dashboard")
    public String employeeDash(Model model, String msg) {

        Employee current = appUserService.getCurrentEmployeeFromContext();
        String name = current.getFirstName() + ' ' +
                current.getLastName();

        model.addAttribute("bookings", bookingService.getAllBookings());
        model.addAttribute("todaysArrivals", bookingService.getTodaysArrivals());
        model.addAttribute("todaysDepartures", bookingService.getTodaysDepartures());
        model.addAttribute("monthly", bookingService.getMonthlyEarnings());
        model.addAttribute("yearly", bookingService.getYearlyEarnings());
        model.addAttribute("currentGuests", bookingService.getCurrentGuests());
        model.addAttribute("currentUser", name);
        model.addAttribute("currentUserRole", current.getRole().name());
        model.addAttribute("msg", msg);
        return "employee/dashboard";
    }


    @GetMapping(path = "/view_all_bookings")
    public String allBookings(Model model) {

        ArrayList<Booking> allBookings = bookingService.getAllBookings();

        model.addAttribute("all_bookings", allBookings);

        return "shared/viewAllBookings";
    }


    @GetMapping(path = "/view_employees")
    public String viewEmployees(Model model, String msg) {

        model.addAttribute("employees", appUserService.getAllEmployees());
        model.addAttribute("msg", msg);

        return "employee/viewAllEmployees";
    }

    @GetMapping("/create_employee")
    public String createEmployee(Model model, String error) {

        model.addAttribute("new_employee_request", new RegistrationRequest());
        model.addAttribute("roles", Role.values());
        model.addAttribute("error", error);

        return "employee/createEmployee";
    }

    @PostMapping("/create_employee")
    public String createEmployee(@ModelAttribute("new_employee_request") RegistrationRequest request) {


        //TODO: fix validation redirects [first find all instances effected by the validation service]

        String error = registrationService.validateRegistration(request);
        if (error != null)
            return error;

        error = registrationService.register(request);
        if (error != null)
            return error;

        return "redirect:/employee/view_employees?msg=empCreated";
    }

    @GetMapping("/change_password")
    public String choosePassword(Model model, String error) {

        if(!appUserService.getCurrentEmployeeFromContext().isPasswordDefault())
            return "redirect:/employee/dashboard";

        model.addAttribute("error", error);
        model.addAttribute("change_password_request", new ChangePasswordRequest());
        return "employee/changePassword";
    }

    @PostMapping("/change_password")
    public String choosePassword(@ModelAttribute("change_password_request") ChangePasswordRequest request) {

        String error = appUserService.validatePasswordChange(request);
        if (error != null)
            return "redirect:/employee/change_password?" + error;

        appUserService.updateUserPassword(appUserService.getCurrentEmployeeFromContext().getId(), request);

        return "redirect:/employee/dashboard?msg=passwordUpdated";
    }

    @GetMapping(path = "/view_customers")
    public String viewCustomers(Model model) {
        model.addAttribute("customers", appUserService.getAllCustomers());
        return "employee/viewAllCustomers";
    }

}
