package net.lele.framework;

import org.hibernate.dialect.MySQL5InnoDBDialect;

public class MySQL5InnoDBUTF8Dialect extends MySQL5InnoDBDialect {
	@Override
	public String getTableTypeString() {
		return super.getTableTypeString() + " charset=utf8";
	}
}
