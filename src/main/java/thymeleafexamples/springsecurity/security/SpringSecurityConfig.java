/*
 * =============================================================================
 *
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * =============================================================================
 */
package thymeleafexamples.springsecurity.security;

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


//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//                .formLogin()
//                .loginPage("/login.html")
//                .failureUrl("/login-error.html")
//            .and()
//                .logout()
//                .logoutSuccessUrl("/index.html")
//            .and()
//                .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasRole("USER")
//                .antMatchers("/shared/**").hasAnyRole("USER","ADMIN")
//            .and()
//                .exceptionHandling()
//                .accessDeniedPage("/403.html");
//
//    }


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
                    .antMatchers("/login", "/resources/**", "/j_security_check", "WEB-INF/css/**").permitAll()
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
