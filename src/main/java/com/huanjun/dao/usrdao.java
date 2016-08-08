package com.huanjun.dao;

import org.springframework.stereotype.Repository;

import com.huanjun.bean.User;

@Repository
public interface usrdao {
	//增
	public boolean insertuser(User user);
	//该
	public boolean modifyuser(String name);
	//查
	public boolean queryuser(User user);
	public int getid(User user);

	
}
