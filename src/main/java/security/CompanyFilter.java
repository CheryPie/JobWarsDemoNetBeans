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
import model.LoginUser;
import session.LoginSessionBean;

/**
 *
 * @author Presko
 */
@WebFilter(filterName = "companyFilter",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD,
            DispatcherType.ASYNC, DispatcherType.INCLUDE, DispatcherType.ASYNC},
        urlPatterns = {"/post_for_seeker.html", "/seeker_applied_posts.html",
            "/seeker_profile.html", "/rest/seeker_profile/*", "/rest/seeker_profile",
            "/rest/post/applied", "/rest/post/all_posts"})
public class CompanyFilter implements Filter {

    @Inject
    private LoginSessionBean userBean;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponce = (HttpServletResponse) response;
        LoginUser user = userBean.getCurrentLoginUser();
        if (user != null && user.getJobSeeker() != null
                && user.getJobSeeker().getJobSeekerId() != null
                && user.getRole().getName().equals("ROLE_JOB_SEEKER")) {
            chain.doFilter(request, response);
        } else {
            httpResponce.sendRedirect(httpRequest.getContextPath() + "/company_profile.html");
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

}
