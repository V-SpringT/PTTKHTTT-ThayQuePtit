package com.ecommerce.controller.client;

import com.ecommerce.dao.CustomerDAO;
import com.ecommerce.model.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO;

    @Override
    public void init() {
        customerDAO = new CustomerDAO();
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		RequestDispatcher rd = req.getRequestDispatcher("/views/client/login.jsp");
		rd.forward(req,res);
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        System.out.println("user " +  username+ " " + password);
        if (customerDAO.validateCustomer(username, password)) {
      
            response.sendRedirect("/QueProject/home");
        } else {
            // Return an error message and reload the login page
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/views/client/login.jsp").forward(request, response);
        }
    }
}
