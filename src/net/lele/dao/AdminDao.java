package net.lele.dao;

import net.lele.entity.Admin;
import net.lele.framework.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao extends BaseDao<Admin, Integer> {


	public Admin getAdminByUsernameAndPassword(String username, String password) {
		try {
			return super.findOne("from Admin admin where admin.username=? and admin.password=?", new Object[] {username, password});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
