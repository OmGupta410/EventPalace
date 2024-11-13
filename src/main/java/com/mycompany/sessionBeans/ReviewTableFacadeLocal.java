/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.ReviewTable;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Om Gupta
 */
@Local
public interface ReviewTableFacadeLocal {

    void create(ReviewTable reviewTable);

    void edit(ReviewTable reviewTable);

    void remove(ReviewTable reviewTable);

    ReviewTable find(Object id);

    List<ReviewTable> findAll();

    List<ReviewTable> findRange(int[] range);

    int count();
    
}
