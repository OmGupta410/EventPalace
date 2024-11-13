/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.UserRegistrationTable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Om Gupta
 */
@Local
public interface UserRegistrationTableFacadeLocal {

    void create(UserRegistrationTable userRegistrationTable);

    void edit(UserRegistrationTable userRegistrationTable);

    void remove(UserRegistrationTable userRegistrationTable);

    UserRegistrationTable find(Object id);

    List<UserRegistrationTable> findAll();

    List<UserRegistrationTable> findRange(int[] range);

    int count();


    UserRegistrationTable findByEmailAndPassword(String email, String password);

}
