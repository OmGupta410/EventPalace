package com.mycompany.eventpalace.controllers;

import com.mycompany.entity.VenueTable;
import com.mycompany.sessionBeans.VenueTableFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped

//
//@ManagedBean
//@ViewScoped
public class VenueDetailController implements Serializable {

    @EJB
    private VenueTableFacadeLocal venueFacade;

    private Integer venueId;
    private VenueTable venue;
    private List<VenueTable> venues;
    private Integer userId;

    public VenueTable getVenue() {
        if (venue == null) {
            String venueId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("venueId");
            if (venueId != null) {
                try {
                    int id = Integer.parseInt(venueId); // Assuming venueId is an integer
                    venue = venueFacade.find(id);  // Retrieve the venue by id from the database
                } catch (NumberFormatException e) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Venue ID", "Please select a valid venue.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        }
        return venue;
    }

    @PostConstruct
    public void init() {

        System.out.println("VenueDetailController init method called start.");

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
//
            if (session != null) {
             Object userId = (Integer) session.getAttribute("userId"); // Assuming userId is stored in the session
                if (userId instanceof String) {
                    userId = Integer.parseInt((String) userId);
                } else if (userId instanceof Integer) {
                    userId = (Integer) userId;
                } else {    
                    System.out.println("Invalid userId format in session.");
                }
                 System.out.println("user ID in venuedetail controller : " + userId);
        System.out.println("Venue ID in venuedetail controller : " + venueId);
        System.out.println("init in venueDetailController end");
//
            } else {
                System.out.println("No session found. Ensure the user is logged in.");
            }
//      

    }

    public void setVenue(VenueTable venue) {
        this.venue = venue;
    }
// Getter for VenueTable
//    public VenueTable getVenue() {
//        if (venues == null || venues.isEmpty()) {
//    System.out.println("No venues available");
//}
//
//        if (venue == null) {
//            venue = venueFacade.findVenueById(venueId); // Find venue by ID using facade
//        }
//        return venue;
//    }

    public VenueTableFacadeLocal getVenueFacade() {
        return venueFacade;
    }

    public void setVenueFacade(VenueTableFacadeLocal venueFacade) {
        this.venueFacade = venueFacade;
    }

//    public VenueTable getVenue() {
//        return venue;
//    }
//    public void setVenue(VenueTable venue) {
//        this.venue = venue;
//    }
    public List<VenueTable> getVenues() {
        return venues;
    }

    public void setVenues(List<VenueTable> venues) {
        this.venues = venues;
    }

    // Getter for venueId
    public int getVenueId() {
        return venueId;
    }

// Setter for venueId
    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String loadVenueDetails(Integer newVenueId) {
        this.venueId = newVenueId; // Update the venueId
        return "venuDetail.xhtml?faces-redirect=true&venueId=" + newVenueId; // Redirect with updated venueId
    }

}
