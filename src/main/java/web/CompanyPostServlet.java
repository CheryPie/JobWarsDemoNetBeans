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
import dao.SkillDAO;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import model.Company;
import model.Skill;
import session.LoginSessionBean;

@WebServlet("/CompanyPostServlet")
public class CompanyPostServlet extends HttpServlet {

    @EJB
    private SkillDAO skillDAO;

    private static final long serialVersionUID = 1L;

    @EJB
    private JobPostDAO postDAO;

    @Inject
    private LoginSessionBean loginBean;

    public CompanyPostServlet() {
        super();
    }

    private static final String COMPANY_ID = "companyId";
    private static final String DESCRIPTION = "description";
    private static final String SKILLS_ID = "skillsId";
    private static final String HEADER = "header";

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .create();
        response.setCharacterEncoding("UTF-8");
        String companyId = request.getParameter(COMPANY_ID);
        List<JobPost> posts = postDAO.findByCompany(companyId);
        String resource = gson.toJson(posts);
        response.setContentType("application/json");
        response.getWriter().print(resource);
        response.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter(DESCRIPTION);
        String header = request.getParameter(HEADER);
        List<String> skillsId = extractSkillId(description);
        Company company = loginBean.getCurrentLoginUser().getCompany();
        if (company != null && company.getCompanyId() != null
                && header != null && description != null) {
            JobPost post = new JobPost();
       //     post.setCompany(company);
            post.setDescription(description);
            post.setHeader(header);
            postDAO.create(post, skillsId);
            request.getRequestDispatcher("company_page.html").forward(request,
                    response);
        }
    }

    public List<String> extractSkillId(String description) {
        List<Skill> skills = skillDAO.findAll();
        String[] descriptionArray = description.toLowerCase().split(" |,");
        Set<String> descriptionSet = new HashSet<>(Arrays.asList(descriptionArray));
        List<String> skillIds = new ArrayList<>();
        for (String skillStr : descriptionSet) {
            for (Skill skill : skills) {
                if (skill.getName().toLowerCase().equals(skillStr.toLowerCase())) {
                    skillIds.add(skill.getSkillId().toString());
                }
            }
        }
        return skillIds;
    }

    public void getJobsString() throws IOException {
        Integer number = 5000;
//        for (; number <= 6000; number++) {
        String jobsWebSite = "http://www.jobs.bg/f277" + number;
        String description = getJobString(jobsWebSite);
        description = description.replaceAll("\\<[^>]*>", "");
        List<String> skillsId = extractSkillId(description);
        Company company = loginBean.getCurrentLoginUser().getCompany();
        if (company != null && company.getCompanyId() != null
                && description != null) {
            JobPost post = new JobPost();
       //     post.setCompany(company);
            post.setDescription(description.substring(0, 3999));
            post.setHeader("test");
            postDAO.create(post, skillsId);
        }
//        }
    }

    public String getJobString(String urlName) throws MalformedURLException, IOException {
        URL url = new URL(urlName);
        InputStream is = (InputStream) url.getContent();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
