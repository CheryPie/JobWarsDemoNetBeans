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
import model.LoginUser;
import session.LoginSessionBean;

@javax.servlet.annotation.WebFilter(
        filterName = "loginFilter",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD,
            DispatcherType.ASYNC, DispatcherType.INCLUDE},
        urlPatterns = {"/post_job.html", "/company_profile.html", "/company_page.html",
            "/applied_candidates.html", "/post_for_seeker.html", "/seeker_applied_posts.html",
            "/seeker_profile.html", "/rest/seeker_profile/*", "/rest/seeker_profile",
            "/rest/post", "/rest/post/*", "/rest/company_profile", "/rest/company_profile/*"})
public class WebFilter implements Filter {

    @Inject
    private LoginSessionBean userBean;

    @Override
    public void destroy() {
    }

    private final String[] companyBlockedUrls = new String[]{"/post_for_seeker.html", "/seeker_applied_posts.html", "/seeker_profile.html", "/rest/seeker_profile/*", "/rest/seeker_profile"};
    private final String[] seekerBlockedUrls = new String[]{"/post_job.html", "/company_profile.html", "/company_page.html", "/applied_candidates.html", "/rest/company_profile", "/rest/company_profile/*"};
    private boolean isInSeekerBlockedUrls = false;
    private boolean isInCompanyBlockedUrls = false;

    @Override
    public void doFilter(ServletRequest request, ServletResponse responce,
            FilterChain chain) throws IOException, ServletException {
        if (!isHttpCall(request, responce)) {
            return;
        }
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponce = (HttpServletResponse) responce;
        System.err.println(httpRequest.getRequestURI());
        LoginUser user = userBean.getCurrentLoginUser();
        if (user != null && user.getLoginUserId() != null) {
            isInRestrictions(httpRequest.getRequestURI());
            if (user.getRole().getName().equals("ROLE_COMPANY") && isInCompanyBlockedUrls) {
                httpResponce.sendRedirect(httpRequest.getContextPath() + "/company_profile.html");
            } else if (user.getRole().getName().equals("ROLE_JOB_SEEKER") && isInSeekerBlockedUrls) {
                httpResponce.sendRedirect(httpRequest.getContextPath() + "/seeker_profile.html");
            } else {
                chain.doFilter(request, responce);
            }
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

    public void isInRestrictions(String uri) {
        uri = uri.replace("/JOBWARSDEMO", "");
        System.err.println(uri);
        for (String str : companyBlockedUrls) {
            if (str.contains(uri)) {
                isInCompanyBlockedUrls = true;
                System.err.println("company blocked");
                break;
            }
        }
        isInCompanyBlockedUrls = false;
        for (String str : seekerBlockedUrls) {
            if (str.contains(uri)) {
                System.err.println("seeker blocked");
                isInSeekerBlockedUrls = true;
            }
        }
        isInSeekerBlockedUrls = false;
    }
}
