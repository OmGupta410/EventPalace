/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.UserRegistrationTable;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Om Gupta
 */
@Stateless
public class UserRegistrationTableFacade extends AbstractFacade<UserRegistrationTable> implements UserRegistrationTableFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserRegistrationTableFacade() {
        super(UserRegistrationTable.class);
    }
    
    
      @Override
    public UserRegistrationTable findByEmailAndPassword(String email, String password) {
        try {
            TypedQuery<UserRegistrationTable> query = em.createQuery(
                    "SELECT u FROM UserRegistrationTable u WHERE u.email = :email AND u.password = :password", UserRegistrationTable.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null; // Return null if no result is found
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public UserRegistrationTable signup(UserRegistrationTable user) {
        try {
            // Ensure user id is not set explicitly if it is auto-incremented
           if (user.getUserid() != null) {
                user.setUserid(null); // Remove any manual setting of userid
            }

            em.merge(user);  // Persist the user entity
            return user;  // Return the user object
        } catch (Exception e) {
            e.printStackTrace();  // Print the exception stack trace for debugging
            throw new EJBException("Error during user signup: " + e.getMessage(), e);
        }
    }
}
