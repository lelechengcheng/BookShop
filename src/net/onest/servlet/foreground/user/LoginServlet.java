package net.onest.servlet.foreground.user;

import net.onest.bean.User;
import net.onest.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Map<String, String[]> params = req.getParameterMap();

		String username = params.get("username")[0];
		String password = params.get("password")[0];

		User user = UserDao.getUser(username, password);

		if (user == null) {
			// 登陆验证失败
			req.setAttribute("error", "用户名或密码错误!");
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		} else {
			// 登陆验证成功
			session.setAttribute("user", user);
			resp.sendRedirect("/index.jsp");
		}


	}
}
