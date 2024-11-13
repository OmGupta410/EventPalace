/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.VenueFacility;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Om Gupta
 */
@Stateless
public class VenueFacilityFacade extends AbstractFacade<VenueFacility> implements VenueFacilityFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VenueFacilityFacade() {
        super(VenueFacility.class);
    }
    
}
