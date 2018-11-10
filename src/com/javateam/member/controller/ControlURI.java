package com.javateam.member.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlURI extends HttpServlet {
    
	private static final long serialVersionUID = -7319756570026146592L;
	
	// <커맨드, 핸들러인스턴스> 매핑 정보 저장
    private Map<String, Object> commandHandlerMap = new HashMap<String, Object>();
    
    public void init(ServletConfig config) throws ServletException {
    	
        String configFile = config.getInitParameter("configFile");
        Properties prop = new Properties();
        FileInputStream fis = null;
        
        try {
	            fis = new FileInputStream(configFile);
	            prop.load(fis);
        } catch (IOException e) {
            throw new ServletException(e);
        } finally {
            if (fis != null) try { fis.close(); } catch(IOException ex) {}
        }
        
        Iterator<Object> keyIter = prop.keySet().iterator();
        
        while( keyIter.hasNext() ) {
        	
	            String command = (String)keyIter.next();
	            String handlerClassName = prop.getProperty(command);
            
            try {            	
		                Class<?> handlerClass = Class.forName(handlerClassName);
		                Object handlerInstance = handlerClass.newInstance();
		                commandHandlerMap.put(command, handlerInstance);
            
            } catch (ClassNotFoundException e) {
            				throw new ServletException(e);
            } catch (InstantiationException e) {
                			throw new ServletException(e);
            } catch (IllegalAccessException e) {
                			throw new ServletException(e);
            }
        } // while
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)  
    		throws ServletException, IOException {
    	
        requestPro(request, response);        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  
    		throws ServletException, IOException {
    	
        requestPro(request, response);        
    }
    
    private void requestPro(HttpServletRequest request, HttpServletResponse response)  
    		throws ServletException, IOException {
  
    	String command = request.getRequestURI();
   
    	if (command.indexOf(request.getContextPath()) == 0) {
            command = command.substring(request.getContextPath().length());
        }
        
        CommandAction handler = 
            (CommandAction)commandHandlerMap.get(command);

        String viewPage = null;
        
        try {
        		viewPage = handler.requestPro(request, response);
        		System.out.println("viewPage : "+viewPage);
        } catch(Throwable e) {
        	System.out.println("이동 페이지 예외 : ");
            throw new ServletException(e);
        }
        
        // 인증 필터에 따른 추가 사항 : 예외처리 간소화
        // 템플릿(template) 사용
        String movePath = "/template/template.jsp";
        
        if (request.getParameter("contentPath")=="" ||
        	request.getParameter("contentPath")==null) {	
        		
        	request.setAttribute("contentPath", ".."+viewPage); // 상대 상위 경로(..) 추가
        	
        } else {
        	request.setAttribute("contentPath", request.getParameter("contentPath"));
        }
        
        try {
	        	RequestDispatcher dispatcher 
		        	// = request.getRequestDispatcher(viewPage);
	        		= request.getRequestDispatcher(movePath);
		        dispatcher.forward(request, response);	
		} catch (Exception e) {
			System.out.println("페이지 예외 : 로그인 인증 요구 페이지"); /// 불필요한 예외 간단 처리
		} 	
        
    } //
    
} // class