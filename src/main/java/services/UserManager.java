/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.LoginUserDAO;
import java.net.HttpURLConnection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.LoginUser;
import session.LoginSessionBean;

/**
 *
 * @author Bori
 */ 
@Stateless
@Path("user")
public class UserManager {

    @EJB
    LoginUserDAO userDAO;
    
    @Inject
    LoginSessionBean currentLog;
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(LoginUser loginUser) {
        
        System.out.println("login");
        System.out.println(loginUser.getUserName() + loginUser.getPassword());
        
        LoginUser currentUser = userDAO.autenticate(loginUser.getUserName(), loginUser.getPassword());

        if (currentUser == null) {
            System.out.println("Unauth.");
            return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();

        }
        currentLog.setCurrentLoginUser(currentUser);
        System.out.println(currentUser.toString());
        return Response.status(HttpURLConnection.HTTP_OK).build();
    }
    
    @POST
    @Path("register_company")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerCompany(LoginUser newCompany) {
        System.out.println("Register company rest");
        System.out.println(newCompany.getUserName() + " " + newCompany.getPassword());
        if (newCompany.getUserName() != null && newCompany.getPassword() != null && !userDAO.isExist(newCompany.getUserName())) {
            userDAO.createCompany(newCompany);
        }
    }
    
    @POST
    @Path("register_seeker")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerSeeker(LoginUser newSeeker) {
        System.out.println("Register seeker rest");
        System.out.println(newSeeker.getUserName() + " " + newSeeker.getPassword());
        if (newSeeker.getUserName() != null && newSeeker.getPassword() != null && !userDAO.isExist(newSeeker.getUserName())) {
            userDAO.createJobSeeker(newSeeker);
        }
    }
    
    @GET
    @Produces("text/plain")
    public String getLoggedUserPage(){
        System.out.println("getLoggedUser");
        if(currentLog.getCurrentLoginUser().getRole().getLoginUserRoleId() == 1){
            return "post_for_seeker.html";
        }
        else{
            return "company_page.html";
        }
    }   
}
