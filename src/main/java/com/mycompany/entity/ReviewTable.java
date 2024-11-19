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
import javax.persistence.Lob;
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
@Table(name = "review_table")
@NamedQueries({
    @NamedQuery(name = "ReviewTable.findAll", query = "SELECT r FROM ReviewTable r"),
    @NamedQuery(name = "ReviewTable.findByReviewId", query = "SELECT r FROM ReviewTable r WHERE r.reviewId = :reviewId"),
    @NamedQuery(name = "ReviewTable.findByRating", query = "SELECT r FROM ReviewTable r WHERE r.rating = :rating")})
public class ReviewTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ReviewId")
    private Integer reviewId;
    @Lob
    @Size(max = 65535)
    @Column(name = "Comment")
    private String comment;
    @Column(name = "Rating")
    private Integer rating;
    @JoinColumn(name = "UserId", referencedColumnName = "User_id")
    @ManyToOne
    private UserRegistrationTable userId;
    @JoinColumn(name = "VenueId", referencedColumnName = "VenueId")
    @ManyToOne
    private VenueTable venueId;

    public ReviewTable() {
    }

    public ReviewTable(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewId != null ? reviewId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewTable)) {
            return false;
        }
        ReviewTable other = (ReviewTable) object;
        if ((this.reviewId == null && other.reviewId != null) || (this.reviewId != null && !this.reviewId.equals(other.reviewId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.ReviewTable[ reviewId=" + reviewId + " ]";
    }
    
}
