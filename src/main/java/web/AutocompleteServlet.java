/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.SkillDAO;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Skill;

/**
 *
 * @author Presko
 */
@WebServlet(name = "AutocompleteServlet", urlPatterns = {"/AutocompleteServlet"})
public class AutocompleteServlet extends HttpServlet {

    @EJB
    private SkillDAO skillDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try {
            String term = request.getParameter("term");
            List<Skill> list = skillDAO.findByName(term);
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String searchList = gson.toJson(list);
            response.getWriter().write(searchList);
            response.getWriter().flush();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


}
