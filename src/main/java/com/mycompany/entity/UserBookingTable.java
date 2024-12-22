/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Om Gupta
 */
@Entity
@Table(name = "user_booking_table")
@NamedQueries({
    @NamedQuery(name = "UserBookingTable.findAll", query = "SELECT u FROM UserBookingTable u"),
    @NamedQuery(name = "UserBookingTable.findByBookingid", query = "SELECT u FROM UserBookingTable u WHERE u.bookingid = :bookingid"),
    @NamedQuery(name = "UserBookingTable.findByBookingdate", query = "SELECT u FROM UserBookingTable u WHERE u.bookingdate = :bookingdate"),
    @NamedQuery(name = "UserBookingTable.findByEventdate", query = "SELECT u FROM UserBookingTable u WHERE u.eventdate = :eventdate"),
    @NamedQuery(name = "UserBookingTable.findByShift", query = "SELECT u FROM UserBookingTable u WHERE u.shift = :shift"),
    @NamedQuery(name = "UserBookingTable.findByEventtype", query = "SELECT u FROM UserBookingTable u WHERE u.eventtype = :eventtype")})
public class UserBookingTable implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Booking_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Event_date")
    @Temporal(TemporalType.DATE)
    private Date eventdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Shift")
    private String shift;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Event_type")
    private String eventtype;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Booking_id")
    private Integer bookingid;
    @JoinColumn(name = "UserId", referencedColumnName = "User_id")
    @ManyToOne
    private UserRegistrationTable userId;
    @JoinColumn(name = "VenueId", referencedColumnName = "VenueId")
    @ManyToOne
    private VenueTable venueId;
    @OneToMany(mappedBy = "bookingId")
    private Collection<PaymentTable> paymentTableCollection;

    public UserBookingTable() {
    }

    public UserBookingTable(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public UserBookingTable(Integer bookingid, Date bookingdate, Date eventdate, String shift, String eventtype) {
        this.bookingid = bookingid;
        this.bookingdate = bookingdate;
        this.eventdate = eventdate;
        this.shift = shift;
        this.eventtype = eventtype;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public Date getBookingdate() {
        return bookingdate;
    }

    public void setBookingdate(Date bookingdate) {
        this.bookingdate = bookingdate;
    }

    public Date getEventdate() {
        return eventdate;
    }

    public void setEventdate(Date eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public UserRegistrationTable getUserId() {
        return userId;
    }

    public void setUserId(UserRegistrationTable userId) {
        this.userId = userId;
    }

    public VenueTable getVenueId() {
        return venueId;
    }

    public void setVenueId(VenueTable venueId) {
        this.venueId = venueId;
    }

    public Collection<PaymentTable> getPaymentTableCollection() {
        return paymentTableCollection;
    }

    public void setPaymentTableCollection(Collection<PaymentTable> paymentTableCollection) {
        this.paymentTableCollection = paymentTableCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookingid != null ? bookingid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserBookingTable)) {
            return false;
        }
        UserBookingTable other = (UserBookingTable) object;
        if ((this.bookingid == null && other.bookingid != null) || (this.bookingid != null && !this.bookingid.equals(other.bookingid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.UserBookingTable[ bookingid=" + bookingid + " ]";
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

}
