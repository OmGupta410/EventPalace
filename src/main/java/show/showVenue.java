/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
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

    
    /**
     * Creates a new instance of showVenue
     */
    public showVenue() {
    }
    


    
    public List<VenueTable> findAll(){
        return this.venueTableFacade.findAll();
    }
    
   
  

}
 

