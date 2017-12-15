package net.lele.controller.background;

import net.lele.entity.Admin;
import net.lele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginPage {

	@Resource
	private UserService userService;

	@RequestMapping(value="login")
	public String pageLogin(){
		return "/background/login-module";
	}

	@RequestMapping(value = "index")
	public String pageIndex() {
		return "/background/index-module";
	}

	@RequestMapping(value="login.do")
	public String login(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpServletRequest request
	){
		HttpSession session = request.getSession();

		Admin admin = this.userService.getAdmin(username, password);
		if (admin != null) {
			session.setAttribute("admin", admin);
			return "redirect:/admin/index";
		} else {
			request.setAttribute("error", "用户名或密码错误");
			return "forward:/admin/login";
		}
	}
}
