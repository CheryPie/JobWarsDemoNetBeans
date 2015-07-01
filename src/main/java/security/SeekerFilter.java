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
@WebFilter(filterName = "seekerFilter",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD,
            DispatcherType.ASYNC, DispatcherType.INCLUDE, DispatcherType.ASYNC},
        urlPatterns = {"/post_job.html", "/company_profile.html", "/company_page.html",
            "/applied_candidates.html","/rest/seeker_profile/*", "/rest/seeker_profile",
            "/rest/post/applied","/rest/post/all_posts"})
public class SeekerFilter implements Filter {

    @Inject
    private LoginSessionBean userBean;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponce = (HttpServletResponse) response;
        if (userBean.getCurrentLoginUser() != null && userBean.getCurrentLoginUser().getCompany() != null
                && userBean.getCurrentLoginUser().getCompany().getCompanyId() != null
                && userBean.getCurrentLoginUser().getRole().getLoginUserRoleId().equals(new Long("2"))) {
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
