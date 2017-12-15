package net.lele.service;

import net.lele.dao.AdminDao;
import net.lele.dao.UserDao;
import net.lele.entity.Admin;
import net.lele.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

	@Resource
	private UserDao userDao;
	@Resource
	private AdminDao adminDao;

	public User getUser(String username, String password) {
		return this.userDao.getUserByUsernameAndPassword(username, password);
	}

	public boolean isUsernameExisted(String username) {
		return this.userDao.getUserByUsername(username) != null;
	}

	public boolean createUser(User user) {
		return this.userDao.addUser(user);
	}

	public Admin getAdmin(String username, String password) {
		return this.adminDao.getAdminByUsernameAndPassword(username, password);
	}
}
