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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Om Gupta
 */
@Entity
@Table(name = "payment_method")
@NamedQueries({
    @NamedQuery(name = "PaymentMethod.findAll", query = "SELECT p FROM PaymentMethod p"),
    @NamedQuery(name = "PaymentMethod.findByCardId", query = "SELECT p FROM PaymentMethod p WHERE p.cardId = :cardId"),
    @NamedQuery(name = "PaymentMethod.findByCardholdername", query = "SELECT p FROM PaymentMethod p WHERE p.cardholdername = :cardholdername"),
    @NamedQuery(name = "PaymentMethod.findByCardnumber", query = "SELECT p FROM PaymentMethod p WHERE p.cardnumber = :cardnumber"),
    @NamedQuery(name = "PaymentMethod.findByExpirationmonth", query = "SELECT p FROM PaymentMethod p WHERE p.expirationmonth = :expirationmonth"),
    @NamedQuery(name = "PaymentMethod.findByExpirationyear", query = "SELECT p FROM PaymentMethod p WHERE p.expirationyear = :expirationyear")})
public class PaymentMethod implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CardId")
    private Integer cardId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Card_holder_name")
    private String cardholdername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Card_number")
    private String cardnumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Expiration_month")
    private String expirationmonth;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Expiration_year")
    private String expirationyear;
    @JoinColumn(name = "PaymentId", referencedColumnName = "PaymentId")
    @ManyToOne
    private PaymentTable paymentId;

    public PaymentMethod() {
    }

    public PaymentMethod(Integer cardId) {
        this.cardId = cardId;
    }

    public PaymentMethod(Integer cardId, String cardholdername, String cardnumber, String expirationmonth, String expirationyear) {
        this.cardId = cardId;
        this.cardholdername = cardholdername;
        this.cardnumber = cardnumber;
        this.expirationmonth = expirationmonth;
        this.expirationyear = expirationyear;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardholdername() {
        return cardholdername;
    }

    public void setCardholdername(String cardholdername) {
        this.cardholdername = cardholdername;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getExpirationmonth() {
        return expirationmonth;
    }

    public void setExpirationmonth(String expirationmonth) {
        this.expirationmonth = expirationmonth;
    }

    public String getExpirationyear() {
        return expirationyear;
    }

    public void setExpirationyear(String expirationyear) {
        this.expirationyear = expirationyear;
    }

    public PaymentTable getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(PaymentTable paymentId) {
        this.paymentId = paymentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cardId != null ? cardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentMethod)) {
            return false;
        }
        PaymentMethod other = (PaymentMethod) object;
        if ((this.cardId == null && other.cardId != null) || (this.cardId != null && !this.cardId.equals(other.cardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.PaymentMethod[ cardId=" + cardId + " ]";
    }
    
}
