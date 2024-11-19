/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.RoleTable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Om Gupta
 */
@Local
public interface RoleTableFacadeLocal {

    void create(RoleTable roleTable);

    void edit(RoleTable roleTable);

    void remove(RoleTable roleTable);

    RoleTable find(Object id);

    List<RoleTable> findAll();

    List<RoleTable> findRange(int[] range);

    int count();
    
}
