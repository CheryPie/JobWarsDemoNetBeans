/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.LoginSessionBean;

/**
 *
 * @author Presko
 */
@WebFilter(filterName = "companyFilter",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD,
            DispatcherType.ASYNC, DispatcherType.INCLUDE, DispatcherType.ASYNC},
        urlPatterns = {"/post_for_seeker.html", "/seeker_applied_posts.html",
            "/seeker_profile.html"},
        servletNames = {
            "CompanyPostServlet.java",
            "EditCompanySerlet.java",
            "LoginUserServlet.java",
            "LogoutServlet.java",
            "SeekerPostServlet.java",
            "SkillPostServlet.java",
            "SkillSeekerServlet.java"})
public class CompanyFilter implements Filter {

    @Inject
    private LoginSessionBean userBean;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponce = (HttpServletResponse) response;
        if (userBean.getCurrentLoginUser() != null && userBean.getCurrentLoginUser().getJobSeeker()!= null
                && userBean.getCurrentLoginUser().getJobSeeker().getJobSeekerId()!= null
                && userBean.getCurrentLoginUser().getRole().getLoginUserRoleId().equals(new Long("1"))) {
            chain.doFilter(request, response);
        } else {
            httpResponce.sendRedirect(httpRequest.getContextPath() + "/login.html");
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

}
