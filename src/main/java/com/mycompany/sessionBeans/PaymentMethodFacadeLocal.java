/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.PaymentMethod;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Om Gupta
 */
@Local
public interface PaymentMethodFacadeLocal {

    void create(PaymentMethod paymentMethod);

    void edit(PaymentMethod paymentMethod);

    void remove(PaymentMethod paymentMethod);

    PaymentMethod find(Object id);

    List<PaymentMethod> findAll();

    List<PaymentMethod> findRange(int[] range);

    int count();
    public void savePaymentmethod(PaymentMethod paymentmethod);
}
