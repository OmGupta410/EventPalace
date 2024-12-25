package com.mycompany.eventpalace.controllers;

import com.mycompany.entity.VenueTable;
import com.mycompany.sessionBeans.VenueTableFacadeLocal;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
//@SessionScoped

//
//@ManagedBean
//@ViewScoped
public class VenueDetailController implements Serializable{

    @EJB
    private VenueTableFacadeLocal venueFacade;

    private Integer venueId;
    private VenueTable venue;
    private List<VenueTable> venues;
    private Integer userId;

    public VenueTable getVenue() {
        if (venue == null) {
            String venueIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("venueId");
            System.out.println("Venue ID from request parameter 1: " + venueIdParam);
            try {
                venueId = Integer.parseInt(venueIdParam);
                System.out.println("venue id in Venuedetailcontroller " + venueId);
            } catch (NumberFormatException e) {
                System.out.println("Invalid venueId parameter: " + venueIdParam);
                return null;
            }

            if (venueId != null) {
                try {
//                    int id = Integer.parseInt(venueId);
                    venueId = Integer.parseInt(venueIdParam);
                    venue = venueFacade.find(venueId);
                } catch (NumberFormatException e) {
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Venue ID", "Please select a valid venue.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } catch (Exception e) {
                    e.printStackTrace();
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Loading Venue", "An error occurred while loading the venue.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        }
        return venue;
    }

    @PostConstruct
    public void init() {
        System.out.println("VenueDetailController init method called.");
//        System.out.println("VenueFacade is " + (venueFacade != null ? "available" : "null"));
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                Object sessionUserId = session.getAttribute("userId");
                System.out.println("userId in session in init of venueDetailcontroller" + sessionUserId);
                if (sessionUserId instanceof Integer) {
                    userId = (Integer) sessionUserId;
                } else if (sessionUserId instanceof String) {
                    userId = Integer.parseInt((String) sessionUserId);
                } else {
                    System.out.println("Invalid userId format in session.");
                }
            } else {
                System.out.println("Session is null. Ensure the user is logged in.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error parsing userId from session: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("VenueDetailController init method called end.");
    }

    public void setVenue(VenueTable venue) {
        this.venue = venue;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

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
        return "venueDetail.xhtml?faces-redirect=true&venueId=" + newVenueId; // Redirect with updated venueId
    }
    
    
    

}
