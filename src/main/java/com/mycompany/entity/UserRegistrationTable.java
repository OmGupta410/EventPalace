/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "user_registration_table")
@NamedQueries({
    @NamedQuery(name = "UserRegistrationTable.findAll", query = "SELECT u FROM UserRegistrationTable u"),
    @NamedQuery(name = "UserRegistrationTable.findByUserid", query = "SELECT u FROM UserRegistrationTable u WHERE u.userid = :userid"),
    @NamedQuery(name = "UserRegistrationTable.findByName", query = "SELECT u FROM UserRegistrationTable u WHERE u.name = :name"),
    @NamedQuery(name = "UserRegistrationTable.findByEmail", query = "SELECT u FROM UserRegistrationTable u WHERE u.email = :email"),
    @NamedQuery(name = "UserRegistrationTable.findByPassword", query = "SELECT u FROM UserRegistrationTable u WHERE u.password = :password"),
    @NamedQuery(name = "UserRegistrationTable.findByContactNo", query = "SELECT u FROM UserRegistrationTable u WHERE u.contactNo = :contactNo"),
    @NamedQuery(name = "UserRegistrationTable.findByStatus", query = "SELECT u FROM UserRegistrationTable u WHERE u.status = :status")})
public class UserRegistrationTable implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ContactNo")
    private String contactNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Status")
    private String status;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "User_id")
    private Integer userid;
    @JoinTable(name = "user_venue", joinColumns = {
        @JoinColumn(name = "User_id", referencedColumnName = "User_id")}, inverseJoinColumns = {
        @JoinColumn(name = "Venue_id", referencedColumnName = "VenueId")})
    @ManyToMany
    private Collection<VenueTable> venueTableCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<UserBookingTable> userBookingTableCollection;
    @JoinColumn(name = "Role_id", referencedColumnName = "Role_Id")
    @ManyToOne
    private RoleTable roleid;
    @OneToMany(mappedBy = "userId")
    private Collection<VenueTable> venueTableCollection1;
    @OneToMany(mappedBy = "userId")
    private Collection<ReviewTable> reviewTableCollection;

    public UserRegistrationTable() {
    }

    public UserRegistrationTable(Integer userid) {
        this.userid = userid;
    }

    public UserRegistrationTable(Integer userid, String name, String email, String password, String contactNo, String status) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contactNo = contactNo;
        this.status = status;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }


    public Collection<VenueTable> getVenueTableCollection() {
        return venueTableCollection;
    }

    public void setVenueTableCollection(Collection<VenueTable> venueTableCollection) {
        this.venueTableCollection = venueTableCollection;
    }

    public Collection<UserBookingTable> getUserBookingTableCollection() {
        return userBookingTableCollection;
    }

    public void setUserBookingTableCollection(Collection<UserBookingTable> userBookingTableCollection) {
        this.userBookingTableCollection = userBookingTableCollection;
    }

    public RoleTable getRoleid() {
        return roleid;
    }

    public void setRoleid(RoleTable roleid) {
        this.roleid = roleid;
    }

    public Collection<VenueTable> getVenueTableCollection1() {
        return venueTableCollection1;
    }

    public void setVenueTableCollection1(Collection<VenueTable> venueTableCollection1) {
        this.venueTableCollection1 = venueTableCollection1;
    }

    public Collection<ReviewTable> getReviewTableCollection() {
        return reviewTableCollection;
    }

    public void setReviewTableCollection(Collection<ReviewTable> reviewTableCollection) {
        this.reviewTableCollection = reviewTableCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRegistrationTable)) {
            return false;
        }
        UserRegistrationTable other = (UserRegistrationTable) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.UserRegistrationTable[ userid=" + userid + " ]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
