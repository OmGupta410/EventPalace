package com.mycompany.eventpalace.resources;

import com.mycompany.entity.UserRegistrationTable;
import com.mycompany.sessionBeans.UserRegistrationTableFacade;

import com.mycompany.sessionBeans.UserRegistrationTableFacadeLocal;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author
 */
@Path("/app")
public class JakartaEE8Resource {
//    @GET
//    @Path(value="/hello")
//    public String hello(){return "hello world";}
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

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(UserRegistrationTable user) {

//        UserRegistrationTable newUsers=UserRegistrationTableFacade.signup(user);
        UserRegistrationTable newUsers = userFacade.signup(user);

        return Response.status(Response.Status.CREATED).entity(newUsers).build();
    }

}
