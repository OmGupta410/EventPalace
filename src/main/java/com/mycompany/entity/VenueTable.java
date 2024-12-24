/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Om Gupta
 */
@Entity
@Table(name = "venue_table")
@NamedQueries({
    @NamedQuery(name = "VenueTable.findAll", query = "SELECT v FROM VenueTable v"),
    @NamedQuery(name = "VenueTable.findByVenueId", query = "SELECT v FROM VenueTable v WHERE v.venueId = :venueId"),
    @NamedQuery(name = "VenueTable.findByVenuename", query = "SELECT v FROM VenueTable v WHERE v.venuename = :venuename"),
    @NamedQuery(name = "VenueTable.findByLocation", query = "SELECT v FROM VenueTable v WHERE v.location = :location"),
    @NamedQuery(name = "VenueTable.findByFinalprice", query = "SELECT v FROM VenueTable v WHERE v.finalprice = :finalprice"),
    @NamedQuery(name = "VenueTable.findByBookingadvanceprice", query = "SELECT v FROM VenueTable v WHERE v.bookingadvanceprice = :bookingadvanceprice"),
    @NamedQuery(name = "VenueTable.findByCapacity", query = "SELECT v FROM VenueTable v WHERE v.capacity = :capacity")})
public class VenueTable implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Venue_name")
    private String venuename;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 255)
    @Column(name = "Location")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Lob()
    @Size(min = 1, max = 65535)
    @Column(name = "Description")
    private String description;
    @Lob()
    @Column(name = "Image")
    private byte[] image;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Final_price")
    private BigDecimal finalprice;
    @Basic(optional = false)
    @NotNull()
    @Column(name = "Booking_advance_price")
    private BigDecimal bookingadvanceprice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Capacity")
    private int capacity;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "VenueId")
    private Integer venueId;
    @ManyToMany(mappedBy = "venueTableCollection")
    private Collection<UserRegistrationTable> userRegistrationTableCollection;
    @OneToMany(mappedBy = "venueId")
    private Collection<UserBookingTable> userBookingTableCollection;
    @OneToMany(mappedBy = "venueId")
    private Collection<PaymentTable> paymentTableCollection;
    @OneToMany(mappedBy = "venueId")
    private Collection<VenueFacility> venueFacilityCollection;
    @JoinColumn(name = "UserId", referencedColumnName = "User_id")
    @ManyToOne
    private UserRegistrationTable userId;
    @OneToMany(mappedBy = "venueId")
    private Collection<ReviewTable> reviewTableCollection;
    @OneToMany(mappedBy = "venueId")
    private Collection<VenueGallery> venueGalleryCollection;
    

    public VenueTable() {
    }

    public VenueTable(Integer venueId) {
        this.venueId = venueId;
    }

    public VenueTable(Integer venueId, String venuename, String location, String description, BigDecimal finalprice, BigDecimal bookingadvanceprice, int capacity) {
        this.venueId = venueId;
        this.venuename = venuename;
        this.location = location;
        this.description = description;
        this.finalprice = finalprice;
        this.bookingadvanceprice = bookingadvanceprice;
        this.capacity = capacity;
    }

    public Integer getVenueId() {
        return venueId;
    }

    public void setVenueId(Integer venueId) {
        this.venueId = venueId;
    }

    public String getVenuename() {
        return venuename;
    }

    public void setVenuename(String venuename) {
        this.venuename = venuename;
    }


    public BigDecimal getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(BigDecimal finalprice) {
        this.finalprice = finalprice;
    }

    public BigDecimal getBookingadvanceprice() {
        return bookingadvanceprice;
    }

    public void setBookingadvanceprice(BigDecimal bookingadvanceprice) {
        this.bookingadvanceprice = bookingadvanceprice;
    }


    public Collection<UserRegistrationTable> getUserRegistrationTableCollection() {
        return userRegistrationTableCollection;
    }

    public void setUserRegistrationTableCollection(Collection<UserRegistrationTable> userRegistrationTableCollection) {
        this.userRegistrationTableCollection = userRegistrationTableCollection;
    }

    public Collection<UserBookingTable> getUserBookingTableCollection() {
        return userBookingTableCollection;
    }

    public void setUserBookingTableCollection(Collection<UserBookingTable> userBookingTableCollection) {
        this.userBookingTableCollection = userBookingTableCollection;
    }

    public Collection<PaymentTable> getPaymentTableCollection() {
        return paymentTableCollection;
    }

    public void setPaymentTableCollection(Collection<PaymentTable> paymentTableCollection) {
        this.paymentTableCollection = paymentTableCollection;
    }

    public Collection<VenueFacility> getVenueFacilityCollection() {
        return venueFacilityCollection;
    }

    public void setVenueFacilityCollection(Collection<VenueFacility> venueFacilityCollection) {
        this.venueFacilityCollection = venueFacilityCollection;
    }

    public UserRegistrationTable getUserId() {
        return userId;
    }

    public void setUserId(UserRegistrationTable userId) {
        this.userId = userId;
    }

    public Collection<ReviewTable> getReviewTableCollection() {
        return reviewTableCollection;
    }

    public void setReviewTableCollection(Collection<ReviewTable> reviewTableCollection) {
        this.reviewTableCollection = reviewTableCollection;
    }

    public Collection<VenueGallery> getVenueGalleryCollection() {
        return venueGalleryCollection;
    }

    public void setVenueGalleryCollection(Collection<VenueGallery> venueGalleryCollection) {
        this.venueGalleryCollection = venueGalleryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (venueId != null ? venueId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VenueTable)) {
            return false;
        }
        VenueTable other = (VenueTable) object;
        if ((this.venueId == null && other.venueId != null) || (this.venueId != null && !this.venueId.equals(other.venueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.VenueTable[ venueId=" + venueId + " ]";
    }

//    public String getVenuename() {
//        return venuename;
//    }
//
//    public void setVenuename(String venuename) {
//        this.venuename = venuename;
//    }

 

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

   
  

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
}
