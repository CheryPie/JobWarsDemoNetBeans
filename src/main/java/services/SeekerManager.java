/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.JobPostDAO;
import dao.JobSeekerDAO;
import java.util.List;
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
import model.JobPost;
import model.JobSeeker;
import model.LoginUser;
import session.LoginSessionBean;

/**
 *
 * @author Rosen
 */
@Stateless
@Path("seeker_profile")
public class SeekerManager {
    @EJB
    private JobSeekerDAO jobSeekerDAO;
    
    @Inject
    private LoginSessionBean loginBean;
    
    @POST
    @Path("editJobSeekerProfile")
    @Consumes(MediaType.APPLICATION_JSON)
    public void doPost(JobSeeker jobSeeker){
        LoginUser user = loginBean.getCurrentLoginUser();
        JobSeeker seeker = user.getJobSeeker();
        seeker.setFullName(jobSeeker.getFullName());
        seeker.setPosition(jobSeeker.getPosition());
        user.setJobSeeker(jobSeeker);
        loginBean.setCurrentLoginUser(user);
    }
    
    @POST
    @Path("editJobSeekerSkills")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editSeekerSkills(String skills){
        jobSeekerDAO.edit(loginBean.getCurrentLoginUser().getJobSeeker(), skills);
    }
    
    @GET
    @Path("load_seeker_profile")
    @Produces("application/json")
    public Response setSeekerFildsValue(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        JobSeeker jobSeeker = loginBean.getCurrentLoginUser().getJobSeeker();
        return Response.status(Response.Status.OK).entity(gson.toJson(jobSeeker)).build();
    }   
    
}
