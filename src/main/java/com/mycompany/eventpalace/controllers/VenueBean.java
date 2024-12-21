/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mycompany.eventpalace.controllers;

import com.mycompany.entity.VenueTable;
import com.mycompany.sessionBeans.VenueTableFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Om Gupta
 */
@Named(value = "venueBean")
@SessionScoped
public class VenueBean implements Serializable {

     @EJB
    private VenueTableFacadeLocal venueFacade;

    private List<VenueTable> venues;
    private VenueTable selectedVenue; // Added this to hold the selected venue

    // Getter for all venues
    public List<VenueTable> getVenues() {
        if (venues == null) {
            venues = venueFacade.findAllVenues(); // Retrieve all venues using the facade
        }
        return venues;
    }

    // Select a venue and navigate to its detail page
    public String selectVenue(VenueTable venue) {
        selectedVenue = venue; // Set the selected venue
        return "VenueDetail.xhtml?faces-redirect=true"; // Navigate to venueDetail page
    }


    // Default constructor
    public VenueBean() {
    }
   

    // Getter setter for the selected venue

    public VenueTable getSelectedVenue() {
        return selectedVenue;
    }
    public void setSelectedVenue(VenueTable selectedVenue) {
        this.selectedVenue = selectedVenue;
    }

    public VenueTableFacadeLocal getVenueFacade() {
        return venueFacade;
    }

    public void setVenueFacade(VenueTableFacadeLocal venueFacade) {
        this.venueFacade = venueFacade;
    }
    
//    new store venueid in session
    
//    private Integer selectedVenueId;
//
//    public Integer getSelectedVenueId() {
//        return selectedVenueId;
//    }
//
//    public void setSelectedVenueId(Integer selectedVenueId) {
//        this.selectedVenueId = selectedVenueId;
//    }
//
//    public String selectVenue(Integer venueId) {
//        this.selectedVenueId = venueId;
//        return "venuDetail.xhtml?faces-redirect=true";
//    }
    
}
