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
	        String keyword = request.getParameter("keyword");
	        String sort = request.getParameter("sort");
	        
	        
	        List<Product> list = null;
	        
        	int count = 0;
	        
//	        String msg ="<form　id =\"TableServlet\"><div class=\"order\">"
//	        		+ "<select class=\"base-text\" name = \"sort\">"
//	        		+ "<option>並び替え</option>"
//	        		+ "<option value=\"categoryId\">商品ID</option>"
//	        		+ "<option value=\"caregory\">カテゴリ</option>"
//	        		+ "<option value=\"priceLow\">単価：安い順</option>"
//	        		+ "<option value=\"priceHigh\">単価：高い順</option>"
//	        		+ "<option value=\"dayNew\">登録日：古い順</option>"
//	        		+ "<option value=\"dayOld\">登録日：新しい順</option>"
//	        		+ "</select>"
//	        		+ "</form>"
//	        		+ "</div>"
//	        		+ "<thead><tr><th>商品ID</th><th>商品名</th><th>単価</th><th>カテゴリ</th><th>詳細</th></tr></thead><tbody>";
        	
        	String msg = "マインド";
        	
	        ProductService productService = new ProductService();
        	
	        // 入力値のチェック
	        if (ParamUtil.isNullOrEmpty(keyword)) {
	        		        	
	        	list = productService.allProducts();
		        for(Product a: list) {
	        		count++;
	        	}
	        }else {
	        	list = productService.selectByKeyword(keyword);
	        	for(Product a: list) {
	        		count++;
	        	}

	        }
	        
	        
	        
        	request.setAttribute("list", list);
        	request.setAttribute("msg", msg);
        	request.setAttribute("sort", sort);
	        
	        if(count == 0) {
	        	request.setAttribute("errorSelect", "該当する結果はありません！");
	        }
	        
	        String btn = request.getParameter("btn");
	        if(btn != null) {
		        if(!(btn.equals("delete"))) {
		        	msg = "明らか";
		        	request.setAttribute("msg", msg);
		        }
	        }
        	request.setAttribute("count", count);
        	request.getRequestDispatcher("menu.jsp").forward(request, response);
	    }

}
