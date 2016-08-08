package com.huanjun.daoimpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.huanjun.bean.Myfile;
import com.huanjun.dao.MyfileDao;
@Repository
public class Myfiledaoimpl implements  MyfileDao{
	
	private static Log log = LogFactory.getLog(userdaoimpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(Myfile file) {
		String sql="insert into myfile(user_id,name,type,size,path) values(?,?,?,?,?)";
		Object[] params={file.getUser_id(),file.getName(),file.getType(),file.getSize(),file.getPath()};
		jdbcTemplate.update(sql, params);
		
	}

	public Myfile load(int id) {
		String sql="select * from";
		return null;
		
		
	}

	public List<Myfile> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
