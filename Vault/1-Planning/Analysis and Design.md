#Plan
### Classes

- Identity user
	- \<Copy Identity User\>
	- 
 - User
	- firstName : string
	- lastName : string
	- addressLine1 : string
	- addressLine2 : string
	- town : string
	- postcode : string
	- currentRole : string
- Staff
	- age : int
	- toString() : string
- Customer
	- emergencyContactName : string
	- emergencyContactNumber : string
	- cards : List\<Card\>
	- bookings : List\<booking\>
	- dogs : List\<Dog\>
	- toString() : string
- Dog
	- dogId : int
	- breed : string
	- colour : string
	- size : Size
	- sex : char
	- age : int
	- isSpayedNutered : boolean
	- isVaccinated : boolean
	- weight : double
	- insurenceProvider : string
	- microchipNumber : string
	- medicalNotes : string
	- behaviourNotes : string
	- vetId : int
	- vet : Vet
	- ownerId : int
	- owner : Owner
	- toString() : string
- Kennel
	- kennelId : int
	- size : string
	- capacity : string
	- keeperId : int
	- keeper : Staff
- Vet
	- vetId : int
	- name : string
	- practice : string
	- addressline1 : string
	- addressline2 : string
	- town : string
	- postcode : string
	- contactNumber : string
	- dogs : List\<Dogs\>
- Payment
	- paymentId : int
	- bookingId : int
	- booking : Booking
	- cardId : int
	- card : Card
	- price : double
	- pricePaid : double
- Card
	- cardId : int
	- cardType : string
	- cardNumber : string
	- nameOnCard : string
	- securityNumber : string
	- expiresOn : Date
	- customerId : int
	- customer : Customer
- Booking
	- bookingId : int
	- customerId : int
	- custromer : Customer
	- paymentId : int
	- payment : Payment
	- staffId : int
	- staff : Staff
	- dropOffDate : Date
	- pickUpDate : Date
	- groomingIncluded : Boolean
	- dogs : List\<Dog\>
	- kennels : List\<Kennel\>


## Normalization
#### info
Phone Number
Dogs Name 
Breed
Neutered/Spayed?
Gender
Ver Name
Vet Address 
Vet Phonenumber
Proof of Vaccination/Kennel Cough?
Proof of Flea/Worming Tratments
Operastions in the last siz months?
Allergies
Current Medications 
Dog Age
Microchip Details
Passport Number
Home Email
Collar & Tag Supplies?
Permission to let dog of leash?
Antisocial Behaviours?
Emergency Contact's Name
Relationship
Address 
PhoneNumber
Date and time of arrival
Date and time of departure
Number of Nights

Signature of the Owner
Date

## Views
Plan for designing my views is to begin by designing a basic layout which i plan to get from a bootstrap template idea. From this template i will add each view. At this stage they dont have to be perfect and they dont all have to be compleat just enough for an acceptable handin

# Templates Used
Bootstrap Login Form with Floating Labels
Scrolling Nav
Bootstrap Registration Page with Floating Labels
https://startbootstrap.com/theme/sb-admin-2


# Event Handlers
### Creating A Booking

Form : booking/create_booking.html

Drop-off Date  ->  onChange  ->  Check that the entered date is not before the current 

Pick-Up Date -> onChange -> check that the entered date is not before the Drop-off date

Add Dog -> onCick -> navigates user to the add dog page

Confirm Booking -> onClick -> Sends the entered booking data to the createBooking                        method in the booking controller which will validaidate the booking and send the user to the confirm booking view if successfull

### Register Employee

form : admin/create_employee.html

Create Employee -> onClick -> sends the entered details for the user to the dateabase to be validated if successfull the user will be asked to confirm the details in the confirm_new_employee view












