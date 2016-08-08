package com.huanjun.dao;

import com.huanjun.bean.User;
import com.huanjun.bean.diskinfo;

public interface diskinfodao {
	//该
	public boolean modifydisk(User user);
	//查
	public diskinfo query(int userid);
	
}
