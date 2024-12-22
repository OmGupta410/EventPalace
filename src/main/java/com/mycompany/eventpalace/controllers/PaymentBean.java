package com.mycompany.eventpalace.controllers;

import com.mycompany.entity.PaymentMethod;
import com.mycompany.entity.PaymentTable;
import com.mycompany.entity.UserBookingTable;
import com.mycompany.entity.VenueTable;
import com.mycompany.sessionBeans.PaymentMethodFacadeLocal;
import com.mycompany.sessionBeans.PaymentTableFacadeLocal;
import com.mycompany.sessionBeans.VenueTableFacadeLocal;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.servlet.http.HttpSession;

@Named(value = "PaymentBean")
@SessionScoped

public class PaymentBean implements Serializable{

    @EJB
    private PaymentMethodFacadeLocal paymentMethodFacade;
     private String cardHolderName;
    private String cardNumber;
    private String expMonth;
    private String expYear;
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
        
         System.out.println("Selected Payment: " + selectedPayment);
    }

    return selectedPayment;
}

    public String processPayment() {
        System.out.println("payment processing ............");
       
 System.out.println("Selected Payment: " + selectedPayment);
        if (selectedPayment != null) {
            try{

            // Save updated payment to the database
            paymentTableFacade.savePaymentt(selectedPayment);
            
            if (cardNumber != null && !cardNumber.isEmpty()) {
                PaymentMethod paymentMethod = new PaymentMethod();
                paymentMethod.setCardholdername(cardHolderName);
                paymentMethod.setCardnumber(cardNumber);
                paymentMethod.setExpirationmonth(expMonth);
                paymentMethod.setExpirationyear(expYear);
                paymentMethod.setPaymentId(selectedPayment);
                paymentMethodFacade.savePaymentmethod(paymentMethod);
            }
//            
             System.out.println("Payment processed successfully.");
             
              paymentTableFacade.savePaymentt(selectedPayment);
              
            System.out.println("status changes to completed");
             
             return "bookingSuccess.xhtml?faces-redirect=true";
             
            }catch(Exception e){
            System.err.println("Error processing payment: " + e.getMessage());
                e.printStackTrace();
                throw new RuntimeException("Failed to process payment.");
            }
        } else {
            
            throw new RuntimeException("No payment selected for processing");
        }
        
//            selectedPayment.setDatetime(new Date());
// Save updated payment to the database
           
            
            
          
            
                
            
            
        
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

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }
    

}
