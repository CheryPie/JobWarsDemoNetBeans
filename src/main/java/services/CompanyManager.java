/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

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
import utils.Helper;

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
    public Response editCompany(Company company) {
        LoginUser user = loginBean.getCurrentLoginUser();
        Company currentCompany = user.getCompany();
        company.setCompanyId(currentCompany.getCompanyId());
        user.setCompany(company);
        loginBean.setCurrentLoginUser(user);
        companyDAO.edit(company);
        return Response.ok().build();
    }

    @GET
    @Path("load_company_profile")
    @Produces("application/json")
    public Response getCompanyProfileFieldsValue() {
        Company company = loginBean.getCurrentLoginUser().getCompany();
        return Response.status(Response.Status.OK).entity(Helper.toJson(company)).build();
    }

}
