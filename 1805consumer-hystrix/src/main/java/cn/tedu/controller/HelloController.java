package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.tedu.feign.EurekaServiceFeign;



@RestController
public class HelloController {
	@Autowired
	private EurekaServiceFeign eurekaServiceFeign;
	
	@GetMapping("/hello/{name}")
	@ResponseBody
	@HystrixCommand(fallbackMethod="helloFallback")
	public String hello(@PathVariable String name){
		return eurekaServiceFeign.hello(name);
	}
	
	public String helloFallback(String name){
		return "tony";//调用失败的时候，返回默认值
	}


}
