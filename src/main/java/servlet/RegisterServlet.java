package servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Product;
import service.ProductService;
import util.ParamUtil;

@WebServlet("/kadaiRegister")
@MultipartConfig(
location="/tmp/files",
maxFileSize=1000000,
maxRequestSize=1000000,
fileSizeThreshold=1000000
)
public class RegisterServlet extends HttpServlet {
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 文字化け対策
        request.setCharacterEncoding("UTF-8");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        String pdId = request.getParameter("pdId");
        String pdName = request.getParameter("pdName");
        String priceStr = request.getParameter("price");
        String roleIdStr = request.getParameter("roleId");
        String description = request.getParameter("description");        
//        Part part=request.getPart("file");
        		
        String msg ="";
        int error = 0;
        
        //入力されているかどうか
        if (ParamUtil.isNullOrEmpty(pdId)) {
        	request.setAttribute("errorId", "商品IDは必須です。");
        	error = 1;
        }
        if (ParamUtil.isNullOrEmpty(pdName)) {
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
			request.getRequestDispatcher("insert.jsp").forward(request, response);
		}
        //大きいpriceを入力されら時の処理追加する
		int price = Integer.parseInt(priceStr);
		int roleId = Integer.parseInt(roleIdStr);
		
		
		//画像も登録したい;;
//		String filename=part.getSubmittedFileName();
//		
//		String path=getServletContext().getRealPath("/upload");
		
        Product pd = new Product(pdId, pdName, price, roleId, description, timestamp, timestamp);
        ProductService productService = new ProductService();

        int a = productService.insertProduct(pd);
        
        if(a == 1) {
        	msg = "登録が完了しました";
        }else {
        	msg = "同じ商品IDが既に存在します";
        }
        
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("insert.jsp").forward(request, response);
//    	User user = new User(id, pass);
//    	UserService userService = new UserService();
//        User a = userService.login(user);
//        if(a != null) {
//        	session.setAttribute("user", a);
//            // 表示メッセージの受け渡し
//            request.getRequestDispatcher("menu.jsp").forward(request, response);
//        }else{
//        	msg = "ログインにしっぱいしました　";
//        	request.setAttribute("msg", msg);
//            request.getRequestDispatcher("index.jsp").forward(request, response);
//
//        }
    }

}
