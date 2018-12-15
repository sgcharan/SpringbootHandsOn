package com.sampleProject.configs;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    DataSource dataSource;
	
	@Bean
	public PasswordEncoder encoder(){
		return new BCryptPasswordEncoder();
	}

	//Enable jdbc authentication
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder());
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("*/css/**","*/images/**","*/js/**","*/jquery/**","*/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	/*	http.authorizeRequests().antMatchers("/register").permitAll().antMatchers("/","/welcome").hasAnyRole("USER", "ADMIN")
				.antMatchers("/products").hasAnyRole("USER", "ADMIN")
				.antMatchers("/cart").hasAnyRole("USER").antMatchers("/modifyUser")
				.hasAnyRole("ADMIN").anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.and().logout().permitAll();

		http.csrf().disable();*/
	
		http.authorizeRequests()
		.antMatchers("/","/welcome","/products","/productsMulti","/cart","/cartMulti","/invoiceMulti","/addOtherUsers","/orderFor","*/css/**","*/js/**","*/jquery/**").access("hasRole('USER')").and().formLogin().loginPage("/login").permitAll();
		http.csrf().disable();
		http.headers().frameOptions().sameOrigin();
	}
	
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager() throws Exception {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		return jdbcUserDetailsManager;
	}

    //remove this in memory authentication configuration
	// @Autowired
	//public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
	//	authenticationMgr.inMemoryAuthentication().withUser("admin").password("admin").authorities("ROLE_USER").and()
	//			.withUser("javainuse").password("javainuse").authorities("ROLE_USER", "ROLE_ADMIN");
	//} 

}
