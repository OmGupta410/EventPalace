/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Om Gupta
 */
@Entity
@Table(name = "venue_gallery")
@NamedQueries({
    @NamedQuery(name = "VenueGallery.findAll", query = "SELECT v FROM VenueGallery v"),
    @NamedQuery(name = "VenueGallery.findByGalleryId", query = "SELECT v FROM VenueGallery v WHERE v.galleryId = :galleryId"),
    @NamedQuery(name = "VenueGallery.findByBirthdayimage", query = "SELECT v FROM VenueGallery v WHERE v.birthdayimage = :birthdayimage"),
    @NamedQuery(name = "VenueGallery.findByMarriageimage", query = "SELECT v FROM VenueGallery v WHERE v.marriageimage = :marriageimage"),
    @NamedQuery(name = "VenueGallery.findByPartyimage", query = "SELECT v FROM VenueGallery v WHERE v.partyimage = :partyimage")})
public class VenueGallery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Gallery_Id")
    private Integer galleryId;
    @Size(max = 255)
    @Column(name = "Birthday_image")
    private String birthdayimage;
    @Size(max = 255)
    @Column(name = "Marriage_image")
    private String marriageimage;
    @Size(max = 255)
    @Column(name = "Party_image")
    private String partyimage;
    @JoinColumn(name = "VenueId", referencedColumnName = "VenueId")
    @ManyToOne
    private VenueTable venueId;

    public VenueGallery() {
    }

    public VenueGallery(Integer galleryId) {
        this.galleryId = galleryId;
    }

    public Integer getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(Integer galleryId) {
        this.galleryId = galleryId;
    }

    public String getBirthdayimage() {
        return birthdayimage;
    }

    public void setBirthdayimage(String birthdayimage) {
        this.birthdayimage = birthdayimage;
    }

    public String getMarriageimage() {
        return marriageimage;
    }

    public void setMarriageimage(String marriageimage) {
        this.marriageimage = marriageimage;
    }

    public String getPartyimage() {
        return partyimage;
    }

    public void setPartyimage(String partyimage) {
        this.partyimage = partyimage;
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
        hash += (galleryId != null ? galleryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VenueGallery)) {
            return false;
        }
        VenueGallery other = (VenueGallery) object;
        if ((this.galleryId == null && other.galleryId != null) || (this.galleryId != null && !this.galleryId.equals(other.galleryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.VenueGallery[ galleryId=" + galleryId + " ]";
    }
    
}
