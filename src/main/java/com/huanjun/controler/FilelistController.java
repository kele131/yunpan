package com.huanjun.controler;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.huanjun.bean.Myfile;
import com.huanjun.bean.User;
import com.huanjun.bean.diskinfo;
import com.huanjun.daoimpl.Diskinfodaoimpl;
import com.huanjun.daoimpl.Myfiledaoimpl;
import com.huanjun.daoimpl.userdaoimpl;

@Controller
@RequestMapping(value="/files")
public class FilelistController {

	private static Log log=LogFactory.getLog(FilelistController.class);
	@Autowired
	Myfiledaoimpl filedao;
	@Autowired
	userdaoimpl  userdao;
	@Autowired
	Diskinfodaoimpl diskdao;
	@RequestMapping(value="/list")
	private  ModelAndView filelist(){
		log.info("file list");
		ModelAndView mv = new ModelAndView();  
		mv.setViewName("file");
        return mv;
		
	}

	@RequestMapping(value="/share")
	private  ModelAndView sharelist(){
		log.info("share file list");
		ModelAndView mv = new ModelAndView();  
		mv.setViewName("share");
        return mv;
		
	}
	@RequestMapping(value="/Upload")
	 public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model,HttpSession session) {  
		  
        System.out.println("开始");  
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)(request);
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = file.getOriginalFilename(); 
        Myfile myfile=new Myfile();
        User user=(User)session.getAttribute("user");
        myfile.setName(fileName);
        myfile.setPath("F://upload");
        myfile.setSize((int) file.getSize());
        myfile.setType("doc");
        myfile.setUser_id(userdao.getid(user));
        
//        String fileName = new Date().getTime()+".jpg";  
        System.out.println(path);  
        File targetFile = new File("F://upload", fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        
       
        try {  
        	 //保存  
            file.transferTo(targetFile);  
            //插入文件表
            filedao.save(myfile);
            //更新磁盘信息
            diskdao.modifydisk(userdao.getid(user), myfile);
            diskinfo disk=diskdao.query(userdao.getid(user));
            session.setAttribute("disk", disk);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName);  
  
        return "upload";  
    }  
	
}
