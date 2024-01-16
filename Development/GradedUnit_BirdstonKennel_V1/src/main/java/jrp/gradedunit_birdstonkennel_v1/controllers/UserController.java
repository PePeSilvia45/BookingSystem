package jrp.gradedunit_birdstonkennel_v1.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jrp.gradedunit_birdstonkennel_v1.models.dog.Dog;
import jrp.gradedunit_birdstonkennel_v1.models.dog.DogService;
import jrp.gradedunit_birdstonkennel_v1.models.requests.LoginRequest;
import jrp.gradedunit_birdstonkennel_v1.models.requests.RegistrationRequest;
import jrp.gradedunit_birdstonkennel_v1.models.registration.RegistrationService;
import jrp.gradedunit_birdstonkennel_v1.models.requests.UpdateRequest;
import jrp.gradedunit_birdstonkennel_v1.models.user.AppUserService;
import jrp.gradedunit_birdstonkennel_v1.models.user.Customer;
import jrp.gradedunit_birdstonkennel_v1.models.user.Employee;
import jrp.gradedunit_birdstonkennel_v1.models.user.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * This class holds all the user related controllers used for handling any actions relating to users and their accounts
 */
@Controller
@AllArgsConstructor
@RequestMapping(path = "/user")
public class UserController {

    /**
     * Stores an instance of the appUserService
     */
    private final AppUserService appUserService;
    /**
     * Stores an instance of the DogService
     */
    private final DogService dogService;


    /**
     * Stores an instance of the RegistrationService
     */
    private final RegistrationService registrationService;

    /**
     * This is the GET action for the registration view which populates the model with the Registration Request
     *
     * @param model The model for the view with the Registration Request attached
     * @param error Catches any errors sent to the view
     * @return direction to the register view
     */
    @GetMapping(path = "/register")
    public String register(Model model, String error) {

        RegistrationRequest request = new RegistrationRequest();
        model.addAttribute("reg_form", request);
        model.addAttribute("error", error);
        return "public/register";

    }


    /**
     * This is the POST action for the registration view which registers a users details
     *
     * @param request the information from the registration form
     * @return redirection to the login page
     */
    @PostMapping(path = "/register")
    public String register(@ModelAttribute("reg_form") RegistrationRequest request) {

        //set user as customer
        request.setAccountType(Role.CUSTOMER);

        String check = registrationService.validateRegistration(request);
        if (check != null)
            return check;

        String sendFail = this.registrationService.register(request);
        if (sendFail != null)
            return sendFail;

        return "redirect:/public/home?msg=confirmEmail";
    }

    /**
     * This is the GET action for the login view
     *
     * @param model The model for the view with the login request object attached
     * @return direction to the login method
     */
    @GetMapping(path = "/login")
    public String login(Model model) {

        LoginRequest loginRequest = new LoginRequest();
        model.addAttribute("login_request", loginRequest);

        return "public/login";
    }

    /**
     * This action is triggered on a successful login
     *
     * @param loginRequest The information from the login view
     * @return redirection to the home page if the user is a customer and to the employee dash if they are not
     */
    @RequestMapping(path = "/login_success")
    public String perform_login(@ModelAttribute("login_request") LoginRequest loginRequest) {

        Role usersRole = appUserService.getCurrentRole();

        if (usersRole == null || usersRole.equals(Role.CUSTOMER))
            return "redirect:../public/home";
        else {
            if(appUserService.getCurrentEmployeeFromContext().isPasswordDefault()) {
                return "redirect:/employee/change_password";
            }
            return "redirect:/employee/dashboard";
        }
    }


    /**
     * Takes the token and confirms the confirmation token in the database
     *
     * @param token The token to be confirmed
     * @param model The model to be passed to the next view
     * @return the result of the token being confirmed
     */
    @RequestMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token, Model model) {

        //TODO: make employees choose a new password


        registrationService.confirmToken(token);
        LoginRequest loginRequest = new LoginRequest();
        model.addAttribute("login_request", loginRequest);
        return "public/login";
    }

    /**
     * This method logs out the current user
     *
     * @param request  The request from the servlet
     * @param response The response to the servlet
     * @return redirects the now logged-out user to the login page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String preform_logout(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/user/login?logout";
    }


    /**
     * This is the POST method for the update profile function
     *
     * @param id      The id of the user being updated
     * @param request The information to update
     * @return Direction or Redirection to the appropriate view
     */
    @RequestMapping(value = "/update_profile/{id}")
    public String updateUserDetails(@PathVariable Long id, @ModelAttribute("update_request") UpdateRequest request) {

        if (request == null || (id == null || id < 1))
            throw new IllegalStateException("No user details to edit");

        String validation = appUserService.validateUserUpdate(request);
        if (validation != null)
            return validation;

        appUserService.updateUser(id, request);

        if (appUserService.getCurrentRole() == Role.CUSTOMER)
            return "redirect:/customer/my_profile?msg=success";
        else
            return "redirect:/employee/view_employees?msg=success";
    }

}


