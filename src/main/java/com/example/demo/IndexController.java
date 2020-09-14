package com.example.demo;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.logging.Logger;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
    	
    	 ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
    	 HttpSession session = attr.getRequest().getSession();
    	 session.setAttribute("username", "Manisha");
    	 String[] sessionArr = session.getValueNames();
    	 Object samlKeyObj = session.getAttribute("username");
    	 Object samlKeyObj2 = session.getAttribute("_springSamlStorageKey");
    	 Object samlKeyObj3 = session.getAttribute("SPRING_SECURITY_CONTEXT");
    	 Logger logger=Logger.getLogger("global");
    	 logger.info("============Session=======================++++=======================");
    	 logger.info(Arrays.toString(sessionArr));
    	 logger.info(samlKeyObj.toString());
    	 logger.info(samlKeyObj2.toString());
    	 logger.info(samlKeyObj3.toString());
    	 logger.info("====================+++++===========+++===========================");
    	 return "index";
    	
    }
    
    @RequestMapping("/logout")
    private String deletecookie(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	
    	Cookie[] cookies = request.getCookies();
    	for (Cookie cookie : cookies) {
	    	cookie.setMaxAge(0);
	    	cookie.setValue(null);
	    	cookie.setPath("/");
	    	response.addCookie(cookie);
    	}
    	 HttpSession session=request.getSession();  
         session.invalidate();  
    	//response.sendRedirect("https://localhost:8443/logout");
    	return "logout";
    	
    }
    

}








