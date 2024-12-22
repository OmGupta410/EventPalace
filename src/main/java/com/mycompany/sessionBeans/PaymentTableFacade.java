/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.PaymentTable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Om Gupta
 */
@Stateless
public class PaymentTableFacade extends AbstractFacade<PaymentTable> implements PaymentTableFacadeLocal {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaymentTableFacade() {
        super(PaymentTable.class);
    }
    
    public void savePaymentt(PaymentTable payment) {
        if (payment.getPaymentId() == null) {
            em.persist(payment); // Save a new record
        } else {
            em.merge(payment);  // Update an existing record
        }
    }
    
    public PaymentTable findPaymentById(int paymentId){
        return em.find(PaymentTable.class, paymentId);

    }
}
