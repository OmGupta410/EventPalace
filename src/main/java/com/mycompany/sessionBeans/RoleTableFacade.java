/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.RoleTable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 *
 * @author Om Gupta
 */
@Stateless
public class RoleTableFacade extends AbstractFacade<RoleTable> implements RoleTableFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleTableFacade() {
        super(RoleTable.class);
    }
    
      public RoleTable findByUsernameAndPassword(String username, String password) {
        TypedQuery<RoleTable> query = em.createQuery(
                "SELECT r FROM RoleTable r WHERE r.username = :username AND r.password = :password", RoleTable.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        
        // Assuming that username and password combination is unique
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // No result found
        }
    }


    
}
