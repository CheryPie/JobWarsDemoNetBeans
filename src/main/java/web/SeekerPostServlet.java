package web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.JobPost;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.JobPostDAO;
import dao.JobSeekerPostDAO;
import javax.inject.Inject;
import model.JobSeeker;
import model.JobSeekerPost;
import session.LoginSessionBean;

/**
 * Servlet implementation class SeekerPostServlet
 */
@WebServlet("/SeekerPostServlet")
public class SeekerPostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private JobPostDAO postDAO;
    @EJB
    private JobSeekerPostDAO applyDAO;
    @Inject
    private LoginSessionBean loginBean;

    public SeekerPostServlet() {
        super();
    }

    private static final String SEEKER_ID = "seekerId";
    private static final String POST_INDEX = "post_index";

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .create();
        String seekerId = loginBean.getCurrentLoginUser().getJobSeeker().getJobSeekerId().toString();
        List<JobPost> posts = null;
        posts = postDAO.findByUser(seekerId);
        if(posts!=null && posts.size()==0){
            posts=postDAO.findAll();
        }
        String resource = gson.toJson(posts);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().print(resource);
        response.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            JobSeeker seeker = loginBean.getCurrentLoginUser().getJobSeeker();
            String post_index = request.getParameter(POST_INDEX);
            JobPost post = postDAO.findByUser(seeker.getJobSeekerId().toString()).get(new Integer(post_index));
            if (seeker != null && post != null) {
                JobSeekerPost rel = new JobSeekerPost(seeker, post);
                applyDAO.apply(rel);
                request.getRequestDispatcher("post_for_seeker.html").forward(
                        request, response);
            }
        } catch (NumberFormatException | ServletException | IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
