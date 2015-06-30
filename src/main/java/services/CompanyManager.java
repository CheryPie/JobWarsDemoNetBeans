/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.CompanyDAO;
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
import model.Company;
import model.LoginUser;
import session.LoginSessionBean;

/**
 *
 * @author Rosen
 */
@Stateless
@Path("company_profile")
public class CompanyManager {
    @EJB
    private CompanyDAO companyDAO;
    
    @Inject
    private LoginSessionBean loginBean;
    
    @POST
    @Path("editCompanyProfile")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doPost(Company company){
        LoginUser user = loginBean.getCurrentLoginUser();
        Company com = user.getCompany();
        com.setName(company.getName());
        com.setWebsite(company.getWebsite());
        com.setEmail(company.getEmail());
        com.setBulstat(company.getBulstat());
        com.setDescription(company.getDescription());
        user.setCompany(com);
        loginBean.setCurrentLoginUser(user);
        companyDAO.edit(com);
        return Response.ok().build();
    } 
    
    @GET
    @Path("load_company_profile")
    @Produces("application/json")
    public Response setCompanyProfileFieldsValue(){
        Company company = loginBean.getCurrentLoginUser().getCompany();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return Response.status(Response.Status.OK).entity(gson.toJson(company)).build();
    }
    
}
