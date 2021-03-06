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
@WebFilter(filterName = "seekerFilter",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD,
            DispatcherType.ASYNC, DispatcherType.INCLUDE, DispatcherType.ASYNC},
        urlPatterns = {"/post_job.html", "/company_profile.html", "/company_page.html",
            "/applied_candidates.html", "/rest/company_profile", "/rest/company_profile/*",
            "/rest/submitPost", "/rest/get_company_posts"})
public class SeekerFilter implements Filter {

    @Inject
    private LoginSessionBean userBean;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponce = (HttpServletResponse) response;
        LoginUser user = userBean.getCurrentLoginUser();
        if (user != null && user.getCompany() != null
                && user.getCompany().getCompanyId() != null
                && user.getRole().getName().equals("ROLE_COMPANY")) {
            chain.doFilter(request, response);
        } else {
            httpResponce.sendRedirect(httpRequest.getContextPath() + "/seeker_profile.html");
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

}
