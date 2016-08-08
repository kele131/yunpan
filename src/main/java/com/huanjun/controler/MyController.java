package com.huanjun.controler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.huanjun.bean.User;
import com.huanjun.bean.diskinfo;
import com.huanjun.daoimpl.Diskinfodaoimpl;
import com.huanjun.daoimpl.userdaoimpl;


@Controller
@RequestMapping(value="/login")
public class MyController {
	
	private static Log log=LogFactory.getLog(MyController.class);
	@Autowired
	userdaoimpl userdao;
	@Autowired
	Diskinfodaoimpl diskdao;
	@RequestMapping(value="/welcome")
	private  ModelAndView hello(User user,HttpServletRequest request, HttpSession session){
		log.info("welcome !!!!");
		log.info(user.getUsername());
		boolean vali=userdao.queryuser(user);
		
		
		ModelAndView mv = new ModelAndView();  
		if(vali){
			diskinfo disk=diskdao.query(userdao.getid(user));
			String str=request.getLocalAddr();
			mv.setViewName("disk");
			session.setAttribute("user", user);
			session.setAttribute("disk", disk);
			session.setAttribute("homeId", str);
		}
		else{
			mv.setViewName("index");
			
		}
		
		return mv;
		
	}
	
	
	@RequestMapping(value="/logout")
	
	private  ModelAndView hello(){
		log.info("logout");
		ModelAndView mv = new ModelAndView();  
		
		mv.setViewName("index");
        
		return mv;
		
	}
}
