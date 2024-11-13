/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Om Gupta
 */
@Entity
@Table(name = "venue_facility")
@NamedQueries({
    @NamedQuery(name = "VenueFacility.findAll", query = "SELECT v FROM VenueFacility v"),
    @NamedQuery(name = "VenueFacility.findByFacilityid", query = "SELECT v FROM VenueFacility v WHERE v.facilityid = :facilityid"),
    @NamedQuery(name = "VenueFacility.findByNoofRooms", query = "SELECT v FROM VenueFacility v WHERE v.noofRooms = :noofRooms"),
    @NamedQuery(name = "VenueFacility.findByFoodtype", query = "SELECT v FROM VenueFacility v WHERE v.foodtype = :foodtype"),
    @NamedQuery(name = "VenueFacility.findByParking", query = "SELECT v FROM VenueFacility v WHERE v.parking = :parking")})
public class VenueFacility implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Facility_id")
    private Integer facilityid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "No_of_Rooms")
    private int noofRooms;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Food_type")
    private String foodtype;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Parking")
    private boolean parking;
    @JoinColumn(name = "VenueId", referencedColumnName = "VenueId")
    @ManyToOne
    private VenueTable venueId;

    public VenueFacility() {
    }

    public VenueFacility(Integer facilityid) {
        this.facilityid = facilityid;
    }

    public VenueFacility(Integer facilityid, int noofRooms, String foodtype, boolean parking) {
        this.facilityid = facilityid;
        this.noofRooms = noofRooms;
        this.foodtype = foodtype;
        this.parking = parking;
    }

    public Integer getFacilityid() {
        return facilityid;
    }

    public void setFacilityid(Integer facilityid) {
        this.facilityid = facilityid;
    }

    public int getNoofRooms() {
        return noofRooms;
    }

    public void setNoofRooms(int noofRooms) {
        this.noofRooms = noofRooms;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public boolean getParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public VenueTable getVenueId() {
        return venueId;
    }

    public void setVenueId(VenueTable venueId) {
        this.venueId = venueId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facilityid != null ? facilityid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VenueFacility)) {
            return false;
        }
        VenueFacility other = (VenueFacility) object;
        if ((this.facilityid == null && other.facilityid != null) || (this.facilityid != null && !this.facilityid.equals(other.facilityid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.VenueFacility[ facilityid=" + facilityid + " ]";
    }
    
}
