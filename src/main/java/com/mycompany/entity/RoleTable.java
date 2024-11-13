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
@Table(name = "role_table")
@NamedQueries({
    @NamedQuery(name = "RoleTable.findAll", query = "SELECT r FROM RoleTable r"),
    @NamedQuery(name = "RoleTable.findByRoleId", query = "SELECT r FROM RoleTable r WHERE r.roleId = :roleId"),
    @NamedQuery(name = "RoleTable.findByRolename", query = "SELECT r FROM RoleTable r WHERE r.rolename = :rolename")})

public class RoleTable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Role_Id")
    private Integer roleId;

    @Column(name = "Role_name")
    private String rolename;  // Name of the role (Admin, User, etc.)

    
    private Collection<UserRegistrationTable> userRegistrationTableCollection;
    
    public RoleTable() {
    }

    public RoleTable(Integer roleId) {
        this.roleId = roleId;
    }

    public RoleTable(Integer roleId, String rolename) {
        this.roleId = roleId;
        this.rolename = rolename;
    }

    public Integer getRoleid() {
        return roleId;
    }

    public void setRoleid(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Collection<UserRegistrationTable> getUserRegistrationTableCollection() {
        return userRegistrationTableCollection;
    }

    public void setUserRegistrationTableCollection(Collection<UserRegistrationTable> userRegistrationTableCollection) {
        this.userRegistrationTableCollection = userRegistrationTableCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleTable)) {
            return false;
        }
        RoleTable other = (RoleTable) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.entity.RoleTable[ roleId=" + roleId + " ]";
    }
    
}
