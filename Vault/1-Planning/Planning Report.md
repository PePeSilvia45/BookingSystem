#Plan #Planning-Report

***[[Project Brief]]***
[Planning Report Structure](C:\Users\PARKj\OneDrive\college\HND\Block_Three\Graded_Unit\Documentation\Planning\PlanningReportStructure.pdf)
[Planning Report Doc](C:\Users\PARKj\OneDrive\college\HND\Block_Three\Graded_Unit\Documentation\Planning\Deliverables\PlanningReport_JamesPark.docx)


***Get the #functional and #non-functional requirements from the results of the questions submitted to the client then work on planning the report***

# Introduction

## Aims of the project
- This project should create a website that involves a booking system which allows customers of a kennels to create a booking for their dog to stay at the kennels for a set amount of time. 
- Customers who make bookings should be able to make a booking without an account and with an account. 
- Customers with accounts should be able to save details of their dogs, payment details, and their own information. 
- Deposits for any bookings should be payed through the paypal gateway. employees should have the ability to manage their calander and change days which are not available for booking manually. 
- Calander dates where the kennels is at full capacity should also be unavailable. 

## Overview
- Problem to solve is that the business need a website with a booking system 
- goals are to achive solve the problem within the time frame and budget and 
- the key deliverables are the plan and all documentation related *design, system diagrams, report, gantt chart*
- A gantt chart will track the proposed schedule
- The budget will come from the questionair, after knowing this and more information i will be able to work out the cost.
- Potential limitations and setbacks of the project

## High Level Requirements
- Process bookings for stays of one or multiple pets to be housed in the facility.

- Process bookings for daycare of one or multiple pets.

- Allow cancellations of bookings.

- View active bookings.

- View previous bookings.

- Allow returning customers to create accounts.

- Secure logins

- Allow registered users to store information about their pets on their account.

- Allow deposits to be paid at the time of booking.

- Allow card details to be attached to the registered users account.

- Allow staff to have a login portal to change any details relating to pricing and availability

- Reminders should be able to be sent to customers with upcoming bookings.

- Staff should be able to view data relating to members, staff, and bookings from the database.


# Investigation

## Fact Finding Technique
- Reading  
	- I used the buisnesses current social media to get an idea of the facilities that they offer and how. I used this information to work out the best solution to get this information across in my design.
- Questionnaires  
	- I created a questionair that was delivered to the client to get answers which will help to highlight all functional and non functional requirements required to meet the aims of the project
- Interviews  
	- I can carry out interviews with the client to gather informaton and confirmation about functions that are wanted for the system that relate more to the final functonality so that i can gather the important elements to focus on first.
- Observation
	- 

## Interview Questions and Responses
1. What is the Budget of the project?
2. What is the Timescale of the project?
	- Please find below an extract of our contract for your consideration.
		1. The Developer shall complete the development of the Software according to the milestones described on the form below:
		2. In accordance with such milestones, the final product shall be delivered to the Client by 9/06/2023 (the “**Delivery Date**”).
		3. For a period of 6 months after delivery of the final product, the Developer shall provide the Client attention to answer any questions or assist solving any problems with regard to the operation of the Software up to 40 hours free of charge and billed to the Client at a rate of £100 per hour for any assistance thereafter. The Developer agrees to respond to any reasonable request for assistance made by the Client regarding the Software within 10 days of the request.
		4. The Developer shall provide to the Client after the Delivery Date, a cumulative 8 hours of training with respect to the operation of the Software if requested by the Client.
		5. If the Software as delivered does not conform to the Specifications, the Client shall within 6 months of the Delivery Date notify the Developer in writing of the ways in which it does not conform with the Specifications. The Developer agrees that upon receiving such notice, it shall make reasonable efforts to correct any non-conformity.
		6. In consideration for the Service, the Client shall pay the Company at the rate of £100 per hour (the “Hourly Rate”), with a maximum total fee for all work under this Software Development Agreement of £10,000. Fees billed under the Hourly Rate shall be due and payable upon the Developer providing the Client with an invoice. Invoices will be provided for work completed by the developer once every 4 weeks.
		7. The Parties acknowledge and agree that the Client will hold all intellectual property rights in the Software including, but not limited to, copyright and trademark rights. The Developer agrees not to claim any such ownership in the Software’s intellectual property at any time prior to or after the completion and delivery of the Software to the Client. The Software will not violate the intellectual property rights of any other party.

3. Is there any current branding to be incorporated into the design?

