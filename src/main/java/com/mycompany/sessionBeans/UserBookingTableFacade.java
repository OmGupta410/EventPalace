/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.UserBookingTable;
import com.mycompany.entity.UserRegistrationTable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
    
        public UserRegistrationTable findByEmailAndPassword(String email, String password) {
        try {
            TypedQuery<UserRegistrationTable> query = em.createQuery(
                "SELECT u FROM UserRegistrationTable u WHERE u.email = :email AND u.password = :password", UserRegistrationTable.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    
}
