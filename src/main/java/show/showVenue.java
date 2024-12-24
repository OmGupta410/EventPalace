package show;

import com.mycompany.entity.VenueTable;
import com.mycompany.sessionBeans.VenueTableFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.UploadedFile;

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

    private VenueTable venuetable = new VenueTable();

    private Integer userId;
    private String venueName;
    private String location;
    private String description;
    private BigDecimal finalPrice;
    private BigDecimal bookingAdvancePrice;
    private int capacity;

     private UploadedFile uploadedFile;
    /**
     * Creates a new instance of showVenue
     */
    public showVenue() {
    }

//        public void init() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
//        if (session != null) {
//            userId = (Integer) session.getAttribute("userId");
//            System.out.println("User ID from session: " + userId);
//        } else {
//            System.out.println("No session found. User might not be logged in.");
//        }
//
//    }
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

    // cRUD on venue table
    public List<VenueTable> findAll() {
        return this.venueTableFacade.findAll();
    }

    public String insertVenue() {
        this.venueTableFacade.create(venuetable);
        this.venuetable = new VenueTable();
        return "Venues";

    }

    public String updateVenue(VenueTable v) {
        this.venuetable = v; // Ensure venuetable is the correct reference
        return "EditVenue"; // Navigate to edit page
    }

    public String updateVenue() {
        this.venueTableFacade.edit(venuetable);
        this.venuetable = venuetable;
        return "Venues";
    }

    public void deleteVenue(VenueTable ven) {
        this.venueTableFacade.remove(ven);
    }

    // Getter and Setter for EJB
    public VenueTableFacadeLocal getVenueTableFacade() {
        return venueTableFacade;
    }

    public void setVenueTableFacade(VenueTableFacadeLocal venueTableFacade) {
        this.venueTableFacade = venueTableFacade;
    }

    // Getter and Setter for selectedVenue
    public VenueTable getSelectedVenue() {
        return selectedVenue;
    }

    public void setSelectedVenue(VenueTable selectedVenue) {
        this.selectedVenue = selectedVenue;
    }

    public VenueTable getVenuetable() {
        return venuetable;
    }

    public void setVenuetable(VenueTable venuetable) {
        this.venuetable = venuetable;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
