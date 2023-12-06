# eGovernment Vaccination System 
This project involves the implementation of a web-based MVC eGovernment application designed for managing vaccination records and disseminating news related to the fight against the Covid-19 virus. 
The application caters to registered users, including medical staff, patients, and administrators. 
The following sections outline the main features and functionalities of the system.

## Technologies
- Java
- MVC
- MySQL
- Thymeleaf

## Data Model

### User
- Email address (used as a username for login)
- Password
- Name
- Surname
- Date of birth
- JMBG
- Address
- Phone number
- Registration date and time
- Role (medical staff, patient, or administrator)

### Vaccine Manufacturer
- Manufacturer
- Country of manufacture

### Vaccine
- Name
- Available quantity
- Manufacturer

### News
- Name of the news
- News content
- Date and time of publication

### News about Patients
- Number of patients in the last 24 hours
- Number of people tested in the last 24 hours
- Total number of patients since the beginning of the pandemic
- Number of hospitalized
- Number of patients on ventilators
- Date and time of publication

## Functionality

### User Registration and Authentication
- Users can register with their email and password.
- Different roles (medical staff, patient, administrator) are assigned during registration.
- Medical staff and administrators are programmatically added to the system.

### News Management
- Administrators can create news, including statistical reports published once a day.
- Daily statistics on the home page are accessible to all users.
- Featured statistical reports are highlighted as the first announcement.

### Vaccine Management
- Medical staff can view all vaccines with available quantities.
- Vaccines can be searched, sorted, and individually viewed.
- Administrators can edit vaccine details except for available quantity.
- Procurement of new vaccine quantities is initiated by medical staff.
- Administrators can view and approve/reject procurement requests.
- Rejected requests require comments for clarification.
- Medical staff can update and resubmit rejected requests.

### Patient Vaccination Application
- Patients can view available vaccines and apply for vaccination.
- Medical staff can view all vaccination applications.
- Medical staff can administer vaccines, reducing available quantities and deleting other pending applications from the same user.

### User Profile Management
- Users can view and update their profiles.
- Password changes require validation and confirmation.

### User Registration, Login, and Logout
- Registration includes a link to switch to login if the user has no account.
- Login is required for access to specific functionalities.
- Logout functionality is available on all pages.
