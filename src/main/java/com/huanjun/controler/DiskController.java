package com.huanjun.controler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/home")
public class DiskController {
	
	private static Log log=LogFactory.getLog(DiskController.class);
	
	@RequestMapping(value="/disk")
	private ModelAndView diskinfo(){
		ModelAndView mv = new ModelAndView();  
		mv.setViewName("disk");
		return mv;
		
	}
}
