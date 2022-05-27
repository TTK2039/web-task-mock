package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Categories;
import entity.User;
import service.CategoriesService;
import service.UserService;
import util.ParamUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		List<Categories> cdList = null;
		CategoriesService cdService = new CategoriesService();
		
		String id = request.getParameter("loginId");
		String pass = request.getParameter("pass");

		String msg ="";
		int error = 0;

		//入力されているかどうか
		if (ParamUtil.isNullOrEmpty(id)) {
			request.setAttribute("idError", "IDは必須です。");
			error = 1;
		}
		if (ParamUtil.isNullOrEmpty(pass)) {
			request.setAttribute("passError", "PASSは必須です。");
			error = 1;
		}
		if (error == 1) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}



		User user = new User(id, pass);
		UserService userService = new UserService();
		User a = userService.login(user);
		if(a != null) {
			session.setAttribute("user", a);
			
			cdList = cdService.selectCategory();
			
			session.setAttribute("cdList", cdList);
			// 表示メッセージの受け渡し
			request.getRequestDispatcher("menu.jsp").forward(request, response);
		}else{
			msg = "ログインにしっぱいしました　";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("index.jsp").forward(request, response);

		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub   
		HttpSession session = request.getSession();

		String btn = request.getParameter("btn");
		if(btn.equals("logout")) {
			session.invalidate();
			request.getRequestDispatcher("logout.jsp").forward(request, response);
		}

	}



}
