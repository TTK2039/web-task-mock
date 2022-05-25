package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Product;
import service.ProductService;

@WebServlet("/detailServlet")

public class DetailServlet extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 文字化け対策
        request.setCharacterEncoding("UTF-8");
    	HttpSession session = request.getSession();
        
        String idStr = request.getParameter("id");
        
        int id = Integer.parseInt(idStr);
        
    	Product product = new Product();
    	ProductService productService = new ProductService();
        product = productService.findById(id);
        
        session.setAttribute("product", product);
        // 表示メッセージの受け渡し
        request.getRequestDispatcher("detail.jsp").forward(request, response);
    }
}