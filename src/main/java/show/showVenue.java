package show;

import com.mycompany.entity.VenueTable;
import com.mycompany.sessionBeans.VenueTableFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
    private String image;

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



    // cRUD on venue table
    public List<VenueTable> findAll() {
        return this.venueTableFacade.findAll();
    }

//    public String insertVenue() {
//        if (venuetable.getVenuename()== null || venuetable.getVenuename().isEmpty()) {
//            System.out.println("Venue name is required.");
//            return null;
//        }
//
//        System.out.println("vname : " + venuetable.getVenuename());
//
//        this.venueTableFacade.create(venuetable);
//        this.venuetable = new VenueTable();
//        return "Venues";
//
//    }
    
    
    public String insertVenue() {
    try {
        // Create a new VenueTable object
        VenueTable newVenue = new VenueTable();

        // Set the properties from the form inputs
//        newVenue.setUserId(venuetable.getUserId());
        newVenue.setVenuename(venuetable.getVenuename());
        newVenue.setLocation(venuetable.getLocation());
        newVenue.setFinalprice(venuetable.getFinalprice());
        newVenue.setBookingadvanceprice(venuetable.getBookingadvanceprice());
        newVenue.setCapacity(venuetable.getCapacity());
        newVenue.setDescription(venuetable.getDescription());
        newVenue.setImage(venuetable.getImage());

        // Save the venue using the EJB
        venueTableFacade.create(newVenue);

        // Clear the form fields
        venuetable = new VenueTable();

        return "success"; // Navigate to a success page or refresh the form
    } catch (Exception e) {
        e.printStackTrace();
        return "error"; // Navigate to an error page or display a message
    }
}


 public String updateVenue(VenueTable venue) {
    try {
        venueTableFacade.edit(venue);
        System.out.println("Venue updated: " + venue.getVenuename());
        return "EditVenue"; // Navigate back to the page
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

    public String updateVenue() {
        this.venuetable = venuetable;

        this.venueTableFacade.edit(venuetable);
        return "Venues";
    }

  public void deleteVenue(VenueTable venue) {
    try {
        venueTableFacade.remove(venue);
        System.out.println("Venue deleted: " + venue.getVenuename());
    } catch (Exception e) {
        e.printStackTrace();
    }
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

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public BigDecimal getBookingAdvancePrice() {
        return bookingAdvancePrice;
    }

    public void setBookingAdvancePrice(BigDecimal bookingAdvancePrice) {
        this.bookingAdvancePrice = bookingAdvancePrice;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
    