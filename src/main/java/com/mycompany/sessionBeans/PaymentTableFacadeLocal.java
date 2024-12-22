/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.PaymentTable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Om Gupta
 */
@Local
public interface PaymentTableFacadeLocal {

    void create(PaymentTable paymentTable);

    void edit(PaymentTable paymentTable);

    void remove(PaymentTable paymentTable);

    PaymentTable find(Object id);

    List<PaymentTable> findAll();

    List<PaymentTable> findRange(int[] range);

    int count();
    
    public void savePaymentt(PaymentTable payment);
    
    PaymentTable findPaymentById(int paymentId);
}

