/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CompanyDAO;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Company;
import model.LoginUser;
import session.LoginSessionBean;

/**
 *
 * @author Presko
 */
@WebServlet(name = "EditCompanySerlet", urlPatterns = {"/EditCompanySerlet"})
public class EditCompanySerlet extends HttpServlet {

    @EJB
    private CompanyDAO companyDAO;

    @Inject
    private LoginSessionBean loginBean;

    private static final String COMPANY_NAME = "companyName";
    private static final String EMAIL = "email";
    private static final String WEBSITE = "website";
    private static final String BULSTAT = "bulstat";
    private static final String DESCRIPTION = "description";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String companyName = request.getParameter(COMPANY_NAME);
        String email = request.getParameter(EMAIL);
        String website = request.getParameter(WEBSITE);
        String bulstat = request.getParameter(BULSTAT);
        String description = request.getParameter(DESCRIPTION);

        LoginUser user = loginBean.getCurrentLoginUser();
        Company company = user.getCompany();
        company.setName(companyName);
        company.setWebsite(website);
        company.setEmail(email);
        company.setBulstat(bulstat);
        company.setDescription(description);
        user.setCompany(company);
        loginBean.setCurrentLoginUser(user);
        companyDAO.edit(company);
        request.getRequestDispatcher("company_profile.html").forward(request, response);
    }

}
