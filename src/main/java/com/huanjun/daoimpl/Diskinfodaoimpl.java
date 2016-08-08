package com.huanjun.daoimpl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.huanjun.bean.Myfile;
import com.huanjun.bean.User;
import com.huanjun.bean.diskinfo;
import com.huanjun.dao.diskinfodao;
@Repository
public class Diskinfodaoimpl implements diskinfodao{
	
	private static Log log = LogFactory.getLog(Diskinfodaoimpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;


	@SuppressWarnings("unchecked")
	public diskinfo query(int userid) {
		String sql="select * from mydiskinfo where user_id=?";
		Object[] param={userid};
		diskinfo result = (diskinfo) jdbcTemplate.queryForObject(sql,param, new BeanPropertyRowMapper(diskinfo.class));

           
		return result;
	}

	public boolean modifydisk(int userid, Myfile file) {
		String sql1="update mydiskinfo set filenumber=filenumber+1 where user_id=?";
		String sql="update mydiskinfo set usedsize=usedsize+?  where user_id=?";
		Object[] params={file.getSize(),userid};
		Object[] params1={userid};
		jdbcTemplate.update(sql, params);
		jdbcTemplate.update(sql1, params1);
		return false;
	}

	public boolean modifydisk(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
