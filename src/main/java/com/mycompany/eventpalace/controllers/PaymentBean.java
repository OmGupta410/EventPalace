package com.mycompany.eventpalace.controllers;

import com.mycompany.entity.PaymentTable;
import com.mycompany.entity.UserBookingTable;
import com.mycompany.entity.VenueTable;
import com.mycompany.sessionBeans.PaymentTableFacadeLocal;
import com.mycompany.sessionBeans.VenueTableFacadeLocal;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named(value = "PaymentBean")
@SessionScoped

public class PaymentBean implements Serializable{
    private PaymentTable selectedPayment;

   @EJB
    private VenueTableFacadeLocal venuetablefacade;

    @EJB
    private PaymentTableFacadeLocal paymentTableFacade;
    
 @PostConstruct
public void init() {
    FacesContext context = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

    if (session != null) {
        Object paymentId = session.getAttribute("paymentId");
        if (paymentId != null) {
            try {
              
                selectedPayment = paymentTableFacade.findPaymentById(Integer.parseInt(paymentId.toString()));
            } catch (NumberFormatException e) {
                System.err.println("Error parsing paymentId: " + e.getMessage());
            }
        }
    }

    if (selectedPayment == null) {
        selectedPayment = new PaymentTable();
        System.out.println("No payment details found for the session.");
    }
}


    public PaymentTable getSelectedPayment() {
    if (selectedPayment == null) {
        selectedPayment = new PaymentTable();

        // Get the current session
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        System.out.println("Session paymentId: " + session.getAttribute("paymentId"));

        // Fetch session attributes directly from the session
        Object paymentId = session != null ? session.getAttribute("paymentId") : null;
        Object bookingId = session != null ? session.getAttribute("bookingId") : null;
        Object venueId = session != null ? session.getAttribute("venueId") : null;

        // Debugging logs
        System.out.println("paymentId: " + paymentId);
        System.out.println("bookingId: " + bookingId);
        System.out.println("venueId: " + venueId);

        // Convert and set paymentId if not null
        if (paymentId != null) {
            try {
                selectedPayment.setPaymentId(Integer.parseInt(paymentId.toString()));
            } catch (NumberFormatException e) {
                System.err.println("Error parsing paymentId: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("paymentId is missing in the session");
        }

        // Convert and set bookingId if not null
        if (bookingId != null) {
            UserBookingTable booking = new UserBookingTable();
            try {
                booking.setBookingid(Integer.parseInt(bookingId.toString()));
                selectedPayment.setBookingId(booking);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing bookingId: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("bookingId is missing in the session");
        }

        // Set venueId and retrieve venue details
        if (venueId != null) {
            try {
                VenueTable venue = venuetablefacade.findVenueById(Integer.parseInt(venueId.toString()));
                selectedPayment.setVenueId(venue);
                selectedPayment.setAdvancepayment(venue.getBookingadvanceprice()); // Assuming bookingadvanceprice exists
            } catch (NumberFormatException e) {
                System.err.println("Error parsing venueId or retrieving venue: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Error retrieving venue: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("venueId is missing in the session");
        }

        // Set default values for payment status and date-time
        selectedPayment.setPaymentstatus("Pending");
        selectedPayment.setDatetime(new Date());
    }

    return selectedPayment;
}

    public void processPayment() {
        if (selectedPayment != null) {
            selectedPayment.setPaymentstatus("Completed");
            selectedPayment.setDatetime(new Date());

            // Save updated payment to the database
            paymentTableFacade.savePayment(selectedPayment);
             System.out.println("Payment processed successfully.");
        } else {
            throw new RuntimeException("No payment selected for processing");
        }
    }
    
    private HttpSession getSession() {
        FacesContext context = FacesContext.getCurrentInstance();
        return (HttpSession) context.getExternalContext().getSession(false);
    }


    // Getters and Setters

    public VenueTableFacadeLocal getVenuetablefacade() {
        return venuetablefacade;
    }

    public void setVenuetablefacade(VenueTableFacadeLocal venuetablefacade) {
        this.venuetablefacade = venuetablefacade;
    }

    public PaymentTableFacadeLocal getPaymentTableFacade() {
        return paymentTableFacade;
    }

    public void setPaymentTableFacade(PaymentTableFacadeLocal paymentTableFacade) {
        this.paymentTableFacade = paymentTableFacade;
    }

}
