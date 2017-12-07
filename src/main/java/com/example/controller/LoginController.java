package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * 类名称：LoginController   
 * 类描述：   
 * 创建人：cys   
 * 创建时间：2017年12月7日 下午4:38:14 
 * @version
 */
@Controller
public class LoginController {
	/**
	 * 
	 * @Description: 在定义这个方法时,注意这个路径是匹配到你的自定义的登录页面,需要加@GetMapping注解.
	 * 因为在登录成功时,post提交的也是一个login的路径,需要加以区分
	 * @return String  
	 * @throws
	 * @author cys
	 * @date 2017年12月7日
	 */
	@RequestMapping("/login")
	@GetMapping
	public String login() {
		return "index";
	}
	/**
	 * 
	 * @Description: 登录成功返回的界面
	 * @return String  
	 * @throws
	 * @author cys
	 * @date 2017年12月7日
	 */
	@RequestMapping("/ok")
	//@GetMapping
	public String index() {
		return "ok";
	}
	//退出的界面不需要自己定义
	/*@RequestMapping("/logout")
	public String logout() {
		return "Logout";
	}*/
}