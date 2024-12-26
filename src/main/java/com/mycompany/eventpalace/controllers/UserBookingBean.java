package com.mycompany.eventpalace.controllers;

import com.mycompany.entity.UserBookingTable;
import com.mycompany.sessionBeans.UserBookingTableFacadeLocal;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Om Gupta
 */
@SessionScoped
public class UserBookingBean implements Serializable {

    @EJB
    private UserBookingTableFacadeLocal userBookingTableFacade;

    @Inject
    private HttpSession session;  // Inject the HttpSession

    private List<UserBookingTable> userBookings;

    public void loadUserBookings() {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId != null) {
            System.out.println("User ID fetched from session: " + userId);
            this.userBookings = userBookingTableFacade.findBookingsByUserId(userId);
        } else {
            System.out.println("User ID is null");
        }
    }

    // Getters and Setters
    public List<UserBookingTable> getUserBookings() {
        return userBookings;
    }

    public void setUserBookings(List<UserBookingTable> userBookings) {
        this.userBookings = userBookings;
    }

    // This method will be called to load data for a user
    public String loadBookings() {
        loadUserBookings();
        return "userBookings"; // Redirect to the userBookings page
    }
}
