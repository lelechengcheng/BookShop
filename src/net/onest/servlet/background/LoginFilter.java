package net.onest.servlet.background;

import net.onest.bean.Admin;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 姓名: 马佳
 * 班级: 15级3班
 * 学号: 2014011669
 */
@WebFilter
public class LoginFilter implements Filter {

	public void init(FilterConfig fc) {
		//过滤器初始化代码
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Admin admin = (Admin) session.getAttribute("admin");

		if (admin == null && !req.getServletPath().contains("/admin/login.do") && !req.getServletPath().contains("/admin/login.jsp")) {
			resp.sendRedirect("/admin/login.jsp");
			return;
		}
		chain.doFilter(request, response);
	}

	public void destroy( ) {
		//过滤器被销毁时执行的代码
	}

}
