package jrp.gradedunit_birdstonkennel_v1.database;

import jrp.gradedunit_birdstonkennel_v1.models.booking.Booking;
import jrp.gradedunit_birdstonkennel_v1.models.booking.BookingRepository;
import jrp.gradedunit_birdstonkennel_v1.models.booking.enums.Status;
import jrp.gradedunit_birdstonkennel_v1.models.dog.Dog;
import jrp.gradedunit_birdstonkennel_v1.models.dog.DogRepository;
import jrp.gradedunit_birdstonkennel_v1.models.kennel.Kennel;
import jrp.gradedunit_birdstonkennel_v1.models.kennel.KennelRepository;
import jrp.gradedunit_birdstonkennel_v1.models.payment.Payment;
import jrp.gradedunit_birdstonkennel_v1.models.payment.PaymentRepository;
import jrp.gradedunit_birdstonkennel_v1.models.vet.Vet;
import jrp.gradedunit_birdstonkennel_v1.models.dog.enums.DogGender;
import jrp.gradedunit_birdstonkennel_v1.models.dog.enums.Size;
import jrp.gradedunit_birdstonkennel_v1.models.user.AppUserRepository;

import jrp.gradedunit_birdstonkennel_v1.models.user.Customer;
import jrp.gradedunit_birdstonkennel_v1.models.user.Employee;
import jrp.gradedunit_birdstonkennel_v1.models.user.Role;
import jrp.gradedunit_birdstonkennel_v1.models.vet.VetRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to seed the users table with instances of user
 */
@Component
@AllArgsConstructor
public class UserDataLoader implements CommandLineRunner {


    /**
     * Stores an instance of the AppUSerRepository object
     */
    private final AppUserRepository appUserRepository;
    /**
     * Sores an instance of the dog repository
     */
    private final DogRepository dogRepository;
    /**
     * Sores an instance of the vet repository
     */
    private final VetRepository vetRepository;
    /**
     * Stores an instance of the kennelRepository
     */
    private final KennelRepository kennelRepository;
    /**
     * Stores an instance of the payment repository
     */
    private final PaymentRepository paymentRepository;
    /**
     * Stores an instance of the booking repository
     */
    private final BookingRepository bookingRepository;
    /**
     * Stores an instance of the password encoder
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * Runs the database seeder
     */
    @Override
    public void run(String... args) throws Exception {
//        loadRoles();
        loadUserData();
    }


    //IF I WANT A ROLE TABLE THIS SEEDS IT

//    /**
//     * Tries to create all the roles
//     */
//    public void loadRoles(){
//        createRoleIfNotFound("ADMIN");
//        createRoleIfNotFound("CUSTOMER");
//        createRoleIfNotFound("DESK_CLERK");
//        createRoleIfNotFound("PORTER");
//        createRoleIfNotFound("GROOMER");
//        createRoleIfNotFound("MANAGER");
//    }
//
//    /**
//     * Creates a new role if the role being created is not found
//     *
//     * @param name the role attempting to be created
//     */
//    public void createRoleIfNotFound(String name) {
//
//        Role role = roleRepository.findByName(name);
//
//        if (role == null) {
//            role = new Role(name);
//            roleRepository.save(role);
//        }
//    }


