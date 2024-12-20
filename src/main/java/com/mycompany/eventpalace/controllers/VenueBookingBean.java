package com.mycompany.eventpalace.controllers;


import com.mycompany.entity.UserBookingTable;
import com.mycompany.entity.UserRegistrationTable;
import com.mycompany.entity.VenueTable;
import com.mycompany.sessionBeans.UserBookingTableFacadeLocal;
import java.io.Serializable;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named(value = "venueBookingBean")
@SessionScoped
public class VenueBookingBean implements Serializable {

    private Date bookingDate;  // Date of booking (current date)
    private Date eventDate;    // Event date selected by the user
    private String shift;
    private String eventType;
    private Integer venueId;
    private Integer userId;
    private String details;
    private String mobileNumber; // Changed from Integer to String for better flexibility with phone numbers

    @EJB
    private UserBookingTableFacadeLocal bookingFacade;
    FacesContext context = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

    public VenueBookingBean() {
        // Initialization logic (if any)
      HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
if (session == null) {
    System.out.println("Session is null!");
} else {
    System.out.println("Session ID: " + session.getId());
    System.out.println("User ID from session in booking bean: " + session.getAttribute("userId"));
    System.out.println("Venue ID from session in booking bean: " + session.getAttribute("venueId"));

}


    }

    public String navigateToVenueDetail() {
        return "venuDetail.xhtml?venueId=#{venue.venueId}";
    }

    public String clickBookVenue() {
        System.out.println("hello");
        
        System.out.println("Event Date: " + eventDate);
System.out.println("Shift: " + shift);
System.out.println("Event Type: " + eventType);
System.out.println("Venue ID: " + venueId);
System.out.println("User ID: " + userId);

//Calendar cal = Calendar.getInstance();
//cal.setTime(eventDate);
//cal.set(Calendar.HOUR_OF_DAY, 0);
//cal.set(Calendar.MINUTE, 0);
//cal.set(Calendar.SECOND, 0);
//cal.set(Calendar.MILLISECOND, 0);
//eventDate = cal.getTime();
//Ensure that the session exists before accessing it
if (session == null) {
    throw new IllegalStateException("No valid session found. User might not be logged in.");
}
//If you're trying to interact with a session after it has been invalidated 
if (session != null) {
    session.invalidate();
}
//If you're using FacesContext improperly   
if (context == null) {
    throw new IllegalStateException("FacesContext is not available.");
}


        
//        FacesContext context = FacesContext.getCurrentInstance();
//
//        // Retrieve the userId from the session
//        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            System.out.println("User is not logged in.");
            return "login.xhtml"; // Redirect to login page if userId is not set
        }

        System.out.println("User ID check: " + userId);

        // Retrieve venueId from request parameters
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String venueIdParam = params.get("venueId");
        if (venueIdParam != null) {
            System.out.println("Venue ID is missing.");
            return "error"; // Redirect to an error page
        }

        try {
            venueId = Integer.valueOf(venueIdParam);
            System.out.println("Venue ID-: " + venueId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Venue ID: " + venueIdParam);
            return "error"; // Redirect to an error page
        }

        // Check venue availability
        if (!bookingFacade.isVenueAvailable(venueId, eventDate, shift)) {
            System.out.println("Venue is not available.");
            return "notAvailable"; // Redirect to an error page
        }

        // Create booking object
        UserBookingTable booking = new UserBookingTable();
        booking.setBookingdate(new Date()); // Current date
        booking.setEventdate(getSqlEventDate());
        booking.setShift(shift);
        booking.setEventtype(eventType);

        // Set VenueTable object
        VenueTable venue = new VenueTable();
        venue.setVenueId(venueId);
        booking.setVenueId(venue);

        // Set UserRegistrationTable object
        UserRegistrationTable user = new UserRegistrationTable();
        user.setUserid(userId);
        booking.setUserId(user);

        // Persist the booking
        try {
            bookingFacade.create(booking);
            System.out.println("Booking successfully created.");
        } catch (Exception e) { 
            e.printStackTrace();
            System.out.println("Error while creating booking: " + e.getMessage());
            return "error"; // Redirect to an error page
        }

        System.out.println("booking succes");
        return "bookingSuccess.xhtml"; // Redirect to a success page
    }

    // Getters and Setters
    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        if (eventDate != null) {
            this.eventDate = java.sql.Date.valueOf(
                eventDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            );
        }
    }

    public java.sql.Date getSqlEventDate() {
        return eventDate != null ? new java.sql.Date(eventDate.getTime()) : null;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public UserBookingTableFacadeLocal getBookingFacade() {
        return bookingFacade;   
    }

    public void setBookingFacade(UserBookingTableFacadeLocal bookingFacade) {
        this.bookingFacade = bookingFacade;
    }
}
