/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.VenueTable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Om Gupta
 */
@Local
public interface VenueTableFacadeLocal {

    void create(VenueTable venueTable);

    void edit(VenueTable venueTable);

    void remove(VenueTable venueTable);

    VenueTable find(Object id);

    List<VenueTable> findAll(); 

    List<VenueTable> findRange(int[] range);

       int count();
    
//    VenueTable findByVenueId(Integer venueId);
       
       public List<VenueTable> getAllVenues();
       
       List<VenueTable> findAllVenues();
     VenueTable findVenueById(int venueId);
}
