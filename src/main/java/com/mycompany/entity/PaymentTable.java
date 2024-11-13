/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "payment_table")
@NamedQueries({
    @NamedQuery(name = "PaymentTable.findAll", query = "SELECT p FROM PaymentTable p"),
    @NamedQuery(name = "PaymentTable.findByPaymentId", query = "SELECT p FROM PaymentTable p WHERE p.paymentId = :paymentId"),
    @NamedQuery(name = "PaymentTable.findByAdvancepayment", query = "SELECT p FROM PaymentTable p WHERE p.advancepayment = :advancepayment"),
    @NamedQuery(name = "PaymentTable.findByPaymentstatus", query = "SELECT p FROM PaymentTable p WHERE p.paymentstatus = :paymentstatus"),
    @NamedQuery(name = "PaymentTable.findByDatetime", query = "SELECT p FROM PaymentTable p WHERE p.datetime = :datetime")})
public class PaymentTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PaymentId")
    private Integer paymentId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "Advance_payment")
    private BigDecimal advancepayment;
    @Size(max = 50)
    @Column(name = "Payment_status")
    private String paymentstatus;
    @Column(name = "Date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @JoinColumn(name = "BookingId", referencedColumnName = "Booking_id")
    @ManyToOne
    private UserBookingTable bookingId;
    @JoinColumn(name = "VenueId", referencedColumnName = "VenueId")
    @ManyToOne
    private VenueTable venueId;
    @OneToMany(mappedBy = "paymentId")
    private Collection<PaymentMethod> paymentMethodCollection;

    public PaymentTable() {
    }

    public PaymentTable(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentTable(Integer paymentId, BigDecimal advancepayment) {
        this.paymentId = paymentId;
        this.advancepayment = advancepayment;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public BigDecimal getAdvancepayment() {
        return advancepayment;
    }

    public void setAdvancepayment(BigDecimal advancepayment) {
        this.advancepayment = advancepayment;
    }

    public String getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public UserBookingTable getBookingId() {
        return bookingId;
    }

    public void setBookingId(UserBookingTable bookingId) {
        this.bookingId = bookingId;
    }

    public VenueTable getVenueId() {
        return venueId;
    }

    public void setVenueId(VenueTable venueId) {
        this.venueId = venueId;
    }

    public Collection<PaymentMethod> getPaymentMethodCollection() {
        return paymentMethodCollection;
    }

    public void setPaymentMethodCollection(Collection<PaymentMethod> paymentMethodCollection) {
        this.paymentMethodCollection = paymentMethodCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentId != null ? paymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentTable)) {
            return false;
        }
        PaymentTable other = (PaymentTable) object;
        if ((this.paymentId == null && other.paymentId != null) || (this.paymentId != null && !this.paymentId.equals(other.paymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.PaymentTable[ paymentId=" + paymentId + " ]";
    }
    
}
