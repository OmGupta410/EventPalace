/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventpalace.resources;

import com.mycompany.entity.UserRegistrationTable;
import com.mycompany.sessionBeans.UserRegistrationTableFacadeLocal;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    @EJB
    private UserRegistrationTableFacadeLocal userFacade;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserRegistrationTable loginData) {
        UserRegistrationTable user = userFacade.findByEmailAndPassword(loginData.getEmail(), loginData.getPassword());
        if (user != null) {
            return Response.ok(user).build();
        }
                                                                                                                                                    
        return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
    }
}
