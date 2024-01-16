package jrp.gradedunit_birdstonkennel_v1.models.user;

import jrp.gradedunit_birdstonkennel_v1.models.registration.token.ConfirmationToken;
import jrp.gradedunit_birdstonkennel_v1.models.registration.token.ConfirmationTokenService;
import jrp.gradedunit_birdstonkennel_v1.models.requests.ChangePasswordRequest;
import jrp.gradedunit_birdstonkennel_v1.models.requests.UpdateRequest;
import jrp.gradedunit_birdstonkennel_v1.models.validation.DataValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import static java.time.LocalDate.parse;


/**
 * This class allows us to interact with the User table
 */
@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    /**
     * Stores constant for holding the message for the 'user not found' exception based on email
     */
    private final static String EMAIL_NOT_FOUND_MSG = "User with email %s not found";
    /**
     * Stores constant for holding the message for the user not found exception based on id
     */
    private final static String ID_NOT_FOUND_MSG = "User with email %s not found";
    /**
     * Stores constant for holding the message for the 'user not found' exception
     */
    private final static String EMAIL_TAKEN = "Email %s is already taken";

    /**
     * Stores an instance of a user repository
     */
    private final AppUserRepository appUserRepository;
    /**
     * Stores an instance of the password encoder
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    /**
     * Stores an instance of the ConfirmationTokenService object
     */
    private final ConfirmationTokenService confirmationTokenService;

    /**
     * Stores an instance of the DataValidation object
     */
    private final DataValidator validator;



    /**
     * This method allows us to find users by their username, which in our case will be their email
     *
     * @param email The username to find
     * @return The found user
     * @throws UsernameNotFoundException Throws if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (email == null || email.isEmpty())
            throw new UsernameNotFoundException("No Username Provided");

        return appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
                String.format(EMAIL_NOT_FOUND_MSG, email)
        ));
    }

    public boolean userExists(String email) { return appUserRepository.existsAppUserByEmail(email);}

    /**
     * Loads a user by the ID
     *
     * @param id ID of the user being searched for
     * @return The user information if found
     * @throws UsernameNotFoundException If no user is found
     */
    public AppUser loadUserById(Long id) throws UsernameNotFoundException {

        if (id == null || id < 1)
            throw new UsernameNotFoundException("No Id Provided");

        return appUserRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(
                String.format(ID_NOT_FOUND_MSG, id)
        ));

    }

    public ArrayList<Employee> getAllEmployees(){
        ArrayList<AppUser> users = (ArrayList<AppUser>) appUserRepository.findAll();
        ArrayList<Employee> emps = new ArrayList<>();
        for(AppUser u : users)
            if(u.getClass().equals(Employee.class))
                emps.add((Employee)u);
        return emps;
    }

    public ArrayList<Customer> getAllCustomers(){
        ArrayList<AppUser> users = (ArrayList<AppUser>) appUserRepository.findAll();
        ArrayList<Customer> custs = new ArrayList<>();
        for(AppUser u : users)
            if(u.getClass().equals(Customer.class))
                custs.add((Customer)u);
        return custs;
    }

    /**
     * This method is used to sign up a new user
     *
     * @param appUser The user being signed up
     * @return The link for confirming the user
     */
    public String signUpUser(AppUser appUser) {

        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException(String.format(EMAIL_TAKEN, appUser.getEmail()));
        }

        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);

        if(appUser.getClass().equals(Customer.class)){
            String token = UUID.randomUUID().toString();
            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    appUser
            );

            confirmationTokenService.saveConfirmationToken(confirmationToken);
            return token;
        }
        return null;
    }

    /**
     * Enables the user based on the email address
     *
     * @param email the email of the user being enabled
     */
    public void enableAppUser(String email) {
        appUserRepository.enableAppUser(email);
    }

    /**
     * This method sends on the user's details being updated to the repository
     *
     * @param id      The id of the user being updated
     * @param request The details to be updated into the database
     */
    public void updateUser(Long id, UpdateRequest request) {

        LocalDate dob = parse(request.getDob());

        appUserRepository.updateUser(
                id,
                request.getFirstName(),
                request.getLastName(),
                dob,
                request.getPhoneNumber(),
                request.getAddressLine1(),
                request.getAddressLine2(),
                request.getTown(),
                request.getPostcode(),
                request.getEmergencyContactName(),
                request.getEmergencyContactNumber()
        );
        AppUser appUser = this.loadUserById(id);
        Authentication auth = new UsernamePasswordAuthenticationToken(appUser, null, appUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public void updateUserPassword(Long id, ChangePasswordRequest request){
        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());

        if(this.getCurrentRole() == Role.CUSTOMER || !this.getCurrentEmployeeFromContext().isPasswordDefault())
            appUserRepository.updateUserPassword(id, encodedPassword);
        else
            appUserRepository.updateEmployeeDefaultPassword(id, encodedPassword);
    }

    /**
     * This method is called whe a user is being updated and it validates the necessary information
     *
     * @param request the information being updated
     * @return any errors found or null if non are found
     */
    public String validateUserUpdate(UpdateRequest request){
        if(this.getCurrentRole() == Role.CUSTOMER){

            //TODO: Just send errors back let the controller handle redirects!!!!

            //check that the user is not entering a date in the future
            if (!validator.dob(parse(request.getDob())))
                return "redirect:/customer/edit_profile/" + request.getId() + "?error=dob";
            //check the phone number is long enough and doesn't contain any letters
            if (!validator.phoneNumber(request.getPhoneNumber()))
                return "redirect:/customer/edit_profile/"+request.getId()+"?error=phoneNumber";
            //check the emergency contact phone number is long enough and doesn't contain any letters
            if (!validator.phoneNumber(request.getEmergencyContactNumber()))
                return "redirect:/customer/edit_profile/"+request.getId()+"?error=emergencyContactNumber";
        }
//        else{
//            //check that the user is not entering a date in the future
//            if (!validator.dob(parse(request.getDob())))
//                return "redirect:/employee/editEmployee/" + request.getId() + "?error=dob";
//            //check the phone number is long enough and doesn't contain any letters
//            if (!validator.phoneNumber(request.getPhoneNumber()))
//                return "redirect:/employee/editEmployee/\"+request.getId()+\"?error=phoneNumber";
//            //check the emergency contact phone number is long enough and doesn't contain any letters
//            if (!validator.phoneNumber(request.getEmergencyContactNumber()))
//                return "redirect:/customer/editEmployee/\"+request.getId()+\"?error=emergencyContactNumber";
//        }
        return null;
    }

    /**
     * This method is called to validate passwords for a password change
     *
     * @param request The request with the password information
     * @return Any errors found during validation
     */
    public String validatePasswordChange(ChangePasswordRequest request){

        if(!validator.passwordMatch(request.getPassword(), request.getConfirmPassword()))
            return "error=passwordMatch";
        if(!validator.passwordCorrectLength(request.getPassword()))
            return "error=passwordLength";
        if(!validator.passwordHasNoSpace(request.getPassword()))
            return "error=passwordSpace";
        if(!validator.passwordHasCaps(request.getPassword()))
            return "error=passwordCaps";
        if(!validator.passwordHasDigit(request.getPassword()))
            return "error=passwordDigit";
        if(!validator.passwordHasSpecial(request.getPassword()))
            return "error=passwordSpecial";
        if(!validator.passwordHasLower(request.getPassword()))
            return "error=passwordLower";
        return null;
    }

    /**
     * This method extracts the role from the SecurityContextHolder
     *
     * @return The current users Role
     */
    public Role getCurrentRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getPrincipal() != null) {
            try{
                AppUser current = (AppUser) auth.getPrincipal();
                return current.getRole();
            }catch(Exception e){
                return null;
            }
        }
        return null;
    }

    /**
     * This method is used to get the current user if they are a customer from the security context
     *
     * @return The details of the logged in customer
     */
    public Customer getCurrentCustomerFromContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal().getClass().equals(Customer.class))
            return (Customer) auth.getPrincipal();

        return null;
    }

    /**
     * This method is used to get the current employee if they are a customer from the security context
     *
     * @return The details of the logged in Employee
     */
    public Employee getCurrentEmployeeFromContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal().getClass().equals(Employee.class)) {
            return (Employee) auth.getPrincipal();
        }

        return null;
    }
}
