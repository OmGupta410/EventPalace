package show;

import com.mycompany.entity.UserBookingTable;
import com.mycompany.sessionBeans.UserRegistrationTableFacadeLocal; // Import the local interface for UserTableFacade
import com.mycompany.entity.UserRegistrationTable; // Import the UserTable entity
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.ejb.EJB;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Om Gupta
 */
@Named(value = "userdata")
@SessionScoped
public class Userdata implements Serializable {

    // Inject the UserTableFacadeLocal to access the database methods
    @EJB
    private UserRegistrationTableFacadeLocal userTableFacade;

    // Store the user ID fetched from the session
    private Integer userId;

    // Store the user details fetched from the database
    private UserRegistrationTable userDetails;
    
    
    private List<UserBookingTable> userBookings;

    /**
     * Creates a new instance of Userdata
     */
    public Userdata() {
    }

    /**
     * Fetch the user ID from the session (Mocked for demonstration) Replace
     * this method with actual session management logic
     */
    public void fetchUserIdFromSession() {
        // In a real-world application, retrieve userId from the session context
        this.userId = 1; // Example: Mock userId from session
    }

    /**
     * Fetch user details from the database using the userId
     */
    
       List<UserBookingTable> bookings = new ArrayList<>();
    public void fetchUserDetails() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            Integer sessionUserId = (Integer) externalContext.getSessionMap().get("userId");

            if (sessionUserId != null) {
                this.userId = sessionUserId;
                this.userDetails = userTableFacade.find(userId);
                System.out.println("Fetched User Details: " + userDetails);
                fetchUserBookings();
            } else {
                System.out.println("User ID not found in session.");
            }
        } catch (Exception e) {
            System.err.println("Error fetching user details: " + e.getMessage());
        }
    }
    
    
    
     private void fetchUserBookings() {
        userBookings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventpalace", "username", "password");
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_booking_table WHERE User_id = ?")) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UserBookingTable booking = new UserBookingTable();
                booking.setBookingid(resultSet.getInt("Booking_id"));
                booking.setBookingdate(resultSet.getDate("Booking_date"));
                booking.setEventtype(resultSet.getString("Event_type"));
                booking.setShift(resultSet.getString("Shift"));
                userBookings.add(booking);
            }
        } catch (Exception e) {
            System.err.println("Error fetching user bookings: " + e.getMessage());
        }
    }

    // Getter for userDetails
    public UserRegistrationTable getUserDetails() {
        return userDetails;
    }

    // Getter and Setter for userId
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserRegistrationTableFacadeLocal getUserTableFacade() {
        return userTableFacade;
    }

    public void setUserTableFacade(UserRegistrationTableFacadeLocal userTableFacade) {
        this.userTableFacade = userTableFacade;
    }

    public List<UserBookingTable> getUserBookings() {
        return userBookings;
    }

    public void setUserBookings(List<UserBookingTable> userBookings) {
        this.userBookings = userBookings;
    }

    public List<UserBookingTable> getBookings() {
        return bookings;
    }

    public void setBookings(List<UserBookingTable> bookings) {
        this.bookings = bookings;
    }
    
    
    
    
    
}
