/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package show;

import com.mycompany.entity.UserRegistrationTable;
import com.mycompany.entity.VenueTable;
import com.mycompany.sessionBeans.UserRegistrationTableFacade;
import com.mycompany.sessionBeans.UserRegistrationTableFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Om Gupta
 */
@Named(value = "customerCont")
@SessionScoped
public class CustomerCont implements Serializable {

    @EJB
    private UserRegistrationTableFacadeLocal userRegistrationTableFacade;

    private UserRegistrationTable user = new UserRegistrationTable();

    public CustomerCont() {
    }

//    crud
    // cRUD on venue table
    public List<UserRegistrationTable> findAll() {
        return this.userRegistrationTableFacade.findAll();
    }

    public String insertuser() {
        this.userRegistrationTableFacade.create(user);
        this.user = new UserRegistrationTable();
        return "Customers";

    }

    public String updateuser(UserRegistrationTable user) {
        this.user = user; // Ensure venuetable is the correct reference
        return "updateuser"; // Navigate to edit page
    }

    public String updateuser() {
        this.userRegistrationTableFacade.edit(user);
        this.user = user;
        return "Customers";
    }

    public void deleteUser(UserRegistrationTable ven) {
        try {
            userRegistrationTableFacade.remove(ven);
            System.out.println("user deleted: " + ven.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    

    public UserRegistrationTableFacadeLocal getUserRegistrationTableFacade() {
        return userRegistrationTableFacade;
    }

    public void setUserRegistrationTableFacade(UserRegistrationTableFacadeLocal userRegistrationTableFacade) {
        this.userRegistrationTableFacade = userRegistrationTableFacade;
    }

    public UserRegistrationTable getUser() {
        return user;
    }

    public void setUser(UserRegistrationTable user) {
        this.user = user;
    }

}
