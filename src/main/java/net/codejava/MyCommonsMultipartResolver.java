package net.codejava;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MyCommonsMultipartResolver extends CommonsMultipartResolver {

	@Override
	public boolean isMultipart(HttpServletRequest request) {
		// TODO Auto-generated method stub
		final String header = request.getHeader("Content-Type");
	      if(header == null){
	         return false;
	      }
	      return header.contains("multipart/form-data");
	}

}
