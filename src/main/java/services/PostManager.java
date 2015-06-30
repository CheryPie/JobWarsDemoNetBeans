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
import dao.SkillDAO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import model.Company;
import model.JobPost;
import model.JobSeeker;
import model.Skill;
import session.LoginSessionBean;

/**
 *
 * @author Rosen
 */
@Stateless
@Path("post")
public class PostManager {
    @Inject
    private LoginSessionBean loginBean;
    
    @EJB
    private JobSeekerDAO seekerDAO;
    
    @EJB
    private SkillDAO skillDAO;
    
    @EJB
    private JobPostDAO postDAO;
    
    @POST
    @Path("submitPost")
    @Consumes("application/json")
    public void crateJobPost(JobPost jobPost) {  
        jobPost.setCompany(loginBean.getCurrentLoginUser().getCompany());
        List<String> skillsId = extractSkillId(jobPost.getDescription());
        System.err.println(jobPost.getDescription() + jobPost.getHeader());
        postDAO.create(jobPost, skillsId);
    }
    
    @GET
    @Path("get_company_posts")
    @Produces("application/json")
    public Response getCompanyJobPosts(){
        Company company = loginBean.getCurrentLoginUser().getCompany();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return Response.status(Response.Status.OK).entity(gson.toJson(company.getJobPosts())).build();
    }
    
    @GET
    @Path("{postId}")
    @Produces("application/json")
    public Response getAllSeekers(@PathParam("postId") String postId){ 
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return Response.status(Response.Status.OK).entity(gson.toJson(seekerDAO.findByPost(postId))).build();
    }
    
    public List<String> extractSkillId(String description) {
        List<Skill> skills = skillDAO.findAll();
        String[] descriptionArray = description.toLowerCase().split(" |,");
        Set<String> descriptionSet = new HashSet<>(Arrays.asList(descriptionArray));
        List<String> skillIds = new ArrayList<>();
        for (String skillStr : descriptionSet) {
            for (Skill skill : skills) {
                if (skill.getName().toLowerCase().equals(skillStr.toLowerCase())) {
                    skillIds.add(skill.getSkillId().toString());
                }
            }
        }
        return skillIds;
    }
    
        @GET
    @Path("applied")
    @Produces("application/json")
    public Response getAppliedSeekerPosts(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .create();
        String seekerId = loginBean.getCurrentLoginUser().getJobSeeker().getJobSeekerId().toString();
        List<JobPost> posts = null;
        posts = postDAO.findByUser(seekerId);
        if(posts!=null && posts.size()==0){
            posts=postDAO.findAll();
        }
        return Response.status(Response.Status.OK).entity(gson.toJson(posts)).build();
    }
    
    @GET
    @Path("all_posts")
    @Produces("application/json")
    public Response getAllSeekerPosts(){
        JobSeeker jobSeeker = loginBean.getCurrentLoginUser().getJobSeeker();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .create();
        return Response.status(Response.Status.OK).entity(gson.toJson(jobSeeker.getJobSeekerPosts())).build();
    }
    
}
