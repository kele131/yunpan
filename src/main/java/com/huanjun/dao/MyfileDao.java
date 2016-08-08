package com.huanjun.dao;

import java.util.List;

import com.huanjun.bean.Myfile;

public interface MyfileDao {
	public void save(Myfile file);
	public Myfile load(int id);
	public List<Myfile> loadAll();
}