    /**
     * This method loads the users into the database
     */
    public void loadUserData() {
        if (appUserRepository.count() == 0) {

            Customer user1 = new Customer(
                    "John",
                    "Smith",
                    LocalDate.of(1995, 4, 5),
                    "customer@gmail.com",
                    "customer",
                    "01234567890",
                    "Happy Street",
                    "Croy",
                    "Glasgow",
                    "G23 F5W",
                    "Jane Smith",
                    "09876543210",
                    Role.CUSTOMER
            );
            user1.setPassword(bCryptPasswordEncoder.encode(user1.getPassword()));
            user1.setEmailConfirmed(true);
            appUserRepository.save(user1);

            Employee user2 = new Employee(
                    "Alice",
                    "Jones",
                    LocalDate.of(1988, 7, 12),
                    "desk_clerk@gmail.com",
                    "desk_clerk",
                    "01234567891",
                    "Oak Road",
                    "Stevenston",
                    "Bristol",
                    "BS4 5LJ",
                    "Bob Smith",
                    "07654321098",
                    Role.DESK_CLERK
            );
            user2.setPassword(bCryptPasswordEncoder.encode(user2.getPassword()));
            user2.setEmailConfirmed(true);
            user2.setPasswordDefault(false);
            appUserRepository.save(user2);

            Employee user3 = new Employee(
                    "Mark",
                    "Davis",
                    LocalDate.of(1991, 10, 25),
                    "porter@gmail.com",
                    "porter",
                    "01234567892",
                    "Main Street",
                    "",
                    "Dublin",
                    "D04 H6F6",
                    "Mary Johnson",
                    "08976543210",
                    Role.PORTER
            );
            user3.setPassword(bCryptPasswordEncoder.encode(user3.getPassword()));
            user3.setEmailConfirmed(true);
            user3.setPasswordDefault(false);
            appUserRepository.save(user3);

            Employee user4 = new Employee(
                    "Emily",
                    "Taylor",
                    LocalDate.of(1992, 8, 17),
                    "groomer@gmail.com",
                    "groomer",
                    "01234567893",
                    "Victory Av",
                    "High Street",
                    "Manchester",
                    "M1 1DN",
                    "David Wilson",
                    "07987654321",
                    Role.GROOMER
            );
            user4.setPassword(bCryptPasswordEncoder.encode(user4.getPassword()));
            user4.setEmailConfirmed(true);
            user4.setPasswordDefault(false);
            appUserRepository.save(user4);

            Employee user5 = new Employee(
                    "Adam",
                    "Lee",
                    LocalDate.of(1985, 2, 28),
                    "manager@gmail.com",
                    "manager",
                    "01234567894",
                    "Park Avenue",
                    "",
                    "New York",
                    "10022",
                    "Sarah Brown",
                    "00123456789",
                    Role.MANAGER
            );
            user5.setPassword(bCryptPasswordEncoder.encode(user5.getPassword()));
            user5.setEmailConfirmed(true);
            user5.setPasswordDefault(false);
            appUserRepository.save(user5);

            Employee user6 = new Employee(
                    "Samantha",
                    "Wilson",
                    LocalDate.of(1996, 11, 1),
                    "admin@gmail.com",
                    "admin",
                    "01234567895",
                    "Queen Street",
                    "",
                    "London",
                    "EC4R 1BR",
                    "James Smith",
                    "07896543210",
                    Role.ADMIN
            );
            user6.setPassword(bCryptPasswordEncoder.encode(user6.getPassword()));
            user6.setEmailConfirmed(true);
            user6.setPasswordDefault(false);
            appUserRepository.save(user6);

            Customer user7 = new Customer(
                    "Peter",
                    "Chen",
                    LocalDate.of(1983, 9, 9),
                    "customer2@gmail.com",
                    "customer",
                    "01234567896",
                    "King's Road",
                    "",
                    "Hong Kong",
                    "999077",
                    "Nancy Wang",
                    "85212345678",
                    Role.CUSTOMER
            );
            user7.setPassword(bCryptPasswordEncoder.encode(user7.getPassword()));
            user7.setEmailConfirmed(true);
            appUserRepository.save(user7);

            Employee user8 = new Employee(
                    "Lucy",
                    "Nguyen",
                    LocalDate.of(1990, 6, 15),
                    "desk_clerk2@gmail.com",
                    "desk_clerk",
                    "01234567897",
                    "Pham Van Dong Street",
                    "",
                    "Ho Chi Minh City",
                    "70000",
                    "Tommy Tran",
                    "84987654321",
                    Role.DESK_CLERK
            );
            user8.setPassword(bCryptPasswordEncoder.encode(user8.getPassword()));
            user8.setEmailConfirmed(true);
            user8.setPasswordDefault(false);
            appUserRepository.save(user8);

            Vet vet = new Vet(
                    "tony",
                    "Tonys Tiger care",
                    "01234567890",
                    "Happy Street",
                    "Downtowy",
                    "New York",
                    "NY1 1s2"
            );
            vetRepository.save(vet);


            Dog dog1 = new Dog(
                    "Sparky",
                    "collie",
                    "black",
                    Size.MEDIUM,
                    DogGender.MALE,
                    "6",
                    true,
                    true,
                    "16.5kg",
                    "Sky Pet",
                    "01234567890",
                    "none",
                    "none",
                    user1,
                    vet
            );
            dogRepository.save(dog1);

            Kennel kennel1 = new Kennel();
            kennel1.setCapacity(2);
            kennel1.setKeeper(user3);
            kennel1.setCostPerDay(25);
            kennelRepository.save(kennel1);

            Kennel kennel2 = new Kennel();
            kennel2.setCapacity(2);
            kennel2.setKeeper(user3);
            kennel2.setCostPerDay(25);
            kennelRepository.save(kennel2);

            Kennel kennel3 = new Kennel();
            kennel3.setCapacity(2);
            kennel3.setKeeper(user3);
            kennel3.setCostPerDay(25);
            kennelRepository.save(kennel3);

            Kennel kennel4 = new Kennel();
            kennel4.setCapacity(2);
            kennel4.setKeeper(user3);
            kennel4.setCostPerDay(25);
            kennelRepository.save(kennel4);

            Kennel kennel5 = new Kennel();
            kennel5.setCapacity(2);
            kennel5.setKeeper(user3);
            kennel5.setCostPerDay(25);
            kennelRepository.save(kennel5);

//            Kennel kennel6 = new Kennel();
//            kennel6.setCapacity(2);
//            kennel6.setKeeper(user3);
//            kennel6.setCostPerDay(25);
//            kennelRepository.save(kennel6);
//
//            Kennel kennel7 = new Kennel();
//            kennel7.setCapacity(2);
//            kennel7.setKeeper(user3);
//            kennel7.setCostPerDay(25);
//            kennelRepository.save(kennel7);
//
//            Kennel kennel8 = new Kennel();
//            kennel8.setCapacity(2);
//            kennel8.setKeeper(user3);
//            kennel8.setCostPerDay(25);
//            kennelRepository.save(kennel8);
//
//            Kennel kennel9 = new Kennel();
//            kennel9.setCapacity(2);
//            kennel9.setKeeper(user3);
//            kennel9.setCostPerDay(25);
//            kennelRepository.save(kennel9);
//
//            Kennel kennel10 = new Kennel();
//            kennel10.setCapacity(2);
//            kennel10.setKeeper(user3);
//            kennel10.setCostPerDay(25);
//            kennelRepository.save(kennel10);
//
//            Kennel kennel11 = new Kennel();
//            kennel11.setCapacity(1);
//            kennel11.setKeeper(user3);
//            kennel11.setCostPerDay(25);
//            kennelRepository.save(kennel11);
//
//            Kennel kennel12 = new Kennel();
//            kennel12.setCapacity(1);
//            kennel12.setKeeper(user3);
//            kennel12.setCostPerDay(25);
//            kennelRepository.save(kennel12);
//
//            Kennel kennel13 = new Kennel();
//            kennel13.setCapacity(1);
//            kennel13.setKeeper(user3);
//            kennel13.setCostPerDay(25);
//            kennelRepository.save(kennel13);
//
//            Kennel kennel14 = new Kennel();
//            kennel14.setCapacity(1);
//            kennel14.setKeeper(user3);
//            kennel14.setCostPerDay(25);
//            kennelRepository.save(kennel14);
//
//            Kennel kennel15 = new Kennel();
//            kennel15.setCapacity(1);
//            kennel15.setKeeper(user3);
//            kennel15.setCostPerDay(25);
//            kennelRepository.save(kennel15);
//
//            Kennel kennel16 = new Kennel();
//            kennel16.setCapacity(1);
//            kennel16.setKeeper(user3);
//            kennel16.setCostPerDay(25);
//            kennelRepository.save(kennel16);
//
//            Kennel kennel17 = new Kennel();
//            kennel17.setCapacity(1);
//            kennel17.setKeeper(user3);
//            kennel17.setCostPerDay(25);
//            kennelRepository.save(kennel17);
//
//            Kennel kennel18 = new Kennel();
//            kennel18.setCapacity(1);
//            kennel18.setKeeper(user3);
//            kennel18.setCostPerDay(25);
//            kennelRepository.save(kennel18);
//
//            Kennel kennel19 = new Kennel();
//            kennel19.setCapacity(1);
//            kennel19.setKeeper(user3);
//            kennel19.setCostPerDay(25);
//            kennelRepository.save(kennel19);
//
//            Kennel kennel20 = new Kennel();
//            kennel20.setCapacity(1);
//            kennel20.setKeeper(user3);
//            kennel20.setCostPerDay(25);
//            kennelRepository.save(kennel20);


            Payment pay = new Payment(
                    "test_1231jh312jdsa8sa",
                    120.50,
                    120.50,
                    "test@gmail.com"
            );
            paymentRepository.save(pay);

            Booking book1 = new Booking(
                    LocalDate.of(2023, 5, 27),
                    LocalDate.of(2023, 6, 2),
                    false,
                    user1,
                    pay
            );
            book1.setStatus(Status.COMPLETE);
            book1.setTotal(100);
            book1.setTimeOfBooking(LocalDateTime.now().minusMonths(3));
            book1.setKennels(new ArrayList<>());
            book1.getKennels().add(kennel1);
            book1.setDogs(new ArrayList<>());
            book1.getDogs().add(dog1);
            book1.getDogs().add(dog1);
            bookingRepository.save(book1);


            Booking book2 = new Booking(
                    LocalDate.of(2023, 6, 6),
                    LocalDate.of(2023, 6, 8),
                    false,
                    user7,
                    pay
            );
            book2.setStatus(Status.COMPLETE);
            book2.setTotal(75);
            book2.setTimeOfBooking(LocalDateTime.now());
            book2.setKennels(new ArrayList<>());
            book2.getKennels().add(kennel1);
            book2.getKennels().add(kennel3);
            book2.setDogs(new ArrayList<>());
            book2.getDogs().add(dog1);
            book2.getDogs().add(dog1);
            book2.getDogs().add(dog1);
            book2.getDogs().add(dog1);
            bookingRepository.save(book2);

            Booking book3 = new Booking(
                    LocalDate.of(2023, 6, 1),
                    LocalDate.of(2023, 6, 8),
                    false,
                    user1,
                    pay
            );
            book3.setStatus(Status.COMPLETE);
            book3.setTotal(125);
            book3.setTimeOfBooking(LocalDateTime.now());
            book3.setKennels(new ArrayList<>());
            book3.getKennels().add(kennel2);
            book3.setDogs(new ArrayList<>());
            book3.getDogs().add(dog1);
            book3.getDogs().add(dog1);
            bookingRepository.save(book3);

            Booking book4 = new Booking(
                    LocalDate.of(2023, 5, 27),
                    LocalDate.of(2023, 6, 8),
                    false,
                    user1,
                    pay
            );
            book4.setStatus(Status.COMPLETE);
            book4.setTotal(50);
            book4.setTimeOfBooking(LocalDateTime.now().minusMonths(1));
            book4.setKennels(new ArrayList<>());
            book4.getKennels().add(kennel4);
            book4.getKennels().add(kennel5);
            book4.setDogs(new ArrayList<>());
            book4.getDogs().add(dog1);
            book4.getDogs().add(dog1);
            bookingRepository.save(book4);

            Booking book5 = new Booking(
                    LocalDate.now().minusMonths(1).minusDays(1),
                    LocalDate.now().minusMonths(1).plusDays(4),
                    false,
                    user1,
                    pay
            );
            book5.setStatus(Status.COMPLETE);
            book5.setTotal(210);
            book5.setTimeOfBooking(LocalDateTime.now().minusMonths(1));
            book5.setKennels(new ArrayList<>());
            book5.getKennels().add(kennel4);
            book5.getKennels().add(kennel5);
            book5.setDogs(new ArrayList<>());
            book5.getDogs().add(dog1);
            book5.getDogs().add(dog1);
            bookingRepository.save(book5);

            Booking book6 = new Booking(
                    LocalDate.now(),
                    LocalDate.of(2023, 6, 8),
                    false,
                    user1,
                    pay
            );
            book6.setStatus(Status.COMPLETE);
            book6.setTotal(175);
            book6.setTimeOfBooking(LocalDateTime.now().minusMonths(2));
            book6.setKennels(new ArrayList<>());
            book6.getKennels().add(kennel4);
            book6.getKennels().add(kennel5);
            book6.setDogs(new ArrayList<>());
            book6.getDogs().add(dog1);
            book6.getDogs().add(dog1);
            bookingRepository.save(book6);

            Booking book7 = new Booking(
                    LocalDate.now().minusDays(4),
                    LocalDate.now().plusDays(3),
                    true,
                    user7,
                    pay
            );
            book7.setStatus(Status.COMPLETE);
            book7.setTotal(185);
            book7.setTimeOfBooking(LocalDateTime.now().minusMonths(1));
            book7.setKennels(new ArrayList<>());
            book7.getKennels().add(kennel5);
            book7.setDogs(new ArrayList<>());
            book7.getDogs().add(dog1);
            bookingRepository.save(book7);


            Booking book8 = new Booking(
                    LocalDate.now().minusMonths(4),
                    LocalDate.now().minusMonths(4).plusDays(7),
                    true,
                    user7,
                    pay
            );
            book8.setStatus(Status.COMPLETE);
            book8.setTotal(185);
            book8.setTimeOfBooking(LocalDateTime.now().minusMonths(1));
            book8.setKennels(new ArrayList<>());
            book8.getKennels().add(kennel5);
            book8.setDogs(new ArrayList<>());
            book8.getDogs().add(dog1);
            bookingRepository.save(book8);


        }
    }

