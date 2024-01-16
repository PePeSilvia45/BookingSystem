#Diary 
##### 31/03/2023 
- Had discussion with the client to clarify how booking reminders should be sent out. They clarified that reminders can be sent out eather by a front desk clerk or automatically when one logs in.
- Had a discussion with the client about managing stock. We have came to the understanding that such a system would be an after thought of the project and that it will be added at the end if there is time, if not then it can be added in another itiration.
- I am going to look further into Spring and Java over Easter to see if i can develop enough understanding to go ahead with it for the project and i wil update my plan to reflect this.
- Need to think over the plan so far an detail any steps i have taken.
- ##### I need to gain further knowlage into saving card details
- startbootsrap.com for getting a bootstrap template
- update initial class diagram

##### 02/04/2023
- Create my use case diagrams for making a bookin & registering a new employee. I then started the work on the class diagram by mapping out my user classes and deciding what attributes are needed. I have also decided for the customer class i will make my own data structure to hold the customers dogs, bookings, and cards this will demonstrait what I learned in my data stuctures class. 
##### 03/04/2023
- I have started today by taking time to look into learning developing with spring and Java to assess the learning curve involved. If the learning goes well I plan to Change my initial plan of programming in .NET Framework.
- To  start learning this framework i followed the hour crash course on youtube [# Build an ENTIRE TODO Web Application with Java Spring Boot 3.0.0 in 62 min](https://www.youtube.com/watch?v=yqWtYKWWcpY&t=2s) This allowed me to build a small todo list application in spring with basic database function. I next need to looking into User and Identity
- For learning how to impliment the backend system i plan to build on the todo list application from the previous step and add security, roles, registration, login, verification, and mail sending for this i have found a 2 hour video on youtube to guide me ion the basics of this process
	- [Spring Boot Tutorial | Full Course [2022] [NEW]](https://www.youtube.com/watch?v=9SGDpanrc8U&list=PLwvrYc43l1MzeA2bBYQhCWr2gvWLs9A7S&index=2)
	- [Java Tutorial - Complete User Login and Registration Backend + Email Verification](https://www.youtube.com/watch?v=QwQuro7ekvc)
##### 04/04/2023
- Today i am starting by contining on with the videos i started at the end of yesterday.
- I progressed my knowlage in basic database crud with java and spring today i need to continue with registration and logins
##### 05/04/2023
- I spent today furthering my knowlede with spring learnin user interaction with database. to acheive this i learned how to use postman to sent post get put delete requests to the database and also started learning how to use docker and maildev to send confirmation emails for account registration
- Today i learned how to install docker and use it to download maildev and set up a container for testing email confirmations
##### 08/04/2023
- Todat i made more work on my analysis and mapped out my class diagram by planning all the essential classes and attributes.
##### 10/04/2023
- I made work on my Class diagram finishing it as well as starting and compleating my two activity diagrams and sequence diagrams covering the scenareo of a customer making a booking and a manager registering a new staff member
- I also finished my normilisation and tomorrow plan to move on to my design stage for my views
##### 12/04/2023
- I made work on my design today using bootstrap templates to create the look and feel i desired I managed to complete (Home, About, Find us, Login, Register, Contact-form, Profile, Edit profile, Make booking)
- Due to technical issues i lost todays progress and have to redo my design using notes on resources used.
##### 13/04/2023
- Today i need to respend today reinstalling windows and downloading all the software I need
##### 17/04/2023
- Todays main goal is to finish work on customer views. The only one left to do the view bookings page. 
- with the customer views finished i can now move onto the employee views starting with ones that share a similarity to ones for the customer views naimly 'viewbookings', 'createcustomer' from my customer views can be adapted to view all bookings and to create employees
##### 18/04/2023 
- starting work on employee facing view design today is my main goal.
- I will begin by creating a viewbookings page for employees to view all bookings this will mostly be identical to the customers view bookings. I am still deciding on how the employee dash will look this could be achived by having a saperate 'layout' view 
##### 19/04/2023
- Finished my design and analysis planning.
##### 20/04/2023
- Toda i plan to start work on laying out my POJO classes in my project leaving out navigational properties for the time being
- With my POJO classes written out i am now goin to start work on enablin a usesr to log in using postgreSQL and Spring.Security
- using postgres I created my database from within the terminal [Image of database creation](C:\Users\PARKj\Documents\Graded_Unit\Graded_Unit\Development\Documentation\DatabaseCreation.png)
- When working on the setting up of a database and my users table i found out that calling my class 'User' and the rolles attribute 'currentRole' would cause errors due to these being reserved names in postgresql so but after changing these names i managed to create my first table in the database [Image of user table creation](C:\Users\PARKj\Documents\Graded_Unit\Graded_Unit\Development\Documentation\AppUserTableCreation.png)
- I am now able to register users to the database using POSTMAN to send post requests. My reason for doing this now is to test table insertion from the backend. Later i will replace the post requerts with the models that come from the views.
##### 21/04-2023
- I have a working database and confirmation email using tokens which expire after 15 mins. I now need to get this function working with the front end using the views i have already created.
 - Had a discustion with the client on pricing and capacity checking and we have agreed on an approach that i will impliment using a Kennel table and having a price per dog per kennel
 - I learned how to seed tables in the database using [this website](https://javahowtos.com/guides/107-spring/376-how-to-seed-the-database-in-spring-boot-project.html)
 - I have now successfully created my contact message table and have got the post request working ready to be intergrated into the front end view
 - I have now successfully created the roles table and have implimented functionality for usaers to be in multiple roles using collections
 - Next i plan to make the dogs table and attaching a customer owner.
 - ###### **I need to start thinking about employee and customer sepiration in the database**

##### 22/04-2023 
- FIX CONTACT MESSEGE SERVICE AND REPOSITORY
- I now have all my tables in my database and relationships showing correct in postgresql terminal. I now need to start testing the tables with post and get requests.
- For making new email templates to be sent i have started using [Stripo](https://my.stripo.email/cabinet/#/login?locale=en&fpr=) To try make html strings to be used as emails.

##### 23/04/2023 
- Today i started work on implimenting views to my project 
- I started by branching my repository so i had a point in my project where i could rever to if i have any major failures.
- I then added the home page for the unregistered user. On this view i managed top get the contact form working and sending to the contact email for the buisness
- I then moved on to the sign in process. here i encountered my first major obstical. I could not get the login working.
	- To figure out this problem I have started researching authentication in Spring and using JWT token validation to set up my quthorization.
##### 24/04/2023
- Thoday i had to scrap my work on the view integration and start again with fresh eyes. Doing so i have now integrated registration and token verification for users.
- I am next going to impliment the authentication Security tutorial i followed into my main project To handle logins.
##### 26/04/2023
- After some dificulty i managed to figure out how to enable loging in and loging out i now need to work on implimenting validation checks for the registration form
- Login and log out function working as well as registration and the registration request validation

##### 27/04/2023
- I now have the Customer user being able to log in and view their account information.
- They can also edit the information and database updates accordingly and shown a success message
- I have added Validation to the Update form and the contact us form and a success and fail message as required

##### 28/04/2023
- Need to impliment a message telling the user to confirm their email
- Validate all errors and submit all to the user at once user String[]
- #important Add a back button to edit and create forms
- get add dog working with post before form
- make Dog and vet Service and Repository and seed database
- create a list of the enums to be used for dropdowns
##### 29/04/2023
- i need to check my validation for adding dogs then that form is finished.
- I  will then move onto starting my booking function.
##### 30/04/2023
- I have the creation of bookings well underway i just need to integrate the payment gateway before saving the information to the database
- Had a problem with the api key not working
##### 01/05/2023
- Solved my problem with the api key i had muddled up my public key with my webhook endpoiunt private key
- I now have bookings working
- I now will move on to implimenting my view all bookings page
##### 02/05/2023
- I have the Employe view more polished and am now moving on to employee facing functions
##### 03/05/2023
- The Employee fuinctions have begun 
- I have re strucured my navigation sections of my site to make it more readable in code
- The employees can now view all bookings and cancel bookings
- bookings past a certain date can no longer be canceled
##### 04/05/2023
- Managers and admings can create other employees
- New employees are issued a default password and email
- upon first login they are asked to change their password
##### 05/05/2023
- things to fix:
	- validation sending error redirects should be changed to just send the error and controler should handle the redirects to avoid incorect navigation
	- All dogs being deleted instead of just one
- Discussed with the client about permissions and have a plan for what employee roles should see what information.
- 