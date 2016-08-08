package com.huanjun.daoimpl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.huanjun.bean.User;
import com.huanjun.dao.usrdao;
@Repository
public class userdaoimpl implements usrdao {
	
	private static Log log = LogFactory.getLog(userdaoimpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean insertuser(User user) {
		log.info("insert into user");
		String sql = "INSERT INTO user(email,username,password,gender,joindate) VALUES(?,?,?,?,?)";
		Date date=new Date();
		Object[] params = new Object[]{user.getEmail(), user.getUsername(),user.getPassword(),
				user.getGender(),date};
		int result = jdbcTemplate.update(sql, params);
		if(result>=1)
			return true;
		else
			return false;
	}

	public boolean modifyuser(String name) {
		
		return false;
	}

	public boolean queryuser(User user) {
		log.info("cha xun");
		String sql="select id from user where username=? and password=?";
		Object[] params = new Object[]{user.getUsername(),user.getPassword()};
		log.info(params[0]);
		int result;
		try{
			 result=jdbcTemplate.queryForInt(sql, params);
		}catch(EmptyResultDataAccessException e){
			result=0;
		}
		
		if(result>=1)
			return true;
		else
			return false;
	}

	public int getid(User user) {
		log.info("get user id");
		log.info(user.getUsername());
		String sql="select id from user where username=?";
		Object[] param={user.getUsername()};
		int result=jdbcTemplate.queryForInt(sql,param);
		if(result<0)
			return 0;
		else
			return result;
	}

}
