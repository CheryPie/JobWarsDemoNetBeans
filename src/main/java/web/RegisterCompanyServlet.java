package web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginUser;
import dao.LoginUserDAO;

/**
 * Servlet implementation class RegisterCompanyServlet
 */
@WebServlet("/RegisterCompanyServlet")
public class RegisterCompanyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private LoginUserDAO loginDAO;

    public RegisterCompanyServlet() {
        super();
    }

    private static final String USER_NAME = "username";
    private static final String PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter(USER_NAME);
        String password = request.getParameter(PASSWORD);
        LoginUser user = new LoginUser();
        if (userName != null && password != null && !loginDAO.isExist(userName)) {
            user.setUserName(userName);
            user.setPassword(password);
            loginDAO.createCompany(user);
            request.getRequestDispatcher("login.html").forward(request, response);
        } else {
            request.getRequestDispatcher("register_company.html").forward(request, response);
        }
    }

}
