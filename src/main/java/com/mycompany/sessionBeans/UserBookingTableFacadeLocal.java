/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.UserBookingTable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Om Gupta
 */
@Local
public interface UserBookingTableFacadeLocal {

    void create(UserBookingTable userBookingTable);

    void edit(UserBookingTable userBookingTable);

    void remove(UserBookingTable userBookingTable);

    UserBookingTable find(Object id);

    List<UserBookingTable> findAll();

    List<UserBookingTable> findRange(int[] range);

    int count();
    
    
    //maybe use timestamp 
    boolean isVenueAvailable(Integer venueId, Date eventDate, String shift);
}
