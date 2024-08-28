package com.ecommerce.controller.client;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ecommerce.dao.CustomerDAO;
import com.ecommerce.dao.ResetPasswordDAO;
import com.ecommerce.model.Customer;
import com.ecommerce.model.ResetPasswordToken;
import com.ecommerce.utils.EmailUtil;

@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CustomerDAO customerDAO = new CustomerDAO();
    private ResetPasswordDAO resetPasswordDAO = new ResetPasswordDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	request.getRequestDispatcher("/views/client/forgot-password.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        Customer customer = customerDAO.findByEmail(email);
        if (customer != null) {
            String token = UUID.randomUUID().toString();
            ResetPasswordToken resetToken = new ResetPasswordToken();
            resetToken.setToken(token);
            resetToken.setEmail(email);
            resetToken.setExpiryDate(new Date(System.currentTimeMillis() + 3600000)); 

            resetPasswordDAO.saveToken(resetToken);
            String resetLink = request.getRequestURL().toString().replace("forgot-password", "reset-password?token=" + token);
            EmailUtil.sendResetPasswordEmail(email, resetLink);
        }

        request.setAttribute("message", "If your email is registered, you'll receive a reset link.");
        request.getRequestDispatcher("/views/client/forgot-password.jsp").forward(request, response);
    }
}