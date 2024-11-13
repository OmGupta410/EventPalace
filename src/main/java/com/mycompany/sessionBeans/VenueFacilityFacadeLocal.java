/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.VenueFacility;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Om Gupta
 */
@Local
public interface VenueFacilityFacadeLocal {

    void create(VenueFacility venueFacility);

    void edit(VenueFacility venueFacility);

    void remove(VenueFacility venueFacility);

    VenueFacility find(Object id);

    List<VenueFacility> findAll();

    List<VenueFacility> findRange(int[] range);

    int count();
    
}
