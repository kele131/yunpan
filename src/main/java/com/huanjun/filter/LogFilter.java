package com.huanjun.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.huanjun.controler.MyController;

public class LogFilter implements Filter {
	private FilterConfig config;
	private static Log log=LogFactory.getLog(MyController.class);
	public void destroy() {
		// TODO Auto-generated method stub
		this.config=null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//---------娑撳娼版禒锝囩垳閻€劋绨�靛湱鏁ら幋鐤嚞濮瑰倹澧界悰宀勵暕婢跺嫮鎮�---------
		//閼惧嘲褰嘢ervletContext鐎电钖勯敍宀�鏁ゆ禍搴ゎ唶瑜版洘妫╄箛锟�
		ServletContext context = this.config.getServletContext(); 
		long before = System.currentTimeMillis();
		log.info("瀵拷婵绻冨锟�...");
		//鐏忓棜顕Ч鍌濇祮閹广垺鍨欻ttpServletRequest鐠囬攱鐪�
		HttpServletRequest hrequest = (HttpServletRequest)request;
		//鐠佹澘缍嶉弮銉ョ箶
		context.log("Filter瀹歌尙绮￠幋顏囧箯閸掓壆鏁ら幋椋庢畱鐠囬攱鐪伴崷鏉挎絻閿涳拷 " + hrequest.getServletPath());
		//Filter閸欘亝妲搁柧鎯х础婢跺嫮鎮婇敍宀冾嚞濮瑰倷绶烽悞鑸垫杹鐞涘苯鍩岄惄顔炬畱閸︽澘娼�
		chain.doFilter(request, response); 
		//---------娑撳娼版禒锝囩垳閻€劋绨�佃婀囬崝鈥虫珤閸濆秴绨查幍褑顢戦崥搴☆槱閻烇拷---------
		long after = System.currentTimeMillis();
		//鐠佹澘缍嶉弮銉ョ箶
		context.log("鏉╁洦鎶ょ紒鎾存将");
		//閸愬秵顐肩拋鏉跨秿閺冦儱绻�
		context.log("鐠囬攱鐪扮悮顐㈢暰娴ｅ秴鍩�" + hrequest.getRequestURI() + "閹碉拷閼鸿京娈戦弮鍫曟？娑擄拷: " + (after - before)); 
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		this.config=arg0;
		
	}

}