4. If not, are you open to the creation of a logo and colour scheme?
	- We require that you follow a responsive design approach to developing interfaces suitable for a range of touchscreen phones, tablets, and traditional pointer-driven desktop computers. We hope that the new design will reflect our company values and mission.

5. Are there any special accessibility requirements?
- We rely on your experience and expertise to enhance the user experience for all of your visitors  including those with disabilities or limitations who land on your site. This includes those with disabilities and limitations such as: Blindness, Low vision, Learning disabilities, Cognitive disabilities, Deafness, Hearing loss, Speech disabilities

6. what if any are your social media accounts you wish to be linked to your page?
	- Instagram, Twitter, Facebook

7. can users’ book without an account?
	- No

8. Do you want third party account verification like logging in with a Facebook account?
	- Yes.

9. Do you want two factor authentication to be implemented?
	- Yes.

10.   are there distinct levels of employee access i.e., basic employee, admin?
	- We require you as the IT expert to develop an IT system that is secure.
		- Admin- will have the responsibility to register new members of staff and provide them with a username (consists of first name, second name and company name for the domain name) and password.
		- Front desk clerk – access Booking, Customer Records and Payment processing
		- Manager – access to all data, pricing.
		- Dog Porters – access the kennel keeping list.
		- Stock Manager – access to Dog supplies Records.
		- Dog Groomers– access to daily customers list for breakfast and dinner.
		- All users require a User Id and Password.
		- Staff should generate the following reports (daily, weekly, yearly):
			- Daily List of Bookings Arriving/Leaving
			- Weekly list of bookings showing arrival and departure times
			- List of dogs with special requirements, e.g. medication, dietary needs
			- Monthly report of revenues received
			- the reports should be able to be exported in different formats (Word, excel, PDF, etc.)

11.   Do you require the implementation of both grooming appointments and stays in the first iteration?
	- No. Booking kennels/stays is a priority. Grooming can be offered as an extra service to the customer at the point of booking.

12.   Are there any dates that are not available for booking?
	- We operate 7/24.

13.   Are deposits at the time of booking needed to secure the booking?
	- Payments should be made in full at the point of sale. Acceptable payment methods are –credit/debit cards, PayPal & invoices for corporate customers.
	- Cancelations made more than 3 days before booking are refunded automatically on to the card registered to the customer.

14.   Do you want reminders of the booking sent via email close to the booking date?
	- Yes. Email and SMS confirmation required for confirmation of bookings, amendments, cancelations, payments.

15.   is there a maximum stay period?
	- We don’t have a maximum or minimum stay.

16.   Do you want a contact form implemented?
	- Yes.

17.   if so, what are the contact details for the business?
	- Birdston Kennels, Loch Lomond Drive, Renfrew, PA75 6QB, 
	- info@birdstonkennels.com
	- 0141  302811

## Functional and non-Functional

- ##### Functional 
	- The webside will have links to the buisnesses social medias 
	- Users will requiree an account to create a booking
	- The website will have third party logging in via facebook or gmail
	- Users should have the option for two factor authentication
	- The system must be secure
	- There should be an admin role
	- Admins should be able to register new staff members
	- Employee usernames should follow the format (first name, last name, @ company name)
	- There should be a front desk clerk role with access to the bookings, customer records and payment processing
	- There should be a Manager role with access to all data and pricing
	- THere should be a dog porter role with access to the kennel keeping list
	- There should be a stock manager role with access to the dog supplies records
	- There should be a dog groomers  role with access to the daily list of groomings
	- Staff should be able to generate reports
	- Daily lists of bookings arrivals and departures should be generated
	- Weekly list of bookings showing arrivals and departures should be generated
	- Dog list with any special requirements should be generated
	- Monthly report of revinue should be generated
	- all reports should be exportable in different formats(word, excel, pdf)
	- Customers should be able to book a stay with the kennel
	- Customers should be able to add optional grooming onto their booking
	- Payments should be made in full at the time of booking.
	- Payment methods should be credit/debit card or paypal
	- Cancellations made for bookings more than 3 days before the booking date should be automatically refunded on the customers registered payment method
	- Email and sms confirmation of bookings should be sent
	- In the case of cancelations or amendments there should also be confirmation sent
	- The website should have a contact form implimented
