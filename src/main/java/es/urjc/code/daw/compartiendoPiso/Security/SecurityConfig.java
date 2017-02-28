package es.urjc.code.daw.compartiendoPiso.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthProvider userRepoAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		configureUrlAuthorization(http);

		// Disable CSRF protection (it is difficult to implement with ng2)
		http.csrf().disable();

		// Use Http Basic Authentication
		http.httpBasic();

		// Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});
	}

	private void configureUrlAuthorization(HttpSecurity http) throws Exception {

		// APP: This rules have to be changed by app developer

		// URLs that need authentication to access to it
		//http.authorizeRequests().antMatchers(HttpMethod.POST, "/books/**").hasRole("USER");
		//http.authorizeRequests().antMatchers(HttpMethod.PUT, "/books/**").hasRole("USER");
		//http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN");
		
		//http.authorizeRequests().antMatchers(HttpMethod.GET, "/").hasRole("USER");
		//http.authorizeRequests().antMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN");

		// Other URLs can be accessed without authentication
		//http.authorizeRequests().anyRequest().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Database authentication provider
		auth.authenticationProvider(userRepoAuthProvider);
	}
}
