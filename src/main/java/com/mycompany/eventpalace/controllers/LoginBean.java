/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventpalace.controllers;


import com.mycompany.entity.UserRegistrationTable;
import com.mycompany.sessionBeans.UserRegistrationTableFacadeLocal;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class LoginBean {
    private String email;
    private String password;
    private String role;

    @EJB
    private UserRegistrationTableFacadeLocal userFacade;

    public String login() {
//        System.out.println(email);
//        System.out.println(role);
//        System.out.println(password);
        
        
        // Check if email and password are not empty
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return "login?faces-redirect=true&error=emptyFields"; // Optional error handling
        }

        // Authenticate user based on email and password
        UserRegistrationTable user = userFacade.findByEmailAndPassword(email, password);

        if (user == null) {
            return "login?faces-redirect=true&error=invalidCredentials"; // Handle invalid login credentials
        }

        // Retrieve the user's role from the RoleTable
        if (user.getRoleid() == null) {
            return "login?faces-redirect=true&error=noRoleAssigned"; // Handle missing role error
        }

        this.role = user.getRoleid().getRolename(); // Assuming `RoleTable` has a `roleName` field

        // Check user role and navigate to the appropriate page
       if ("Admin".equalsIgnoreCase(role)) {
        return "adminDashboard"; // Matches faces-config.xml navigation case
    } else if ("Owner".equalsIgnoreCase(role)) {
        return "ownerDashboard";
    } else if ("Client".equalsIgnoreCase(role)) {
        return "clientDashboard";
    } else {
        return "login?faces-redirect=true&error=invalidCredentials";
    }

    9998766392@Omm

    }

    
    
    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String currentPage = externalContext.getRequestServletPath();

        // Redirect to login page if session or userRole attribute is missing, and user is not already on login page
        if (!currentPage.equals("/Login.xhtml")) {
            HttpSession session = (HttpSession) externalContext.getSession(false);
            if (session == null || session.getAttribute("userRole") == null) {
                try {
                    externalContext.redirect("Login.xhtml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        if (session != null) {
            session.invalidate(); // This invalidates the session
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Session invalidated successfully"));
            System.out.println("Session invalidated successfully.");
        }
        return "/Login.xhtml?faces-redirect=true";  // Redirect to login page
//        return "/Login.xhtml";
    }


    // Getters and Setters
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserRegistrationTableFacadeLocal getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserRegistrationTableFacadeLocal userFacade) {
        this.userFacade = userFacade;
    }
    
    
    
  

}

