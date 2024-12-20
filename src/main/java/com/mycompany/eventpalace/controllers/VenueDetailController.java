package com.mycompany.eventpalace.controllers;

import com.mycompany.entity.VenueTable;
import com.mycompany.sessionBeans.VenueTableFacadeLocal;
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
public class VenueDetailController {

    @EJB
    private VenueTableFacadeLocal venueFacade;

    private int venueId;
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
     FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        userId = (Integer) session.getAttribute("userId"); // Assuming userId is stored in the session
        System.out.println(userId);
    System.out.println("Venue ID: " + venueId);
    System.out.println("init in venueDetailController");
    
//     venue = new VenueTable();
//    venue.setVenuename("Test Venue");
//    venue.setDescription("This is a test venue.");
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
    
    
     private void loadVenueDetails() {
    System.out.println("Loading venue details for ID: " + venueId);
    this.venue = venueFacade.findVenueById(venueId);
    if (this.venue == null) {
        System.out.println("No venue found for ID: " + venueId);
    } else {
        System.out.println("Venue loaded: " + venue.getVenuename());
    }
}


}
