package net.onest.servlet.foreground.user;

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
 * 班级: 2015级3班
 * 学号: 2014011669
 */
@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Map<String, String[]> params = req.getParameterMap();

		String username = params.get("username")[0];
		String password = params.get("password")[0];
		String email = params.get("email")[0];
		String telephone = params.get("telephone")[0];
		String address = params.get("address")[0];

		req.setAttribute("username", username);
		req.setAttribute("password", password);
		req.setAttribute("email", email);
		req.setAttribute("telephone", telephone);
		req.setAttribute("address", address);

		if (UserDao.isUsernameExisted(username)) {
			// 用户名已存在
			req.setAttribute("error_un", "用户名已存在!");
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
		} else {
			if (UserDao.createUser(username, password, email, telephone, address)) {
				// 注册成功
				req.setAttribute("success", "注册成功!");
				req.getRequestDispatcher("/foreground/register-result-module.jsp").forward(req, resp);
			} else {
				// 注册失败
				req.setAttribute("error", "发生未知问题!");
				req.getRequestDispatcher("/register.jsp").forward(req, resp);
			}
		}

	}
}
