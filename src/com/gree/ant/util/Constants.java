// Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2005-6-28 19:39:04
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Constants.java

package com.gree.ant.util;

import java.util.Date;

public class Constants {
	public static final boolean INFO = true;
	

	//服务器
	public static final String FILE_PATH="\\\\192.13.183.83\\e$\\apache-tomcat-7.0.39\\webapps\\greepro\\pro\\node400.jsp";
	public static final String UPLOAD_PATH = "\\\\192.13.183.83\\pro_file\\";
	
	//public static final String UPLOAD_PATH = "\\\\172.16.16.129\\pro_file\\";
	
	//本地
	//public static final String UPLOAD_PATH = "\\\\10.1.18.83\\pro_file\\";
	//public static final String FILE_PATH="\\\\172.16.16.129\\c$\\apache-tomcat-7.0.39_x64\\wtpwebapps\\greepro\\pro\\node400.jsp";

//	public static final String UPLOAD_PATH = "\\\\e:\\upload\\";
//	public static final String FILE_PATH="\\\\e:\\upload\\";
	
	public static final String JNDI_MAIL = "mailSession";

	public static final void out(String msg) {
		System.out.println("[" + new Date(System.currentTimeMillis()) + "] - "
				+ msg);
	}
}