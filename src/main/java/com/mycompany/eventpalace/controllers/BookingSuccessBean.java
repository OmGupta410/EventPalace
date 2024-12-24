/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.eventpalace.controllers;

import com.mycompany.entity.UserBookingTable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Om Gupta
 */
@Named(value = "bookingSuccessBean")
@SessionScoped
public class BookingSuccessBean implements Serializable {

    private UserBookingTable currentBooking;
    
     private String venueName;
    private BigDecimal venueAPrice;

    public BookingSuccessBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        
         // Retrieve current booking from session
        currentBooking = (UserBookingTable) session.getAttribute("currentBooking");

         // Retrieve venueName and venuePrice from session
        venueName = (String) session.getAttribute("venueName");
        venueAPrice = (BigDecimal) session.getAttribute("venueAPrice");
        
        if (currentBooking != null && currentBooking.getVenueId() != null) {
            System.out.println("Booking ID: " + currentBooking.getBookingid());
            System.out.println("Venue Name: " + venueName);
            System.out.println("Booking Date: " + currentBooking.getBookingdate());
            System.out.println("Advance Price: " + venueAPrice);
        } else {
            System.out.println("currentBooking or venueId is null.");
        }

    }

    public UserBookingTable getCurrentBooking() {
        return currentBooking;
    }

    public void setCurrentBooking(UserBookingTable currentBooking) {
        this.currentBooking = currentBooking;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public BigDecimal getVenueAPrice() {
        return venueAPrice;
    }

    public void setVenueAPrice(BigDecimal venueAPrice) {
        this.venueAPrice = venueAPrice;
    }

}
