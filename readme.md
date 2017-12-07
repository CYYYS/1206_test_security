1. 项目搭建:
	这是一个关于spring security的项目.配置文件需要必须引入security和web的依赖包,由于使用了thymeleaf,所以需要导入thymeleaf,前台的一些样式设置使用了bootstrap,
所以需要导入bootstrap依赖.否则,程序是无法正常运行.
	这里使用的注解的方式配置了spring security的配置信息.首先自定义一个类,必须放在你项目的groupid对应的包下面.
	1. 这个类需要继承WebSecurityConfigurerAdapter.
	2. 注入AuthenticationManagerBuilder,要通过一个方法注入,而且指定在项目启动时在内存用户存储中进行请求的权限校验.
	3. 重写configure方法.
	```
	
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
``` 
 
	
	
	
	
	