package net.lele.dao;


import net.lele.entity.Admin;
import net.lele.entity.User;
import net.lele.framework.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao<User, Integer> {
	public User getUserByUsernameAndPassword(String username, String password) {

		try {
			return super.findOne("from User user where user.username=? and user.password=?", new Object[] {username, password});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User getUserByUsername(String username) {
		try {
			return super.findOne("from User user where user.username=?", new Object[] {username});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean addUser(User user) {
		return super.save(user);
	}

}
