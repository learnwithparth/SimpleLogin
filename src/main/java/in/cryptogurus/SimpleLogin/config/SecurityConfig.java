package in.cryptogurus.SimpleLogin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http  
        .authorizeRequests()  
        .antMatchers( "/public/**").permitAll()  
        .anyRequest().authenticated()  
            .and()  
        .formLogin()  
            .loginPage("/login.html")  
            .failureUrl("/login-error.html")  
            .permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("user")
				.password("{noop}pass") // Spring Security 5 requires specifying the password storage format
				.roles("USER");
	}
}
