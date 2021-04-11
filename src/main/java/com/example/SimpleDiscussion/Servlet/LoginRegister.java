package com.example.SimpleDiscussion.Servlet;

import com.example.SimpleDiscussion.Customer.Customer;
import com.example.SimpleDiscussion.Customer.CustomerDAO;
import com.example.SimpleDiscussion.Customer.CustomerDAOImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginRegister", value = "/LoginRegister")
public class LoginRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerDAO cd = new CustomerDAOImpl();
        String userName=request.getParameter("username");
        String password=request.getParameter("password1");
        String submitType=request.getParameter("submit");
        Customer c= cd.getCustomer(userName,password);
        if (submitType.equals("Login") && c!=null && c.getName()!=null){
            request.setAttribute("message", c.getName());
            request.getRequestDispatcher("welcome.jsp").forward(request,response);
        }else if (submitType.equals("Register")){
            c.setName(request.getParameter("name"));
            c.setPassword(password);
            c.setUsername(userName);
            cd.insertCustomer(c);
            request.setAttribute("successMessage", "Registration done, please login to continue !!!");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }else  {
            request.setAttribute("message", "Data Not Found, Click on Register !!!");
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
    }
}
