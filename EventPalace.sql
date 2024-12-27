create database EVENTPALACE;
use EVENTPALACE;
-- Table 1: Role_table
CREATE TABLE Role_table (
    Role_Id INT PRIMARY KEY auto_increment,
    Role_name VARCHAR(255) NOT NULL
);

-- Table 2: User_Registration_table
CREATE TABLE User_Registration_table (
    User_id INT PRIMARY KEY	auto_increment,
    Role_id INT ,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    ContactNo VARCHAR(15) NOT NULL,
    Status VARCHAR(50) NOT NULL,
    FOREIGN KEY (Role_id) REFERENCES Role_table(Role_Id)
);

-- Table 3: Venue_table
CREATE TABLE Venue_table (
    VenueId INT PRIMARY KEY auto_increment,
    UserId INT,
    Venue_name VARCHAR(255) NOT NULL,
    Location VARCHAR(255) NOT NULL,
    Description TEXT NOT NULL,
    Image VARCHAR(255) DEFAULT 'default_image.jpg',
    Final_price DECIMAL(10,2) NOT NULL,
    Booking_advance_price DECIMAL(10,2) NOT NULL,
    Capacity INT NOT NULL,
    FOREIGN KEY (UserId) REFERENCES User_Registration_table(User_id)
);
desc Venue_table;
-- Table 4: User_Venue (Many-to-Many relationship table)
CREATE TABLE User_Venue (
    User_id INT,
    Venue_id INT,
    PRIMARY KEY (User_id, Venue_id),
    FOREIGN KEY (User_id) REFERENCES User_Registration_table(User_id),
    FOREIGN KEY (Venue_id) REFERENCES Venue_table(VenueId)
);

-- Table 5: Venue_Facility
CREATE TABLE Venue_Facility (
    Facility_id INT PRIMARY KEY auto_increment,
    VenueId INT,
    No_of_Rooms INT NOT NULL,
    Food_type VARCHAR(255) NOT NULL,
    Parking BOOLEAN NOT NULL,
    FOREIGN KEY (VenueId) REFERENCES Venue_table(VenueId)
);

-- Table 6: Venue_Gallery
CREATE TABLE Venue_Gallery (
    Gallery_Id INT PRIMARY KEY auto_increment,
    VenueId INT,
    Birthday_image VARCHAR(255) DEFAULT 'birthday_default.jpg',
    Marriage_image VARCHAR(255) DEFAULT 'marriage_default.jpg',
    Party_image VARCHAR(255) DEFAULT 'party_default.jpg',
    FOREIGN KEY (VenueId) REFERENCES Venue_table(VenueId)
);

-- Table 7: User_Booking_table
-- CREATE TABLE User_Booking_table (
-- Booking_id INT PRIMARY KEY auto_increment,
-- UserId INT,
-- VenueId INT,
-- Booking_date DATE NOT NULL default current_timestamp,
-- Event_date DATE NOT NULL,
-- Shift VARCHAR(50) NOT NULL,
-- Event_type VARCHAR(100) NOT NULL,
-- FOREIGN KEY (UserId) REFERENCES User_Registration_table(User_id),
-- FOREIGN KEY (VenueId) REFERENCES Venue_table(VenueId)
-- );

CREATE TABLE User_Booking_table (
    Booking_id INT PRIMARY KEY AUTO_INCREMENT,
    UserId INT,
    VenueId INT,
    Booking_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Event_date DATE NOT NULL,
    Shift VARCHAR(50) NOT NULL,
    Event_type VARCHAR(100) NOT NULL,
    FOREIGN KEY (UserId) REFERENCES User_Registration_table(User_id),
    FOREIGN KEY (VenueId) REFERENCES Venue_table(VenueId)
);


-- Table 8: Payment_table
CREATE TABLE Payment_table (
    PaymentId INT PRIMARY KEY auto_increment,
    BookingId INT,
    VenueId INT,
    Advance_payment DECIMAL(10,2) NOT NULL,
    Payment_status VARCHAR(50) CHECK (Payment_status IN ('pending', 'complete')),
    Date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (BookingId) REFERENCES User_Booking_table(Booking_id),
    FOREIGN KEY (VenueId) REFERENCES Venue_table(VenueId)
);

-- Table 9: Payment_Method
CREATE TABLE Payment_Method (
    CardId INT PRIMARY KEY auto_increment,
    PaymentId INT,
    Card_holder_name VARCHAR(255) NOT NULL,
    Card_number VARCHAR(255) NOT NULL,
    Expiration_month VARCHAR(20) NOT NULL,
    Expiration_year VARCHAR(20) NOT NULL,
    FOREIGN KEY (PaymentId) REFERENCES Payment_table(PaymentId)
);

rollback;


-- Table 10: Review_table
CREATE TABLE Review_table (
    ReviewId INT PRIMARY KEY auto_increment,
    UserId INT,
    VenueId INT,
    Comment TEXT,
    Rating INT CHECK (Rating BETWEEN 1 AND 5),
    FOREIGN KEY (UserId) REFERENCES User_Registration_table(User_id),
    FOREIGN KEY (VenueId) REFERENCES Venue_table(VenueId)
);

show tables;

show databases;

use eventpalace;

show tables;
desc payment_table;	
select * from payment_table;
desc payment_method;
select* from Review_table;


select * from payment_method;
desc user_booking_table;
select * from user_booking_table;		
desc user_registration_table;
select * from user_registration_table;	
desc user_registration_table;
select * from Role_table; 
select * from venue_table;
desc venue_table;



