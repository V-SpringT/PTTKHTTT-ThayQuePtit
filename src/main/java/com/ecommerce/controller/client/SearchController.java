package com.ecommerce.controller.client;
import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

@WebServlet("/search")
public class SearchController extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        List productList = (List) productDAO.searchProducts(query);
        request.setAttribute("productList", productList);
        request.setAttribute("keyword", query);
        request.getRequestDispatcher("/views/client/Search.jsp").forward(request, response);
    }
}
