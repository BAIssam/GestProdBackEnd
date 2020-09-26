package org.bai.produit.GestProd.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	//Authorisation Role -> Acces
	protected void configure(HttpSecurity http) throws Exception{
		http
		.httpBasic()
			.and()
				.authorizeRequests()
					.antMatchers("/GestProd/api/**", "/api/**")
						.hasRole("USER")
					.antMatchers("/**")
						.hasRole("ADMIN").and()
							.csrf().disable().headers()
								.frameOptions().disable();
		
		/*http.csrf().disable()
		.httpBasic().and().authorizeRequests()		
		.anyRequest().authenticated();
		
		http.csrf().disable().cors().disable()
		.httpBasic().and().authorizeRequests()        
        .antMatchers("/**").permitAll()        
        .anyRequest().authenticated();*/
	}
	
	//Authentification Utilisateur -> Role
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(
				NoOpPasswordEncoder.getInstance());
		
		/*auth.inMemoryAuthentication().passwordEncoder(
				NoOpPasswordEncoder.getInstance())
		.withUser("user").password("password1").roles("USER")
		.and()
		.withUser("admin").password("password2").roles("ADMIN","USER");*/
	}

}
