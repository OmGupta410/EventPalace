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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class LoginBean {

    private String email;
    private String password;
    private String role;

    // HttpServletRequest request;
    @EJB
    private UserRegistrationTableFacadeLocal userFacade;

    // method-2
    public String login(HttpServletRequest request) {
        System.out.println("Email: " + email);
        System.out.println("Role: " + role);
        System.out.println("Password: " + password);

        // Check if email, password, or role is empty
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty() || role == null
                || role.trim().isEmpty()) {
            return "login?faces-redirect=true&error=emptyFields"; // Optional error handling
        }

        // Ensure userFacade is initialized
        if (userFacade == null) {
            throw new IllegalStateException("UserFacade is not initialized");
        }

        // Authenticate user based on email and password
        UserRegistrationTable user = userFacade.findByEmailAndPassword(email, password);
        if (user == null) {
            return "login?faces-redirect=true&error=invalidCredentials"; // Handle invalid login credentials
        }

        // Check if the user's role matches the selected role
        if (user.getRoleid() == null || user.getRoleid().getRolename() == null
                || !role.equalsIgnoreCase(user.getRoleid().getRolename())) {
            return "login?faces-redirect=true&error=roleMismatch"; // Handle role mismatch
        }

        // Ensure HttpServletRequest is initialized
        if (request == null) {
            throw new IllegalStateException("HttpServletRequest is not initialized");
        }

        HttpSession session = request.getSession();
        if (session == null) {
            throw new IllegalStateException("Unable to create session");
        }

        // System.out.println("Current session attributes: userId=" +
        // session.getAttribute("userId") + ", role=" + session.getAttribute("role"));
        System.out.println("Checking userFacade: " + (userFacade != null));
        System.out.println("Checking request: " + (request != null));

        // Set attributes in the session
        session.setAttribute("userId", user.getUserid());
        // session.setAttribute("venueid", Venue);
        session.setAttribute("role", user.getRoleid());
        System.out.println("Session ID after login: " + session.getId());
        System.out.println("User ID set in session: " + session.getAttribute("userId"));
        System.out.println("userId is: " + session.getAttribute("userId"));
        System.out.println("role is: " + session.getAttribute("role"));

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userId", user.getUserid());

        // String id = session.getAttribute("id"); // Navigate to the appropriate page
        // based on role
        switch (role.toLowerCase()) {
            case "admin":
                return "adminDashboard";
            case "owner":
                return "ownerDashboard";
            case "user":
                return "clientDashboard";
            default:
                return "login?faces-redirect=true&error=invalidRole";
        }

    }

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String currentPage = externalContext.getRequestServletPath();

        // Redirect to login page if session or userRole attribute is missing, and user
        // is not already on login page
        if (!currentPage.equals("/Login.xhtml")) {
            HttpSession session = (HttpSession) externalContext.getSession(false);
            if (session == null || session.getAttribute("role") == null) {
                try {
                    externalContext.redirect("Login.xhtml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (session != null && session.getAttribute("role") != null) {
                try {
                    externalContext.redirect("index.xhtml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String logout() {
        System.out.println("logging out....");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            ExternalContext externalContext = facesContext.getExternalContext();

            try {
                if (externalContext.getSession(false) == null) {
                    System.out.println("No active session found.");

                }

                // Invalidate session
                if (externalContext.getSession(false) != null) {
                    // externalContext.invalidateSession();
                    System.out.println("Session invalidated successfully.");
                }

                // Redirect to login page
                externalContext.redirect(externalContext.getRequestContextPath() + "/Login.xhtml");
                facesContext.responseComplete(); // Mark the response as complete
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error while redirecting to login page: " + e.getMessage());
            }
        }
        return null; // Return null to prevent further navigation processing
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
