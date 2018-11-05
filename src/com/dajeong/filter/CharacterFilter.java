package com.dajeong.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class CharacterFilter
 */
@WebFilter("/CharacterFilter")
public class CharacterFilter implements Filter {
	
	private String encode;

    /**
     * Default constructor. 
     * 기본생성자
     */
    public CharacterFilter() {
        // TODO Auto-generated constructor+ stub
    }

	/**
	 * @see Filter#destroy() 
	 * 이 클래스의 객체가 소멸 시 
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		// 요청 발생 시 실행
		System.out.println("CharacterFilter in");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response);
		System.out.println("CharacterFilter out");
		// 응답 발생 시 실행
	}

	/**
	 * @see Filter#init(FilterConfig)
	 * 이 클래스의 객체가 생성될 때
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		encode = fConfig.getInitParameter("encode");
	}

}