    public static List<Booking> generate100Bookings(Customer cust, ArrayList<Dog> dogs, ArrayList<Kennel> kennels, Payment pay) {
        List<Booking> bookings = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            Booking booking = new Booking();
            booking.setCustomer(cust);
            booking.setTimeOfBooking(LocalDateTime.now());
            booking.setArrival(generateRandomDate());
            LocalDate endDate;
            do {
                endDate = generateRandomDate();
            } while (endDate.isBefore(booking.getArrival()));
            booking.setDeparture(endDate);
            booking.setDogs(dogs);
            booking.setKennels(kennels);
            booking.setPayment(pay);
            bookings.add(booking);
        }

        return bookings;
    }


    private static final List<String> DOG_NAMES = Arrays.asList("Buddy", "Charlie", "Lucy", "Max", "Daisy", "Molly",
            "Bailey", "Sadie", "Lola", "Rocky", "Maggie", "Cooper", "Stella", "Bear", "Duke", "Toby", "Jack", "Abby",
            "Gus", "Harley");
    private static final Random RANDOM = new Random();


    private static String generateRandomName() {
        String[] firstNames = {"James", "John", "Robert", "Michael", "William", "David", "Richard", "Joseph", "Thomas",
                "Charles", "Christopher", "Daniel", "Matthew", "Anthony", "Mark", "Donald", "Steven", "Paul", "Andrew",
                "George"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore",
                "Taylor", "Anderson", "Jackson", "Parker", "Lee", "Clark", "Young", "Hall", "Wright", "Allen", "King"};
        String firstName = firstNames[RANDOM.nextInt(firstNames.length)];
        String lastName = lastNames[RANDOM.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }

    private static LocalDate generateRandomDate() {
        int year = 2023;
        int month = RANDOM.nextInt(12) + 1;
        int day = RANDOM.nextInt(28) + 1;
        return LocalDate.of(year, month, day);
    }

    private static List<String> generateRandomDogs() {
        int numDogs = RANDOM.nextInt(3) + 1;
        List<String> dogs = new ArrayList<>();
        for (int i = 0; i < numDogs; i++) {
            String dogName = DOG_NAMES.get(RANDOM.nextInt(DOG_NAMES.size()));
            dogs.add(dogName);
        }
        return dogs;
    }
}










