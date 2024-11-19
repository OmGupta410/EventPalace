/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.sessionBeans;

import com.mycompany.entity.VenueGallery;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Om Gupta
 */
@Local
public interface VenueGalleryFacadeLocal {

    void create(VenueGallery venueGallery);

    void edit(VenueGallery venueGallery);

    void remove(VenueGallery venueGallery);

    VenueGallery find(Object id);

    List<VenueGallery> findAll();

    List<VenueGallery> findRange(int[] range);

    int count();
    
}
