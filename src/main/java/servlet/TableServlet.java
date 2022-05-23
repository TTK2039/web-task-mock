package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Product;
import service.ProductService;
import util.ParamUtil;

/**
 * Servlet implementation class TableServlet
 */
@WebServlet("/TableServlet")
public class TableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TableServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // 文字化け対策
	        request.setCharacterEncoding("UTF-8");
	        // ログインID、パスワードを取得
	        String key = request.getParameter("keyword");

	        List<Product> list = null;
	        String msg ="";
	        
	        // 入力値のチェック
	        if (ParamUtil.isNullOrEmpty(key)) {
	            // メッセージ設定
	        	ProductService productService = new ProductService();
	        	
	        	list = productService.allProducts();
	        	
	        	msg = "<table><div class=\"caption\"><p>検索結果：10件</p></div>\r\n<div class=\"order\">"
	        			+ "<select class=\"base-text\">"
	        			+ "<option>並び替え</option>"
	        			+ "<option>商品ID</option>"
	        			+ "<option>カテゴリ</option>"
	        			+ "<option>単価：安い順</option>"
	        			+ "<option>単価：高い順</option>"
	        			+ "<option>登録日：古い順</option>"
	        			+ "<option>登録日：新しい順</option>"
	        			+ "</select>"
	        			+ "</div>"
	        			+ "<thead><tr><th>商品ID</th><th>商品名</th><th>単価</th><th>カテゴリ</th><th>詳細</th></tr></thead><tbody>";
	        	
	        	
	        	for(Product a: list) {
	        		msg += ("<tr><td>" + a.getProduct_id() + "</td><td>" + a.getName() + "</td><td>" + a.getPrice() + "</td><td>" + a.getImage_path() + "</td><td><a class=\"detail_btn\" href=\"./detail.html\">詳細</a></td>");
	        	}
	            
	        	msg += "</tbody><table></div>";
	        	
	        	request.setAttribute("msg", msg);

	            // 次画面指定
	            request.getRequestDispatcher("menu.jsp").forward(request, response);
	            return;
	        }else {
	        	request.getRequestDispatcher("menu.jsp").forward(request, response);
	        }
	    }

}
