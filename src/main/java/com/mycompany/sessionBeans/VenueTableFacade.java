/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.VenueTable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Om Gupta
 */
@Stateless
public class VenueTableFacade extends AbstractFacade<VenueTable> implements VenueTableFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VenueTableFacade() {
        super(VenueTable.class);
    }
    
    public VenueTable findByVenueId(Integer venueId) {
        try {
            return em.createNamedQuery("VenueTable.findByVenueId", VenueTable.class)
                    .setParameter("venueId", venueId)
                    .getSingleResult();
        } catch (Exception e) {
            // Handle exceptions like NoResultException or NonUniqueResultException
            System.err.println("Error fetching venue by ID: " + e.getMessage());
            return null; // Or throw a custom exception if needed
        }
    }

    public List<VenueTable> findByName(String name) {
        return em.createNamedQuery("VenueTable.findByVenuename", VenueTable.class)
                .setParameter("venuename", name)
                .getResultList();
    }

    public List<VenueTable> findByLocation(String location) {
        return em.createNamedQuery("VenueTable.findByLocation", VenueTable.class)
                .setParameter("location", location)
                .getResultList();
    }

//Pagination for Large Data Sets: Add a paginated query to handle large venue data:
    public List<VenueTable> getVenuesWithPagination(int start, int maxResults) {
        return em.createQuery("SELECT v FROM VenueTable v", VenueTable.class)
                .setFirstResult(start)
                .setMaxResults(maxResults)
                .getResultList();
    }

    // Method to fetch all venues
    public List<VenueTable> getAllVenues() {
        return em.createQuery("SELECT v FROM VenueTable v", VenueTable.class).getResultList();
    }

    @Override
    public List<VenueTable> findAllVenues() {
        return em.createQuery("SELECT v FROM VenueTable v", VenueTable.class).getResultList();
    }
    
    public VenueTable findVenueById(int venueId) {
        return em.find(VenueTable.class, venueId)   ;
    }

    public void saveVenue(VenueTable venue) {
        em.persist(venue);
    }
    

    
}
