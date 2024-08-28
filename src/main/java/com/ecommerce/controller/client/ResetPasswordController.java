package com.ecommerce.controller.client;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecommerce.dao.CustomerDAO;
import com.ecommerce.dao.ResetPasswordDAO;
import com.ecommerce.model.Customer;
import com.ecommerce.model.ResetPasswordToken;

@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ResetPasswordDAO resetPasswordDAO = new ResetPasswordDAO();
    private CustomerDAO customerDAO = new CustomerDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        ResetPasswordToken resetToken = resetPasswordDAO.findByToken(token);

        if (resetToken == null) {
            request.setAttribute("error", "The reset link is invalid or has expired.");
            request.getRequestDispatcher("/views/client/reset-password.jsp").forward(request, response);
            return;
        }

        request.setAttribute("token", token);
        request.getRequestDispatcher("/views/client/reset-password.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        String newPassword = request.getParameter("newPassword");

        ResetPasswordToken resetToken = resetPasswordDAO.findByToken(token);

        if (resetToken == null) {
            request.setAttribute("error", "The reset link is invalid or has expired.");
            request.getRequestDispatcher("/views/client/reset-password.jsp").forward(request, response);
            return;
        }

        Customer customer = customerDAO.findByEmail(resetToken.getEmail());

        if (customer != null) {
            customer.setPassword(newPassword);  
            customerDAO.updatePassword(customer);

            resetPasswordDAO.deleteToken(token); 
            request.setAttribute("message", "Your password has been reset successfully.");
        } else {
            request.setAttribute("error", "An error occurred. Please try again.");
        }

        request.getRequestDispatcher("/views/client/login.jsp").forward(request, response);
    }
}
