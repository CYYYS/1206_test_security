package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	//configureGlobal名字不重要,重要的是需要在一个被@EnableWebSecurity注解的类中配置AuthenticationManagerBuilder
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
		.and().withUser("admin").password("admin").roles("ADMIN");
	}
	//.formLogin().loginPage()中的参数是自定义一个路径你在controller中必须是get转发到这个路径.这个转发后的页面
	//是自己定义的一个权限登录页面.
	//403解决http.crsf().disable()
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		authorizeRequests().
		antMatchers("/webjars/**", "/signup", "/about").permitAll().//允许静态资源可以直接访问
		antMatchers("/ok/**").hasRole("USER").
		anyRequest().authenticated().
		and().formLogin().loginPage("/login").permitAll().successForwardUrl("/ok").
		and().logout().and().
		csrf().disable();
	}
}