- ##### Non-Functional 
	- The final product should be delivered to the client on or by 09/06/2023
	- The developer should provide support to the client for 6 months after the delivery date with the first 40 hours free of charge and then an hourly rate of £100
	- there should be a period of training after the delivery date of 8 hours for the developer to teach the client on the products use
	- There will be a 6 month period after the delivery date for the client to bring forward anyways in which the product does not conform to the desired specifications
	- The projects will be billed at £100 per hour to a max of £10,000.
	- Invoices for work complete will be provided every 4 weeks
	- The website will have a responsive design with a suitable range of support for touchscreen and desktop
	- The design will reflect the comapanys values and current branding
	- The website will follow appropriate accessibility to enhance user experience for all
	- There is no maximum or minimum stay period
	- Bookings should be possible at all times with exception to holidays such as christmas / newyear (however boarding over these days is available just no pickups or dropoffs)

## Top Level Use Case Model
- [Initial Use Case Model](C:\Users\PARKj\OneDrive\college\HND\Block_Three\Graded_Unit\Documentation\Diagrams\Initial-Use-Case-Diagram.jpg)
## Top Level Class Diagram
- [Initial Class Diagram](C:\Users\PARKj\OneDrive\college\HND\Block_Three\Graded_Unit\Documentation\Diagrams\Initial-class-diagram.jpg)


# Resources and Materials Required

## Development Platforms Comparison

### ASP.Net & C\#
#### ASP.Net
.Net is one of if not the most powerfull framework when it comes to software development. It offers a great range of features and support being used to develop systems in the C#, F# and Visual Basics programming language.
under .NET there are two technologies in the forms of .Net Framework and .Net Core, with .Net core being the more advances technology. 
Although it is known as a revoutionary framework for developers Microsofts .Net is in no means perfect.
##### Pros 
- Object oriented Software Development Model.
- Visual Stuidos IDE is a robust and powerfull application.
- Simple Caching System - allows improved performance and scaleability.
- Language Independence of .NET Core - crossplatform accross Windows, Linux, and macOS.
- Easy to Deploy.
- Multiple App Support - developing posible in many applications ie gaming, mobile, IoT, AI, & ML.
##### Cons
- Limited object Relational Support - may not align with emerging database designs due to flexability issues.
- Vendor Lock-in - being a part of the microsoft family means that any changes the company impose will impact projects done in this framework.
- Licensing Cost - .NET family can come with a cost in the terms of licensing fees
- Stability Issues for New Releases - new releases can lack proper documentation and support and can be prone to drastic changes
- Difficulty in Transitioning to .NET Core - if you where transfering an existing application to .Net Core the process can be long a tedious
- Memory Leaks

#### C\#
c# is a prefered backend language for backend development and is one of the .NET languages. The syntax of C# is populer with developers of C-style languages like Java and C++. Being such a popular language does mean it comes with several pros
##### Pros
- Integrates well with windows.
- Applications in this language are easily deployed on the network - as lon as the workstation or server support .NET .
- Popular languafe among developers means finding someone to work in it is easier.
- close similaritys to languages like Java meaning resources and help can cross over to an extent.
- Visual Studios has tools to allow teams to work togeather efficiantly.
- C# is a compiled language meaning tue code is stored in binary form on the public facing server meaning the source code is not easily visible to any hacking attempts.
##### Cons
- The fact that C# is a compiled language it also has its disadvantages - it must be recompiled even after the smallest of changes.
- Due to it being part of the .Net framework, the server must be running windows.
- Windows servers are more costly.
- Older .NET frameworks become unsuported by Micosoft - which means older systems need to run older versions of Windows OS.

### Django & Python
#### Django
Django is a framework developed for fast web development. It is also a very powerful tool and allows web development along side the Python language. Being supported by a vast community Django provides constant development and new features and functionalities, This also means that the framework is well tested and documeneted.
Django comes with many advantages some major ones are listed below, It also has its disadvantges even though it contains all the functionality one may need. These disadvantages come from a design stand point rather than a performance one. 
##### Pros
- Implemented in Python - making it very easy to read and using the same language between the front end and the back end.
- Better CDN (Content Delivery Networks) Connectivity & Content Management - Django provides libraries and tools so it can be used as a Content Management System (CMS).
- Batteries Included Framework - Made by web developers for web developers .
- Fast Processing - django is designed to make the process of transmitting resources over the internet faster and easier.
- Rapid-Development - Django is the best framework in the industry for rapid development due to the fact that it uses the MTV architecture.
- Scalable -  this framework is used by some of the biggest and buisiest websites due to the fact that it can handle nearly any kind of hardware.
- Secured - Due to it being made by the worlds best web developers it is a trusted framework. and with great built in encription for passwords and information you can be confidant that the data is secure.
##### Cons
- Monolithic - Django has a perticular way that developers must learn and follow in order to create a project using the Django framework.
- Not for Smaller Projects - Djangos functionallity comes with alot of code and for this reasson requires alot of processsing and time on the server end.
- Regular Expression for URLs.

