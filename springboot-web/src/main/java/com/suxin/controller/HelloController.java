package com.suxin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suxin.annotation.WebLog;

@RequestMapping
@RestController
public class HelloController {
	
	
	@WebLog(remark="hello世界")
	@RequestMapping(value = "/hello")
	public String hello(HttpServletRequest requedt, String name) {
		return "hello world! " + name;
	}

}
