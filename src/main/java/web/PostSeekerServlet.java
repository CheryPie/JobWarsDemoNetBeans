/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.JobSeekerDAO;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.JobSeeker;

/**
 *
 * @author Presko
 */
@WebServlet(name = "PostSeekerServlet", urlPatterns = {"/PostSeekerServlet"})
public class PostSeekerServlet extends HttpServlet {

    @EJB
    private JobSeekerDAO seekerDAO;

    private static final String POST_ID = "postId";

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String postId = request.getParameter(POST_ID);
        List<JobSeeker> seekers = seekerDAO.findByPost(postId);
        String resource = gson.toJson(seekers);
        response.setContentType("application/json");
        response.getWriter().print(resource);
        response.getWriter().flush();
    }

}
