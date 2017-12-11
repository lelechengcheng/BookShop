package net.onest.servlet.background;

import net.onest.bean.Admin;
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
@WebServlet("/admin/login.do")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Map<String, String[]> params = req.getParameterMap();

		String username = params.get("username")[0];
		String password = params.get("password")[0];

		Admin admin = UserDao.getAdmin(username, password);
		if (admin != null) {
			session.setAttribute("admin", admin);
			resp.sendRedirect("/admin/index.jsp");
		} else {
			req.setAttribute("error", "用户名或密码错误");
			req.getRequestDispatcher("/admin/login.jsp").forward(req, resp);
		}

	}
}