#### Python
Over time the demand for Python development has only grow, which is to be expected with one of the world’s most popular programing language. Even though it is widely used and plays a major role in web development there are cons on top of the obvious pros.
##### Pros
- Less code – Due to its light weight syntax python can be very powerful with less code written compared to languages like C# or Java.
- Wide Community Support – Because of its vast adoption and popularity across the globe there is a large communities which means there is a lot of documentation and help to be found.
- Syntax – the simplicity and easy to read nature of the Python syntax, code is easily managed, written, and read.
- Library Support – Python has a large collection of libraries to help the developer build all kinds of applications from web apps to game apps and even machine learning apps.
- Frameworks – these allow easy construction of web apps and they are fast to impliment
##### Cons
- Speed - Because Python uses an interpiter in place of a compiler there is some issues with the speed of the language compared to compiled languages.
- Memory Consumption - Python consomes a large volume of memory when running heavy web applications.
- Not Great For Mobile Use - Python has been reported to not run optomily when running over mobile divices.
- Design Restrictions - Due to Python being a dynamically typed language there are restrictions on design because of tasks being preformed during runtime which in other languages can be run statically

### Spring & Java
#### Spring
The Spring framework is used for making programming in the java language faster , safer, and easier. It has become one of the worlds most popular frameworks due to its productivity, speed, and simplicity.
##### Pros
- Light weight - there is no need to inherite classes or impliment interfaces due to POJO (Plain Old Java Object) implimentation.
- Flexible - Spring has a veriety of trusted libraries, and alows developers to chose between XML or java based annotation
- Secure - Due to Spring monitoring third party dependancies closely, as well as reqular updates Spring is a secure framework to work with.
- Supportive - Spring has a great community which is responisble for providing support and resources.
##### Cons
- Complexity - Developing in the Spring frame work is a complex proccess, which requires a good amount of experties in it or time to learn.
- No Specific Guidelines - Due to Spring not having cross-site scripting, the developer has to work on securing the application against infiltration and malisous attacks.
- High Learning Curve - Difficult and time consuming to learn.
- Lots of XML - developing in Spring requires alot of XML.
#### Java
Java is one of the top languages in the world which is said to have the capability to "Write Once, Run Anywhere". It is an Object-Oriented Programming language. With such a popularity Java definetly comes with its pros, however it comes with its cons aswell.
##### Pros
- Simple - Java is an easy to learn, easy to understand language making it a language that is easy to debug and maintain.
- OOP - Java is an object-oriented programming language which is definied by its structure and data typese as well as its definied functions.
- High-Level Language - Java is easy to learn be it coming from no programming background or from another language. begin a high level language it resembles human language compared to some other lower level languages that look like machine code.
- APIs for Application Development - - java offers various APIs (Application Programming Interfaces) to aid in things like database connection, networking, utilities, and more.
##### Cons
- Memory Requirements - due to each line of code needing interpiting and being a non native language Java is memory consuming which can make it down.
- Verbose - A downside to being a high-level language Java is that the code is made up of longer more complex sentances making it a little over complex.
- Lack of Native Look and Feel - When used for desktop application Java can be weaker when creating GUI (Graphical User Interface). This means more research would be required when developing applications for windows

## Justification for Development Platform
###### justify choice of ASP.Net framework & C\#
For this project I have decided to go with the combination of ASP.Net Framework and the C# Programming language. Due to my fimilurality with these technologies I can better put my time to learning new functions such as adding payment gateways , third party logins, and two factor aithentication. This framework has everything in place I need to create an application that will tick all the functional boxies that i require it to. C# as a lanuage for the backend development is a perfect choice because in my experiance it has been easy to understand, write and debug with the knowlage I have of it.

# Bibliography
#### *Track all links used for planning here and why they where used*
- https://successive.tech/blog/pros-and-cons-of-net-framework/
- https://www.c-sharpcorner.com/article/difference-between-net-framework-and-net-core/
- https://www.agilites.com/pros-and-cons-of-using-c-as-your-backend-programming-language.html
- https://data-flair.training/blogs/django-advantages-and-disadvantages/#:~:text=Django's%20first%20side%20reveals%20rapid,of%20Django%20advantages%20and%20disadvantages
- https://www.analyticsinsight.net/top-5-pros-and-5-cons-of-using-python-in-web-development-projects/
- https://www.javatpoint.com/java-spring-pros-and-cons
- https://www.bairesdev.com/java/pros-cons/