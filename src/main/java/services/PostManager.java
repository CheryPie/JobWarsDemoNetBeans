/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.JobPostDAO;
import dao.JobSeekerDAO;
import dao.SkillDAO;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
import utils.Helper;

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
    public void createJobPost(JobPost jobPost) {
        jobPost.setCompany(loginBean.getCurrentLoginUser().getCompany());
        List<String> skillsId = extractSkillId(jobPost.getDescription());
        postDAO.create(jobPost, skillsId);
    }

    @GET
    @Path("get_company_posts")
    @Produces("application/json")
    public Response getCompanyJobPosts() {
        Company company = loginBean.getCurrentLoginUser().getCompany();
        List<JobPost> posts = postDAO.findByCompany(company.getCompanyId().toString());
        return Response.status(Response.Status.OK).entity(Helper.toJson(posts)).build();
    }

    @GET
    @Path("{postId}")
    @Produces("application/json")
    public Response getAllSeekers(@PathParam("postId") String postId) {
        return Response.status(Response.Status.OK).entity(Helper.toJson(seekerDAO
                .findByPost(postId))).build();
    }

    public List<String> extractSkillId(String description) {
        List<Skill> skills = skillDAO.findAll();
        String[] descriptionArray = description.toLowerCase().split(" |,");
        Set<String> descriptionSet = new HashSet<>(Arrays.asList(descriptionArray));
        List<String> skillNamesLowerCase = descriptionSet.stream().map(s -> s.toLowerCase()).
                collect(Collectors.toList());
        List<String> skillIds = skills.stream().filter(s -> skillNamesLowerCase.
                contains(s.getName().toLowerCase())).map(s -> s.getSkillId().toString()).
                collect(Collectors.toList());
        return skillIds;
    }

    @GET
    @Path("applied")
    @Produces("application/json")
    public Response getAppliedSeekerPosts() {
        String seekerId = loginBean.getCurrentLoginUser().getJobSeeker()
                .getJobSeekerId().toString();
        List<JobPost> posts = postDAO.findByUser(seekerId);
        return Response.status(Response.Status.OK).entity(Helper
                .toJson(posts)).build();
    }

    @GET
    @Path("all_posts")
    @Produces("application/json")
    public Response getAllSeekerPosts() {
        JobSeeker jobSeeker = loginBean.getCurrentLoginUser().getJobSeeker();
        return Response.status(Response.Status.OK).entity(Helper.
                toJson(jobSeeker.getJobSeekerPosts())).build();
    }

}
