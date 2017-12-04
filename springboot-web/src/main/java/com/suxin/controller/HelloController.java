package com.suxin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suxin.annotation.WebLog;

@RequestMapping
@RestController
public class HelloController {
	
	@Value("${myname.wyj}")
	private String name;
	
	
	@WebLog(remark="hello世界")
	@RequestMapping(value = "/hello")
	public String hello(HttpServletRequest requedt) {
		return "hello world! " + name;
	}

}
