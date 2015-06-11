/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.JobSeekerDAO;
import java.io.IOException;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.JobSeeker;
import session.LoginSessionBean;

/**
 *
 * @author Presko
 */
@WebServlet(name = "EditJobSeekerServlet", urlPatterns = {"/EditJobSeekerServlet"})
public class EditJobSeekerServlet extends HttpServlet {

    @EJB
    private JobSeekerDAO seekerDAO;

    @Inject
    private LoginSessionBean loginBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    private static final String FULL_NAME = "fullName";
    private static final String PREFERED_POSITION = "position";
    private static final String SKILL_NAME = "skill";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String fullName = request.getParameter(FULL_NAME);
        String position = request.getParameter(PREFERED_POSITION);
        String skill = request.getParameter(SKILL_NAME);
        JobSeeker seeker = loginBean.getCurrentLoginUser().getJobSeeker();
        seeker.setFullName(fullName);
        seeker.setPosition(position);
        if (seeker != null && seeker.getJobSeekerId() != null
                && fullName != null && position != null) {
            seekerDAO.edit(seeker, skill);
            loginBean.reloadUser();
            request.getRequestDispatcher("seeker_profile.html").forward(request, response);
        }
    }


}
