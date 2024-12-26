/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package show;

import com.mycompany.entity.UserBookingTable;
import com.mycompany.entity.VenueTable;
import com.mycompany.sessionBeans.UserBookingTableFacadeLocal;
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
@Named(value = "bookings")
@SessionScoped
public class Bookings implements Serializable {

    @EJB
    private UserBookingTableFacadeLocal userBookingTableFacade;
    
    private UserBookingTable userBookingtable= new UserBookingTable();
    
    private String date;

    /**
     * Creates a new instance of Bookings
     */
    public Bookings() {
    }
    
     // cRUD on booking table
    public List<UserBookingTable> findAll() {
        return this.userBookingTableFacade.findAll();
    }

    public String insertVenue() {
        this.userBookingTableFacade.create(userBookingtable);
        this.userBookingtable = new UserBookingTable();
        return "Bookings";

    }

    public String updateVenue(UserBookingTable ub) {
        this.userBookingtable = ub; // Ensure venuetable is the correct reference
        return "EditBooking"; // Navigate to edit page
    }

    public String updateVenue() {
        this.userBookingTableFacade.edit(userBookingtable);
        this.userBookingtable = userBookingtable;
        return "Bookings";
    }

    public void deleteVenue(UserBookingTable ubt) {
        this.userBookingTableFacade.remove(ubt);
    }
    public UserBookingTableFacadeLocal getUserBookingTableFacade() {
        return userBookingTableFacade;
    }

    public void setUserBookingTableFacade(UserBookingTableFacadeLocal userBookingTableFacade) {
        this.userBookingTableFacade = userBookingTableFacade;
    }

    public UserBookingTable getUserBookingtable() {
        return userBookingtable;
    }

    // Getter and Setter for EJB
    public void setUserBookingtable(UserBookingTable userBookingtable) {   
        this.userBookingtable = userBookingtable;
    }

   
    
    
    
}
