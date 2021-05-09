package com.kingoin.king.base_data_sys;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class BaseTest {
	
	@Test
	public void test1(){
		
	}
	@Test
	public void test2(){
		
		
	}
	@Test
	public void test3(HttpServletRequest request, HttpServletResponse response)
	              throws ServletException, IOException{
		String savePath = request.getServletContext().getRealPath("/");
		System.err.println(savePath);
	}
}
