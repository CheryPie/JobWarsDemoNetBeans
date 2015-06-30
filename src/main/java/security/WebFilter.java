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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.LoginSessionBean;

@javax.servlet.annotation.WebFilter(
        filterName = "loginFilter",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD,
            DispatcherType.ASYNC, DispatcherType.INCLUDE, DispatcherType.ASYNC},
        urlPatterns = {"/post_job.html", "/company_profile.html", "/company_page.html",
            "/applied_candidates.html", "/post_for_seeker.html", "/seeker_applied_posts.html",
            "/seeker_profile.html"},
        servletNames = {
            "AutocompleteServlet.java",
            "CompanyPostServlet.java",
            "EditCompanySerlet.java",
            "EditJobSeekerServlet.java",
            "LoginUserServlet.java",
            "LogoutServlet.java",
            "PostSeekerServlet.java",
            "RegisterCompanyServlet.java",
            "RegisterSeekerServlet.java",
            "SeekerPostServlet.java",
            "SkillPostServlet.java",
            "SkillSeekerServlet.java"})
public class WebFilter implements Filter {

    @Inject
    private LoginSessionBean userBean;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse responce,
            FilterChain chain) throws IOException, ServletException {
        if (!isHttpCall(request, responce)) {
            return;
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponce = (HttpServletResponse) responce;
        if (userBean.getCurrentLoginUser() != null && userBean.getCurrentLoginUser().getLoginUserId() != null) {
            chain.doFilter(request, responce);
        } else {
            httpResponce.sendRedirect(httpRequest.getContextPath() + "/login.html");
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    private boolean isHttpCall(ServletRequest req, ServletResponse resp) {
        return ((req instanceof HttpServletRequest) && (resp instanceof HttpServletResponse));
    }
}
