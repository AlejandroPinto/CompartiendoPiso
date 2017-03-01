package es.urjc.code.daw.compartiendoPiso.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public UserRepositoryAuthProvider userRepoAuthProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/index").permitAll();
		http.authorizeRequests().antMatchers("/signin").permitAll();
		http.authorizeRequests().antMatchers("/register").permitAll();
		http.authorizeRequests().antMatchers("/offer-house").permitAll();
		http.authorizeRequests().antMatchers("/offer-room").permitAll();
		http.authorizeRequests().antMatchers("/user").permitAll();
		http.authorizeRequests().antMatchers("/contact").permitAll();
		
		
		// URLs that need authentication to access to it
		http.authorizeRequests().antMatchers("/admin").authenticated();

	    // Private pages (all other pages)
        //http.authorizeRequests().antMatchers("/user{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
		
		// Login form
        http.formLogin().loginPage("/signin");
        http.formLogin().usernameParameter("name");
        http.formLogin().passwordParameter("pass");
        http.formLogin().defaultSuccessUrl("/user/{id}"); //¿?¿?
        //http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutUrl("/disconnect");
        http.logout().logoutSuccessUrl("/");
        
        // Other URLs can be accessed without authentication
     	//http.authorizeRequests().anyRequest().permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Database authentication provider
		auth.authenticationProvider(userRepoAuthProvider);
	}
}
