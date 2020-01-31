package pe.gob.juntos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

	@Autowired
	    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		 auth.authenticationProvider(customAuthenticationProvider);
	    }


	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http		
		.csrf().disable()		
		.authorizeRequests()
			.antMatchers(	"/",
							"/login"
							).permitAll()
			.antMatchers("/**").hasAuthority("JUNTOS").anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.failureUrl("/login?error=true")
			.defaultSuccessUrl("/bandeja",true)
			.usernameParameter("username")
			.passwordParameter("password")
			.and()
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").and()
		.exceptionHandling()
			.accessDeniedPage("/accesoDenegado")
			;		
		
		http.headers().cacheControl();	
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/pdf/**", "/archivos/**", "/signed/**");
	}

}