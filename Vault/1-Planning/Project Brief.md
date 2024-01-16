#Plan

# Background
Birdston Kennels is a dog boarding business which offer 20 kennels allowing which allow for a calm, quiet, and relaxing stay. Our purpose build kennels are all extra-large and comprise of an indoor heated area with attached covered outside area (ensuring your dog remains dry throughout the day) Our staff dog ratio is one member of staff to every 6 or 7 kennels enabling us to operate a personalised service tailored to meet our guests individual needs. Our luxury dog boarding kennels are situated in 122 acres of beautiful, wooded countryside between Kirkintilloch and Milton Of Campsie, just seven miles from Glasgow City Centre.

At Birdston Kennels we offer a large range of activities within our grounds to keep our dogs stimulated, healthy and happy. Lead walking and free running in our large grass paddock or playtime with in one of our designated outdoor play areas.

We have highly experienced staff offering a complete range of grooming services for all breeds of dog. Open to boarders and non-boarders. The grooming parlour has many of its own customers who are not boarding, and pet welfare is our priority.

Our dog grooming salon is situated within Birdston Luxury Kennels offering a complete range of grooming services. Our salon is a friendly and calm environment where the dog and owner are made to feel welcome by the staff. There will be a consultation given before the work is begun where the groomer will discuss with the owner and decide which style is best for the dog.

# Requirements

The system is required to provide the following functionality.

- Allow stays to be booked.
- Allow day boarding.
- *Allow grooming to be booked.*
- Allow bookings to be cancelled.
- Allow bookings to be checked.
- Bookings can have multiple dogs with appropriate pricing
- Provide reminders of upcoming bookings.
- Store dog details.
- Allow price changes.
- Allow deposits to be paid.
- Display the availability of each date.
- Returning customers should be able to create an account.

The system is required to hold the following information

- Owner(Owner ID, First Name, Last Name, Contact Number, Address, Postcode, Emergency Contact Name, Emergency Contact Number)

- Dog(Dog ID, Breed, Colour, Size, Sex, Age, Is Spayed/Neutered, Vaccinated, Weight, Insurance provider, Microchip Number, Vet ID, Medical Notes, Behavioural Notes)

- Vet(Vet ID, Name, Practice, Address, Postcode, Contact Number)

- Booking(Booking ID, Dropoff Date, Pick-up Date, price paid, price total, Number of Dogs, Number of Kennels)

- Booking_Dog(Dog ID, Booking ID, Kennel ID)

- Kennel(Kennel ID, Size, Capacity, Keeper)

The system should have a user login and password system that will offer differing levels of access.