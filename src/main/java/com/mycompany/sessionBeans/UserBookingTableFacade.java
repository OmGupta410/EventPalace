/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.UserBookingTable;
import java.time.LocalDate;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Om Gupta
 */
@Stateless
public class UserBookingTableFacade extends AbstractFacade<UserBookingTable> implements UserBookingTableFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserBookingTableFacade() {
        super(UserBookingTable.class);
    }
    
    
    @Override
    public boolean isVenueAvailable(Integer venueId, Date eventDate, String shift) {
//        String query = "SELECT COUNT(u) FROM UserBookingTable u WHERE u.venueId.id = :venueId AND u.eventdate = :eventDate AND u.shift = :shift";
        String query = "SELECT COUNT(u) FROM UserBookingTable u WHERE u.venueId.venueId = :venueId AND u.eventdate = :eventDate AND u.shift = :shift";
        Long count = em.createQuery(query, Long.class)
                       .setParameter("venueId", venueId)
                       .setParameter("eventDate", eventDate)
                       .setParameter("shift", shift)
                       .getSingleResult();
        return count == 0; // True if no booking exists
    }
}
