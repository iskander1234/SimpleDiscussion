package com.example.SimpleDiscussion.Servlet;

import com.example.SimpleDiscussion.dao.PublicationDAO;
import com.example.SimpleDiscussion.model.Publication;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "/", value = "/")
public class PublicationServlet extends HttpServlet {
    private PublicationDAO publicationDAO;

    public PublicationServlet() {
        this.publicationDAO = new PublicationDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action){
            case "/new":
                showNewForm(request,response);
                break;
            case "/insert":
                try {
                    insertPublication(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "/delete":
                try {
                    deletePublication(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "/edit":
                try {
                    showEditForm(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "/update":
                try {
                    updatePublication(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                //handle list
                listPublication(request,response);
                break;
        }
    }

    private void listPublication(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Publication> listPublication = publicationDAO.selectAllPublications();
        request.setAttribute("listPublication", listPublication);
        RequestDispatcher dispatcher =request.getRequestDispatcher("publication-list.jsp");
        dispatcher.forward(request,response);
    }

    private void updatePublication(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String comments = request.getParameter("comments");

        Publication publication = new Publication(id, name,description,comments);
        publicationDAO.updatePublications(publication);
        response.sendRedirect("list");

    }

    private void deletePublication(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        publicationDAO.deletePublication(id);
        response.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Publication existingPublication = publicationDAO.selectPublications(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("publication-form.jsp");
        request.setAttribute("publication", existingPublication);
        dispatcher.forward(request,response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       RequestDispatcher dispatcher = request.getRequestDispatcher("publication-form.jsp");
       dispatcher.forward(request,response);
    }

    private void insertPublication(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException{
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String comments = request.getParameter("comments");
        Publication newPublication = new Publication(name,description,comments);
        publicationDAO.insertPublications(newPublication);
        response.sendRedirect("list");
    }
}
