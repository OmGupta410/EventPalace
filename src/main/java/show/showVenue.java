package show;

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
@Named(value = "showVenue")
@SessionScoped
public class showVenue implements Serializable {

    @EJB
    private VenueTableFacadeLocal venueTableFacade;

    private VenueTable selectedVenue; // Holds the venue being edited

    /**
     * Creates a new instance of showVenue
     */
    public showVenue() {
    }

    // Getter and Setter for EJB
    public VenueTableFacadeLocal getVenueTableFacade() {
        return venueTableFacade;
    }

    public void setVenueTableFacade(VenueTableFacadeLocal venueTableFacade) {
        this.venueTableFacade = venueTableFacade;
    }

    // Method to fetch all venues
    public List<VenueTable> findAll() {
        return this.venueTableFacade.findAll();
    }

    // Getter and Setter for selectedVenue
    public VenueTable getSelectedVenue() {
        return selectedVenue;
    }

    public void setSelectedVenue(VenueTable selectedVenue) {
        this.selectedVenue = selectedVenue;
    }

    // Method to load a venue by ID
    public void loadVenue(int id) {
        selectedVenue = venueTableFacade.find(id); // Assumes 'find' is implemented in VenueTableFacadeLocal
        if (selectedVenue != null) {
            System.out.println("Loaded Venue: " + selectedVenue.getVenuename()); // Replace with actual field name
        } else {
            System.out.println("Venue not found for ID: " + id);
        }
    }

    // Method to save changes made to the selected venue
    public void saveVenue() {
        if (selectedVenue != null) {
            venueTableFacade.edit(selectedVenue); // Assumes 'edit' method is implemented in VenueTableFacadeLocal
            System.out.println("Venue updated successfully.");
        } else {
            System.out.println("No venue selected to save.");
        }
    }
}
