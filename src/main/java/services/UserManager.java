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
    private LoginSessionBean currentLog;
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(LoginUser loginUser) {
        LoginUser currentUser = userDAO.autenticate(loginUser.getUserName(), loginUser.getPassword());
        if (currentUser == null) {
            return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();

        }
        currentLog.setCurrentLoginUser(currentUser);
        return Response.status(HttpURLConnection.HTTP_OK).build();
    }
    
    @POST
    @Path("register_company")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerCompany(LoginUser newCompany) {
        if (newCompany.getUserName() != null && newCompany.getPassword() != null && !userDAO.isExist(newCompany.getUserName())) {
            userDAO.createCompany(newCompany);
        }
    }
    
    @POST
    @Path("register_seeker")
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerSeeker(LoginUser newSeeker) {
        if (newSeeker.getUserName() != null && newSeeker.getPassword() != null && !userDAO.isExist(newSeeker.getUserName())) {
            userDAO.createJobSeeker(newSeeker);
        }
    }
    
    @GET
    @Produces("text/plain")
    public String getLoggedUserPage(){ 
        if(currentLog.getCurrentLoginUser().getRole().getLoginUserRoleId() == 1){
            return "post_for_seeker.html";
        }
        else{
            return "company_page.html";
        }
    }   
}
