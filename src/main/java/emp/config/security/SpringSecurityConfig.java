package emp.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {




    public SpringSecurityConfig() {
        super();
    }
//    In case using locally uncomment it for testing.
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("jim").password("{noop}demo").roles("ADMIN").and()
//                .withUser("bob").password("{noop}demo").roles("USER").and()
//                .withUser("ted").password("{noop}demo").roles("USER","ADMIN");
//    }

    /** This class allows you to override the default Web Security configuration */

        @Override
        protected void configure(HttpSecurity http) throws Exception
        {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    // Allow access to URLs required for form login
                    .antMatchers("/login", "/resources/**", "/j_security_check", "/css/**", "css/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    // Use Java EE pre-authentication and map these roles to Spring Security
                    .jee().mappableRoles("USER", "ADMIN");
        }

    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        // Register our login page (found in the resources/templates folder) as a ViewController
        // The template 'login' HTML uses the Thymeleaf template engine for simplicity and convenience
        registry.addViewController("/login").setViewName("login");
    }


}
