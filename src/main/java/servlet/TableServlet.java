package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
        //入力情報受け取り
		String keyword = request.getParameter("keyword");
		String find = request.getParameter("find");
		String btn = request.getParameter("btn");
		String sort = request.getParameter("sort");
		
		//Product情報の受け取り
		String idStr = request.getParameter("id");
		String pdId = request.getParameter("pdId");
		String name = request.getParameter("pdName");
		String priceStr = request.getParameter("price");
		String roleIdStr = request.getParameter("roleId");
		String description = request.getParameter("description");
		
		//
		int count = 0;
		int error = 0;
		String msg = "";
		List<Product> list = null;
		ProductService pdService = new ProductService();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		//更新画面の処理
		if(btn != null && btn.equals("update")) {			
			//入力されているかどうか
	        if (ParamUtil.isNullOrEmpty(pdId)) {
	        	request.setAttribute("errorId", "商品IDは必須です。");
	        	error = 1;
	        }
	        if (ParamUtil.isNullOrEmpty(name)) {
	        	request.setAttribute("errorName", "商品名は必須です。");
	        	error = 1;
	        }
	        if (ParamUtil.isNullOrEmpty(priceStr)) {
	        	request.setAttribute("errorPrice", "単価は必須です。");
	        	error = 1;
	        }
	        if (ParamUtil.isNullOrEmpty(roleIdStr)) {
	        	error = 1;
	        }
			if (error == 1) {
				msg += "エラーです";
				request.setAttribute("error", msg);
				request.getRequestDispatcher("updateInput.jsp").forward(request, response);
			}
			
			int id = Integer.parseInt(idStr);
			int price = Integer.parseInt(priceStr);
			int roleId = Integer.parseInt(roleIdStr);
			
			Product pd = new Product(id, pdId, name, price, roleId, description, timestamp);
			int a = pdService.updateById(pd);
			if(a == 1) {
				msg = "更新に成功しました。";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("menu.jsp").forward(request, response);
			}else if (a == 0) {
				msg = "商品IDが重複しています、";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("updateInput.jsp").forward(request, response);
			}else if (a == -1) {
				msg="エラーが発生しました。";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("updateInput.jsp").forward(request, response);
			}
		}
		
		//削除画面の処理
		if(btn != null && btn.equals("delete")) {
			int a = pdService.deleteByPdId(pdId);
			if(a == 1) {
				msg = "削除に成功しました。";
				
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("menu.jsp").forward(request, response);
			}else {
				msg = "削除に失敗しました。";
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("detail.jsp").forward(request, response);
			}
		}

		//メニュー画面
//		if(find != null) {

			// 入力値のチェック
			if (ParamUtil.isNullOrEmpty(keyword)) {

				list = pdService.allProducts();
				for(Product a: list) {
					count++;
				}
			}else {
				list = pdService.selectByKeyword(keyword);
				for(Product a: list) {
					count++;
				}
			}
			//並び替え
			if(sort != null) {
				 switch (sort) {
		         case "sortId":
		        	 list.sort((p1,p2) -> p1.getId() >= p2.getId() ? 1: -1);
		 			request.setAttribute("resultSort", "現在ID順です");
		             break;
		         case "sortCate":
		        	 list.sort((p1,p2) -> p1.getCategory_id() >= p2.getCategory_id() ? 1: -1);
			 		 request.setAttribute("resultSort", "現在カテゴリ順です");
		        	 break;
		         case "sortPriceLow":
		        	 list.sort((p1,p2) -> p1.getPrice() >= p2.getPrice() ? 1: -1);
			 		 request.setAttribute("resultSort", "現在 単価:安い順です");
		        	 break;
		         case "sortPriceHigh":
		        	 list.sort((p1,p2) -> p1.getPrice() <= p2.getPrice() ? 1: -1);
			 		 request.setAttribute("resultSort", "現在 単価:高い順です");
		        	 break;
		         case "sortDayOld":
		        	 list.sort((p1, p2) -> p1.getCreated_at().compareTo(p2.getCreated_at()));
			 		 request.setAttribute("resultSort", "現在 登録日:古い順です");
		        	 break;
		         case "sortDayNew":
		        	 list.sort((p1, p2) -> p2.getCreated_at().compareTo(p1.getCreated_at()));
			 		 request.setAttribute("resultSort", "現在 登録日:新しい順です");
		        	 break;
		         default:
		        	 break;
				 }
			}

			
			session.setAttribute("list", list);
			request.setAttribute("sort", sort);

			if(count == 0) {
				request.setAttribute("errorSelect", "該当する結果はありません！");
			}		


			request.setAttribute("count", count);
			request.getRequestDispatcher("menu.jsp").forward(request, response);

//		}
	}

}
