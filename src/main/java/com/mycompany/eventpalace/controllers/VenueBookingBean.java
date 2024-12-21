package com.mycompany.eventpalace.controllers;

import com.mycompany.entity.PaymentTable;
import com.mycompany.entity.UserBookingTable;
import com.mycompany.entity.UserRegistrationTable;
import com.mycompany.entity.VenueTable;
import com.mycompany.sessionBeans.PaymentTableFacadeLocal;
import com.mycompany.sessionBeans.UserBookingTableFacadeLocal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Named(value = "venueBookingBean")
//@SessionScoped
@RequestScoped
public class VenueBookingBean implements Serializable {

    private Date bookingDate; // Current date
    private Date eventDate;   // Event date selected by the user
    private String shift;
    private String eventType;
    private Integer venueId;
    private Integer userId;
    private String details;
    private String mobileNumber; // Changed from Integer to String for flexibility

    @EJB
    private UserBookingTableFacadeLocal bookingFacade;

    @EJB
    private PaymentTableFacadeLocal paymentTableFacade;

    public VenueBookingBean() {
        initialize();
    }
    FacesContext context = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

    private void initialize() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context == null) {
            System.out.println("FacesContext is not available.");
            return;
        }

        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        if (session != null) {
            userId = (Integer) session.getAttribute("userId");
            System.out.println("User ID from session: " + userId);
        } else {
            System.out.println("No session found. User might not be logged in.");
        }

        // Retrieve venueId from URL parameters
        Map<String, String> params = context.getExternalContext().getRequestParameterMap();
        String venueIdParam = params.get("venueId");
        if (venueIdParam != null) {
            try {
                venueId = Integer.valueOf(venueIdParam);
                System.out.println("Venue ID from URL: " + venueId);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Venue ID: " + venueIdParam);
            }
        }
    }

    public String clickBookVenue() {
        if (userId == null) {
            System.out.println("User is not logged in.");
            return "login.xhtml"; // Redirect to login page
        }

        if (venueId == null) {
            System.out.println("Venue ID is missing.");
            return "error.xhtml"; // Redirect to an error page
        }

        // Check venue availability
        if (!bookingFacade.isVenueAvailable(venueId, eventDate, shift)) {
            System.out.println("Venue is not available.");
            return "notAvailable"; // Redirect to an error page
        }

        // Create and persist booking
        try {
            UserBookingTable booking = new UserBookingTable();
            booking.setBookingdate(new Date()); // Set current date
            booking.setEventdate(getSqlEventDate());
            booking.setShift(shift);
            booking.setEventtype(eventType);

            // Set venue and user references
            VenueTable venue = new VenueTable();
            venue.setVenueId(venueId);
            booking.setVenueId(venue);

            UserRegistrationTable user = new UserRegistrationTable();
            user.setUserid(userId);
            booking.setUserId(user);

            bookingFacade.create(booking);
            System.out.println("Booking successfully created.");

            // Store booking details in the session
            session.setAttribute("currentBooking", booking);

            // Fetch advance payment from the venue
            BigDecimal advancePayment = venue.getBookingadvanceprice();
            if (advancePayment == null) {
                advancePayment = BigDecimal.ZERO; // Or handle appropriately
            }
            if (booking == null || venue == null) {
                throw new IllegalArgumentException("Booking or Venue cannot be null");
            }
            System.out.println("Advance Payment: " + advancePayment);

            // Create a new payment record
            PaymentTable payment = new PaymentTable();
            payment.setBookingId(booking);
            payment.setVenueId(venue);
            payment.setAdvancepayment(advancePayment);
            payment.setPaymentstatus("Pending");
            payment.setDatetime(new Date());


//            //test data
//            PaymentTable payment = new PaymentTable();
//            payment.setAdvancepayment(new BigDecimal("100.00")); // Test value
//            payment.setPaymentstatus("Pending");
//            payment.setDatetime(new Date());

            // Save payment to the database
            paymentTableFacade.create(payment);
            System.out.println("Payment record successfully created.");

            // Store payment and booking details in the session
            session.setAttribute("paymentId", payment.getPaymentId());
            session.setAttribute("bookingId", booking.getBookingid());

            System.out.println("Session Booking ID: " + session.getAttribute("bookingId"));
            System.out.println("Session Payment ID: " + session.getAttribute("paymentId"));

            // Redirect to the payment page
            return "payment.xhtml?faces-redirect=true";
        } catch (ConstraintViolationException e) {
            System.err.println("Error while creating booking: " + e.getMessage());
            e.printStackTrace();
            for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
                System.out.println("Constraint Violation: " + violation.getMessage());
                System.out.println("Invalid Value: " + violation.getInvalidValue());
            }
            return "error"; // Redirect to an error page
        }
    }

    // Utility to convert eventDate to SQL Date
    public java.sql.Date getSqlEventDate() {
        return eventDate != null ? new java.sql.Date(eventDate.getTime()) : null;
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
        this.eventDate = eventDate;
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

    public PaymentTableFacadeLocal getPaymentTableFacade() {
        return paymentTableFacade;
    }

    public void setPaymentTableFacade(PaymentTableFacadeLocal paymentTableFacade) {
        this.paymentTableFacade = paymentTableFacade;
    }

    public FacesContext getContext() {
        return context;
    }

    public void setContext(FacesContext context) {
        this.context = context;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }

}
