<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>${user.getUsername()}的网盘</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="/resource/css/base.css"/>
		<link rel="stylesheet" href="/resource/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<link rel="stylesheet" href="/resource/css/disk.css"/>
		<link rel="stylesheet" href="/resource/css/file.css"/>
		<link rel="stylesheet" href="/resource/contextmenu/theme/contextmenu.css"/>
		<link rel="stylesheet" href="/resource/checkInput.v2/skin.css"/>
		<script type="text/javascript" src="/resource/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/resource/zTree/js/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript" src="/resource/zTree/js/jquery.ztree.exedit-3.5.min.js"></script>
		<script type="text/javascript" src="/resource/contextmenu/jquery.contextmenu.js"></script>
		<script type="text/javascript" src="/resource/js/progressBar.js"></script>
		<script type="text/javascript" src="/resource/checkInput.v2/checkInput.js"></script>
		<style>
			#upload{position:relative;}
			#upload_button{
				position:absolute;
				top:0px;
				left:0px;
				border:none!important;
				padding:0!important;
				margin:0!important;
				text-align:left;
				opacity:0;
			}
			input{vertical-align:text-top;}
			.pretty_file_input {position: relative;}
			.pretty_file_input .fake{width:170px;disabled:disabled;position: absolute;}
			.pretty_file_input .browse{top:0px;left:180px;position: absolute;}
			.pretty_file_input .file{opacity:0;width:170px;position: absolute;}
			#space_bar{
				margin:20px 0 0 8px;
				padding:0 3px;
				display:block;
			}
			.progress_bar{margin:9px 0;}
			.total_progress,.current_progress,.bar_data{display:block;}
			.total_progress,.bar_data{ padding:2px; text-align:center;}
			.total_progress{background:#FFF;font-size:12px;}
			.current_progress{ background:#A9D5F4;float:left;color:black;font-weight:800;}
			
	        .active{
	        	background-color:#ADD5FF!important;
	        	border-color:#4D90FE!important;
	        	box-shadow: 0 0 20px rgba(77, 144, 254, 0.7);
	            transition: all 0.5s ease 0s;
	        }
	        .on{
	        	transform:scale(0.5);
	        	-webkit-transform:scale(0.5);
	        	opacity:0.8!important;
	        	transition: transform 0.3s ease 0s;
	        	-webkit-transition: transform 0.3s ease 0s;
	        }
	        #folder .draggable_ghost{border:1px solid #4D90FE!important;}
	        
	        form dl{width:360px;margin:0;}
			form dt{width:90px;float:left;text-align:right;font-size:18px;}
			form dt,form dd{padding:3px 3px;}
	        #dialog textarea{height:60px;resize:none;}
			#dialog textarea,#dialog input{
				width:250px;
				font-size:16px;
				padding:3px 4px;
				padding-right:27px;
				background-position:right 100px;
				background-repeat:no-repeat;
				transition: all 0.3s ease 0s;
				 -webkit-transition: all 0.7s ease-in-out 0s;
				background-image:url(/resource/checkInput.v2/tip.png);
			}
		</style>
	</head>
	<body>
		<div id="wrap">
    		<div id="sky">
    			
    			<div id="cloud">
	    			<span>${user.getUsername()}</span>
	    			<span><a href="<%=request.getContextPath() %>/login/logout" style="color:blue">&nbsp&nbsp&nbsp&nbsp[退出]</a></span>
    			</div> 
    		</div>
	        <div id="main">
	        	<div id="left">
	        		<div id="user_info">
	        			<div id="portrait"></div>
	        			<div id="user_detail">
	        				</br>
	        				</br>
	        				<span>空间大小:  ${disk.getTotalsize()}</span></br>
	        				<span>已使用:  ${disk.getUsedsize()}</span></br>
	        				<span>剩余空间:  ${disk.getTotalsize()- disk.getUsedsize()}</span></br>
	        				<span>文件数量:  ${disk.getFilenumber()}</span></br>
	        			</div>
	        		</div>
	        		
	        		<a id="setting" href="<%=request.getContextPath() %>/u/setting"><span>个人信息设置</span></a>&nbsp&nbsp&nbsp&nbsp
	        		<a id="other" href="javascript:message();"><span>给梁威留个言？</span></a>
	        	
	        		<div id="dir_tree"><ul id="my_file_tree" class="ztree"></ul></div>
	        	</div>
	        	<div id="right">
	        		<div id="file_path">
	        			<div id="path_wrap">
		        			<div id="root"><span>我的网盘</span>:</div>
		        			<div id="children_path"></div>
	        			</div>
	        		</div>
	        		<div id="tools_bar">
	        			
	        			<a href="<%=request.getContextPath() %>/files/list" id="disk">我的文件主页</a>
	        			<a href="<%=request.getContextPath() %>/files/share" id="share">我的分享主页</a>
	        		</div>
	        		<div id="folder">
	        			<ul>
						</ul>
	        		</div>
	        	</div>
	        </div>
    	</div>
    	<div id="upload_queue"></div>
    	<div id="dialog"></div>
	</body>
	<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/js/dialog.js"></script>    				
	<SCRIPT type="text/javascript">
	//  留言
		message = function(){
		    var form = "<form class='leave_message'><dl>"+
						"<dt>标题：</dt><dd><input name='title' type='text' value=''/></dd>"+
			    		"<dt>内容：</dt><dd><textarea name='content'/></dd>"+
			    		"<dt></dt><dd>"+
			    			"<button type='button' onclick='dialog.close();'>取消</button>"+
			    			"<button type='submit'>提交</button>"+
			    		"</dd>"+
			    	"</dl></form>";
     
   		  dialog.show(form,"提交留言和意见");
		 }
	</SCRIPT>

</html>
