package net.lele.controller.foreground;

import net.lele.entity.User;
import net.lele.service.BookService;
import net.lele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String login(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpServletRequest request
	) {
		HttpSession session = request.getSession();

		User user = this.userService.getUser(username, password);

		if (user == null) {
			// 登陆验证失败
			request.setAttribute("error", "用户名或密码错误!");
			return "forward:/login";
		} else {
			// 登陆验证成功
			session.setAttribute("user", user);
			return "redirect:/";
		}

	}

	@RequestMapping("logout.do")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	@RequestMapping("register.do")
	public String register(
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("email") String email,
			@RequestParam("telephone") String telephone,
			@RequestParam("address") String address,
			HttpServletRequest request
	) {

		request.setAttribute("username", username);
		request.setAttribute("password", password);
		request.setAttribute("email", email);
		request.setAttribute("telephone", telephone);
		request.setAttribute("address", address);

		if (this.userService.isUsernameExisted(username)) {
			// 用户名已存在
			request.setAttribute("error_un", "用户名已存在!");
			return "forward:/register";
		} else {

			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user.setEmail(email);
			user.setTelephone(telephone);
			user.setAddress(address);

			if (this.userService.createUser(user)) {
				// 注册成功
				request.setAttribute("success", "注册成功!");
				System.out.println("成功");
				return "/foreground/register-result-module";
			} else {
				// 注册失败
				request.setAttribute("error", "发生未知问题!");
				System.out.println("失败");
				return "forward:/register";
			}
		}
	}
}
