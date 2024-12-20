package com.mycompany.eventpalace.controllers;

import com.mycompany.entity.RoleTable;
import com.mycompany.entity.UserRegistrationTable;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.registry.infomodel.User;

/**
 * RegistrationBean for user registration via REST API.
 */
@Named(value = "RegistrationBean")
@SessionScoped
public class RegistrationBean implements Serializable {

    private String name;
    private String email;
    private String password;
    private String contactno;
    private String successMessage;
    private String errorMessage;
    private String status;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getters and Setters
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

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

//    public String signup() {
//        System.out.println("itno the insert");
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target("http://localhost:8080/EventPalace/resources/app/signup");
//
////        way-1 insert data using REST
////        System.out.println("insertName:- " + insertName);
//        UserRegistrationTable user = new UserRegistrationTable();
//        user.setName(name);
//        user.setEmail(email);
//        user.setPassword(password);
//        user.setContactNo(contactno);
//        user.setStatus(status);
//
//        Response response = target.request(MediaType.APPLICATION_JSON)
//                .post(Entity.entity(user, MediaType.APPLICATION_JSON));
//
////        way- 2 insert data using EJB 
////        Student student = new Student();
////        student.setName(name);
////        studentEJB.signUp(student);
////        stdList.add(student);
//        if (response.getStatus() == 201) {
//            System.out.println("Student inserted successfully!");
//        } else {
//            System.out.println("Error inserting student: " + response.getStatus());
//        }
//
//        response.close();
//        client.close();
//
//        this.name = "";
////        this.user = new UserRegistrationTable();
//        return "Login";
//    }
    
    
     public String signup() {
    // Creating the client and target URL for REST API
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8080/EventPalace/resources/app/signup");

    // Creating User object from form data
    UserRegistrationTable user = new UserRegistrationTable();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    user.setContactNo(contactno);
    user.setStatus(status);

    // Set the correct role for the user based on input
    RoleTable userRole = new RoleTable(); // Create a RoleTable object
    
    // Assign role based on user input
    if ("admin".equalsIgnoreCase(role)) {
        userRole.setRoleId(1);  // Role 1 for Admin
    } else if ("owner".equalsIgnoreCase(role)) {
        userRole.setRoleId(2);  // Role 2 for Owner
    } else if ("user".equalsIgnoreCase(role)) {
        userRole.setRoleId(3);  // Role 3 for User
    }
    
    // Set the role of the user
    user.setRoleid(userRole);

    // Sending the data to REST API for insertion into the database
    Response response = target.request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(user, MediaType.APPLICATION_JSON));

    // Checking the response status
    if (response.getStatus() == 201) {
        System.out.println("User registered successfully!");
        // After successful insertion, reset fields and redirect to login page
        name = "";
        email = "";
        password = "";
        contactno = "";
        status = "";
        return "Login";  // Redirecting to login page
    } else {
        System.out.println("Error inserting user: " + response.getStatus());
        return null;  // Stay on the current page if there is an error
    }
}


}

