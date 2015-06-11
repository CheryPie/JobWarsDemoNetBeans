package web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginUser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.LoginUserDAO;
import javax.inject.Inject;
import session.LoginSessionBean;

/**
 * Servlet implementation class LoginUserServlet
 */
@WebServlet("/LoginUserServlet")
public class LoginUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private LoginUserDAO loginDAO;

    @Inject
    private LoginSessionBean loginBean;

    public LoginUserServlet() {
        super();
    }

    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        loginBean.reloadUser();
        response.setCharacterEncoding("UTF-8");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .create();
        String resource = null;
        LoginUser user = loginBean.getCurrentLoginUser();
        if (user.getCompany() != null) {
            resource = gson.toJson(user.getCompany());
        } else if (user.getJobSeeker() != null) {
            resource = gson.toJson(user.getJobSeeker());
        }
        response.setContentType("application/json");
        response.getWriter().print(resource);
        response.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter(USER_NAME);
        String password = request.getParameter(PASSWORD);
        if (userName != null && password != null) {
            LoginUser user = loginDAO.autenticate(userName, password);
            if (user != null) {
                loginBean.setCurrentLoginUser(user);
                if (user.getCompany() != null
                        && user.getCompany().getCompanyId() != null) {
                    request.getRequestDispatcher("company_page.html").forward(
                            request, response);
                    return;
                } else if (user.getJobSeeker() != null
                        && user.getJobSeeker().getJobSeekerId() != null) {
                    request.getRequestDispatcher("post_for_seeker.html").forward(
                            request, response);
                    return;
                }
            } else {
                request.getRequestDispatcher("login.html").forward(request,
                        response);
                return;
            }
        } else {
            request.getRequestDispatcher("login.html").forward(request,
                    response);
            return;
        }
        request.getRequestDispatcher("login.html").forward(request, response);
    }
}
